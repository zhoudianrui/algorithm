package io.frlib.algorithm.base;

/**
 * 二叉树节点
 * @param <E>
 */
public class TreeNode<E> {
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
