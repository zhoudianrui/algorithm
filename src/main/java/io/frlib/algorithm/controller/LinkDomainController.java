package io.frlib.algorithm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkDomainController {

    class ListNode {
        int val;
        ListNode(int val) {
            this.val = val;
        }
        ListNode next;

        @Override
        public String toString() {
            String s = this.val + "";
            ListNode p = next;
            while (p != null) {
                s += ", " + p.val;
                p = p.next;
            }
            return s;
        }
    }


    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private ListNode buildLinkList(int[] nums) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode p = head;
        for(int i : nums) {
            ListNode node = new ListNode(i);
            p.next = node;
            p = node;
        }
        return head.next;
    }

    @RequestMapping("/sort")
    public void sort(@RequestParam("nums") int[] nums) {
        ListNode head = buildLinkList(nums);
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode p = head;
        while (p != null) {
            ListNode temp = p.next;
            insertNode(dummy, p);
            p = temp;
        }
        System.out.println(dummy.next);
    }

    private void insertNode(ListNode head, ListNode target) {
        ListNode pre = head, p = head;
        while (p != null) {
            if (target.val >= p.val) {
                pre = p;
                p = p.next;
            } else {
                break;
            }
        }
        pre.next = target;
        target.next = p;
    }
}
