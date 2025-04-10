package data

class StudentComparator : Comparator<Student> {
    override fun compare(o1: Student, o2: Student): Int {
        val t = o1.name.compareTo(o2.name)
        if (t != 0) return t
        val a = o1.age - o2.age
        return if (a != 0) a else o1.id - o2.id
    }
}