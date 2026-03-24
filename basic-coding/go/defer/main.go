package main

import (
	"fmt"
	"os"
	"strconv"
)

func mightPanic(i int) (retval error) {
	defer func() {
		if r := recover(); r != nil {
			retval = fmt.Errorf("recovered; r=%v", r)
		}
	}()
	if i%2 == 0 {
		panic("encountered an error")
	}
	return nil
}

func doSomething(i int) error {
	defer fmt.Println("doSomething defer 1")

	// Try to do something with data provided
	err := mightPanic(i)
	if err != nil {
		fmt.Printf("mightPanic returned an error; err=%+v\n", err)
	} else {
		fmt.Printf("mightPanic returned nil\n")
	}
	fmt.Println("After call to mightPanic")

	return nil
}

func main() {
	iStr := os.Args[1]
	i, err := strconv.Atoi(iStr)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Input is i=%d\n", i)

	defer fmt.Println("main defer 1")
	defer fmt.Println("main defer 2")
	fmt.Println("About to doSomething")
	err = doSomething(i)
	fmt.Printf("After calling doSomething; err=%+v\n", err)
	defer fmt.Println("main defer 3")
}
