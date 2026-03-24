
class Node(object):

    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

def tree_sum(node):
    # Create a stack and push the provided node onto it
    stack = []
    stack.append(node)
    sum = 0

    while len(stack) > 0:
        curr_node = stack.pop()
        sum = sum + curr_node.val
        if curr_node.left != None:
            stack.append(curr_node.left)
        if curr_node.right != None:
            stack.append(curr_node.right)

    return sum


a = Node(3)
b = Node(11)
c = Node(4)
d = Node(4)
e = Node(-2)
f = Node(1)

a.left = b
a.right = c
b.left = d
b.right = e
c.right = f
print(f"sum={tree_sum(a)}")

a = Node(1)
b = Node(6)
c = Node(0)
d = Node(3)
e = Node(-6)
f = Node(2)
g = Node(2)
h = Node(2)

a.left = b
a.right = c
b.left = d
b.right = e
c.right = f
e.left = g
f.right = h
print(f"sum={tree_sum(a)}")
