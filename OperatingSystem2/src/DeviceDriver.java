import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DeviceDriver
{
	public static void main(String[] args) throws Exception
	{
	String testfile="test.txt";

	BufferedReader in = new BufferedReader(new FileReader(testfile));
	
	String line = in.readLine();
	String[] split;
	
	String command1;
	String command2;
	
	
	HardwareCommand disk = new HardwareCommand();
	if(line.equals("===================="))
		{
		
		line=in.readLine();
		
			for(int i=0; line!= null;i++)
			{
				if(!line.equals("===================="))
				{
					split=line.split(" ");
					command1=split[0];
					if(command1.equalsIgnoreCase("seek"))
					{
						command2=split[1];
						disk.SEEK(command2);
						
					
					}
					else if(command1.equalsIgnoreCase("read"))
					{
						command2=split[1];
						//System.out.println(command2);		
						disk.read(command2);
					}
					else if(command1.equalsIgnoreCase("write"))
					{
						disk.write(split);
					}

				}
				else
				{
					disk.out("====================");
					System.out.println("====================");
					disk.reset();
				}
				line=in.readLine();
			}
		
		}
	disk.close();
	}
	
}
