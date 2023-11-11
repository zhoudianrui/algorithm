package io.frlib.algorithm.base;

import java.util.*;

/**
 * 普通二叉树
 */
public class BinaryTree<E> {

    class TreeNode<E> {
        E value;
        TreeNode<E> left;
        TreeNode<E> right;
        TreeNode(E value, TreeNode<E> left, TreeNode<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    TreeNode<E> root; // 根节点

    public TreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<E> root) {
        this.root = root;
    }


    TreeNode<E> buildTree(List<E> elements) {
        if (elements == null || elements.size() == 0) {
            throw new RuntimeException("元素不足");
        }
        E element = elements.get(0);
        root = new TreeNode(element, null, null);
        buildTree(root, elements, 0);
        return root;
    }

    /**
     * 基于第index个节点构建二叉树
     * @param node
     * @param elements
     * @param index
     */
    private void buildTree(TreeNode<E> node, List<E> elements, int index) {
        if (node == null || index >= elements.size()) {
            return;
        }
        if (2 * index + 1 >= elements.size()) { //左子树越界
            return;
        }
        E leftValue = elements.get(2 * index + 1);
        if (leftValue != null) {
            node.left = new TreeNode<>(leftValue, null, null);
        }
        if (2 * (index + 1) >= elements.size()) { // 右子树越界
            return;
        }
        E rightValue = elements.get(2 * (index + 1));
        if (rightValue != null) {
            node.right = new TreeNode<>(rightValue, null, null);
        }
        buildTree(node.left, elements, 2 * index + 1);
        buildTree(node.right, elements, 2 * (index + 1));
    }

    public void traverse(TreeNode<E> node) {
        TreeMap<Integer, List<E>> map = new TreeMap<>();
        traverse(node, 0, map);
        for(Integer level: map.keySet()) {
            List<E> list = map.get(level);
            for (E e: list) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    private void traverse(TreeNode<E> node, int level, TreeMap<Integer, List<E>> map) {
        if (node == null) return;
        // 前序位置
        //System.out.print(node);
        List<E> list = map.get(level);
        if (list == null) {
            list = new ArrayList<>();
            map.put(level, list);
        }
        list.add(node.value);
        traverse(node.left, level + 1, map);
        traverse(node.right, level + 1, map);
    }

    public static void main(String[] args) {
        List<Integer> elements = new ArrayList<>();
        for(int i = 10; i > 0; i--) {
            elements.add(i);
        }
        elements.set(5, null);
        elements.set(7, null);
        BinaryTree<Integer> tree = new BinaryTree<>();
        BinaryTree<Integer>.TreeNode<Integer> root = tree.buildTree(elements);
        tree.traverse(root);
    }
}
