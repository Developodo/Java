package com.carlosserrano.basicojfx.model;

import com.carlosserrano.basicojfx.Utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemDAO extends Item{
    
    private boolean persist;

    public ItemDAO() {
        super();
        persist=false;
    }
    public ItemDAO(int id, String title, String description){
        super(id,title,description);
        persist=false;
    }
    public ItemDAO(String title, String description){
        super(-1,title,description);
        persist=false;
    }
    
    public ItemDAO(Item i){
        id=i.id;
        title=i.title;
        description=i.description;
    }
    
        
    public ItemDAO(int id){
        super();

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q="SELECT * FROM item WHERE id="+id;
            PreparedStatement ps=conn.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.id=id;
                    this.title=rs.getString("title");
                    this.description=rs.getString("description");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void persist(){
        persist=true;
    }
    public void detatch(){
        persist=false;
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description); 
        if(persist){
            save();
        }
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        if(persist){
            save();
        }
    }

    public int save(){
        int result=-1;
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            
            if(this.id>0){
                //UPDATE
                String q = "UPDATE item SET title = ?, description = ? WHERE id = ?";
                PreparedStatement ps = csql.prepareStatement(q);
                ps.setString(1, title);
                ps.setString(2, description);
                ps.setInt(3, id);
                result = ps.executeUpdate();
            }else{
                //INSERT
                String q = "INSERT INTO item (id,title,description) VALUES(NULL,?,?)";
                PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setString(2, description);
                result = ps.executeUpdate();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);  //<-- return last id inserted
                    }
                }
                this.id = result;
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
     public static List<Item> selectAll(){
           return selectAll("");
     }
    
    public static List<Item> selectAll(String pattern){
        List<Item> result=new ArrayList<>();
        
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q="SELECT * FROM item";
           
            if(pattern.length()>0){
                q+=" WHERE title LIKE ?";
            }
            PreparedStatement ps=conn.prepareStatement(q);
            
            if(pattern.length()>0){
                ps.setString(1, pattern+"%");
            }
            
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Item n = new Item();
                    n.setId(rs.getInt("id"));
                    n.setTitle(rs.getString("title"));
                    n.setDescription(rs.getString("description"));
                    result.add(n);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }
    
    public int remove(){
        int result=-1;
        if (this.id > 0) {
            
            try {
               java.sql.Connection csql = ConnectionUtil.getConnection();
                String q = "DELETE FROM item WHERE id=" + this.id;

                PreparedStatement ps = csql.prepareStatement(q);
                result = ps.executeUpdate();
                if(result>0)
                    this.id = -1;
                

            } catch (SQLException ex) {
                Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
