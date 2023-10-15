// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import model.CourseScheduler;
import service.CourseSchedulerService;
import constants.ErrorConstants;
import constants.InputCommands;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Please provide a file path as a command-line argument.");
            return;
        }

        CourseSchedulerService courseSchedulerService = new CourseSchedulerService(CourseScheduler.getInstance());
        String filePath = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into individual words using space as the delimiter
                String[] inputs = line.split(" ");

                switch (inputs[0]) {
                    case InputCommands.ADD_COURSE_OFFERING -> {
                        if (inputs.length < 6) {
                            System.out.println(ErrorConstants.INPUT_DATA_ERROR);
                            break;
                        }
                        System.out.println(courseSchedulerService.addCourseOffering(inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
                    }
                    case InputCommands.REGISTER -> {
                        if (inputs.length < 3) {
                            System.out.println(ErrorConstants.INPUT_DATA_ERROR);
                            break;
                        }
                        courseSchedulerService.registerEmployee(inputs[1], inputs[2]);
                    }
                    case InputCommands.CANCEL -> {
                        if (inputs.length < 2) {
                            System.out.println(ErrorConstants.INPUT_DATA_ERROR);
                            break;
                        }
                        courseSchedulerService.cancelRegistration(inputs[1]);
                    }
                    case InputCommands.ALLOT -> {
                        if (inputs.length < 2) {
                            System.out.println(ErrorConstants.INPUT_DATA_ERROR);
                            break;
                        }
                        courseSchedulerService.allotCourse(inputs[1]);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}