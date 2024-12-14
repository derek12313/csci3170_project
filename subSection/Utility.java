package subSection;
import java.sql.*;
import java.util.*;


public class Utility {
    private Statement stmt;

    public Utility(Statement s) {
        stmt = s;
    }

    public void printQuery(String query) {
        try {
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();  
            System.out.print(" |");
            int col = rsmd.getColumnCount();
            for(int i=1; i<=col; i++) {
                System.out.print(" " + rsmd.getColumnName(i) + " |");
            }
            System.out.print("\n");
            while (rs.next()){
                System.out.print(" |");
                for(int i=1; i<=col; i++) {
                    System.out.print(" " + rs.getString(i) + " |");
                }
                System.out.print("\n");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public int prompt(Scanner scanner, String interfaceName, String[] options) {
        System.out.println("-----" + interfaceName + "-----");
        System.out.println("What kind of operation would you like to perform?");
        for(int i=0; i<options.length; i++) {
            System.out.println(i+1 + ". " + options[i]);
        }
        System.out.print("Enter Your Choice: ");
        while(true) {
            int choice = scanner.nextInt();
            if(choice >= 1 && choice <= options.length) {
                return choice;
            }
            else {
                System.out.println("Invalid input, please try again:");
            }
        }
    }
}
