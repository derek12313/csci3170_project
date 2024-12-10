package subSection;
import java.sql.*;


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
}
