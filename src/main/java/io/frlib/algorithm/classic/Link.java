package io.frlib.algorithm.classic;

import io.frlib.algorithm.base.LinkList;
import io.frlib.algorithm.base.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表相关题目
 */
public class Link<E> {

    /*
     * 链表合并(leetcode 21)
     */
    public Node<E> combine(Node<E> a, Node<E> b) {
        Node<E> dummy = new Node<>(null, null);
        Node<E> p = a;
        Node<E> q = b;
        Node<E> node = dummy;
        while(p != null & q != null) {
            if (p.compareTo(q.getValue()) > 0) {
                node.setNext(q);
                q = q.getNext();
            } else {
                node.setNext(p);
                p = p.getNext();
            }
            node = node.getNext();
        }
        if (p != null) {
            node.setNext(p);
        }
        if (q!= null) {
            node.setNext(q);
        }
        return dummy.getNext();
    }

    private void print(Node<E> head) {
        while(head != null) {
            System.out.print(head.getValue() + " ");
            head = head.getNext();
        }
    }

    /**
     * 单链表逆转
     * @param head
     * @return
     */
    public Node<E> reverseRecursive(Node<E> head) {
        if (head == null || head.getNext() == null) return head;
        Node<E> newHead = reverseRecursive(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    public Node<E> reverseIterator(Node<E> head) {
        Node<E> pre = null, p = head;
        while(p != null) {
            Node<E> next = p.getNext();
            p.setNext(pre);
            pre = p;
            p = next;
        }
        return pre;
    }

    /**
     * 判断链表是否有环(leetcode 141)
     * 通过快慢指针判定，如果存在环形，快指针会与慢指针相遇
     * @param head
     * @return
     */
    public boolean hasCycle(Node<E> head) {
        Node<E> fast = head, slow = head;
        while(fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) return true;
        }
        return false;
    }

    /**
     * 判断链表的环形起点(leetcode 142)
     * 通过快慢指针移动，当指针相遇时，假设相遇在m点，起点在k点，则m为环的倍数。此时如果慢指针从头出发&快指针同步前进，走k步即为起点
     * @param head
     * @return
     */
    public Node<E> detectCycle(Node<E> head) {
        Node<E> fast = head, slow = head;
        while(fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (slow == fast) break;
        }
        if (fast == null || fast.getNext() == null) {
            return null;
        }
        slow = head;
        while(slow != head) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return slow;
    }

    /**
     * 判断链表是否相交(leetcode 160)
     * a -> b -> c -> d -> e -> f
     * x -> y -> e-> f
     * 因为链表可能不一样长，所以不能直接前行比较。
     * ①：长链表前行n（maxLength - minLength）,直到终点前是否相遇
     * ②：两条链表链接后等长判断
     * @param headA
     * @param headB
     * @return
     */
    public Node<E> getIntersectionNode(Node<E> headA, Node<E> headB) {
        // 长链表前行判定法
        //return getIntersectionNodeWithPreForward(headA, headB);
        return getIntersectionNodeWithCombination(headA, headB);
    }

    /**
     * 长链表前行法
     * @param headA
     * @param headB
     * @return
     */
    private Node<E> getIntersectionNodeWithPreForward(Node<E> headA, Node<E> headB) {
        Node<E> p = headA, q = headB;
        int lengthA = 0, lengthB = 0;
        while(p != null) {
            p = p.getNext();
            lengthA ++;
        }
        while(q != null) {
            q = q.getNext();
            lengthB++;
        }
        p = headA;
        q = headB;
        if (lengthA < lengthB) {
            // B先前行
            int gap = lengthB - lengthA;
            while (gap > 0) {
                q = q.getNext();
                gap --;
            }
        } else {
            int gap = lengthA - lengthB;
            while(gap > 0) {
                p = p.getNext();
                gap --;
            }
        }
        while(p != q && p != null) {
            p = p.getNext();
            q = q.getNext();
        }
        return p;
    }

    private Node<E> getIntersectionNodeWithCombination(Node<E> headA, Node<E> headB) {
        Node<E> p = headA, q = headB;
        while(p != q) {
            if (p == null) {
                p = headB;
            } else {
                p = p.getNext();
            }
            if (q == null) {
                q = headA;
            } else {
                q = q.getNext();
            }
        }
        return p;
    }

    public static void main(String[] args) {
        LinkList<Integer> list1 = new LinkList<>();
        List<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            nums.add(i);
        }
        LinkList<Integer> list2 = new LinkList<>();
        for(int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                list1.addLast(i);
            } else {
                list2.addLast(i);
            }
        }
        Link link = new Link();
        /*link.print(list1.getHead());
        System.out.println();*/
        /*link.print(list2.getHead());
        System.out.println();
        Node<Integer> result = link.combine(list1.getHead(), list2.getHead());
        link.print(result);
        System.out.println();*/
        /*Node<Integer> newHead = link.reverseRecursive(list1.getHead());
        link.print(newHead);*/
        /*Node<Integer> head = link.reverseIterator(list1.getHead());
        link.print(head);*/
        System.out.println(link.hasCycle(list1.getHead()));
    }
}
