import java.util.Random;

public class Quicksort {
	int[] a;
	int medOfMed;
	int count;
	
	
	public Quicksort(){
		a = new int[0];
		medOfMed = 0;
		count = 0;
	}
	
	public void reset(){
		int[] original = new int[0];
		this.a = original;
		medOfMed = 0;
		count = 0;
	}
	
	public int getPartCount(){
		return count;
	}
	
	
	public Quicksort(int[] a){
		this.a = a;
		medOfMed = 0;
		count = 0;
	}
	
	public int[] populate(int num){
		int[] result = new int[num];
		for(int i = 0; i < num; i++)
			result[i] = (int)(Math.random() * 999999999);
		return result;
	}
	
	public Quicksort random() {
		int num = a.length;
		Random rand = new Random();
		for (int i = 0; i < num; i++) 
				a[i] = rand.nextInt(10);

		Quicksort result = new Quicksort(a);
		return result;
	}
	
	public void swap(int[] arr, int i, int j){
		int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
	}
	
	//--------------------------quick sort with last element as pivot--------------------
	
	public int partition(int[]arr, int left, int right){
		int pivot = arr[right];
		int i = left - 1;
		
		for(int j = left; j <= right - 1; j++){
			count++;
			if(arr[j]<= pivot){
				i++;
				swap(arr, i, j);
			}
		}		
		swap(arr, i + 1, right);
		return i + 1;
	}
	
	public void qs1(int[] a, int left, int right){
		
		if(left < right){
			int pivot = partition(a, left, right);
			qs1(a, left, pivot - 1);
			qs1(a, pivot + 1, right);
		}
	}
	
	//----------------------quicksort with random pivot-----------------------
	
	public int randmizedPartition(int[] a, int start, int end){
		Random rand = new Random();
		int ramPiv = start + rand.nextInt(end - start + 1);
		swap(a, ramPiv, end);
		return partition(a, start, end);
		}
	
	public void qs2(int[]a, int left, int right){
		if(left < right){
			int pivot = randmizedPartition(a, left, right);
			qs1(a, left, pivot - 1);
			qs1(a, pivot + 1, right);
		}
	}
	
	public int randmizedSelect(int[]a, int start, int end, int ith){
		if(start == end)
			return a[start];
		int pivot = randmizedPartition(a, start, end);
		int k = pivot - start + 1;
		
		if(ith == k)
			return a[pivot];
		else if(ith < k)
			return randmizedSelect(a, start, pivot - 1, ith);
		else
			return randmizedSelect(a, pivot + 1, end, ith - k);
	}
	
	//------------------------quicksort with median of median---------------------
	
	public void insertionSort(int[] a, int start, int end){
		int i, j, temp;
		for(i = start + 1; i < end; i++){
			temp = a[i];
			for(j = i - 1; (j >= start && a[j] > temp); j--){
				count++;
				a[j + 1] = a[j];
			}
			a[j + 1] = temp;
		}
	}
	
	public int findMedian(int[]a, int start, int end){
		insertionSort(a, start, end);
		int elem = end - start;
		return a[start + (elem / 2)];
	}
	
	public int select(int[] a, int start, int last, int index){
		
			int n = last - start + 1;
			int i;
			int[] median = new int[(n + 4) / 5];
			
			for(i = 0; i < n / 5; i++)
				median[i] = findMedian(a, start + i * 5, (start + i * 5) + 5);
			if(i * 5 < n)
				median[i] = findMedian(a, start + i * 5, (start + i * 5) + (n % 5));


			if(i == 0)
				medOfMed = median[i];
			else if(i < 2)
				medOfMed = median[i - 1];
			else if(i <= 5)
				medOfMed = findMedian(median, 0, median.length - 1);
			else
				select(median, 0, i - 1, i / 2);

		return last;
	}
	
	
	public void moveToLast(int[]a, int pivot){
		boolean found = false;
		int k = 0;
		
		while(!found){
			if(a[k] == medOfMed)
				found = true;
			k++;
			count++;
		}
		k--;
		swap(a, k, pivot);
	}
	
	public void qs3(int[] a, int left, int right){
		if(left < right){
			int temp = select(a, left, right, (right - left) / 2);
			moveToLast(a, temp);
			int pivot = partition(a, left, right);
			qs3(a, left, pivot - 1);
			qs3(a, pivot + 1, right);
		}
	}
}


