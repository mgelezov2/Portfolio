package ca2_ooa;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michael
 */

//EMPLOYEE TEST THAT EXTENDS TO THE EMPLOYEE CLASS ALLOWING USAGE OF METHODS FROM THE EMPLOYEE CLASS
public class EmployeeTest extends Employee{

    public static void mainCall() {
      
//PROJECTGROUP ARRAY FOR STORING EMPLOYEE OBJECTS; NAME, EMAIL AND EMPLOYEE NUMBER
        ArrayList<String> projectGroup = new ArrayList<String>();
        ArrayList<Integer> empNums = new ArrayList<Integer>();
      
// M ARRAY FOR STORING A SPECIFIC EMPLOYEE THAT IS SELECTED BY AN EMPLOYEE NUMBER
        List<String> m = new ArrayList<String>();
      
//EMPLOYEE OBJECTS THAT ARE STORED INTO AN ARRAY
        Employee empObj1 = new Employee();
        Employee empObj2 = new Employee();
        Employee empObj3 = new Employee();
        
//EMPLOYEE OBJECTS THAT ARE INITALIZED
        String empObject1Name = empObj1.getName("Joe Bloggs"), 
               empObject1Email = empObj1.getEmail(empObj1.setEmail("jb@gmail.com"));
        
        int empObjectNum1 = empObj1.getEmpNum(123456);
        
        
        
        String empObject2Name = empObj2.getName("Ana Banana"), 
               empObject2Email = empObj2.getEmail(empObj2.setEmail("ab@gmail.com"));
        
        int empObjectNum2 = empObj2.getEmpNum(345213);
        
        
        String empObject3Name = empObj3.getName("Tom Thumb"),
               empObject3Email = empObj3.getEmail(empObj3.setEmail("tt@gmail.com"));
        
        int empObjectNum3 = empObj3.getEmpNum(542134);

        //OBJECTS BELOW ARE STORED IN AN ARRAY AND AN EMAIL CHECK TO MAKE SURE IT FOLLOWS @GMAIL.COM FORMAT
if(empObject1Email.contains("@gmail.com") && empObject2Email.contains("@gmail.com") && empObject3Email.contains("@gmail.com")){
            projectGroup.add(empObject1Name);
            projectGroup.add(empObject1Email);
            empNums.add(empObjectNum1);
        
            projectGroup.add(empObject2Name);
            projectGroup.add(empObject2Email);
            empNums.add(empObjectNum2);

            projectGroup.add(empObject3Name);
            projectGroup.add(empObject3Email);
            empNums.add(empObjectNum3);
}else{
    System.out.println("Error invalid email format");
}
        
        
        
        //System.out.println(projectGroup + "\n");
        //System.out.println(empNums + "\n");
        //System.out.println(empObj1.getNextEmpNum(123456));
        
// IF EMPLOYEE EXISTS WITH THIS EMPLOYEE NUMBER IT IS STORED IN M ARRAY IF NOT ERROR IS OUTPUTTED
        if(empNums.contains(123456)){
            System.out.println(empObj1.getName(empObject1Name) + " " + empNums.get(0));
            //m.add(empObj1.getName(empObject1Name));
            //System.out.println(m);
        }else{
            System.out.println("Employee does not exist");
        }
        
}

//WHERE THE EMPLOYEE TEST IS CALLED TO RUN
    public static void main(String[] args) {
           EmployeeTest.mainCall();
    }
}

