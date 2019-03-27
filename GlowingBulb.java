/**
 * Application to find kth glowing bulb
 * @author AnirudhVeeramalla
 */

import java.io.*;
public class GlowingBulb {
	
	static int[] primes; //holds prime numbers from 1 to 40
    static int len=0;
    
    /**
     * method which finds kth glowing bulb
     * @param kthBulb
     * @return kth glowing bulb
     */
    static long binarySearch(long kthBulb)
    {
        long low=1;
        long high=(long)Math.pow(10,15)*40;
        while(low<high)
        {
            long mid=low+(high-low)/2;
            if(isValid(mid,kthBulb))
            {
            	high=mid;
            }
            else
            {
            	low=mid+1;
            }
        }
        return low;
    }
    
    /**
     * method to find the glowing_number less than or equal to 'number'
     * @param number
     * @param currentposition
     * @param denom
     * @param noOfPrimes
     * @return glowing_number less than or equal to 'number'
     */
    static long glowingNumber(long number,int currentposition,long denom,int noOfPrimes)
    {
        if(currentposition==len) {
        	return 0;
        }
        long include=0;
        long nextdenom=denom*primes[currentposition];
        include=glowingNumber(number,currentposition+1,nextdenom,noOfPrimes+1)+((long)(Math.pow(-1,noOfPrimes+1.0)))*(number/nextdenom);//no Of glowing numbers that are multiples of primes including number 
        long exclude=glowingNumber(number,currentposition+1,denom,noOfPrimes);//no of glowing numbers that are multiples of primes excluding current number
        return include+exclude;
    }
    
    /**
     * method which checks glowing number of current number with k value
     * @param number
     * @param k
     * @return boolean value
     */
    static boolean isValid(long number,long k)
    {
        long glowNumber=glowingNumber(number,0,1,1);
        return glowNumber>=k;
    }
    
    /**
     * method which fills all the primes from switchStatus
     * @param switchStatus
     */
    static void fillPrimes(String switchStatus)
    {
    	primes=new int[12];
        len=0;
    	for(int j=0;j<switchStatus.length();j++)
        {
            if(switchStatus.charAt(j)=='1')
            {
            	primes[len++]=j+1;
            }
        }
    }
    
    /**
     * Main method which takes input and do actual calls to other methods
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
         int t=Integer.parseInt(br.readLine());
         for(int i=0;i<t;i++)
         {
             String switchStatus=br.readLine();
             long kthBulb=Long.parseLong(br.readLine());
             fillPrimes(switchStatus);
             bw.write(Long.toString(binarySearch(kthBulb)));
             bw.newLine();
         }
         br.close();
         bw.close();
    }

}