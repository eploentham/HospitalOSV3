//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
package com.hosv3.objdb.squery; 
import com.hospital_os.usecase.connection.*;
//import com.hosv3.objdb.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class SpecialQueryVisit2DB extends VisitDB
{
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public SpecialQueryVisit2DB(ConnectionInf db)
    {
        super(db);
    }
  //////////////////////////////////////////////////////////////////////////////////////
   public Vector selectLabByDatePid(boolean all,String from,String to,String pid) throws Exception
    {
	   return selectCatByDatePid(all,from,to,pid,"2");
    }
   //////////////////////////////////////////////////////////////////////////////////////
   public Vector selectXrayByDatePid(boolean all,String from,String to,String pid) throws Exception
   {
	   return selectCatByDatePid(all,from,to,pid,"3");    
   }
   //////////////////////////////////////////////////////////////////////////////////////
   public Vector selectCatByDatePid(boolean all,String from,String to,String pid,String cat) throws Exception
    {
        String sql = "select * from t_visit where t_visit_id in " +
                "(select t_visit.t_visit_id from t_order " +
                "left join t_visit on t_order.t_visit_id = t_visit.t_visit_id " +
                "where t_order.t_patient_id = '" + pid + "' ";
        if(!all){
            sql += " and (" + dbObj.begin_visit_time + " >= '" +from+",00:00:00" + 
                    "' and " + dbObj.begin_visit_time + " <= '" +to+",23:59:59')";
        }
        sql += " and t_order.f_item_group_id = '" + cat + "') " +
                " order by " + dbObj.begin_visit_time + " desc";
       
        return eQuery(sql);
    }
   //////////////////////////////////////////////////////////////////////////////
}
