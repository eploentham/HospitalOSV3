/*
 * Report18FileName.java
 *
 * Created on 11 สิงหาคม 2548, 14:14 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.utility;

/**
 *
 * @author Noom
 */
public class Report18FileName {
    public static int HEALTH_DEPT = 0;
    public static int NHSO = 1;
    
    public String  Anc = "";
    public String  Appoint = "";
    public String  Card = "";
    public String  Chronic = "";
    public String  Death = "";
    public String  Diag = "";
    public String  Drug = "";
    public String  Epi = "";
    public String  Fp = "";
    public String  Home = "";
    public String  Mch = "";
    public String  Nutrition = "";
    public String  Person = "";
    public String  Pp = "";
    public String  Proced = "";
    public String  Serveil = "";
    public String  Service = "";
    public String  Woman = "";

    public static Report18FileName SELECT_ALL = new Report18FileName("1");
    
    
    
    /**
     * Creates a new instance of Report18FileName 
     */
    public Report18FileName() {
    }
    public Report18FileName(String init) 
    {
        Anc = init;
        Appoint = init;
        Card = init;
        Chronic = init;
        Death = init;
        Diag = init;
        Drug = init;
        Epi = init;
        Fp = init;
        Home = init;
        Mch = init;
        Nutrition = init;
        Person = init;
        Pp = init;
        Proced = init;
        Serveil = init;
        Service = init;
        Woman = init;
    }
}
