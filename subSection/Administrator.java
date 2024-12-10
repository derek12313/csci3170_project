package subSection;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Administrator {
    private Statement stmt;

    public Administrator(Statement s) {
        stmt = s;
    }
    
    private void create() {
        String createCategory = 
        """
        CREATE TABLE CATEGORY (
            C_ID INTEGER NOT NULL PRIMARY KEY CHECK(C_ID >= 1 AND C_ID <= 9),
            C_NAME VARCHAR(20) NOT NULL
        )
        """;
        String createManufacturer =
        """
        CREATE TABLE MANUFACTURER (
            M_ID INTEGER NOT NULL PRIMARY KEY CHECK(M_ID >= 1 AND M_ID <= 999),
            M_NAME VARCHAR(20) NOT NULL,
            M_ADDRESS VARCHAR(50) NOT NULL,
            M_NUMBER INTEGER NOT NULL CHECK(M_NUMBER >= 10000000 AND M_NUMBER <= 99999999)
        )
        """;
        String createPart =
        """
        CREATE TABLE PART (
            P_ID INTEGER NOT NULL PRIMARY KEY CHECK(P_ID >= 1 AND P_ID <= 999),
            P_NAME VARCHAR(20) NOT NULL,
            P_PRICE INTEGER NOT NULL CHECK(P_PRICE >= 1 AND P_PRICE <= 99999),
            M_ID INTEGER NOT NULL,
            C_ID INTEGER NOT NULL,
            P_WARRANTY INTEGER NOT NULL CHECK(P_WARRANTY >= 1 AND P_WARRANTY <= 99),
            P_AVAILABLE_QUANTITY INTEGER NOT NULL CHECK(P_AVAILABLE_QUANTITY >= 0 AND P_AVAILABLE_QUANTITY <= 99),
            CONSTRAINT fk_manufacturer FOREIGN KEY (M_ID) REFERENCES MANUFACTURER(M_ID),
            CONSTRAINT fk_category FOREIGN KEY (C_ID) REFERENCES CATEGORY(C_ID)
        )
        """;
        String createSalesperson =
        """
        CREATE TABLE SALESPERSON (
            S_ID INTEGER NOT NULL PRIMARY KEY CHECK(S_ID >= 1 AND S_ID <= 99),
            S_NAME VARCHAR(20) NOT NULL,
            S_ADDRESS VARCHAR(50) NOT NULL,
            S_PHONE_NUMBER INTEGER NOT NULL CHECK(S_PHONE_NUMBER >= 10000000 AND S_PHONE_NUMBER <= 99999999),
            S_EXPERIENCE INTEGER NOT NULL CHECK(S_EXPERIENCE >= 1 AND S_EXPERIENCE <= 9)
        )
        """;
        String createTransaction =
        """
        CREATE TABLE TRANSACTION (
            T_ID INTEGER NOT NULL PRIMARY KEY CHECK(T_ID >= 1 AND T_ID <= 9999),
            P_ID INTEGER NOT NULL CHECK(P_ID >= 1 AND P_ID <= 999),
            S_ID INTEGER NOT NULL CHECK(S_ID >= 1 AND S_ID <= 99),
            TRANSACTION_DATE DATE NOT NULL,
            CONSTRAINT fk_part FOREIGN KEY (P_ID) REFERENCES PART(P_ID),
            CONSTRAINT fk_salesperson FOREIGN KEY (S_ID) REFERENCES SALESPERSON(S_ID)
        )
        """;
        try {
            System.out.print("Processing...");
            stmt.executeQuery(createCategory);
            stmt.executeQuery(createManufacturer);
            stmt.executeQuery(createPart);
            stmt.executeQuery(createSalesperson);
            stmt.executeQuery(createTransaction);
            System.out.println("Done! Database is initialized!");
        } catch(Exception e) {
            System.out.println("Something went wrong in create" + e);
        }
    }

    private void delete() {
        try {
            System.out.print("Processing...");
            stmt.executeQuery("DROP TABLE category CASCADE CONSTRAINTS");
            stmt.executeQuery("DROP TABLE manufacturer CASCADE CONSTRAINTS");
            stmt.executeQuery("DROP TABLE part CASCADE CONSTRAINTS");
            stmt.executeQuery("DROP TABLE salesperson CASCADE CONSTRAINTS");
            stmt.executeQuery("DROP TABLE transaction CASCADE CONSTRAINTS");
            System.out.println("Done! Database is removed!");
        } catch(Exception e) {
            System.out.println("Something went wrong in delete" + e);
        }
    }

    private void loadCategory(File file) {
        String thisLine;
        try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((thisLine = br.readLine()) != null) {
            String[] arg = thisLine.split("\t");
            String insertAdministrator = String.format("INSERT INTO CATEGORY VALUES('%s', '%s')", arg[0], arg[1]);
            System.out.println(insertAdministrator);
            stmt.executeQuery(insertAdministrator);
        }         
        br.close();
        } catch (Exception e){
            System.err.println("Error: " + e);
        }
    }

    private void loadManufacturer(File file) {
        String thisLine;
        try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((thisLine = br.readLine()) != null) {
            String[] arg = thisLine.split("\t");
            String insertManufacturer = String.format("INSERT INTO MANUFACTURER VALUES('%s', '%s', '%s', '%s')", arg[0], arg[1], arg[2], arg[3]);
            System.out.println(insertManufacturer);
            stmt.executeQuery(insertManufacturer);
        }         
        br.close();
        } catch (Exception e){
            System.err.println("Error: " + e);
        }
    }

    private void loadPart(File file) {
        String thisLine;
        try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((thisLine = br.readLine()) != null) {
            String[] arg = thisLine.split("\t");
            String insertPart = String.format("INSERT INTO PART VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')", arg[0], arg[1], arg[2], arg[3], arg[4], arg[5], arg[6]);
            System.out.println(insertPart);
            stmt.executeQuery(insertPart);
        }         
        br.close();
        } catch (Exception e){
            System.err.println("Error: " + e);
        }
    }

    private void loadSalesperson(File file) {
        String thisLine;
        try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((thisLine = br.readLine()) != null) {
            String[] arg = thisLine.split("\t");
            String insertSalesperson = String.format("INSERT INTO SALESPERSON VALUES('%s', '%s', '%s', '%s', '%s')", arg[0], arg[1], arg[2], arg[3], arg[4]);
            System.out.println(insertSalesperson);
            stmt.executeQuery(insertSalesperson);
        }         
        br.close();
        } catch (Exception e){
            System.err.println("Error: " + e);
        }
    }

    private void loadTransaction(File file) {
        String thisLine;
        try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((thisLine = br.readLine()) != null) {
            String[] arg = thisLine.split("\t");
            String insertTransaction = String.format("INSERT INTO TRANSACTION VALUES('%s', '%s', '%s', TO_DATE('%s', 'DD/MM/YYYY'))", arg[0], arg[1], arg[2], arg[3]);
            System.out.println(insertTransaction);
            stmt.executeQuery(insertTransaction);
        }         
        br.close();
        } catch (Exception e){
            System.err.println("Error: " + e);
        }
    }

    private void load(Scanner scanner) {
        try {
            System.out.println("Type in the source data folder path: ");// ./project-files/sample_data
            String path = "";
            path = scanner.nextLine();
            path = scanner.nextLine();
            System.out.println("path:" + path);
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            File categoryFile=null, manufacturerFile=null, salespersonFile=null, partFile=null, transactionFile=null;
            for(int i=0; i < listOfFiles.length; i++) {
                if(listOfFiles[i].getName().compareTo("category.txt") == 0) categoryFile = listOfFiles[i];
                if(listOfFiles[i].getName().compareTo("manufacturer.txt") == 0) manufacturerFile = listOfFiles[i];
                if(listOfFiles[i].getName().compareTo("salesperson.txt") == 0) salespersonFile = listOfFiles[i];
                if(listOfFiles[i].getName().compareTo("part.txt") == 0) partFile = listOfFiles[i];
                if(listOfFiles[i].getName().compareTo("transaction.txt") == 0) transactionFile = listOfFiles[i];
            }
            loadCategory(categoryFile);
            loadManufacturer(manufacturerFile);
            loadSalesperson(salespersonFile);
            loadPart(partFile);
            loadTransaction(transactionFile);
            System.out.println("Done! Data is inputted to the database!");
        } catch(Exception e) {
            System.out.println("Something went wrong in load: " + e);
        }

    }

    private void show(Scanner scanner) {
        Utility utility = new Utility(stmt);
        boolean found = false;
        System.out.print("Which table would you like to show: ");
        while(!found) {
            String table = scanner.nextLine();
            String[] validTable = {"category", "manufacturer", "salesperson", "part", "transaction"};
            for(int i=0; i<5; i++) {
                if(table.compareToIgnoreCase(validTable[i]) == 0) {
                    System.out.println("Content from table " + validTable[i] + ":");
                    utility.printQuery("SELECT * FROM " + table);
                    found = true;
                }
            }
            if(!found) System.out.print("Invalid table! Enter again(valid example: category): ");
        }
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Utility utility = new Utility(stmt);
        String[] options = 
        {
            "Create all tables",
            "Delete all tables",
            "Load from datafile",
            "Show content of a table",
            "Return to the main menu"
        };
        boolean running = true;
        while(running) {
            int choice = utility.prompt(scanner, "administrator menu", options);
            switch (choice) {
                case 1:
                    create();
                    break; 
                case 2:
                    delete();
                    break; 
                case 3:
                    load(scanner);
                    break;
                case 4:
                    show(scanner);
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
