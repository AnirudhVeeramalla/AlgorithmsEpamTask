/**
 * Application to sort numbers based on weights assigned to numbers
 * @author AnirudhVeeramalla
 */

import java.util.*;
import java.io.*;
public class DescendingWeights {

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    
    /**
     * method to sort numbers based on weights
     * @param input
     * @param k
     * @return sorted number based on weights
     */
	@SuppressWarnings("unchecked")
	static int[] bucketSort(int[] input,int k)
    {
        ArrayList<Integer>[] buckets=(ArrayList<Integer>[])new ArrayList[k]; // declare k buckets
        for(int i=0;i<k;i++)
        {
        	buckets[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<input.length;i++)
        {
        	buckets[input[i]%k].add(input[i]); //fill number at specified bucket
        }
        for(int i=0;i<k;i++)
        {
        	Collections.sort(buckets[i]); //sort each bucket to get minimum value first
        }
        int counter=0;
        for(int i=k-1;i>=0;i--)
        {
            for(int j=0;j<buckets[i].size();j++)
            {
            	input[counter++]=buckets[i].get(j);//place result back into input array
            }
        }
        return input;
    }
	
	/**
	 * method to print result
	 * @param result
	 * @throws Exception
	 */
	static void printResult(int[] result) throws Exception
	{
		 for(int i=0;i<result.length;i++)
		 {
			 bw.write(Integer.toString(result[i])+" ");
		 }
		 bw.newLine();
	}
	
	/**
	 * Main method which takes input and calls other functions
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args ) throws Exception {
        int[] input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();//taking input
        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        inputs=bucketSort(inputs,input[1]);
        printResult(inputs);
        br.close();
        bw.close();
    }
}
