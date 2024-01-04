/* Assignment 10 (100 marks in total; 5% of the final score of this course)
 *
 * Question 1 (50 marks in total)
    Use mutils.BinarySearchTree as a basic structure to implement a TreeMap for <String, Integer>.
    You cannot use JCF provided map.
 */

import mutils.BinarySearchTree;
import mutils.Node;

import java.util.ArrayList;

public class Q1_TreeMapByBST {

    // use m_bst as the basic data structure for the treemap of <String, Integer>
    BinarySearchTree m_bst = new BinarySearchTree();
    // Question 1.1 (5 marks): implement method size().
    // Returns the number of elements in this map (its cardinality).
    // You can ONLY modify the body of this method including the return statement.
    public int size() {
        return get_size(m_bst.root);
    }
    public int get_size(Node root){
        //I could have just stored this as a global variable
        if(root==null){
            return 0;
        }else{
            int size=1;
            size+=get_size(root.left);
            size+=get_size(root.right);
            return size;
        }
    }
    // Question 1.2 (5 marks): implement method isEmpty().
    // Returns true if this map contains no elements, otherwise return false.
    // You can ONLY modify the body of this method including the return statement.
    public boolean isEmpty() {

        return (size()==0);
    }

    // Question 1.3 (15 marks) implement method get().
    // If key is contained in the map, return the value of the key.
    // If key is not contained in the map, return -1.
    // You can ONLY modify the body of this method including the return statement.
    public Node find(String key, Node root){
        if(root == null){
            return null;
        }
        if(root.data.equals(key)){
            return root;
        }

        Node ans = find(key,root.left);
        if(ans!=null){
            return ans;
        }
        ans = find(key,root.right);
        return ans;


    }
    public int get(String key) {
        Node ans;
        ans = find(key,m_bst.root);
        if(ans==null){
            return -1;
        }
        return ans.freq;
    }

    // Question 1.4 (15 marks) implement method put().
    // If the key is already contained in the map, overwrite the old value and return -1;
    // If the key is not contained in the map, add a new <key,value> entry to the map and return 1;
    // You can ONLY modify the body of this method including the return statement.
    public int put(String key, int value) {
        //System.out.println(key);
        Node old_node = find(key,m_bst.root);
        if(old_node==null){
            Node new_node = new Node(key,value);
            m_bst.insert(new_node);
            return 1;
        }else{
            //System.out.println(old_node.data);
            old_node.freq=value;
            return -1;
        }

    }

    // Question 1.5 (10 marks) implement method getKeysInAlphabeticalOrder().
    // return the keys of this map as an ArrayList<String> in Alphabetical order.
    // You can ONLY modify the body of this method including the return statement.
    public void inorder(ArrayList<String> ans, Node root){
        if (root == null) {
            return;
        }

        inorder(ans,root.left);
        ans.add(root.data);
        //System.out.println(root.data+" "+root.freq);
        inorder(ans,root.right);
    }
    public ArrayList<String> getKeysInAlphabeticalOrder(){
        ArrayList<String> ans = new ArrayList<String>();
        inorder(ans, m_bst.root);

        return ans;
    }

    public static void main(String[] args) {
        // Creating the object of BinarySearchTree class
        Q1_TreeMapByBST bst = new Q1_TreeMapByBST();
        // call the method insert
        bst.put("abef", 2);
        bst.put("abd", 1);
        bst.put("abee", 1);
        bst.put("f", 1);
        bst.put("a", 1);
        bst.put("bce", 1);
        ArrayList<String> ans = bst.getKeysInAlphabeticalOrder();
        for(String word: ans){
            System.out.println(word);
        }
    }
}
