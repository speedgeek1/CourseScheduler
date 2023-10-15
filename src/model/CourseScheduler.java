package model;

import java.util.HashMap;

public class CourseScheduler {
    private HashMap<String, CourseOffering> courses;
    private HashMap<String, String> courseRegistrationMappings;

    public CourseScheduler() {
        this.courses = new HashMap<>();
        this.courseRegistrationMappings = new HashMap<>();
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
