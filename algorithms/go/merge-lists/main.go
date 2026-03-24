package main

import "fmt"

type Node struct {
	next *Node
	val int
}

func merge(a, b *Node) *Node {
	// Figure out which of the two nodes has the least value.  That will be
	// the new head node that we will return
	aPtr := a
	bPtr := b
	var retval *Node
	if a.val < b.val {
		retval = a
		aPtr = aPtr.next
	} else {
		retval = b
		bPtr = bPtr.next
	}
	current := retval

	// Now we have two pointers, each is pointing to the current node in each
	// list.  We also have a head which is the least of the two heads of the
	// input lists.
	for ; aPtr != nil && bPtr != nil; {
		// Figure out which of the current values is the least. Then update the
		// current node's next value to the least and update current to point
		// to that least value
		if aPtr.val < bPtr.val {
			current.next = aPtr
			aPtr = aPtr.next
		} else {
			current.next = bPtr
			bPtr = bPtr.next
		}
		current = current.next
	}

	// Figure out if we have a remainder at all
	var remainder *Node
	if aPtr != nil {
		remainder = aPtr
	}
	if bPtr != nil {
		remainder = bPtr
	}
	if remainder != nil {
		current.next = remainder
	}

	return retval
}

func printList(n *Node) {
	current := n
	for {
		fmt.Println(current.val)
		if current.next != nil {
			current = current.next
		} else {
			break
		}
	}
}

func main() {
	a := &Node{val: 1}
	b := &Node{val: 3}
	c := &Node{val: 6}
	d := &Node{val: 7}
	a.next = b
	b.next = c
	c.next = d

	q := &Node{val: 2}
	r := &Node{val: 3}
	s := &Node{val: 4}
	t := &Node{val: 5}
	u := &Node{val: 8}
	q.next = r
	r.next = s
	s.next = t
	t.next = u

	h := merge(a, q)
	printList(h)
}
