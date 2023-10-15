package model;

import java.util.HashMap;

public class CourseScheduler {

    private static CourseScheduler instance;
    private HashMap<String, CourseOffering> courses;
    private HashMap<String, String> courseRegistrationMappings;

    private CourseScheduler() {
        this.courses = new HashMap<>();
        this.courseRegistrationMappings = new HashMap<>();
    }

    public static CourseScheduler getInstance() {
        if (instance == null) {
            instance = new CourseScheduler();
        }
        return instance;
    }

    public HashMap<String, CourseOffering> getCourses() {
        return courses;
    }

    public void setCourses(HashMap<String, CourseOffering> courses) {
        this.courses = courses;
    }

    public HashMap<String, String> getCourseRegistrationMappings() {
        return courseRegistrationMappings;
    }

    public void setCourseRegistrationMappings(HashMap<String, String> courseRegistrationMappings) {
        this.courseRegistrationMappings = courseRegistrationMappings;
    }
}
