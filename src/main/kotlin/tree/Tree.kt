package tree

import data.StudentComparator

class Tree<T>(val comparator: Comparator<T>) {

    private var root: Node<T>? = null

    val isEmpty: Boolean
    get() = root == null

    fun add(value: T) : Boolean {
        if (isEmpty) {
            root = Node(value)
            return true
        }
        return addInSubTree(value, root!!)
    }

    private fun addInSubTree(value: T, root: Node<T>): Boolean {
        if (value == root.key) return false
        if (comparator.compare(value, root.key) < 0) {
            if (root.left == null) {
                root.left = Node(value)
                return true
            } else {
                return addInSubTree(value, root.left!!)
            }
        } else {
            if (root.right == null) {
                root.right = Node(value)
                return true
            } else {
                return addInSubTree(value, root.right!!)
            }
        }
    }

    fun traverse() {
        traverse(root)
        println()
    }

    private fun traverse(node: Node<T>?) {
        if (node != null) {
            traverse(node.left)
            visit(node)
            traverse(node.right)
        }
    }

    private fun visit(node: Node<T>) {
        println(node.key)
    }

    fun contains(value: T) = find(value) != null

    private fun find(value: T) : Node<T>? {
        if (isEmpty) return null
        return findInSubTree(value, root!!)
    }

    private fun findInSubTree(value: T, root: Node<T>) : Node<T>? {
        if (value == root.key) return root
        return if (comparator.compare(value, root.key) < 0) {
            if (root.left == null) null else findInSubTree(value, root.left!!)
        } else {
            if (root.right == null) null else findInSubTree(value, root.right!!)
        }
    }

    internal data class Node<T>(var key: T, var left: Node<T>? = null, var right: Node<T>? = null) {
        val isLeaf: Boolean
        get() = left == null && right == null
    }
}