package subSection;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Salesperson {
    private Statement stmt;

    public Salesperson(Statement s) {
        stmt = s;
    }

    private void search(Scanner scanner) {
        Utility utility = new Utility(stmt);
        int criterion;
        System.out.println("Choose the search criterion: ");
        System.out.println("1. Part name");
        System.out.println("2. Manufacturer Name");
        System.out.print("Choose the search criterion: ");
        while (true) {
            criterion = scanner.nextInt();
            if (criterion >= 1 && criterion <= 2) {
                break;
            } else {
                System.out.println("Invalid input, please try again:");
            }
        }
        scanner.nextLine();
        String keyword = null;
        System.out.print("Type in the Search Keyword: ");
        while (true) {
            keyword = scanner.nextLine();
            if (keyword.trim().length() > 0) {
                break;
            } else {
                System.out.println("Invalid input, please try again:");
            }
        }
        String order = null;
        System.out.println("Choose ordering: ");
        System.out.println("1. By Price, ascending order");
        System.out.println("2. By Price, descending order");
        System.out.print("Choose the search criterion: ");
        while (true) {
            int ordering = scanner.nextInt();
            if (ordering >= 1 && ordering <= 2) {
                if (ordering == 1) {
                    order = "ASC";
                } else {
                    order = "DESC";
                }
                break;
            } else {
                System.out.println("Invalid input, please try again:");
            }
        }

        String query = null;
        if (criterion == 1) {
            query = "SELECT P_ID AS ID, " +
                "P_NAME AS Name, " +
                "M_NAME AS Manufacturer, " +
                "C_NAME AS Category, " +
                "P_AVAILABLE_QUANTITY AS Quantity, " +
                "P_WARRANTY AS Warranty, " +
                "P_PRICE AS Price " +
                "FROM part " +
                "INNER JOIN manufacturer ON part.m_id = manufacturer.m_id " +
                "INNER JOIN category ON part.c_id = category.c_id " +
                "WHERE P_NAME LIKE '%" + keyword + "%' " +
                "ORDER BY P_PRICE " + order;

        } else {
            query = "SELECT P_ID AS ID, " +
                "P_NAME AS Name, " +
                "M_NAME AS Manufacturer, " +
                "C_NAME AS Category, " +
                "P_AVAILABLE_QUANTITY AS Quantity, " +
                "P_WARRANTY AS Warranty, " +
                "P_PRICE AS Price " +
                "FROM part " +
                "INNER JOIN manufacturer ON part.m_id = manufacturer.m_id " +
                "INNER JOIN category ON part.c_id = category.c_id " +
                "WHERE M_NAME LIKE '%" + keyword + "%' " +
                "ORDER BY P_PRICE " + order;
        }
        utility.printQuery(query);
        System.out.println("End of Query");
    }



    private void sell(Scanner scanner) {
        Utility utility = new Utility(stmt);
        int pID;
        int sID;
        int tID = 0;
        System.out.print("Enter The Part ID: ");
        while (true) {
            pID = scanner.nextInt();
            if (pID > 0) {
                break;
            } else {
                System.out.println("Invalid input, please try again:");
            }
        }

        System.out.print("Enter The Salesperson ID: ");
        while (true) {
            sID = scanner.nextInt();
            if (sID > 0) {
                break;
            } else {
                System.out.println("Invalid input, please try again:");
            }
        }
        try {
        ResultSet rs = stmt.executeQuery("SELECT MAX(T_ID) AS maxID FROM transaction");
        if (rs.next()) {
            tID = rs.getInt("maxID") + 1;
        } else {
            System.out.println("No transcations record");
        }
        rs.close();
    } catch (Exception e) {
        System.err.println("Error retrieving T_ID: " + e);
    }
        String checkSQL = "SELECT P_AVAILABLE_QUANTITY, P_NAME FROM part WHERE P_ID = " + pID;
        String sellSQL = "UPDATE part SET P_AVAILABLE_QUANTITY = P_AVAILABLE_QUANTITY - 1 WHERE P_ID = " + pID;
        String transactionSQL = "INSERT INTO transaction (T_ID, P_ID, S_ID, TRANSACTION_DATE) VALUES ("+ tID + ", " + pID + ", " + sID + ", SYSDATE)";
        try {
            ResultSet resultSet = stmt.executeQuery(checkSQL);
            if (resultSet.next()) {
                int availableQuantity = resultSet.getInt("P_AVAILABLE_QUANTITY");
                String pName = resultSet.getString("P_NAME");

                if (availableQuantity > 0) {
                    stmt.executeUpdate(sellSQL);
                    stmt.executeUpdate(transactionSQL);
                    System.out.println("Product: " + pName + " (id: " + pID + ") Remaining Quantity: " + (availableQuantity - 1));
                } else {
                    System.out.println("Part is not available");
                }
            } else {
                System.out.println("Part ID not found");
            }
        } catch (Exception e) {
            System.err.println("Something went wrong in performing transaction: " + e);
        }
    }


    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Utility utility = new Utility(stmt);
        String[] options = {
            "Search for parts",
            "Sell a part",
            "Return to the main menu"
        };
        boolean running = true;
        while (running) {
            int choice = utility.prompt(scanner, "Operations for salesperson menu", options);
            switch (choice) {
                case 1:
                    search(scanner);
                    break;
                case 2:
                    sell(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again");
            }
        }
    }
}