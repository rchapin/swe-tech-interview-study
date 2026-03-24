package main

import (
	"fmt"
	"context"
	"os"
	"os/signal"
	"strconv"
	"syscall"
	"time"
	"hash/fnv"
	"encoding/binary"
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

func count(ctx context.Context, cancel context.CancelFunc, tickerDuration int, timerFactor int) {
	timer := time.NewTimer(time.Duration(tickerDuration * timerFactor) * time.Second)
	ticker := time.NewTicker(time.Duration(tickerDuration) * time.Second)
	counter := 0
	for {
		select {
		case <- ticker.C:
			hasher := fnv.New64a()
			buf := make([]byte, 4)
			binary.LittleEndian.PutUint32(buf, uint32(counter))
			hasher.Write(buf)
			hash := hasher.Sum64()

			fmt.Printf("hash=%T %+v, 0x%x, %d, 0x%x\n", hash, hash, hash, counter, counter)
			counter++
		case <- timer.C:
			fmt.Println("Timer timed out")
			cancel()
		case <- ctx.Done():
			fmt.Printf("Received ctx.Done, ctx.Err=%+v\n", ctx.Err())
			return
		}
	}
}

func main() {
	ctx, cancel := context.WithCancel(context.Background())
	setupSignalHandler(cancel)
	tickerDurationStr := os.Args[1]
	tickerDuration, err := strconv.Atoi(tickerDurationStr)
	if err != nil {
		panic(err)
	}
	timerFactorStr := os.Args[2]
	timerFactor, err := strconv.Atoi(timerFactorStr)
	if err != nil {
		panic(err)
	}
	count(ctx, cancel, tickerDuration, timerFactor)
}
