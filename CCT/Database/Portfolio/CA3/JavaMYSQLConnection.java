package ca3_ooa;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static javax.lang.model.type.TypeKind.NULL;

public class JavaMYSQLConnection {
    private static String MySQL_url = "";
    private static String MySQL_user = "";
    private static String MySQL_pass = "";
    private static String user;
    private static List<String> userManagement = new ArrayList<>();
    private static Scanner col_userInput = new Scanner(System.in);
    private static void collegeLogin(String MySQL_url, String MySQL_user, String MySQL_pass) throws SQLException {
        Connection collegeLogin_connection = DriverManager.getConnection(MySQL_url, MySQL_user, MySQL_pass);
        System.out.println("Database Connected!");
        System.out.println("Please enter your username: ");
        String col_username = col_userInput.nextLine();
        System.out.println("Please enter your password: ");
        String col_password = col_userInput.nextLine();
        String loginNameQuery = "SELECT colUser_full_name, colUser_login_name, colUser_password FROM users WHERE colUser_login_name = '" + col_username + "'";
        PreparedStatement PreparedLoginNameStatement = collegeLogin_connection.prepareStatement(loginNameQuery);
        ResultSet loginNameSet = PreparedLoginNameStatement.executeQuery();
        if (loginNameSet.next()) {
            String user_FN = loginNameSet.getString("colUser_full_name");
            String user_LN = loginNameSet.getString("colUser_login_name");
            String user_Pass = loginNameSet.getString("colUser_password");
            if (user_LN.equals("") || user_LN.equals(NULL)) {
                System.out.println("User is not registered");
            }
            if (col_username.equals("admin") && col_password.equals(user_Pass)) {
                System.out.println("Hello, " + user_FN);
                user = "admin";
            }
            else if (col_username.equals("office") && col_password.equals(user_Pass)) {
                System.out.println("Hello, " + user_FN);
                user = "office";
            }
            else if (col_username.equals("lecturer") && col_password.equals(user_Pass)) {
                System.out.println("Hello, " + user_FN);
                user = "lecturer";
            }
            else {
                System.out.println("Login failed. Check if your username or password was correct.");
            }
            }
    }
    private static void displayConsole(){
        try {
        FileWriter CourseRep = new FileWriter("CourseReport.csv");
        FileWriter StudentsRep = new FileWriter("StudentReport.csv");
        FileWriter LecturersRep = new FileWriter("LecturersReport.csv");
        FileWriter moduleRepID = new FileWriter("module_report_by_id.csv");
        Connection displayConsole_connection = DriverManager.getConnection(MySQL_url, MySQL_user, MySQL_pass);
        System.out.println("Database Connected!");
        String CourseRepRequest = 
                "SELECT course.moduleName AS module_name, course.programName AS program_name, course.modulePopulation AS enrolled_students, lecturer.lecturerName AS lecturer_name, CASE WHEN course.roomLocation = '' "
                + "THEN 'Online'ELSE course.roomLocation END AS classroom_location FROM Courses course INNER JOIN Lecturers lecturer ON course.lecturerID = lecturer.lecturerID;";
        PreparedStatement CourseReportStat = displayConsole_connection.prepareStatement(CourseRepRequest);
        ResultSet CourseRep_output = CourseReportStat.executeQuery();
        String StudentRepRequest = 
                "SELECT student.studentName AS student_name, student.studentID AS student_number, course.programName AS taken_program, GROUP_CONCAT(DISTINCT enrolled.moduleID) AS enrolled_module,GROUP_CONCAT(DISTINCT CASE WHEN grades.passed_module_ID IS NOT NULL THEN CONCAT(grades.passed_module_ID, ': ', grades.grades_pass) ELSE NULL END) AS module_completed, GROUP_CONCAT(DISTINCT grades.failed_module_id) "
                + "AS module_repeat FROM Students student LEFT JOIN  Enrollments enrolled ON student.studentID = enrolled.studentID LEFT JOIN Courses course ON enrolled.programID = course.programID LEFT JOIN Grades grades ON student.studentID = grades.studentID AND enrolled.moduleID = grades.moduleID GROUP BY student.studentName, student.studentID, course.programName;";
        PreparedStatement StudentReportStat = displayConsole_connection.prepareStatement(StudentRepRequest);
        ResultSet StudentRep_output = StudentReportStat.executeQuery();
        String LecturerRepRequest = 
                "SELECT LecturerName AS lecturer_name, LecturerRole AS lecturer_role,GROUP_CONCAT(Courses.moduleName) AS undergoing_modules, SUM(Courses.modulePopulation) "
                + "AS current_student_total, GROUP_CONCAT(Courses.programName) AS undergoing_classes FROM Lecturers JOIN Courses ON Lecturers.lecturerID = Courses.lecturerID GROUP BY Lecturers.lecturerID, LecturerName, LecturerRole;";
        PreparedStatement LecturerReportStatement = displayConsole_connection.prepareStatement(LecturerRepRequest);
        ResultSet LecturerRep_output = LecturerReportStatement.executeQuery();
        while (true){
            if (user.equals("admin")) {
                    System.out.println("5: Change password");
                    System.out.println("6: Log out");
                    System.out.println("7: Manage users");
                }
                if (user.equals("office")) {
                    System.out.println("1: Generate a Course Report");
                    System.out.println("2: Generate a Student Report");
                    System.out.println("3: Generate a Lecturer Report");
                    System.out.println("4: Generate a Module Report through an ID");
                    System.out.println("5: Change password");
                    System.out.println("6: Log out");
                }
                if (user.equals("lecturer")) {
                    System.out.println("3: Generate a Lecturer Report");
                    System.out.println("5: Change password");
                    System.out.println("6: Log out");
                }
            System.out.println("Select an option: ");
            int user_choice = col_userInput.nextInt();
            col_userInput.nextLine();
            switch (user_choice) {
             case 1:
                while(CourseRep_output.next()){
               
               String moduleName = CourseRep_output.getString("module_name"), programName = CourseRep_output.getString("program_name"), 
                      lecturerName = CourseRep_output.getString("lecturer_name"), roomLocation = CourseRep_output.getString("classroom_location");
               int enrolledStudents = CourseRep_output.getInt("enrolled_students");
               

               CourseRep.append((moduleName)).append(",");
               CourseRep.append((programName)).append(",");
               CourseRep.append(String.valueOf(enrolledStudents)).append(",");
               CourseRep.append((lecturerName)).append(",");
               CourseRep.append((roomLocation)).append("\n");
               collegeReportGeneration("CourseReport.csv");
               
               
            }
            CourseRep.flush();
            CourseRep.close();
            break;
                    
            case 2:
            
             while(StudentRep_output.next()){
                String studentName = StudentRep_output.getString("student_name"), studentID = StudentRep_output.getString("student_number"),
                        takenProgramme = StudentRep_output.getString("taken_program"), enrolledModule = StudentRep_output.getString("enrolled_module"), 
                        moduleCompleted = StudentRep_output.getString("module_completed"), moduleFailed = StudentRep_output.getString("module_repeat");
            
                StudentsRep.append((studentName)).append(",");
                StudentsRep.append((studentID)).append(",");
                StudentsRep.append((takenProgramme)).append(",");
                StudentsRep.append((enrolledModule)).append(",");
                StudentsRep.append((moduleCompleted)).append(",");
                StudentsRep.append((moduleFailed)).append("\n");
                collegeReportGeneration("StudentReport.csv");
             }
             StudentsRep.flush();
             StudentsRep.close();
             break;
            case 3:
            
             while(LecturerRep_output.next()){
               String lecturerName = LecturerRep_output.getString("lecturer_name"), lecturerRole = LecturerRep_output.getString("lecturer_role"), 
                      runningModules = LecturerRep_output.getString("undergoing_modules"), runningClasses = LecturerRep_output.getString("undergoing_classes") ;
               int total_of_students = LecturerRep_output.getInt("current_student_total");
               
               LecturersRep.append((lecturerName)).append(",");
               LecturersRep.append((lecturerRole)).append(",");
               LecturersRep.append((runningModules)).append(",");
               LecturersRep.append(String.valueOf(total_of_students)).append(",");
               LecturersRep.append((runningClasses)).append("\n");
               collegeReportGeneration("LecturerReport.csv");
            }
            LecturersRep.flush();
            LecturersRep.close();
            
            break;
          case 4:
               System.out.println("Please Enter a Module ID you wish to view: ");
               String moduleId = col_userInput.nextLine();
               String moduleColumn_titles = "Module ID,Module Name,Lecturer Name,Location,Student Name\n";
               moduleRepID.write(moduleColumn_titles);
                       
               String moduleIDReq = "SELECT studentID FROM Grades WHERE moduleID = '" +moduleId+ "'";
               PreparedStatement moduleIDStat = displayConsole_connection.prepareStatement(moduleIDReq);
               ResultSet moduleID_output = moduleIDStat.executeQuery();
                   while (moduleID_output.next()) {
                           String studentId = moduleID_output.getString("studentID");
                           moduleRepID.append((moduleId)).append(",");
                           String moduleNameReq = "SELECT moduleName, lecturerID, roomLocation FROM Courses WHERE moduleID = '" +moduleId+ "'";
                           PreparedStatement moduleNameStat = displayConsole_connection.prepareStatement(moduleNameReq);
                           ResultSet moduleName_output = moduleNameStat.executeQuery();
                            if (moduleName_output.next()) {
                                String moduleName = moduleName_output.getString("moduleName");
                                moduleRepID.append((moduleName)).append(",");
                                String lecturerId = moduleName_output.getString("lecturerID");
                                String lecturerNameQuery = "SELECT lecturerName FROM Lecturers WHERE lecturerID = '" +lecturerId+ "'";
                                PreparedStatement lecturerNameStat = displayConsole_connection.prepareStatement(lecturerNameQuery);
                                ResultSet lecturerNameSet = lecturerNameStat.executeQuery();
                                if (lecturerNameSet.next()) {
                                    String lecturerName = lecturerNameSet.getString("lecturerName");
                                    moduleRepID.append((lecturerName)).append(",");
                                    }                                    
                                String location = moduleName_output.getString("roomLocation");
                                moduleRepID.append((location)).append(",");
                                }
                String studentNameQuery = "SELECT studentName FROM Students WHERE studentID = '" +studentId+ "'";
                PreparedStatement studentNameStat = displayConsole_connection.prepareStatement(studentNameQuery);
                ResultSet studentNameSet = studentNameStat.executeQuery();
                        if (studentNameSet.next()) {
                            String studentName = studentNameSet.getString("studentName");
                            moduleRepID.append((studentName)).append("\n");
                            }
            }
            moduleRepID.flush();
            moduleRepID.close();
            break;
        case 5:
            System.out.println("Please enter your new password");
            String changingToNewPass = col_userInput.nextLine();          
            String changeToNewPass = "UPDATE users SET colUser_password = '" +changingToNewPass+"' WHERE colUser_login_name = '" +user+"';";
            PreparedStatement changeToNewPassStat = displayConsole_connection.prepareStatement(changeToNewPass);
            changeToNewPassStat.executeUpdate();
            break;
        case 6:
            System.out.println("Logging out of College Console");
            collegeLogin(MySQL_url, MySQL_user, MySQL_pass);
            break;
        case 7: 
            if (user.equals("admin")){
                collegeManageUsers();
            }else{
                System.out.println("ERROR: You do not have permission for this user");
            }
            break;
            default:
            System.out.println("ERROR: Please choose between 1 and 6 options");
            }
            
    }
}catch (IOException | SQLException e) {
            System.out.println("Connection Failure!");
            e.printStackTrace();
        }
    }
    private static void collegeReportGeneration(String reportFormat){
        System.out.println("Please wait while we're generating your report... " + reportFormat);
    }
    private static void collegeManageUsers(){
        System.out.println("1: Add a user");
        System.out.println("2: Delete a user");
        System.out.println("3: Update a user");
        System.out.println("4: View users");
        System.out.println("5: Go back to main menu");
        int manageUserChoice = col_userInput.nextInt();
        col_userInput.nextLine();
        switch(manageUserChoice){
            case 1:
                manageUser_AddUser();
                break;
            case 2:
                manageUser_removeUser();
                break;
            case 3:
                manageUser_UpdateUser();
                break;
            case 4:
                manageUser_ViewUser();
                break;
            case 5:
                System.out.println("Redirecting back to main menu..");
                displayConsole();
                break;
            default:
                System.out.println("ERROR: Please choose between 1 and 5 options");
        }
    }
    private static void manageUser_AddUser(){
        System.out.println("Enter a user you wish to add: ");
        String addedUser = col_userInput.nextLine();
        userManagement.add(addedUser);
        System.out.println("User " + addedUser + " added");
    }
    private static void manageUser_removeUser(){
        System.out.println("Enter a user you wish to remove: ");
        String userToBeRemoved = col_userInput.nextLine();
        if(userManagement.contains(userToBeRemoved)){
            userManagement.remove(userToBeRemoved);
            System.out.println("User " + userToBeRemoved + " removed");
        }else{
            System.out.println("ERROR: This user does not exist within this College");
        }
    }
    private static void manageUser_UpdateUser(){
        System.out.println("Please enter a user name you wish to change: ");
        String currentUser = col_userInput.nextLine();
        System.out.println("Please enter the username you wish to change your old user name to: ");
        String newUser = col_userInput.nextLine();
        if(userManagement.contains(currentUser)){
            userManagement.remove(currentUser);
            userManagement.add(newUser);
            System.out.println("You have changed your old username to " + newUser);
            
        }else{
            System.out.println("ERROR: This user does not exist within this College");
        }
    }
    private static void manageUser_ViewUser(){
        for (String listOfUsers : userManagement){
            System.out.println(listOfUsers);
        }
    }
    public static void main(String[] args) {
        try{
        collegeLogin(MySQL_url, MySQL_user, MySQL_pass);
        displayConsole();
        collegeManageUsers();
        }catch (SQLException e) {
            System.out.println("Connection Failure!");
            e.printStackTrace();
}
}
}
