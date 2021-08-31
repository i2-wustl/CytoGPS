package toolkit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class Permutation {
	
	public static void getCombination(int arr[], int n, int r, List<List<Integer>> combination)
    {
        // A temporary array to store all combination one by one
        int data[]=new int[r];
        // Get all combination using temprary array 'data[]'
        combinationUtil(arr, data, 0, n-1, 0, r, combination);
    }
	
	private static void combinationUtil(int arr[], int data[], int start, int end, int index, int r, List<List<Integer>> combination) {
		// Current combination is ready to be added, add it
		if (index == r)
		{
			List<Integer> oneCombination = new ArrayList<>();
			for (int x: data) {
				oneCombination.add(x);
			}
			combination.add(oneCombination);
			return;
		}
		
		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
		
		for (int i=start; i<=end && end-i+1 >= r-index; i++)
		{
			data[index] = arr[i];
			combinationUtil(arr, data, i+1, end, index+1, r, combination);
		}

	}
	

}
