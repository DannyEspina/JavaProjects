/*******************************************************************
This is the only file you edit (other than making your own test
files to test your program).

You are required to improve the two functions provided.
Read the comments before each one to see what they should do.
Please note that the implementation you are given is about as bad
as it can get.
Just about anything you try will make it better.

Your goal is to reduce caches from main memory and saves to main
memory by using a cache. You may use other functions and variables
to try and improve your algorithm.

To reach your goal, this is what you have to work with:
You have two caches, keys and vals.
The keys cache is initially all -1, meaning nothing is stored.
As you cache values from main memory, the keys cache will hold the
address from memory of the value copied.
The vals cache is initially all 0.
As you cache values from main memory, the vals cache will hold the
values from memory.
Example:
You cache main memory address 6 to your local cache.
It turns out that main memory address 6 holds the value of 4.
You choose to store that in your local cache at address 2.
Your keys cache will have a 6 at address 2.
Your vals cache will have a 4 at address 2.

How you work with main memory...
To move a value from main memory to cache, use the cache function:
  cache(MainMemoryAddress, CacheAddress);
Both addresses are integers.
To move a value from cache to main memory, use the save function:
  save(CacheAddress);
You only need the cache address because the keys cache has the
main memory address and the vals cache has the value.

You can directly work with your cache using read and write:
	keys.read(2);
This will return the value at address 2 in keys.
You can read from vals also: vals.read(2);
	vals.write(1,16);
This writes a value of 16 to address 1 in vals.
You can write to keys also: keys.write(1,16);

Note: You are trying to reduce the total number of cache and save
calls which use main memory. You can use as many read/write calls
as you like on your local cache.
********************************************************************/

public class MyCache extends CacheSim
{ 
	//incrementWrite will help on alternating indexes
	//in write()
	private int incrementWrite;
	//numOfReads will keep track on the amount of 
	//times a index of a cache has been read
	private int[] numOfReads;
	/**
	* This constructor is required. You must have it.
	* If you REALLY want to, you can initialize variables in here.
	* However, the call to super(i,j) is mandatory.
	*/
	public MyCache(int i, int j)
	{ 
		super(i,j); 
		incrementWrite=0;
		numOfReads = new int[keys.getSize()];
	}
	/**
	* This function reads a value from main memory.
	* However, you can't access main memory directly.
	* So, you either need to find the value in your cache,
	* or you cache it from memory.
	* Once you have it in cache, you can read the value.
	* @param address	The address in main memory
	* @return The value from main memory 
	**/
	public int read(int address)
	{
		System.out.println("Reading from address "+address);
		//foundAddr will stay at -1 if the address is not found
		int foundAddr=-1;
		int size = keys.getSize();
		int readVal;
		//this will find the address in the keys address
		for(int i=0; i<size;i++)
		{
			
			if(keys.read(i)==address)
			{
				foundAddr=i;
				numOfReads[i]+=1;
			}
		}
		
		if(foundAddr==-1)
		{
		//this will find the least used index on the cache 
		int leastUsed=numOfReads[0]; 
		boolean changed = false;
		for(int i=1; i<numOfReads.length;i++)
		{
			if(leastUsed>numOfReads[i])
			{
				leastUsed=i;
				changed = true;
			}
		}
		
		cache(address,leastUsed); 
		keys.write(leastUsed,address);
		readVal=vals.read(leastUsed);
		//resets the numOfReads of the newly written index to 0
		if(!changed)
		{
		numOfReads[leastUsed]=0;
		}
		
		return readVal;
		}
		else
		{
		return vals.read(foundAddr);
		}
	}
/*
	* This function saves a value to memory.
	* However, you can't access main memory directly.
	* You can save the value in your cache.
	* Then, you can write it from your cache to main memory.
	* @param address	The address in main memory
	* @param value	The value to write
	*/
	public void write(int address, int value)
	{
		System.out.println("Writing "+value+" to address "+address);
		
		int size = keys.getSize();
		int leastUsed=numOfReads[0];
		//this will find the least used index on the cache 
		for(int i=1; i<numOfReads.length;i++)
		{
			if(leastUsed>numOfReads[i])
			{
				leastUsed=i;
			}
		}
		vals.write(incrementWrite,value);
		keys.write(incrementWrite, address);	
		incrementWrite++;
		//if the cache is full this will save the least used index
		//and set incrementWrite to this index so it can overwrite it
		//next time around.
		if(incrementWrite==size)
		{
		save(leastUsed);
		incrementWrite=leastUsed;
		}
	

	}

	/**
	* This function is called when the test is complete.
	* Use this to take care of any work you haven't finished.
	*/
	public void uncache()
	{
		
		if(incrementWrite!=keys.getSize())
			for(int i=0; i<incrementWrite; i++)
			{	
			save(i);
			}
	}

	
}
