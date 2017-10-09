import java.util.Random;
public class Queen 
{
	private int[][] board;
	private int[][] conflicts;
	private int size;
	private int[] prevRow;
	private int[] prevCol; 
	private int[] ConflictQs;
	
	private int numOfQ;
	private boolean notDone= true;
	private Random rand;
public Queen(int n)
{
	rand = new Random();
	numOfQ=0;
	size = n;
	prevRow = new int[size+1];
	prevCol = new int[size+1];
	ConflictQs = new int[size];
	board = new int[size][size]; 
	conflicts = new int[size][size];
	for(int i=0; i<=size-1; i++)
	{
		ConflictQs[i]=-1;
		
		for(int j=0; j<=size-1; j++)
		{
			conflicts[i][j]=0;
			board[i][j]=0;
		}
	}
}
public int[][] getArray()
{
	return board;
}
public boolean isSafe(int[][] a, int col, int row)
{

	//checks right columns 
	for (int i=col+1; i<=size-1; i++)
	{
		if(a[row][i]==1)
		{
			
			return false;
		}
	}
	//checks left columns

	for (int i=col-1; i>=0; i--)
	{
		
		if(a[row][i]==1)
		{
			
			return false;
		}
	}
	//checks upper left diagonal  

	for (int i=row, j=col; i>=0 && j>=0; i--, j-- )
	{
		
		if(a[i][j]==1)
		{
			
			return false;
		}
	}
	//checks lower left diagonal
	for(int i=row, j=col; i<=size-1 && j>=0; i++, j--)
	{
		
		if (a[i][j]==1)
		{
			
			return false;
		}
	}
	
	return true;
}
public void removeQueen(int[][] a, int col, int row)
{
	a[row][col]=0;
	numOfQ--;
}

public int BacktrackAlgor(int[][] a, int col, int row, int count, boolean remove)
{
	
	if(count==0)
	{
	
	a[row][col]=1;
	System.out.println("Board: ");
	printArray(a);
	numOfQ++;
	prevRow[numOfQ]=row;
	prevCol[numOfQ]=col;
	col++;
	count++;
	}
	else if(remove && numOfQ>=0)
	{
		
		row=prevRow[numOfQ];
		row++;
		
		removeQueen(a,prevCol[numOfQ],prevRow[numOfQ]);
	}
	
	while(col<size && notDone)
	{
		if(row>=size && col!=0)
		{
			
		 	count=BacktrackAlgor(a,col-1,0,count,true);
		 	col--;
		}
		else if(row>=size && col==0)
		{
			
			notDone=false;
			return count;
		}
		if(notDone)
		{
		if(isSafe(a, col, row))
			{
			
				a[row][col]=1;
				System.out.println("Board: ");
				printArray(a);
				numOfQ++;
				prevRow[numOfQ]=row;
				prevCol[numOfQ]=col;
				col++;
				count++;
			
				count = BacktrackAlgor(a,col,0,count,false);
			}
		else
			{	
				row++;
			}
		
		}	
		
	}

	return count;
		
}
//------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------
public int MinConflicts(int[][] a, int count)
{

	
	a=intializeBoard(a);
	count+=size;
	bestFitQueen(a,count,0);
	System.out.println("Board:");
	printArray(a);
	System.out.println("Conflicts table");
	printArray(conflicts);
	return count;
}
public int[][] intializeBoard (int[][] a)
{
	int index;
	for(int i=0; i<=size-1; i++)
	{
	index=findSpot(a,i,-1);
	System.out.println("index "+ index);
	a[index][i]=1;
	addQueenPaths(index, i);
	System.out.println("Conflicts yo" + conflicts[index][i]);
	if(conflicts[index][i]>1)
	{
		System.out.println("well");
		ConflictQs[index]=i;
	}
	System.out.println("Board:");
	printArray(a);
	System.out.println("Conflicts table");
	printArray(conflicts);
	}
	
	return a;
}
public int findSpot(int[][] a, int col, int avoid)
{
	System.out.println("avoid "+ avoid);
	int[] rowIndex = new int[size];
	int count =0;
	int randNum;
	int min=a[0][col];
	int index= min;
	
	for(int i=0; i<=size-1; i++)
	{
		if(conflicts[i][col]<min)
		{
			min=conflicts[i][col];
		}
	}
	
	for(int i=0; i<=size-1; i++)
	{
		if(conflicts[i][col]==min)
		{
			
			count++;
			System.out.println("count " + (count-1));
			rowIndex[count-1]=i;
		}
	}
	
	if(count==1)
	{
		index=rowIndex[0];
	}
	else if(count>1)
	{
		randNum=rand.nextInt(count)+0;
		while(avoid==randNum)
		{
		randNum=rand.nextInt(count)+0;
		System.out.println("random " +randNum);
		System.out.println("min " + min);
		}
		index=rowIndex[randNum];
		
	}
	
	return index;
}
public void printArray(int[][] a)
{
	for(int i=0; i<=size-1; i++)
	{
		for(int j=0; j<=size-1; j++)
		{
			System.out.print(a[i][j] + " ");
		}
		System.out.println();
	}
}
public void addQueenPaths(int row, int col)
{
	conflicts[row][col]+=1;
	//going left
	for(int i = col+1; i<=size-1; i++)
	{
		conflicts[row][i]+=1;
		
	}
	//going right
	for(int i=col-1; i>=0; i--)
	{
		conflicts[row][i]+=1;
		
	}
	//going up
	for(int i=row+1; i<=size-1; i++)
	{
		conflicts[i][col]+=1;
		
	}
	//going down
	for(int i=row-1; i>=0; i--)
	{
		conflicts[i][col]+=1;
		
	}
	//going down right
	for(int i=row+1, j=col+1; i<=size-1 && j<=size-1; i++, j++)
	{
		conflicts[i][j]+=1;
		
	}
	//going up left
	for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--)
	{
		conflicts[i][j]+=1;
		
	}
	//going down left
	for(int i=row+1, j=col-1; i<=size-1 && j>=0; i++, j--)
	{
		conflicts[i][j]+=1;
		
	}
	//going up right
	for(int i=row-1, j=col+1; i>=0 && j<=size-1; i--, j++)
	{
		conflicts[i][j]+=1;
	
	}
}
public void removeQueenPaths(int row, int col)
{
	conflicts[row][col]-=1;
	//going left
	for(int i = col+1; i<=size-1; i++)
	{
		conflicts[row][i]-=1;
	}
	//going right
	for(int i=col-1; i>=0; i--)
	{
		conflicts[row][i]-=1;
	}
	//going up
	for(int i=row+1; i<=size-1; i++)
	{
		conflicts[i][col]-=1;
	}
	//going down
	for(int i=row-1; i>=0; i--)
	{
		conflicts[i][col]-=1;
	}
	//going down right
	for(int i=row+1, j=col+1; i<=size-1 && j<=size-1; i++, j++)
	{
		conflicts[i][j]-=1;
	}
	//going up left
	for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--)
	{
		conflicts[i][j]-=1;
	}
	//going down left
	for(int i=row+1, j=col-1; i<=size-1 && j>=0; i++, j--)
	{
		conflicts[i][j]-=1;
	}
	//going up right
	for(int i=row-1, j=col+1; i>=0 && j<=size-1; i--, j++)
	{
		conflicts[i][j]-=1;
	}
}
public int[] findQueen(int[][] a, int row, int col)
{
	int[] b = new int[size];
	for(int i =0; i<=size-1; i++)
	{
		b[i]=-1;
	}
	//going right
	for(int i = col+1; i<=size-1; i++)
	{
		if(a[row][i]==1)
		{
			System.out.println("one");
			b[row]=i;
			return b ;
		}
	}
	//going left
	for(int i=col-1; i>=0; i--)
	{
		if(a[row][i]==1)
		{
			System.out.println("two");
			b[row]=i;
			return b;
		}
	}
	//going up
	for(int i=row+1; i<=size-1; i++)
	{
		if(a[i][col]==1)
		{
			System.out.println("three");
			b[i]=col;
			return b;
		}
	}
	//going down
	for(int i=row-1; i>=0; i--)
	{
		if(a[i][col]==1)
		{
			System.out.println("four");
			b[i]=col;
			return b;
		}
	}
	//going down right
	for(int i=row+1, j=col+1; i<=size-1 && j<=size-1; i++, j++)
	{
		if(a[i][j]==1)
		{
			System.out.println("five");
			b[i]=j;
			return b;
		}
	}
	//going up left
	for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--)
	{
		if(a[i][j]==1)
		{
			System.out.println("six");
			b[i]=j;
			return b;
		}
	}
	//going down left
	for(int i=row+1, j=col-1; i<=size-1 && j>=0; i++, j--)
	{
		if(a[i][j]==1)
		{
			System.out.println("seven");
			b[i]=j;
			return b;
		}
	}
	//going up right
	for(int i=row-1, j=col+1; i>=0 && j<=size-1; i--, j++)
	{
		if(a[i][j]==1)
		{
			System.out.println("eight");
			b[i]=j;
			return b;
		}
	}

	return b;
}
public int bestFitQueen(int[][] a, int count, int i)
{
	System.out.println("lskdjflajksdf");
	int[] save = new int[size];
	int avoid;
	int row;
	int col;
	int index;
	for (int j=0; j<=size-1; j++)
	{
		
		save[j]=-1;
	}

	System.out.println("hello" + i);
	System.out.println(i + " " + ConflictQs[i]);
	if(ConflictQs[i]!=-1)
	{
		System.out.println("hi");
		System.out.println("row " + i + " col " + ConflictQs[i]);
		row = i;
		col = ConflictQs[i];
		save = findQueen(a,row,col);
		for(int j=0; j<=size-1; j++)
		{
			if(save[j]!=-1)
			{
				row=j;
				col=save[j];
			}
		}
		ConflictQs[i]=-1;
		System.out.println("row after " + row + " col after " +col);
		a[row][col]=0;
		System.out.println("Board remove:");
		printArray(a);
		removeQueenPaths(row, col);
		System.out.println("Conflicts table remove");
		printArray(conflicts);
		
		index = findSpot(a, col, row);
		System.out.println("index yo " + index);
		a[index][col]=1;
		if(conflicts[index][col]>1)
		{
			ConflictQs[index]=col;
		}
		addQueenPaths(index, col);
		System.out.println("Board:");
		printArray(a);
		System.out.println("Conflicts table");
		printArray(conflicts);
		bestFitQueen(a, count, index);
		
}
	if(i<size-1)
	{
	bestFitQueen(a, count, i+1);
	}
	return count;
}	
}

