/*
 * StandardDB.java
 *
 * Created on 3 มิถุนายน 2548, 15:51 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.objdb;

/**
 *
 * @author tong(padungrat)
 */
public interface StandardDB {
    
    /** Creates a new instance of StandardDB */
    public Object selectByKeyID(String key_id) throws Exception;
    public int deleteByKeyID(String key_id) throws Exception;
    public int updateByKeyID(String key_id) throws Exception;
    public int insertData(Object object) throws Exception;
    
}
