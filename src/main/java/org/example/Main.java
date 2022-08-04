package org.example;
package java.sql;
import com.mysql.cj.protocol.Resultset;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
//import java.sql.*;

public class Main {
    static Scanner inp = new Scanner(System.in);
    public static void main(String[] args)
    {
        int ch;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", "root", "19-Jun-02");
            Statement st = con.createStatement();
            do{System.out.println("CHOOSE YOUR OPTION");
            System.out.println("1)SAVE NEW CONTACT\n2)DISPLAY CONTACTS\n3)EXIT");
            ch = inp.nextInt();
            switch(ch) {
                case 1:
                    String cname, cmobile, cplace, cdob, cemail;
                    System.out.println("ENTER FOLLOWING DETAILS OF THE CONTACT");
                    System.out.println("NAME");
                    cname = inp.next();
                    System.out.println("MOBILE NUMBER");
                    cmobile = inp.next();
                    System.out.println("PLACE");
                    cplace = inp.next();
                    System.out.println("DATE OF BIRTH IN DD/MM/YYYY FORMAT");
                    cdob = inp.next();
                    System.out.println("EMAIL ID");
                    cemail = inp.next();
                    int cid = 0;
                    ResultSet rs1 = st.executeQuery("select * from details;");
                    while (rs1.next()) {
                        cid = rs1.getInt("contact_id");
                    }
                    String sql = "insert into details values('" + cname + "','" + cmobile + "','" + cplace + "','" + cdob + "','" + cemail + "'," + (cid + 1) + ");";
                    // Statement stmt = conn.createStatement();
                    st.executeUpdate(sql);
                    /*if (i > 0) {
                        System.out.println("ROW INSERTED");
                    } else {
                        System.out.println("ROW NOT INSERTED");
                    }*/
                    break;
                case 2:
                    ResultSet rs = st.executeQuery("select * from details;");
                    System.out.println("NAME\tMOBILE\t\tPLACE\tDATE OF BIRTH\tEMAIL ID");
                    while (rs.next()) {
                        System.out.println(rs.getString("name") + "\t" + rs.getString("Mobile") + "\t" + rs.getString("Place") + "\t" + rs.getString("DOB") + "\t" + rs.getString("email"));
                    }
                    break;
            }
            }while(ch!=3);
        }catch(Exception e)
        {
            //  System.out.println(e);
            e.printStackTrace();
        }
    }
}