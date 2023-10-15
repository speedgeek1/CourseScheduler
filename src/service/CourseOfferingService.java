package service;

import model.CourseOffering;
import model.Registration;

import java.util.Comparator;

public class CourseOfferingService {

    public CourseOffering createCourse(String courseName, String instructor, String date, int minEmployees, int maxEmployees) {
        String courseId = formCourseId(courseName, instructor);
        return new CourseOffering(courseId, courseName, instructor, date, minEmployees, maxEmployees);
    }

    public String registerEmployee(CourseOffering courseOffering, String email) {
        if(courseOffering.isFull()) {
            return "COURSE_FULL_ERROR";
        }

        String registrationId = formRegistrationId(courseOffering.getCourseName(), email);
        if(courseOffering.getRegistrations().stream().anyMatch(registration -> registration.getId().equals(registrationId))) {
            return "INPUT_DATA_ERROR";
        }

        Registration registration = new Registration(registrationId, email);
        courseOffering.getRegistrations().add(registration);
        return registrationId;
    }

    public boolean cancelRegistration(CourseOffering courseOffering, String courseRegistrationId) {
        Registration res = courseOffering.getRegistrations().stream().filter(registration -> registration.getId().equals(courseRegistrationId)).toList().get(0);

        if(courseOffering.getCourseStatus() == CourseOffering.CourseStatus.ALLOTED || courseOffering.getCourseStatus() == CourseOffering.CourseStatus.CANCELLED) {
            res.setRegistrationStatus(Registration.RegistrationStatus.CANCEL_REJECTED);
            return false;
        } else {
            res.setRegistrationStatus(Registration.RegistrationStatus.CANCEL_ACCEPTED);
            courseOffering.getRegistrations().remove(res);
            return true;
        }
    }

    public void allotCourse(CourseOffering courseOffering) {
        Registration.RegistrationStatus finalStatus = null;
        boolean courseCancelled = false;
        if(courseOffering.getRegistrations().size() >= courseOffering.getMinEmployee()) {
            finalStatus = Registration.RegistrationStatus.CONFIRMED;
            courseOffering.setCourseStatus(CourseOffering.CourseStatus.ALLOTED);
        } else {
            courseOffering.setCourseStatus(CourseOffering.CourseStatus.CANCELLED);
            courseCancelled = true;
        }

        courseOffering.getRegistrations().sort(Comparator.comparing(Registration::getId));

        for(Registration registration : courseOffering.getRegistrations()) {
            if(!courseCancelled) registration.setRegistrationStatus(finalStatus);
            System.out.printf("%s %s %s %s %s %s %s%n", registration.getId(), registration.getEmail(), courseOffering.getId(), courseOffering.getCourseName(), courseOffering.getInstructor(), courseOffering.getDate(), registration.getRegistrationStatus().name());
        }
    }

    public String formCourseId(String courseName, String instructor) {
        return String.format("OFFERING-%s-%s", courseName.toUpperCase(), instructor.toUpperCase());
    }

    public String formRegistrationId(String courseName, String email) {
        String userName = email.split("@")[0];
        return String.format("REG-COURSE-%s-%s", userName.toUpperCase(), courseName.toUpperCase());
    }
}
