package com.example.dbtest;

import java.sql.*;
import java.util.ArrayList;

public class SQL {

    String url = "jdbc:postgresql://10.10.104.136:5432/alko_pavel?user=postgres&password=123";
    Connection connection;

    public SQL()  {
        connect();
    }
    public SQL(String url)  {
        connect(url);
    }

    public void connect() {
        connect(url);
    }

    public void connect(String url) {
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Удалось");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }
    public ArrayList<Liquer> loadLiquer() throws SQLException {
        ArrayList<Liquer> liqList = new ArrayList<>();
        Statement st = connection.createStatement();
        String query = "select * from liquer l; ";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            Liquer l = new Liquer(rs.getString("label"),
                    rs.getString("country"),
                    rs.getString("category"),
                    rs.getInt("exposure"),
                    rs.getFloat("strength"),
                    rs.getInt("sugar"));
            liqList.add(l);
        }
        return liqList;
    }

    public boolean pushToDb(String label, String country, String category, int exposure, float strength, int sugar) throws SQLException {
//        INSERT INTO public.liquer ("label",strength,country,exposure,sugar,category)
//        VALUES ('J Daniel',40.0,'America',3,10,'Strong');
        String query = "insert into public.liquer (\"label\",strength,country,exposure,sugar,category) " +
                "values ('"+label+"', "+strength+", '"+country+"', "+exposure+", "+sugar+", '"+category+"')";
        Statement st = connection.createStatement();

        return st.execute(query);

    }
}
