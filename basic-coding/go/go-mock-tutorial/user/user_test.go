package user_test

import (
	"testing"

	"github.com/golang/mock/gomock"
	"ryanchapin.com/example/go-mock-tutorial/mocks"
	"ryanchapin.com/example/go-mock-tutorial/user"
)

func TestUser(t *testing.T) {
	mockCtrl := gomock.NewController(t)
	defer mockCtrl.Finish()
	mockDoer := mocks.NewMockDoer(mockCtrl)
	mockDoer.EXPECT().DoSomething(123, "Hello GoMock").Return(nil).Times(1)

	testUser := &user.User{Doer: mockDoer}
	testUser.Use()
}
