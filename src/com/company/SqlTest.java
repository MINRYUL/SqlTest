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

            st.executeUpdate("drop Table apply cascade");
            st.executeUpdate("drop Table college");
            st.executeUpdate("drop Table student");

            System.out.println("Creating College, Student, Apply relations");
            // 3개 테이블 생성: Create table문 이용
            st.executeUpdate("create table College(cName varchar(20), state varchar(20), enrollment int);");
            st.executeUpdate("create table Student(sID int, sName varchar(20), GPA numeric(2,1), sizeHS int);");
            st.executeUpdate("create table Apply(sID int, cName varchar(20), major varchar(20), decision char);");

            System.out.println("Inserting tuples to College, Student, Apply relations");
            // 3개 테이블에 튜플 생성: Insert문 이용
            st.executeUpdate("insert into College values ('Stanford', 'CA', 15000);");
            st.executeUpdate("insert into College values ('Berkeley', 'CA', 36000);");
            st.executeUpdate("insert into College values ('MIT', 'MA', 10000);");
            st.executeUpdate("insert into College values ('Cornell', 'NY', 21000);");

            st.executeUpdate("insert into Student values (123, 'Amy', 3.9, 1000);");
            st.executeUpdate("insert into Student values (234, 'Bob', 3.6, 1500);");
            st.executeUpdate("insert into Student values (345, 'Craig', 3.5, 500);");
            st.executeUpdate("insert into Student values (456, 'Doris', 3.9, 1000);");
            st.executeUpdate("insert into Student values (567, 'Edward', 2.9, 2000);");
            st.executeUpdate("insert into Student values (678, 'Fay', 3.8, 200);");
            st.executeUpdate("insert into Student values (789, 'Gary', 3.4, 800);");
            st.executeUpdate("insert into Student values (987, 'Helen', 3.7, 800);");
            st.executeUpdate("insert into Student values (876, 'Irene', 3.9, 400);");
            st.executeUpdate("insert into Student values (765, 'Jay', 2.9, 1500);");
            st.executeUpdate("insert into Student values (654, 'Amy', 3.9, 1000);");
            st.executeUpdate("insert into Student values (543, 'Craig', 3.4, 2000);");

            st.executeUpdate("insert into Apply values (123, 'Stanford', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (123, 'Stanford', 'EE', 'N');");
            st.executeUpdate("insert into Apply values (123, 'Berkeley', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (123, 'Cornell', 'EE', 'Y');");
            st.executeUpdate("insert into Apply values (234, 'Berkeley', 'biology', 'N');");
            st.executeUpdate("insert into Apply values (345, 'MIT', 'bioengineering', 'Y');");
            st.executeUpdate("insert into Apply values (345, 'Cornell', 'bioengineering', 'N');");
            st.executeUpdate("insert into Apply values (345, 'Cornell', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (345, 'Cornell', 'EE', 'N');");
            st.executeUpdate("insert into Apply values (678, 'Stanford', 'history', 'Y');");
            st.executeUpdate("insert into Apply values (987, 'Stanford', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (987, 'Berkeley', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (876, 'Stanford', 'CS', 'N');");
            st.executeUpdate("insert into Apply values (876, 'MIT', 'biology', 'Y');");
            st.executeUpdate("insert into Apply values (876, 'MIT', 'marine biology', 'N');");
            st.executeUpdate("insert into Apply values (765, 'Stanford', 'history', 'Y');");
            st.executeUpdate("insert into Apply values (765, 'Cornell', 'history', 'N');");
            st.executeUpdate("insert into Apply values (765, 'Cornell', 'psychology', 'Y');");
            st.executeUpdate("insert into Apply values (543, 'MIT', 'CS', 'N');");

            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();

            System.out.println("Query 1");
            // Query 1을 실행: Select문 이용
            // Query 처리 결과는 적절한 Print문을 이용해 Display
            st.executeUpdate("create or replace function test() returns trigger as $$\n" +
                    "begin\ninsert into Apply values(New.sID, 'Stanford', 'geology', null);\n" +
                    "insert into Apply values(New.sID, 'MIT', 'biology', null);\nreturn New;\nend;\n$$\n" +
                    "language 'plpgsql';\n" +
                    "create trigger R1\n" +
                    "after insert on Student\n" +
                    "for each row\n" +
                    "when (New.GPA > 3.3 and New.GPA <= 3.6)\n" +
                    "execute procedure test();");

            st.executeUpdate("insert into Student values ('111', 'Kevin', 3.5, 1000);");
            st.executeUpdate("insert into Student values ('222', 'Lori', 3.8, 1000);");
            rs = st.executeQuery("select * from Student");
            if(rs != null){
                System.out.println("---------------STUDENT---------------");
                while(rs.next()) {
                    int sid = rs.getInt("sid");
                    String cName = rs.getString("sname");
                    float gpa = rs.getFloat("gpa");
                    int sizehs = rs.getInt("sizehs");
                    System.out.println("sid : "+sid+" cName : "+cName+" gpa : "+gpa+" sizehs : "+sizehs);
                }
                rs = null;
            }

            rs = st.executeQuery("select * from Apply");
            if(rs != null){
                System.out.println("---------------APPLY---------------");
                while(rs.next()) {
                    int sid = rs.getInt("sid");
                    String cName = rs.getString("cname");
                    String major = rs.getString("major");
                    String decision = rs.getString("decision");
                    System.out.println("sid : "+sid+" cName : "+cName+" major : "+major+" decision : "+decision);
                }
                rs = null;
            }

            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            // Query 2 ~ Query 6에 대해 Query 1과 같은 방식으로 실행: Select문 이용
            // Query 처리 결과는 적절한 Print문을 이용해 Display

            System.out.println("Query 2");
            st.executeUpdate("create or replace function test() returns trigger as $$\n" +
                    "begin\nupdate Apply\n" +
                    "set cName = New.cName\nwhere cName = Old.cName;\nreturn New;\nend;\n$$\n" +
                    "language 'plpgsql';\n" +
                    "create trigger R3\n" +
                    "after update of cName on College\n" +
                    "for each row\n" +
                    "execute procedure test();");

            st.executeUpdate("update College set cName = 'The Farm' where cName = 'Stanford';");
            st.executeUpdate("update College set cName = 'Bezerkeley' where cName = 'Berkeley';");
            rs = st.executeQuery("select * from College");
            if(rs != null){
                System.out.println("---------------College---------------");
                while(rs.next()) {
                    String cName = rs.getString("cname");
                    String state = rs.getString("state");
                    String enrollment = rs.getString("enrollment");
                    System.out.println("cName : "+cName+" state : "+state+" enrollment : "+enrollment);
                }
                rs = null;
            }

            st.executeUpdate("drop Table apply cascade");
            st.executeUpdate("drop Table college");
            st.executeUpdate("drop Table student");

            System.out.println("Creating College, Student, Apply relations");
            // 3개 테이블 생성: Create table문 이용
            st.executeUpdate("create table College(cName varchar(20), state varchar(20), enrollment int);");
            st.executeUpdate("create table Student(sID int, sName varchar(20), GPA numeric(2,1), sizeHS int);");
            st.executeUpdate("create table Apply(sID int, cName varchar(20), major varchar(20), decision char);");

            System.out.println("Inserting tuples to College, Student, Apply relations");
            // 3개 테이블에 튜플 생성: Insert문 이용
            st.executeUpdate("insert into College values ('Stanford', 'CA', 15000);");
            st.executeUpdate("insert into College values ('Berkeley', 'CA', 36000);");
            st.executeUpdate("insert into College values ('MIT', 'MA', 10000);");
            st.executeUpdate("insert into College values ('Cornell', 'NY', 21000);");

            st.executeUpdate("insert into Student values (123, 'Amy', 3.9, 1000);");
            st.executeUpdate("insert into Student values (234, 'Bob', 3.6, 1500);");
            st.executeUpdate("insert into Student values (345, 'Craig', 3.5, 500);");
            st.executeUpdate("insert into Student values (456, 'Doris', 3.9, 1000);");
            st.executeUpdate("insert into Student values (567, 'Edward', 2.9, 2000);");
            st.executeUpdate("insert into Student values (678, 'Fay', 3.8, 200);");
            st.executeUpdate("insert into Student values (789, 'Gary', 3.4, 800);");
            st.executeUpdate("insert into Student values (987, 'Helen', 3.7, 800);");
            st.executeUpdate("insert into Student values (876, 'Irene', 3.9, 400);");
            st.executeUpdate("insert into Student values (765, 'Jay', 2.9, 1500);");
            st.executeUpdate("insert into Student values (654, 'Amy', 3.9, 1000);");
            st.executeUpdate("insert into Student values (543, 'Craig', 3.4, 2000);");

            st.executeUpdate("insert into Apply values (123, 'Stanford', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (123, 'Stanford', 'EE', 'N');");
            st.executeUpdate("insert into Apply values (123, 'Berkeley', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (123, 'Cornell', 'EE', 'Y');");
            st.executeUpdate("insert into Apply values (234, 'Berkeley', 'biology', 'N');");
            st.executeUpdate("insert into Apply values (345, 'MIT', 'bioengineering', 'Y');");
            st.executeUpdate("insert into Apply values (345, 'Cornell', 'bioengineering', 'N');");
            st.executeUpdate("insert into Apply values (345, 'Cornell', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (345, 'Cornell', 'EE', 'N');");
            st.executeUpdate("insert into Apply values (678, 'Stanford', 'history', 'Y');");
            st.executeUpdate("insert into Apply values (987, 'Stanford', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (987, 'Berkeley', 'CS', 'Y');");
            st.executeUpdate("insert into Apply values (876, 'Stanford', 'CS', 'N');");
            st.executeUpdate("insert into Apply values (876, 'MIT', 'biology', 'Y');");
            st.executeUpdate("insert into Apply values (876, 'MIT', 'marine biology', 'N');");
            st.executeUpdate("insert into Apply values (765, 'Stanford', 'history', 'Y');");
            st.executeUpdate("insert into Apply values (765, 'Cornell', 'history', 'N');");
            st.executeUpdate("insert into Apply values (765, 'Cornell', 'psychology', 'Y');");
            st.executeUpdate("insert into Apply values (543, 'MIT', 'CS', 'N');");

            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            System.out.println("Query 3");

            st.executeUpdate("create view CSaccept as\n" +
                    "select sID, cName\n" +
                    "from Apply\n" +
                    "where major = 'CS' and decision = 'Y';");

            rs = st.executeQuery("select * from CSaccept;");
            if(rs != null){
                System.out.println("---------------CSaccept---------------");
                while(rs.next()) {
                    int sID = rs.getInt("sID");
                    String cName = rs.getString("cName");
                    System.out.println("sID : "+sID+" cName : "+cName);
                }
                rs = null;
            }

            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            System.out.println("Query 4");
            st.executeUpdate("create or replace function test() returns trigger as $$\n" +
                    "begin\ndelete from Apply\nwhere sID = Old.sID\nand cName = Old.cName\n" +
                    "and major = 'CS' and decision = 'Y';\nreturn New;\nend;\n$$\n" +
                    "language 'plpgsql';\n" +
                    "create trigger CSacceptDelete\n" +
                    "instead of delete on CSaccept\n" +
                    "for each row\n" +
                    "execute procedure test();");

            st.executeUpdate("delete from CSaccept where sID = 123;");

            rs = st.executeQuery("select * from CSaccept;");
            if(rs != null){
                System.out.println("---------------CSaccept---------------");
                while(rs.next()) {
                    int sID = rs.getInt("sID");
                    String cName = rs.getString("cName");
                    System.out.println("sID : "+sID+" cName : "+cName);
                }
                rs = null;
            }

            rs = st.executeQuery("select * from Apply");
            if(rs != null){
                System.out.println("---------------APPLY---------------");
                while(rs.next()) {
                    int sid = rs.getInt("sid");
                    String cName = rs.getString("cname");
                    String major = rs.getString("major");
                    String decision = rs.getString("decision");
                    System.out.println("sid : "+sid+" cName : "+cName+" major : "+major+" decision : "+decision);
                }
                rs = null;
            }

            System.out.println("Continue? (Enter 1 for continue)");
            scan.nextLine();
            System.out.println("Query 5");
            st.executeUpdate("create or replace function test() returns trigger as $$\n" +
                    "begin\nupdate Apply\nset cName = New.cName\nwhere sID = Old.sID\n" +
                    "and cName = Old.cName\nand major = 'CS' and decision = 'Y';\nreturn New;\nend;\n$$\n" +
                    "language 'plpgsql';\n" +
                    "create trigger CSacceptUpdate\n" +
                    "instead of update on CSaccept\n" +
                    "for each row\n" +
                    "execute procedure test();");

            st.executeUpdate("update CSaccept set cName = 'CMU' where sID = 345;");

            rs = st.executeQuery("select * from CSaccept;");
            if(rs != null){
                System.out.println("---------------CSaccept---------------");
                while(rs.next()) {
                    int sID = rs.getInt("sID");
                    String cName = rs.getString("cName");
                    System.out.println("sID : "+sID+" cName : "+cName);
                }
                rs = null;
            }

            rs = st.executeQuery("select * from Apply");
            if(rs != null){
                System.out.println("---------------APPLY---------------");
                while(rs.next()) {
                    int sid = rs.getInt("sid");
                    String cName = rs.getString("cname");
                    String major = rs.getString("major");
                    String decision = rs.getString("decision");
                    System.out.println("sid : "+sid+" cName : "+cName+" major : "+major+" decision : "+decision);
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
