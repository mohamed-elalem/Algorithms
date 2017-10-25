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


class Node {
    public static int RED = 0;
    public static int BLACK = 1;
    
    public int key;
    public int size;
    public int color;
    public Node left;
    public Node right;
    public Node p;
}

public class RBTree {
    private Node root;
    private Node nil;
    private int loc = 0;
    
    public RBTree() {
        nil = new Node();
        nil.left = nil.right = nil.p = nil;
        nil.size = 0;
        nil.color = Node.BLACK;
        
        root = nil;
    }
    
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        
        if(y.left != nil) {
            y.left.p = x;
        }
        y.p = x.p;
        
        if(x.p == nil) {
            root = y;
        }
        else if(x.p.left == x) {
            x.p.left = y;
        }
        else {
            x.p.right = y;
        }
        
        y.left = x;
        x.p = y;
        
        y.size = x.size;
        x.size = x.left.size + x.right.size + 1;
    }
    
    public void rightRotate(Node x) {
        Node y = x.left;
        
        x.left = y.right;
        if(y.right.p != nil) {
            y.right.p = x;
        }
        
        y.p = x.p;
        if(x.p == nil) {
            root = y;
        }
        else if(x.p.left == x) {
            x.p.left = y;
        }
        else {
            x.p.right = y;
        }
        y.right = x;
        x.p = y;
        
        y.size = x.size;
        x.size = x.left.size + x.right.size + 1;
    }
    
    public void transplant(Node u, Node v) {
        if(u.p == nil) {
            root = v;
        }
        else if(u.p.left == u) {
            u.p.left = v;
        }
        else {
            u.p.right = v;
        }
        v.p = u.p;
    }
    
    public Node min(Node x) {
        if(x.left != nil) {
            return min(x.left);
        }
        return x;
    }
    
    public void insert(int key) {
        Node x = root;
        Node y = nil;
        while(x != nil) {
            y = x;
            x.size++;
            
            if(x.key > key) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }
        System.out.println("Found a suitable place for new node");
        Node z = new Node();
        z.key = key;
        z.left = z.right = nil;
        z.p = y;
        z.color = Node.RED;
        z.size = 1;
        
        if(y == nil) {
            root = z;
        }
        else if(y.key > key) {
            y.left = z;
        }
        else {
            y.right = z;
        }
        insertFix(z);
        System.out.println("Finished Inserting");
    }
    
    public void insertFix(Node z) {
        while(z.p.color == Node.RED) {
            if(z.p.p.left == z.p) {
                Node y = z.p.p.right;
                
                if(y.color == Node.RED) {
                    z.p.color = Node.BLACK;
                    y.color = Node.BLACK;
                    z.p.p.color = Node.RED;
                    z = z.p.p;
                }
                else {
                    if(z.p.right == z) {
                        z = z.p;
                        leftRotate(z);
                    }
                    z.p.color = Node.BLACK;
                    z.p.p.color = Node.RED;
                    rightRotate(z.p.p);
                }
            }
            else {
                Node y = z.p.p.left;
                if(y.color == Node.RED) {
                    z.p.color = Node.BLACK;
                    y.color = Node.BLACK;
                    z.p.p.color = Node.RED;
                    
                    z = z.p.p;
                }
                else {
                    if(z.p.left == z) {
                        z = z.p;
                        rightRotate(z);
                    }
                    z.p.color = Node.BLACK;
                    z.p.p.color = Node.RED;
                    leftRotate(z.p.p);
                }
            }
        }
        root.color = Node.BLACK;
    }
    
    void delete(Node z) {
        Node y = z;
        int yOriginalColor = y.color;
        Node x = nil;
        if(z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        }
        else if(z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        }
        else {
            y = min(z.right);
            x = y.right;
            if(y.p == z) {
                x.p = y;
            }
            else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }
        if(yOriginalColor == Node.BLACK) {
            deleteFix(x);
        }
        y.size = z.size;
        while(y != nil) {
            y.size--;
            y = y.p;
        }
    }
    
    void deleteFix(Node x) {
        while(x != root && x.color == Node.BLACK) {
            if(x == x.p.left) {
                Node w = x.p.right;
                if(w.color == Node.RED) {
                    w.color = Node.BLACK;
                    x.p.color = Node.RED;
                    leftRotate(x.p);
                    w = x.p.right;
                }
                if(w.left.color == Node.BLACK && w.right.color == Node.BLACK) {
                    w.color = Node.RED;
                    x = x.p;
                }
                else {
                    if(w.right.color == Node.BLACK) {
                        w.left.color = Node.BLACK;
                        w.color = Node.RED;
                        rightRotate(w);
                        w = x.p.right;
                    }
                    w.color = x.p.color;
                    x.p.color = Node.BLACK;
                    w.right.color = Node.BLACK;
                    leftRotate(x.p);
                    x = root;
                }
            }
            else {
                Node w = x.p.left;
                if(w.color == Node.RED) {
                    w.color = Node.BLACK;
                    x.p.color = Node.RED;
                    rightRotate(x.p);
                    w = x.p.left;
                }
                if(w.left.color == Node.BLACK && w.right.color == Node.BLACK) {
                    w.color = Node.RED;
                    x = x.p;
                }
                else {
                    if(w.left.color == Node.BLACK) {
                        w.right.color = Node.BLACK;
                        w.color = Node.RED;
                        leftRotate(w);
                        w = x.p.left;
                    }
                    w.color = x.p.color;
                    x.p.color = Node.BLACK;
                    w.right.color = Node.BLACK;
                    rightRotate(x.p);
                    x = root;
                }
            }
        }
        x.color = Node.BLACK;
    }
    
    public void preorder() {
        preorder(root);
        System.out.println("------------------------------------");
    }
    
    private void preorder(Node x) {
        if(x != nil) {
            System.out.println("Key = " + x.key + ", Size = " + x.size + ", Color = " + (x.color == Node.RED ? "RED" : "BLACK"));
            preorder(x.left);
            preorder(x.right);
        }
    }
    
    public Node search(int key) {
        Node x = root;
        while(x != nil) {
            if(x.key > key) {
                x = x.left;
            }
            else if(x.key < key) {
                x = x.right;
            }
            else {
                break;
            }
        }
        return x;
    }
    
    public int orderMeanSelect(int index) {
        return orderMeanSelect(root, index).key;
    }
    
    private Node orderMeanSelect(Node x, int index) {
        int rank = x.left.size + 1;
        if(index == rank) {
            return x;
        }
        else if(index < rank) {
            return orderMeanSelect(x.left, index);
        }
        return orderMeanSelect(x.right, index - rank);
    }
    
    public int orderMeanRank(Node x) {
        int r = x.left.size + 1;
        while(x != root) {
            if(x == x.p.right) {
                r += x.p.left.size + 1;
            }
            x = x.p;
        }
        return r;
    }
    
    public void inorder() {
        loc = 0;
        inorder(root);
    }
    
    public void inorder(Node x) {
        if(x != nil) {
            inorder(x.left);
            System.out.println("[" + loc + "] " + x.key);
            loc++;
            inorder(x.right);
        }
    }
}
