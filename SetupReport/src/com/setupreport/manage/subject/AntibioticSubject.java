/*
 * AntibioticSubject.java
 *
 * Created on 28 ตุลาคม 2548, 19:39 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.ManageAntibiotic;
import java.util.HashMap;
/**
 *
 * @author tong(Padungrat)
 */
public class AntibioticSubject implements ManageAntibiotic{
    
    HashMap HashMapGUI;
    int size = 0;
    public AntibioticSubject() {
        HashMapGUI = new HashMap();
    }
    public void registerMainReportManage(ManageAntibiotic manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }
    public void notifyShowItemDrug(java.util.Vector vItem) {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((ManageAntibiotic)HashMapGUI.get(String.valueOf(i))).notifyShowItemDrug(vItem);
        }
    }
    
}
