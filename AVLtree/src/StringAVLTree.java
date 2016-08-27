
/* Borhan Alizadeh
 * Comp282 Mon/Wed
 * Assignment 2
 * 03/11/2015
 * This program balances avl trees. */



class StringAVLNode {
	private String val;
	private int balance;
	private StringAVLNode left = null;
	private StringAVLNode right = null;

	public StringAVLNode() {
		this.val = "";
		left = null;
		right = null;
		balance = 0;
	}

	//constructor
	public StringAVLNode(String str) {
		val = str;
		left = null;
		right = null;
		balance = 0;
	}

	// get balance
	public int getBalance() {
		return balance;
	}

	// it sets balance to bal
	public void setBalance(int bal) {
		this.balance = bal;
	}

	// get value
	public String getVal() {
		return val;
	}

	// gets left child node
	public StringAVLNode getLeft() {
		return left;
	}

	// it sets left child to pt
	public void setLeft(StringAVLNode pt) {
		this.left = pt;
	}

	// get right child
	public StringAVLNode getRight() {
		return right;
	}

	// sets right child to pt
	public void setRight(StringAVLNode pt) {
		this.right = pt;
	}

	
 
	
}








//StringAVLNode
public class StringAVLTree {
	
	
	public static void main(String[] args) {
		StringAVLTree temp = new StringAVLTree();
		temp.insert("21");
		temp.insert("12");
		temp.insert("10");
		temp.insert("46");
		temp.insert("102");
		temp.insert("11");
		temp.insert("55");
		temp.insert("23");
		temp.insert("9");
		temp.printTree(temp.getRoot());
		
	}		
    
	   private void printTree( StringAVLNode t )
	    {
	        if( t != null )
	        {
	            printTree(t.getLeft());
	            System.out.println( t.getVal());
	            printTree( t.getRight() );
	        }
	    }
	   
	private StringAVLNode root;

	// the one and only constructor
	public StringAVLTree() {
		root = null;
	}

	// this is here to make it easier for me to write a test
	// program – you would never do this in real life!
	public StringAVLNode getRoot() {
		return root;
	}

	// Rotate the node to the right ****Cant be Static!!****
	private static StringAVLNode rotateRight(StringAVLNode n) {

		StringAVLNode temp;
		temp = n.getLeft();
		// perform rotation
		n.setLeft(temp.getRight());
		temp.setRight(n);
		// return the new root
		return temp;
	}

	// Rotate the node to the left ****Cant be Static!!****
	private StringAVLNode rotateLeft(StringAVLNode n) {

		StringAVLNode temp;
		temp = n.getRight();
		// perform rotation
		n.setRight(temp.getLeft());
		temp.setLeft(n);
		// return the new root
		return temp;

	}


	// Return the height of the tree
	int height = 0;

	// return height
	public int height() {
		return height(root);
	}

	// calculate the height and return result
	protected int height(StringAVLNode pt) {
		int result;
		if (pt == null) {
			result = 0;
		} else {
			if (height(pt.getLeft()) > height(pt.getRight())) {
				result = height(pt.getLeft()) + 1;
			} else if (height(pt.getLeft()) < height(pt.getRight())) {
				result = height(pt.getRight()) + 1;
			} else {
				result = height(pt.getRight()) + 1;
			}
		}
		return result;
	}

	// Return the number of leaves in the tree
	public int leafCt() {
		return countLeaves(root, 0);
	}

	// it counts the number of leaves
	private int countLeaves(StringAVLNode pt, int count) {
		// if pt be null then it just return zero
		if (pt == null) {
			return 0;
		}
		// if the left and right side of the current node be null
		// then it add one to the result
		if (pt.getLeft() == null && pt.getRight() == null) {
			return 1 + count;
		} else {
			int lc = countLeaves(pt.getLeft(), count);
			int total = countLeaves(pt.getRight(), lc);
			return total;
		}
	}

	// Return the number of perfectly balanced AVL nodes
	public int balanced() {
		return height;
	}

	// it gets the smallest number on the right subtree
	public String successor(String str) {
		StringAVLNode x = root;
		if (x.getRight() != null) {
			x = x.getRight();
			while (x.getLeft() != null) {
				x = x.getLeft();
			}
		}
		return x.getVal();
	}

	// insert a string in our tree
	public void insert(String str) {
		root = insert(str, root);
	}

	// insert method
	private StringAVLNode insert(String str, StringAVLNode t) {
		int newBalance;
		int oldBalance;
		if (t == null) {
			// easiest case – inserted node goes here
			t = new StringAVLNode(str);
			newBalance = 0;
		} else if (t.getVal() == str) {
			// already in the tree – do nothing
		}

		else if (str.compareToIgnoreCase(t.getVal()) < 0) {
			// str is smaller than this node – go left
			// get the old balance of the left child
			// (where the insertion is taking place)

			if (t.getLeft() == null) {
				oldBalance = 0;
				t.setBalance(t.getBalance() - 1);
			} else {
				oldBalance = t.getLeft().getBalance();
			}
			// insert a new node on the left side of
			// current node if it t.getLeft be null
			t.setLeft(insert(str, t.getLeft()));
			// gets the newBalance
			newBalance = t.getLeft().getBalance();
			if (oldBalance == 0 && newBalance != 0) {
				// did the height increase?
				// fix the balance value
				// System.out.println("\nFixing  balance");

				t.setBalance(t.getBalance() - 1);
				if (t.getBalance() == -2) {
					// out of balance – must rotate
					if (t.getLeft().getBalance() == -1) {
						// single rotation
						// and balance update
						t = rotateRight(t);
						t.setBalance(0);
						t.getRight().setBalance(0);

					} else { // double rotation
						// and balance update
						// once you get it right here,
						// basically the
						// same code can be used in other places,
						// including delete
						// rotateLeft(t);
						// t= rotateRight(t);
						/*
						 */
						t.setLeft(rotateLeft(t.getLeft()));
						t = rotateRight(t);
						// we have three cases here
						if (t.getBalance() == 1) {
							t.setBalance(0);
							t.getRight().setBalance(0);
							t.getLeft().setBalance(-1);

						} else if (t.getBalance() == -1) {
							t.setBalance(0);
							t.getLeft().setBalance(0);
							t.getRight().setBalance(1);

						} else {// balance=0
							t.setBalance(0);
							t.getRight().setBalance(0);
							t.getLeft().setBalance(0);
						}
					}
				}
			}

			// if the string we want to insert be greater than
			// the pointer it goes right
		} else if (str.compareToIgnoreCase(t.getVal()) > 0) {
			if (t.getRight() == null) {
				// sets the oldBalance to zero
				oldBalance = 0;
				t.setBalance(t.getBalance() + 1);
			} else {
				// sets the old balance to the balance of
				// right pointer's node
				oldBalance = t.getRight().getBalance();
			}
			t.setRight(insert(str, t.getRight()));
			// sets new balance
			newBalance = t.getRight().getBalance();

			// if oldBalance be zero but newBalance not
			// then we need to update
			if (oldBalance == 0 && newBalance != 0) {
				// did the height
				// increase?
				// fix the balance value
				t.setBalance(t.getBalance() + 1);
				if (t.getBalance() == 2) {
					// out of balance – must
					// rotate
					if (t.getRight().getBalance() == 1) {
						// single rotation
						// and balance update

						t = rotateLeft(t);
						t.setBalance(0);
						t.getLeft().setBalance(0);

					} else { // double rotation
						// and balance update
						// once you get it right here, basically the
						// same code can be used in other places,
						// including delete

						t.setRight(rotateRight(t.getRight()));
						t = rotateLeft(t);
						if (t.getBalance() == 1) {
							t.setBalance(0);
							t.getRight().setBalance(0);
							t.getLeft().setBalance(-1);

						} else if (t.getBalance() == -1) {
							t.setBalance(0);
							t.getLeft().setBalance(0);
							t.getRight().setBalance(1);

						} else {// bal=0
							t.setBalance(0);
							t.getRight().setBalance(0);
							t.getLeft().setBalance(0);

						}
					}
				}
			}
		}
		return t;
	}

	// delete the string from our tree
	public void delete(String str) {
		delete(root, str);
	}

	// delete method
	private StringAVLNode delete(StringAVLNode t, String str) {
		int oldBalance;
		int newBalance;
		if (t == null)
			;
		// Do nothing if it is not in the tree
		else if (str.compareTo(t.getVal()) < 0) {

			// get the old balance.
			oldBalance = t.getLeft().getBalance();

			if (t.getLeft() == null)
				;
			// still must deal with this special case in case
			// the element to be deleted is not in the tree
			else
				t.setLeft(delete(t.getLeft(), str));
			// get the new balance
			newBalance = t.getLeft().getBalance() - 1;

			if (newBalance < oldBalance) {
				// did the height descrese?
				// correct the balance
				t.setBalance(t.getBalance() - 1);
			}

			if (t.getBalance() == -1 || t.getBalance() == 0
					|| t.getBalance() == 1) {
				// need to rotate? //there are now
				// actually 3 cases because

				if (t.getRight().getBalance() == 1) {
					t = rotateLeft(t);
				} else if (t.getRight().getBalance() == 0) {
					t = rotateLeft(t);
				} else {
					// double rotation case
					rotateRight(t.getRight());
					rotateLeft(t);
				}
			}
		} else if (str.compareTo(t.getVal()) > 0) {
			// get the old balance.
			oldBalance = t.getLeft().getBalance();

			if (t.getRight() == null)
				;
			// still must deal with this special case in case
			// the element to be deleted is not in the tree
			else

				t.setRight(delete(t.getRight(), str));

			// get the new balance
			newBalance = t.getRight().getBalance() - 1;

			if (newBalance < oldBalance) {
				// did the height descrese?
				// correct the balance
				t.setBalance(t.getBalance() - 1);
			}

			if (t.getBalance() == -1 || t.getBalance() == 0
					|| t.getBalance() == 1) {
				// need to rotate?
				// there are now
				// actually 3 cases because

				if (t.getLeft().getBalance() == 1) {
					t = rotateRight(t);
				} else if (t.getLeft().getBalance() == 0) {
					t = rotateRight(t);
				} else {
					// double rotation case
					rotateLeft(t.getLeft());
					rotateRight(t);
				}
			}
		} else {// t is the node to be deleted
			oldBalance = t.getBalance();
			t = replace(t, null, t.getLeft());
		}
		return t;

	}

	// replace the t with replacement node we pass in
	private StringAVLNode replace(StringAVLNode t, StringAVLNode prev,
			StringAVLNode replacement) {
		int oldBalance;
		int newBalance;
		if (replacement.getRight() == null) {
			// at the node that will replace
			// the deleted node

			if (prev != null) {
				// if replacement node is NOT the child
				// move the replacement node
				// Recall there is no setVal
				replace(t.getLeft(), prev, replacement);
			}

			else {
				// get the old balance
				oldBalance = t.getBalance();
				t = replace(t, replacement, replacement.getRight());

				// find the new balance
				newBalance = t.getRight().getBalance() + 1;

				// update balance and rotate if needed
				if (oldBalance == 0 && newBalance != 0) {
					// did the height
					// increase?
					// fix the balance value
					t.setBalance(t.getBalance() + 1);
					if (t.getBalance() == 2) {
						// out of balance ñ must rotate
						if (t.getRight().getBalance() == 1) {
							// single rotation
							// and balance update
							t = rotateLeft(t);
						} else { // double rotation
									// and balance update
									// once you get it right here,
									// basically the
									// same code can be used in
									// other places,
									// including delete
							rotateRight(t.getRight());
							rotateLeft(t);
						}
					}
				}
			}
		}
		return t;
	}


	
	
	public static String myName() {
		return "Borhan Alizadeh";
	}
}