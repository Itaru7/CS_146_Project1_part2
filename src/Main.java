
public class Main {
	public static void main(String[] args){
		int[] arr = new int[40];
		Quicksort a1 = new Quicksort(arr);
		a1.random();
		
		System.out.println("Before sorting.");
		System.out.print(arr[0]);

		for(int i = 1; i < arr.length; i++)
			System.out.print(", " + arr[i]);
		System.out.println();
		
		System.out.println("After sorting.");
		a1.qs2(arr, 0, (arr.length - 1));
		System.out.print(arr[0]);
		for(int i = 1; i < arr.length; i++)
			System.out.print(", " + arr[i]);
		System.out.println("");
	}

}

