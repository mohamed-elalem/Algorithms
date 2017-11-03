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

class Node {
    public static final int RED = 0;
    public static final int BLACK = 1;
    public int low;
    public int high;
    public int max;
    public Node left;
    public Node right;
    public Node p;
    public int color;
}

public class RBTree {

    
    
    
    private Node nil;
    private Node root;
    
    public RBTree() {
        nil = new Node();
        nil.left = nil.right = null;
        nil.p = nil;
        nil.color = Node.BLACK;
        root = nil;
    }
    
    private void leftRotate(Node x) {
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
        x.p = y;
        y.left = x;
        
        y.max = x.max;
        x.max = Math.max(x.high, Math.max(x.left.max, x.right.max));
    }
    
    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        
        if(y.right != nil) {
            y.right.p = nil;
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
        x.p = y;
        y.right = x;
        
        y.max = x.max;
        x.max = Math.max(x.high, Math.max(x.left.max, x.right.max));
    }
    
    private Node min(Node x) {
        if(x.left != nil) {
            return min(x.left);
        }
        return x;
    }
    
    private void transplant(Node u, Node v) {
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
    
    public Node search(int key) {
        Node x = root;
        while(x != nil) {
            if(x.low > key) {
                x = x.left;
            }
            else if(x.low < key) {
                x = x.right;
            }
            else {
                break;
            }
        }
        return x;
    }
    
    public void insert(int low, int high) {
        Node z = new Node();
        z.low = low;
        z.high = high;
        z.left = z.right = nil;
        z.max = high;
        z.color = Node.RED;
        insert(z);
    }
    
    public void delete(int low) {
        Node z = search(low);
        delete(z);
    }
    
    public void insert(Node z) {
        Node x = root;
        Node y = nil;
        
        while(x != nil) {
            y = x;
            x.max = Math.max(x.max, z.max);
            if(x.low > z.low) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }
        
        z.p = y;
        
        if(y == nil) {
            root = z;
        }
        else if(y.low > z.low) {
            y.left = z;
        }
        else {
            y.right = z;
        }
        insertFixUp(z);
    }
    
    public void insertFixUp(Node z) {
        while(z.p.color == Node.RED) {
            if(z.p == z.p.p.left) {
                Node y = z.p.p.right;
                
                if(y.color == Node.RED) {
                    z.p.color = Node.BLACK;
                    y.p.color = Node.BLACK;
                    z.p.p.color = Node.RED;
                    z = z.p.p;
                }
                else {
                    if(z == z.p.right) {
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
                    if(z == z.p.left) {
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
    
    public void delete(Node z) {
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
            yOriginalColor = y.color;
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
            deleteFixUp(x);
        }
        while(y != nil) {
            y.max = Math.max(y.max, Math.max(y.left.max, y.right.max));
            y = y.p;
        }
    }
    
    public void deleteFixUp(Node x) {
        while(x != root && x.color == Node.BLACK) {
            if(x == x.p.left) {
                Node w = x.p.right;
                if(w.color == Node.RED) {
                    w.color = Node.BLACK;
                    x.p.color = Node.RED;
                    leftRotate(x.p);
                }
                if(w.left.color == Node.BLACK && w.right.color == Node.BLACK) {
                    w.color = Node.RED;
                    x =x.p;
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
                    w.left.color = Node.BLACK;
                    rightRotate(x.p);
                    x = root;
                }
            }
        }
        x.color = Node.BLACK;
    }
    
    boolean overlap(int low1, int high1, int low2, int high2) {
        return (low1 <= high2 && low2 <= high1) || (low2 <= high1 && low1 <= high2);
    }
    
    Node intervalSearch(int low, int high) {
        Node x = root;
        while(x != nil && !overlap(x.low, x.high, low, high)) {
            System.out.println("Checking [ "  + x.low + ", " + x.high + " ] intersects [ " + low + ", " + high + " ] ");
            if(x.left !=  nil && x.left.max >= low) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }
        return x;
    }
    
    public void preorder() {
        preorder(root);
        System.out.println("-------------------------------");
    }
    
    public void preorder(Node x) {
        if(x != nil) {
            System.out.println("[ " + x.low + ", " + x.high + " ] " + x.max + ", " + (x.color == Node.BLACK ? "Black" : "Red"));
            preorder(x.left);
            preorder(x.right);
        }
    }
}
