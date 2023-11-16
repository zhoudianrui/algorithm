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
        link.print(list1.getHead());
        System.out.println();
        /*link.print(list2.getHead());
        System.out.println();
        Node<Integer> result = link.combine(list1.getHead(), list2.getHead());
        link.print(result);
        System.out.println();*/
        /*Node<Integer> newHead = link.reverseRecursive(list1.getHead());
        link.print(newHead);*/
        Node<Integer> head = link.reverseIterator(list1.getHead());
        link.print(head);
    }
}
