from collections import deque

q = deque()
q.appendleft(1)
q.appendleft(2)
q.appendleft(3)
print(q.pop())
print(q.pop())
q.appendleft(4)
print(q.pop())
print(q.pop())

