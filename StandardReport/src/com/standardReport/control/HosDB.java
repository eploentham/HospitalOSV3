/*
 * HosDB.java
 *
 * Created on 28 กรกฎาคม 2548, 13:11 น.
 */

package com.standardReport.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.standardReport.objdb.RP115Group1_2549DB;
import com.standardReport.objdb.RP115Group2_2549DB;
import com.standardReport.objdb.RP115Group3_2549DB;
import com.standardReport.objdb.RP115Group4_2549DB;
import com.standardReport.objdb.RP504DB;
import com.standardReport.objdb.RP505DB;
import com.standardReport.objdb.RP506DB;
import com.standardReport.objdb.RP506SurveilDB;
import com.standardReport.objdb.TreatStatusDB;

/**
 *
 * @author ojika
 */
public class HosDB
{
    
    public RP504DB theRP504DB;
    public RP505DB theRP505DB;
    public RP506DB theRP506DB;
    public RP506SurveilDB theRP506SurveilDB;
    public RP115Group1_2549DB theRP115Group1_2549DB;
    public RP115Group2_2549DB theRP115Group2_2549DB;
    public RP115Group3_2549DB theRP115Group3_2549DB;
    public RP115Group4_2549DB theRP115Group4_2549DB;
    
    public TreatStatusDB theTreatStatusDB;
    public ConnectionInf theConnectionInf;

    
    public HosDB(ConnectionInf c)
    {
        theConnectionInf = c;
        theRP504DB = new RP504DB(c);
        theRP505DB = new RP505DB(c);
        theRP506DB = new RP506DB(c);
        theRP506SurveilDB = new RP506SurveilDB(c);
        theRP115Group1_2549DB = new RP115Group1_2549DB(c);
        theRP115Group2_2549DB = new RP115Group2_2549DB(c);
        theRP115Group3_2549DB = new RP115Group3_2549DB(c);
        theRP115Group4_2549DB = new RP115Group4_2549DB(c);
        
        theTreatStatusDB = new TreatStatusDB(c);
    }
    
}
