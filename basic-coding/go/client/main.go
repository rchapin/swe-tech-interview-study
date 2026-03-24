package main

import (
	"bufio"
	"context"
	"flag"
	"fmt"
	"io"
	"net"
	"os"
	"sync"
	"time"
)

const (
	getConnWaitTime   = 5
	connWriteDeadline = 5
	inputPrompt       = "Enter data to send to server: "
)

func connect(protocol, host, port string) (conn net.Conn, err error) {
	defer func() {
		if r := recover(); r != nil {
			conn = nil
			err = fmt.Errorf("%v", r)
		}
	}()
	conn, err = net.Dial(protocol, host+":"+port)
	if err != nil {
		panic(err)
	}
	return conn, nil
}

func getConnection(protocol, host, port string, successChan chan bool) net.Conn {
	for {
		conn, err := connect(protocol, host, port)
		if err != nil {
			fmt.Printf("Unable to make a connection, sleeping; getConnWaitTime=%d, err=%s\n", getConnWaitTime, err)
			time.Sleep(getConnWaitTime * time.Second)
		} else {
			return conn
		}
	}
}

func readInput(wg *sync.WaitGroup, cancel context.CancelFunc, inputChan chan string, successChan chan bool) {
	defer wg.Done()
	for {
		success := <-successChan
		if success {
			fmt.Printf(inputPrompt)
		}
		reader := bufio.NewReader(os.Stdin)
		input, err := reader.ReadString('\n')
		if err != nil {
			panic(err)
		}
		if input == "quit\n" {
			cancel()
			break
		}
		inputChan <- input
	}
}

func reconnect(conn net.Conn, err error, protocol, host, port string, successChan chan bool) net.Conn {
	fmt.Printf("Connection was closed, attempting to reconnect; errType=%T, err=%s\n", err, err)
	conn.Close()
	retval := getConnection(protocol, host, port, successChan)
	fmt.Println("Reconnected to server")
	return retval
}

func start(
	ctx context.Context,
	wg *sync.WaitGroup,
	inputChan chan string,
	successChan chan bool,
	protocol, host, port string) {

	conn := getConnection(protocol, host, port, successChan)
	defer conn.Close()
	defer wg.Done()
	// Write to the successChan to unblock the readInput go routine so it will then
	// prompt the user for input and write to the input channel.
	successChan <- true

ChanConsumer:
	for {
		select {
		case input := <-inputChan:
			// Nest a loop inside of this case statement so that we do not lose user
			// input if/when we have to reconnect to the server.
			for {
				// Validating that the connection is still open. We attempt to read from
				// the connection to determine if the TCP connection is still established
				// because writes to the connection will buffer in the kernel's TCP stack
				// before being flushed and will not reliably return an error right away.
				oneByte := make([]byte, 1)
				conn.SetReadDeadline(time.Now().Add(1 * time.Second))
				// if _, err := conn.Read(oneByte); err == io.EOF{
				if _, err := conn.Read(oneByte); err != nil {
					fmt.Printf("error type=%T\n", err)
					if err == io.EOF {
						conn = reconnect(conn, err, protocol, host, port, successChan)
						// Re-run this nested loop, re-validate the connection and if it is
						// valid we will then send the data that has already been pulled off
						// the channel.
						continue
					}
				}

				// fmt.Printf("Attempt to send data to the server, input=%s\n", input)
				conn.SetWriteDeadline(time.Now().Add(connWriteDeadline * time.Second))
				_, err := conn.Write([]byte(input))
				if err != nil {
					conn = reconnect(conn, err, protocol, host, port, successChan)
					continue
				}
				// Break out of the nested loop so that we can then block on either selecting
				// from the channel or the ctx Done notification.
				break
			}
			successChan <- true
		case <-ctx.Done():
			break ChanConsumer
		}
	}
}

func main() {
	// Define cli input and parse os.Args
	var host, port, protocol string
	flag.StringVar(&host, "host", "", "Hostname to which we will connect")
	flag.StringVar(&port, "port", "", "Port on which will will connect")
	flag.StringVar(&protocol, "protocol", "", "Protocol used for the connection")
	flag.Parse()
	fmt.Printf("client connecting; protocol=%s, host=%s, port=%s\n", protocol, host, port)

	ctx, cancel := context.WithCancel(context.Background())
	wg := &sync.WaitGroup{}
	inputChan := make(chan string)
	successChan := make(chan bool)

	wg.Add(1)
	go readInput(wg, cancel, inputChan, successChan)
	wg.Add(1)
	go start(ctx, wg, inputChan, successChan, protocol, host, port)
	wg.Wait()
	fmt.Println("Shutdown complete")
}
