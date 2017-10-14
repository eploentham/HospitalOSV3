/*
 * HosManage.java
 *
 * Created on 5 กันยายน 2548, 16:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage;
import com.hosv3.control.HosControl;
import com.reportquery.manage.control.ManageControl;
/**
 *
 * @author tong(Padungrat)
 */
public class HosManage {
    
    public ManageControl theManageControl;
    public HosManage(HosControl hc) {
        theManageControl = new ManageControl(hc);
    }
    
}
