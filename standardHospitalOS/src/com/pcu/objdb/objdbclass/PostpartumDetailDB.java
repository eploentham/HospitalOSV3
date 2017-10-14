/*
 * MchDetailDB.java
 *
 * Created on 3 สิงหาคม 2548, 17:13 น.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author LionHeart
 */
public class PostpartumDetailDB
{
    
    /** Creates a new instance of MchDetailDB */
    public PostpartumDetailDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public MchDetail dbObj;
//    final private String idtable = "772";
    
    public PostpartumDetailDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new MchDetail();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_postpartum_detail";
        dbObj.pk_field = "t_health_postpartum_detail_id";
        dbObj.gravida = "postpartum_detail_gravida";
        dbObj.alive_baby = "postpartum_detail_alive_baby";
        dbObj.death_baby = "postpartum_detail_death_baby";
        dbObj.born_place = "postpartum_detail_born_place";
        dbObj.last_men_date = "postpartum_detail_last_men_date";
        dbObj.birth_date = "postpartum_detail_birth_date";
        dbObj.birth_method = "postpartum_detail_birth_method";
        dbObj.bddoctor = "postpartum_detail_bddoctor";
        dbObj.born_result = "postpartum_detail_born_result";
        dbObj.born_at_hospital = "postpartum_detail_born_at_hospital";
        dbObj.vdrl_rs = "postpartum_detail_vdrl_rs";
        dbObj.hb_rs = "postpartum_detail_hb_rs";
        dbObj.hiv_rs = "postpartum_detail_hiv_rs";
        dbObj.hct_date = "postpartum_detail_hct_date";
        dbObj.thalassaemia = "postpartum_detail_thalassaemia";
        dbObj.dental = "postpartum_detail_dental";
        dbObj.decayed_tooth = "postpartum_detail_decayed_tooth";
        dbObj.limestone = "postpartum_detail_limestone";
        dbObj.inflame_gum = "postpartum_detail_inflame_gum";
        dbObj.hct_lavel = "postpartum_detail_hct_lavel";
        dbObj.t_health_family_id = "t_health_family_id";
        dbObj.active = "postpartum_detail_active";
        dbObj.born_amount = "postpartum_detail_born_amount";
        return true;
    }
    
    public int insert(MchDetail o) throws Exception
    {
        String sql="";
        MchDetail p=o;
        if(p.getObjectId()==null)
            p.generateOID("");
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field  
          + " ," + dbObj.gravida
         + " ," + dbObj.alive_baby
         + " ," + dbObj.death_baby
         + " ," + dbObj.born_place
         + " ," + dbObj.last_men_date
         + " ," + dbObj.birth_date
         + " ," + dbObj.birth_method
         + " ," + dbObj.bddoctor
         + " ," + dbObj.born_result
         + " ," + dbObj.born_at_hospital
         + " ," + dbObj.vdrl_rs
         + " ," + dbObj.hb_rs
         + " ," + dbObj.hiv_rs
         + " ," + dbObj.hct_date
         + " ," + dbObj.thalassaemia
         + " ," + dbObj.dental
         + " ," + dbObj.decayed_tooth
         + " ," + dbObj.limestone
         + " ," + dbObj.inflame_gum
         + " ," + dbObj.hct_lavel
         + " ," + dbObj.t_health_family_id
         + " ," + dbObj.active
         + " ," + dbObj.born_amount
        + " ) values ('"
        + p.getObjectId()  
          + "','" + p.gravida
         + "','" + p.alive_baby
         + "','" + p.death_baby
         + "','" + p.born_place
         + "','" + p.last_men_date
         + "','" + p.birth_date
         + "','" + p.birth_method
         + "','" + p.bddoctor
         + "','" + p.born_result
         + "','" + p.born_at_hospital
         + "','" + p.vdrl_rs
         + "','" + p.hb_rs
         + "','" + p.hiv_rs
         + "','" + p.hct_date
         + "','" + p.thalassaemia
         + "','" + p.dental
         + "','" + p.decayed_tooth
         + "','" + p.limestone
         + "','" + p.inflame_gum
         + "','" + p.hct_lavel
         + "','" + p.t_health_family_id
         + "','" + p.active
         + "','" + p.born_amount
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public int update(MchDetail o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        MchDetail p=o;
        String field =""    
          + "', " + dbObj.	gravida+ "='" + p.	gravida
         + "', " + dbObj.	alive_baby+ "='" + p.	alive_baby
         + "', " + dbObj.	death_baby+ "='" + p.	death_baby
         + "', " + dbObj.	born_place+ "='" + p.	born_place
         + "', " + dbObj.	last_men_date+ "='" + p.	last_men_date
         + "', " + dbObj.	birth_date+ "='" + p.	birth_date
         + "', " + dbObj.	birth_method+ "='" + p.	birth_method
         + "', " + dbObj.	bddoctor+ "='" + p.	bddoctor
         + "', " + dbObj.	born_result+ "='" + p.	born_result
         + "', " + dbObj.	born_at_hospital+ "='" + p.	born_at_hospital
         + "', " + dbObj.	vdrl_rs+ "='" + p.	vdrl_rs
         + "', " + dbObj.	hb_rs+ "='" + p.	hb_rs
         + "', " + dbObj.	hiv_rs+ "='" + p.	hiv_rs
         + "', " + dbObj.	hct_date+ "='" + p.	hct_date
         + "', " + dbObj.	thalassaemia+ "='" + p.	thalassaemia
         + "', " + dbObj.	dental+ "='" + p.	dental
         + "', " + dbObj.	decayed_tooth+ "='" + p.	decayed_tooth
         + "', " + dbObj.	limestone+ "='" + p.	limestone
         + "', " + dbObj.	inflame_gum+ "='" + p.	inflame_gum
         + "', " + dbObj.	hct_lavel+ "='" + p.	hct_lavel
         + "', " + dbObj.t_health_family_id+ "='" + p.t_health_family_id
         + "', " + dbObj.active+ "='" + p.active
         + "', " + dbObj.born_amount+ "='" + p.born_amount
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public int delete(MchDetail o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByFid(String fid) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.t_health_family_id + " = '" + fid + "' and "
                + dbObj.active + " = '1' order by "+dbObj.gravida;

        return eQuery(sql);
    }
    
//    public MchDetail readByPPCare(String ppcare_id)  throws Exception {
//        String sql="select * from " + dbObj.table + " where "
//                + dbObj.t_health_pp_care_id + " = '" + ppcare_id + "'";
//        Vector v = eQuery(sql);
//        if(v.isEmpty())
//            return null;
//        else
//            return (MchDetail)v.get(0);
//    }
//    public MchDetail readByFid(String f_id)  throws Exception {
//        String sql="select * from " + dbObj.table + " where "
//                + dbObj.t_health_family_id + " = '" + f_id + "' order by pp_care_child_gravida desc";
//        Vector v = eQuery(sql);
//        if(v.isEmpty())
//            return null;
//        else
//            return (MchDetail)v.get(0);
//    }
//    public MchDetail readByFidAndGravida(String f_id,String gravida)  throws Exception {
//        String sql="select * from " + dbObj.table + " where "
//                + dbObj.t_health_family_id + " = '" + f_id + "' and "
//                + dbObj.gravida + " = '" + gravida + "'";
//        Vector v = eQuery(sql);
//        if(v.isEmpty())
//            return null;
//        else
//            return (MchDetail)v.get(0);
//    }
    public Vector eQuery(String sql) throws Exception
    {
        MchDetail p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new MchDetail();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.gravida = rs.getString(dbObj.gravida);
            p.alive_baby = rs.getString(dbObj.alive_baby);
            p.death_baby = rs.getString(dbObj.death_baby);
            p.born_place = rs.getString(dbObj.born_place);
            p.last_men_date = rs.getString(dbObj.last_men_date);
            p.birth_date = rs.getString(dbObj.birth_date);
            p.birth_method = rs.getString(dbObj.birth_method);
            p.bddoctor = rs.getString(dbObj.bddoctor);
            p.born_result = rs.getString(dbObj.born_result);
            p.born_at_hospital = rs.getString(dbObj.born_at_hospital);
            p.vdrl_rs = rs.getString(dbObj.vdrl_rs);
            p.hb_rs = rs.getString(dbObj.hb_rs);
            p.hiv_rs = rs.getString(dbObj.hiv_rs);
            p.hct_date = rs.getString(dbObj.hct_date);
            p.thalassaemia = rs.getString(dbObj.thalassaemia);
            p.dental = rs.getString(dbObj.dental);
            p.decayed_tooth = rs.getString(dbObj.decayed_tooth);
            p.limestone = rs.getString(dbObj.limestone);
            p.inflame_gum = rs.getString(dbObj.inflame_gum);
            p.hct_lavel = rs.getString(dbObj.hct_lavel);
            p.t_health_family_id = rs.getString(dbObj.t_health_family_id);
            p.active = rs.getString(dbObj.active);
            p.born_amount = rs.getString(dbObj.born_amount);
            list.add(p);
        }
        rs.close();
        return list;
    }
    

    
}
