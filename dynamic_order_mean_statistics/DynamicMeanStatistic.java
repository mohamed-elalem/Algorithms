/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicmeanstatistic;

/**
 *
 * @author mohamed
 */
public class DynamicMeanStatistic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RBTree rb = new RBTree();
        rb.insert(8);
        rb.preorder();
        rb.insert(4);
        rb.preorder();
        rb.insert(2);
        rb.preorder();
        rb.insert(11);
        rb.preorder();
        rb.insert(845);
        rb.preorder();
        rb.insert(12);
        rb.preorder();
        rb.insert(56);
        rb.preorder();
        rb.insert(462);
        rb.preorder();
        rb.insert(112);
        rb.preorder();
        rb.insert(998);
        rb.preorder();
        rb.insert(256);
        rb.preorder();
        rb.insert(842);
        rb.preorder();
        rb.insert(4523);
        rb.preorder();
        rb.insert(8452);
        rb.preorder();
        rb.insert(623);
        rb.preorder();
        rb.insert(23);
        rb.preorder();
        rb.insert(101);
        rb.preorder();
        rb.insert(84451);
        rb.preorder();
        rb.insert(632);
        rb.preorder();
        rb.insert(431);
        rb.preorder();
        rb.insert(484);
        rb.preorder();
        rb.insert(231);
        rb.preorder();
        rb.insert(847);
        rb.preorder();
        rb.insert(397);
        rb.preorder();
        rb.delete(rb.search(8));
        rb.preorder();
        rb.inorder();
        System.out.println(rb.orderMeanSelect(17));
        System.out.println(rb.orderMeanRank(rb.search(842)));
    }
    
}
