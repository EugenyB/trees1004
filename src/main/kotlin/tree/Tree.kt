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

    fun remove(value: T) {
        remove(value, root)
    }

    private fun remove(value: T, node: Node<T>?) : Node<T>? {
        var root = node
        if (root == null) return root
        if (value == root.key) {
            if (root.isLeaf) {
                root = null
            } else if (root.left != null) {
                root.key = predecessor(root)
                root.left = remove(root.key, root.left)
            } else {
                root.key = successor(root)
                root.right = remove(root.key, root.right)
            }
        } else if (comparator.compare(value, root.key) < 0) {
            root.left = remove(value, root.left)
        } else {
            root.right = remove(value, root.right)
        }
        return root
    }

    private fun predecessor(node: Node<T>) : T {
        var root = node
        root = root.left!!
        while (root.right != null) {
            root = root.right!!
        }
        return root.key
    }

    private fun successor(node: Node<T>) : T {
        var root = node
        root = root.right!!
        while (root.left != null) {
            root = root.left!!
        }
        return root.key
    }

    internal data class Node<T>(var key: T, var left: Node<T>? = null, var right: Node<T>? = null) {
        val isLeaf: Boolean
        get() = left == null && right == null
    }
}