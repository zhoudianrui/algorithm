package io.frlib.algorithm.base;

import java.util.NoSuchElementException;

public class LinkList<E> {

    class Node<E> {
        E value; // 节点元素
        Node next; // 下一个节点
        Node(E e, Node next) {
            this.value = e;
            this.next = next;
        }
    }

    // 头结点
    private Node<E> head;
    private int size; // 节点数

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // 增(头插)
    public void add(E e) {
        Node node = new Node(e, null);
        if (head != null) {
            node.next = head;
        }
        head = node;
        size++;
    }

    // a -> b -> c -> null
    private Node addLast(Node node, E e) {
        if (node == null) {
            return new Node(e, null);
        }

        node.next = addLast(node.next, e);
        return node;
    }

    // 删(尾删除)
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("");
        }

        head = remove(head, index);
        size--;
    }

    // a -> b -> c -> null
    private Node<E> remove(Node<E> node, int index) {
        if (index == 0) {
            return node.next;
        }
        node.next = remove(node.next, index - 1);
        return node;
    }

    // 修改
    public void update(int index, E e) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("");
        }
        Node node = get(head, index);
        node.value = e;
    }

    // 查
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("");
        }
        return get(head, index).value;
    }

    private Node<E> get(Node node, int index) {
        if (index == 0) {
            return node;
        }
        Node result =  get(node.next, index-1);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node p = head;
        while (p != null) {
           sb.append(p.value.toString() + " ");
           p = p.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkList<Integer> linkList = new LinkList();
        int[] nums = {1,10,2,9,3,8,4,7,5,6};
        for(int num: nums) {
            linkList.add(num);
        }
        System.out.println(linkList);
        linkList.remove(2);
        System.out.println(linkList);
        int value = linkList.get(2);
        System.out.println(value);
        linkList.update(2, value * value);
        System.out.println(linkList);
    }
}
