/**
 * Application to find number of specialRods
 * @author AnirudhVeeramalla
 */
import java.io.*;

public class RodCutting {
	
	/**
	 * method to find number of special rods from 1 to n
	 * @param n
	 * @return number of special rods from 1 to n
	 */
	static int noOfSpecialRods(int n)
	{
		//if length is 0 then no of special rods is 0
		if(n==1)
            return 0;
		//if n is special rod itself
        else if((n&(n+1))==0)
            return ((int)Math.ceil((Math.log(n)/Math.log(2))))-1;
		//if n is not special rod
        else
        	return ((int)Math.floor((Math.log(n)/Math.log(2))))-1;
	}
	
	/**
	 * Main Method which takes inputs and calls noOfSpecialRods method
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int t=Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++)
        {
            int n=Integer.parseInt(br.readLine());
            int noSpecialRods=noOfSpecialRods(n);
            bw.write(Integer.toString(noSpecialRods));
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}