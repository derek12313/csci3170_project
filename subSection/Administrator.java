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
        String category = 
        """
        CREATE TABLE CATEGORY (
            CATEGORY_ID INTEGER NOT NULL PRIMARY KEY CHECK(CATEGORY_ID >= 1 AND CATEGORY_ID <= 9),
            CATEGORY_NAME VARCHAR(20) NOT NULL
        )
        """;
        String manufacturer =
        """
        CREATE TABLE MANUFACTURER (
            MANUFACTURER_ID INTEGER NOT NULL PRIMARY KEY CHECK(MANUFACTURER_ID >= 1 AND MANUFACTURER_ID <= 999),
            MANUFACTURER_NAME VARCHAR(20) NOT NULL,
            MANUFACTURER_ADDRESS VARCHAR(50) NOT NULL,
            MANUFACTURER_NUMBER INTEGER NOT NULL CHECK(MANUFACTURER_NUMBER >= 10000000 AND MANUFACTURER_NUMBER <= 99999999)
        )
        """;
        String part =
        """
        CREATE TABLE PART (
            PART_ID INTEGER NOT NULL PRIMARY KEY CHECK(PART_ID >= 1 AND PART_ID <= 999),
            PART_NAME VARCHAR(20) NOT NULL,
            PART_PRICE INTEGER NOT NULL CHECK(PART_PRICE >= 1 AND PART_PRICE <= 99999),
            PART_MANUFACTURER_ID INTEGER NOT NULL CHECK(PART_MANUFACTURER_ID >= 1 AND PART_MANUFACTURER_ID <= 99),
            PART_CATEGORY_ID INTEGER NOT NULL CHECK(PART_CATEGORY_ID >= 1 AND PART_CATEGORY_ID <= 9),
            PART_WARRANTY INTEGER NOT NULL CHECK(PART_WARRANTY >= 1 AND PART_WARRANTY <= 99),
            PART_AVAILABLE_QUANTITY INTEGER NOT NULL CHECK(PART_AVAILABLE_QUANTITY >= 0 AND PART_AVAILABLE_QUANTITY <= 99)

        )
        """;
        String salesperson =
        """
        CREATE TABLE SALESPERSON (
            SALESPERSON_ID INTEGER NOT NULL PRIMARY KEY CHECK(SALESPERSON_ID >= 1 AND SALESPERSON_ID <= 99),
            SALESPERSON_NAME VARCHAR(20) NOT NULL,
            SALESPERSON_ADDRESS VARCHAR(50) NOT NULL,
            SALESPERSON_PHONE_NUMBER INTEGER NOT NULL CHECK(SALESPERSON_PHONE_NUMBER >= 10000000 AND SALESPERSON_PHONE_NUMBER <= 99),
            SALESPERSON_EXPERIENCE INTEGER NOT NULL CHECK(SALESPERSON_EXPERIENCE >= 1 AND SALESPERSON_EXPERIENCE <= 9)
        )
        """;
        String transaction =
        """
        CREATE TABLE TRANSACTION (
            TRANSACTION_ID INTEGER NOT NULL PRIMARY KEY CHECK(TRANSACTION_ID >= 1 AND TRANSACTION_ID <= 9999),
            PART_ID INTEGER NOT NULL CHECK(PART_ID >= 1 AND PART_ID <= 999),
            SALESPERSON_ID INTEGER NOT NULL CHECK(SALESPERSON_ID >= 1 AND SALESPERSON_ID <= 99),
            TRANSACTION_DATE DATE NOT NULL
        )
        """;
        try {
            //stmt.executeQuery(category);
            //stmt.executeQuery(manufacturer);
            //stmt.executeQuery(part);
            //stmt.executeQuery(salesperson);
            //stmt.executeQuery(transaction);
            System.out.println("Table added successfully \n");
        }
        catch(Exception e) {
            System.out.println("Something went wrong in create\n" + e);
        }
    }

    public void execute() {
        System.out.print("executing admin\n");
        create();
    }

}
