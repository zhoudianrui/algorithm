package io.frlib.algorithm.base;

/**
 * 链表节点元素
 * @param <E>
 */
public class Node<E> implements Comparable<Node<E>> {
    E value; // 节点元素
    Node next; // 下一个节点

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(E e, Node next) {
        this.value = e;
        this.next = next;
    }

    public int compareTo(Node<E> o) {
        return ((Comparable)value).compareTo(o.getValue());
    }

}
