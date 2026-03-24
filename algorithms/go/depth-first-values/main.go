package main

import "fmt"

type Node struct {
	val uint8
	left *Node
	right *Node
}

func dfs(root *Node, result *[]uint8) {
	// Visit the node
	fmt.Println(string(root.val))
	*result = append(*result, root.val)
	if root.left != nil {
		dfs(root.left, result)
	}
	if root.right != nil {
		dfs(root.right, result)
	}
}

func main() {

	a := &Node{val: 'a'}
	b := &Node{val: 'b'}
	c := &Node{val: 'c'}
	d := &Node{val: 'd'}
	e := &Node{val: 'e'}
	f := &Node{val: 'f'}

	a.left = b
	a.right = c
	b.left = d
	b.right = e
	c.right = f

	result := []uint8{}
	dfs(a, &result)

	fmt.Println(result)
}
