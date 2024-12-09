import java.sql.*;
import java.util.Scanner;
import java.io.*;
import subSection.*;;

class main {
    public static void main(String args[]) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.print("connecting to database\n");
            conn = DriverManager.getConnection
            (
            "jdbc:oracle:thin:@db18.cse.cuhk.edu.hk:1521/oradb.cse.cuhk.edu.hk",
            "h007",
            "chesEvOc"
            );
            System.out.print("connected to database\n");
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.err.println("Something went wrong:\n" + e + "\n");
        }
        Administrator administrator = new Administrator(stmt);
        administrator.execute();
            
    }
}