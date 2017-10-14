//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;

import com.hosv3.object.*;

import com.hospital_os.usecase.connection.*;
//import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;
//import java.util.*;
//import java.sql.*;

public class Appointment2DB extends AppointmentDB
{
    public Appointment2 dbObj2;
    
    public Appointment2DB(ConnectionInf db)
    {
        super(db);
//        dbObj2 = new Appointment2(dbObj);
//        dbObj2.status = "patient_appointment_status";
//        dbObj2.vn = "patient_appointment_vn";
    }
 
}
