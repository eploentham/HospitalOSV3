/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_os.objdb;

import com.hospital_os.object.LisOrder;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Somprasong
 */
public class LisOrderDB {

    private ConnectionInf theConnectionInf;
    private LisOrder dbObj;
    final public String idtable = "lis2";
    OrderItemDrugDB theOrderItemDrugDB;

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public LisOrderDB(ConnectionInf db) {
        theConnectionInf = db;
        dbObj = new LisOrder();
        this.initConfig();
    }

    public LisOrder initConfig() {
        dbObj.table = "t_lis_order";
        dbObj.pk_field = "t_lis_order_id";
        dbObj.ln = "lab_number";
        dbObj.order_id = "t_order_id";
        dbObj.status = "status";
        dbObj.exec_staff = "exec_staff";
        dbObj.cancel_staff = "cancel_staff";
        return dbObj;
    }

    public int insert(LisOrder p) throws Exception {
        p.generateOID(idtable);
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(dbObj.table);
        sql.append(" (");
        sql.append(dbObj.pk_field);
        sql.append(" ,");
        sql.append(dbObj.ln);
        sql.append(" ,");
        sql.append(dbObj.order_id);
        sql.append(" ,");
        sql.append(dbObj.status);
        sql.append(" ,");
        sql.append(dbObj.exec_staff);
        sql.append(" ) values ('");
        sql.append(p.getObjectId());
        sql.append("','");
        sql.append(p.ln);
        sql.append("','");
        sql.append(p.order_id);
        sql.append("','");
        sql.append(p.status);
        sql.append("','");
        sql.append(p.exec_staff);
        sql.append("')");
        return theConnectionInf.eUpdate(sql.toString());
    }

    public int updateCancel(LisOrder p) throws Exception {

        StringBuilder sql = new StringBuilder("update ");
        sql.append(dbObj.table);
        sql.append(" set ");
        sql.append(dbObj.status);
        sql.append("='");
        sql.append(p.status);
        sql.append("', ");
        sql.append(dbObj.cancel_staff);
        sql.append("='");
        sql.append(p.cancel_staff);
        sql.append("', ");
        sql.append("cancel_datetime");
        sql.append("=");
        sql.append("CURRENT_TIMESTAMP");
        sql.append(" where ");
        sql.append(dbObj.pk_field);
        sql.append("='");
        sql.append(p.getObjectId());
        sql.append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }

    public LisOrder getLisOrderbyOrderId(String orderId) throws Exception {
        String sql = "SELECT * "
                + "FROM t_lis_order "
                + "Where t_lis_order.t_order_id = '" + orderId + "' ";
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        LisOrder lisOrder = null;
        if (rs.next()) {
            lisOrder = new LisOrder();
            lisOrder.setObjectId(rs.getString(dbObj.pk_field));
            lisOrder.ln = rs.getString(dbObj.ln);
            lisOrder.order_id = rs.getString(dbObj.order_id);
            lisOrder.status = rs.getString(dbObj.status);
            lisOrder.exec_datetime = rs.getTimestamp("exec_datetime");
        }
        rs.close();

        return lisOrder;
    }

    public String getLabNobyOrderId(String orderId) throws Exception {
        String sql = "SELECT t_lis_order.lab_number "
                + "FROM t_lis_order "
                + "Where t_lis_order.t_order_id = '" + orderId + "' ";
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        String string = "";
        if (rs.next()) {
            string = rs.getString(dbObj.ln);
        }
        rs.close();

        return string;
    }

    @SuppressWarnings("UseOfObsoleteCollectionType")
    public Vector eQuery(String sql) throws Exception {
        LisOrder p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        int i = 0;
        while (rs.next()) {
            p = new LisOrder();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.ln = rs.getString(dbObj.ln);
            p.order_id = rs.getString(dbObj.order_id);
            p.status = rs.getString(dbObj.status);
            p.exec_datetime = rs.getTimestamp("exec_datetime");
        }
        rs.close();
        return list;
    }
}
