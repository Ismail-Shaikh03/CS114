import java.util.*;
import javax.swing.JOptionPane;

import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.UIManager;

public class Tree_Demo
{
    private static void display(String title, ArrayList<String> result, String delim)
    {
        String output = title;
        for (String s : result) output += s + delim;
        System.out.println(output );
        JOptionPane.showMessageDialog(null, output + "        ");
        result.clear();
    }
    
    public static void main(String[] argv)
    {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("TextField.font", new Font("Droid Sans Mono Slashed", Font.BOLD, 20));
       
        //Comparator<String> str_comp = (s1, s2) -> s1.length() - s2.length();
        //Comparator<String> str_comp = (s1, s2) -> s1.compareTo(s2);
        Comparator<String> str_comp = (s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase());
	
		BSTree<String> bst = new BSTree<>(str_comp);
        
        bst.insert("grape");
        bst.insert("apple");
        bst.insert("banana");
        bst.insert("blueberry");
        bst.insert("pear");
        bst.insert("orange");
        bst.insert("Pear");
        bst.insert("strawberry");
        bst.insert("plum");
        bst.insert("PeAr");
        bst.insert("fig");
        bst.insert("date");
        bst.insert("PEAR");
        
        ArrayList<String> result = new ArrayList<>();
        
        bst.serial(result); 
        display("\n\nThe BST Serialized:\n\n", result, " ");
             
        bst.inorder(result);
        display("\n\nThe BST Inorder:\n\n", result, "\n");
         
        bst.preorder(result);
        display("\n\nThe BST Preorder:\n\n", result, "\n");
         
        bst.postorder(result);
        display("\n\nThe BST Postorder:\n\n", result, "\n");
       
        //search a key in the BST
        String key = JOptionPane.showInputDialog("\nEnter search key:  ");
        String ret_val = bst.find (key);
        display("\n\nKey: " + key + "\n\nsearch result:\n\n" + ret_val, result, "");
        //output = "Key: " + key + "\n\nsearch result:\n" + ret_val; 
        //System.out.println("\n\n" + output );
        //JOptionPane.showMessageDialog(null, output + "        ");
        
        bst.findAll(result, key);
        display("\n\nKey: " + key + "\n\nfind all search result:\n\n", result, "\n");
        
        String lo = JOptionPane.showInputDialog("\nEnter low key:  ");
        String hi = JOptionPane.showInputDialog("\nEnter high key:  ");
        
        bst.range(result, lo, hi);
        display("\n\nSearch Range: [" + lo + ", " + hi + "]\n\nrange search result:\n\n", result, "\n");
        
        display("\n\nNode count: " + bst.nodeCount() + "\n\n", result, "");
        
        display("\n\nTree depth: " + bst.depth() + "\n\n", result, "");

    }
}