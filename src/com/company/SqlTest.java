package com.company;

import java.sql.*;
import java.util.Scanner;

public class SqlTest {

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:postgresql://localhost/";
        String user = "postgres";
        String password = "1221zxc151";

        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("SQL Programming Test");

            System.out.println("Connecting PostgreSQL database");
            // JDBC를 이용해 PostgreSQL 서버 및 데이터베이스 연결
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            //rs = st.executeQuery("SELECT VERSION()");

            st.executeUpdate("drop Table ParentOf cascade");
            st.executeUpdate("drop Table Flight");
            st.executeUpdate("drop Table Sales");
            st.executeUpdate("drop Table Customer");
            st.executeUpdate("drop Table Item");
            st.executeUpdate("drop Table Store");


            // 3개 테이블 생성: Create table문 이용
            st.executeUpdate("create table ParentOf(parent varchar(20), child varchar(20));");
            st.executeUpdate("insert into ParentOf values('Alice', 'Carol');");
            st.executeUpdate("insert into ParentOf values('Bob', 'Carol');");
            st.executeUpdate("insert into ParentOf values('Carol', 'Dave');");
            st.executeUpdate("insert into ParentOf values('Carol', 'George');");
            st.executeUpdate("insert into ParentOf values('Dave', 'Mary');");
            st.executeUpdate("insert into ParentOf values('Eve', 'Mary');");
            st.executeUpdate("insert into ParentOf values('Mary', 'Frank');");

            st.executeUpdate("create table Flight(orig varchar(20), dest varchar(20), airline varchar(20), cost int);");
            st.executeUpdate("insert into Flight values('A', 'ORD', 'United', 200);");
            st.executeUpdate("insert into Flight values('ORD', 'B', 'American', 100);");
            st.executeUpdate("insert into Flight values('A', 'PHX', 'Southwest', 25);");
            st.executeUpdate("insert into Flight values('PHX', 'LAS', 'Southwest', 30);");
            st.executeUpdate("insert into Flight values('LAS', 'CMH', 'Frontier', 60);");
            st.executeUpdate("insert into Flight values('CMH', 'B', 'Frontier', 60);");
            st.executeUpdate("insert into Flight values('A', 'B', 'JetBlue', 195);");

            st.executeUpdate("create table Sales(storeID varchar(20), itemID varchar(20), custID varchar(20), price int);");
            st.executeUpdate("create table Customer(custID varchar(20), cName varchar(20), gender char, age int);");
            st.executeUpdate("create table Item(itemID varchar(20), category varchar(20), color varchar(20));");
            st.executeUpdate("create table Store(storeID varchar(20), city varchar(20), county varchar(20), state char(2));");

            st.executeUpdate("insert into Customer values('cust1', 'Amy', 'F', 20);");
            st.executeUpdate("insert into Customer values('cust2', 'Bob', 'M', 21);");
            st.executeUpdate("insert into Customer values('cust3', 'Craig', 'M', 25);");
            st.executeUpdate("insert into Customer values('cust4', 'Doris', 'F', 22);");

            st.executeUpdate("insert into Item values('item1', 'Tshirt', 'blue');");
            st.executeUpdate("insert into Item values('item2', 'Jacket', 'blue');");
            st.executeUpdate("insert into Item values('item3', 'Tshirt', 'red');");
            st.executeUpdate("insert into Item values('item4', 'Jacket', 'blue');");
            st.executeUpdate("insert into Item values('item5', 'Jacket', 'red');");

            st.executeUpdate("insert into Store values('store1', 'Palo Alto', 'Santa Clara', 'CA');");
            st.executeUpdate("insert into Store values('store2', 'Mountain View', 'Santa Clara', 'CA');");
            st.executeUpdate("insert into Store values('store3', 'Menlo Park', 'San Mateo', 'CA');");
            st.executeUpdate("insert into Store values('store4', 'Belmont', 'San Mateo', 'CA');");
            st.executeUpdate("insert into Store values('store5', 'Seattle', 'King', 'CA');");
            st.executeUpdate("insert into Store values('store6', 'Redmond', 'King', 'CA');");

            // 3개 테이블에 튜플 생성: Insert문 이용
            st.executeUpdate("insert into Sales values('store1', 'item1', 'cust1', 10);");
            st.executeUpdate("insert into Sales values('store1', 'item1', 'cust2', 15);");
            st.executeUpdate("insert into Sales values('store1', 'item1', 'cust3', 20);");
            st.executeUpdate("insert into Sales values('store1', 'item1', 'cust3', 25);");
            st.executeUpdate("insert into Sales values('store1', 'item2', 'cust1', 30);");
            st.executeUpdate("insert into Sales values('store1', 'item2', 'cust2', 35);");
            st.executeUpdate("insert into Sales values('store1', 'item2', 'cust3', 40);");
            st.executeUpdate("insert into Sales values('store1', 'item2', 'cust2', 45);");
            st.executeUpdate("insert into Sales values('store1', 'item3', 'cust1', 50);");
            st.executeUpdate("insert into Sales values('store1', 'item3', 'cust1', 55);");
            st.executeUpdate("insert into Sales values('store2', 'item3', 'cust2', 60);");
            st.executeUpdate("insert into Sales values('store2', 'item1', 'cust2', 65);");
            st.executeUpdate("insert into Sales values('store2', 'item2', 'cust3', 70);");
            st.executeUpdate("insert into Sales values('store2', 'item2', 'cust3', 75);");
            st.executeUpdate("insert into Sales values('store2', 'item2', 'cust4', 80);");
            st.executeUpdate("insert into Sales values('store2', 'item2', 'cust4', 85);");
            st.executeUpdate("insert into Sales values('store2', 'item2', 'cust1', 90);");
            st.executeUpdate("insert into Sales values('store2', 'item2', 'cust1', 95);");
            st.executeUpdate("insert into Sales values('store2', 'item2', 'cust1', 95);");
            st.executeUpdate("insert into Sales values('store2', 'item2', 'cust2', 90);");

            st.executeUpdate("insert into Sales values('store3', 'item2', 'cust2', 85);");
            st.executeUpdate("insert into Sales values('store3', 'item2', 'cust2', 80);");
            st.executeUpdate("insert into Sales values('store3', 'item2', 'cust3', 75);");
            st.executeUpdate("insert into Sales values('store3', 'item2', 'cust3', 70);");
            st.executeUpdate("insert into Sales values('store3', 'item3', 'cust3', 65);");
            st.executeUpdate("insert into Sales values('store3', 'item3', 'cust2', 60);");
            st.executeUpdate("insert into Sales values('store3', 'item3', 'cust2', 55);");
            st.executeUpdate("insert into Sales values('store3', 'item3', 'cust2', 50);");
            st.executeUpdate("insert into Sales values('store3', 'item3', 'cust3', 45);");
            st.executeUpdate("insert into Sales values('store3', 'item3', 'cust3', 40);");

            st.executeUpdate("insert into Sales values('store4', 'item3', 'cust1', 35);");
            st.executeUpdate("insert into Sales values('store4', 'item3', 'cust1', 30);");
            st.executeUpdate("insert into Sales values('store4', 'item3', 'cust2', 25);");
            st.executeUpdate("insert into Sales values('store4', 'item3', 'cust2', 20);");
            st.executeUpdate("insert into Sales values('store4', 'item3', 'cust2', 15);");
            st.executeUpdate("insert into Sales values('store4', 'item3', 'cust2', 10);");
            st.executeUpdate("insert into Sales values('store4', 'item4', 'cust3', 15);");
            st.executeUpdate("insert into Sales values('store4', 'item4', 'cust3', 20);");
            st.executeUpdate("insert into Sales values('store4', 'item4', 'cust3', 25);");
            st.executeUpdate("insert into Sales values('store4', 'item4', 'cust3', 30);");

            st.executeUpdate("insert into Sales values('store5', 'item4', 'cust4', 35);");
            st.executeUpdate("insert into Sales values('store5', 'item4', 'cust4', 40);");
            st.executeUpdate("insert into Sales values('store5', 'item4', 'cust4', 45);");
            st.executeUpdate("insert into Sales values('store5', 'item4', 'cust4', 50);");
            st.executeUpdate("insert into Sales values('store5', 'item4', 'cust1', 55);");
            st.executeUpdate("insert into Sales values('store5', 'item4', 'cust1', 60);");
            st.executeUpdate("insert into Sales values('store5', 'item4', 'cust1', 65);");
            st.executeUpdate("insert into Sales values('store5', 'item4', 'cust2', 70);");
            st.executeUpdate("insert into Sales values('store5', 'item5', 'cust2', 75);");
            st.executeUpdate("insert into Sales values('store5', 'item5', 'cust2', 80);");

            st.executeUpdate("insert into Sales values('store6', 'item5', 'cust3', 85);");
            st.executeUpdate("insert into Sales values('store6', 'item5', 'cust3', 90);");
            st.executeUpdate("insert into Sales values('store6', 'item2', 'cust3', 95);");
            st.executeUpdate("insert into Sales values('store6', 'item2', 'cust4', 90);");
            st.executeUpdate("insert into Sales values('store6', 'item3', 'cust4', 85);");
            st.executeUpdate("insert into Sales values('store6', 'item3', 'cust4', 80);");
            st.executeUpdate("insert into Sales values('store6', 'item4', 'cust4', 75);");
            st.executeUpdate("insert into Sales values('store6', 'item4', 'cust4', 70);");
            st.executeUpdate("insert into Sales values('store6', 'item5', 'cust4', 65);");
            st.executeUpdate("insert into Sales values('store6', 'item5', 'cust4', 60);");


            System.out.println("Recursive test 1");
            // Query 1을 실행: Select문 이용
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            // Query 처리 결과는 적절한 Print문을 이용해 Display
            rs = st.executeQuery("with recursive\n" +
                    "Ancestor(a,d) as (select parent as a, child as d from ParentOf\n" +
                    "union\nselect Ancestor.a, ParentOf.child as d\n" +
                    "from Ancestor, ParentOf\n" +
                    "where Ancestor.d = ParentOf.parent)\n" +
                    "select a from Ancestor where d = 'Mary'");

            if(rs != null){
                System.out.println("---------------a---------------");
                while(rs.next()) {
                    String a = rs.getString("a");
                    System.out.println("a : "+a);
                }
                rs = null;
            }


            System.out.println("Recursive test 2");
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            // Query 2 ~ Query 6에 대해 Query 1과 같은 방식으로 실행: Select문 이용
            // Query 처리 결과는 적절한 Print문을 이용해 Display

            rs = st.executeQuery("with recursive\n" +
                    "Route(orig ,dest, total) as\n" +
                    "(select orig, dest, cost as total from Flight\n" +
                    "union\nselect R.orig, F.dest, cost+total as total\n" +
                    "from Route R, Flight F\n" +
                    "where R.dest = F.orig)" +
                    "select * from Route\n" +
                    "where orig = 'A' and dest = 'B'");

            if(rs != null){
                System.out.println("---------------Route---------------");
                while(rs.next()) {
                    String orig = rs.getString("orig");
                    String dest = rs.getString("dest");
                    int total = rs.getInt("total");
                    System.out.println("orig : "+orig+" / dest : "+dest+" / total : "+total);
                }
                rs = null;
            }



            System.out.println("Recursive test 3");
            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();

            rs = st.executeQuery("with recursive\n" +
                    "ToB(orig, total) as\n" +
                    "(select orig, cost as total from Flight where dest = 'B'\n" +
                    "union\nselect F.orig, cost+total as total\n" +
                    "from Flight F, ToB TB\n" +
                    "where F.dest = TB.orig)" +
                    "select min(total) from ToB where orig = 'A'");

            if(rs != null){
                System.out.println("---------------min(total)---------------");
                while(rs.next()) {
                    int min = rs.getInt("min");
                    System.out.println("min : "+min);
                }
                rs = null;
            }


            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            System.out.println("OLAP test 1");
            rs = st.executeQuery("select storeID, itemID, custID, sum(price)\n" +
                    "from Sales\n" +
                    "group by cube(storeID, itemID, custID);");

            if(rs != null){
                System.out.println("---------------cube---------------");
                while(rs.next()) {
                    String storeID = rs.getString("storeID");
                    String itemID = rs.getString("itemID");
                    String custID = rs.getString("custID");
                    int sumPrice = rs.getInt("sum");
                    System.out.println("storeID : " + storeID + " / itemID : " + itemID + " / custID: " +custID + " / sum(price): " + sumPrice );
                }
                rs = null;
            }

            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            System.out.println("OLAP test 2");
            rs = st.executeQuery("select storeID, itemID, custID, sum(price)\n" +
                    "from Sales F\n" +
                    "group by cube(storeID, (itemID, custID));");

            if(rs != null){
                System.out.println("---------------cube---------------");
                while(rs.next()) {
                    String storeID = rs.getString("storeID");
                    String itemID = rs.getString("itemID");
                    String custID = rs.getString("custID");
                    int sumPrice = rs.getInt("sum");
                    System.out.println("storeID : " + storeID + " / itemID : " + itemID + " / custID: " +custID + " / sum(price): " + sumPrice );
                }
                rs = null;
            }

            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            System.out.println("OLAP test 3");
            rs = st.executeQuery("select storeID, itemID, custID, sum(price)\n" +
                    "from Sales F\n" +
                    "group by rollup(storeID, itemID, custID);");

            if(rs != null){
                System.out.println("---------------rollup---------------");
                while(rs.next()) {
                    String storeID = rs.getString("storeID");
                    String itemID = rs.getString("itemID");
                    String custID = rs.getString("custID");
                    int sumPrice = rs.getInt("sum");
                    System.out.println("storeID : " + storeID + " / itemID : " + itemID + " / custID: " +custID + " / sum(price): " + sumPrice );
                }
                rs = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
            }

        }
    }
}
