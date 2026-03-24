package main

import (
	"fmt"
)

func pairSum(arr []int, t int) []int {
	// Map to store the value in the input array as the key, and its
	// index as the value
	m := make(map[int]int)
	for i, v := range arr {
		if v > t {
			// This value is greater than our target value so just
			// skip it altogether
			continue
		}
		remainder := t - v
		remainderIdx, ok := m[remainder]
		if ok {
			// We found a match
			return  []int{remainderIdx, i}
		}
		// Otherwise, store the value of this element in the map and
		// its index as the value
		m[v] = i
	}

	return nil
}

func main() {

	test_data := []struct{
		I []int
		T int
		E []int
	}{
		{I: []int{3, 2, 5, 4, 1}, T: 8, E: []int{0, 2}},
		{I: []int{4, 7, 9, 2, 5, 1}, T: 3, E: []int{3, 5}},
	}

	for _, t := range test_data {
		actual := pairSum(t.I, t.T)
		fmt.Printf("input=%+v, expected=%+v, actual=%+v\n", t.I, t.E, actual)
	}
}
