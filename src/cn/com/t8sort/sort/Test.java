package cn.com.t8sort.sort;

public class Test {
    
	public static void main(String[] args) {
		
	   Integer[] a={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
	       
		T8IntSort.dao.bubbleSort(a);
		T8IntSort.dao.quickSort(a);
		T8IntSort.dao.insertSort(a);
		T8IntSort.dao.selectSort(a);
		T8IntSort.dao.shellSort(a);
		T8IntSort.dao.heapSort(a);
	}
}
