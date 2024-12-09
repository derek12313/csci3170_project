package subSection;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Administrator {
    private Statement stmt;
    public Administrator(Statement s) {
        stmt = s;
    }
    
    public void execute() {
        System.out.print("executing admin\n");
    }
}
