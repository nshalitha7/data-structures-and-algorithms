package ds;

import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int nodeCount;

    public class Node {
        private T data;
        private Node left;
        private Node right;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * add element to bst without using recursive
     * @param elem
     * @return
     */
//    public boolean add(T elem) {
//        Node trav = root;
//        Node newNode = new Node(elem, null, null);
//        if (root == null) {
//            root = newNode;
//            nodeCount = 1;
//            return true;
//        }
//        while(trav != null) {
//            Node left = trav.left;
//            Node right = trav.right;
//            if (elem == trav.data) return false;
//            if (less(elem, trav.data) && left == null) {
//                trav.left = newNode;
//                nodeCount++;
//                return true;
//            } else if(less(trav.data, elem) && right == null) {
//                trav.right = newNode;
//                nodeCount++;
//                return true;
//            }
//
//            if (less(trav.data, elem)) trav = right;
//            else trav = left;
//        }
//
//        return false;
//    }

    public boolean add(T elem) {
        if (contains(elem)) {
            return false;
        } else {
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    private Node add(Node root, T elem) {
        if (root == null) {
            root = new Node(elem, null, null);
        }

        if (less(elem, root.data)) {
            root.left = add(root.left, elem);
        } else if (less(root.data, elem)) {
            root.right = add(root.right, elem);
        }

        return root;
    }

    public boolean remove(T elem) {
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        } else {
            return false;
        }
    }

    private Node remove(Node root, T elem) {
        if (root == null) return null;

        if (less(elem, root.data)) {
            root.left = remove(root.left, elem);
        } else if(less(root.data, elem)) {
            root.right = remove(root.right, elem);
        } else { // root.data = elem, do remove here
            if (root.left == null && root.right == null) {
                root.data = null;
                root = null;
                return null;
            } else if (root.right == null) {
                Node left = root.left;
                root.data = null;
                root = null;
                return left;
            } else if (root.left == null) {
                Node right = root.right;
                root.data = null;
                root = null;
                return right;
            } else {
                Node node = digLeft(root.right);
                root.data = node.data;

                root.right = remove(root.right, node.data);
            }
        }

        return root;
    }

    private Node digLeft(Node node) {
        while (node.left != null)
            node = node.left;

        return node;
    }

    public boolean contains(T elem) {
        if (root == null || elem == null) return false;
        return contains(root, elem);
    }

    private boolean contains(Node trav, T elem) {
        if (trav == null) return false;
        if (trav.data.equals(elem)) return true;

        if (less(elem, trav.data)) {
            return contains(trav.left, elem);
        } else {
            return contains(trav.right, elem);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null) return 0;
        
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    // Search Algorithms - DFS(pre-order/post-order/in-order), BFS(level-order)
    public void preOrderTraversalRecursive(Node trav) {
        if (trav == null) return;

        System.out.print(trav.data + " ");
        preOrderTraversalRecursive(trav.left);
        preOrderTraversalRecursive(trav.right);
    }

    public void preOrderTraversal(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.data + " ");

            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null)stack.push(cur.left);
        }
    }

    public void inOrderTraversalRecursive(Node trav) {
        if (trav == null) return;

        inOrderTraversalRecursive(trav.left);
        System.out.print(trav.data + " ");
        inOrderTraversalRecursive(trav.right);
    }

    public void inOrderTraversal(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();

        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            System.out.print(node.data + " ");

            node = node.right;
        }
    }

    public void postOrderTraversalRecursive(Node trav) {
        if (trav == null) return;

        postOrderTraversalRecursive(trav.left);
        postOrderTraversalRecursive(trav.right);
        System.out.print(trav.data + " ");
    }

    public void postOrderTraversal(Node root) {
        Stack<Node> stack = new Stack<>();
        Node lastVisited = null;

        Node trav = root;
        while(!stack.isEmpty() || trav != null) {
            if (trav != null) {
                stack.push(trav);
                trav = trav.left;
            } else {
                Node node = stack.peek();
                if (node.right != null && lastVisited != node.right) {
                    trav = node.right;
                } else {
                    System.out.print(node.data + " ");
                    lastVisited = stack.pop();
                }
            }
        }
    }

    private void levelOrderTraversal(Node root) {
        if (root == null) return;
        int height = height(root);
        for (int i = 1; i <= height; i++) {
            printLevelOrder(root, i);
            System.out.println();
        }
    }

    private void printLevelOrder(Node node, int level) {
        if (node == null) return;
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            printLevelOrder(node.left, level - 1);
            printLevelOrder(node.right, level - 1);
        }
    }

    private boolean less(T elem1, T elem2) {
        return elem2.compareTo(elem1) > 0; // elem1 > elem2 returns true
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(8);
        bst.add(6);
        bst.add(18);
        bst.add(3);
        bst.add(7);
        bst.add(15);
        bst.add(20);
        bst.add(1);
        bst.add(4);
        bst.add(13);
        System.out.println("size: " + bst.size());
        System.out.println("height: " + bst.height());
        bst.preOrderTraversal(bst.root);
        System.out.println();
        bst.inOrderTraversal(bst.root);
        System.out.println();
        bst.postOrderTraversal(bst.root);
        System.out.println();
//        bst.levelOrderTraversal(bst.root);
//        System.out.println();
//        System.out.println("removed 3: " + bst.remove(3));
        System.out.println("size: " + bst.size());
        System.out.println("contains 7: " + bst.contains(7));
        System.out.println("contains 3: " + bst.contains(3));
//        bst.preOrderTraversal(bst.root);
        System.out.println();
        bst.levelOrderTraversal(bst.root);
        System.out.println();
    }
}
