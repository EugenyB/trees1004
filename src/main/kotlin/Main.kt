import data.Student
import data.StudentComparator
import tree.Tree

fun main() {
    val tree = Tree(StudentComparator())
    tree.add(Student(1,"Tom", 18))
    tree.add(Student(2,"Jane", 19))
    tree.add(Student(3,"John", 22))
    tree.add(Student(4,"Tom", 17))
    tree.add(Student(5,"Jane", 18))
    tree.add(Student(9,"Kate", 22))
    tree.add(Student(6,"Piter", 19))
    tree.add(Student(7,"Kate", 22))
    tree.add(Student(8,"Mary", 21))

    tree.traverse()

    println(tree.contains(Student(1,"Tom", 17)))
}