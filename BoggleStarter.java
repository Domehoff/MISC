import java.io.*;
import java.util.*;


public class Boggle
{	
	static String[][] board;
	static long startTime,endTime;
	static final long MILLISEC_PER_SEC = 1000;
	static TreeSet<String> dictSet = new TreeSet<String>();
	static TreeSet<String> foundSet = new TreeSet<String>();

	public static void main( String args[] ) throws Exception
	{	
		startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );
		
		BufferedReader infile = new BufferedReader(new FileReader(args[0]));
		
		while ( infile.ready() )
		{
			dictSet.add(infile.readLine());
		}
		
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				dfs( row, col, ""  );
			}
		}
			
		for (String e : foundSet )
		{
			System.out.print(e);
			System.out.println();
		}
	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{	
		word += board[r][c];
		//CHECK
		if (dictSet.contains(word) && word.length() >= 3)
		{
			foundSet.add(word);
		}
		//NORTH
		if ( r-1 >= 0 && board[r-1][c] != null )
		{	
			String temp = board[r][c];
			board[r][c] = null; 
			dfs( r-1, c, word ); 
			board[r][c] = temp;
		}
		//NORTH EAST
		if ( r-1 >= 0 && c + 1 < board[0].length && board[r-1][c + 1] != null )  
		{	
			String temp = board[r][c];
			board[r][c] = null;
			dfs( r-1, c + 1, word ); 
			board[r][c] = temp;
		}
		//EAST
		if (c + 1 < board[0].length && board[r][c + 1] != null )  
		{	
			String temp = board[r][c]; 
			board[r][c] = null; 
			dfs( r, c + 1, word );
			board[r][c] = temp; 
		}
		//SOUTH EAST
		if (r + 1 < board.length && c + 1 < board[0].length && board[r+1][c + 1] != null )  
		{	
			String temp = board[r][c];
			board[r][c] = null;
			dfs( r+1, c+1, word ); 
			board[r][c] = temp; 
		}
		//SOUTH
		if (r + 1 < board.length && board[r + 1][c] != null )  
		{	
			String temp = board[r][c];
			board[r][c] = null;
			dfs( r + 1, c, word );
			board[r][c] = temp;
		}
		//SOUTH WEST
		if (r+1 < board.length && c - 1 >= 0 && board[r+1][c-1] != null )   
		{	
			String temp = board[r][c]; 
			board[r][c] = null; 
			dfs( r + 1, c - 1, word ); 
			board[r][c] = temp;
		}
		//WEST
		if (c - 1 >= 0  && board[r][c - 1] != null )  
		{	
			String temp = board[r][c];
			board[r][c] = null; 
			dfs( r , c - 1 , word ); 
			board[r][c] = temp;
		}
		//NORTH WEST
		if (r - 1 >= 0 && c - 1 >= 0 && board[r - 1][c - 1] != null ) 
		{	
			String temp = board[r][c]; 
			board[r][c] = null; 
			dfs( r - 1, c - 1, word ); 
			board[r][c] = temp; 
		}
		
	} // END DFS ----------------------------------------------------------------------------

	//=======================================================================================
	static String[][] loadBoard( String fileName ) throws Exception
	{	
		Scanner SC = new Scanner( new File(fileName) );
		int r = SC.nextInt();
		int c = r;
		String[][] board = new String[r][c];
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				board[i][j] = SC.next();
			}
		}
		SC.close();
		return board;
	} //END LOADBOARD 

} // END BOGGLE CLASS
