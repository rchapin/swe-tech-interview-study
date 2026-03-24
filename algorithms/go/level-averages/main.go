package main

import (
	"fmt"
)

type Node struct {
	val int
	left *Node
	right *Node
	level int
}

func traverseTree(n *Node, level int, levelVals map[int][]int) {
	// Add the value of this node to the levelVals for the current
	// level and then check to see if we have any child nodes
	levelVals[level] = append(levelVals[level], n.val)
	if n.left != nil {
		traverseTree(n.left, level+1, levelVals)
	}
	if n.right != nil {
		traverseTree(n.right, level+1, levelVals)
	}
}

func calcAverages(levelVals map[int][]int) []float64 {
	fmt.Println(levelVals)
	retval := make([]float64, len(levelVals))
	j := 1
	for i := 0; i < len(levelVals); i++ {
		vals := levelVals[j]
		fmt.Println(vals)
		numElems := float64(len(vals))
		var total float64
		for _, val := range vals {
			total += float64(val)
		}
		retval[i] = total/numElems
		j++
	}
	return retval
}

func levelAverages(root *Node) []float64 {

	// It doesn't really matter how we traverse the tree as long as we know what level
	// we are on, we can add the value to the levels map and then calulcate the
	// average when we are done.
	levelVals := map[int][]int{}
	traverseTree(root, 1, levelVals)
	return calcAverages(levelVals)
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

	fmt.Println(levelAverages(a))
}
