/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interval_trees;

/**
 *
 * @author mohamed
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RBTree rb = new RBTree();
        rb.insert(5, 8);
        rb.preorder();
        rb.insert(5, 15);
        rb.preorder();
        rb.insert(4, 12);
        rb.preorder();
        rb.insert(20, 25);
        rb.preorder();
        rb.insert(1, 7);
        rb.preorder();
        rb.insert(2, 8);
        rb.preorder();
        rb.insert(4, 24);
        rb.preorder();
        rb.insert(30, 48);
        rb.preorder();
        
        Node res = rb.intervalSearch(14, 18);
        System.out.println(res.low + ", " + res.high);
    }
    
}
