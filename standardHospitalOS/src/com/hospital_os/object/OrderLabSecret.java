/*
 * OrderLabSecret.java
 *
 * Created on 2 �չҤ� 2549, 14:24 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class OrderLabSecret extends Persistent
{
    public String order_id;
    public String specimen_code = "";
    
    /** Creates a new instance of OrderLabSecret */
    public OrderLabSecret()
    {
    }
    
}
