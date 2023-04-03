import java.util.Scanner;
import java.io.*;

public class Lab4 {
    public int sizeInputs;
    //node class that defines BST node
    class Node {
        String key;
        Node left, right;

        public Node(String data) {
            key = data;
            left = right = null;
        }
    }

    // BST root node
    Node root;

    // Constructor for BST
    Lab4() {
        root = null;
    }

    // insert a node in BST
    public void insert(String key) {
        root = insertRecursive(root, key);
        sizeInputs++;
    }

    //recursive insert function
    //have a third else condition with an equal to 0 then make insert return false and don't change the node
    Node insertRecursive(Node root, String key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key.compareTo(root.key) < 0)     //insert in the left subtree
            root.left = insertRecursive(root.left, key);
        else if (key.compareTo(root.key) > 0)    //insert in the right subtree
            root.right = insertRecursive(root.right, key);
        // return pointer
        return root;
    }

    // method for inorder traversal of BST
    void trieToList() {
        trieToListRecursive(root);
    }

    // recursively traverse the BST
    void trieToListRecursive(Node root) {
        if (root != null) {
            trieToListRecursive(root.left);
            System.out.print(root.key + " ");
            trieToListRecursive(root.right);
        }
    }

    //search
    public boolean search(String key) {
        Node searchNode;
        searchNode = searchRecursive(root, key);
        if (searchNode != null)
            return true;
        else
            return false;
    }

    //recursive insert function
    Node searchRecursive(Node root, String key) {
        if (root == null) {
            return null;
        }
        // val is greater than root's key
        else if (root.key.compareTo(key) > 0) {
            return searchRecursive(root.left, key);
        }
        // val is less than root's key
        else if (root.key.compareTo(key) < 0) {
            return searchRecursive(root.right, key);
        } else {
            return root;
        }
    }

    //largest
    public void largest() {
        Node largestNode;
        largestNode = largestRecursive(root);
        System.out.println("The largest string is: " + largestNode.key);
    }

    //recursive largest function
    Node largestRecursive(Node root) {
        if (root == null) {
            return null;
        }
        else if (root.right == null) {
            return root;
        }
        else {
            return largestRecursive(root.right);
        }
    }

    //smallest
    public void smallest() {
        Node smallestNode;
        smallestNode = smallestRecursive(root);
        System.out.println("The smallest string is: " + smallestNode.key);
    }

    //recursive smallest function
    Node smallestRecursive(Node root){
        if (root == null) {
            return null;
        }
        else if (root.left == null) {
            return root;
        }
        else {
            return smallestRecursive(root.left);
        }

    }




    //size
    public void size() {
        System.out.println("The number of stings currently stored is: " + sizeInputs);
    }

    //height returns the height of the trie
    public void height() {
        int height = heightRecursive(root);
        System.out.println("The height of the trie is: " + height);


    }

    //recursive height function
    int heightRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        else {
            //compute the height of each subtree
            int leftHeight = heightRecursive(root.left);
            int rightHeight = heightRecursive(root.right);

            //use the larger one
            if (leftHeight > rightHeight) {
                return (leftHeight + 1);
            }
            else {
                return (rightHeight + 1);
            }
        }
    }


    public static void main(String[] args) {
        //File input = new File ("input.txt");
        //create a BST object
        Lab4 bst = new Lab4();

        String fileName = args[0];
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       //read the input file with the commands line by line

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String arr[] = line.split(" ", 2);
                String firstWord = arr[0];

                //insert
                if (firstWord.equalsIgnoreCase("insert")) {
                    bst.insert(arr[1]);
                }
                //search
                else if (firstWord.equalsIgnoreCase("search")) {
                    bst.search(arr[1]);
                    boolean ret_val = bst.search(arr[1]);
                    System.out.println ("\n" + arr[1] + " found in BST: " + ret_val);
                }
                //print
                else if (firstWord.equalsIgnoreCase("print")) {
                    bst.trieToList();
                }
                //largest
                else if (firstWord.equalsIgnoreCase("largest")) {
                    bst.largest();
                }
                //smallest
                else if (firstWord.equalsIgnoreCase("smallest")) {
                    bst.smallest();
                }
                //height
                else if (firstWord.equalsIgnoreCase("height")) {
                    bst.height();
                }
                //size
                else if (firstWord.equalsIgnoreCase("size")) {
                    bst.size();
                }

                // read next line
            }
            scanner.close();

        }
    }
