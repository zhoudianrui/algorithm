package io.frlib.algorithm.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("sum")
public class ComputerSum {

    @RequestMapping("/{num}")
    public String twoSum(@PathVariable("num") int num, @RequestParam("single") boolean single, @RequestParam("nums") int[] nums,
                                @RequestParam("target") int target) {
        if (nums == null || nums.length == 0) {
            System.out.println("no pair");
            return "none";
        }

        if (num == 2) {
           if (single) {
               return hasOnePairInTwo(nums, target);
           } else {
               Arrays.sort(nums);
               List<List<Integer>> result = hasMultiInTwo(nums, 0, target);
               return CollectionUtils.isEmpty(result) ? "none" : "done";
           }
        } else if (num == 3) {
            List<List<Integer>> result = hasMultiInThree(nums, target);
            System.out.println("3 pairs result:" + result);
            return CollectionUtils.isEmpty(result) ? "none" : "done";
        } else {
            List<List<Integer>> result = hasMultiInN(nums, target, num ,0);
            System.out.println(num + " pairs result:" + result);
            return CollectionUtils.isEmpty(result) ? "none" : "done";
        }
    }

    /**
     * 仅有一对
     * @param nums
     * @param target
     * @return
     */
    private String hasOnePairInTwo(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int left = nums[low], right = nums[high];
            int sum = left + right;
            if (sum > target) {
                high--;
            } else if (sum < target) {
                low++;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(nums[low]);
                list.add(nums[high]);
                System.out.println("single pair:" + list);
                return "done";
            }
        }
        return "none";
    }

    /**
     * 多对
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> hasMultiInTwo(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 排序
        int low = start, high = nums.length - 1;
        while (low < high) {
            int left = nums[low], right = nums[high];
            int sum = left + right;
            if (sum > target) {
                while (low < high && nums[high] == right) {
                    high--;
                }
            } else if (sum < target) {
                while (low < high && nums[low] == left) {
                    low++;
                }
            } else {
                // 添加结果
                List<Integer> list = new ArrayList<>();
                list.add(left);
                list.add(right);
                result.add(list);
                // 找到第一个与low不同的元素
                while (low < high && nums[low] == left) {
                   low++;
                }
                // 找到第一个与high不同的元素
                while (high > low && nums[high] == right) {
                    high--;
                }
            }
        }
        return result;
    }


    private List<List<Integer>> hasMultiInThree(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> tuples = hasMultiInTwo(nums, i  + 1, target-nums[i]);
            for (List<Integer> tuple : tuples) {
                tuple.add(nums[i]);
                result.add(tuple);
            }
            // 跳过一直重复的元素，否则结果重复
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }

    // nSum
    private List<List<Integer>> hasMultiInN(int[]nums, int target, int n, int start) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int size = nums.length;
        if (n < 2 || size < n) return null;
        if (n == 2) {
            int low = start, high = size - 1;
            while (low < high) {
                int left = nums[low], right = nums[high];
                int sum = left + right;
                if (sum > target) {
                    while (low < high && nums[high] == right) {
                        high--;
                    }
                } else if (sum < target) {
                    while (low < high && nums[low] == left) {
                        low++;
                    }
                } else {
                    // 添加结果
                    List<Integer> list = new ArrayList<>();
                    list.add(left);
                    list.add(right);
                    result.add(list);
                    // 找到第一个与low不同的元素
                    while (low < high && nums[low] == left) {
                        low++;
                    }
                    // 找到第一个与high不同的元素
                    while (high > low && nums[high] == right) {
                        high--;
                    }
                }
            }
        } else {
            for (int i = start; i < size; i++) {
                List<List<Integer>> tuples = hasMultiInN(nums, target-nums[i], n-1, i + 1);
                for (List<Integer> tuple : tuples) {
                    tuple.add(nums[i]);
                    result.add(tuple);
                }
                while (i < size - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return result;
    }
}
