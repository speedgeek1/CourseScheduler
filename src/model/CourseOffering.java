package model;

import java.util.ArrayList;



public class CourseOffering {

    public enum CourseStatus { CREATED, CANCELLED, ALLOTED };

    private String id;
    private String courseName;
    private String instructor;
    private String date;

    private int minEmployee;

    private int maxEmployee;

    private ArrayList<Registration> registrations;

    private CourseStatus courseStatus;

    public CourseOffering(String id, String courseName, String instructor, String date, int minEmployee, int maxEmployee) {
        this.id = id;
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minEmployee = minEmployee;
        this.maxEmployee = maxEmployee;
        this.courseStatus = CourseStatus.CREATED;
        this.registrations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMinEmployee() {
        return minEmployee;
    }

    public void setMinEmployee(int minEmployee) {
        this.minEmployee = minEmployee;
    }

    public int getMaxEmployee() {
        return maxEmployee;
    }

    public void setMaxEmployee(int maxEmployee) {
        this.maxEmployee = maxEmployee;
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(ArrayList<Registration> registrations) {
        this.registrations = registrations;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public boolean isFull() {
        return registrations.size() == maxEmployee;
    }
}
