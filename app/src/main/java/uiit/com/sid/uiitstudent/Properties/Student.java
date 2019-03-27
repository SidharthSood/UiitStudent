package uiit.com.sid.uiitstudent.Properties;

public class Student {
    private String id;
    private String email;
    private String name;
    private int rollNumber;
    private int semester;
    private StudentsBranch branch;

    public Student(String id, String email, String name, int rollNumber, int semester, StudentsBranch branch) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.rollNumber = rollNumber;
        this.semester = semester;
        this.branch = branch;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int getSemester() {
        return semester;
    }

    public StudentsBranch getBranch() {
        return branch;
    }

}
