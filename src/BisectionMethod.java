import java.util.Scanner;

public class BisectionMethod {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int counter = 0;
		double resolution = 0.01, threshold = 0.001, root;
		double left, right, subLeft, subRight;
		double[] f, fPrime, roots;
		
		f = getPoly();
		fPrime = new double[f.length - 1];
		roots = new double[f.length-1];
		
		System.out.print("Left end point: ");
		left = sc.nextDouble();
		System.out.print("Right end point: ");
		right = sc.nextDouble();
		
		fPrime = diff(f);
		
		// Setup for finding roots
		subLeft = left;
		subRight = subLeft + resolution;
		
		do {
			// Look for sign change
			if(findValue(f, subLeft) * findValue(f, subRight) < 0) {
				root = bisection(f, subLeft, subRight);
				roots[counter] = root;
				counter++;
			}
			// Look for a sign change in the input function's derivative
			else if(findValue(fPrime, subLeft) * findValue(fPrime, subRight) < 0) {
				root = bisection(fPrime, subLeft, subRight);
				// If a root is found in the 
				if(Math.abs(findValue(f, root)) < threshold) {
					roots[counter] = root;
					counter++;
				}
			}
			subLeft += resolution;
			subRight += resolution;
		} while((right-subRight) > 0.01);
		
		if(counter > 0)
			printArray(roots);
		else
			System.out.println("Using the information you have entered, no roots were found");
	}
	
	static double[] getPoly() {
		Scanner sc = new Scanner(System.in);
		int degree;
		double f[];
		
		System.out.println("Please enter the degree of the polynomial");
		System.out.print("Degree: ");
		degree=sc.nextInt();
		f = new double[degree+1];
		
		System.out.print("Enter " + (degree+1) + " numbers corresponding to the coefficients of the polynomial: ");
		for(int i = 0; i < degree+1; i++) {
			f[i] = sc.nextDouble();
		}
		return f;
	}
	
	static double[] diff(double f[]) {
		double[] fPrime = new double[f.length-1];	
		for(int i = 0; i<fPrime.length; i++)
			fPrime[i] = f[i+1]*(i+1);
		return fPrime;
	}
	
	static double findValue(double f[], double x) {
		double total = 0;
		for(int i = 0; i < f.length; i++)
			total += f[i]*Math.pow(x, i);
		return total;
	}
	
	static double bisection(double poly[], double subLeft, double subRight) {
		double tolerance = 0.0000001, mid;
		do {
			mid = (subLeft + subRight)/2;
			if(findValue(poly, subLeft) * findValue(poly, mid) < 0) 
				subRight = mid;
			else 
				subLeft = mid;
		} while(Math.abs(findValue(poly, mid)) > tolerance);
		return mid;
	}
	
	static void printArray(double poly[]) {
		System.out.println("\nRoots: ");
		for(int i = 0; i<poly.length; i++) {
			System.out.print("Root " + (i+1) + ": ");
			System.out.printf("%.5f\n", poly[i]);
		}
	}
}