package group1.tutoringcenter.models;

public class Request {

    private int id;
    private String studentName;
    private String status;

    public Request(int id, String studentName, String status) {
        this.id = id;
        this.studentName = studentName;
        this.status = status;
    }

    public Request() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}