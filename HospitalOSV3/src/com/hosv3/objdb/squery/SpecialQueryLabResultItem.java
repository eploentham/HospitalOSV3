/*
 * SpecialQueryLabResultItem.java
 *
 * Created on 19 ธันวาคม 2548, 16:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.objdb.squery;
import com.hospital_os.object.OrderItem;
import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.*;
import java.util.*;
/**
 *
 * @author kingland
 */
public class SpecialQueryLabResultItem extends LabResultItemDB {
    
    /** Creates a new instance of SpecialQueryLabResultItem */
    public SpecialQueryLabResultItem(ConnectionInf db) {
        super(db);
    }
    
    public Vector listLabResultItemByItemID(String id) throws Exception{
        String sql = "select * from " +dbObj.table
                +" where " +dbObj.item_id +" in ( select b_item_id from b_item_lab_group where b_item_lab_set_id in (Select b_item_lab_set_id from b_item_lab_set where b_item_id = '"+ id +"'))";
        return eQuery(sql);
    }    
    public Vector listLabResultItemByItemID(Vector v)throws Exception{
        String sql = "select * from " +dbObj.table +" where ";
        for(int i=0,size=v.size();i<size;i++){
            OrderItem oi = (OrderItem)v.get(i);
            if(oi != null && i==0){
                sql = sql +dbObj.item_id+ " = '"+oi.item_code+"'";
            }
            else if(oi != null){
                sql = sql +" or "+dbObj.item_id+ " = '"+oi.item_code+"'";
            }
        }
        return eQuery(sql);
    }
}
