package main

import (
	"fmt"
	"unicode"
	"strconv"
	"strings"
	"os"
)

func uncompress(input string) string {
	fmt.Printf("Running uncompress; input=%s\n", input)
	// Iterate over each char in the string.  If the character is a numerical value
	// then we need to keep walking through the chars in the string until we find
	// a non-numerical character. We will, with the numerical chars, generate an
	// int and save that value.  We will then use the next, non-numerical char for
	// the one to output n number of time.
	numChars := 0
	retval := ""
   orderOfMagnitude := 1

	for _, c := range input {
		charStr := string(c)
		fmt.Printf("charStr=%s\n", charStr)

		if unicode.IsNumber(c) {
			numChar, err := strconv.Atoi(fmt.Sprintf("%c", c))
			if err != nil {
				panic(err)
			}
			fmt.Println(numChar)
			numChars = (numChars * orderOfMagnitude) + numChar

			orderOfMagnitude = orderOfMagnitude * 10
		   fmt.Printf("numChars=%d, orderOfMagnitude=%d\n", numChars, orderOfMagnitude)
		} else {
			// We need to render out this char n number of times
			retval = retval + strings.Repeat(string(charStr), numChars)

			// Reset the values that we use to generate/grow our numChars value
			orderOfMagnitude = 1
			numChars = 0
		}

	}
	return retval
}

func validate(expected, actual string) {
	if expected != actual {
		panic(fmt.Errorf("expected did not equal actual"))
	}
}

func main() {
	input := os.Args[1]
	expected := os.Args[2]
	actual := uncompress(input)
	fmt.Println(actual)
	validate(expected, actual)
}
