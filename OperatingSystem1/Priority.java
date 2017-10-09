//This algorithm will work on jobs with the highest priority
public class Priority {
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
				with the highest priority */
				int temp=jobs[0][2];
				int count= 0; 
				
				//if there's only one job than just cycle the first element in the array
				if (jobs.length==1)
					{
					System.out.println("  Working on Job "+jobs[0][0]);
					jobsource.cycle(jobs[0][0]);
					}
					
				else
				{
					//finds the highest priority job
					for(int i=0; i<jobs.length; i++)
						{
							if(temp<jobs[i][2])
							{
								temp = jobs[i][2];
								count= i; 
							}
						}
				//uses count to pinpoint the job with the highest priority	
				System.out.println("  Working on Job "+jobs[count][0]);
				
				
				jobsource.cycle(jobs[count][0]);
				}
				
			}
			
			else jobsource.cycle();
		}
		
		
		System.out.println(jobsource.report());
	}
}
