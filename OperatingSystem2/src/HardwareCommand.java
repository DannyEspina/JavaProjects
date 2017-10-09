import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class HardwareCommand 
{
	private int sector;
	private int track;
	private File file; 
	private PrintWriter out;
	
	
	
	public HardwareCommand() throws FileNotFoundException
	{
		sector=0;
		track=0;
		file = new File("output.txt");
		out = new PrintWriter(new FileOutputStream(file));
	}
	public void SEEK (String position) 
	{
	
		int seeking=Integer.parseInt(position);
		int seekTrack = seeking/10;
		int seekSector = seeking%10;
		if(track==seekTrack)
		{
			while(sector!=seekSector)
			{
				out.println("IDlE");
				System.out.println("IDlE");
				RotateSector();
			}
		}
		if(track<seekTrack)
		{
			out.println("ARM 1");
			System.out.println("ARM 1");
			rightRotateAll();
			
			while(track!=seekTrack)
			{
				out.println("IDlE");
				System.out.println("IDLE");
				//System.out.println(sector+" "+track);
				rightRotateAll();
				//System.out.println(sector+" "+track);
				
			}
			out.println("ARM 0");
			System.out.println("ARM 0");
			RotateSector();
			
		
		if(sector!=seekSector)
		{
			while(sector!=seekSector)
			{
				out.println("IDlE");
				System.out.println("IDlE");
				RotateSector();
			}
		}
		}
		if(track>seekTrack)
		{
			out.println("ARM -1");
			System.out.println("ARM -1");
			leftRotateAll();
			//System.out.println(sector+" "+track);
			
			while(track!=seekTrack)
			{
				out.println("IDlE");
				System.out.println("IDLE");
				//System.out.println(sector+" "+track);
				leftRotateAll();
				//System.out.println(sector+" "+track);
			}
			out.println("ARM 0");
			System.out.println("ARM 0");
			RotateSector();
			
		
		if(sector!=seekSector)
		{
			while(sector!=seekSector)
			{
				out.println("IDlE");
				System.out.println("IDlE");
				//System.out.println(sector+" "+track);
				RotateSector();
				//System.out.println(sector+" "+track);
			}
		}
		}
		
		}
		
		
		
		
	
	public void read(String command)
	{
	
		int count = Integer.parseInt(command);
		for(int i=0; i<count;i++)
		{
		out.println("READ");
		System.out.println("READ");
		RotateSector();
		
		}
		
		
	}
	public void write(String[] commands)
	{
		for(int i=1; i<commands.length;i++)
		{
			out.println("WRITE "+commands[i]);
			System.out.println("WRITE "+commands[i]);
			RotateSector();
			
		}
		
	}
	
	public void rightRotateAll()
	{
		if(this.sector==9)
		{
			this.sector=0;
			this.track+=1;
		}
		else
		{
			this.sector+=1;
			this.track+=1;
			
		}
	
	}
	public void RotateSector()
	{
		if(this.sector==9)
		{
			this.sector=0;
			
		}
		else
		{
			this.sector+=1;
		}
		
	}
	public void leftRotateAll()
	{
		if(this.sector==9)
		{
			this.sector=0;
			this.track-=1;
		}
		else
		{
			this.sector+=1;
			this.track-=1;
		}
	
	}
	public void reset()
	{
		this.sector=0;
		this.track=0;
	}
	public void close()
	{
		out.close();
	}
	public void out(String output)
	{
		out.println(output);
	}
			
}
