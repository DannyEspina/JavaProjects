// This is an example of how to use the Jobs class for your project.
// This implements a FIFO algorithm. Therefore, NOBODY in the class should have trouble with the FIFO part of the assignment.

/**
I did not do anything special to compile this. I used:
  javac Test.java
Then, I ran the program with:
  java Test
**/

public class First
{
	public static void main(String[] args)
	{

		Jobs jobsource = new Jobs();
		
		System.out.println(jobsource);
		
		while(!jobsource.done())
		{
			// Load the current waiting jobs (don't try to work with an old set, get a new set every cycle).
			int[][] jobs = jobsource.getJobs();
			
			// This is for debugging. It is nice to know what clock cycle you are on and how many jobs there are.
			System.out.println("Clock: "+jobsource.getClock());
			System.out.println("  Job Count: "+jobs.length);
			
			// Don't assume that there is a waiting job. The job list may be empty.
			if(jobs.length>0)
			{
				// For debugging... Show the job list
				// Notice, index 0 is the Job ID, index 1 is the duration left, and index 2 is the priority.
				// The jobs will always be sorted from lowest Job ID to highest Job ID.
				for(int j=0; j<jobs.length; j++)
					System.out.println("    Job:"+jobs[j][0]+" Dur:"+jobs[j][1]+" Pri:"+jobs[j][2]);
					
				// More debugging... Show which job you are going work on.
				System.out.println("  Working on Job "+jobs[0][0]);
				
				// Cycle your selected job.
				jobsource.cycle(jobs[0][0]);
			}
			// If you have an empty job list, you must run an empty cycle.
			else jobsource.cycle();
		}
		
		// All done. Print the report (or save it to a CSV file).
		System.out.println(jobsource.report());
	}
}