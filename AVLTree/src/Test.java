public class Test {
    public static void main(String... args) {
        AVLTree avlTree = new AVLTree();

        avlTree.root = avlTree.insert(avlTree.root, 9);
        avlTree.root = avlTree.insert(avlTree.root, 5);
        avlTree.root = avlTree.insert(avlTree.root, 10);
        avlTree.root = avlTree.insert(avlTree.root, 0);
        avlTree.root = avlTree.insert(avlTree.root, 6);
        avlTree.root = avlTree.insert(avlTree.root, 11);
        avlTree.root = avlTree.insert(avlTree.root, -1);
        avlTree.root = avlTree.insert(avlTree.root, 1);
        avlTree.root = avlTree.insert(avlTree.root, 2);
        avlTree.root = avlTree.insert(avlTree.root, 12);

        avlTree.inOrder(avlTree.root);

        System.out.println(avlTree.root);

        avlTree.root = avlTree.deleteNode(avlTree.root, 10);
        avlTree.root = avlTree.deleteNode(avlTree.root, 4);

        avlTree.preOrder(avlTree.root);

        avlTree = avlTree.subTree(avlTree.findNode(5));

        avlTree.preOrder(avlTree.root);
    }

}
