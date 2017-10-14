/*
 * SpecialContrctAdjustByVGaCT.java
 *
 * Created on 8 กันยายน 2547, 19:59 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class SpecialContrctAdjustByVGaCTDB
{
    /** Creates a new instance of SpecialContrctAdjustByVGaCT */
    public ConnectionInf theConnectionInf;
    public SpecialContrctAdjustByVGaCT dbObj;
    public SpecialContrctAdjustByVGaCTDB(ConnectionInf db)
    {
        theConnectionInf = db;
        
        dbObj = new SpecialContrctAdjustByVGaCT();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.plan_id ="b_contract_plans_id";
        dbObj.adjustment ="contract_condition_adjustment";
        dbObj.draw ="contract_condition_draw";
        dbObj.item_group_code_category ="b_item_subgroup_id";
        /*dbObj.contract_id ="b_contract_id";*/
        return true;
    }
    
    public SpecialContrctAdjustByVGaCT readContractAdjustByCGaCT(String plans_id,String item_subgroup) throws Exception
    {
        String sql = "SELECT " +
        dbObj.plan_id + "," +
        dbObj.adjustment + "," +
        dbObj.draw  + "," +
        dbObj.item_group_code_category + "" +
        /*dbObj.contract_id + "" +*/
        " FROM b_contract_plans " +
        " INNER JOIN b_contract ON (b_contract_plans.b_contract_id = b_contract.b_contract_id) " +
        " INNER JOIN b_contract_condition ON (b_contract.b_contract_id = b_contract_condition.b_contract_id)" +
        " WHERE " +
        " (b_contract_plans.b_contract_plans_id = '" + plans_id + "') AND " +
        " (b_contract_condition.b_item_subgroup_id = '" + item_subgroup + "')";
        
        Vector vc =  eQuery(sql);
        if(vc.size() == 0)
            return null;
        else
            return (SpecialContrctAdjustByVGaCT)vc.get(0);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialContrctAdjustByVGaCT p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialContrctAdjustByVGaCT();
            p.plan_id = rs.getString(dbObj.plan_id);
            p.adjustment = rs.getString(dbObj.adjustment);
            p.draw = rs.getString(dbObj.draw);
            p.item_group_code_category  = rs.getString(dbObj.item_group_code_category);
            /*p.contract_id = rs.getString(dbObj.contract_id);*/
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
