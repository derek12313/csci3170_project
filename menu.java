import java.sql.*;
import java.util.Scanner;
import java.io.*;

class menu {
    public static void main(String args[]) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.print("connecting to database\n");
            conn = DriverManager.getConnection(
            //"jdbc:mysql://projgw.cse.cuhk.edu.hk:2712/h007?autoReconnect=true&useSSL=false"
                "jdbc:oracle:thin:@db18.cse.cuhk.edu.hk:1521/oradb.cse.cuhk.edu.hk"
                , "h007"
                , "chesEvOc"
            );
            System.out.print("connected to database\n");
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.err.println("Something went wrong:\n" + e + "\n");
        }

        
       
    }
}