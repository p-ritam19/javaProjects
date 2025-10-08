import java.util.List;

public class EmployeeDataAnalyzer {
    public static void main(String[] args){
        EmployeeManager employeeManager = new EmployeeManager();

        employeeManager.addEmployee(new Employee(1,"Geek1","Engineering",75000.0));
        employeeManager.addEmployee(new Employee(2,"Geek2","Marketing",68000.0));
        employeeManager.addEmployee(new Employee(3,"Geek3","Engineering",55000.0));

        Thread processor1 = new EmployeeProcessor(employeeManager,1);
        Thread processor2 = new EmployeeProcessor(employeeManager,2);
        Thread processor3 = new EmployeeProcessor(employeeManager,3);

        processor1.start();
        processor2.start();
        processor3.start();

        try{
            processor1.join();
            processor2.join();
            processor3.join();

        }catch(InterruptedException e){
            System.out.println("Error waiting for threads completion:" + e.getMessage());
        }

        System.out.println("\nEmployees with salary >= 70000:");
        List<Employee> highEarners = employeeManager.filterEmployeesBySalary(70000.0);
        highEarners.forEach(System.out::println);


        System.out.println("\nEmployees sorted by salary:");
        List<Employee>sortedEmployees = employeeManager.sortEmployeesBySalary();
        sortedEmployees.forEach(System.out::println);


    }
}
