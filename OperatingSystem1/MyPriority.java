//This algorithm will alternate between the jobs with small durations or high priority.
//The goal here is to not work on the same job two clock cycles in a row.
//Unless the job array only has one element. 
public class MyPriority
{
	public static void main(String[] args)
	{

		Jobs jobsource = new Jobs();
	
		System.out.println(jobsource);
		
		//these variables are initialized before the loop.
		boolean alternate = true;
		int tempd1=0;
		int tempp1=0;
		int index1=0;
		int jobID1=0;
		int jobID2=0;
		while(!jobsource.done())
		{
			
			int[][] jobs = jobsource.getJobs();
			
			
			System.out.println("Clock: "+jobsource.getClock());
			System.out.println("  Job Count: "+jobs.length);
			
			
			if(jobs.length>0)
			{
				
				for(int j=0; j<jobs.length; j++)
					System.out.println("    Job:"+jobs[j][0]+" Dur:"+jobs[j][1]+" Pri:"+jobs[j][2]);
				
				if(jobs.length==1)	
				{
					jobsource.cycle(jobs[0][0]);
					System.out.println("  Working on Job "+jobs[0][0] +" one");
					
					
				}
				else
				{
				if(alternate==true)
				{
				tempd1=jobs[0][1];
				tempp1=jobs[0][2];
				index1=0;
				jobID1=0;
				
				/*finds the job with the lowest duration time or highest priority
				 that is not the same job as the previous clock cycle*/
				for(int i=0; i<jobs.length; i++)
				{
					if(jobs[i][0]!=jobID2)
					{
					if(tempd1>jobs[i][1] || tempp1<jobs[i][2])
					{
						tempd1=jobs[i][1];
						tempp1=jobs[i][2];
						jobID1=jobs[i][0];
						index1 =i;
					}
					}
				}
				System.out.println("  Working on Job "+jobs[index1][0]+" true " +jobID1);
				jobsource.cycle(jobs[index1][0]);
				// set to false to alternate in the next clock cycle.
				alternate = false;
				}
				
				//if alternate==false go here...
				else
				{
				int tempd2 =jobs[0][1];
				int tempp2 =jobs[0][2];
				int index2=0;
				jobID2=0;
				
				/*finds the job with the lowest duration time or highest priority
				 that is not the same job as the previous clock cycle*/
				for(int i=0; i<jobs.length; i++)
				{
					if(jobs[i][0]!=jobID1)
					{
					if(tempd2>jobs[i][1] || tempp2<jobs[i][2])
					{
						tempd2 =jobs[i][1];
						tempp2 =jobs[i][2];
						jobID2 =jobs[i][0];
						index2 =i;
					}
					}
				}
			
				System.out.println("  Working on Job "+jobs[index2][0] + " false " +jobID2);
				jobsource.cycle(jobs[index2][0]);
				alternate = true;
				}
					
				}
			}
			
			else jobsource.cycle();
		}
		
		System.out.println(jobsource.report());
	}
}