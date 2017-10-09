import java.util.Scanner;

public class mainQueen 
{
	public static void main(String[] args)
	{
		
		Scanner scan = new Scanner(System.in);
		int size;
		int count;
		String answer;
		
		System.out.println("Enter the size of the nxn chessboard: ");
		size=scan.nextInt();
		
		Queen Queen= new Queen(size);
		count = Queen.BacktrackAlgor(Queen.getArray(), 0, 0, 0, false);
		//count = Queen.MinConflicts(Queen.getArray(), 0,0, false);
		System.out.println("the number of Queen placements are: " + count);
	}
}
