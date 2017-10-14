/*
 * ManageAfterMchResp.java
 *
 * Created on 29 กรกฎาคม 2548, 15:50 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.subject;
import com.pcu.object.BornMch;
/**
 *
 * @author tong(Padungrat)
 */
public interface ManageAfterMchResp {
    
    /** ใช้ในการเรียกข้อมูลการเข้ารับบริการหลังคลอด */
    public void notifyCallAfterMchMother(BornMch bornmch);
    /**ใช้ในการเรียกข้อมูล ให้แสดง panel ของข้อมูลการคลอด*/
    public void notifyCallAfterBornMchMother();
    /**ใช้ในส่งข้อมูลกลับมาให้ panel ข้อมูลการเข้ารับบริการหลังคลอด*/
    public void notifyGetDataBornMchMotherToAfterMchMother(BornMch bornmch);
    /** ใช้ในการเรียกข้อมูล ให้ panel ของ ข้อมูลหลังคลอด แสดง อะไรบางอย่างเมื่อมีการเลือก Panel ของ PCU*/
    public void notifyCallAfterBornMchMotherService(boolean inFirst);
}
