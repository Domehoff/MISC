import java.util.*;
import java.io.*;

public class Knapsack
{
	private static int SET_LEN = 16;
	public static void main(String[] args) throws Exception
	{
		int[] set = new int[SET_LEN];
		int target;

		if ( args.length < 1 )
		{	System.out.println("FATAL ERROR: must enter input filename on cmd line!\n");
			System.exit(-1);
		}

		Scanner infile = new Scanner(new FileReader(args[0]));
		for (int i=0 ; i<SET_LEN ; ++i)
		{	set[i] = infile.nextInt();
			System.out.format("%d ", set[i]);
		}
		System.out.println();
		target=infile.nextInt();
		System.out.printf("%d\n", target);

		int MAX16BITS = 0X0000FFFF; 
		for ( int bitMap  = 1; bitMap <= MAX16BITS; ++bitMap )
		{
			int sumOfSet = 0;
			String StringOfSet = "";
			for ( int i = 0 ; i < 16 ; ++i )
			{
				if((bitMap >> i)%2 == 1)
				{	
					sumOfSet += set[i];
					StringOfSet += set[i] + " ";
				}
			}
			if(sumOfSet == target)
				System.out.println(StringOfSet);
		}
	} 
} 
