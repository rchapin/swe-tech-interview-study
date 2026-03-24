package main

import (
	"fmt"
)

type Base struct {
	b int
	tag string
}

func (b Base) Describe() string {
	return fmt.Sprintf("base belongs to us; b=%d", b.b)
}

func (b Base) DescribeTag() string {
	return fmt.Sprintf("Base; tag=%s", b.tag)
}

type Container struct {
	Base
	c string
	tag string
}

func (c Container) DescribeTag() string {
	return fmt.Sprintf("Container; tag=%s", c.tag)
}

func main() {
	co := Container{}
	co.b = 1
	co.c = "foo"
	fmt.Printf("%+v\n", co)
	fmt.Println(co.Describe())

	b1 := Base{b: 10, tag: "b's tag"}
	c1 := Container{Base: b1, c: "foo", tag: "Container's tag"}
	fmt.Printf("b1=%+v\n", b1)
	fmt.Printf("c1=%+v\n", c1)
	fmt.Println(b1.DescribeTag())
	fmt.Println(c1.DescribeTag())
	fmt.Println(c1.Base.DescribeTag())
}
