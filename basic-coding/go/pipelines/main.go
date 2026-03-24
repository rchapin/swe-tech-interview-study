package main

import (
	"fmt"
	"sync"
)

func write(wg *sync.WaitGroup, nums ...int) <-chan int {
	retval := make(chan int)
	wg.Add(1)
	go func() {
		defer wg.Done()
		for _, n := range nums {
			retval <- n
		}
		close(retval)
	}()
	return retval
}

func read(wg *sync.WaitGroup, input <-chan int) {
	wg.Add(1)
	go func() {
		defer wg.Done()
		for n := range input{
			fmt.Println(n)
		}
	}()
}

func main() {
	wg := &sync.WaitGroup{}
	c := write(wg, 0, 1, 1, 2, 3, 5, 8)
	read(wg, c)
	wg.Wait()
}
