/*
 * HomeDB.java
 *
 * Created on 11 มิถุนายน 2548, 12:57 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;

/**
 *
 * @author Administrator
 */
public class HomeDB{
    
    /** Creates a new instance of HomeDB */
    public HomeDB() {
    }
    
    public ConnectionInf theConnectionInf;
    
    public Home dbObj;
    private VillageDB dbObjv;
    final private String idtable = "714";
    
    public HomeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Home();
        dbObjv = new VillageDB(theConnectionInf);
        
        initConfig(); 
        
    }
    
    public boolean initConfig()
    {
//        super.initConfig();
        dbObj.table = "t_health_home"; 
        dbObj.pk_field = "t_health_home_id";
        dbObj.office_id = "b_visit_office_id";
        dbObj.home_number = "health_home_number";   
        dbObj.home_house ="health_home_house"; 
        dbObj.home_moo = "health_home_moo";
        dbObj.home_road = "health_home_road";
        dbObj.home_tambol = "health_home_tambon";
        dbObj.home_amphur = "health_home_amphur";
        dbObj.home_changwat = "health_home_changwat";
        dbObj.family = "health_home_family";
        dbObj.zone = "health_home_responsible_zone";
        dbObj.volunteer = "health_home_volunteer";
        dbObj.v_name = "health_home_volunteer_name";
        dbObj.owner = "health_home_owner";
        dbObj.owner_number = "health_home_owner_number";
        dbObj.charactor_id = "f_health_home_charactor_id";
        dbObj.community_charac_id = "f_health_home_community_charac_id";
        dbObj.village_id ="t_health_village_id"; 
        dbObj.home_staff_record ="home_staff_record";
        dbObj.home_staff_modify = "home_staff_modify";   
        dbObj.home_staff_cancel ="home_staff_cancel";
        dbObj.home_record_date_time ="home_record_date_time";
        dbObj.home_modify_date_time = "home_modify_date_time";   
        dbObj.home_cancel_date_time ="home_cancel_date_time";
        dbObj.active ="home_active"; 
        
        /*amp use for count home*/
        dbObj.count_home = "count_home";
        dbObj.sum_family = "sum_family";
        
        /*jao use for sql*/
        
               
        return true;
    }
    
    public int insert(Home o) throws Exception
    {
        String sql="";
        Home p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.office_id
        + " ,"  + dbObj.home_number
        + " ,"	+ dbObj.home_house
        + " ,"	+ dbObj.home_moo
        + " ,"	+ dbObj.home_road
        + " ,"	+ dbObj.home_tambol
        + " ,"	+ dbObj.home_amphur
        + " ,"	+ dbObj.home_changwat
        + " ,"	+ dbObj.family
        + " ,"	+ dbObj.zone  
        + " ,"	+ dbObj.volunteer
        + " ,"	+ dbObj.v_name
        + " ,"	+ dbObj.owner
        + " ,"	+ dbObj.owner_number
        + " ,"	+ dbObj.charactor_id
        + " ,"	+ dbObj.community_charac_id
        + " ,"  + dbObj.village_id
        + " ,"  + dbObj.home_staff_record
        + " ,"  + dbObj.home_staff_modify
        + " ,"  + dbObj.home_staff_cancel
        + " ,"  + dbObj.home_record_date_time
        + " ,"  + dbObj.home_modify_date_time
        + " ,"  + dbObj.home_cancel_date_time
        + " ,"	+ dbObj.active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.office_id
        + "','" + p.home_number
        + "','" + p.home_house
        + "','" + p.home_moo
        + "','" + p.home_road    
        + "','" + p.home_tambol        
        + "','" + p.home_amphur       
        + "','" + p.home_changwat    
        + "','" + p.family
        + "','" + p.zone
        + "','" + p.volunteer       
        + "','" + p.v_name
        + "','" + p.owner      
        + "','" + p.owner_number
        + "','" + p.charactor_id       
        + "','" + p.community_charac_id 
        + "','" + p.village_id
        + "','" + p.home_staff_record
        + "','" + p.home_staff_modify
        + "','" + p.home_staff_cancel
        + "','" + p.home_record_date_time
        + "','" + p.home_modify_date_time
        + "','" + p.home_cancel_date_time
        + "','" + p.active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Home o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Home p=o;
        String field =""
        + "', " + dbObj.office_id + "='" + p.office_id
        + "', " + dbObj.home_number + "='" + p.home_number
        + "', " + dbObj.home_house + "='" + p.home_house
        + "', " + dbObj.home_moo + "='" + p.home_moo
        + "', " + dbObj.home_road + "='" + p.home_road    
        + "', " + dbObj.home_tambol + "='" + p.home_tambol    
        + "', " + dbObj.home_amphur + "='" + p.home_amphur    
        + "', " + dbObj.home_changwat + "='" + p.home_changwat    
        + "', " + dbObj.family + "='" + p.family         
        + "', " + dbObj.zone + "='" + p.zone         
        + "', " + dbObj.volunteer + "='" + p.volunteer    
        + "', " + dbObj.v_name + "='" + p.v_name    
        + "', " + dbObj.owner + "='" + p.owner    
        + "', " + dbObj.owner_number + "='" + p.owner_number    
        + "', " + dbObj.charactor_id + "='" + p.charactor_id    
        + "', " + dbObj.community_charac_id + "='" + p.community_charac_id 
        + "', " + dbObj.village_id + "='" + p.village_id
        + "', " + dbObj.home_staff_record + "='" + p.home_staff_record
        + "', " + dbObj.home_staff_modify + "='" + p.home_staff_modify
        + "', " + dbObj.home_staff_cancel + "='" + p.home_staff_cancel
        + "', " + dbObj.home_record_date_time + "='" + p.home_record_date_time
        + "', " + dbObj.home_modify_date_time + "='" + p.home_modify_date_time
        + "', " + dbObj.home_cancel_date_time + "='" + p.home_cancel_date_time
        + "', " + dbObj.active + "='" + p.active 
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Home o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**ใช้ในการ ค้าหาหมู้บ้านทั้งหมดที่มีอยู่ในฐานข้อมูล และเก็บลง Vecrot เพื่อเป็น Temp ของการหาว่า
     *ผู้ป่วย อยู่บ้านไหน ของ PCU
     *โออยข้อมูลที่จะได้ออำมาจะมีเพียง field keyID ของตาราง, 
     * health_home_number, health_home_house และt_health_village_id
     *@return Vector ของ Object Home
     */
    public Vector selectForImportHCIS() throws Exception
    {
        String sql = " select " +
                dbObj.pk_field +
                "," + dbObj.home_number +
                "," + dbObj.home_house +
                "," + dbObj.village_id +
                " from " +dbObj.table+
                "  order by " + dbObj.home_number;
       
                
        Home home;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            home = new Home();
            home.setObjectId(rs.getString(dbObj.pk_field));  
         
            home.home_number = rs.getString(dbObj.home_number);
            home.home_house = rs.getString(dbObj.home_house);
            home.village_id = rs.getString(dbObj.village_id);
            
            list.add(home);
        }
        rs.close();
        
        return list;
    }
    
    
    /**
     * เดียวถ้าไม่มีใครใช้แล้วจะลบ
     * @deprecated ไม่ใช้
     * @param search =     search2 =
     * @return Vector
     * @author modify by kingland
     */
    public Vector listHomeByNumber(String search,String search2) throws Exception
    {
        String sql="select * from t_health_home "
                +" left join t_health_village on  t_health_village.t_health_village_id = t_health_home.t_health_village_id" +
                "  where ";
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.home_number
                + " like " + search + "" + " or "
                + dbObj.home_house+ " like '" + search  + "') and ";
            }
            sql = sql +" t_health_home.t_health_village_id= " +search2
                    + " and " +dbObj.active + " = '1' order by " + dbObj.home_record_date_time;
        Vector v=eQuery(sql);
        if(v.size()==0)
        { //  
            return null;
        }
        else
        {  // 
            return v;
        }
    }
    /**
     * ค้นหาบ้าน แบบไม่เจาะจง
     * @param  homenumber = บ้านเลขที่ villageid = รหัสหมู่บ้าน
     * @return vector ของ บ้าน
     * @authur modify by kingland
     * @date 4/8/2549
     * @deprecated henbe unused use listHomeLikeHomeNo instead;
     */
    public Vector listHomeByNumber2(String homenumber,String villageid) throws Exception
    {
        Vector v = listHomeLikeHomeNo(homenumber,villageid);
        if(v.size()==0)
        { //  
            return null;
        }
        else
        {  // 
            return v;
        }        
    }
    public Vector listHomeLikeHomeNo(String homenumber,String villageid) throws Exception
    {
        String sql="select * from " + dbObj.table
                +" left join "+dbObjv.dbObj.table
                +" on "+dbObj.table+"."+dbObj.village_id+" = "+dbObjv.dbObj.table+"."+dbObjv.dbObj.pk_field
                + " where ";            
            if(homenumber.trim().length() !=0)
            {
                sql = sql + dbObj.table+"."+dbObj.home_house+ " like '" + homenumber  +  "%' and ";
            }
            sql = sql +dbObj.table+"."+dbObj.village_id+ " = '" +villageid+ "' and " +dbObj.active + " = '1'" +
                    " order by " + dbObj.home_house + " limit 50 ";
        return eNewQuery(sql);

    }
    public Vector selectByHomeRoad(String homenumber,String villageid) throws Exception
    {
        String sql="select * from " + dbObj.table
                +" left join "+dbObjv.dbObj.table
                +" on "+dbObj.table+"."+dbObj.village_id+" = "+dbObjv.dbObj.table+"."+dbObjv.dbObj.pk_field
                + " where ";
            if(homenumber.trim().length() !=0)
            {
                sql = sql + "("+dbObj.table+"."+dbObj.home_house+ " like '" + homenumber  +  "' or ";
                sql = sql + dbObj.table+"."+dbObj.home_road+ " like '" + homenumber  +  "%') and ";
            }
            if(villageid!=null)
                sql+=dbObj.table+"."+dbObj.village_id+ " = '" +villageid+ "'";

            sql+=dbObj.active + " = '1' order by " + dbObj.home_house + " limit 50 ";
        return eNewQuery(sql);

    }
    /**
     * ค้นหาบ้าน แบบเจาะจง
     * @param  homenumber = บ้านเลขที่ villageid = รหัสหมู่บ้าน
     * @return vector ของ บ้าน
     * @authur modify by kingland
     * @date 4/8/2549
     * @deprecated henbe unused use listHomeEqHomeNo instead;
     */
    public Vector listHomeByNumber3(String homenumber,String villageid) throws Exception
    {
        Vector v = listHomeEqHomeNo(homenumber,villageid);
        if(v.size()==0)
        { //  
            return null;
        }
        else
        {  // 
            return v;
        }
    }
    /**
     *ได้มีการแก้ไขเพราะว่ามีการเช็ค varchar=bigInt henbe modify
     **/
    public Vector listHomeEqHomeNo(String homenumber,String villageid) throws Exception
    {
        String sql="select * from " + dbObj.table
                +" left join "+dbObjv.dbObj.table
                +" on "+dbObj.table+"."+dbObj.village_id
                +" = "+dbObjv.dbObj.table+"."+dbObjv.dbObj.pk_field+ " where ";            
            if(homenumber.trim().length() !=0)
                sql = sql + dbObj.table+"."+dbObj.home_house+ " = '" + homenumber  +  "' and ";

            sql = sql +dbObj.table+"."+dbObj.village_id+ " = '" +villageid+ "' and " +dbObj.active + " = '1'" +
                    " order by " + dbObj.home_house + " limit 50 ";
        return eNewQuery(sql);
    }
    /**     
     * -
     * @param -
     * @return -
     * @author -
     */
    public Home countOfHomeSumOfFamily(String villageId) throws Exception
    {
        String sql ="SELECT sum(CASE WHEN " + dbObj.family + " <> '' THEN to_number(" + dbObj.family + ",'99999D') " +
                " ELSE 0 END) AS sum_family, " +
                "Count(" + dbObj.pk_field + ") AS count_home " +
                "FROM " + dbObj.table +
                " WHERE (((" + dbObj.active + ")='1') AND ((" + dbObj.village_id + ")='" + villageId + "'))";        
        
        Vector v = extraQueryCountHomeAndFamily(sql);
        if(v.size()==0)
            return null;
        else
            return (Home)v.get(0);
    }
    
    public Home selectHomeByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
                +" left join "+dbObjv.dbObj.table
                +" on "+dbObj.table+"."+dbObj.village_id+" = "+dbObjv.dbObj.table+"."+dbObjv.dbObj.pk_field+ 
        " where " + dbObj.pk_field + " = '" + pk + "' and " + dbObj.active + " ='1'" ;
        Vector v=eNewQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Home)v.get(0); 
    }
    public Vector selectBy2Key(String key)throws Exception
    {
        String keys[] = key.split(" ");
        String sql = " select * from t_health_home " +
                " left join t_health_village on t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                " where home_active = '1' " +
                " and (t_health_home.health_home_house like '%"+keys[0]+"%' " +
                " or t_health_home.health_home_moo like '%"+keys[0]+"%' " +
                " or t_health_village.village_name like '%"+keys[0]+"%') ";
        if(keys.length>1)
        sql += " and (t_health_home.health_home_house like '%"+keys[1]+"%' " +
                " or t_health_home.health_home_moo like '%"+keys[1]+"%' " +
                " or t_health_village.village_name like '%"+keys[1]+"%')";
        sql += " order by health_home_house limit 300 ";
        return this.eQuery(sql);
    }
    /**
     * ค้นหาบ้าน แบบไม่เจาะจง
     * @param  homenumber = บ้านเลขที่
     * @return vector
     * @authur modify by kingland
     * @date 4/8/2549
     */
    public Vector selectAllVillage(String search) throws Exception
    {   
        Vector v = new Vector();
        
        String sql ="SELECT * FROM " +
                    "( SELECT " + dbObj.table + " .*,"+dbObjv.dbObj.village_tambon+","+dbObjv.dbObj.village_ampur+","+dbObjv.dbObj.village_changwat+","+dbObjv.dbObj.village_moo+
                    " FROM " + dbObjv.dbObj.table + " , " + dbObj.table + 
                    " WHERE "+ dbObj.table + " . " +  dbObj.village_id + " = " + dbObjv.dbObj.table + " . " + dbObjv.dbObj.pk_field +
                    " AND " + dbObjv.dbObj.village_active + " = '1' " +
                    " AND " + dbObj.active + " = '1' ) as q " +
                    " WHERE q." + dbObj.home_number + " LIKE '%" + search + "'" + 
                    " OR q."  + dbObj.home_house+ " LIKE '" + search
                    + "%' order by q." + dbObj.home_number + " limit 50 ";
        
        v = eNewQuery(sql);
        
        if(v.size()==0)
            return null;
        else
            return v;        
    }
    /**
     * ค้นหาบ้าน แบบเจาะจง
     * @param  homenumber = บ้านเลขที่
     * @return vector
     * @authur modify by kingland
     * @date 4/8/2549
     */
    public Vector selectAllVillage2(String search) throws Exception
    {   
        Vector v = new Vector();
        
        String sql ="SELECT * FROM " +
                    "( SELECT " + dbObj.table + " .*,"+dbObjv.dbObj.village_tambon+","+dbObjv.dbObj.village_ampur+","+dbObjv.dbObj.village_changwat+","+dbObjv.dbObj.village_moo+
                    " FROM " + dbObjv.dbObj.table + " , " + dbObj.table + 
                    " WHERE "+ dbObj.table + " . " +  dbObj.village_id + " = " + dbObjv.dbObj.table + " . " + dbObjv.dbObj.pk_field +
                    " AND " + dbObjv.dbObj.village_active + " = '1' " +
                    " AND " + dbObj.active + " = '1' ) as q " +
                    " WHERE q." + dbObj.home_number + " = '" + search + "'" + 
                    " OR q."  + dbObj.home_house+ " = '" + search
                    + "%' order by q." + dbObj.home_number + " limit 50 ";
        
        v = eNewQuery(sql);
        
        if(v.size()==0)
            return null;
        else
            return v;        
    }
    /**
     * เดียวถ้าไม่มีใครใช้แล้วจะลบ
     * @deprecated ไม่ใข้แล้ว
     * @param search =     search2 =
     * @return Vector
     * @author modify by kingland
     */
    public Vector extraQueryCountHomeAndFamily(String sql) throws Exception
    {
        Home p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Home();
            p.count_home = rs.getString(dbObj.count_home);
            p.sum_family = rs.getString(dbObj.sum_family);
            list.add(p);
        }
        rs.close();
        return list;
    }
    public Vector eNewQuery(String sql) throws Exception
    {
        Home p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Home();
            p.setObjectId(rs.getString(dbObj.pk_field));  
          //  
            p.office_id = rs.getString(dbObj.office_id);
            p.home_number = rs.getString(dbObj.home_number);
            p.home_house = rs.getString(dbObj.home_house);
//            p.home_moo = rs.getString(dbObj.home_moo);
            p.home_road = rs.getString(dbObj.home_road);
//            p.home_tambol = rs.getString(dbObj.home_tambol);
//            p.home_amphur = rs.getString(dbObj.home_amphur);
//            p.home_changwat = rs.getString(dbObj.home_changwat);
             p.home_moo = rs.getString(dbObjv.dbObj.village_moo);
            p.home_tambol = rs.getString(dbObjv.dbObj.village_tambon);
            p.home_amphur = rs.getString(dbObjv.dbObj.village_ampur);
            p.home_changwat = rs.getString(dbObjv.dbObj.village_changwat);
            p.family = rs.getString(dbObj.family);           
            p.zone = rs.getString(dbObj.zone);         
            p.volunteer = rs.getString(dbObj.volunteer);
            p.v_name = rs.getString(dbObj.v_name);
            p.owner = rs.getString(dbObj.owner);
            p.owner_number = rs.getString(dbObj.owner_number);
            p.charactor_id = rs.getString(dbObj.charactor_id);
            p.community_charac_id = rs.getString(dbObj.community_charac_id);
            p.village_id = rs.getString(dbObj.village_id);
            p.home_staff_record = rs.getString(dbObj.home_staff_record);
            p.home_staff_modify = rs.getString(dbObj.home_staff_modify);
            p.home_staff_cancel = rs.getString(dbObj.home_staff_cancel);
            p.home_record_date_time = rs.getString(dbObj.home_record_date_time);
            p.home_modify_date_time = rs.getString(dbObj.home_modify_date_time);
            p.home_cancel_date_time = rs.getString(dbObj.home_cancel_date_time);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    public Vector eQuery(String sql) throws Exception
    {
        Home p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Home();
            p.setObjectId(rs.getString(dbObj.pk_field));  
          //  
            p.office_id = rs.getString(dbObj.office_id);
            p.home_number = rs.getString(dbObj.home_number);
            p.home_house = rs.getString(dbObj.home_house);
            if(p.home_house==null)
                p.home_house = "";
            p.home_moo = rs.getString(dbObj.home_moo);
            p.home_road = rs.getString(dbObj.home_road);
            p.home_tambol = rs.getString(dbObj.home_tambol);
            p.home_amphur = rs.getString(dbObj.home_amphur);
            p.home_changwat = rs.getString(dbObj.home_changwat);
//            p.home_tambol = rs.getString(dbObjv.dbObj.village_tambon);
//            p.home_amphur = rs.getString(dbObjv.dbObj.village_ampur);
//            p.home_changwat = rs.getString(dbObjv.dbObj.village_changwat);
            p.family = rs.getString(dbObj.family);           
            p.zone = rs.getString(dbObj.zone);         
            p.volunteer = rs.getString(dbObj.volunteer);
            p.v_name = rs.getString(dbObj.v_name);
            p.owner = rs.getString(dbObj.owner);
            p.owner_number = rs.getString(dbObj.owner_number);
            p.charactor_id = rs.getString(dbObj.charactor_id);
            p.community_charac_id = rs.getString(dbObj.community_charac_id);
            p.village_id = rs.getString(dbObj.village_id);
            p.home_staff_record = rs.getString(dbObj.home_staff_record);
            p.home_staff_modify = rs.getString(dbObj.home_staff_modify);
            p.home_staff_cancel = rs.getString(dbObj.home_staff_cancel);
            p.home_record_date_time = rs.getString(dbObj.home_record_date_time);
            p.home_modify_date_time = rs.getString(dbObj.home_modify_date_time);
            p.home_cancel_date_time = rs.getString(dbObj.home_cancel_date_time);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /**
     * เดียวถ้าไม่มีใครใช้แล้วจะลบ
     * @deprecated ไม่ใช้แล้ว
     * @param -
     * @return -
     * @author modify by kingland
     */
    public Home selectByNo(String home_no,String road,String village_id) throws Exception
    {
        String sql="select * from " + dbObj.table
                + " where " +  dbObj.home_house + " = '" + home_no
                + "' and "  + dbObj.home_road + " = '" + road
                + "' and "  + dbObj.village_id + " = '" + village_id
                + "' and "  + dbObj.active + " = '1'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Home)v.get(0);
    }    
    
    /**
     * เดียวถ้าไม่มีใครใช้แล้วจะลบ ใช้โดย PatientControl เพื่อค้นหาคนไข้ที่กรอกบ้านเลขที่จะนำไปค้นว่ามีบ้าน
     * เลขที่นี้อยู่จริงหรือไม่ แล้วก็จะได้เลือกบ้านนั้นให้ผู้ป่วยเข้าไปเลย
     * @param -
     * @return -
     * @author modify by kingland
     * @see : ค้นบ้านจากเลขบ้าน และ pk ของหมู่บ้าน
     */
    public Home selectByNo(String home_no,String village_id) throws Exception
    {
        String sql="select * from " + dbObj.table
                + " where " +  dbObj.home_house + " = '" + home_no
                + "' and "  + dbObj.village_id + " = '" + village_id
                + "' and "  + dbObj.active + " = '1'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Home)v.get(0);
    }     
    public Vector selectByVillage(String village_id) throws Exception
    {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.village_id + " = '" + village_id
                + "' and "  + dbObj.active + " = '1'";
        return eQuery(sql);
    }    
    
    /**
     *
     *
     * @param -
     * @return -
     * @author modify by kingland
     * @see ค้นข้อมูลจาก PrimaryKey ของมัน
     */
    public Home selectByPK(String home_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + home_id+"'";
        Vector v=eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (Home)v.get(0); 
    } 
    
}
