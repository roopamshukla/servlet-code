/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;

public class ProductServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String []inputs=new String[100];
        String pid=request.getParameter("pid");
        String pname=request.getParameter("pname");
        String des=request.getParameter("des");
        String date=request.getParameter("date");
        int qty=Integer.parseInt(request.getParameter("qty"));
        int price=Integer.parseInt(request.getParameter("price"));
        int count=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_info","root","password");
            Statement s=c.createStatement();
            ResultSet rs=s.executeQuery("select * from product_details");
            while(rs.next())
            {
                count++;
            }
            String q="insert into product_details values(?,?,?,?,?,?,?)";
            PreparedStatement ps=c.prepareStatement(q);
            ps.setInt(1,count+1);
            ps.setString(2,pid);
            ps.setString(3,pname);
            ps.setString(4,des);
            ps.setString(5,date);
            ps.setInt(6,qty);
            ps.setInt(7,price);
            
            ps.execute();
            
              } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd=request.getRequestDispatcher("DisplayExisting");
        rd.forward(request,response);
    }
}
