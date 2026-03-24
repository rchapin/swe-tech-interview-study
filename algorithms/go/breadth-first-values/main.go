package main

import "fmt"

type Node struct {
	val string
	left *Node
	right *Node
}

func bfs(root *Node) []string {
	retval := []string{}
	q := make(chan *Node, 256)
	q <- root

	for {
		if len(q) == 0 {
			break
		}
		current := <-q
		retval = append(retval, current.val)
		fmt.Println(*current)
		if current.left != nil {
			q <- current.left
		}
		if current.right != nil {
			q <- current.right
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
	f := &Node{val: "f"}

	a.left = b
	a.right = c
	b.left = d
	b.right = e
	c.right = f
	fmt.Println(bfs(a))
}
