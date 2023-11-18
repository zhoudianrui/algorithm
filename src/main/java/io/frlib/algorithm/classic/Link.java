package io.frlib.algorithm.classic;

import io.frlib.algorithm.base.LinkList;
import io.frlib.algorithm.base.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
            if (p.compareTo(q) > 0) {
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
        System.out.println();
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

    /**
     * 删除倒数第N个节点（leetcode 19）
     * @param head
     * @return
     */
    public Node<E> removeNthFromEnd(Node<E> head, int n) {
        if (n <= 0) return head;
        Node<E> dummy = new Node<E>(null, null);
        dummy.setNext(head);
        Node<E> fast = dummy, slow = dummy;
        int i = 0;
        // 前进N步
        while(i < n) {
            fast = fast.getNext();
            if (fast == null) break;
            i++;
        }
        if (fast == null) return head; // 元素不足
        while(fast.getNext() != null) {
            fast = fast.getNext();
            slow = slow.getNext();
        }
        slow.setNext(slow.getNext().getNext());
        return dummy.getNext();
    }

    /**
     * 合并K个有序链表
     * @param lists
     * @return
     */
    public Node<E> mergeKLists(Node<E>[] lists) {
        int arrayCount = lists.length;
        if (arrayCount == 0) return null;
        Node<E> dummy = new Node<E>(null, null);
        Node<E> p = dummy;
        PriorityQueue<Node<E>> queue = new PriorityQueue<>(arrayCount, (a, b) -> a.compareTo(b));
        for(Node<E> head: lists) {
            if (head != null) {
                queue.add(head);
            }
        }
        while(!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (node.getNext() != null) {
                queue.add(node.getNext());
            }
            p.setNext(node);
            p = p.getNext();
        }
        return dummy.getNext();
    }

    /**
     * 分割列表(leetcode 86)
     * @param head
     * @param x
     * @return
     */
    public Node<Integer> partition(Node<Integer> head, int x) {
        Node<Integer> smallDummy = new Node(null, null);
        Node<Integer> bigDummy = new Node(null, null);
        Node<Integer> small = smallDummy, big = bigDummy, p = head;
        while(p != null) {
            if (p.getValue().intValue() >= x) {
                big.setNext(p);
                big = big.getNext();
            } else {
                small.setNext(p);
                small = small.getNext();
            }
            // 断开列表
            Node<Integer> next = p.getNext();
            p.setNext(null);
            p = next;
        }
        small.setNext(bigDummy.getNext());
        return smallDummy.getNext();
    }

    /**
     * 链表的中间节点(leetcode 876)
     * @param head
     * @return
     */
    public Node<E> middleNode(Node<E> head) {
        Node<E> fast = head, slow = head;
        while(fast != null) {
            fast = fast.getNext();
            if (fast == null) break;
            fast = fast.getNext();
            slow = slow.getNext();
        }
        return slow;
    }

    /**
     * k个一组翻转链表(leetcode 25 ★★★)
     * @param head
     * @param k
     * @return
     */
    public Node<E> reverseKGroup(Node<E> head, int k) {
        if (head == null) {
            return null;
        }
        Node<E> p = head;
        // 循环找一组节点
        int i = 0;
        while (i < k && p != null) {
            p = p.getNext();
            i++;
        }
        if (i < k && p == null) return head;
        // 翻转该组节点
        Node<E> newHead = reverse(head, p);
        // 递归翻转剩余节点
        Node<E> node = reverseKGroup(p, k);
        head.setNext(node);
        return newHead;
    }

    private Node<E> reverse(Node<E> from, Node<E> end) {
        if (from.getNext() == end) return from;
        Node<E> newHead = reverse(from.getNext(), end);
        from.getNext().setNext(from);
        from.setNext(null);
        return newHead;
    }

    public static void main(String[] args) {
        /*LinkList<Integer> list1 = new LinkList<>();
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
        }*/
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
        /*System.out.println(link.hasCycle(list1.getHead()));*/
        /*Node<Integer> newHead = link.removeNthFromEnd(list1.getHead(), -1);
        link.print(newHead);*/
        /*Node<Integer> newHead = link.mergeKLists(new Node[]{list1.getHead(), list2.getHead(), new Node<Integer>(0, null), null});
        link.print(newHead);*/
        /*LinkList<Integer> list = new LinkList<>();
        list.addLast(1);list.addLast(4);list.addLast(3);list.addLast(2);list.addLast(5);list.addLast(2);
        link.print(list.getHead());
        Node<Integer> newHead = link.partition(list.getHead(), 3);
        link.print(newHead);*/
        /*LinkList<Integer> list = new LinkList<>();
        list.addLast(1);list.addLast(2);list.addLast(3);list.addLast(4);list.addLast(5);
        link.print(list.getHead());
        Node<Integer> node = link.middleNode(list.getHead());
        link.print(node);*/
        LinkList<Integer> list = new LinkList<>();
        list.addLast(1);list.addLast(2);list.addLast(3);
        link.print(list.getHead());
        Node<Integer> result = link.reverseKGroup(list.getHead(), 2);
        link.print(result);
    }
}
