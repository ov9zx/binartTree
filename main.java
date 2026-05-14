class Node {
    int value;
    Node leftChild;
    Node rightChild;

    public Node(int value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }
}

class Tree {
    Node root;

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.leftChild = insertRecursive(current.leftChild, value);
        }
        else if (value >= current.value) {
            current.rightChild = insertRecursive(current.rightChild, value);
        }

        return current;
    }
    
    public int findMin() {
        if (root == null) return -1;

        Node current = root;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current.value;
    }
    
    public int findMax(){
        if (root == null) return -1;
        Node current = root;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current.value;
    }
    
    public Node search(int key) {
        Node current = root;

        while (current != null && current.value != key) {
            if (key < current.value) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return current;
    }
    
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) return null;

        if (value < current.value) {
            current.leftChild = deleteRecursive(current.leftChild, value);
        } else if (value > current.value) {
            current.rightChild = deleteRecursive(current.rightChild, value);
        } else {
            if (current.leftChild == null) return current.rightChild;
            if (current.rightChild == null) return current.leftChild;

            current.value = findSmallestValue(current.rightChild);
            current.rightChild = deleteRecursive(current.rightChild, current.value);
        }
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.leftChild == null ? root.value : findSmallestValue(root.leftChild);
    }
    
    public void printTree(Node current) {
        if (current != null) {
            printTree(current.leftChild);
            System.out.print(current.value + " ");
            printTree(current.rightChild);
        }
    }
    
    // Show tree with root at top
    public void showTree() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }
        showTreeRecursive(root, "", false);
    }
    
    private void showTreeRecursive(Node node, String prefix, boolean isLeft) {
        if (node == null) return;
        
        // Print right subtree first (to maintain left-right order)
        if (node.rightChild != null) {
            showTreeRecursive(node.rightChild, prefix + (isLeft ? "│   " : "    "), false);
        }
        
        // Print current node
        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.value);
        
        // Print left subtree
        if (node.leftChild != null) {
            showTreeRecursive(node.leftChild, prefix + (isLeft ? "    " : "│   "), true);
        }
    }
}

class main {
    public static void main(String[] args) {
        Tree tree1 = new Tree();
        
        tree1.insert(15);
        tree1.insert(12);
        tree1.insert(10);
        tree1.insert(22);
        tree1.insert(11);
        tree1.insert(41);
        tree1.insert(71);
        tree1.insert(3);
        tree1.insert(1);
        tree1.insert(7);
        tree1.insert(6);

        System.out.println("Min: " + tree1.findMin());
        System.out.println("Max: " + tree1.findMax());

        System.out.print("Tree content: ");
        tree1.printTree(tree1.root);
        System.out.println("\n");

        // Show tree structure
        System.out.println("Tree Structure:");
        tree1.showTree();

        int target = 7;
        Node result = tree1.search(target);
        if (result != null) {
            System.out.println("\nElement " + target + " found! ");
        } else {
            System.out.println("\nElement " + target + " not found! ");
        }
        
        System.out.println("\nDeleting 11...");
        tree1.delete(11);
        System.out.println("\nTree after deletion:");
        tree1.showTree();
    }
}
