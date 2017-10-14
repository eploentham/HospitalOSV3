/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.object;

import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author LionHearth
 */
public class LabList extends Persistent{
    String initstring = "";
    public static String TYPE_OLD = "0";
    public static String TYPE_NEW = "1";
    public static String FROM_EXECUTE = "0";
    public static String FROM_MENU = "1";
    public String order_id = initstring;
    public String order_name = initstring;
    public String item_nick_name = initstring;
    public String order_date = initstring;
    public String order_execute_date = initstring;
    public String type = initstring;
    public String status = initstring;
    public String ln = initstring;
}
