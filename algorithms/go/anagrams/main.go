package main

import (
	"fmt"
	"reflect"
)

const space = ' '

func stringToSet(s string) map[rune]int {
	retval := make(map[rune]int)
	for _, c := range s {
		if c == space {
			continue
		}
		count := retval[c]
		count++
		retval[c] = count
	}
	return retval
}

func anagrams(a, b string) bool {
	aSet := stringToSet(a)
	bSet := stringToSet(b)
	return reflect.DeepEqual(aSet, bSet)
}

func main() {

	test_data := []struct{
		A string
		B string
		E bool
	}{
		{A: "restful", B: "fluster", E: true, },
		{A: "cats", B: "tocs", E: false,},
		{A: "monkeyswrite", B: "newyorktimes", E: true},
		{A: "acs fireplug satirical", B: "supercalifragalistic", E: true},
		{A: "aaac", B: "ac", E: false},
	}

	for _, t := range test_data {
		actual := anagrams(t.A, t.B)
		if actual != t.E {
			panic(fmt.Errorf("a=%s, b=%s, actual=%t\n", t.A, t.B, t.E))
		}
	}

	fmt.Println(test_data)
}
