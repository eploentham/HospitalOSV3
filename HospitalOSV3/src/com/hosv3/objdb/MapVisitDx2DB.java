//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb; 
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class MapVisitDx2DB extends MapVisitDxDB
{
    public MapVisitDx2DB(ConnectionInf db)
    {
        super(db);
    }
    
    public Vector selectByVid(String vid) throws Exception{
    	String sql = " select * from " + dbObj.table
    	+ " where " + dbObj.pk_field
    	+ " like " + vid + "%" +
        " AND " + dbObj.visit_diag_map_active + "='1'";
    	return eQuery(sql);
    }
    
    public int deleteByVid(String vID) throws Exception {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.visit_id + " like '" + vID +"%'";
        
        return theConnectionInf.eUpdate(sql);
    }    

    public int updateInActiveByVisit(String visit_id
            ,String date_time,String eid) throws Exception {
        String sql="update " + dbObj.table +
                " set " + dbObj.visit_diag_map_active + " = '0'" +
                " , " + dbObj.visit_diag_map_cancel_date_time + " = '" + date_time + "'" +
                " , " + dbObj.visit_diag_map_staff_cancel + " = '" + eid + "'" +
                " where " + dbObj.visit_id + " like '" + visit_id +"%'" + 
                " and " + dbObj.visit_diag_map_active + " = '1'";
        return theConnectionInf.eUpdate(sql);
    }
}
