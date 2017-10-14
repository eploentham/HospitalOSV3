/*
 * ManageSubject.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2549, 13:19 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.subject;

/**
 *
 * @author pu
 */
public class ManageSubject
{
    public ServicePointSubject theServicePointSubject;
    public ServicePointTypeSubject theServicePointTypeSubject;
    
    public NCDItemGroupMapItemSubject theNCDItemGroupMapItemSubject;
    public OPDItemSubject theOPDItemSubject;
    public OperatingItemSubject theOperatingItemSubject;
    public ReferHospitalSubject theReferHospitalSubject;
    /**
     * Creates a new instance of ManageSubject 
     */
    public ManageSubject()
    {
        theServicePointSubject = new ServicePointSubject();
        theServicePointTypeSubject = new ServicePointTypeSubject();
        
        theNCDItemGroupMapItemSubject = new NCDItemGroupMapItemSubject();
        theOPDItemSubject = new OPDItemSubject();
        theOperatingItemSubject = new OperatingItemSubject();
        theReferHospitalSubject = new ReferHospitalSubject();
    }
    
}
