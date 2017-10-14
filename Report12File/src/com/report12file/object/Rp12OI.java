/*
 * AerDB.java
 *
 * Created on 1 �ԧ�Ҥ� 2548, 10:19 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



package com.report12file.object;
import java.sql.*;

import com.linuxense.javadbf.*;


/**
 * ��Ǻ͡����ᵡ��ҧ������§ҹ
 * @author henbe
 */
public interface Rp12OI 
{
    
    /**
     * ��ҹ���������� array
     * @return String[]
     */
    public String[] getValueArray();
    /**
     * ��ҹ���Ϳ�Ŵ����� array
     * @return String[]
     */
    public String[] getHeaderArray();
    /**
     * ��Ǩ�ͺ�ҡ datadict
     * @param sb �ѿ�����红�����
     * @param error �ѿ������ error
     * @return �觼š�õ�Ǩ�ͺ boolean
     */
    public boolean checkDatadict(StringBuffer sb,int[] error) ;
    /**
     * ���ҧ instant �ͧ������§ҹ
     * @return instant �ͧ��§ҹ
     */
    public Rp12OI initInstant();
    /**
     * ��˹���Ҩҡ ResultSet
     * @param rs ��Ҩҡ�ҹ������
     * @return ��� instant �����ҡ resultset
     * @throws java.lang.Exception �ҡ�����ҹ��Ŵ�Դ��Ҵ ���ա���� error
     */
    public boolean setValue(ResultSet rs) throws Exception;
    /**
     * ���������������� ��� SQL
     * @param con ��ͧ�ҧ�����������
     * @param startDate �ѹ���������§ҹ
     * @param endDate �ѹ����ش��§ҹ
     * @param mode ���͡Ẻ����ء��¡������੾�з���ҹ
     * @throws java.lang.Exception ��͹ exception
     * @return �觤�� preparestatement ���Ѻ�к�
     */
    public PreparedStatement getPreparedStatement(Connection con
            ,String startDate,String endDate,int mode)throws Exception;

    /**
     * �������
     * @return String �������
     */
    String getFileName();

    /**
     * �����ͧ͢��Ŵ�����Ѻ DBF
     * @return DBFField[]
     * @throws java.lang.Exception �ҡ�ա�û�С�ȿ�Ŵ�Դ��Ҵ
     */
    DBFField[] getDBFField() throws Exception;

    /**
     * ��� ��� �ͧ��Ŵ�����Ѻ DBF
     * @return DBFField[]
     */
    Object[] getDBFValue();

    /**
     * ���Ϳ�Ŵ�����Ѻ��͹����� �ŧ�ҡ header array
     * @return String[]
     */
    String[] getWarningArray();
}
