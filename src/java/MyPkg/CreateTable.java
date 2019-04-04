package MyPkg;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CreateTable extends DBConnect {
    public void CreateTable()
    {
    DBConnect db = new DBConnect();
        try {
            Connection con = db.connect();
            String qr = "create table if not exists user(userid int primary key, Name varchar(30),password varchar(8),email varchar(20),address varchar(40),contact int(10)";
            queryExecuter(qr);
            String qr1="create table if not exists item(item_no int(5),prod_name varchar(30),dept varchar(20),year varchar(5),price double(5,2),description varchar(500)";
             queryExecuter(qr1);
            
        } catch (Exception ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        CreateTable c = new CreateTable();
        
        
        
        
    }

   
}
