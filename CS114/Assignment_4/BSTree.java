/*
 *
 *
 * */
import java.util.*;

class BSTree<T> 
{   //-------------------------------------------------------------
    //node class
    //
    class Node<T> 
    { 
        T key; 
        Node<T> left, right; 
   
        public Node(T data)
        { 
            key = data; 
            left = right = null; 
        } 
		public Node(T data, Node<T> l, Node<T> r)
        { 
            key = data; 
            left = l;
			right = r; 
        } 
        public String toString(){ return key.toString(); }
    }
 
    Node<T> root;       // BST root node
    Comparator<T> comp; // Comparator Object

    //-------------------------------------------------------------
    // Tree Constructor
    //
    BSTree(Comparator<T> c)
    { 
        root = null;
        comp = c;        
    } 

	
    //-------------------------------------------------------------
    // Deletion
    //
    void deleteKey(T key) { root = delete(root, key); } 
    private Node<T> delete(Node<T> root, T key)  
    { 
        if (root == null)  return root; 
   
        if (comp.compare(key, root.key) < 0)
            root.left = delete(root.left, key); 
        else if (comp.compare(key, root.key) > 0)
            root.right = delete(root.right, key); 
        else  
        {   // node contains only one child
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
   
            // node has two children; 
            //get inorder successor (min value in the right subtree) 
            root.key = minValue(root.right); 
   
            // Delete the inorder successor 
            root.right = delete(root.right, root.key); 
        } 
        return root; 
    }

    //-------------------------------------------------------------
    // find minimum value in tree. (assumes root != null)
    //
    private T minValue(Node<T> root)  
    { 
        while (root.left != null)   
            root = root.left;     
        return root.key; 
    } 
    
    //-------------------------------------------------------------
    // Insertion
    //
    void insert(T key) { root = insert(root, key); } 
    private Node<T> insert(Node<T> root, T key) 
    { 
        //tree is empty
        if (root == null) { 
            root = new Node<T>(key); 
            return root; 
        } 
        //traverse the tree
        if (comp.compare(key, root.key) < 0)
            root.left = insert(root.left, key); 
        else
            root.right = insert(root.right, key); 

        return root; 
    } 
 
    //-------------------------------------------------------------
    // In order traversal
    //
    void inorder(ArrayList<String> result) { inorder(root, result); } 
    private void inorder(Node<T> root, ArrayList<String> result) 
    { 
        if (root != null) 
        { 
            inorder(root.left, result); 
            result.add(root.key + " "); 
            inorder(root.right, result); 
        } 
    } 
    //-------------------------------------------------------------
    // Pre order traversal
    //
    void preorder(ArrayList<String> result) { preorder(root,result);  }
    private void preorder(Node<T> root,ArrayList<String> result){
        if(root !=null){
            result.add(root.key+" ");
            preorder(root.left,result);
            preorder(root.right,result);
        }
    }
    
    //-------------------------------------------------------------
    // Post order traversal
    //
    void postorder(ArrayList<String> result) { postorder(root,result);  }
    private void postorder(Node<T> root,ArrayList<String> result){
        if(root !=null){
            postorder(root.left,result);
            postorder(root.right,result);
            result.add(root.key+" ");
        }
    }
    
    //-------------------------------------------------------------
    // Serialization of the tree
    //
    void serial(ArrayList<String> result) { serial(root,result); }
    private void serial(Node<T> root,ArrayList<String> result){
        if(root==null){
            result.add("~");
        }
        else{
            result.add(root.key.toString());
            serial(root.left,result);
            serial(root.right,result);
        }
    }
    //-------------------------------------------------------------
    // Find all the values in a given range of keys
    //
    void range(ArrayList<T> list, T lo, T hi) {range(list,root,lo,hi); }
    private void range(ArrayList<T>list,Node<T> root,T k1, T k2){
        if(root!=null){
            if(comp.compare(root.key,k1)>=0 && comp.compare(root.key,k2)<=0){
               list.add(root.key);
            }
            if(comp.compare(root.key,k1)>0){
                range(list,root.left,k1,k2);
            }
            if(comp.compare(root.key,k2)<0){
                range(list,root.right,k1,k2);
            }
        }
    }
    //-------------------------------------------------------------
    // Find a Node
    // 
    T find(T key) {return find(root,key);}
    private T find(Node<T> root, T key) {
        T keyFound=null;
        if (root != null) {
            if (comp.compare(root.key, key)==0) {
                keyFound= root.key;
            } else if (comp.compare(key, root.key) < 0) {
                keyFound=find(root.left, key);
            } else {
                keyFound=find(root.right, key);
            }
        }
        return keyFound;
    }
    
    //-------------------------------------------------------------
    // Find all Nodes
    // 
    void findAll(ArrayList<T> result, T key) {findAll( result,  root,  key); }
    private void findAll(ArrayList<T> result, Node<T> root, T key){
        if(root!=null){
            if(comp.compare(root.key,key)==0){
                result.add(root.key);
            }
            findAll(result,root.left,key);
            findAll(result,root.right,key);
        }
    }
    //-------------------------------------------------------------
    // Count Nodes
    //********************************* 
    int nodeCount() { return nodecount(root);
    }
    private int nodecount( Node<T> root){
        if(root==null){
            return 0;
        }
        int left_side_count=nodecount(root.left);
        int right_side_count=nodecount(root.right);
        int total=1+left_side_count+right_side_count;
        return total ;
    }
    
    
    //-------------------------------------------------------------
    // Compute depth (empty tree return 0, one node tree return 1)
    //*********************************
   int depth() { return depth(root); }
   private int depth(Node<T> root){
        if(root!=null){
        int left_tree_depth=depth(root.left);
        int right_tree_depth=depth(root.right);
        if(left_tree_depth>right_tree_depth){
            return left_tree_depth+1;
        }
        else{
            return right_tree_depth+1;
        }
   }
        return 0;
}
}
