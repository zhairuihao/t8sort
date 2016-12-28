package cn.com.t8sort.sort;

import java.util.Arrays;


/**
 * Title: T8IntSort
 * Description: ���������㷨
 * Company: 
 * @author zharh
 * @date 2016��10��15�� ����12:46:06
 */
public class T8IntSort {

	public static final T8IntSort dao = new T8IntSort();

	/**
	 * 1��ֱ�Ӳ������� ��1������˼�룺��Ҫ�����һ�����У�����ǰ��(n-1)[n>=2] �����Ѿ�����
	 * ��˳��ģ�����Ҫ�ѵ�n�����嵽ǰ����������У�ʹ����n���� Ҳ���ź�˳��ġ���˷���ѭ����ֱ��ȫ���ź�˳��
	 */
	public Integer[] insertSort(Integer[] a) {
		// int
		// a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		int temp = 0;
		for (int i = 1; i < a.length; i++) {
			int j = i - 1;
			temp = a[i];
			for (; j >= 0 && temp < a[j]; j--) {
				a[j + 1] = a[j];// ������temp��ֵ�������һ����λ
			}
			a[j + 1] = temp;
		}
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);

		return a;
	}

	/**
	 * 2��ϣ��������С�������� ��1������˼�룺�㷨�Ƚ�Ҫ�����һ������ĳ������d��n/2,nΪҪ�������ĸ������ֳ������飬
	 * ÿ���м�¼���±����d.��ÿ����ȫ��Ԫ�ؽ���ֱ�Ӳ�������Ȼ������һ����С��������d/2���������з��飬
	 * ��ÿ�����ٽ���ֱ�Ӳ������򡣵���������1ʱ������ֱ�Ӳ��������������ɡ�
	 * 
	 * @param a
	 * @return
	 */
	public Integer[] shellSort(Integer[] a) {
		// int a[]={1,54,6,3,78,34,12,45,56,100};
		double d1 = a.length;
		int temp = 0;
		while (true) {
			d1 = Math.ceil(d1 / 2);
			int d = (int) d1;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < a.length; i += d) {
					int j = i - d;
					temp = a[i];
					for (; j >= 0 && temp < a[j]; j -= d) {
						a[j + d] = a[j];
					}
					a[j + d] = temp;
				}
			}
			if (d == 1)
				break;
		}
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);

		return a;
	}

	/**
	 * 3.��ѡ������ ��1������˼�룺��Ҫ�����һ�����У�ѡ����С��һ�������һ��λ�õ���������
	 * Ȼ����ʣ�µ�������������С����ڶ���λ�õ������������ѭ���������ڶ����������һ�����Ƚ�Ϊֹ��
	 * 
	 * @param a
	 * @return
	 */
	public Integer[] selectSort(Integer[] a) {
		// int a[]={1,54,6,3,78,34,12,45};
		int position = 0;
		for (int i = 0; i < a.length; i++) {
			int j = i + 1;
			position = i;
			int temp = a[i];
			for (; j < a.length; j++) {
				if (a[j] < temp) {
					temp = a[j];
					position = j;
				}
			}
			a[position] = a[i];
			a[i] = temp;
		}
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
		return a;
	}

	/**
	 * 4�������� ��1������˼�룺��������һ������ѡ�������Ƕ�ֱ��ѡ���������Ч�Ľ��� �ѵĶ������£�����n��Ԫ�ص����У�h1,h2,...,hn),
	 * ���ҽ������㣨hi>=h2i,hi>=2i+1����hi<=h2i,hi<=2i+1��(i=1,2,...,n/2)ʱ��֮Ϊ�ѡ�
	 * ������ֻ��������ǰ�������Ķѡ��ɶѵĶ�����Կ������Ѷ�Ԫ�أ�����һ��Ԫ�أ���Ϊ�����󶥶ѣ���
	 * ��ȫ���������Ժ�ֱ�۵ر�ʾ�ѵĽṹ���Ѷ�Ϊ��������Ϊ����������������
	 * ��ʼʱ��Ҫ������������п�����һ��˳��洢�Ķ��������������ǵĴ洢��ʹ֮��Ϊһ���ѣ�
	 * ��ʱ�ѵĸ��ڵ�������Ȼ�󽫸��ڵ���ѵ����һ���ڵ㽻����Ȼ���ǰ��(n-1)�������µ���ʹ֮��Ϊ�ѡ�
	 * �������ƣ�ֱ��ֻ�������ڵ�Ķѣ��������������������õ���n���ڵ���������С�
	 * ���㷨������������������Ҫ�������̣�һ�ǽ����ѣ����ǶѶ���ѵ����һ��Ԫ�ؽ���λ�á�
	 * ���Զ�����������������ɡ�һ�ǽ��ѵ���͸���������Ƿ���������͸����ʵ������ĺ�����
	 * 
	 * @param a
	 * @return
	 */
	public Integer[] heapSort(Integer[] a) {
		System.out.println("��ʼ����");
		int arrayLength = a.length;
		// ѭ������
		for (int i = 0; i < arrayLength - 1; i++) {
			// ����
			buildMaxHeap(a, arrayLength - 1 - i);
			// �����Ѷ������һ��Ԫ��
			swap(a, 0, arrayLength - 1 - i);
			System.out.println(Arrays.toString(a));
		}
		return null;
	}

	/**
	 * ���Qλ��
	 * 
	 * @param a
	 * @param i
	 * @param j
	 */
	private void swap(Integer[] a, int i, int j) {
		// TODOAuto-generatedmethodstub
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * ��data�����0��lastIndex���󶥶�
	 * 
	 * @param a
	 * @param lastIndex
	 */
	private void buildMaxHeap(Integer[] a, int lastIndex) {
		// TODOAuto-generatedmethodstub
		// ��lastIndex���ڵ㣨���һ���ڵ㣩�ĸ��ڵ㿪ʼ
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// k���������жϵĽڵ�
			int k = i;
			// �����ǰk�ڵ���ӽڵ����
			while (k * 2 + 1 <= lastIndex) {
				// k�ڵ�����ӽڵ������
				int biggerIndex = 2 * k + 1;
				// ���biggerIndexС��lastIndex����biggerIndex+1�����k�ڵ�����ӽڵ����
				if (biggerIndex < lastIndex) {
					// �������ӽڵ��ֵ�ϴ�
					if (a[biggerIndex] < a[biggerIndex + 1]) {
						// biggerIndex���Ǽ�¼�ϴ��ӽڵ������
						biggerIndex++;
					}
				}
				// ���k�ڵ��ֵС����ϴ���ӽڵ��ֵ
				if (a[k] < a[biggerIndex]) {
					// ��������
					swap(a, k, biggerIndex);
					// ��biggerIndex����k����ʼwhileѭ������һ��ѭ�������±�֤k�ڵ��ֵ�����������ӽڵ��ֵ
					k = biggerIndex;
				} else {
					break;
				}
			}
		}
	}

	/**
	 * 5.ð������ ��1������˼�룺��Ҫ�����һ�����У��Ե�ǰ��δ�ź���ķ�Χ�ڵ�ȫ������
	 * ���϶��¶����ڵ����������ν��бȽϺ͵������ýϴ�������³�����С������ð�� ����ÿ�������ڵ����ȽϺ������ǵ�����������Ҫ���෴ʱ���ͽ����ǻ�����
	 * 
	 * @param a
	 * @return
	 */
	public Integer[] bubbleSort(Integer[] a) {
		// int
		// a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		int temp = 0;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
		return a;
	}

	/**
	 * 6.�������� ��1������˼�룺ѡ��һ����׼Ԫ��,ͨ��ѡ���һ��Ԫ�ػ������һ��Ԫ��,ͨ��һ��ɨ�裬
	 * ���������зֳ�������,һ���ֱȻ�׼Ԫ��С,һ���ִ��ڵ��ڻ�׼Ԫ��,��ʱ��׼Ԫ�������ź�������ȷλ��,Ȼ������ͬ���ķ����ݹ�����򻮷ֵ������֡�
	 * 
	 * @param a
	 * @return
	 */
	// int
	// a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
	public Integer[] quickSort(Integer[] a) {
		quick(a);
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
		return a;
	}

	private int getMiddle(Integer[] list, int low, int high) {
		int tmp = list[low];// ����ĵ�һ����Ϊ����
		while (low < high) {
			while (low < high && list[high] >= tmp) {
				high--;
			}
			list[low] = list[high];// ������С�ļ�¼�Ƶ��Ͷ�
			while (low < high && list[low] <= tmp) {
				low++;
			}
			list[high] = list[low];// �������ļ�¼�Ƶ��߶�
		}
		list[low] = tmp;// �����¼��β
		return low;// ���������λ��
	}

	private void _quickSort(Integer[] list, int low, int high) {
		if (low < high) {
			int middle = getMiddle(list, low, high);// ��list�������һ��Ϊ��
			_quickSort(list, low, middle - 1);// �Ե��ֱ���еݹ�����
			_quickSort(list, middle + 1, high);// �Ը��ֱ���еݹ�����
		}
	}

	private void quick(Integer[] a2) {
		if (a2.length > 0) {// �鿴�����Ƿ�Ϊ��
			_quickSort(a2, 0, a2.length - 1);
		}
	}
	
	

}
