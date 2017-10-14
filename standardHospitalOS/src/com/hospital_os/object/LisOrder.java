/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import java.sql.Timestamp;

/**
 *
 * @author Somprasong
 */
public class LisOrder extends Persistent{

    public String ln = "";
    public String order_id = "";
    public String status = "1";
    public String exec_staff = "";
    public String cancel_staff = "";
    public Timestamp exec_datetime;

    @Override
    public String toString() {
        return order_id;
    }
}
