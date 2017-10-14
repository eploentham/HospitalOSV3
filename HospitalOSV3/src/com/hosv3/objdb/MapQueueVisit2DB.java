//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb; 
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import com.hosv3.utility.Constant;

//import com.hosv3.utility.*;

public class MapQueueVisit2DB extends MapQueueVisitDB
{
 
    public MapQueueVisit2DB(ConnectionInf db)
    {
        super(db);
    }
    public int save(MapQueueVisit mqv) throws Exception
    {
        if(mqv.getObjectId() == null){
            mqv.setObjectId(Constant.generateOid(idtable,MapQueueVisit.hospital_site));
            return insert(mqv);
        }
        else return update(mqv);
    }
    public int insert(MapQueueVisit o) throws Exception
    {
        String sql="";
        MapQueueVisit p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.visit_id
        + " ,"	+ dbObj.queue_visit
        + " ,"	+ dbObj.queue
        + " ,"	+ dbObj.active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.visit_id
        + "','" + p.queue_visit
        + "','" + p.queue
        + "','" + p.active
        + "')";
        sql = Constant.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
}
