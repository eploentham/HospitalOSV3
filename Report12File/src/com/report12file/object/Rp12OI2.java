/*
 * AerDB.java
 *
 * Created on 1 ๏ฟฝิง๏ฟฝาค๏ฟฝ 2548, 10:19 ๏ฟฝ.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



package com.report12file.object;

import com.reportcenter.object.RpOI;


/**
 * ตัวบอกความแตกต่างแต่ละรายงาน
 * @author henbe
 */
public abstract class Rp12OI2 extends RpOI
{
    /**
     * สร้าง instant ของแต่ละรายงาน
     * @return instant ของรายงาน
     */
    public abstract Rp12OI2 initInstant(); 



}
