package main

import (
	"fmt"
)

type Node struct {
	val int
	left *Node
	right *Node
}

func maxPathSum(n *Node) int {
	if n.left == nil && n.right == nil {
		return n.val
	}

	var leftTree, rightTree, retval int

	if n.left != nil {
		leftTree = maxPathSum(n.left)
	}
	if n.right != nil {
		rightTree = maxPathSum(n.left)
	}

	if leftTree > rightTree {
		retval = leftTree + n.val
	} else {
		retval = rightTree + n.val
	}

	return retval
}

func main() {

	a := &Node{val: 3}
	b := &Node{val: 11}
	c := &Node{val: 4}
	d := &Node{val: 4}
	e := &Node{val: -2}
	f := &Node{val: 1}

	a.left = b
	a.right = c
	b.left = d
	b.right = e
	c.right = f

	//       3
	//    /    \
	//   11     4
	//  / \      \
	// 4   -2     1

	fmt.Println(maxPathSum(a))
}
