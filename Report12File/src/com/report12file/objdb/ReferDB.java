/*
 * chaDataDB.java
 *
 * Created on 9 ????? 2547, 18:43 ?.
 */

package com.report12file.objdb;


import com.hospital_os.usecase.connection.*;
import java.io.FileNotFoundException;
import java.util.Vector;
/**
 *
 * @author  ojika
 */
public class ReferDB {
    
    /** Creates a new instance of chaDataDB */
    ConnectionInf theConnectionInf; 
    
    public ReferDB(ConnectionInf con) {  
        theConnectionInf = con; 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
    public String queryData(String visit, String refer) 
    {      
        String strRefer = new String();
        String SQLCmd = "";
        /*String SQLCmd = " SELECT (t_visit_refer_in_out " +
                        " || visit_refer_in_out_lab " +
                        " || visit_refer_in_out_observe " +
                        " || visit_refer_in_out_result_request ) AS ireftype " +
                        " FROM " +
                        " t_visit_refer_in_out " +
                        " WHERE " +
                        " t_visit_refer_in_out.t_visit_id = '" +visit+ "'" +
                        " AND t_visit_refer_in_out.f_visit_refer_type_id = '" +refer+ "'";
   */
   if(refer != null)
   {
        if(refer.trim().equalsIgnoreCase("0"))
        {
            // OUT
            SQLCmd = " select t_visit.b_visit_office_id_refer_out " +
                        " from t_visit " +
                        " where t_visit.t_visit_id = " + visit + 
                        " and t_visit.f_visit_status_id <> '4' ";
        }
        else if(refer.trim().equalsIgnoreCase("1"))
        {
            // IN
            SQLCmd = " select t_visit.b_visit_office_id_refer_in " +
                        " from t_visit " +
                        " where t_visit.t_visit_id = " + visit + 
                        " and t_visit.f_visit_status_id <> '4' ";
        }
   }
        
        
        
        System.out.println("CHA SQL : "+SQLCmd);
        java.util.Vector vc = new java.util.Vector();
        try{
            strRefer =  selectDB(SQLCmd);
            return strRefer;
        }
        catch(Exception ex)
        {   
            ex.printStackTrace();
            return  "";
        }
    }
    
    public String selectDB(String sql) throws Exception {
        java.util.Vector vc = new java.util.Vector();
        java.sql.ResultSet rs = theConnectionInf.eQuery(sql);
        String refer = new String();
       
        while(rs.next())
        {   
                refer = rs.getString(1);         
        }
        
        return refer;
        
    }

    public Vector selectByDate(String startDate, String endDate) throws Exception {
        return null;
    }

    public String convertToString(Vector vObject, boolean isGetColumnName) {
        return null;
    }

    public void setDBFPathFile(String path) throws FileNotFoundException {
    }

    public void closeFile() throws Exception {
    }
    
}
