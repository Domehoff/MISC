/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph 
{
	private final int NO_EDGE = -1; // all real edges are positive
	private int G[][];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new int[dimension][dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;
		
		for(int i = 0; i < dimension; i++)
		{	for(int j = 0; j < dimension; j++)
			{
				if(i == j)
				{
					G[i][j] = 0;
				}
				else 
				{
					G[i][j] = NO_EDGE;
				}
			}
		}
		ArrayList<Integer> values = new ArrayList<Integer>();
		while ( graphFile.hasNextInt() )
		{
			int temp = graphFile.nextInt();
			values.add(temp);
			if(values.size() == 3)
			{
				addEdge(values.get(0), values.get(1), values.get(2));
				values.clear();
			}
		}
	} // END readGraphFile
	private void addEdge( int r, int c, int w )
	{
		G[r][c] = w;
		++numEdges; // only this method adds edges so we do increment counter here only
	}
  private boolean hasEdge(int fromNode, int toNode)
  {
		if(G[fromNode][toNode] > 0) return true;
		else return false;
  }
	private int inDegree(int node)
	{
		int inCount = 0;
		for(int i = 0; i < G.length; i++)
		{
			if((G[i][node] > 0))
			{
				inCount++;
			}
		}
		return inCount;
	}
	private int outDegree(int node)
	{
		int outCount = 0;
		for(int i = 0; i < G.length; i++)
		{
			if((G[node][i] > 0))
			{
				outCount++;
			}
		}
		return outCount;
	}
	private int degree(int node)
	{
		int tempD = inDegree(node) + outDegree(node);
		return tempD;
	}
	public int maxOutDegree()
	{
		ArrayList<Integer> maxOut = new ArrayList<Integer>();
		int tempAdd;
		for(int i = 0; i < G.length-1; i++)
		{
			tempAdd = outDegree(i);
			maxOut.add(tempAdd);
		}
		return Collections.max(maxOut);
	}
	public int maxInDegree()
	{
		ArrayList<Integer> maxIn = new ArrayList<Integer>();
		int tempAdd;
		for(int i = 0; i < G.length-1; i++)
		{
			tempAdd = inDegree(i);
			maxIn.add(tempAdd);
		}
		return Collections.max(maxIn);
	}
	public int minOutDegree()
	{
		int[] temp = new int[G.length-1];
		int tempAdd;
		for(int i = 0; i < G.length-1; i++)
		{
			tempAdd = outDegree(i);
			temp[i] = tempAdd;
		}
		Arrays.sort(temp);
		return temp[0];
	}
	public int minInDegree()
	{
		int[] temp = new int[G.length-1];
		int tempAdd;
		for(int i = 0; i < G.length-1; i++)
		{
			tempAdd = inDegree(i);
			temp[i] = tempAdd;
		}
		Arrays.sort(temp);
		return temp[0];
	}	
	public int maxDegree()
	{
		ArrayList<Integer> max = new ArrayList<Integer>();
		int tempAdd;
		for(int i = 0; i < G.length-1; i++)
		{
			tempAdd = degree(i);
			max.add(tempAdd);
		}
		return Collections.max(max);
	}
	public int minDegree()
	{
		ArrayList<Integer> min = new ArrayList<Integer>();
		int tempAdd;
		for(int i = 0; i < G.length-1; i++)
		{
			tempAdd = degree(i);
			min.add(tempAdd);
		}
		Collections.sort(min);
		return min.get(0);
	}
	public void removeEdge(int fromNode, int toNode)
	{
		try
		{
			if(hasEdge(fromNode, toNode))
			{
				G[fromNode][toNode] = NO_EDGE;
			}
		}
		catch(Exception X)
		{
			System.out.println("java.lang.Exception: Non Existent Edge Exception: " + "removeEdge(" + fromNode + "," + toNode + ")");
			System.exit(0);
		}
	}
	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			for ( int c=0 ; c < G[r].length ; ++c )
				the2String += String.format("%3s",G[r][c] + " ");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING
} //EOF