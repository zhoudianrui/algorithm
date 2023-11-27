package io.frlib.algorithm.classic;

/**
 * 排序算法（从小到大排序）
 */
public class Sort {
    // 常规算法：选择、插入、冒泡 O(n*n)

    /**
     * 每次从[i+1,n]区间选出1个最小元素与nums[i]交换
     * @param nums
     */
    public static void select(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }
            if (minIndex != i) {
                swap(nums, i, minIndex);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 两两比较依次交换
     * @param nums
     */
    public static void bubble(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean swap = false; // 已经排好序提前终止
            for(int j = n - 1; j > i; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j-1);
                    swap = true;
                }
            }
            if (!swap) {
                System.out.println("第" + i + "次已经排完");
                break;
            }
        }
    }

    /**
     * 每次选择一个元素插入到已经排序的数组里面 nums[i]插入nums[0...i-1]中
     * @param nums
     */
    public static void insert(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            for (int j = 0; j < i; j++) { // 已排好序的
                if (temp < nums[j]) {
                    // [j,i]之间的元素后移一位，nums[j]替换为temp
                    move(nums, j, i);
                    nums[j] = temp;
                    break;
                }
            }
        }
    }

    private static void move(int[] nums, int low, int high) {
        while (high > low) {
            nums[high] = nums[high - 1];
            high--;
        }
    }

    // 高效排序：快速排序、归并排序、堆排序

    /**
     * 归并排序，分治法：[0, mid],[mid + 1, n]分别排序然后合并
     * @param nums
     */
    public static void merge(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        mergeSort(nums,0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int start, int end) {
        if (start < end) {
            int middle = start + (end - start) / 2;
            mergeSort(nums, start, middle);
            mergeSort(nums, middle + 1, end);
            merge(nums, start, middle, end);
        }
    }

    private static void merge(int[] nums, int left, int middle, int right) {
        // [left, middle] [middle + 1, right]合并
        int i = left, j = middle + 1;
        int writeIndex = 0;
        int length = right - left + 1;
        int[] temp = new int[length];
        while(i <= middle && j <= right) {
            int value = nums[i];
            if (nums[i] <= nums[j]) {
                i++;
            } else {
                value = nums[j];
                j++;
            }
            temp[writeIndex] = value;
            writeIndex++;
        }
        while (i <= middle) {
            temp[writeIndex] = nums[i];
            i++;
            writeIndex++;
        }
        while(j <= right) {
            temp[writeIndex] = nums[j];
            j++;
            writeIndex++;
        }
        System.arraycopy(temp, 0, nums, left, length);
    }

    /**
     * 选择i为哨兵，寻找比nums[i]小的元素放置在左边，大的元素放置在右边，依次对左右子数组做排序
     * @param nums
     */
    public static void quick(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot  - 1);
            quickSort(nums, pivot + 1, end);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        int value = nums[start];
        int left = start, right = end;
        while (left < right) {
            while(nums[left] <= value && left < end) {
                left ++;
            }
            while (nums[right] >= value && right > start) {
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
        swap(nums, right, start);
        return right;
    }

    public static void heap(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        // 根元素
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, i, n);
        }
        // 依次对剩余的元素建堆
        for (int i = n - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }
    }

    /**
     * 当前元素与孩子节点中较大的互换，然后依次对较大元素的后继元素做递归
     * @param nums
     * @param start
     * @param size
     */
    private static void heapify(int[] nums, int start, int size) {
        int left = 2 * start + 1, right = 2 * start + 2;
        int biggestIndex = start;
        if (left < size && nums[left] > nums[biggestIndex]) {
            biggestIndex = left;
        }
        if (right < size && nums[right] > nums[biggestIndex]) {
            biggestIndex = right;
        }
        if (biggestIndex != start) {
            swap(nums, biggestIndex, start);
            heapify(nums, biggestIndex, size);
        }
    }

    public static void main(String[] args) {
        int[] nums = {7,4,8,2,1,9,3,5,6,10};
//        Sort.select(nums);
//        Sort.bubble(nums);
//        Sort.insert(nums);
//        Sort.merge(nums);
//        Sort.quick(nums);
        Sort.heap(nums);
        for(int num: nums) {
            System.out.print(num + " ");
        }
    }
}
