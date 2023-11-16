package io.frlib.algorithm.classic;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 查找目标元素
 */
public class FindTarget {

    private int[] nums; // 数组

    public int[] getNums() {
        return nums;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }


    /**
     * 移动k个元素的有序数组查找目标元素
     *
     * @param target
     * @return
     */
    public int searchTargetInPartSort(int target) {
        if (nums == null || nums.length == 0) return -1;
        // 排序
        Arrays.sort(nums);
        // 移动k个元素
        shuffleInOrder(3);
        print();
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (target == nums[middle]) {
                return middle;
            }
            // 右边有序
            if (nums[middle] < nums[right]) {
                if (target >= nums[middle] && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }

            // 左边有序
            if (nums[middle] > nums[left]) {
                if (target < nums[middle] && target >= nums[left]) {
                    right = middle - 1;
                } else {
                    left = middle;
                }
            }
        }
        return -1;
    }

    private void shuffleInOrder(int k) {
        if (k >= nums.length) return;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = nums.length - k; i < nums.length; i++) {
            list.addLast(nums[i]);
        }
        for (int i = nums.length - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }
        for (int i = k - 1; i >= 0; i--) {
            nums[i] = list.removeLast();
        }
    }

    private void print() {
        // 数组打印
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FindTarget instance = new FindTarget();
        int[] nums = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        instance.setNums(nums);
        int resultIndex = instance.searchTargetInPartSort(5);
        System.out.println("target index is " + resultIndex);
    }
}
