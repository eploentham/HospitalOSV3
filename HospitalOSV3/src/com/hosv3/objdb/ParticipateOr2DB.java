/*
 * ParticipateOr2DB.java
 *
 * Created on 27 กรกฎาคม 2548, 16:07 น.
 */

package com.hosv3.objdb;
import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.*;
import java.util.*;

/**
 *
 * @author  kingland
 */
public class ParticipateOr2DB extends ParticipateOrDB{
    
    /** Creates a new instance of ParticipateOr2DB */
    public ParticipateOr2DB(ConnectionInf db) {
        super(db);
    }
    
    public Vector selectByDxIcd9(String code) throws Exception {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.t_diag_icd9_id
        + " = '" + code + "' and "
        + dbObj.active + " = '1'";
        return eQuery(sql);
   }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
}
