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
public class LisLn extends Persistent {

    public String ln = "";
    public String visit_id = "";
    public String exec_datetime = "";
    public Timestamp rec_datetime;

    @Override
    public String toString(){
        return ln;
    }
}
