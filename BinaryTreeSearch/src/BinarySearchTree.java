import java.util.ArrayList;

public class BinarySearchTree {

    Node root;
    int size = 0;

    int size() {
        return size;
    }

    void addNode(int key, String name) {
        if (ifNodeExists(key)) {
            Node newNode = new Node(key, name);
            Node focusNode = findNode(key);
            focusNode.name = newNode.name;
            return;
        }
        Node newNode = new Node(key, name);
        size++;
        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;
            while (true) {
                parent = focusNode;
                if (key < focusNode.key) {
                    focusNode = focusNode.leftChild;
                    if (focusNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    focusNode = focusNode.rightChild;
                    if (focusNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    void inOrderTraverse(Node focusNode) {
        if (focusNode != null) {
            inOrderTraverse(focusNode.leftChild);
            System.out.println(focusNode);
            inOrderTraverse(focusNode.rightChild);
        }
    }

    void preOrderTraverse(Node focusNode) {
        if (focusNode != null) {
            System.out.println(focusNode);
            preOrderTraverse(focusNode.leftChild);
            preOrderTraverse(focusNode.rightChild);
        }
    }

    void postOrderTraverse(Node focusNode) {
        if (focusNode != null) {
            postOrderTraverse(focusNode.leftChild);
            postOrderTraverse(focusNode.rightChild);
            System.out.println(focusNode);
        }
    }

    Node findNode(int key) {
        Node focusNode = root;

        while (focusNode.key != key) {
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null) {
                System.out.println("Node with key of: " + key + " was not found");
                return null;
            }
        }
        return focusNode;
    }

    boolean remove(int key) {
        Node focusNode = root;
        Node parent = root;
        boolean isItALeftChild = true;

        while (focusNode.key != key) {
            parent = focusNode;
            if (key < focusNode.key) {
                isItALeftChild = true;
                focusNode = focusNode.leftChild;
            } else {
                isItALeftChild = false;
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null)
                return false;
        }

        if (focusNode.leftChild == null && focusNode.rightChild == null) {
            if (focusNode == root)
                root = null;
            else if (isItALeftChild) {
                parent.leftChild = null;
            } else
                parent.rightChild = null;
        } else if (focusNode.rightChild == null) {
            if (focusNode == root)
                root = focusNode.leftChild;
            else if (isItALeftChild)
                parent.leftChild = focusNode.leftChild;
            else
                parent.rightChild = focusNode.leftChild;
        } else if (focusNode.leftChild == null) {
            if (focusNode == root)
                root = focusNode.rightChild;
            else if (isItALeftChild)
                parent.leftChild = focusNode.rightChild;
            else
                parent.rightChild = null;
        } else {
            Node replacementNode = getReplacement(focusNode);
            if (focusNode == root)
                root = replacementNode;
            else if (isItALeftChild)
                parent.leftChild = replacementNode;
            else
                parent.rightChild = replacementNode;
        }
        size--;
        return true;
    }

    private Node getReplacement(Node nodeToReplace) {
        Node replacementParent = nodeToReplace;
        Node replacement = nodeToReplace;
        Node focusNode = nodeToReplace.rightChild;

        while (focusNode.leftChild != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }
        if (replacement != nodeToReplace.rightChild) {
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = nodeToReplace.rightChild;
        }
        return replacement;
    }

    BinarySearchTree subTree(Node rootNode) {
        BinarySearchTree subTree = new BinarySearchTree();
        subTree.subTreeAdd(rootNode);
        return subTree;
    }

    private void subTreeAdd(Node focusNode) {
        if (focusNode != null) {
            addNode(focusNode.key, focusNode.name);
            subTreeAdd(focusNode.leftChild);
            subTreeAdd(focusNode.rightChild);
        }
    }

    Node[] path(Node nodeFrom, Node nodeTo) {
        if (nodeFrom == null || nodeTo == null) {
            System.out.println("Can't make such a path");
            return null;
        }
        else if (subTree(nodeFrom).ifNodeExists(nodeTo.key)) {
            ArrayList<Node> pathArrayList = new ArrayList<Node>();
            Node focusNode = nodeFrom;
            pathArrayList.add(focusNode);
            while (focusNode.key != nodeTo.key) {
                if(focusNode.key < nodeTo.key)
                    focusNode = focusNode.rightChild;
                else
                    focusNode = focusNode.leftChild;
                pathArrayList.add(focusNode);
            }
            Node[] pathArray = new Node[pathArrayList.size()];
            for(int index = 0; index < pathArray.length; index++)
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
        if (nodeFrom.key == nodeTo.key) {
            System.out.println(nodeFrom.key);
            return null;
        }
        Node focusNode = root;
        while (focusNode != null) {
            if (nodeTo.key < focusNode.key && nodeFrom.key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else if (focusNode.key < nodeTo.key && focusNode.key < nodeFrom.key) {
                focusNode = focusNode.rightChild;
            } else
                break;
        }
        ArrayList<Node> pathNodeFrom = new ArrayList<Node>();
        ArrayList<Node> pathNodeTo = new ArrayList<Node>();
        pathNodeTo.add(focusNode);
        while (true) {
            if (focusNode.key == nodeTo.key)
                break;
            if (focusNode.key < nodeTo.key) {
                focusNode = focusNode.rightChild;
            } else
                focusNode = focusNode.leftChild;
            pathNodeTo.add(focusNode);
        }
        focusNode = pathNodeTo.get(0);
        while (true) {
            if (focusNode.key == nodeFrom.key)
                break;
            if (focusNode.key < nodeFrom.key) {
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
                System.out.print("\n\n" + element.key);
            } else
                System.out.print(" --> " + element.key);
        }
    }

    boolean ifNodeExists(int key) {
        Node focusNode = root;
        if (size > 0) {
            while (focusNode.key != key) {
                if (key < focusNode.key) {
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

    Node minNode() {
        Node focusNode = root;

        while (focusNode.leftChild != null) {
            focusNode = focusNode.leftChild;
        }
        return focusNode;
    }

    Node maxNode() {
        Node focusNode = root;

        while (focusNode.rightChild != null) {
            focusNode = focusNode.rightChild;
        }
        return focusNode;
    }

}
