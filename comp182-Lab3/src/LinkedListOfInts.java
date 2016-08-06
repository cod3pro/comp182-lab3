

//Name: Borhan Alizadeh
//SID: 106592575

//** Your code MUST compile **

import java.util.*;

public class LinkedListOfInts {
	static Node head;

	/* FROM HERE - FROM HERE - FROM HERE - FROM HERE - FROM HERE */

	public Node nodeAtIndex(int k) {
		Node current = head;
		int L = 0;
		while (L != k) {
			current = current.getNext();
			L++;
		}
		return current;
	}

	public void swapValues(int i, int j) {
		Node current = head;
		Node current1 = head;

		int k = 0;
		int O = 0;
		while (k != i) {
			current = current.getNext();
			k++;
		}

		while (O != j) {
			current1 = current1.getNext();
			O++;
		}

		int temp;
		temp = current.getValue();
		current.setValue(current1.getValue());
		current1.setValue(temp);

	}

	public void selectionSort(Node new_head, int new_head_index) {

		/*
		 * FILL IN THE BLANKS. DO NOT ADD ANY OTHER STATEMENTS AND JUST FILL OUT
		 * THE BLANK (CONDITION AND THE ARGUMENTS).
		 */

		if (new_head != null) // hint: The opposite of the base case
		{
			int i = getIndexOfSmallest(new_head, new_head_index);
			swapValues(new_head_index, i);
			selectionSort(new_head.getNext(), (new_head_index + 1));
		}

	}

	// You may want to understand at least partially how this function is
	// implemented.
	// It takes two arguments: where you want to start searching in the linked
	// list for
	// the smallest value and the index of the start node in the list.

	public int getIndexOfSmallest(Node new_head, int new_head_index) {

		int min_so_far = new_head.getValue();
		int min_so_far_index = new_head_index;
		int current_index = new_head_index;

		while (new_head != null) {
			if (min_so_far > new_head.getValue()) {
				min_so_far = new_head.getValue();
				min_so_far_index = current_index;
			}

			new_head = new_head.getNext();
			current_index++;
		}

		return min_so_far_index;

	}

	// You might find this function useful when testing the selectionSort()
	// function
	public static Node getHead() {
		return head;
	}

	/*
	 * You can use the yourMainMethod method to do any testing you'd like to do
	 * (none is also allowed). It will be automatically called when you execute
	 * the class.
	 */
	public static void yourMainMethod() {
		LinkedListOfInts List = new LinkedListOfInts();
		List.insert(3);
		List.insert(12);
		List.insert(-3);
		List.insert(7);
		List.insert(-2);
		List.insert(2);
		List.insert(4);
		System.out.println(List);
		LinkedListOfInts List1 = new LinkedListOfInts();
		List1.insert(1);
		List1.insert(10);
		List1.insert(9);
		List1.insert(7);
		List1.insert(11);
		List1.insert(12);
		System.out.println(List1);
		System.out.println(List1.nodeAtIndex(3).getValue());
		List1.swapValues(0, 3);
		System.out.println(List1);
		// List.swapValues(0, 3);
		// System.out.println(List);
		List.selectionSort(getHead(), 0);
		System.out.println(List);


		

	}

	/* TO HERE - TO HERE - TO HERE - TO HERE - TO HERE - TO HERE */

	public LinkedListOfInts() {
		head = null;
	}

	public void insert(int n) {
		Node nd = new Node(n);
		nd.setNext(head);
		head = nd;
	}

	public String toString() {
		String str = "[";
		Node n = head;
		while (n != null) {
			str += n.getValue() + " ";
			n = n.getNext();
		}
		str += "]";
		return str;
	}

	public static void main(String[] args) {

		yourMainMethod();
	}
}

class Node {
	private int value;
	private Node next, prev;

	public Node(int v) {
		value = v;
		next = null;
	}

	public int getValue() {
		return value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node nd) {
		next = nd;
	}

	public void setValue(int v) {
		value = v;
	}
}