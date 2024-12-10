import java.sql.*;
import java.util.*;
import subSection.*;;

class main {
    public static void main(String args[]) {
        Connection conn = null;
        Statement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            //System.out.print("connecting to database\n");
            conn = DriverManager.getConnection
            (
            "jdbc:oracle:thin:@db18.cse.cuhk.edu.hk:1521/oradb.cse.cuhk.edu.hk",
            "h007",
            "chesEvOc"
            );
            //System.out.print("connected to database\n");
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.err.println("Something went wrong:\n" + e + "\n");
        }
        Administrator administrator = new Administrator(stmt);
        // Salesperson salesperson = new Salesperson(stmt);
        // Manager manager = new Manager(stmt);
        Utility utility = new Utility(stmt);
        System.out.println("Welcome to sales system!");
        String[] options = 
        {
            "Operations for administrator",
            "Operations for salesperson",
            "Operations for manager",
            "Exit this program"
        };
        Boolean running = true;
        while(running) {
            int choice = utility.prompt(scanner, "Main menu", options);
            switch (choice) {
                case 1:
                    administrator.execute();
                    break; 
                case 2:
                    // salesperson.execute();
                    break; 
                case 3:
                    // manager.execute();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again");
            }               
        }
        System.out.println("Closing the sales system...");
        scanner.close();
    }
}