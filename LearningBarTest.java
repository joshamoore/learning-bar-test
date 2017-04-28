import java.util.ArrayList;
import java.util.Hashtable;

public class LearningBarTest {
	/*
	 * Question 1:
	 * This algorithm uses a hash table to search in O(1) time, which gives us our
	 * resulting array in O(n) time.
	 * */
	public static ArrayList<String> removeDupes(ArrayList<String> list) {
		ArrayList<String> noDupes = new ArrayList<String>(); /* Return list */
		Hashtable ht = new Hashtable(); /* Hashtable is used to check for duplicates */
		for (int i=0; i<list.size(); i++) {
			if (ht.contains(list.get(i))==false) {
				ht.put(list.get(i), list.get(i));
				noDupes.add(list.get(i));
			}
		}
		return noDupes;
	}
	/*
	 * Question 2:
	 * This algorithm runs in O(n) time, updating the smallest integer at a time
	 * Notes: We also assume that duplicate integers should only be returned once,
	 * so you will get 3 different integers returned. The inside loop is only for
	 * searching the list of smallest values, which is O(1) time.
	 * If this was requesting n smallest values, this would not be an appropriate algorithm,
	 * and we would be better off sorting the array first in O(nlg(n)) time.
	 * This does not return a sorted array.
	 * */
	public static int[] smallestThree(int[] arr) {
		int[] temp = new int[3]; /* Temporary array for 3 smallest values */
		int added = 0;
		boolean zero = false; /* Whether we detected a 0 */
		for (int i=0; i<arr.length; i++) {
			/* This is to account for leading 0's in the input array */
			if (added<3) {
				if (arr[i]==0 && zero==false) {
					temp[added]=0;
					added++;
					zero=true;
				}
				if ((arr[i]!=0) && checkDupe(arr[i],temp)==false) {
					temp[added] = arr[i];
					added++;
				}
			}
			else if (added>=3 && checkDupe(arr[i],temp)==false) {
				/* If elements not in the temp array */
				if (arr[i]<temp[findLargest(temp)]) {
					temp[findLargest(temp)] = arr[i];
				}
			}
		}
		return temp;
	}
	/*
	 * Checks whether an array already contains a value n
	 * */
	public static boolean checkDupe(int n, int[] arr) {
		boolean found = false;
		for (int i=0;i<arr.length;i++) {
			if (n==arr[i]) {
				found = true;
			}
		}
		return found;
	}
	/*
	 * Returns the position of the largest value in the array
	 * */
	public static int findLargest(int[] arr) {
		int pos=0;
		int lrg=arr[0];
		for (int i=1;i<arr.length;i++) {
			if (lrg<arr[i]) {
				pos=i;
				lrg = arr[i];
			}
		}
		return pos;
	}
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		/* Add some names to our list */
		list.add("Bob");
		list.add("Alice");
		list.add("John");
		list.add("Bob");
		list.add("Jake");
		list.add("Bob");
		list.add("Alice");
		list.add("John");
		list.add("Bob");
		list.add("Jake");
		list.add("James");
		list.add("Adam");
		list.add("Alice");
		list.add("James");
		list.add("Adam");
		list.add("Alice");
		list = removeDupes(list);	
		for (int i=0;i<list.size();i++) {
			System.out.println("At index "+i+": "+list.get(i));
		}
		
		System.out.println("----------------------");
		
		/* Initialize some values in the array */
		int[] values = {0,0,0,12,24,2,6,9,15,17,15,26,1,22,9,9,3,2,2,12,0,-11,15,6,1,4,10,23,6,9,15,17,15,26,22,9,9,3};
		values = smallestThree(values);
		for (int i=0;i<values.length;i++) {
			System.out.println("At index "+i+": "+values[i]);
		}
	}
}
