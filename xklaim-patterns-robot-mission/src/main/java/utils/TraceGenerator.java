package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;




// TraceGenerator is a class for generating traces: random, sequenced, ordered and Strict ordered


public class TraceGenerator {
	
	 /**
	  *  Generates a random trace of size n: This method generates a random trace of size n. 
	  * It uses a HashSet to keep track of which numbers have already been added to the trace to ensure that there are no duplicates. 
	  * The method starts by generating a random integer between 0 and n-1 and adds it to the result list.
	  * It then generates additional random integers and adds them to the list as long as they are not equal to the previously added integer. 
	  * Once the size of the HashSet equals n, the method returns the result list as an int array 
	  * @param n
	  * @return */
	public static int[] createRandom(int n) {
		// Set to keep track of unique numbers
		HashSet<Integer> uniqueNumbers = new HashSet<>();
		// List to store the resulting trace
		List<Integer> trace = new ArrayList<>();
		// Random number generator
		Random rand = new Random();
		// Initialize the last added number to an invalid value
		int lastAdded = -1; 
		while (uniqueNumbers.size() < n || trace.size() < 2*n) {
			int nextValue = rand.nextInt(n);
			// If the generated number is not equal to the last added number, add it to the trace
			if (lastAdded != nextValue) {
			trace.add(nextValue);
			lastAdded = nextValue;
			uniqueNumbers.add(nextValue);
			}
		}
		// Convert the trace list to an int array and return it
		return trace.stream().mapToInt(i -> i).toArray();			
		}
		

	// Generates a sequenced trace of size n: This method generates a trace of size n where the numbers are sequenced in order from 0 to n-1. 
	//The method first generates a random trace of size n using the createRandom method and then checks whether the trace is sequenced or not. 
	//If the trace is not sequenced, then the method generates another random trace until it gets a sequenced trace. 
	//Finally, it returns the sequenced trace in the form of an integer array.
	public static int[] createSequenced(int n) {
		// Generate a random trace
		int[] randtable = createRandom(n);
		// Convert the random trace to a list
		List<Integer> SequencedTrace = Arrays.stream(randtable).boxed().collect(Collectors.toList());
		// Create a list of integers from 0 to n-1 to check against the trace
		List<Integer> check = IntStream.rangeClosed(0, n - 1).boxed().collect(Collectors.toList());
		// Keep generating random traces until a sequenced trace is found
		while (true) {
			for (int i = 0; i <= SequencedTrace.size() - n; i++) {
				// If the sublist of the trace from i to i+n-1 is equal to the
				// list of integers from 0 to n-1, return the random trace
				if (SequencedTrace.subList(i, i + n).equals(check) == true) {
					return randtable;
				}
			}
			// Generate a new random trace
			randtable = createRandom(n);
			SequencedTrace = Arrays.stream(randtable).boxed().collect(Collectors.toList());
		}

	}

	// Generates an ordered trace of size n: This method generates an ordered trace of integers. It first generates a sequenced trace using the createSequenced method,
	// then loops through the trace to check if it is ordered. If the first element of the trace is not zero, it generates a new
	// sequenced trace until an ordered trace is found. The trace is ordered if each element is greater than the previous maximum
	// element. Once an ordered trace is found, the method returns the trace as an array of integers.
	public static int[] createOrdered(int n) {
		// Generate a sequenced trace
		int[] randtable = createSequenced(n);
		List<Integer> SequencedTrace = Arrays.stream(randtable).boxed().collect(Collectors.toList());
		int maxList = 0;
		// Loop until an ordered trace is found
		while (true) { 
		    // Check if the first element is zero
			if (SequencedTrace.get(0) == 0) {
				// Loop through the trace
				for (int i = 0; i < SequencedTrace.size(); i++) {
		            // If we reach the end of the trace, it's ordered
					if (i == SequencedTrace.size() - 1) {
						//System.out.println(" Ordered trace : " + SequencedTrace);
						return randtable;
					}
					 // Check if the current element is greater than the previous max
					if (SequencedTrace.get(i) > maxList) {
		                // Check if the next element is greater than the current element
						if (SequencedTrace.get(i + 1) > SequencedTrace.get(i)) {
							maxList = SequencedTrace.get(i + 1);
							continue;
						}
		                // If the next element is not greater, the trace is not ordered
						break;
					}

				}
			}
		    // If the first element is not zero, generate a new sequenced trace
			randtable = createSequenced(n);
			SequencedTrace = Arrays.stream(randtable).boxed().collect(Collectors.toList());
			maxList = 0;
		}

	}

	//Generates an strictly ordered trace of size n: This method generates a strictly ordered trace of size n. 
	//It first creates a sequenced trace using the createOrdered() method, and then continuously generates
	//new traces until it finds a trace that meets the strict ordering condition. 
	//It checks if the current trace is strictly ordered by verifying if it contains all the integers from 0 to n-1 in the correct order,
	//and returns the trace once a strictly ordered trace is found.

	public static int[] createStrictOrdered(int n) {
	    while (true) {
	        // Generate an ordered trace
	        int[] orderedTrace = createOrdered(n);
	        boolean isStrictOrdered = true;

	        for (int i = 0; i < n - 1; i++) {
	            int loc1Count = 0;

	            for (int value : orderedTrace) {
	                if (value == i) {
	                    loc1Count++;
	                } else if (value == i + 1) {
	                    if (loc1Count > 0) {
	                        loc1Count--;
	                    } else {
	                        isStrictOrdered = false;
	                        break;
	                    }
	                }
	            }

	            if (!isStrictOrdered) {
	                break;
	            }
	        }

	        if (isStrictOrdered) {
	            return orderedTrace;
	        }
	    }
	}

	// Generates an fair trace of size n: This method generates a fair trace, where all the numbers in the trace appear the same number of times. 
	//It does this by first generating a random trace, then checking the frequency of each number in the trace.
	//If all numbers appear with the same frequency, the trace is considered fair and the random table used to generate the trace is returned.
	//If the trace is not fair, a new random table and trace are generated and checked again.
	public static int[] createFair(int n) {
		// Generate a random trace
		int[] randtable = createRandom(n);
		List<Integer> FairTrace = Arrays.stream(randtable).boxed().collect(Collectors.toList());
		List<Integer> check = IntStream.rangeClosed(0, n - 1).boxed().collect(Collectors.toList());
		int[] count = new int[n];
		while (true) {
			// Count the frequency of each number in the FairTrace
			for (int i = 0; i < n; i++) {
				count[i]=Collections.frequency(FairTrace, check.get(i));}
			// Check if all numbers in the FairTrace have the same frequency
				if(Arrays.stream(count).boxed().collect(Collectors.toList()).stream().distinct().count() <= 1==true) {
				//System.out.println("Fair trace : " + FairTrace);	
				// If the trace is fair, return the random table used to generate it
				return randtable;}
				else {
			// If the trace is not fair, generate a new random table and trace
			randtable =  createRandom(n);
			FairTrace = Arrays.stream(randtable).boxed().collect(Collectors.toList());}
}

}
	

}
