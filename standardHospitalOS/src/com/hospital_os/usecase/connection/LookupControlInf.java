/*
 * CommonInf.java
 *
 * Created on 20 ���Ҥ� 2546, 10:05 �.
 */

package com.hospital_os.usecase.connection;
/**
 *
 * @author  Administrator
 */
public interface LookupControlInf {
    
    public CommonInf readHosData(String pk);
    public java.util.Vector listData(String str);
}
