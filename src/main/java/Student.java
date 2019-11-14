public class Student {
    private final String name;
    private final int studentNumber;

    /**
     * Create a new Student object.
     *
     * @param name          Name of the Student
     * @param studentNumber Student number used to identify student in central administration
     */
    public Student(String name, int studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
    }

    /**
     * Retrieve the name of the current student.
     *
     * @return String representation of the name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the student number of the current student.
     * This number can be used to uniquely identify students.
     *
     * @return integer representation of the number.
     */
    public int getStudentNumber() {
        return studentNumber;
    }

    /**
     * Determine whether the current Student object represents the same Student as the passed object.
     *
     * @param other Object to compare to the current Student
     * @return boolean with a truthy value in case of equality
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Student)) {
            return false;
        }

        Student student = (Student) other;
        return studentNumber == student.getStudentNumber();
    }
}