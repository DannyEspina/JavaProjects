//This algorithm will work on jobs with the highest waiting time
import java.util.ArrayList;
public class Robin {
	public static void main(String[] args)
	{

		Jobs jobsource = new Jobs();
		
		System.out.println(jobsource);
		
		//A double dimension is used to store the job ID and to 
		//keep track of the waiting times.
		ArrayList<ArrayList<Integer>> wait =new ArrayList<ArrayList<Integer>>();
		while(!jobsource.done())
		{
			
			int[][] jobs = jobsource.getJobs();
		
			System.out.println("Clock: "+jobsource.getClock());
			System.out.println("  Job Count: "+jobs.length);
			
			
			if(jobs.length>0)
			{
				for(int j=0; j<jobs.length; j++)
				{
					System.out.println("    Job:"+jobs[j][0]+" Dur:"+jobs[j][1]+" Pri:"+jobs[j][2]);
				}
				//finds the largest job ID
				int largest = jobs[0][0]; 
				for(int i=0; i<jobs.length; i++)
				{
					if(largest<jobs[i][0])
						largest = jobs[i][0];
				}
				//uses the largest job ID as a condition when adding new arraylists.
				//once created it is initialized to 0
				for(int i=wait.size(); i<=largest; i++)
				{
						wait.add(i, new ArrayList<Integer>());
						wait.get(i).add(0);
					
				}
				
				
				int count= 0; 
				if (jobs.length==1)
					{
					System.out.println("  Working on Job "+jobs[0][0]);
					//reset jobs[0][0] waiting time to 0
					wait.get(jobs[0][0]).add(0,0);
					jobsource.cycle(jobs[0][0]);
					}
					
				else
				{
					//stores the first element's waiting time into temp
					int temp = wait.get(jobs[0][0]).get(0);
					//finds the job with the highest waiting time
					for(int i=1; i<jobs.length; i++)
						{
							if(temp<wait.get(jobs[i][0]).get(0))
							{
								temp=wait.get(jobs[i][0]).get(0);
								count =i;
							}
						}
				/*count is used to pinpoint the job with the highest 
				waiting time and reset it to 0*/
				wait.get(count).add(0);
				
				/*increase the waiting time on every jobs except the one with 
				 the highest waiting time */
				int incNum;
				for(int i=0; i<jobs.length; i++)
				{
					if(i!=count)
					{
						incNum=wait.get(jobs[i][0]).get(0);
						wait.get(jobs[i][0]).add(0,++incNum);
						

					}
				}
				
				System.out.println("  Working on Job "+jobs[count][0]);
				
				wait.get(jobs[count][0]).add(0,0);
				
				jobsource.cycle(jobs[count][0]);
				}
				
			}
			
			else jobsource.cycle();
		}
		
		
		System.out.println(jobsource.report());
	}
}
