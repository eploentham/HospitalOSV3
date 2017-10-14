/*
 * SpecialQueryOrderDrugDB.java
 *
 * Created on 27 มกราคม 2548, 22:06 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  Administrator
 */
public class SpecialQueryOrderDrugDB {
    
    /** Creates a new instance of SpecialQueryOrderDrugDB */
    OrderItemDrug dbOrderItemDrug = null;
    OrderItem  dbOrderItem = null;
    public ConnectionInf theConnectionInf;
    OrderItemDrugDB theOrderItemDrugDB = null;
    OrderItemDB theOrderItemDB = null;
    Vector vOrder = null ;
    Vector vOrderDrug = null ;
    vOrderSpecial thevOrderSpecial  = new vOrderSpecial();
    String orderitem ="";
    public SpecialQueryOrderDrugDB(ConnectionInf db) {
        theConnectionInf = db;
        theOrderItemDrugDB = new OrderItemDrugDB(db);
        theOrderItemDB = new OrderItemDB(db);
        dbOrderItemDrug = theOrderItemDrugDB.initConfig();
        dbOrderItem = theOrderItemDB.initConfig();
        
        orderitem = dbOrderItemDrug.table + "." + dbOrderItemDrug.order_item_id ;
        dbOrderItem.pk_field =  dbOrderItem.table + "." + dbOrderItem.pk_field ;
        
        dbOrderItemDrug.item_id = dbOrderItemDrug.table + "." + dbOrderItemDrug.item_id ;
      /*  dbOrderItem.item_code =  dbOrderItem.table + "." + dbOrderItem.item_code ;
*/
        
    }
    
    
    public vOrderSpecial queryOrderItemAndOrderItemDrugByVisitID(String visit_id) throws Exception
    {   String sql = "SELECT " +
        /*
        dbOrderItem.category_group + "," +
        dbOrderItem.charge_complete+ "," +
        dbOrderItem.clinic_code+ "," +
        dbOrderItem.common_name+ "," +
        dbOrderItem.continue_order+ "," +
        dbOrderItem.discontinue+ "," +
        dbOrderItem.discontinue_time+ "," +
        dbOrderItem.dispense+ "," +
        dbOrderItem.dispense_time+ "," +
        dbOrderItem.executer+ "," +
        dbOrderItem.executed_time+ "," +
        dbOrderItem.hn+ "," +
        dbOrderItem.item_code+ "," +
        dbOrderItem.item_group_code_billing+ "," +
        dbOrderItem.item_group_code_category+ "," +
        dbOrderItem.order_complete+ "," +
        dbOrderItem.order_cost+ "," +
        dbOrderItem.order_time+ "," +
        dbOrderItem.order_user+ "," +
        dbOrderItem.pk_field+ "," +
        dbOrderItem.price+ "," +
        dbOrderItem.qty+ "," +
        dbOrderItem.refer_out+ "," +
        dbOrderItem.request+ "," +
        dbOrderItem.secret+ "," +
        dbOrderItem.status+ "," +
        dbOrderItem.vertifier+ "," +
        dbOrderItem.vertify_time+ "," +
        dbOrderItem.visit_id+ "," +
   */     
        dbOrderItemDrug.caution + "," +
        dbOrderItemDrug.day_time + "," +
        dbOrderItemDrug.description + "," +
        dbOrderItemDrug.dose + "," +
        dbOrderItemDrug.frequency + "," +
        dbOrderItemDrug.instruction + "," +
        dbOrderItemDrug.item_id + "," +
        orderitem + "," +
        dbOrderItemDrug.pk_field + "," +
        dbOrderItemDrug.printing + "," +
        dbOrderItemDrug.purch_uom + "," +
        dbOrderItemDrug.usage_special + "," +
        dbOrderItemDrug.usage_text + "," +
        dbOrderItemDrug.use_uom + " " +
        
        " FROM " + dbOrderItemDrug.table + "," + dbOrderItem.table +"" +
        " WHERE " + orderitem + "=" + dbOrderItem.pk_field + "" +
        " AND " + dbOrderItem.visit_id + "='" + visit_id + "'" +
        " AND " + dbOrderItem.status + " <> '" + OrderStatus.DIS_CONTINUE+ "'" +
        " ORDER BY "+ dbOrderItem.vertify_time + ","+  dbOrderItem.status + " DESC ," + dbOrderItem.category_group;
       
        return eQuery(sql);
        
    }
    
    public vOrderSpecial eQuery(String sql)  throws Exception
    {
        vOrder = new Vector();
        vOrderDrug = new Vector();
        OrderItem orderitem = new OrderItem();
        OrderItemDrug orderitemdrug = new OrderItemDrug();
        
        
       /* Constant.println(theConnectionInf.getClass().getName());
*/
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next()) 
	{   
            orderitemdrug= new OrderItemDrug();
            orderitem = new OrderItem();
            /*
            
            orderitem.category_group= rs.getString(dbOrderItem.category_group);
            orderitem.charge_complete= rs.getString(dbOrderItem.charge_complete);
            orderitem.clinic_code= rs.getString(dbOrderItem.clinic_code);
            orderitem.common_name= rs.getString(dbOrderItem.common_name);
            orderitem.continue_order= rs.getString(dbOrderItem.continue_order);
            orderitem.discontinue= rs.getString(dbOrderItem.discontinue);
            orderitem.discontinue_time= rs.getString(dbOrderItem.discontinue_time);
            orderitem.dispense= rs.getString(dbOrderItem.dispense);
            orderitem.dispense_time= rs.getString(dbOrderItem.dispense_time);
            orderitem.executer= rs.getString(dbOrderItem.executer);
            orderitem.executed_time= rs.getString(dbOrderItem.executed_time);
            orderitem.hn= rs.getString(dbOrderItem.hn);
            
            orderitem.item_code= rs.getString(dbOrderItemDrug.order_item_id);
            
            orderitem.item_group_code_billing= rs.getString(dbOrderItem.item_group_code_billing);
            orderitem.item_group_code_category= rs.getString(dbOrderItem.item_group_code_category);
            orderitem.order_complete= rs.getString(dbOrderItem.order_complete);
            orderitem.order_cost= rs.getString(dbOrderItem.order_cost);
            orderitem.order_time= rs.getString(dbOrderItem.order_time);
            orderitem.order_user= rs.getString(dbOrderItem.order_user);
            
            orderitem.setObjectId(rs.getString(dbOrderItemDrug.order_item_id));
            
            orderitem.price= rs.getString(dbOrderItem.price);
            orderitem.qty= rs.getString(dbOrderItem.qty);
            orderitem.refer_out= rs.getString(dbOrderItem.refer_out);
            orderitem.request = rs.getString(dbOrderItem.request);
            orderitem.secret= rs.getString(dbOrderItem.secret);
            orderitem.status= rs.getString(dbOrderItem.status);
            orderitem.vertifier= rs.getString(dbOrderItem.vertifier);
            orderitem.vertify_time= rs.getString(dbOrderItem.vertify_time);
            orderitem.visit_id= rs.getString(dbOrderItem.visit_id);
*/
            orderitemdrug.caution= rs.getString(dbOrderItemDrug.caution );
            orderitemdrug.day_time= rs.getString(dbOrderItemDrug.day_time );
            orderitemdrug.description= rs.getString(dbOrderItemDrug.description );
            orderitemdrug.dose= rs.getString(dbOrderItemDrug.dose );
            orderitemdrug.frequency= rs.getString(dbOrderItemDrug.frequency );
            orderitemdrug.instruction= rs.getString(dbOrderItemDrug.instruction );
            orderitemdrug.item_id= rs.getString(dbOrderItem.item_code );/*orderitem.item_code;//
*/
            orderitemdrug.order_item_id= rs.getString(dbOrderItemDrug.order_item_id );/*orderitem.item_code;//
*/
            orderitemdrug.setObjectId(rs.getString(dbOrderItemDrug.pk_field ));
            orderitemdrug.printing= rs.getString(dbOrderItemDrug.printing );
            orderitemdrug.purch_uom= rs.getString(dbOrderItemDrug.purch_uom );
            orderitemdrug.usage_special= rs.getString(dbOrderItemDrug.usage_special );
            orderitemdrug.usage_text= rs.getString(dbOrderItemDrug.usage_text );
            orderitemdrug.use_uom= rs.getString(dbOrderItemDrug.use_uom);
            
            
            
            
            
          /*  vOrder.add(orderitem);
*/
            vOrderDrug.add(orderitemdrug);
            orderitemdrug = null;
            orderitem = null;
        }
        thevOrderSpecial.vorder = vOrder;
        thevOrderSpecial.vorderitemdrug = vOrderDrug;
        rs.close();
        return thevOrderSpecial;
    }
    public Vector getOrderItem()
    {
        return this.vOrder;
    }
    public Vector getOrderItemDrug()
    {
        return this.vOrderDrug;
    }
    
}
