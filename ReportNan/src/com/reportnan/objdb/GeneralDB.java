/*
 * GeneralDB.java
 *
 * Created on 12 ÁÔ¶Ø¹ÒÂ¹ 2549, 17:03 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.utility.ComboFix;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class GeneralDB
{
    ConnectionInf theConnectionInf;
    Vector vAllYear;
    String SQL = "";
    ResultSet rs;
    
    /** Creates a new instance of GeneralDB */
    public GeneralDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    
     public Vector AllYear()
     {
         vAllYear = new Vector();
         try
         {
             SQL = "select " +
                     "substring(t_visit.visit_begin_visit_time,1,4) AS visit_year " +
                     "from " +
                     "t_visit " +
                     "where " +
                     "t_visit.f_visit_status_id <> '4' " +
                     "and t_visit.f_visit_type_id = '0' " +
                     "group by visit_year " +
                     "order by visit_year";
             
             System.out.println(SQL);
             vAllYear = evQuery(SQL);
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
         return vAllYear;         
     }
     
     private Vector evQuery(String sql) throws Exception
     {
         ComboFix theComboFix;
         Vector vAllYear = new Vector();
         rs = theConnectionInf.eQuery(sql);         
         while(rs.next())
         {
             theComboFix = new ComboFix();
             System.out.print("*-*-*-*-*-*-" + rs.getRow());
             theComboFix.code = String.valueOf(rs.getRow());
             theComboFix.name = rs.getString(1);
             
             vAllYear.add(theComboFix);
         }
         return vAllYear;
     }
     
     public static void main(String[] argv)
     {
         GeneralDB pr = new GeneralDB(null);
         pr.AllYear();
     }
}
