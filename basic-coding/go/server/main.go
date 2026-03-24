package main

import (
	"flag"
	"fmt"
	"sync"
	"net"
	"context"
	"os"
	"os/signal"
	"syscall"
)

func setupSignalHandler(cancel context.CancelFunc) {
	c := make(chan os.Signal)
	signal.Notify(c, os.Interrupt, syscall.SIGTERM)
	go func() {
		signal := <-c
		fmt.Printf("Received shutdown signal; signal=%+v\n", signal)
		cancel()
	}()
}

func processConnection(ctx context.Context, wg *sync.WaitGroup, conn net.Conn) {
	defer wg.Done()
	buff := make([]byte, 1024)
	for {
		select {
		case <- ctx.Done():
			return
		default:
			rLen, err := conn.Read(buff)
			if err != nil {
				fmt.Printf("reading from connection errored; err=%+v\n", err)
				return
			}
			message := string(buff[:rLen])
			fmt.Printf("Received message; message=%s\n", message)
			wLen, err := conn.Write([]byte("ack"))
			fmt.Printf("Wrote bytes; wLen=%d\n", wLen)
		}
	}
}

func connect(wg *sync.WaitGroup, ctx context.Context, host, port, protocol string) {
	defer wg.Done()
	server, err := net.Listen(protocol, host + ":" + port)
	if err != nil {
		panic(err)
	}
	defer server.Close()
	fmt.Printf("Server listening; protocol=%s, host=%s, port=%s\n", protocol, host, port)
	for {
		select {
		case <- ctx.Done():
			return
		default:
			conn, err := server.Accept()
			if err != nil {
				panic(err)
			}
			fmt.Println("Client connected")
			wg.Add(1)
			go processConnection(ctx, wg, conn)
		}
	}
}

func main() {
	ctx, cancel := context.WithCancel(context.Background())
	setupSignalHandler(cancel)

	var host, port, protocol string
	flag.StringVar(&host, "host", "", "Hostname to which we will connect")
	flag.StringVar(&port, "port", "", "Port on which will will connect")
	flag.StringVar(&protocol, "protocol", "", "Protocol used for the connection")
	flag.Parse()
	wg := &sync.WaitGroup{}
	wg.Add(1)
	go connect(wg, ctx, host, port, protocol)
	wg.Wait()
}
