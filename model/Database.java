package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
   private static final String URL = "jdbc:mysql://localhost:3306/atm_db_final";
   private static final String USER = "root";
   private static final String PASSWORD = "psnl@8904";

   public Database() {
   }

   public static Connection connect() throws SQLException {
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db_final", "root", "psnl@8904");
   }

   public static void main(String[] var0) {
      try {
         Connection var1 = connect();

         try {
            if (var1 != null) {
               System.out.println("✅ Connected to MySQL successfully!");
            }
         } catch (Throwable var5) {
            if (var1 != null) {
               try {
                  var1.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (var1 != null) {
            var1.close();
         }
      } catch (SQLException var6) {
         System.out.println("❌ Database connection failed!");
         var6.printStackTrace();
      }

   }
}
