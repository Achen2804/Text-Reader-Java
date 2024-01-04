/* Assignment 10 (100 marks in total; 5% of the final score of this course)
 *
 * Question 2 (50 marks in total)
    Use the Q1_TreeMapByBST to implement a WordCounter to count words in documents.
    You cannot use JCF provided map.
 */
import mutils.Node;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Q2_WordCounter {
    // use the m_treemap as the data structure for word counting
    Q1_TreeMapByBST m_treemap = new Q1_TreeMapByBST();

    // Question 2.1 (15 marks) implement buildTreeMap() to build a treemap that stores
    // the words and word frequencies of a document into m_treemap.
    // "filename" is the path to the file. Please use the "JingleBell.txt" under folder "data".
    // You can ONLY modify the body of this method including the return statement.
    public void buildTreeMap(String filename){
        File file = new File(filename);
        try {
            Scanner reader = new Scanner(file);
            reader.useDelimiter("[^a-zA-Z']+");
            while(reader.hasNext()){
                String word = reader.next().toLowerCase();
                //System.out.println(word);
                int freq = m_treemap.get(word);
                if(freq==-1){
                    m_treemap.put(word,1);
                }else{
                    m_treemap.put(word,freq+1);
                }
            }
            reader.close();
        }catch(Exception e){
            System.out.println("Hey, there's no file. IDK what's happening");
        }
        //System.out.println(m_treemap.size());
    }

    // Question 2.2 (15 marks) print all the counted words in the document in alphabetical order.
    // You can ONLY modify the body of this method including the return statement.
    public void printWordsAlphabetical(){
        ArrayList<String> inorder = m_treemap.getKeysInAlphabeticalOrder();
        for(String word : inorder){
            System.out.println(word+" ");
        }
    }

    // Question 2.3 (15 marks) write an output file named "Output.txt" under folder "data"
    // The output of will consist of two lists.
    // Each list contains all the words from the file, along with the number of times
    // that the word occurred.
    // One list is sorted alphabetically.
    // The other list is sorted according to the number of occurrences, with the most
    // common words (i.e., a word with a larger frequency or count is said to be more common)
    // at the top and the least common at the bottom.
    // IMPORTANT: the output format is given in the file "Output_Example.txt" under folder "data".
    // Input parameter "filename" is the path to the output file.
        public void inorder(ArrayList<String>[] ans, Node root){
            if (root == null) {
                return;
            }
            inorder(ans,root.left);
            ans[root.freq-1].add(root.data);
            inorder(ans,root.right);
        }

    public void outputResult(String filename) {
        ArrayList<String> inorder = m_treemap.getKeysInAlphabeticalOrder();
        //Hey there, you're reading this section. Please understand that I used to have two different classes for a reverse tree and a reverse node. If you see weird
        // code commented out that's why
        //
        try{
            File file = new File("./src/data/"+filename);
            if(file.createNewFile()){
                System.out.println("Hey, we had to make this file. That isn't right but it's also not a big deal");
            }else{
                System.out.println("File was found, let's write");
            }
            FileWriter writer = new FileWriter("./src/data/"+filename);
            writer.write(m_treemap.size()+" words found in file:\n\n");
            writer.write("List of words in alphabetical order (with counts in parentheses):\n\n");
            int max=0;
            for(String word : inorder){
                writer.write("   "+word + " (" + m_treemap.get(word)+")\n");
                max = Math.max(max,m_treemap.get(word));

            }
            ArrayList<String>[] ans =(ArrayList<String>[])new ArrayList[max];
            for(int x=0;x<max;x++){
                ans[x] = new ArrayList<>();
            }
            inorder(ans,m_treemap.m_bst.root);
            writer.write("\n\nList of words by frequency of occurence:\n\n");

            for(int x=max-1;x>=0;x--){
                for(String word: ans[x]){
                    writer.write("   "+word + " (" + m_treemap.get(word)+")\n");
                }
            }


            writer.write("\n");
            System.out.println("We wrote to the file");
            writer.close();
        }catch (Exception e){
            System.out.println("Something went very wrong");
        }
    }

    // Question 2.4 (5 marks) call each of the above methods in the main() method to
    // successfully count the words and produce expected outputs.
    public static void main(String[] args){
        Q2_WordCounter test = new Q2_WordCounter();
        test.buildTreeMap("./src/data/JingleBell.txt");
        System.out.println("Here are the words in alphabetical order: ");
        test.printWordsAlphabetical();
        test.outputResult("Output.txt");
    }

}
