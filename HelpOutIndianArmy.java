/**
 * Application to help Indian by determining uncovered area
 * @author AnirudhVeeramalla
 */

import java.util.*;
import java.io.*;

/**
 * class which contains left and right protected boundaries
 * @author AnirudhVeeramalla
 *
 */
class ProtectedArea implements Comparable<ProtectedArea>{
	
    long leftBoundary;
    long rightBoundary;
    
    ProtectedArea(long leftBoundary,long rightBoundary)
    {
        this.leftBoundary=leftBoundary;
        this.rightBoundary=rightBoundary;
    }
    
    @Override
    public int compareTo(ProtectedArea other)
    {
        if(this.leftBoundary<other.leftBoundary)
        {
        	return -1;
        }
        else if(this.leftBoundary>other.leftBoundary)
        {
        	 return 1;
        }
        return 0;
    }
}

public class HelpOutIndianArmy {
	
	 static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

     /**
      * method which finds unprotected area by Indian army
      * @param s
      * @param e
      * @param n
      * @throws Exception
      */
	static void unProtectedArea(long s,long e,long n) throws Exception
	{
		PriorityQueue<ProtectedArea> pq=new PriorityQueue<ProtectedArea>(); //priority queue is used to get minimum left boundary
        long unprotected=e-s;
        
        for(int i=0;i<n;i++)
        {
            String[] xcor=br.readLine().split(" ");
            long x=Long.parseLong(xcor[0]);
            long power=Long.parseLong(xcor[1]);
            pq.add(new ProtectedArea(x-power,x+power));// add all protectedArea into priority queue
        }
        
        long protectedarea=0;
        while(pq.size()>1)
        {
        	ProtectedArea firstsmall=pq.remove();
        	ProtectedArea secondsmall=pq.remove();
            if(firstsmall.rightBoundary>=secondsmall.leftBoundary)
            {
                pq.add(new ProtectedArea(firstsmall.leftBoundary,Math.max(firstsmall.rightBoundary,secondsmall.rightBoundary))); //merge two areas
            }
            else
            {
                pq.add(secondsmall);
                if(firstsmall.leftBoundary>e||firstsmall.rightBoundary<s)
                {
                	continue;
                }
                if(firstsmall.leftBoundary<s)
                {
                	firstsmall.leftBoundary=s;
                }
                if(firstsmall.rightBoundary>e)
                {
                	firstsmall.rightBoundary=e;
                }
                protectedarea+=(firstsmall.rightBoundary-firstsmall.leftBoundary); //add to protected area
            }
        }
        if(!pq.isEmpty()) //single element in priority queue
        {
        	ProtectedArea firstsmall=pq.remove();
          if(!(firstsmall.leftBoundary>e||firstsmall.rightBoundary<s)) //Area which is not out of bounds 
          {
             if(firstsmall.leftBoundary<s)
             {
        	   firstsmall.leftBoundary=s;
             }
	         if(firstsmall.rightBoundary>e)
	         {
	        	 firstsmall.rightBoundary=e;
	         }
	         protectedarea+=(firstsmall.rightBoundary-firstsmall.leftBoundary);
          }
        }
        bw.write(Long.toString(unprotected-protectedarea));
        br.close();
        bw.close();
	}
	
	/**
	 * Main method which takes input and calls unprotectedArea method
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
        long[] input=Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        unProtectedArea(input[1],input[2],input[0]);
    }
}
