import java.util.ArrayList;

public class AVLTree {

    Node root;
    int size;

    int height(Node focusNode) {
        if (focusNode == null)
            return 0;
        return focusNode.height;
    }

    private Node rotationRight(Node focusNode) {
        Node focusNodeLeft = focusNode.leftChild;
        Node focusNodeLeftRight = focusNodeLeft.rightChild;

        focusNodeLeft.rightChild = focusNode;
        focusNode.leftChild = focusNodeLeftRight;

        focusNode.height = max(height(focusNode.leftChild), height(focusNode.rightChild)) + 1;
        focusNodeLeft.height = max(height(focusNodeLeft.leftChild), height(focusNodeLeft.rightChild)) + 1;

        return focusNodeLeft;
    }

    private Node rotationLeft(Node focusNode) {
        Node focusNodeRight = focusNode.rightChild;
        Node focusNodeRightLeft = focusNodeRight.leftChild;

        focusNodeRight.leftChild = focusNode;
        focusNode.rightChild = focusNodeRightLeft;

        focusNode.height = max(height(focusNode.leftChild), height(focusNode.rightChild)) + 1;
        focusNodeRight.height = max(height(focusNodeRight.leftChild), height(focusNodeRight.rightChild)) + 1;

        return focusNodeRight;
    }

    private int getBalance(Node focusNode) {
        if (focusNode == null)
            return 0;
        return height(focusNode.leftChild) - height(focusNode.rightChild);
    }

    Node insert(Node focusNode, int value) {
        if (focusNode == null)
            return (new Node(value));

        if (value < focusNode.value)
            focusNode.leftChild = insert(focusNode.leftChild, value);
        else if (focusNode.value < value)
            focusNode.rightChild = insert(focusNode.rightChild, value);
        else
            return focusNode;

        size++;

        focusNode.height = max(height(focusNode.rightChild), height(focusNode.leftChild)) + 1;

        int balance = getBalance(focusNode);
        //LL
        if (balance > 1 && value < focusNode.leftChild.value)
            return rotationRight(focusNode);
        //RR
        if (balance < -1 && value > focusNode.rightChild.value)
            return rotationLeft(focusNode);
        //RL
        if (balance < -1 && value < focusNode.rightChild.value) {
            focusNode.rightChild = rotationRight(focusNode.rightChild);
            return rotationLeft(focusNode);
        }
        //LR
        if (balance > 1 && value > focusNode.leftChild.value) {
            focusNode.leftChild = rotationLeft(focusNode.leftChild);
            return rotationRight(focusNode);
        }
        return focusNode;
    }

    private Node minNode(Node maxNode) {
        Node focusNode = maxNode;

        while (focusNode.leftChild != null) {
            focusNode = focusNode.leftChild;
        }
        return focusNode;
    }

    private Node maxNode(Node minNode) {
        Node focusNode = minNode;

        while (focusNode.rightChild != null) {
            focusNode = focusNode.rightChild;
        }
        return focusNode;
    }

    Node deleteNode(Node focusNode, int value) {
        if (ifNodeExists(value)) {
            if (focusNode == null)
                return focusNode;

            if (value < focusNode.value)
                focusNode.leftChild = deleteNode(focusNode.leftChild, value);
            else if (value > focusNode.value)
                focusNode.rightChild = deleteNode(focusNode.rightChild, value);
            else {
                if (focusNode.leftChild == null || focusNode.rightChild == null) {
                    Node auxiliaryNode;
                    if (focusNode.leftChild == null)
                        auxiliaryNode = focusNode.rightChild;
                    else
                        auxiliaryNode = focusNode.leftChild;

                    if (auxiliaryNode == null) {
                        auxiliaryNode = focusNode;
                        focusNode = null;
                    } else
                        focusNode = auxiliaryNode;
                } else {
                    Node auxiliaryNode = minNode(focusNode.rightChild);
                    focusNode.value = auxiliaryNode.value;
                    focusNode.rightChild = deleteNode(focusNode.rightChild, auxiliaryNode.value);
                }
            }
            if (focusNode == null)
                return null;

            focusNode.height = max(height(focusNode.leftChild), height(focusNode.rightChild)) + 1;

            size--;

            int balance = getBalance(focusNode);
            //LL
            if (balance > 1 && getBalance(focusNode.leftChild) >= 0)
                return rotationRight(focusNode);
            //RR
            if (balance < -1 && getBalance(focusNode.rightChild) <= 0)
                return rotationLeft(focusNode);
            //RL
            if (balance < -1 && getBalance(focusNode.rightChild) > 0) {
                focusNode.rightChild = rotationRight(focusNode.rightChild);
                return rotationLeft(focusNode);
            }
            //LR
            if (balance > 1 && getBalance(focusNode.leftChild) < 0) {
                focusNode.leftChild = rotationLeft(focusNode.leftChild);
                return rotationRight(focusNode);
            }
            return focusNode;
        }
        System.out.println("Can't remove this value");
        return root;
    }

    private int max(int value_a, int value_b) {
        return (value_a > value_b) ? value_a : value_b;
    }

    void preOrder(Node focusNode) {

        if (focusNode != null) {
            System.out.print(focusNode + " ");
            preOrder(focusNode.leftChild);
            preOrder(focusNode.rightChild);
        }
        else
            System.out.println("koniec galezi");
    }

    void inOrder(Node focusNode) {

        if (focusNode != null) {
            inOrder(focusNode.leftChild);
            System.out.print(focusNode + " ");
            inOrder(focusNode.rightChild);
        }
    }

    void postOrder(Node focusNode) {

        if (focusNode != null) {
            postOrder(focusNode.leftChild);
            postOrder(focusNode.rightChild);
            System.out.print(focusNode + " ");
        }
    }

    boolean ifNodeExists(int value) {
        Node focusNode = root;
        if (size > 0) {
            while (focusNode.value != value) {
                if (value < focusNode.value) {
                    focusNode = focusNode.leftChild;
                } else {
                    focusNode = focusNode.rightChild;
                }
                if (focusNode == null) {
                    return false;
                }
            }
        } else
            return false;
        return true;
    }

    AVLTree subTree(Node rootNode) {
        AVLTree subTree = new AVLTree();
        subTree.subTreeAdd(rootNode.rightChild);
        System.out.println();
        return subTree;
    }

    void subTreeAdd(Node focusNode) {
        if (focusNode != null) {
            root = insert(root, focusNode.value);
            subTreeAdd(focusNode.leftChild);
            subTreeAdd(focusNode.rightChild);
        }
    }

    Node[] path(Node nodeFrom, Node nodeTo) {
        if (nodeFrom == null || nodeTo == null) {
            System.out.println("Can't make such a path");
            return null;
        } else if (subTree(nodeFrom).ifNodeExists(nodeTo.value)) {
            ArrayList<Node> pathArrayList = new ArrayList<Node>();
            Node focusNode = nodeFrom;
            pathArrayList.add(focusNode);
            while (focusNode.value != nodeTo.value) {
                if (focusNode.value < nodeTo.value)
                    focusNode = focusNode.rightChild;
                else
                    focusNode = focusNode.leftChild;
                pathArrayList.add(focusNode);
            }
            Node[] pathArray = new Node[pathArrayList.size()];
            for (int index = 0; index < pathArray.length; index++)
                pathArray[index] = pathArrayList.get(index);
            showPathWhole(pathArray);
            return pathArray;
        } else {
            System.out.println("Can't make such a path");
            return null;
        }
    }

    Node[] pathWhole(Node nodeFrom, Node nodeTo) {
        if (nodeTo == null || nodeFrom == null)
            return null;
        if (nodeFrom.value == nodeTo.value) {
            System.out.println(nodeFrom.value);
            return null;
        }
        Node focusNode = root;
        while (focusNode != null) {
            if (nodeTo.value < focusNode.value && nodeFrom.value < focusNode.value) {
                focusNode = focusNode.leftChild;
            } else if (focusNode.value < nodeTo.value && focusNode.value < nodeFrom.value) {
                focusNode = focusNode.rightChild;
            } else
                break;
        }
        ArrayList<Node> pathNodeFrom = new ArrayList<Node>();
        ArrayList<Node> pathNodeTo = new ArrayList<Node>();
        pathNodeTo.add(focusNode);
        while (true) {
            if (focusNode.value == nodeTo.value)
                break;
            if (focusNode.value < nodeTo.value) {
                focusNode = focusNode.rightChild;
            } else
                focusNode = focusNode.leftChild;
            pathNodeTo.add(focusNode);
        }
        focusNode = pathNodeTo.get(0);
        while (true) {
            if (focusNode.value == nodeFrom.value)
                break;
            if (focusNode.value < nodeFrom.value) {
                focusNode = focusNode.rightChild;
            } else
                focusNode = focusNode.leftChild;
            pathNodeFrom.add(focusNode);
        }
        Node[] path = new Node[pathNodeFrom.size() + pathNodeTo.size()];
        int pathIndex = 0;
        while (pathIndex < pathNodeFrom.size()) {
            path[pathIndex] = pathNodeFrom.get(pathNodeFrom.size() - 1 - pathIndex);
            pathIndex++;
        }
        for (int i = 0; i < pathNodeTo.size(); i++) {
            path[pathIndex] = pathNodeTo.get(i);
            pathIndex++;
        }
        showPathWhole(path);
        return path;
    }

    private void showPathWhole(Node[] path) {
        boolean wasFirst = false;
        for (Node element : path) {
            if (!wasFirst) {
                wasFirst = true;
                System.out.print("\n\n" + element.value);
            } else
                System.out.print(" --> " + element.value);
        }
    }

    Node findNode(int value) {
        Node focusNode = root;

        while (focusNode.value != value) {
            if (value < focusNode.value) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null) {
                System.out.println("Node with value of: " + value + " was not found");
                return null;
            }
        }
        return focusNode;
    }

}
