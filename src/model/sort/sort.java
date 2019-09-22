package model.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 排序算法(10种)
 * 冒泡排序
 * 选择排序
 * 插入排序
 * 希尔排序
 * 快速排序
 * 归并排序
 * 堆排序
 * 计数排序
 * 桶排序
 * 基数排序
 */
public class sort {

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 算法：冒泡排序
     * 流程：两两相邻比较
     * 最快：O(n) 已排序
     * 最慢：O(n^2) 反序
     * 平均：O(n^2)
     * 稳定性：稳定
     */
    private static int[] bubbleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            boolean flag = false;
            for (int j = 1; j < array.length - i + 1; j++) {
                if (array[j - 1] > array[j]) {
                    flag = true;
                    swap(array, j - 1, j);
                }
            }
            if (!flag) {
                break;
            }
        }
        return array;
    }

    /**
     * 算法：选择排序
     * 流程：每轮选取最小值
     * 最快：O(n^2)
     * 最慢：O(n^2)
     * 平均：O(n^2)
     * 稳定性：不稳定
     */
    private static int[] selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, minIndex, i);
        }
        return array;
    }

    /**
     * 算法：插入排序
     * 流程：将当前值插入前面已排序的序列中
     * 最快：O(n)   已排序
     * 最慢：O(n^2)   反序
     * 平均：O(n^2)
     * 稳定性：稳定
     */
    private static int[] insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int cur = array[i];
            int j = i - 1;
            while (j >= 0 && cur < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = cur;
        }
        return array;
    }


    /**
     * 算法：希尔排序
     * 流程：希尔序列按组进行插入排序，直到跨度到1
     * 最快：与希尔序列有关
     * 最慢：与希尔序列有关
     * 平均：O(nlog(n))
     * 稳定性：不稳定
     */
    private static int[] shellSort(int[] array) {
        for (int h = array.length / 2; h >= 1; h /= 2) {
            for (int i = h; i < array.length; i++) {
                //同插入排序
                int cur = array[i];
                int j = i - h;
                while (j >= 0 && array[j] > cur) {
                    array[j + h] = array[j];
                    j -= h;
                }
                array[j + h] = cur;
            }
        }
        return array;
    }

    /**
     * 算法：快速排序
     * 流程：选取一个数作为基准数，大的放右边，小的放左边，递归
     * 最快：O(nlogn)
     * 最慢：O(n^2) 带排序列为正序或逆序
     * 平均：O(nlogn)
     * 空间复杂度：O(log(n)) 递归树深度为log2n
     * 稳定性：不稳定
     */
    private static int[] quickSort(int[] array) {
        qSort(array, 0, array.length - 1);
        return array;
    }

    private static void qSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = partition(array, left, right);
            qSort(array, left, mid - 1);
            qSort(array, mid + 1, right);
        }
    }

    private static int partition(int[] array, int left, int right) {
        int temp = array[left];
        while (left < right) {
            while (left < right && array[right] >= temp) {
                right--;
            }
            array[left] = array[right];
            while (left < right && array[left] <= temp) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = temp;
        return left;
    }

    /**
     * 算法：归并排序
     * 流程：拆成小数组，排序后合并
     * 最快：O(nlogn)
     * 最慢：O(nlogn)
     * 平均：O(nlogn)
     * 空间复杂度：O(n) 辅助数组
     * 稳定性：不稳定
     */
    private static int[] mergeSort(int[] array) {
        mSort(array, 0, array.length - 1);
        return array;
    }

    private static void mSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mSort(array, left, mid);
        mSort(array, mid + 1, right);
        merge(array, mid, left, right);
    }

    private static void merge(int[] array, int mid, int left, int right) {
        int[] a = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (array[i] < array[j]) {
                a[k++] = array[i++];
            } else {
                a[k++] = array[j++];
            }
        }
        while (i <= mid) a[k++] = array[i++];
        while (j <= right) a[k++] = array[j++];
        // 把临时数组复制到原数组 不能直接array=a;
        for (i = 0; i < k; i++) {
            array[left++] = a[i];
        }
    }
    /**
     * 算法：堆排序
     * 流程：构造大顶堆 堆顶移动到数组尾部
     * 最快：O(nlogn)
     * 最慢：O(nlogn)
     * 平均：O(nlogn)
     * 稳定性：不稳定
     */
    private static int[] heapSort(int[] array) {
        //构建大顶堆
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length - 1);
        }
        //进行堆排序
        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i);
            downAdjust(array, 0, i - 1);
        }
        return array;
    }

    private static void downAdjust(int[] array, int parent, int n) {
        //临时保存要下沉的元素
        int temp = array[parent];
        //定位左孩子节点的位置
        int child = 2 * parent + 1;
        //开始下沉
        while (child <= n) {
            // 如果右孩子节点比左孩子大，则定位到右孩子
            if (child + 1 <= n && array[child] < array[child + 1])
                child++;
            // 如果孩子节点小于或等于父节点，则下沉结束
            if (array[child] <= temp) break;
            // 父节点进行下沉
            array[parent] = array[child];
            parent = child;
            child = 2 * parent + 1;
        }
        array[parent] = temp;
    }

    /**
     * 算法：计数排序
     * 流程：与最大值最小值差相同长度的辅助数组
     * 最快：O(n+k)  k为辅助数组的长度
     * 最慢：O(n+k)
     * 平均：O(n+k)
     * 空间复杂度：O(k)
     * 稳定性：稳定
     */
    private static int[] countSort(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int[] a = new int[max - min + 1];
        for (int i : array) {
            a[i - min]++;
        }
        for (int i = 0, j = 0; i < array.length; i++) {
            while (a[j] == 0) {
                j++;
            }
            array[i] = j + min;
            a[j]--;
        }
        return array;
    }

    /**
     * 算法：桶排序
     * 流程：分块放进桶，桶内排序，最后综合
     * 时间复杂度：O(n+k)
     * 空间复杂度：O(n+k)
     * 稳定性：稳定
     */
    private static int[] bucketSort(int[] array) {
        int max = array[0];
        int min = array[0];
        // 寻找数组的最大值与最小值
        for (int i = 1; i < array.length; i++) {
            if (min > array[i])
                min = array[i];
            if (max < array[i])
                max = array[i];
        }
        int d = max - min;
        int bucketNum = d / 5 + 1;
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(bucketNum);
        //初始化桶
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }
        //遍历原数组，将每个元素放入桶中
        for (int i : array) {
            bucketList.get((i - min) / d).add(i);
        }
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
        }
        //把每个桶排序好的数据进行合并汇总放回原数组
        int k = 0;
        for (int i = 0; i < bucketNum; i++) {
            for (Integer t : bucketList.get(i)) {
                array[k] = t;
                k++;
            }
        }
        return array;
    }

    /**
     * 算法：基数排序
     * 流程：按位比较放进桶中
     * 时间复杂度：O(n+k)
     * 空间复杂度：O(n+k)
     * 稳定性：稳定
     */
    private static int[] radioSort(int[] array) {
        int max = array[0];
        // 找出最大值
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        // 计算最大值是几位数
        int num = 1;
        while (max / 10 > 0) {
            num++;
            max = max / 10;
        }
        // 创建10个桶
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(10);
        //初始化桶
        for (int i = 0; i < 10; i++) {
            bucketList.add(new LinkedList<>());
        }
        // 进行每一趟的排序，从个位数开始排
        for (int i = 1; i <= num; i++) {
            for (int value : array) {
                // 获取每个数最后第 i 位是数组
                int radio = (value / (int) Math.pow(10, i - 1)) % 10;
                //放进对应的桶里
                bucketList.get(radio).add(value);
            }
            //合并放回原数组
            int k = 0;
            for (int j = 0; j < 10; j++) {
                for (Integer t : bucketList.get(j)) {
                    array[k] = t;
                    k++;
                }
                //取出来合并了之后把桶清光数据
                bucketList.get(j).clear();
            }
        }
        return array;
    }

    private static void display(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(bubbleSort(array1));
        int[] array2 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(selectSort(array2));
        int[] array3 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(insertSort(array3));
        int[] array4 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(shellSort(array4));
        int[] array5 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(countSort(array5));
        int[] array6 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(bucketSort(array6));
        int[] array7 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(radioSort(array7));
        int[] array8 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(quickSort(array8));
        int[] array9 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(mergeSort(array9));
        int[] array10 = new int[]{2, 5, 3, 1, 2, 4, 6, 3, 3, 99, 18, 35, 67, 22, 59};
        display(heapSort(array10));
    }
}