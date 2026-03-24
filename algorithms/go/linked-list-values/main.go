package main

import (
	"fmt"
)

type Node struct {
	next *Node
	val  string
}

func walkList(n *Node) []string {
	retval := []string{}
	current := n
	for {
		retval = append(retval, current.val)
		if current.next != nil {
			current = current.next
		} else {
			break
		}
	}
	return retval
}

func main() {
	a := &Node{val: "a"}
	b := &Node{val: "b"}
	c := &Node{val: "c"}
	d := &Node{val: "d"}
	e := &Node{val: "e"}

	a.next = b;
	b.next = c;
	c.next = d;
	d.next = e;

	fmt.Println(walkList(a))
}
