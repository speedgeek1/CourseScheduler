package service;

import Utills.EmailValidationUtils;
import Utills.StringUtils;
import model.CourseOffering;
import model.CourseScheduler;

public class CourseSchedulerService {

    public CourseScheduler courseScheduler;

    public CourseOfferingService courseOfferingService;

    public CourseSchedulerService(CourseScheduler courseScheduler) {
        this.courseScheduler = courseScheduler;
        this.courseOfferingService = new CourseOfferingService();
    }

    public String addCourseOffering(String courseName, String instructor, String date, String minEmployees, String maxEmployees) {
        int minEmp = Integer.parseInt(minEmployees);
        int maxEmp = Integer.parseInt(maxEmployees);

        CourseOffering courseOffering = courseOfferingService.createCourse(courseName, instructor, date, minEmp, maxEmp);

        courseScheduler.getCourses().put(courseOffering.getId(), courseOffering);
        return courseOffering.getId();
    }

    public void registerEmployee(String email, String courseOfferingId) {
        if(StringUtils.isNullOrEmpty(email) || StringUtils.isNullOrEmpty(courseOfferingId) || !EmailValidationUtils.isValidEmail(email)) {
            System.out.println("INPUT_DATA_ERROR");
            return;
        }

        String res = courseOfferingService.registerEmployee(courseScheduler.getCourses().get(courseOfferingId), email);

        if(res.equals("COURSE_FULL_ERROR") || res.equals("INPUT_DATA_ERROR")) {
            System.out.println(res);
            return;
        }

        courseScheduler.getCourseRegistrationMappings().put(res, courseOfferingId);
        System.out.printf("%s %s%n", res, "ACCEPTED");
    }

    public void cancelRegistration(String courseRegistrationId) {
        if(!courseScheduler.getCourseRegistrationMappings().containsKey(courseRegistrationId)) {
            System.out.printf("%s %s%n", courseRegistrationId, "CANCEL_REJECTED");
        } else {
            CourseOffering courseOffering = courseScheduler.getCourses().get(courseScheduler.getCourseRegistrationMappings().get(courseRegistrationId));
            boolean isCancelled = courseOfferingService.cancelRegistration(courseOffering, courseRegistrationId);

            if(isCancelled) {
                System.out.printf("%s %s%n", courseRegistrationId, "CANCEL_ACCEPTED");
            } else {
                System.out.printf("%s %s%n", courseRegistrationId, "CANCEL_REJECTED");
            }
        }
    }

    public void allotCourse(String courseOfferingId) {
        if(!courseScheduler.getCourses().containsKey(courseOfferingId)) {
            System.out.println("COURSE_NOT_FOUND_ERROR");
        } else {
            courseOfferingService.allotCourse(courseScheduler.getCourses().get(courseOfferingId));
        }
    }

}
