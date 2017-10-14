/*
 * NameReport.java
 *
 * Created on 3 ���Ҥ� 2548, 15:10 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.utility;
import java.util.*;
/**
 *
 * @author tong(Padungrat)
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
              
        report = null;
        report = new Report();
        
        report.INDEX = "1";
        report.ENG_NAME = "ReportDrugDispense";
        report.THAI_NAME = "��§ҹ����ҳ��������";
        report.DESCRIPTION = "����ҳ�������ҵ����ǧ���ҷ���˹� (����ҳ ��� ��Ť��)";
        
        theHashMapReport.put(report.INDEX,report );        
      
        report = null;
        report = new Report();
        
        report.INDEX = "2";
        report.ENG_NAME = "ReportChronic";
        report.THAI_NAME = "��§ҹ�������ä������ѧ";
        report.DESCRIPTION = "�������ä������ѧ (��-����) �¡������͢��� �͡���͢���";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "3";
        report.ENG_NAME = "ReportCostPayment";
        report.THAI_NAME = "��§ҹ��Ť�ҡ���ѡ��";
        report.DESCRIPTION = "��Ť�ҡ���ѡ�ҷ���Դ��鹨�ԧ�¡��������������������ҵðҹ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "4";
        report.ENG_NAME = "ReportRevenueAndExpense";
        report.THAI_NAME = "��§ҹ�ҧ����Թ";
        report.DESCRIPTION = "��§ҹ�ҧ����Թ�¡�� ����Ѻ�Թʴ ���¡�纨ҡ�Է��� �����ػ��������";
        
        theHashMapReport.put(report.INDEX,report );

        report = null;
        report = new Report();
        
        report.INDEX = "5";
        report.ENG_NAME = "ReportOutPatientPaymentINOUTCUP";
        report.THAI_NAME = "��§ҹ�ӹǹ�����¹͡(��/����)";
        report.DESCRIPTION = "�ӹǹ�����¹͡ (��/����) ����͢��� - �͡���͢��� �¡����Է�ԡ���ѡ��";
        
        theHashMapReport.put(report.INDEX,report );

        
        report = null;
        report = new Report();
        
        report.INDEX = "6";
        report.ENG_NAME = "ReportPatientInServicePoint";
        report.THAI_NAME = "��§ҹ��ª��ͼ����·���ҹ�ش��ԡ��";
        report.DESCRIPTION = "��§ҹ��ª��ͼ����·���ҹ�ش��ԡ��";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "7";
        report.ENG_NAME = "ReportClinicINOUTCup";
        report.THAI_NAME = "�ӹǹ�����¹͡�¡���Ἱ� (�� ����á���,���¡���)";
        report.DESCRIPTION = "��§ҹ�ӹǹ�����¹͡�¡���Ἱ� (�� ����á���,���¡���)";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "8";
        report.ENG_NAME = "ReportCostTotalGroupByOrder";
        report.THAI_NAME = "�������·���Դ��鹨�ԧ";
        report.DESCRIPTION = "�������·���Դ��鹨�ԧ �¡����������¡��";
        
        theHashMapReport.put(report.INDEX,report );     
        

        report = null;
        report = new Report();
        
        report.INDEX = "9";
        report.ENG_NAME = "ReportARIC";
        report.THAI_NAME = "ARIC (���ص�ӡ��� 5 ��)";
        report.DESCRIPTION = "��§ҹ ARIC (���ص�ӡ��� 5 ��)";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "10";
        report.ENG_NAME = "ReportPlentyDisease";
        report.THAI_NAME = "�ѹ�Ѻ�ä�����������ä�ҡ����ش";
        report.DESCRIPTION = "��§ҹ�ѹ�Ѻ�ä�����������ä�ҡ����ش ��� ICD10,ICD9 CM �����ǧ���ҷ���˹�";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "11";
        report.ENG_NAME = "ReportEyeDisease";
        report.THAI_NAME = "��§ҹ�ӹǹ�������ä��";
        report.DESCRIPTION = "��§ҹ�ӹǹ�������ä�� �¡���,˭ԧ";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "12";
        report.ENG_NAME = "ReportCostPaymentShareOFA7";
        report.THAI_NAME = "��§ҹ�������·���Է�Ԫ�������¡������㹹͡";
        report.DESCRIPTION = "��§ҹ�������·���Է�Ԫ�������¡�������/�͡";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "13";
        report.ENG_NAME = "ReportPatientAdminAndDischarge";
        report.THAI_NAME = "��§ҹ������㹷���Ѻ���� ��Ш�˹���";
        report.DESCRIPTION = "��§ҹ������㹷���Ѻ���� (Admit) ��Ш�˹��� (Disc) �����ǧ����˹�";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "14";
        report.ENG_NAME = "ReportPatientOverService";
        report.THAI_NAME = "��§ҹ��ª��ͼ����¹͡�������Ѻ��ԡ���ҡ���Ҩӹǹ���駷���к�";
        report.DESCRIPTION = "��§ҹ��ª��ͼ����¹͡�������Ѻ��ԡ���ҡ���Ҩӹǹ���駷���к�(�ӹǹ��� visit)";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "15";
        report.ENG_NAME = "ReportCostDrugInServicePoint";
        report.THAI_NAME = "�ӹǹ�ҷ����������Ъ�ǧ����";
        report.DESCRIPTION = "��§ҹ�ӹǹ�ҷ����������Ъ�ǧ����";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "16";
        report.ENG_NAME = "ReportOrderLab";
        report.THAI_NAME = "�ӹǹ��¡�� LAB ";
        report.DESCRIPTION = "�ӹǹ��¡�� LAB (��Ǩ�ͧ,�觵�Ǩ,�Ѻ��Ǩ)";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "17";
        report.ENG_NAME = "ReportAccident19Cause";
        report.THAI_NAME = "��§ҹ�غѵ��˵� 19 ���˵� ";
        report.DESCRIPTION = "�ӹǹ�������غѵ��˵� 19 ���˵�";
        
        theHashMapReport.put(report.INDEX,report );
    }
    
    
    
}
