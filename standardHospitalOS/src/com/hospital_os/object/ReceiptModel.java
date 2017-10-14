/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital_os.object;

/**
 *
 * @author LionHearth
 */
public class ReceiptModel extends X39Persistent{
    final private String idtable = "";
    public String description = "";
    final static String CASH = "1";
    final static String CREDIT = "2";
    public ReceiptModel() {
    }

    public static ReceiptModel initConfig() {
        ReceiptModel dbObj = new ReceiptModel();
        dbObj.table = "f_billing_receipt_model";
        dbObj.setObjectId("f_billing_receipt_model_id");
        dbObj.pk_field = "f_billing_receipt_model_id";
        dbObj.description = "receipt_model_description";
        return dbObj;
    }

    @Override
    public X39Persistent getInstant(String[] strd) {
        ReceiptModel receiptModel = new ReceiptModel();
        receiptModel.setStringArray(strd);
        return receiptModel;
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
