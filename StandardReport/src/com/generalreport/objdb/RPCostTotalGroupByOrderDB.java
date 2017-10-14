/*
 * RPCostTotalGroupByOrderDB.java
 *
 * Created on 13 ���Ҥ� 2548, 14:51 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.object.*;
import com.generalreport.utility.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author americus
 */
public class RPCostTotalGroupByOrderDB
{
    public ConnectionInf theConnectionInf;
    Vector vc;
    CategoryGroupItem theCategoryGroupItem;
    OrderItem theOrderItem;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vPateintInservicePoint ;
    Vector vString;
    Vector vData;
    int language =1;
    /** Creates a new instance of RPCostTotalGroupByOrderDB */
    public RPCostTotalGroupByOrderDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        initObject();
    
    }
    /**
     *  ��㹡�� new Object �ͧ�����ŷ�����㹡�� �� SQL
     */
    private void initObject()
    {
        theCategoryGroupItem = new CategoryGroupItem(); 
        theOrderItem  = new OrderItem();
        
        theCategoryGroupItem.setInitDataFieldName();
        theOrderItem.setInitDataFieldName();
    }    
    
    public Vector queryCostTotalGroupByOrderByDate(String startDate,String finishDate,Vector vStatus)
    {   
        vc  =null;
        try
        {
            SQL = "SELECT " +
                    theCategoryGroupItem.tableName +"."+ theCategoryGroupItem.description +" AS item_subgroup" +
                    ", sum(query1.price) AS price  " +
                    // �����Ţͧ�Ҥҵ鹷ع �����繵�ͧ�Ѵ ������Ҩе�ͧ�ٵ鹷ب�ԧ
                    ", CASE WHEN (sum(query1.cost) > 0) THEN sum(query1.cost) ELSE 0 END AS cost  " +
                  "FROM " +
                     theCategoryGroupItem.tableName +" INNER JOIN " +
                    "(SELECT " +
                    theOrderItem.tableName +"."+ theOrderItem.subgroup_id +" AS b_item_subgroup_id, " +
                    theOrderItem.tableName +"."+ theOrderItem.pk_table +", " +
                    theOrderItem.tableName +"."+ theOrderItem.common_name +", " +
                    theOrderItem.tableName +"."+ theOrderItem.order_price +", " +
                    theOrderItem.tableName +"."+ theOrderItem.order_qty +
                    ", ceil((CASE WHEN ("+theOrderItem.tableName +"."+ theOrderItem.order_price +" > 0) " +
                            " THEN "+theOrderItem.tableName +"."+ theOrderItem.order_price +
                            " ELSE 0 " +
                            " END) *"+ theOrderItem.tableName +"."+ theOrderItem.order_qty +") AS price " +
                    ", ((CASE WHEN (("+theOrderItem.tableName +"."+ theOrderItem.order_cost +" <> '') AND ("+theOrderItem.tableName +"."+ theOrderItem.order_cost +" <> 'null')) " +
                            "THEN to_number("+theOrderItem.tableName +"."+ theOrderItem.order_cost +",'9999999.99') " +
                            "ELSE 0 " +
                            "END) *"+ theOrderItem.tableName +"."+ theOrderItem.order_qty +") AS cost " +
                    "FROM " +
                    "t_order " +
                    "WHERE (" ;
                    if(vStatus != null)
                    {
                       if(vStatus.size() != 0)
                       {
                           int size = vStatus.size();
                           int index_last = size-1;                    
                           for(int i=0; i<size; i++)
                           {
                               if( index_last == (i))
                               {
                                   SQL  = SQL + "("+ theOrderItem.tableName +"."+ theOrderItem.status +" = '" + vStatus.get(i) +"')) ";
                               }
                               else
                               {
                                   SQL  = SQL + "("+ theOrderItem.tableName +"."+ theOrderItem.status +" = '" + vStatus.get(i) +"') OR ";
                               }
                           }
                       }
                    }
         SQL = SQL + " AND (substring("+ theOrderItem.tableName +"."+ theOrderItem.order_date_time +",0,11) Between '" + startDate + "' And '" + finishDate + "') " +
                    "ORDER BY "+ theOrderItem.tableName +"."+ theOrderItem.subgroup_id +", "+ theOrderItem.tableName +"."+ theOrderItem.pk_table +
                    ") AS query1 ON query1.b_item_subgroup_id = "+ theCategoryGroupItem.tableName +"."+ theCategoryGroupItem.pk_table +
                        " WHERE " +
                            theCategoryGroupItem.tableName +"."+ theCategoryGroupItem.active +" = '1' " +
                   "GROUP BY " +
                    theCategoryGroupItem.tableName +"."+ theCategoryGroupItem.pk_table +
                    ", "+ theCategoryGroupItem.tableName +"."+ theCategoryGroupItem.description +
                    " ORDER BY " +
                    theCategoryGroupItem.tableName +"."+ theCategoryGroupItem.pk_table +
                    ", "+ theCategoryGroupItem.tableName +"."+ theCategoryGroupItem.description ;
         
            System.out.println("SQL queryCostTotalGroupByOrderByDate : " + SQL);
            rs = theConnectionInf.eQuery(SQL);
            vc = getData(rs);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(vc == null)
            {
                vc = null;
            }
        }
        return vc;
    }
    
    private Vector getData(ResultSet resultset) throws Exception
    {
        int column = 0;
        String[] rowdata = null;
        String[] columnname = null;
        vString =  null;
        vData = new Vector();
        //��Ǩ�ͺ��� resultset
        if(rs!= null)
        {
            //�ӡ���Ѻ��������ǹ��� field
            metadata = rs.getMetaData();
            //�Ѻ�ӹǹ column
            column = metadata.getColumnCount();
            //init array ����ըӹǹ ��ҡѺ column
            columnname = new String[column];
            vString = new Vector();
            //�ӡ������Ҫ��� field ��� get ��
            for(int i = 0 ; i < column;i++)
            {
                columnname[i] = metadata.getColumnLabel(i+1);
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase(),language);
            }
            //�ӡ������Ңͧ field �Ѻ������
            while(rs.next())
            {   rowdata = new String[column];
                for(int i = 0 ; i < column;i++)
                {
                    if(rs.getObject(i+1) != null)
                    {
                        rowdata[i] = rs.getString(i+1);
                    }
                }
                vString.add(rowdata);
            }
            vData.add(columnname);
            vData.add(vString);
        }
        return vData;
    }
    
}
