//This algorithm will work on jobs with the shortest duration.
public class Shortest {
	public static void main(String[] args)
	{

		Jobs jobsource = new Jobs();
		
		System.out.println(jobsource);
		
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
				
				/*The temp variable will be used inside the loop to store the job
				with the lowest duration time */
				int temp=jobs[0][1];
				int count= 0; 
				
				//if there's only one job than just cycle the first element in the array
				if (jobs.length==1)
					{
					System.out.println("  Working on Job "+jobs[0][0]);
					jobsource.cycle(jobs[0][0]);
					}
					
				else
				{
				//finds the shortest job
					for(int i=0; i<jobs.length; i++)
						{
							if(temp>jobs[i][1])
							{
								temp = jobs[i][1];
								count= i; 
							}
						}
				//uses count to pinpoint the shortest job
				System.out.println("  Working on Job "+jobs[count][0]);
				
				
				jobsource.cycle(jobs[count][0]);
				}
				
			}
			
			else jobsource.cycle();
		}
		
		
		System.out.println(jobsource.report());
	}
	}
