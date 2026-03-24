package main

import "fmt"


func hasPath(graph map[string][]string, src, dst string) bool {
	// Execute a DFS looking for our dst string node.  As soon
	// as we find it, we can return true
	if src == dst {
		// We are able to get to the dst node, return true
		return true
	}
	// Otherwise, we have to keep traversing the graph to see
	// if we can find the dst node
	retval := false
	neighbors := graph[src]
	for _, neighbor := range neighbors {
		if (hasPath(graph, neighbor, dst)) {
			return true
		}
	}
	return retval
}

func main() {

	testData := []struct{
		graph map[string][]string
		src string
		dst string
	}{
		{
			graph: map[string][]string{
				"f": []string{"g", "i"},
				"g": []string{"h"},
				"h": []string{},
				"i": []string{"g", "k"},
				"j": []string{"i"},
				"k": []string{},
			},
			src: "f",
			dst: "k",
		},
		{
			graph: map[string][]string{
				"f": []string{"g", "i"},
				"g": []string{"h"},
				"h": []string{},
				"i": []string{"g", "k"},
				"j": []string{"i"},
				"k": []string{},
			},
			src: "f",
			dst: "j",
		},
		{
			graph: map[string][]string{
				"f": []string{"g", "i"},
				"g": []string{"h"},
				"h": []string{},
				"i": []string{"g", "k"},
				"j": []string{"i"},
				"k": []string{},
			},
			src: "i",
			dst: "h",
		},
	}


	for _, t := range testData {
		fmt.Println(t)
		fmt.Println(hasPath(t.graph, t.src, t.dst))
	}
}
