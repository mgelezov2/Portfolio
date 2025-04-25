package ca2_ooa;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author michael
 */

//COMPANY CLASS THAT EXTENDS TO EMPLOYEE FOR USAGE OF EMPLOYEE METHODS
public class Company_base extends Employee{
    Employee employee_comp = new Employee();
    String company_Name;
    ArrayList<String> staff = new ArrayList<>();

//COMPANY CLASS CONSTRUCTOR
     public Company_base(){
        company_Name = "Business Gnómes Ltd";
        
    }
     
//INITALIZED COMPANY CONSTRUCTOR
    public Company_base(String companyName){
        company_Name = companyName;
    }

//ADDS NEW STAFF TO THE COMPANY ARRAY CALLED STAFF
    public ArrayList<String> addNewStaff(String name, String email, int empNum){
        staff.add(employee_comp.getName(name));
        staff.add(employee_comp.getEmail(email));
        staff.add(String.valueOf(employee_comp.getNextEmpNum(empNum)));
        return staff;
     }
    
//REMOVES AN EMPLOYEE BY EMPLOYEE NUMBER
//BUG: UNABLE TO REMOVE EMPLOYEE DESPITE ENTERING STAFF NUMBER    
    public ArrayList<String> removeStaff(int empNum){
        staff.remove(employee_comp.getName(name));
        staff.remove(employee_comp.getEmail(email));
     
        return staff;
    }

//CALCULATES AMOUNT OF STAFF MEMBERS IN THE ARRAY
    public int getStaffNumber(){
        int size = staff.size();
        return size;
    }
    
//RETURNS LIST OF EMPLOYEES AND CHECKS IF THEY ARE ON THE LIST
//BUG: UNABLE TO RETURN A NAME OF THE TARGETTED EMPLOYEE BY EMPLOYEE NUMBER AS IT RETURNS NOTHING WHEN PROMPTED TO DO SO
    public String listEmployees(int empNum){
        Iterator<String> employeeList = staff.iterator();
        while(employeeList.hasNext()){
            if(employeeList.next().equals(String.valueOf(employee_comp.getNextEmpNum(empNum)))){
            System.out.println("Current staff list: " + staff);
            }
        }
        return null;
    }

//MANAGER CLASS CONSOLE THAT EXTENDS TO COMPANY CLASS FOR USAGE OF ITS METHODS  
    public static class Manager extends Company_base{
    Company_base manager_system = new Company_base();
    Scanner userInput = new Scanner(System.in);
    int choice = 0;
    String userName,password;
    String userInp,userPassInp;
    String nameInp, emailInp;
    int empNumInp;
    
//RETURNS USERNAME THAT THE MANAGER HAS TYPED IN
    public String getuserName(){
        return userName;
    }
    
//ALLOWS FOR MANUAL INPUT FOR THE MANAGER TO ENTER THEIR USERNAME
    public String setuserName(String managerUsername){
        return this.userName = managerUsername;
    }
    
//RETURNS PASSWORD TYPED BY THE MANAGER
    public String getPassword(){
        return password;
    }
    
//ALLOWS FOR MANUAL INPUT OF THE PASSWORD FOR THE MANAGER TO ENTER THEIR USERNAME
    public String setPassword(String managerPassword){
        return this.password = managerPassword;
    }

//MANAGER CONSOLE DISPLAY THAT DISPLAYS THE LOG IN, ADD EMPLOYEES TO A LIST, VIEW CURRENT EMPLOYEES ON THAT LIST AND AN OPTION TO EXIT THE PROGRAM
    public void managerConsole(){
    do {
    System.out.println("Enter \"1 to log in\", \"2 to add new employee\", \"3 to view current employees\" , \"4 to remove an employee\" or \"5 to exit\"");
    choice = userInput.nextInt();
    userInp = userInput.nextLine();
    switch (choice)
    {
        case 1:
        System.out.println("Please enter Username: ");
        userInp = userInput.nextLine();
        System.out.println("Please enter Password: ");
        userPassInp = userInput.nextLine();
        if(userInp.equals(setuserName("Gnomeo")) && userPassInp.equals(setPassword("Smurf"))){
            System.out.println("Login successful!");
        }else{
            System.out.println("Login failed");
        }
            break;
        case 2: 
            System.out.println("Type name of the employee you wish to add: ");
            nameInp = userInput.nextLine();
            System.out.println("Type email of the employee you wish to add: ");
            emailInp = userInput.nextLine();
            System.out.println("Type employee number of the employee you wish to add: ");
            empNumInp = userInput.nextInt();
            System.out.println(manager_system.addNewStaff(nameInp, emailInp, empNumInp));
            
            break;
        case 3: 
            System.out.println(manager_system.listEmployees(empNumInp));
            break;
        case 4: 
            System.out.println("Type employee number of the employee you wish to remove: ");
            empNumInp = userInput.nextInt();
            manager_system.removeStaff(empNumInp);
            break;
        case 5: 
            System.out.println("Exiting Menu");
            break;
        default:
            System.out.println("Choice must be a value between 1 and 5.");
    }   
}while (choice != 5); 

}
    
    }

//MAIN CALL FOR TESTING THE ABOVE FUNCTIONS, UNCOMMENT THE TESTS BELOW TO RUN FUNCTIONS
    public static void main(String[] args) {
        
//COMPANY METHODS TEST

      // Company_base comp_test = new Company_base();
       //System.out.println(comp_test.addNewStaff("John Doe", "jd@gmail.com", 123456));
       //System.out.println(comp_test.getStaffNumber());
      // System.out.println(comp_test.listEmployees(123456));
      
//MANAGER TEST

      //Manager manager_test = new Manager();
      //manager_test.managerConsole();
      
        
    }

    
}
