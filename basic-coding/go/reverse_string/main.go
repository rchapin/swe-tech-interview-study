package main

import (
	"fmt"
	"bufio"
	"os"
	"os/signal"
	"syscall"
	"strings"
)

func setupSignalHandler() {
	c := make(chan os.Signal)
	signal.Notify(c, os.Interrupt, syscall.SIGTERM)
	go func() {
		signal := <-c
		fmt.Printf("Received shutdown signal; signal=%+v\n", signal)
		os.Exit(0)	
	}()
}

func reverseString(s string) string {
	r := []rune(s)
	for i, j := 0, len(r)-1; i < j; i, j = i+1, j-1 {
		r[i], r[j] = r[j], r[i]
	}
	return string(r)
}

func main() {
	setupSignalHandler()

	for {
		fmt.Printf("Please enter a string to be reversed:")
		reader := bufio.NewReader(os.Stdin)
		input, err := reader.ReadString('\n')
		input = strings.TrimSpace(input)
		if err != nil {
			panic(err)
		}
		fmt.Printf("Reversed string; input=%s, reversed=%s\n", input, reverseString(input))
	}
}
