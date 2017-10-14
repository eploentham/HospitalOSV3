/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital_os.object;

/**
 *
 * @author LionHearth
 */
public class OrderSpecified  extends X39Persistent{
    final private String idtable = "";
    public String description = "";
    final static String DOCTOR_DF = "1";
    public OrderSpecified() {
    }

    public static OrderSpecified initConfig() {
        OrderSpecified dbObj = new OrderSpecified();
        dbObj.table = "f_order_specified";
        dbObj.setObjectId("f_order_specified_id");
        dbObj.pk_field = "f_order_specified_id";
        dbObj.description = "order_specified_description";
        return dbObj;
    }

    @Override
    public X39Persistent getInstant(String[] strd) {
        OrderSpecified orderSpecified = new OrderSpecified();
        orderSpecified.setStringArray(strd);
        return orderSpecified;
    }

    @Override
    public String[] getStringArray() {
        return new String[]{
                    getObjectId(), description
                };
    }

    @Override
    public void setStringArray(String[] array) {
        setObjectId(array[0]);
        description = array[1];
    }

    @Override
    public String getIdTable() {
        return this.idtable;
    }
}
