import java.text.DecimalFormat;

public class problem1 {
	public static void main(String[] args)
	{
		
		DecimalFormat df = new DecimalFormat("0.000000");
		int count= 1;
		double epsilon = 0.00001; 
		double maxK, maxKMinus1, supNorm;
		double[] X= {0,0,0};
		double[] XofK = new double[3];
		System.out.println("(1) Fixed-Point:\n");
		  System.out.print(" K  |   X1^(K)  |   X2^(K)  |   X3^(K)  | ||X^(K)-X^(K-1)||∞   \n");
		System.out.println("============================================================");
		do 
		{
			XofK[0] = (1/3.0)*(Math.cos(X[1]*X[2])+0.5);
			XofK[1] = (5/81.0)*(Math.pow(X[0],2)+Math.sin(X[2])+1.06)-5*(Math.pow(X[1],2)+0.01);
			XofK[2] = (10*Math.PI-3)/(-60)+(Math.pow(Math.E, -X[0]*X[1]))/(-20); 
			
			maxK = max(XofK);
			maxKMinus1 = max(X);
			supNorm = Math.abs(maxK-maxKMinus1);
			
			if(count>9)
				System.out.print(" "+count+" |");
			else
				System.out.print(" "+count+"  |");
			
			
			System.out.print("  "+df.format(XofK[0])+ " |  "+df.format(XofK[1]) +" |"+df.format(XofK[2]) +" |");
			System.out.println("  "+df.format(supNorm)+" ");
			
			X[0] = XofK[0]; 
			X[1] = XofK[1]; 
			X[2] = XofK[2];
			count++;
		}while(supNorm>epsilon);
		
	
		
		// Quasi-Newton's Method 
		X[0]=0;
		X[1]=0;
		X[2]=0;
		double h = 0.000000000001;
		double[] Y = new double[3];
		double[] Func = new double[3];
		double[][] J;
		count = 1;
		System.out.println("\n(3) Quasi-Newtons Method:\n");
		  System.out.print(" K  |   X1^(K)  |   X2^(K)  |   X3^(K)  | ||X^(K)-X^(K-1)||∞   \n");
		System.out.println("============================================================");
			
		do {

		J = jacboiM(X,h);
		
		Func[0]=-F(X,0);
		Func[1]=-F(X,1);
		Func[2]=-F(X,2);
	
		Y=SystemSolve(J,Func);
		maxKMinus1 = max(X);
		X[0]= X[0]+Y[0];
		X[1]= X[1]+Y[1];
		X[2]= X[2]+Y[2];
		maxK = max(X);
		
		supNorm = Math.abs(maxK-maxKMinus1);
		
		if(count>9)
			System.out.print(" "+count+" |");
		else
			System.out.print(" "+count+"  |");
		
		System.out.print("  "+df.format(X[0])+ " |  "+df.format(X[1]) +" |"+df.format(X[2]) +" |");
		System.out.println("  "+df.format(supNorm)+" " );
	
		count++;
		
		}while(supNorm>epsilon); 		
	
	}
	//solves the linear system of equations 
	public static double[] SystemSolve(double[][] A, double[] B)
	{
	
		double epsilon = 0.0008;
		double[] XofK= new double[3];
		double maxXofK, maxX, supNorm;
		double[] XofKMinus1 = {0,0,0};
		
		do
		{
			XofK[0] = jacobiMethod(A, XofKMinus1, B, 0);
			XofK[1] = jacobiMethod(A, XofKMinus1, B, 1);
			XofK[2] = jacobiMethod(A, XofKMinus1, B, 2);
		
			maxXofK = max(XofK);
			maxX= max(XofKMinus1);
			supNorm = Math.abs(maxXofK-maxX);
			
			XofKMinus1[0] = XofK[0]; 
			XofKMinus1[1] = XofK[1]; 	
			XofKMinus1[2] = XofK[2]; 	
			
		}while(supNorm>epsilon);
		return XofK;
	}
	public static double jacobiMethod(double[][] A, double[] X, double[] B, int i){
		double result = 0;
		for(int j = 0; j<A[i].length; j++)
		{
			if(j != i)
			result += -(A[i][j])*X[j];
		}
		result += B[i];
		result /= A[i][i];
		return result;
	}
	//creates our jacobi matrix 
	public static double[][] jacboiM(double[] X, double h){
		double[][] jacobi = new double[X.length][X.length];
		
		for(int i=0; i<X.length; i++)
			for(int j=0; j<X.length; j++)
			{
				jacobi[i][j] = (F(add(X,E_h(j,X.length,h)),i) - F(X,i))/h;
			}
	
		return jacobi;
		
	}
	//adding two vectors 
	public static double[] add(double[] A, double[] B)
	{
		double[] C = new double[A.length];
		for(int i = 0; i < C.length; i++)
		{
			C[i] = A[i] + B[i];
		}
		return C; 
	}
	//our E_K*h vector 
	public static double[] E_h(int K, int length, double h)
	{
		double[] E = new double[length];
		E[K] = h; 
		return E;
	}
	//This will hold our functions. we can solve it by inputing the vector and a number from 0 to 1 to indicate which function to use. 
	public static double F(double[] X, int sub) {
		double result = 0; 
	
		if(sub ==0)
		{
		result = 3*X[0]-Math.cos(X[1]*X[2])-0.5;
		}
		else if (sub ==1)
		{
		result = Math.pow(X[0], 2) -81*Math.pow(X[1]+0.1,2)+Math.sin(X[2])+1.06;
		}
		else if (sub == 2)
		{
		result = Math.pow(Math.E, -X[0]*X[1]) + 20*X[2] + (10*Math.PI -3)/3.0;
		}
		return result;
		
	}
	//Finding the max of each vector 
	public static double max(double[] a)
	{
		double max =a[0];
		for(int i = 1; i<a.length; i++)
		{
			if(a[i]>max)
			{
				max=a[i];
			}
		}
		return max;
	}
}

