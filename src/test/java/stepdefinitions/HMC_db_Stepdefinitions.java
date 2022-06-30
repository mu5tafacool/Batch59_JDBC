package stepdefinitions;

import io.cucumber.java.en.Given;

import java.sql.*;

public class HMC_db_Stepdefinitions {

    String url = "jdbc:sqlserver://184.168.194.58:1433;databaseName=hotelmycamp ; " +
            "user=techproed;password=P2s@rt65";
    String username = "techproed";
    String password = "P2s@rt65";

    Connection connection; // Database'e baglanmamizi saglar
    Statement statement; // Query calistirmamizi sagliyor
    ResultSet resultSet; // query sonucunda donen sonucu store etmemize yariyor


    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }

    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        String query = "SELECT " + field + " FROM " + table;
        resultSet = statement.executeQuery(query); //sorguyu calistir ve gelen query i yani resultu resultset e atadik
    }

    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {

        // resultset iterator gibi calisir
        //
        resultSet.first();
        System.out.println(resultSet.getString("Price"));// 225.0000 ik degeri yazdirma
        resultSet.next(); // iterator'a benzer sekilde calisir
        // next()'u imleci bir sonraki degerin yanina goturur
        // bize true veya false doner
        System.out.println(resultSet.getString("Price")); // 4000.0000

        System.out.println(resultSet.next()); // true
        System.out.println(resultSet.getString("Price")); // 445.0000
        // next() kullanilirken cok dikkatli olmaliyiz
        // cunku nerede olursa olsun imleci bir sonraki elemente gecirecektir.

        System.out.println("===============Liste===============");
        resultSet.absolute(0); //0 row kesinliginden siralama
        int sira = 1;
        while (resultSet.next()) { //resultSet.next() bunun sonucu bana true donuyor ve true olana kadar calisiyor son elemente kadar bak
            System.out.println(sira + ". kayt : " + resultSet.getString("Price"));
            sira++;
            /*
            * 1. kayt : 225.0000
              2. kayt : 4000.0000
              *
              *
            * */
        }
    }

}
