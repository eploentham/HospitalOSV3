/*
 * CheckSQLDB.java
 *
 * Created on 20 มิถุนายน 2547, 16:52 น.
 */

package com.reportquery.objectdb;
import com.hospital_os.usecase.connection.*;
//import com.hospital_os.utility.*;
//import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class CheckSQLDB
{
    
    /** Creates a new instance of CheckSQLDB */
    public ConnectionInf theConnectionInf;
    public CheckSQLDB(ConnectionInf db)
    {
        theConnectionInf = db;
    }
    
    public boolean checkSQL(String sql)
    {   boolean result = false;
        try
        {
            /*ResultSet rs = theConnectionInf.eQuery(sql);
*/
            PreparedStatement ps = this.theConnectionInf.ePQuery(sql);
            
            ResultSet rs = ps.executeQuery();
            if(rs != null)
            {
                result = true;
            }
            else
            {    result = false;}
        }
        catch(Exception ex)
        {   ex.printStackTrace();
        }
        return result;
    }
    
    public boolean checkSQLParam(String sql)
    {   boolean result = false;
        try
        {
            
            PreparedStatement ps = this.theConnectionInf.ePQuery(sql);
            ps.setString(1, "");
            ps.setString(2, "");
            ResultSet rs = ps.executeQuery();
            if(rs != null)
            {  result = true;
            }
            else
            {  result = false;;
            }
        }
        catch(Exception ex)
        {   ex.printStackTrace();
        }
        return result;
    }
    
    public ResultSet querySQL(String sql)
    {
        ResultSet rs = null;
        try
        {
            rs = theConnectionInf.eQuery(sql);
            
        }
        catch(Exception ex)
        {
            /*ex.printStackTrace();
*/
            rs = null;
        }
        return rs;
    }
    
    public ResultSet querySQLParam(String sql,String startdate,String endddate)
    {
        ResultSet rs = null;
        try
        {
            String tmp_sql = sql;
            int cur_index = 0;
            int num_qt = 0;
            while(cur_index != -1)
            {
                cur_index++;
                cur_index = tmp_sql.indexOf("?",cur_index);
                num_qt = num_qt +1;
            }
            num_qt = num_qt-1;     /*ลูปเกินมา 1 รอบ
*/
            
            PreparedStatement ps = this.theConnectionInf.ePQuery(sql);
            ps.setString(1,startdate);
            ps.setString(2,endddate);
            if(num_qt > 2)
            {
                for(int i=3;i<=num_qt;i++)
                {
                    ps.setString(i,startdate);
                    i++;
                    ps.setString(i,endddate);
                }
            }
            rs = ps.executeQuery();
            
        }
        catch(Exception ex)
        {
            /*ex.printStackTrace();
*/
            rs = null;
        }
        return rs;
    }
    
}
