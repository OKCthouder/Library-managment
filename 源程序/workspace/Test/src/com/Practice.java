package com;

import java.sql.*;  
public class Practice{  
    public static void main(String[] arg){  
        PreparedStatement ps=null;  
        Connection ct=null;  
        ResultSet rs=null;  
    try {  
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        ct=DriverManager.getConnection  
        ("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=yyj","sa","123456");  
        ps=ct.prepareStatement("select * from Yyj_Student");  
        rs=ps.executeQuery();  
        while(rs.next()){  
            String s=rs.getString(2);  
            System.out.println(s);  
        }  
          
    } catch (Exception e) {  
        e.printStackTrace();  
        // TODO: handle exception  
    }     
              
          
    }  
}
