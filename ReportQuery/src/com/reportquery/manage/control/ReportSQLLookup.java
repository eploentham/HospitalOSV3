/*
 * EmployeeLookup.java
 *
 * Created on 27 กรกฎาคม 2548, 15:22 น.
 */

package com.reportquery.manage.control;
//import com.henbe.connection.*;
import com.hosv3.control.*;
import com.hospital_os.usecase.connection.*;

/**
 *
 * @not deprecated because use henbe package
 * @author  kingland
 */
public class ReportSQLLookup implements LookupControlInf{
    
    private QueryControl theLookup;
    /** Creates a new instance of EmployeeLookup */
    public ReportSQLLookup(QueryControl lookup) {
       theLookup = lookup;
    }
    
    
    public java.util.Vector listData(String str) {
//        return theLookup.listDoctor(str);
        return theLookup.listSQLByName(str);
    }
    
    public CommonInf readHosData(String str) {
//        return theLookup.readEmployeeById(str);
        return  null;
    }
    
}
