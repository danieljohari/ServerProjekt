import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class DBSearch {
    public static Connection connection;
    public static String mail;
    public static String password;
    public static String X;




    //static Connector connector = new Connector();


    public static void main(String[] args) throws IOException {


        connection = Connector.getConnection();
        findUser(mail, password);

    }

    private static String[] findUser(String mail, String password) {
        String[] result = new String[2];
        String[] mailOgPass;
        String mailsql=null;
        String passwordsql=null;

        try {
            // System.out.println("indtast mail: ");
            mailOgPass = ServerProjekt.sendToKlient();
            mail = mailOgPass[0];
            mail = mail.replace("%40", "@");
            password = mailOgPass[1];
            String sql = "select * from sys.login where mail= " + "'" + mail + "'" + "and password =" + "'" + password + "'" + "limit 1";
            Statement statement = connection.createStatement();

            for (ResultSet rs = statement.executeQuery(sql);
                 rs.next();
                 passwordsql = rs.getString("Password")) {
                System.out.println("mail: " + rs.getString("Mail") + "\npassword:" + rs.getString("Password") + "\n");
                mailsql = rs.getString("Mail");
            }


            if (mail.equals(mailsql) && password.equals(passwordsql)) {
                System.out.println("jeg er blevet dum i hovedet");
                X = "Velkommen til:";
                ServerProjekt.sendFromServer(X);


            }
            connection.close();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;

    }


    public static void insertIntoRemote() {


    }

    public static ArrayList getRemoteData() {

        return null;
    }

    /*

    private static String findbruger(String mail, String Pass) {
        String UserCPR = null;
        String sqlFindUser = "\n" + "select idloginoplysninger, cpr, mail from PatientPortal.loginoplysninger where cpr password = 'Johari' and mail = 'daniel@dtu.dk'; ";

       // ResultSet Rs = statement.e

    }

     */
}
