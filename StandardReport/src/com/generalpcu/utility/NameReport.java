/*
 * NameReport.java
 *
 * Created on 3 ���Ҥ� 2548, 15:10 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.utility;
import java.util.*;
/**
 *
 * @author pu
 */
public class NameReport {
    
    HashMap theHashMapReport;
    public NameReport()
    {
        theHashMapReport = new HashMap();
        setObject();
    }
    
    /**
     *  �觢�������§ҹ
     */
    public HashMap getObject()
    {
        return theHashMapReport;
    }
    
    /**
     * �������§ҹ���� ����� ��ͧ������ ��ǹ�����·ء����
     */
    private void setObject()
    {
        Report report = new Report(); 
        
        report.INDEX = "1";
        report.ENG_NAME = "ReportChronicPCU";
        report.THAI_NAME = "��§ҹ�ä������ѧ";
        report.DESCRIPTION = "�ӹǹ�������ä������ѧ ��� ��ª��ͼ������ä������ѧ ";
        
        theHashMapReport.put(report.INDEX,report );        
      
        report = null;
        report = new Report();
        
        report.INDEX = "2";
        report.ENG_NAME = "ReportNutritionPCU";
        report.THAI_NAME = "��§ҹ����ҡ��";
        report.DESCRIPTION = "��������´����ҡ�âͧ������ 0-5 �բͧ���������ҹ �ࢵ�Ӻŷ���Ѻ�Դ�ͺ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "3";
        report.ENG_NAME = "ReportUncontagiousPCU";
        report.THAI_NAME = "��§ҹ�ä���Դ���";
        report.DESCRIPTION = "�ӹǹ�������ä���Դ��� ��� ��ª��ͼ������ä���Դ��� �¡��������ҹ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "4";
        report.ENG_NAME = "ReportResidentPCU";
        report.THAI_NAME = "��§ҹ��Ъҡ�";
        report.DESCRIPTION = "��������´�ͧ��Ъҡ÷�������ࢵ�Ѻ�Դ�ͺ";
        
        theHashMapReport.put(report.INDEX,report );

        report = null;
        report = new Report();
        
        report.INDEX = "5";
        report.ENG_NAME = "ReportPregnancePCU";
        report.THAI_NAME = "��§ҹ��ýҡ�����";
        report.DESCRIPTION = "��ª���˭ԧ��駤���� ������Ѻ��ԡ�� ANC �¡��������ҹ";
        
        theHashMapReport.put(report.INDEX,report );

        report = null;
        report = new Report();
       
        report.INDEX = "6";
        report.ENG_NAME = "ReportPersonMaimPCU";
        report.THAI_NAME = "��§ҹ���ԡ��";
        report.DESCRIPTION = "��ª��ͼ��ԡ�� ��ṡ��������������ԡ�� ��������ҹ";
        
        theHashMapReport.put(report.INDEX,report );
      
        report = null;
        report = new Report();
        
        report.INDEX = "7";
        report.ENG_NAME = "ReportEpiPCU";
        report.THAI_NAME = "��§ҹ����Ѻ�Ѥ�չ";
        report.DESCRIPTION = "��ª����硷���Ѻ�Ѥ�չ ��� �ӹǹ�ͧ����Ѻ�Ѥ�չ";
        
        theHashMapReport.put(report.INDEX,report );     
        
        report = null;
        report = new Report();
        
        report.INDEX = "8";
        report.ENG_NAME = "ReportFpPCU";
        report.THAI_NAME = "��§ҹ����ҧἹ��ͺ����";
        report.DESCRIPTION = "��ª������ͨӹǹ�ͧ��������Ѻ�ҧἹ��ͺ���� ��� ��ª��ͼ����������Ѻ����ҧἹ��ͺ����";
        
        theHashMapReport.put(report.INDEX,report );     
    
    }
    
    
    
}
