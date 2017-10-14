/*
 * LabResultItem2DB.java
 *
 * Created on 27 กันยายน 2548, 15:18 น.
 */

package com.hosv3.objdb;
//import java.util.*;
//import java.sql.*;
import com.hospital_os.usecase.connection.*;
//import com.hospital_os.utility.*;
import  com.hospital_os.objdb.LabResultItemDB;

import com.hosv3.object.LabResultItem2;

/**
 *
 * @author  kingland
 */
public class LabResultItem2DB extends LabResultItemDB{
    
    /** Creates a new instance of LabResultItem2DB */
    public LabResultItem2 dbObj2;
    public LabResultItem2DB(ConnectionInf db){
        super(db);
    }

}
