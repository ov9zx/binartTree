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
        if (root == null) return -1; // إذا الشجرة فارغة

        Node current = root;
        // نضل نمشي يسار لحد ما نوصل لآخر عقدة
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current.value; // هاي هي أصغر قيمة
    }
    public int findMax(){
        if (root == null) return -1;
        Node current = root;
        // نضل نمشي يسار لحد ما نوصل لآخر عقدة
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current.value;
    }
    public Node search(int key) {
        Node current = root; // نبدأ من الجذر

        while (current != null && current.value != key) {
            // إذا القيمة أصغر نروح يسار، وإذا أكبر نروح يمين
            if (key < current.value) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return current; // راح يرجع العقدة إذا لقاها، أو null إذا ماكو
    }
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) return null;

        // البحث عن العقدة المراد حذفها
        if (value < current.value) {
            current.leftChild = deleteRecursive(current.leftChild, value);
        } else if (value > current.value) {
            current.rightChild = deleteRecursive(current.rightChild, value);
        } else {
            // وجدنا العقدة! هسة نطبق الحالات الثلاثة:

            // الحالة 1 و 2: عقدة بطفل واحد أو بدون أطفال
            if (current.leftChild == null) return current.rightChild;
            if (current.rightChild == null) return current.leftChild;

            // الحالة 3: عقدة بطفلين (نبحث عن البديل Преемник)
            current.value = findSmallestValue(current.rightChild);

            // حذف البديل من مكانه القديم
            current.rightChild = deleteRecursive(current.rightChild, current.value);
        }
        return current;
    }

    // ميثود مساعدة لإيجاد أصغر قيمة في الجهة اليمنى
    private int findSmallestValue(Node root) {
        return root.leftChild == null ? root.value : findSmallestValue(root.leftChild);
    }
    public void printTree(Node current) {
        if (current != null) {
            printTree(current.leftChild);  // 1. روح يسار (Рекурсия влево)
            System.out.print(current.value + " "); // 2. اطبع القيمة
            printTree(current.rightChild); // 3. روح يمين (Рекурсия вправо)
        }
    }

}
class main {
    public static void main(String[] args) {
        Tree tree1 = new Tree();
        // إضافة العناصر
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

        // طباعة النتائج بشكل صحيح
        System.out.println("Min: " + tree1.findMin());
        System.out.println("Max: " + tree1.findMax());

        System.out.print("Tree content: ");
        tree1.printTree(tree1.root); // طباعة العناصر مرتبة
        System.out.println();


        int target = 7;
        Node result = tree1.search(target);
        if (result != null) {
            System.out.println("Element " + target + " found! ");
        } else {
            System.out.println("Element " + target + " not found! ");
        }
        tree1.delete(11);//if deleted prnt deleted make this
    }
}
