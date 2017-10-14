/*
 * HosDB.java
 *
 * Created on 7 �ѹ��¹ 2548, 15:50 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objdb;
import com.report12file.objdb.ClinicDB;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.report12file.objdb.ReferDB;

/**
 *
 * @author tong(Padungrat)
 */
public class HosDB {
    
    public ConnectionInf theConnectionInf;
    public ClinicDB theClinicDB;
    public Clinic12FilesDB theClinic12FilesDB;
    public ClinicMapDB theClinicMapDB;
    public AerDB theAerDB;
    public ChaDB theChaDB;
    public ChtDB theChtDB;
    public IdxDB theIdxDB;
    public InsDB theInsDB;
    public IopDB theIopDB;
    public IpdDB theIpdDB;
    public IrfDB theIrfDB; //�ջѭ��
    public OdxDB theOdxDB;
    public OopDB theOopDB;  
    public OpdDB theOpdDB;
    public PatDB thePatDB;
    public OrfDB theOrfDB; // �ջѭ������ͧ clic ���١����¹ ���е��ҧ�������ա������¹�ŧ���
    
    public ReferDB thereferDB;
    public HosDB(ConnectionInf connection) {
        theConnectionInf = connection;
        thereferDB = new ReferDB(theConnectionInf);
        theAerDB = new AerDB(theConnectionInf,thereferDB);
        theChaDB = new ChaDB(theConnectionInf);
        theChtDB = new ChtDB(theConnectionInf);
        theIdxDB = new IdxDB(theConnectionInf);  
        theInsDB = new InsDB(theConnectionInf);  
        theIopDB = new IopDB(theConnectionInf);  
        theIpdDB = new IpdDB(theConnectionInf);  
        theIrfDB = new IrfDB(theConnectionInf);  
        theOdxDB = new OdxDB(theConnectionInf);  
        theOopDB = new OopDB(theConnectionInf);  
        theOpdDB = new OpdDB(theConnectionInf); 
        theOrfDB = new OrfDB(theConnectionInf); 
        thePatDB = new PatDB(theConnectionInf); 

        
        theClinicDB = new ClinicDB(theConnectionInf);
        theClinicMapDB = new ClinicMapDB(theConnectionInf);
        theClinic12FilesDB = new Clinic12FilesDB(theConnectionInf);        
        
    }
    
}
