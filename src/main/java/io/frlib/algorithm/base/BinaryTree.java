package io.frlib.algorithm.base;

import java.util.*;

/**
 * 普通二叉树
 */
public class BinaryTree<E> {

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

    public void levelTraverse(TreeNode<E> node) {
        TreeMap<Integer, List<E>> map = new TreeMap<>();
        levelTraverse(node, 0, map);
        for(Integer level: map.keySet()) {
            List<E> list = map.get(level);
            for (E e: list) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    private void levelTraverse(TreeNode<E> node, int level, TreeMap<Integer, List<E>> map) {
        if (node == null) {
            return;
        }
        // 前序位置
        //System.out.print(node);
        List<E> list = map.get(level);
        if (list == null) {
            list = new ArrayList<>();
            map.put(level, list);
        }
        list.add(node.value);
        levelTraverse(node.left, level + 1, map);
        levelTraverse(node.right, level + 1, map);
    }

    /**
     * 根 -> 左 -> 右
     * @param node
     */
    public void preOrderWithRecurise(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrderWithRecurise(node.left);
        preOrderWithRecurise(node.right);
    }

    public void preOrderWithIterator(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            TreeNode<E> p = stack.pop();
            System.out.print(p.value + " ");
            if (p.right != null) {
                stack.push(p.right);
            }
            if (p.left != null) {
                stack.push(p.left);
            }
        }
    }

    /**
     * 中序遍历：左 -> 根 -> 右
     * @param node
     */
    public void inOrderWithRecurise(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderWithRecurise(node.left);
        System.out.print(node.value + " ");
        inOrderWithRecurise(node.right);
    }

    public void inOrderWithIterator(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode p = node;
        while(!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                System.out.print(p.value + " ");
                p = p.right;
            }
        }
    }

    /**
     * 后序遍历：左 -> 右 -> 根
     * @param node
     */
    public void postOrderWithRecurise(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderWithRecurise(node.left);
        postOrderWithRecurise(node.right);
        System.out.print(node.value + " ");
    }

    public void postOrderWithIterator(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> p = node;
        HashMap<TreeNode<E>, Boolean> visited = new HashMap<>();
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.peek();
                if (p.right != null && !visited.getOrDefault(p.right, false)) {
                    p = p.right;
                    stack.push(p);
                    p = p.left;
                } else {
                    p = stack.pop();
                    System.out.print(p.value + " ");
                    visited.put(p, true);
                    p = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> elements = new ArrayList<>();
        for(int i = 1; i < 12; i++) {
            elements.add(i);
        }
        elements.set(5, null);
        elements.set(7, null);
        elements.set(8, null);
        BinaryTree<Integer> tree = new BinaryTree<>();
        TreeNode<Integer> root = tree.buildTree(elements);
        //tree.levelTraverse(root);
        System.out.println("前序遍历开始");
        // 前序遍历
        tree.preOrderWithRecurise(root);
        System.out.println();
        tree.preOrderWithIterator(root);
        System.out.println();
        System.out.println("中序遍历开始");
        // 中序遍历
        tree.inOrderWithRecurise(root);
        System.out.println();
        tree.inOrderWithIterator(root);
        System.out.println();
        System.out.println("后序遍历开始");
        // 后序遍历
        tree.postOrderWithRecurise(root);
        System.out.println();
        tree.postOrderWithIterator(root);
        System.out.println();
    }

}
