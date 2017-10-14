//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;

import com.hospital_os.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;

import java.util.*;
import java.sql.*;

public class Option2DB extends OptionDB
{
    
    public Option2DB(ConnectionInf db)
    {
        super(db);
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(Option o) throws Exception
    {
        String sql="";
        Option p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        
        + " ,"	+ dbObj.verify
        + " ,"	+ dbObj.execute
        + " ,"	+ dbObj.dispense
        + " ,"	+ dbObj.discontinue
        + " ,"	+ dbObj.del_patient
        + " ,"	+ dbObj.cancel_receipt
        + " ,"	+ dbObj.discharge
        + " ,"	+ dbObj.admit
        + " ,"	+ dbObj.printJasper
        + " ,"	+ dbObj.commit
        + " ,"	+ dbObj.inqueuevisit
        + " ,"	+ dbObj.life
        + " ,"	+ dbObj.passwd_cancel_receipt
        + " ,"	+ dbObj.drug_interaction
        + " ,"	+ dbObj.drug_standard_allergy        
        + " ,"	+ dbObj.auto_diag_icd10
        //    + " ,"	+ dbObj.start_time_queue
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.verify
        + "','" + p.execute
        + "','" + p.dispense
        + "','" + p.discontinue
        + "','" + p.del_patient
        + "','" + p.cancel_receipt
        + "','" + p.discharge
        + "','" + p.admit
        + "','" + p.printJasper
        + "','" + p.commit
        + "','" + p.inqueuevisit
        + "','" + p.life
        + "','" + p.passwd_cancel_receipt
        + "','" + p.drug_interaction
        + "','" + p.drug_standard_allergy   
        + "','" + p.auto_diag_icd10
        //     + "','" + p.start_time_queue
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Option o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Option p=o;
        String field =""
        + "', " + dbObj.verify + "='" + p.verify
        + "', " + dbObj.execute + "='" + p.execute
        + "', " + dbObj.dispense + "='" + p.dispense
        + "', " + dbObj.discontinue + "='" + p.discontinue
        + "', " + dbObj.del_patient + "='" + p.del_patient
        + "', " + dbObj.cancel_receipt + "='" + p.cancel_receipt
        + "', " + dbObj.discharge + "='" + p.discharge
        + "', " + dbObj.admit + "='" + p.admit
        + "', " + dbObj.printJasper + "='" + p.printJasper
        + "', " + dbObj.commit + "='" + p.commit
        + "', " + dbObj.inqueuevisit + "='" + p.inqueuevisit
        + "', " + dbObj.passwd_cancel_receipt + "='" + p.passwd_cancel_receipt
        + "', " + dbObj.drug_interaction + "='" + p.drug_interaction
        + "', " + dbObj.drug_standard_allergy + "='" + p.drug_standard_allergy
        //      + "', " + dbObj.start_time_queue + "='" + p.start_time_queue
        + "', " + dbObj.life + "='" + p.life
        + "', " + dbObj.auto_diag_icd10 + "='" + p.auto_diag_icd10
        + "'" + "where " + dbObj.pk_field + " = '" + p.getObjectId() + "'"
        ;
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(Option o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Option selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Option)v.get(0);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception 
    {    
             String sql="select * from " + dbObj.table;
             Vector v = eQuery(sql);
             if(v.size()==0)
                 return null;
             else
                 return v;
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Option p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Option();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.verify = rs.getString(dbObj.verify);
            p.execute = rs.getString(dbObj.execute);
            p.dispense = rs.getString(dbObj.dispense);
            p.discontinue = rs.getString(dbObj.discontinue);
            p.del_patient = rs.getString(dbObj.del_patient);
            p.cancel_receipt = rs.getString(dbObj.cancel_receipt);
            p.discharge = rs.getString(dbObj.discharge);
            p.admit = rs.getString(dbObj.admit);
            p.printJasper= rs.getString(dbObj.printJasper);
            p.commit = rs.getString(dbObj.commit);
            p.inqueuevisit = rs.getString(dbObj.inqueuevisit);
            //       p.start_time_queue = rs.getString(dbObj.start_time_queue);
            p.life = rs.getString(dbObj.life);
            p.passwd_cancel_receipt = rs.getString(dbObj.passwd_cancel_receipt);
            p.drug_interaction = rs.getString(dbObj.drug_interaction);
            p.drug_standard_allergy = rs.getString(dbObj.drug_standard_allergy);
            p.auto_diag_icd10 = rs.getString(dbObj.auto_diag_icd10);
            list.add(p);
        }
        rs.close();
        return list;
    }
    //////////////////////////////////////////////////////////////////////////////    
}
