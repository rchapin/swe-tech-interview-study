package user

import "ryanchapin.com/example/go-mock-tutorial/doer"

type User struct {
	doer.Doer
}

func (u *User) Use() error {
	return u.Doer.DoSomething(123, "Hello GoMock")
}
