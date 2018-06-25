public class Test {
    public static void main(String... args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        binarySearchTree.addNode(50, "Basia");
        binarySearchTree.addNode(15, "Kasia");
        binarySearchTree.addNode(22, "Asia");
        binarySearchTree.addNode(45, "Jacek");
        binarySearchTree.addNode(35, "Malgosia");
        binarySearchTree.addNode(75, "Iga");
        binarySearchTree.addNode(80, "Ludwik");
        binarySearchTree.addNode(25, "Pawel");
        binarySearchTree.addNode(30, "Paffele");
        binarySearchTree.addNode(50, "Michal");

        System.out.println("In order traverse:");
        binarySearchTree.inOrderTraverse(binarySearchTree.root);
        System.out.println("\nPost order traverse");
        binarySearchTree.postOrderTraverse(binarySearchTree.root);
        System.out.println("\nPre order traverse");
        binarySearchTree.preOrderTraverse(binarySearchTree.root);

        System.out.println("\nSearching for key of 30");
        System.out.println(binarySearchTree.findNode(30));

        System.out.println("\nSearching for key of 40");
        System.out.println(binarySearchTree.findNode(40));

        System.out.println("\nRemoving key of 30");
        System.out.println(binarySearchTree.remove(30));

        System.out.println("\nSearching for key of 30");
        binarySearchTree.findNode(30);

        System.out.println();

        binarySearchTree.inOrderTraverse(binarySearchTree.root);

        System.out.println("\n\nMaximum key of binary tree = " + binarySearchTree.maxNode());

        System.out.println("\n\nMinimum key of binary tree = " + binarySearchTree.minNode());

        System.out.println("In order traverse:");

        binarySearchTree.inOrderTraverse(binarySearchTree.findNode(45));

        System.out.println("BinarySearchTree size equals: " + binarySearchTree.size());

        System.out.println("\n\n\n");

        BinarySearchTree subTree45 = binarySearchTree.subTree(binarySearchTree.findNode(45));

        System.out.println();

        System.out.println("Subtree in order traverse:");

        subTree45.inOrderTraverse(subTree45.root);

        System.out.println("\n\n" + subTree45.root);
        System.out.println(subTree45.size());

        binarySearchTree.path(binarySearchTree.findNode(50),binarySearchTree.findNode(80));

        binarySearchTree.pathWhole(binarySearchTree.findNode(25),binarySearchTree.findNode(80));

        subTree45.path(subTree45.findNode(45), binarySearchTree.findNode(25));

        subTree45.pathWhole(subTree45.findNode(25), subTree45.findNode(35));
    }
}
