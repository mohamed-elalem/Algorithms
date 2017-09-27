#include <stdio.h>

class RedBlackTree {
private:
	
	const int RED = 0;
	const int BLACK = 1;
	
	class Node {
	public:
		int key;
		int color;
		Node* left;
		Node* right;
		Node* p;
	};
	
	Node* nil, *root;
	
	
	
	void rotateLeft(Node *x) {
		Node *y = x->right;
		
		x->right = y->left;
		
		if(y->left != this->nil) {
			y->left->p = x;
		}
		y->p = x->p;
		
		if(x->p == this->nil) {
			this->root = y;
		}
		else if(x->p->left == x) {
			x->p->left = y;
		}
		else {
			x->p->right = y;
		}
		y->left = x;
		x->p = y;
	}
	
	void rotateRight(Node *x) {
		Node *y = x->left;
		
		x->left = y->right;
		
		if(y->right != this->nil) {
			y->right->p = x;
		}
		
		y->p = x->p;
		
		if(x->p == this->nil) {
			this->root = y;
		}
		else if(x->p->left == x) {
			x->p->left = y;
		}
		else {
			x->p->right = y;
		}
		
		x->p = y;
		y->right = x;
	}
	
	Node* min(Node *x) {
		if(x->left != this->nil) {
			return min(x->left);
		}
		return x;
	}
	
	void transplant(Node* u, Node* v) {
		if(u->p == this->nil) {
			this->root = v;
		}
		else if(u->p->left == u) {
			u->p->left = v;
		}
		else {
			u->p->right = v;
		}
		
		v->p = u->p;
	}
	
	void insertFixUp(Node *z) {
		while(z->p->color == RED) {
			if(z->p->p->left == z->p) {
				Node *y = z->p->p->right;
				
				if(y->color == RED) {
					z->p->p->color = RED;
					z->p->color = BLACK;
					y->p->color = BLACK;
					z = z->p->p;
				}
				else {
					if(z->p->right == z) {
						z = z->p;
						rotateLeft(z);
					}
					z->p->color = BLACK;
					z->p->p->color = RED;
					rotateRight(z->p->p);
				}
			}
			else {
				Node *y = z->p->p->left;
				if(y->color == RED) {
					z->p->color = BLACK;
					y->color = BLACK;
					z->p->p->color = RED;
					z = z->p->p;
				}
				else {
					if(z->p->left == z) {
						z = z->p;
						rotateRight(z);
					}
					else {
						z->p->color = BLACK;
						z->p->p->color = RED;
						rotateLeft(z->p->p);
					}
				}
			}
		}
		this->root->color = BLACK;
	}
	
	void eraseFixUp(Node *x) {
		while(x != this->root && x->color == BLACK) {
			Node *w = this->nil;
			if(x->p->left == x) {
				w = x->p->right;
				
				if(w->color == RED) {
					w->color = BLACK;
					x->p->color = BLACK;
					rotateLeft(x->p);
					w = x->p->right;
				}
				
				if(w->left->color == BLACK && w->right->color == BLACK) {
					w->color == RED;
					x = x->p;
				}
				else {
					if(w->right->color == BLACK) {
						w->left->color = BLACK;
						w->color = RED;
						rotateRight(w);
						w = x->p->right;
					}
					w->color = x->p->color;
					x->p->color = BLACK;
					w->right->color = BLACK;
					rotateLeft(x->p);
					x = this->root;
				}
			}
			else {
				w = x->p->left;
				
				if(w->color == RED) {
					w->color = BLACK;
					x->p->color = RED;
					rotateRight(x->p);
					w = x->p->right;
				}
				else if(w->left->color == BLACK && w->right->color == BLACK) {
					w->color = RED;
					x = x->p;
				}
				else {
					if(w->left->color == BLACK) {
						w->right->color = BLACK;
						w->color = RED;
						rotateLeft(w);
						w = x->p->left;
					}
					w->color = x->p->color;
					x->p->color = BLACK;
					w->right->color = BLACK;
					rotateRight(x->p);
					x = this->root;
				}
			}
		}
		x->color = BLACK;
	}
	
public:
	RedBlackTree() {
		this->nil = new Node;
		this->nil->color = BLACK;
		this->nil->p = nullptr;
		this->nil->left = this->nil->right = nullptr;
		
		this->root = this->nil;
	}
	
	void insert(int key) {
		Node *y = this->nil;
		Node *x = this->root;
		
		while(x != this->nil) {
			y = x;
			if(x->key > key) {
				x = x->left;
			}
			else {
				x = x->right;
			}
		}
		Node *z = new Node;
		
		z->key = key;
		z->color = RED;
		z->p = y;
		z->left = z->right = this->nil;
		
		if(y == this->nil) {
			this->root = z;
		}
		else if(y->key > z->key) {
			y->left = z;
		}
		else {
			y->right = z;
		}
		
		insertFixUp(z);
	}
	
	
	void erase(Node *z) {
		Node *y = z;
		Node *x = this->nil;
		int yOriginalColor = y->color;
		
		if(z->left == this->nil) {
			x = z->right;
			transplant(z, z->right);
		}
		else if(z->right == this->nil) {
			x = z->left;
			transplant(z, z->left);
		}
		else {
			y = min(z->right);
			yOriginalColor = y->color;
			x = y->right;
			if(y->p == z) {
				x->p = y;
			}
			else {
				transplant(y, y->right);
				y->right = z->right;
				y->right->p = y;
			}
			transplant(z, y);
			y->left = z->left;
			y->left->p = y;
			y->color = z->color;
		}
		
		if(yOriginalColor == BLACK) {
			eraseFixUp(x);
		}
	}
	
	void inorder() {
		inorder(this->root);
	}
	
	void inorder(Node *x) {
		if(x != this->nil) {
			inorder(x->left);
			printf("%d ", x->key);
			inorder(x->right);
		}
	}
	
	void preorder() {
		preorder(this->root);
	}
	
	void preorder(Node *x) {
		if(x != this->nil) {
			printf("%d ", x->key);
			preorder(x->left);
			preorder(x->right);
		}
	}
	
	Node* search(int key) {
		Node *x = this->root;
		while(x != this->nil) {
			if(x->key == key) {
				return x;
			}
			else if(x->key > key) {
				x = x->left;
			}
			else {
				x = x->right;
			}
		}
		return this->nil;
	}
};

int main(int argc, char **argv)
{
	RedBlackTree rb;
	rb.insert(5);
	rb.insert(7);
	rb.insert(8);
	rb.insert(20);
	rb.insert(30);
	rb.insert(80);
	rb.insert(90);
	rb.insert(100);
	rb.erase(rb.search(80));
	rb.preorder();
	return 0;
}
