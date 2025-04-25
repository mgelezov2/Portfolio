package ca1_ooa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author michael
 */
//NOTE: Manual edit for the directory that students.txt and status.txt are located need to be changed to the users directory.

//UNFINISHED CHANGES
//IMPLEMENT USER INPUT DATA TO FILE MANUALLY
//MAKE IF STATEMENTS FOR SUBJECT NUMBER VALIDATION MORE PRESENTABLE

public class CA1_OOA {

    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) throws FileNotFoundException,InputMismatchException ,IOException {
        try{
            //FILE READER,WRITER AND MAIN ARRAY STORAGE
            BufferedReader studentReadTxt = new BufferedReader(new FileReader("/home/michael/NetBeansProjects/CA1_OOA/students.txt"));   
            ArrayList<String> stListValidation = new ArrayList<>();        
            BufferedWriter studentWriteTxt = new BufferedWriter(new FileWriter("/home/michael/NetBeansProjects/CA1_OOA/status.txt", false));
            //FOR LOOP REITERATION GOING LINE BY LINE THROUGH VALIDATIONS
            Stream<String> fileStream = Files.lines(Paths.get("students.txt"));
            int noOfLines = (int) fileStream.count();
            for (int i = 0; i < noOfLines; i++) {
                 String lineInProgress = studentReadTxt.readLine();
                 // String lineProcessed = lineInProgress;
                 String[] splitStudentData = lineInProgress.split(" ");
     
   
                //VALIDATE FIRST NAME THROUGH THE REGEX   
                if(splitStudentData[0].matches("[a-zA-Z]+")){
                   stListValidation.add(splitStudentData[0]);
                 }
                 else{ 
                   System.out.println("Error first name invalid!");      
                 }

               //VALIDATE SECOND NAME THROUGH THE REGEX    
               if(splitStudentData[1].matches("[a-zA-Z ']+|[0-9]+")){
                  stListValidation.add(splitStudentData[1]);
                }
               else{
                  System.out.println("Error second name invalid!");      
                }
            //VALIDATE AMOUNT OF CLASSES THROUGH THE REGEX AND REPLACES WORKLOAD WITH THE AMOUNT OF SUBJECT STUDENTS ARE DOING WITH FOLLOWING STATUS;
            //FULL-TIME, PART-TIME, LIGHT AND VERY LIGHT ALL LINKED TO APPROPIATE POSITIONS
            String workLoad = splitStudentData[3];
            String workLoadReplace = workLoad;
               if(workLoad.matches("^[0-9]{1,9}$+") && Integer.parseInt(workLoad) > 0 && Integer.parseInt(workLoad) < 9){
                  stListValidation.add(splitStudentData[3]);
               }
               else{
                  System.out.println("Error amount of classes is invalid!");      
               }
               if(workLoad.contains(splitStudentData[3])){
                  workLoadReplace = workLoadReplace.replace("8", "Full-time");
               }
               if(workLoad.contains(splitStudentData[3])){
                  workLoadReplace = workLoadReplace.replace("7", "Full-time");
               }
               if(workLoad.contains(splitStudentData[3])){
                  workLoadReplace = workLoadReplace.replace("6", "Full-time");
               }
               if(workLoad.contains(splitStudentData[3])){
                  workLoadReplace = workLoadReplace.replace("5", "Part-time");
               }
               if(workLoad.contains(splitStudentData[3])){
                  workLoadReplace = workLoadReplace.replace("4", "Part-time");
               }
               if(workLoad.contains(splitStudentData[3])){
                  workLoadReplace = workLoadReplace.replace("3", "Part-time");
               }
               if(workLoad.contains(splitStudentData[3])){
                  workLoadReplace = workLoadReplace.replace("2", "Light");
               }
               if(workLoad.contains(splitStudentData[3])){
                  workLoadReplace = workLoadReplace.replace("1", "Very Light");
               }

            //VALIDATE STUDENT NUMBER THROUGH THE REGEX WHICH IS SPLIT INTO 3 TO TARGET SPECIFIC INDEXS FOR VALIDATION      
            Pattern StudentNum = Pattern.compile("[A-Z]+|\\d+|[0-9]+");
            Matcher StudentNumMatch = StudentNum.matcher(splitStudentData[2]);
            ArrayList<String> stNumDataArray = new ArrayList<>();
            ArrayList<String> stNumValidation = new ArrayList<>();
            int minYear = 20, maxYear = 24;
               while (StudentNumMatch.find()) {
                      stNumDataArray.add(StudentNumMatch.group());
               }
            String stYear = stNumDataArray.get(0);
            String stCol = stNumDataArray.get(1);
            String stIntNum = stNumDataArray.get(2);
         
               if(stYear.matches("[A-Z]+|\\d+|[0-9]+") && minYear < Integer.parseInt(stYear) && Integer.parseInt(stYear) < maxYear){
                  stNumValidation.add(stYear);
               }
               if(stCol.matches("[A-Z]+|\\d+|[0-9]+")  && stCol.contains("CCT")){
                  stNumValidation.add(stCol);
               }
               if(stIntNum.matches("[A-Z]+|\\d+|[0-9]+") && 0 < Integer.parseInt(stIntNum) && Integer.parseInt(stIntNum) < 251){
                  stNumValidation.add(stIntNum);
               }
               if(stNumValidation.size() == 3){
                  stListValidation.add(splitStudentData[2]);
               }else{
                  System.out.println("Error student number is invalid!");      
               }
               //WRITES THE WHOLE LIST FROM STUDENT.TXT TO STATUS.TXT
            System.out.println(stListValidation.size());
            int arrayCount = stListValidation.size();
               if (arrayCount == 4){ 
                  // studentWriteTxt.write(lineProcessed + "\n");
                   studentWriteTxt.write("\n" + splitStudentData[2] + "-" + splitStudentData[1] + "\n" + workLoadReplace);
                   stListValidation.removeAll(stListValidation);
            
               }
             }
            studentReadTxt.close();
            studentWriteTxt.close();
     
    
            
//ERROR EXCEPTION CATCHES
}catch(FileNotFoundException e){
     System.out.println("Error: The file you're trying to access is missing from the directory!");
     e.printStackTrace();
    }catch (InputMismatchException e) {
     System.out.println("Error: The Data does not match records!");
     e.printStackTrace();
    }
    
}
}
