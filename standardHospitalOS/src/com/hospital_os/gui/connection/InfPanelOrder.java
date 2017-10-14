/*
 * InfPanelObject.java
 *
 * Created on 8 ตุลาคม 2549, 10:26 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.gui.connection;

import com.hospital_os.object.*;
/**
 *
 * @author Administrator
 */
public interface InfPanelOrder{
    public boolean setOrderItem(OrderItem or);
    public OrderItem getOrderItem();
    public boolean saveOrderItem();
    public boolean setItem(Item it);
    public Item getItem();
}
