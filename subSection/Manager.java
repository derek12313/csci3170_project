package subSection;

import java.sql.*;
import java.util.Scanner;

public class Manager{
    private Statement stmt;

    public Manager(Statement s){
        stmt = s;
    }

    // OP1
    private void listAllSalespersons(Scanner scanner){
        Utility utility = new Utility(stmt);
        int order;
        while (true) {
            System.out.println("Choose ordering: ");
            System.out.println("1. By ascending order");
            System.out.println("2. By descending order");
            System.out.println("Choose the list of ordering: ");
            if (scanner.hasNextInt()) {
                order = scanner.nextInt();
                if (order == 1 || order == 2) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer (1 or 2).");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer (1 or 2).");
                scanner.next();
            }
        }

        String query;
        if (order == 1) {
            query = """
                SELECT S_ID AS ID,
                       S_NAME AS Name,
                       S_PHONE_NUMBER AS "Mobile Phone",
                       S_EXPERIENCE AS "Years of Experience"
                FROM SALESPERSON
                ORDER BY S_NAME ASC
                """;
        } else {
            query = """
                SELECT S_ID AS ID,
                       S_NAME AS Name,
                       S_PHONE_NUMBER AS "Mobile Phone",
                       S_EXPERIENCE AS "Years of Experience"
                FROM SALESPERSON
                ORDER BY S_EXPERIENCE DESC
                """;
        }
        utility.printQuery(query);
        System.out.println("End of Query");
    }

    // OP2
    private void countSalesRecord(Scanner scanner){
        Utility utility = new Utility(stmt);
        int lowerBound, upperBound;
        while (true) {
            try {
                System.out.print("Type in the lower bound of years of experience: ");
                lowerBound = scanner.nextInt();
                System.out.print("Type in the upper bound of years of experience: ");
                upperBound = scanner.nextInt();
        
                if (upperBound >= lowerBound) {
                    break;
                }
                System.out.println("Error: Upper bound must be greater than or equal to the lower bound. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter integers only.");
            }
        }
        System.out.println("Transaction Record:");
        String query = """
            SELECT S.S_ID AS "ID",
                S.S_NAME AS "Name",
                S.S_EXPERIENCE AS "Years of Experience",
                COUNT(T.T_ID) AS "Number of Transactions"
            FROM SALESPERSON S
            LEFT JOIN TRANSACTION T ON S.S_ID = T.S_ID
            WHERE S.S_EXPERIENCE BETWEEN %d
            AND %d
            GROUP BY S.S_ID, S.S_NAME, S.S_EXPERIENCE
            ORDER BY COUNT(T.T_ID) DESC
            """.formatted(lowerBound, upperBound);

        utility.printQuery(query);
        System.out.println("End of Query");
    }

    // OP3
    private void listAllManufacturerSales(Scanner scanner){
        Utility utility = new Utility(stmt);

        String query = """
            SELECT M.M_ID AS "Manufacturer ID",
                   M.M_NAME AS "Manufacturer Name",
                   SUM(P.P_PRICE) AS "Total Sales Value"
            FROM MANUFACTURER M
            JOIN PART P ON M.M_ID = P.M_ID
            JOIN TRANSACTION T ON P.P_ID = T.P_ID
            GROUP BY M.M_ID, M.M_NAME
            ORDER BY SUM(P.P_PRICE) DESC
            """;
        utility.printQuery(query);
        System.out.println("End of Query");
    }

    // OP4
    private void nMostPopularPart(Scanner scanner) {
        Utility utility = new Utility(stmt);
        int num_parts;
    
        while (true) {
            System.out.print("Type in the number of parts: ");
            if (scanner.hasNextInt()) {
                num_parts = scanner.nextInt();
                if (num_parts > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next();
            }
        }
        scanner.nextLine();
        String query = """
            SELECT p.P_ID AS "Part ID", 
                    p.P_NAME AS "Part Name", 
                    COUNT(p.P_ID) AS "No. of Transaction"
            FROM PART p
            JOIN TRANSACTION t ON p.P_ID = t.P_ID
            GROUP BY p.P_ID, p.P_NAME
            ORDER BY COUNT(p.P_ID) DESC
            FETCH FIRST %d ROWS ONLY
        """.formatted(num_parts);

        utility.printQuery(query);
        System.out.println("End of Query");
    }

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        Utility utility = new Utility(stmt);
        String[] options = {
            "List all salespersons",
            "Count the no. of sales record of each salesperson under specific range on years of experience",
            "Show the total sales value of each manufacturer",
            "Show the N most popular part",
            "Return to the main menu"
        };
        boolean running = true;
        while (running) {
            int choice = utility.prompt(scanner, "Operations for salesperson menu", options);
            switch (choice) {
                case 1:
                    listAllSalespersons(scanner);
                    break;
                case 2:
                    countSalesRecord(scanner);
                    break;
                case 3:
                    listAllManufacturerSales(scanner);
                    break;
                case 4:
                    nMostPopularPart(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again");
            }
        }
    }
}