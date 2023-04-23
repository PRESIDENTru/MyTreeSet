import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MyTreeSet<T> {

    private int size;
    private Node root;

    private class Node {

        public T element;
        public Node left = null;
        public Node right = null;

        Node(T element) {
            this.element = element;
        }
    }

    public T add(T element) {
        if (root == null) {
            root = new Node(element);
            size++;
            return null;
        }
        return addHelper(root, element);
    }

    private T addHelper(Node node, T element) {
        Comparable<? super T> k = (Comparable<? super  T>) element;
        int cmp = k.compareTo(node.element);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(element);
                size++;
                return null;
            }
            return addHelper(node.left, element);
        }
        if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(element);
                size++;
                return null;
            }
            return addHelper(node.right, element);
        }
        return null;
    }

    private Node getNode(Object target) {
        Comparable<? super T> k = (Comparable<? super T>) target;
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.element);
            if (cmp < 0) {
               node = node.left;
            }
            if (cmp > 0) {
                node = node.right;
            }
            if (cmp == 0) {
                return node;
            }
        }
        return null;
    }

    public T contains(T element) {
        Node node = getNode(element);
        if (node == null) return null;
        return node.element;
    }
    private Node getParent(Object target) {
        Comparable<? super T> k = (Comparable<? super T>) target;
        Node node = root;
        Node parent = root;
        while (node != null) {
            int cmp = k.compareTo(node.element);
            if (cmp < 0) {
                parent = node;
                node = node.left;
            }
            if (cmp > 0) {
                parent = node;
                node = node.right;
            }
            if (cmp == 0) {
                return parent;
            }
        }
        return null;
    }
    public T remove(T element) {
        T oldElement = contains(element);
        if (element == root.element) root = delRecursive(element);
        else delRecursive(element);
        return oldElement;
    }

    private Node delRecursive(T element) {
        Node node = getNode(element);
        Node parent = getParent(element);
        if (node.left == null && node.right == null) {
            if (node == parent.left) {
                parent.left = null;
            }
            if (node == parent.right) {
                parent.right = null;
            }
            size--;
            return parent;
        }
        if (node.right == null) {
            if (node == parent.left) {
                parent.left = node.left;
            }
            if (node == parent.right) {
                parent.right = node.left;
            }
            size--;
            return parent;
        }
        if (node.left == null) {
            if (node == parent.left) {
                parent.left = node.right;
            }
            if (node == parent.right) {
                parent.right = node.right;
            }
            size--;
            return parent;
        }
        Node tempNode = findSmallest(node.right);
        delRecursive(tempNode.element);
        node.element = tempNode.element;
        return parent;
    }

    private Node findSmallest(Node node) {
        if (node.left == null) return node;
        else {
            return findSmallest(node.left);
        }
    }
    public void printTree() {
        recPrintTree(root);
        System.out.println("__________");
    }

    private void recPrintTree(Node node) {
        if (node.left != null) recPrintTree(node.left);
        System.out.println(node.element);
        if (node.right != null) recPrintTree(node.right);
    }
}
