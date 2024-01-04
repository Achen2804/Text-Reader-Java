package mutils;

public class BinarySearchTree {

    // instance variable
    public Node root;

    // constructor for initialise the root to null BYDEFAULT
    public BinarySearchTree() {
        this.root = null;
    }

    // insert method to insert the new node
    public void insert(Node new_node) {
        this.root = insert(root, new_node);
    }

    public Node insert(Node root, Node new_node) {
        // Base Case: root is null or not
        if (root == null) {
            // Insert the new data, if root is null.
            root = new_node;
            // return the current root to his sub tree
            return root;
        }
        // Here checking for root data is greater or equal to newData or not
        else if (root.compareTo(new_node) > 0) {
            // if current root data is greater than the new data then now process the left sub-tree
            root.left = insert(root.left, new_node);
        } else {
            // if current root data is less than the new data then now process the right sub-tree
            root.right = insert(root.right, new_node);
        }
        return root;
    }

    //Traversal
    public void inorder() {
        inorder(root);
    }

    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }


    public static void main(String[] args) {
        // Creating the object of BinarySearchTree class
        BinarySearchTree bst = new BinarySearchTree();
        // call the method insert
        bst.insert(new Node("abef", 2));
        bst.insert(new Node("abd", 1));
        bst.insert(new Node("abee", 1));
        bst.insert(new Node("f", 1));
        bst.insert(new Node("a", 1));
        bst.insert(new Node("bce", 1));
        bst.inorder();
    }
}