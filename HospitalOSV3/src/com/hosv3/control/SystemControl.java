/*
 * SystemControl.java
 *
 * Created on 25 ���Ҥ� 2546, 13:51 �.
 */
package com.hosv3.control;
import com.hospital_os.gui.connection.AbsVersionControl;
import com.hospital_os.gui.connection.AbsVersionControl3;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.CryptPassword;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.XPersistent;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import com.hospital_os.utility.IOStream;
import com.hospital_os.utility.Secure;
import com.hosv3.control.thread.*;
import com.hosv3.gui.dialog.DialogSendError;
import com.hosv3.utility.Constant;

//import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.utility.ConnectionDBMgr;
import com.hosv3.utility.Splash;
import com.hosv3.utility.connection.UpdateStatus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.hospital_os.object.Version;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.MailSplashScreen;
/**
 *
 * @author  henbe
 */
public class SystemControl {
    ConnectionInf theConnectionInf;
    HosDB theHosDB;
    HosObject theHO;
    HosSubject theHS;
    LookupControl theLookupControl;
    SolveEmptyFamilyThread sef = new SolveEmptyFamilyThread();
    SolveEmptyHCISThread solveEmpHCIS = new SolveEmptyHCISThread();
    SolveHNPatternThread solveHNPat = new SolveHNPatternThread();
    SolveDeprecatedVNThread solveDepVN = new SolveDeprecatedVNThread();
    SolveDepNameSNamePID solveDepPID = new SolveDepNameSNamePID();
    private UpdateStatus theUS;
    private int button_family;
    public static final String SQL_CHECK_PATIENT_NO_FAMILY = " select t_patient.* from t_patient "
                    + " left join t_health_family on t_health_family.t_health_family_id = t_patient.t_health_family_id"
                    + " where t_health_family.t_health_family_id is null";
    public static final String SQL_COUNT_PATIENT_NO_FAMILY = "select count(*) from ("
                    + " select t_patient.patient_hn,t_patient.t_health_family_id from t_patient "
                    + " left join t_health_family on t_health_family.t_health_family_id = t_patient.t_health_family_id"
                    + " where t_health_family.t_health_family_id is null) q ";
    /** Creates a new instance of LookupControl */
    public SystemControl(ConnectionInf con, HosObject ho, HosDB hdb, HosSubject hs) {
        theConnectionInf = con;
        theHosDB = hdb;
        theHS = hs;
        theHO = ho;
    }
    public WSConfig readWSConfig(String id)
    {
        WSConfig wsConfig = null;
        try
        {
            wsConfig = this.theHosDB.theWSConfigDB.selectByID(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return wsConfig;
    }
    public int saveWSConfig(WSConfig wsConfig)
    {
        int res = 0;
        try
        {
            if(wsConfig.user_name.equals(""))
                return 3;
            if(wsConfig.password.equals(""))
                return 4;
            if(wsConfig.getObjectId()==null)
            {
                res = this.theHosDB.theWSConfigDB.insert(wsConfig);
            }
            else
            {
                res = this.theHosDB.theWSConfigDB.update(wsConfig);
            }
            this.theHO.theWSConfig = wsConfig;
            this.theUS.setStatus("�ѹ�֡�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            this.theUS.setStatus("�ѹ�֡�����żԴ��Ҵ",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        return res;
    }
    public Vector listNhsoRight()
    {
        Vector v = null;
        try
        {
            v = theHosDB.theNhsoRightDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public Vector listNhsoSubInscl()
    {
        Vector v = null;
        try
        {
            v = theHosDB.theNhsoSubInsclDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public Vector listNhsoMainInscl()
    {
        Vector v = null;
        try
        {
            v = theHosDB.theNhsoMainInsclDB.selectExceptWEL();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public NhsoRight selectNhsoRightByDes(String des)
    {
        if(des==null)
            des = "";
        NhsoRight v = null;
        try
        {
            v = theHosDB.theNhsoRightDB.selectByDes(des);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public int saveNhsoRight(NhsoRight nhsoRight)
    {
        int res = 0;
        try
        {
            if(nhsoRight.des.equals(""))
                return 3;
            if(nhsoRight.getObjectId()==null)
            {
                res = this.theHosDB.theNhsoRightDB.insert(nhsoRight);
            }
            else
            {
                res = this.theHosDB.theNhsoRightDB.update(nhsoRight);
            }
            this.theUS.setStatus("�ѹ�֡�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            this.theUS.setStatus("�ѹ�֡�����żԴ��Ҵ",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        return res;
    }
    public int deleteNhsoRight(NhsoRight nhsoRight)
    {
        int res = 0;
        try
        {
            res = this.theHosDB.theNhsoRightDB.delete(nhsoRight);
            this.theUS.setStatus("ź�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            this.theUS.setStatus("ź�����żԴ��Ҵ",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        return res;
    }
    public int saveMapNhsoPlan(MapNhsoPlan mnp)
    {
        int res = 0;
        try
        {
            if(mnp.getObjectId()==null)
            {
                res = this.theHosDB.theMapNhsoPlanDB.insert(mnp);
            }
            else
            {
                res = this.theHosDB.theMapNhsoPlanDB.update(mnp);
            }
            this.theUS.setStatus("�ѹ�֡�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            this.theUS.setStatus("�ѹ�֡�����żԴ��Ҵ",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        return res;
    }
    public int saveMapNhsoSubInscl(MapNhsoSubInscl mnsi)
    {
        int res = 0;
        try
        {
            if(mnsi.getObjectId()==null)
            {
                res = this.theHosDB.theMapNhsoSubInsclDB.insert(mnsi);
            }
            else
            {
                res = this.theHosDB.theMapNhsoSubInsclDB.update(mnsi);
            }
            this.theUS.setStatus("�ѹ�֡�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            this.theUS.setStatus("�ѹ�֡�����żԴ��Ҵ",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        return res;
    }
    public int saveMapNhsoMainInscl(MapNhsoMainInscl mnsi)
    {
        int res = 0;
        try
        {
            if(mnsi.getObjectId()==null)
            {
                res = this.theHosDB.theMapNhsoMainInsclDB.insert(mnsi);
            }
            else
            {
                res = this.theHosDB.theMapNhsoMainInsclDB.update(mnsi);
            }
            this.theUS.setStatus("�ѹ�֡�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            this.theUS.setStatus("�ѹ�֡�����żԴ��Ҵ",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        return res;
    }
    public int deleteNhsoSubInscl(MapNhsoSubInscl mnsi)
    {
        int res = 0;
        try
        {
            res = this.theHosDB.theMapNhsoSubInsclDB.delete(mnsi);
            this.theUS.setStatus("ź�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            this.theUS.setStatus("ź�����żԴ��Ҵ",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        return res;
    }
    public int deleteNhsoMainInscl(MapNhsoMainInscl mnsi)
    {
        int res = 0;
        try
        {
            res = this.theHosDB.theMapNhsoMainInsclDB.delete(mnsi);
            this.theUS.setStatus("ź�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            this.theUS.setStatus("ź�����żԴ��Ҵ",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        return res;
    }
    public Vector listMapNhsoPlanByNhsoRightID(String id)
    {
        Vector v = null;
        try
        {
            v = this.theHosDB.theMapNhsoPlanDB.selectByNhsoRightID(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public Vector listMapNhsoSubInsclByNhsoRightID(String id)
    {
        Vector v = null;
        try
        {
            v = this.theHosDB.theMapNhsoSubInsclDB.selectByNhsoRightID(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public Vector listMapNhsoSubInsclByNhsoRightIDAndType(String id,String type)
    {
        Vector v = null;
        try
        {
            v = this.theHosDB.theMapNhsoSubInsclDB.selectByNhsoRightIDAndType(id, type);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public Vector listMapNhsoMainInsclByNhsoRightIDAndType(String id,String type)
    {
        Vector v = null;
        try
        {
            v = this.theHosDB.theMapNhsoMainInsclDB.selectByNhsoRightIDAndType(id, type);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public Vector listMapNhsoMainInsclByNhsoRightID(String id)
    {
        Vector v = null;
        try
        {
            v = this.theHosDB.theMapNhsoMainInsclDB.selectByNhsoRightID(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public boolean saveNews(String[] value) {
//            ,"select id,topic as des,text as text from b_site_news"
//            ," "
//            ," delete from b_site_news where id like ?"
        Constant.println(UseCase.UCID_saveNews);
        String objectid =   null;
        theConnectionInf.open();
        try {
            theHO.date_time = this.theLookupControl.intReadDateTime();
            String sql = "insert into b_site_news values (?"
                    + ",'" + theHO.date_time
                    + "','" + theHO.theEmployee.getObjectId()
                    + "',?,?)";
            PreparedStatement ps = theConnectionInf.getConnection().prepareStatement(sql);
            ps.setString(1, value[0] + theHO.date_time);
            ps.setString(2, value[0] + theHO.date_time);
            ps.setString(3, value[1]);
            ps.executeUpdate();
            theUS.setStatus(Constant.getTextBundle("��úѹ�֡�����Ţ������") + " " +
                    Constant.getTextBundle("�������"), UpdateStatus.COMPLETE);
            setStatus(UseCase.TH_saveNews,UpdateStatus.COMPLETE,null);
//            saveLog(UseCase.UCID_saveNews,objectid,null,UpdateStatus.COMPLETE);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            setStatus(UseCase.TH_saveNews,UpdateStatus.ERROR,ex);
            saveLog(UseCase.UCID_saveNews,objectid,ex,UpdateStatus.ERROR);
            return false;
        } finally {
            theConnectionInf.close();
        }
    }

    public boolean deleteNews(String id) {

        if (!theUS.confirmBox(("�׹�ѹ���ź�����Ţ�����¡�ù��"), UpdateStatus.WARNING)) {
            return false;

        }
        theConnectionInf.open();
        try {
            String sql = "delete from b_site_news where id like '" + id + "'";
            theConnectionInf.eUpdate(sql);
            theUS.setStatus(Constant.getTextBundle("���ź�����Ţ������") + " " +
                    Constant.getTextBundle("�������"), UpdateStatus.COMPLETE);
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("���ź�����Ţ������") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"), UpdateStatus.ERROR);
            return false;
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listNews(String text) {

        theConnectionInf.open();
        try {
            //henbe comment 100253 kong �����÷������Ӥ������� sql �����������ǡ������١�����ѡ��ù��
            String key[] = text.split(" ");
            String sqllookup = "select id,topic as des,text||' '||topic as text from b_site_news";
            String sql = "select * from (" + sqllookup + ") as query where text ilike '%" + key[0] + "%'";
            for (int i = 1; key.length > 1 && i < key.length; i++) {
                sql += " and text ilike '%" + key[i] + "%' ";

            }
            sql += "order by des limit 50 ";
//            String sql = "select id,topic as des,text as text " +
//                    "from b_site_news where " +
//                    "topic ilike '%" + text + "%' or " +
//                    "text ilike '%" + text + "%'  " +
//                    "order by date_time desc limit 50 ";
//            System.out.println(sql);
            ResultSet rs = theConnectionInf.eQuery(sql);
            Vector vData = new Vector();
            while (rs.next()) {
                String[] data = new String[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)};
                vData.add(data);
            }
            return vData;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public void setDepControl(LookupControl lc) {
        theLookupControl = lc;
        XPersistent x = new XPersistent();
        x.setHospitalSiteId(theLookupControl.readSite().off_id);
    }

    public void setUpdateStatus(UpdateStatus us) {
        theUS = us;
    }

    public void setWardLogin(Object selectedItem) {
        theHO.theWard = (Ward) selectedItem;
    }

    /**
     *@author henbe
     *��ѭ�� VN ���������������¹���Ţ VN ����ش����
     *
     */
    public int solveEmptyHCIS(UpdateStatus theUS) {
        try {
            if (!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ�����ѭ�� HN_HCIS �繤����ҧ"), UpdateStatus.WARNING)) {
                return 0;


            }
            if (solveEmpHCIS.isAlive()) {
                theUS.setStatus(("��й�����������ҧ��ô��Թ���"), UpdateStatus.WARNING);
                return 0;
            } else {
                solveEmpHCIS = new SolveEmptyHCISThread();
                solveEmpHCIS.setControl(theConnectionInf, theHosDB, theHO, theUS, null);
            }

            solveEmpHCIS.start();

            return 1;
        } catch (Exception e) {
            this.theUS.setStatus(Constant.getTextBundle("�����ѭ�� HN_HCIS �繤����ҧ") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"), UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
            return 0;
        }
    }

    /**
     *@author henbe
     *��ѭ�� VN ���������������¹���Ţ VN ����ش����
     */
    public int solveDeprecatedVN(UpdateStatus theUS) {
        try {
            if (!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ�����ѭ�� VN ���"), UpdateStatus.WARNING)) {
                return 0;


            }
            if (solveDepVN.isAlive()) {
                theUS.setStatus(("��й�����������ҧ��ô��Թ���"), UpdateStatus.WARNING);
                return 0;
            } else {
                solveDepVN = new SolveDeprecatedVNThread();
                solveDepVN.setControl(theConnectionInf, theHosDB, theHO, theUS, null);
            }
            solveDepVN.start();

            return 1;
        } catch (Exception e) {
            theUS.setStatus(Constant.getTextBundle("�����ѭ�� VN ���") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"), UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
            return 0;
        }
    }

    /**
     *@author henbe
     *��ѭ�� �����š�õ��������Ţ��Ъҡ�
     */
    public void solveEmptyFamily() {
        try {
            if (!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ�����ѭ�һ�Ъҡ��繤����ҧ"), UpdateStatus.WARNING)) {
                return;


            }
            if (sef.isAlive()) {
                theUS.setStatus(("��й�����������ҧ��ô��Թ���"), UpdateStatus.WARNING);
                return;
            } else {
                sef = new SolveEmptyFamilyThread();
                sef.setControl(theConnectionInf, theHosDB, theHO, theUS, null);
                sef.setTotalNumber(button_family);
            }

//            theConnectionInf.open();
            sef.start();

        } catch (Exception e) {
            theUS.setStatus(Constant.getTextBundle("�����ѭ�һ�Ъҡ��繤����ҧ") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"), UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
            return;
        }
    }

    /**
     *@author henbe
     *��ѭ�� �����š�õ��������Ţ��Ъҡ�
     */
    public int solveHNPattern(UpdateStatus theUS) {
        try {
            if (!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ�����ѭ�� HN ��� Pattern ����˹�"), UpdateStatus.WARNING)) {
                return 0;


            }
            if (solveHNPat.isAlive()) {
                theUS.setStatus(Constant.getTextBundle("��й�����������ҧ��ô��Թ���"), UpdateStatus.WARNING);
                return 0;
            } else {
                solveHNPat = new SolveHNPatternThread();
                solveHNPat.setControl(theConnectionInf, theHosDB, theHO, theUS, theLookupControl);
            }

            solveHNPat.start();
            return 1;
        } catch (Exception e) {
            this.theUS.setStatus(Constant.getTextBundle("�����ѭ�� HN ��� Pattern ����˹�") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"), UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
            return 0;
        }
    }

    /**
     *@author henbe
     *��ѭ�� VN ���������������¹���Ţ VN ����ش����
     */
    public int solveDepNameSNamePID(UpdateStatus theUS) {
        try {
            if (!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ�����ѭ�� ����-ʡ�� ����Ţ�ѵë��"), UpdateStatus.WARNING)) {
                return 0;


            }
            if (solveDepPID.isAlive()) {
                theUS.setStatus(("��й�����������ҧ��ô��Թ���"), UpdateStatus.WARNING);
                return 0;
            } else {
                solveDepPID = new SolveDepNameSNamePID();
                solveDepPID.setControl(theConnectionInf, theHosDB, theHO, theUS, null);
            }
            solveDepPID.start();

            return 1;
        } catch (Exception e) {
            theUS.setStatus(Constant.getTextBundle("�����ѭ�� VN ���") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"), UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
            return 0;
        }
    }
    /*
     *@author Pongtorn (Henbe)
     *@name ��˹���������Ѻ�к������������������§�������
     */

    public void setSoundEnabled(boolean enable) {
        theHO.is_sound_enabled = enable;
    }
    /*
     *@author Pongtorn (Henbe)
     *@name ��ҹ��ҵ���âͧ�к������������§�������
     */

    public boolean isSoundEnabled() {
        return theHO.is_sound_enabled;
    }

    public boolean loginSetup(String uname, char[] passwd, Object sp) {
        boolean ret = false;
        theConnectionInf.open();
        try {
            ret = intLogin(uname, passwd, sp);
            theHS.theSystemSubject.notifyLoginSetup(Constant.getTextBundle("�����͡�Թ�ͧ������") + " " +
                    Constant.getTextBundle("�������"), com.hosv3.utility.connection.UpdateStatus.COMPLETE);
        } catch (Exception e) {
            theHS.theSystemSubject.notifyLoginSetup(Constant.getTextBundle("�����͡�Թ�ͧ������") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"), com.hosv3.utility.connection.UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return ret;
    }

    public boolean login(String uname, char[] passwd, Object sp) {
        boolean ret = false;
        theConnectionInf.open();
        try {
            ret = intLogin(uname, passwd, sp);
            theHS.theSystemSubject.notifyLogin(Constant.getTextBundle("�����͡�Թ�ͧ������") + " " +
                    Constant.getTextBundle("�������"), com.hosv3.utility.connection.UpdateStatus.COMPLETE);
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            theUS.confirmBox("�������ö��¹���������سҵ�Ǩ�ͺ�ҹ�����ŷ��Դ����ա����", UpdateStatus.WARNING);
            System.exit(0);
        } finally {
            theConnectionInf.close();
        }
        return ret;
    }

    /**
     *���� save ���� ��� logout ��лŴ��͡������
     */
    public void exitProgram() {
        saveProgram();
        System.exit(0);
    }

    public void restartProgram() {
        saveProgram();
    }

    public void saveProgram() {
        theConnectionInf.open();
        try {
            String date_time = theLookupControl.intReadDateTime();
            if (theHO.theVisit != null
                    && theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId())) {
                if (theHO.theVisit != null) {
                    theHO.theVisit.locking = "0";
                    theHosDB.theVisitDB.updateLocking(theHO.theVisit);
                }
                if (theHO.theListTransfer != null) {
                    theHO.theListTransfer.locking = "0";
                    theHosDB.theQueueTransferDB.updateLock(theHO.theListTransfer);
                }
            }
            theHO.theEmployee.last_logout = date_time;
            theHosDB.theEmployeeDB.updateLogout(theHO.theEmployee);
        } catch (Exception ee) {
            ee.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
    }

    private boolean intLogin(String uname, char[] passwd, Object sp)
            throws Exception {
        boolean ret = false;
        String date_time = theLookupControl.intReadDateTime();
        Employee e = null;
        if (uname == null && passwd == null && sp == null) {
            //�����ҹ���ҹ����礨ҡ˹�� login ��������ͧ���ա�����¡�� checkUser();
            e = theHO.theEmployee;
        }
        if (uname != null && uname.equals("")) {
            //�����ҹ���ҹ����礨ҡ˹�� login ��������ͧ���ա�����¡�� checkUser();
            e = theHO.theEmployee;
        }
        if (e == null) {
            e = theHosDB.theEmployeeDB.selectByUsername(uname);

        }
        if (sp == null) {
            sp = (ServicePoint) theHosDB.theServicePointDB.selectByPK(e.default_service_id);


        }
        ret = true;
        e.last_login = date_time;
        theHosDB.theEmployeeDB.updateLogout(e);
        theHO.theServicePoint = (ServicePoint) sp;
        theHO.theEmployee = theHosDB.theEmployeeDB.selectByPK(e.getObjectId());
        Vector v = theHosDB.theGActionAuthDB.selectByAid(e.authentication_id);
        theHO.theGActionAuthV = new GActionAuthV(v);
        return ret;
    }

    public Employee checkUser(String uname, char[] passwd, Object sp) {
        theConnectionInf.open();
        try {
            Employee e = this.readEmpByUser(uname);
            if (e != null) {
                theHO.theEmployee = e;
//                Constant.println("11111111111111111111111111111111111111112"+e.authentication_id);
                theHO.theServicePoint = (ServicePoint) sp;
                String pass = CryptPassword.encryptText(String.valueOf(passwd));//se.encode(new String(passwd));

                if (e.password.equals(pass)) {
                    return e;

                } else {
                    return null;

                }
            }

            if (Gutil.checkSecureAdmin(uname, String.valueOf(passwd))) //if(uname.equals("opensource"))
            {
                e = new Employee();
                e.setObjectId("opensource");
                e.employee_id = "OpenSourceTechnology";
                e.fname = "OpenSource";
                e.lname = "Technology";
                e.authentication_id = "10";
                theHO.theServicePoint = (ServicePoint) sp;
                theHO.theEmployee = e;
//                Constant.println("11111111111111111111111111111111111111113");
                return e;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public int checkVersion(Version version) {
        int result = 0;
        theConnectionInf.open();
        try {
            Version theDB = theHosDB.theVersionDB.selectbyDbCode(version);
            if (theDB.app_code.equals(version.app_code)) {
                result = 1;

            }
            theConnectionInf.close();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            result = 0;
        }
        return result;
    }

    public Version getDbVersion() {
        Version theVersion = null;
        Vector vc = null;
        theConnectionInf.open();
        try {
            vc = theHosDB.theVersionDB.selectAllVersion();
            if (vc != null) {
                theVersion = (Version) vc.get(0);
                theVersion.db_code = theVersion.db_code.substring(0, theVersion.db_code.lastIndexOf('.'));
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theVersion;
    }

    /**
     * ��Ǩ�ͺ������ҧ Field �ͧ�ҹ������
     */
    public String getBuildProgramNumber() {
        return Constant.getTextBundleConfig("BUILD_PROGRAM_NUMBER");
    }

    public Version getAppVersion() {
        Version version = new Version();
        version.app_code = Constant.getTextBundleConfig("APP_CODE");
        version.db_code = Constant.getTextBundleConfig("DB_CODE");
        version.db_code = version.db_code.substring(0, version.db_code.lastIndexOf('.'));
        return version;
    }

    public void insertVersionBuild7() {
        theConnectionInf.open();
        try {
            if (theHosDB.theVersionDB.insertVersionBuild7()) {
                Constant.println("Insert Index for build 7");
                theHosDB.theBillingDB.checkField();
                theHosDB.theBillingInvoiceDB.checkField();
                theHosDB.theReceiptDB.checkField();
            }
        } catch (Exception ex) {
        }
        theConnectionInf.close();
    }

    public void insertVersionBuild8() {
        theConnectionInf.open();
        try {
            if (theHosDB.theVersionDB.insertVersionBuild8()) {
                Constant.println("Insert Index for build 8");
                theHosDB.theOrderItemDB.checkIndex();
                theHosDB.theOrderItemDrugDB.checkIndex();
                theHosDB.theDrugAllergyDB.checkIndex();
            }
        } catch (Exception ex) {
        }
        theConnectionInf.close();
    }

    public void insertVersionBuild8_1() {
        VersionDB theVersionDB = new VersionDB(theConnectionInf);
        DrugDosePrintDB theDrugDosePrintDB = new DrugDosePrintDB(theConnectionInf);
        DrugDoseMapUomDB theDrugDoseMapUomDB = new DrugDoseMapUomDB(theConnectionInf);

        theConnectionInf.open();
        try {

            if (theVersionDB.insertVersionBuild8_1()) {
                Constant.println("Insert Index for build 8.1");

                theDrugDosePrintDB.createTableForBuild8_1();
                theDrugDosePrintDB.insertDataToTableForBuild8_1();
                ////////////////////
                theDrugDoseMapUomDB.createTableForBuild8_1();
            }
        } catch (Exception ex) {

        }

        theDrugDoseMapUomDB = null;
        theDrugDosePrintDB = null;
        theConnectionInf.close();

    }
/**
 * @deprecated henbe unused
 */
    public void insertVersionBuild8_2() {
        VersionDB theVersionDB = new VersionDB(theConnectionInf);
        VitalTemplateDB theVitalTemplateDB = new VitalTemplateDB(theConnectionInf);
        VisitDB theVisitDB = new VisitDB(theConnectionInf);
        AuthenticationDB theAuthenticationDB = new AuthenticationDB(theConnectionInf);
        OptionDB theOptionDB = new OptionDB(theConnectionInf);
        GuideAfterDxTransactionDB theGuideAfterDxTransactionDB = new GuideAfterDxTransactionDB(theConnectionInf);
        theConnectionInf.open();
        try {
            if (theVersionDB.insertVersionBuild8_2()) {
                Constant.println("Insert Index for build 8.2");
                Constant.print("Alter Table Field size to Max....");
                theVitalTemplateDB.alterTableFieldToV2B82();
                Constant.println("ok");
                Constant.print("Add Field in Visit.......");
                theVisitDB.alterTableFieldToV2B82();
                Constant.println("ok");
                Constant.print("Insert Data .......");
                theAuthenticationDB.insertAuthenticationIPD();
                Constant.println("ok");
                Constant.print("Add Field in setup_authorization.......");
                theOptionDB.addFieldV2B82();
                Constant.print("ok");
                Constant.print("Alter Table Field size to Max....");
                theGuideAfterDxTransactionDB.alterTableFieldToV2B82();
                Constant.println("ok");

            }
        } catch (Exception ex) {
        }
        theVitalTemplateDB = null;
        theVisitDB = null;
        theAuthenticationDB = null;
        theOptionDB = null;
        theConnectionInf.close();
    }

    public void insertVersionBuild8b() {
        VersionDB theVersionDB = new VersionDB(theConnectionInf);

        OrderItemDB theOrderItemDB = new OrderItemDB(theConnectionInf);
        OrderItemDrugDB theOrderItemDrugDB = new OrderItemDrugDB(theConnectionInf);
        DrugAllergyDB theDrugAllergyDB = new DrugAllergyDB(theConnectionInf);
        QueueTransferDB theQueueTransferDB = new QueueTransferDB(theConnectionInf);
        theConnectionInf.open();
        try {
            if (theVersionDB.insertVersionBuild8b()) {
                Constant.println("Insert Index for build 8b");
                /**build8*/
                theOrderItemDB.checkIndex();
                theOrderItemDrugDB.checkIndex();
                theDrugAllergyDB.checkIndex();

                theQueueTransferDB.createTableForBuild8();

            }


        } catch (Exception ex) {

        }
        theVersionDB = null;
        theOrderItemDB = null;
        theOrderItemDrugDB = null;
        theDrugAllergyDB = null;
        theQueueTransferDB = null;
        theConnectionInf.close();
    }

    /**
     *@deprecated henbe ������ҹ�ѧ�ѹ�����������ѹ������ҹ��÷ӧҹ�ͧ��� ������������ѹ���١
     */
    public void insertVersion3_1() {
        theConnectionInf.open();
        try {
            if (theHosDB.theVersionDB.insertVersion3_1()) {
                Constant.println("Update to Version 3.1");
                Runtime.getRuntime().exec("");
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
    }
//    /**
//     *@deprecated henbe unused
//     */
//    public boolean checkVersion(javax.swing.JFrame frm,boolean show)
//    {
//        return checkVersion(frm,show,false);
//    }

    public boolean checkVersion(javax.swing.JFrame frm, boolean show, boolean setup_mode) {
        Version app_version = getAppVersion();
        Version db_version = getDbVersion();
        if (!db_version.db_code.equals(app_version.db_code) || show) {
            javax.swing.JOptionPane.showMessageDialog(frm, "<HTML><H4>" + Constant.getTextBundle("��س���������������СѺ�ҹ������") +
                    " " +
                    Constant.getTextBundle("����") + " " +
                    Constant.getTextBundle("�駼������к�") + "</H4> "
                    + "<BR>" + Constant.getTextBundle("��й������������ѹ") + " " + app_version.app_code
                    + "<BR>" + Constant.getTextBundle("�е�ͧ��Ѻ�ҹ�����������ѹ") + " " + app_version.db_code
                    + "<BR>" + Constant.getTextBundle("�袳йհҹ�����Ţͧ�ç��Һ�������ѹ") + " " + db_version.db_code
                    + "<BR></HTML>", Constant.getTextBundle("��õԴ��Ͱҹ�����żԴ��Ҵ"), javax.swing.JOptionPane.OK_OPTION);
            return false;
        }
        Constant.println("*****************************************");
        Constant.println(" Program Version : " + app_version.app_code);
        Constant.println(" Match Database Version: " + app_version.db_code);
        Constant.println(" Database Version: " + db_version.db_code);
        Constant.println(" Match Program Version: " + db_version.app_code);
        Constant.println(" Update Time      : " + db_version.update_time);
        Constant.println("*****************************************");
        theHO.theVersion = app_version;
        return true;
    }
    public boolean checkAlert(JFrame frm) {

            int row = checkFamilyButton();
            if (row > 0) {
                javax.swing.JOptionPane.showMessageDialog(frm, Constant.getTextBundle("��سҡ����� Family �ҡ˹�Ҩͼ������к����ǹ�Ţ�ӴѺ")
                        + "\n " +
                        Constant.getTextBundle("������䢢����Ż�Ъҡ��繤����ҧ�ӹǹ") +
                        " " + row + " " +
                        Constant.getTextBundle("��¡��"),
                        Constant.getTextBundle("��õ�Ǩ�ͺ�ҹ������"), javax.swing.JOptionPane.OK_OPTION);
            }
            row = checkHCISButton();
            if (row > 0) {
                javax.swing.JOptionPane.showMessageDialog(frm, Constant.getTextBundle("��سҡ����� HCIS �ҡ˹�Ҩͼ������к����ǹ�Ţ�ӴѺ")
                        + "\n " +
                        Constant.getTextBundle("��������Ţ��Ъҡ����ú 6 ��ѡ") +
                        " " +
                        Constant.getTextBundle("�ӹǹ") +
                        " " + row + " " +
                        Constant.getTextBundle("��¡��"),
                        Constant.getTextBundle("��õ�Ǩ�ͺ�ҹ������"), javax.swing.JOptionPane.OK_OPTION);
            }
            row = checkHCISNumber();
            if (row > 0) {
                javax.swing.JOptionPane.showMessageDialog(frm, Constant.getTextBundle("���������Ţ HN_HCIS ���") +
                        " " +
                        Constant.getTextBundle("����觼ŵ�͡���͡��§ҹ 18 ���")
                        + "\n " +
                        Constant.getTextBundle("��س��駼������к�����") +
                        " " +
                        Constant.getTextBundle("������ HCIS �˹�Ҩͼ������к�"),
                        Constant.getTextBundle("��õ�Ǩ�ͺ�ҹ������"), javax.swing.JOptionPane.OK_OPTION);
            }
            return true;
    }

    /**
     *@deprepated henbe unused
     */
    public boolean updateVersion(javax.swing.JFrame frm, boolean show) {
        Version app_version = getAppVersion();
        Version db_version = getDbVersion();
        int update_ret;
        if (db_version.app_code.equals(app_version.app_code) || show) {
            String warning = "<HTML>" + Constant.getTextBundle("��Ǩ�ͺ�����ѹ�ͧ�ҹ�����šѺ�����") +
                    " " +
                    Constant.getTextBundle("�š�õ�Ǩ�ͺ�繴ѧ���")
                    + "  <BR><BR> " + Constant.getTextBundle("�ҹ�����Ţͧ�ç��Һ��") +
                    " " +
                    Constant.getTextBundle("version") + " "
                    + db_version.db_code + "<BR><BR> "
                    + Constant.getTextBundle("��Ѩ�غѹ����������ѹ") + " " + app_version.app_code
                    + " " + Constant.getTextBundle("��Ѻ�ҹ�����������ѹ") + " " + app_version.db_code;
            boolean can_update = false;
            if (app_version.db_code.compareTo(db_version.db_code) > 0) {
                warning = warning + "<BR><BR>" + Constant.getTextBundle("��ͧ��û�Ѻ��ا�ҹ����������������ѹ����ش");
                can_update = true;
            } else if (app_version.db_code.compareTo(db_version.db_code) == 0) {
                warning = warning + "<BR><BR>" + Constant.getTextBundle("�����ѹ�ç�ѹ��������ͧ�ա������¹�ŧ��");
            } else {
                warning = warning + "<BR><BR>" + Constant.getTextBundle("��س�������������Ѻ�ҹ�����������ѹ���");
            }
            update_ret = javax.swing.JOptionPane.showConfirmDialog(frm, warning, Constant.getTextBundle("����͹"), javax.swing.JOptionPane.YES_NO_OPTION);
            Constant.println("*****************************************");
            Constant.println(" " + db_version.description);
            Constant.println(" Version Program  : " + db_version.app_code);
            Constant.println(" Version Database : " + db_version.db_code);
            Constant.println(" Build Version    : " + getBuildProgramNumber());
            Constant.println(" Update Time      : " + db_version.update_time);
            Constant.println("*****************************************");
            if (can_update && update_ret == javax.swing.JOptionPane.YES_OPTION) {
                insertVersionBuild7();
                insertVersionBuild8b();
                insertVersionBuild8();
                insertVersionBuild8_1();
                insertVersionBuild8_2();
                insertVersion3_1();
            }
        }
        Constant.println("*****************************************");
        Constant.println(" " + db_version.description);
        Constant.println(" Version Program  : " + db_version.app_code);
        Constant.println(" Version Database : " + db_version.db_code);
        Constant.println(" Build Version    : " + getBuildProgramNumber());
        Constant.println(" Update Time      : " + db_version.update_time);
        Constant.println("*****************************************");
        theHO.theVersion = app_version;

        return true;
    }
    public StringBuffer updateResult = new StringBuffer();

    /**
     * ��Ѻ��ا�ҹ�����Ţͧ�������ѡ�ͧ hos ��觨л�ҡ������ login
     *  �����͹䢴ѧ���仹��
     *  admin �����Է������¹�ç���ҧ�ҹ����������ҹ��
     *  user ��������Է������¹�ç���ҧ�ҹ������
     *  ��ͧ�ѹ��� delete ���� drop ���������ö����
     *  �������¹�ŧ�ç���ҧ�ҹ�����Ũ��ռŵ�͡������¹�ŧ�����ѹ�ͧ����ͧ�١����
     * @param frm
     * @param show
     * @param spl
     * @return
     */
    public boolean setVersion(javax.swing.JFrame frm, boolean show, Splash spl) {
        System.out.println("public boolean setVersion(javax.swing.JFrame frm,boolean show,Splash spl)");
        try {
            theConnectionInf.open();
            AbsVersionControl3 cvheal = new CheckVersionHealthy(theHosDB);
            AbsVersionControl3 cvpcu = new CheckVersionPcu(theHosDB);
            AbsVersionControl3 cvhos = new CheckVersionHos(theHosDB);
            AbsVersionControl3 cvreport = new CheckVersionReport(theConnectionInf);
            boolean update_version = false;
            boolean hos_complete = cvhos.getCurrentVersion().equals("");
            Vector vfile = new Vector();
            AbsVersionControl.getHeaderUpdate(updateResult, "");
            if (!cvheal.isVersionCorrect() && !hos_complete) {
                Vector v = cvheal.getFileUpdateV();
                for (int i = 0; i < v.size(); i++) {
                    vfile.add(v.get(i));

                }
                AbsVersionControl.executeSQL(theConnectionInf, v, updateResult);
                if (!v.isEmpty()) {
                    update_version = true;

                }
            }
            System.out.println("test1" + update_version);
            if (!cvpcu.isVersionCorrect() && !hos_complete) {
                Vector v = cvpcu.getFileUpdateV();
                for (int i = 0; i < v.size(); i++) {
                    vfile.add(v.get(i));

                }
                AbsVersionControl.executeSQL(theConnectionInf, v, updateResult);
                if (!v.isEmpty()) {
                    update_version = true;

                }
            }
            System.out.println("test2" + update_version);
            if (!cvreport.isVersionCorrect() && !hos_complete) {
                Vector v = cvreport.getFileUpdateV();
                for (int i = 0; i < v.size(); i++) {
                    vfile.add(v.get(i));

                }
                AbsVersionControl.executeSQL(theConnectionInf, v, updateResult);
                if (!v.isEmpty()) {
                    update_version = true;

                }
            }
            if (!cvhos.isVersionCorrect()) {
                Vector v = cvhos.getFileUpdate(cvhos.getCurrentVersion());
                for (int i = 0; i < v.size(); i++) {
                    vfile.add(v.get(i));

                }
                AbsVersionControl.executeSQL(theConnectionInf, v, updateResult);
                if (!v.isEmpty()) {
                    update_version = true;

                }
            }
            if (!update_version) {
                return false;

                //�������ͧ update schema database �è� update ����

            }
            boolean update_schema = false;
            if (cvhos.isSchemaUpdate(cvhos.getCurrentVersion())
                    || cvpcu.isSchemaUpdate(cvpcu.getCurrentVersion())
                    || cvreport.isSchemaUpdate(cvreport.getCurrentVersion())
                    || cvheal.isSchemaUpdate(cvheal.getCurrentVersion())) {
                update_schema = true;
            }
            String warning = Constant.getTextBundle("�׹�ѹ��û�Ѻ�����ѹ�ͧ�ҹ������ HospitalOS ����������ѹ�Ѩ�غѹ")
                    + "\n HospitalOS " + cvhos.getWarningMessage()
                    + "\n PCU-OS     " + cvpcu.getWarningMessage()
                    + "\n Report     " + cvreport.getWarningMessage() + "\n";
            String warning2 = Constant.getTextBundle("��سһ�Ѻ��ا����� HospitalOS����������ѹ�Ѩ�غѹ")
                    + "\n HospitalOS " + cvhos.getWarningMessage()
                    + "\n PCU-OS     " + cvpcu.getWarningMessage()
                    + "\n Report     " + cvreport.getWarningMessage() + "\n";

            updateScriptFile2(theConnectionInf, spl, vfile, update_schema, frm, warning + updateResult.toString(),warning2, theHO.theEmployee.authentication_id);
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return false;
        }
    }

    private int checkFamilyButton() {

        try {

            theConnectionInf.open();
            ///////////////////////////////////////////////////////////////////////////��ͧ������
            //��Ǩ�ͺ��Ъҡë�Өҡ�Ţ�ѵû�ЪҪ�
            String sql = SQL_COUNT_PATIENT_NO_FAMILY;
            ResultSet rs = theConnectionInf.eQuery(sql);
            button_family = 0;
            if (rs.next()) {
                button_family = rs.getInt(1);

            }
            return button_family;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return 0;
        }
    }

    private int checkHCISButton() {
        try {
            theConnectionInf.open();
            ///////////////////////////////////////////////////////////////////////////��ͧ������
            //��Ǩ�ͺ��Ъҡë�Өҡ�Ţ�ѵû�ЪҪ�
            String sql = "select count(*) from t_health_family where (health_family_hn_hcis = ''"
                    + "    or health_family_hn_hcis is null "
                    + //                    "    or length(t_health_family.health_family_hn_hcis) <>6 " +
                    //����͡������ҵ͹ migrate �ӹǹ�����������ѡ���ͧ��Ǩ�ͺ�͹�͡��§ҹ��Ҷ١��ͧ��������
                    "   or t_health_family.health_family_hn_hcis='000000')";
            ResultSet rs = theConnectionInf.eQuery(sql);
            int num_row = 0;
            if (rs.next()) {
                num_row = rs.getInt(1);

            }
            return num_row;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return 0;
        }
    }

    private int checkHCISNumber() {
        try {
            theConnectionInf.open();
            ///////////////////////////////////////////////////////////////////////////��ͧ������
            //pu: ��Ǩ�ͺ��Ъҡë�Өҡ�Ţ hn_hcis ¡��ѹ hn_hcis �繤����ҧ �������͹
            String sql = "select count(t_health_family.health_family_hn_hcis) "
                    + " from t_health_family "
                    + " where t_health_family.health_family_hn_hcis  <> '' "
                    + " group by t_health_family.health_family_hn_hcis "
                    + " having count(t_health_family.health_family_hn_hcis)  > 1 ";
            ResultSet rs = theConnectionInf.eQuery(sql);
            int num_row = 0;
            if (rs.next()) {
                num_row = rs.getInt(1);

            }
            return num_row;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return 0;
        }
    }

    //pu: ��Ǩ�ͺ version ���ç�Ѻ �Ţ version ��͹˹�� ������顴���� UpdateDB
    private int checkUpdateDBButton(Version version) {
        //�Ţ version ��͹˹��
        String lastest_app_version = "3.7.250706";
        int num_row = 0;
        if (version.app_code.equals(lastest_app_version)) {
            num_row = 1;

        }
        return num_row;
    }

    /**
     *hosv4
     */
    public static boolean executeSQLFile(ConnectionInf con, String filename, boolean change_schema) throws SQLException, FileNotFoundException, UnsupportedEncodingException, IOException {
        Constant.println("public void executeSQLFile(String filename) " + filename);
        Statement stmt;
        int ret = 0, count = 0;
        PrintStream fos = new PrintStream(new FileOutputStream("updatedb.log", true));
        fos.println("\n\npublic void executeSQLFile " + filename);

        stmt = con.getConnection().createStatement();
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis, "UTF8");
        BufferedReader in = new BufferedReader(isr);
        String str = "";
        String exe = "";
        while ((str = in.readLine()) != null) {
            try {
                // Somprasong add 241209 ������õ�Ǩ�ͺ��� sql �óշ���ա����䢡Ѻ editor �����ա�������ѭ���ѡɳ���������� "?"
                if (str.length() <= 0) {
                    continue;

                }
                int i_mob = (int) str.charAt(0);
                if (i_mob == 65279) {
//                    System.out.println("str = " + str);
                    str = str.substring(1);
//                    System.out.println("str2 = " + str);
                }
                if (str.startsWith("--")) {
                    continue;


                }
                exe = exe + " " + str;
                if (str.lastIndexOf(";") != -1) {

                    if (exe.toLowerCase().trim().startsWith("drop table t_") && !change_schema) {
                        throw new FileNotFoundException(filename + " must not remove data");
                    }
                    if (exe.toLowerCase().trim().startsWith("delete from t_") && !change_schema) {
                        throw new FileNotFoundException(filename + " must not remove data");
                    }
                    if (exe.toLowerCase().trim().startsWith("copy")) {
                        throw new FileNotFoundException(filename + " please execute with command line");
                    }
                    if (exe.length() > 5 && exe.substring(0, 5).compareToIgnoreCase("alter") == 0) {
                        Constant.println(exe);

                    }
                    ret += stmt.executeUpdate(exe);
                    exe = "";
                    count++;
                }
            } catch (SQLException e) {
                Constant.println(exe);
                Constant.println("\t" + e.getMessage());
                fos.println(exe);
                fos.println("\t" + e.getMessage());
                exe = "";
//                    e.printStackTrace(Constant.getPrintStream());
            }
        }
        fos.close();
        Constant.println("result:" + ret + " of " + count);
        return true;
    }

    public static Connection getConnectionFromFile() {
        String config = IOStream.readInputDefault(".hospital_os.cfg");
        try {
            //�ʹ���ʵç���
            String conf = Secure.decode(config);
            String tmp = new String();
            String value = new String();
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            //settings.put("DONT_REMIND", value.trim());

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            //settings.put("SERVER", value.trim());
            String server = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            //settings.put("DATABASE", value.trim());
            String database = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            //settings.put("PORT", value.trim());

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            //settings.put("USERNAME", value.trim());
            String user = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            //settings.put("PASSWORD", value.trim());
            String pass = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);

            String url = "jdbc:postgresql://" + server + ":5432/" + database;
            String type = "0"; //0 postgres 1 mysql 2 sqlserver
            System.out.println("URL" + url);
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return null;
        }
    }

    public static boolean executeSQLFile(String filename, String password, boolean delete) {
        try {
            System.out.println("public void executeSQLFile(String filename) " + filename);
            if (!password.equals("h" + filename.substring(1, 5))) {
                System.out.println("permission fail");
                return false;
            }
            Statement stmt = getConnectionFromFile().createStatement();
            int ret = 0;
            int count = 0;
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF8");
            BufferedReader in = new BufferedReader(isr);
            String str = "";
            String exe = "";
            while ((str = in.readLine()) != null) {
                try {
                    if (str.startsWith("--")) {
                        continue;
                    }
                    exe = exe + " " + str;
                    if (str.endsWith(";")) {
                        if (exe.trim().startsWith("drop")) {
                            System.out.println(exe);
                        }
                        if (exe.length() > 5 && exe.substring(0, 5).compareToIgnoreCase("alter") == 0) {
                            System.out.println(exe);
                        }
                        ret += stmt.executeUpdate(exe);
                        exe = "";
                        count++;
                    }
                } catch (SQLException e) {
                    System.out.println(exe);
                    System.out.println("\t" + e.getMessage());
                    exe = "";
//                    e.printStackTrace(System.out.getPrintStream());
                }
            }
            System.out.println("result:" + ret + " of " + count);
            if (delete) {
                File file = new File(filename);
                file.delete();
            }
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return false;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return false;
        } catch (IOException ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return false;
        }
    }

    public Employee readEmpByUser(String user) {
        try {
            theConnectionInf.open();
            ResultSet rs = theLookupControl.theConnectionInf.eQuery(
                    " select b_employee_id,employee_login,employee_password,f_employee_authentication_id,b_service_point_id"
                    + " from b_employee"
                    + " where employee_login = '" + user + "' and employee_active = '1'");
            Employee emp = new Employee();
            while (rs.next()) {
                emp.setObjectId(rs.getString(1));
                emp.employee_id = rs.getString(2);
                emp.password = rs.getString(3);
                emp.authentication_id = rs.getString(4);
                emp.default_service_id = rs.getString(5);
            }
            return emp;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     * ��Ǩ�ͺ��û�Ѻ��ا�ҹ�����ŵ�ͧ�����¼������к���ҹ��
     * @param theConnectionInf
     * @param spl
     * @param vfile
     * @param update_schema
     * @param frm
     * @param warning
     * @param authen
     * @return
     */
    public static boolean updateScriptFile(ConnectionInf theConnectionInf, Splash spl, Vector vfile, boolean update_schema, JFrame frm, String warning, String authen) {

        try {
            if (!authen.equals(Authentication.ADMIN)) {
                return false;
            }
            String title = Constant.getTextBundle("��Ѻ��ا�ҹ������");
            if (update_schema) {
                title = Constant.getTextBundle("��Ѻ�ҹ�������������¹�����ѹ�١����");


            }
            int ret = JOptionPane.showConfirmDialog(frm, new javax.swing.JTextArea(warning), title, javax.swing.JOptionPane.YES_NO_OPTION);

            if (ret != JOptionPane.YES_OPTION) {
                return false;

                /////////////////////////////////

            }
            theConnectionInf.begin();

            boolean res = true;
            if (spl != null) {
                spl.setVisible(true);

            }
            for (int i = 0; i < vfile.size() && res; i++) {
                String filename = (String) vfile.get(i);
                executeSQLFile(theConnectionInf, filename, update_schema);
            }
            theConnectionInf.commit();
            /////////////////////////////////
            if (spl != null) {
                spl.setVisible(false);

            }
            return true;
        } catch (Exception ex) {
            if (spl != null) {
                spl.setVisible(false);

            }
            theConnectionInf.rollback();
            ex.printStackTrace(Constant.getPrintStream());
            JOptionPane.showMessageDialog(null, Constant.getTextBundle("��û�Ѻ��ا�ҹ�������ѵ��ѵ�") + " " +
                    Constant.getTextBundle("�Դ��Ҵ") + " " +
                    Constant.getTextBundle("��سҵ�Ǩ�ͺ��� Update �ҹ�����Ũҡ log"));
            return false;
        }
    }
    /**
     * ��Ǩ�ͺ��û�Ѻ��ا�ҹ�����ŵ�ͧ�����¼������к���ҹ��
     * @param theConnectionInf
     * @param spl
     * @param vfile
     * @param update_schema
     * @param frm
     * @param warning
     * @param authen
     * @return
     */
    public static boolean updateScriptFile2(ConnectionInf theConnectionInf, Splash spl, Vector vfile
            , boolean update_schema, JFrame frm, String warning,String warning2, String authen) {

        try {
            if (!authen.equals(Authentication.ADMIN)) {
                JOptionPane.showMessageDialog(frm, new javax.swing.JTextArea(warning2));
                return false;
            }
            String title = Constant.getTextBundle("��Ѻ��ا�ҹ������");
            if (update_schema) {
                title = Constant.getTextBundle("��Ѻ�ҹ�������������¹�����ѹ�١����");


            }
            int ret = JOptionPane.showConfirmDialog(frm, new javax.swing.JTextArea(warning), title, javax.swing.JOptionPane.YES_NO_OPTION);

            if (ret != JOptionPane.YES_OPTION) {
                return false;

                /////////////////////////////////

            }
            theConnectionInf.begin();

            boolean res = true;
            if (spl != null) {
                spl.setVisible(true);

            }
            for (int i = 0; i < vfile.size() && res; i++) {
                String filename = (String) vfile.get(i);
                executeSQLFile(theConnectionInf, filename, update_schema);
            }
            theConnectionInf.commit();
            /////////////////////////////////
            if (spl != null) {
                spl.setVisible(false);

            }
            return true;
        } catch (Exception ex) {
            if (spl != null) {
                spl.setVisible(false);

            }
            theConnectionInf.rollback();
            ex.printStackTrace(Constant.getPrintStream());
            JOptionPane.showMessageDialog(null, Constant.getTextBundle("��û�Ѻ��ا�ҹ�������ѵ��ѵ�") + " " +
                    Constant.getTextBundle("�Դ��Ҵ") + " " +
                    Constant.getTextBundle("��سҵ�Ǩ�ͺ��� Update �ҹ�����Ũҡ log"));
            return false;
        }
    }

    public static void main(String[] argc) {
        ConnectionInf con = new ConnectionDBMgr();
        try {
            con.open();
            SystemControl.executeSQLFile(con, "/home/henbe/Desktop/H1N1/H1N1_drugcode24.sql", true);
            if (argc.length != 2) {
                return;
            }
            SystemControl.executeSQLFile(argc[0], argc[1], true);
            System.in.read();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            con.close();
        }
    }

////////////////////////////////////////////////////////////////////////////////////////
    private void saveLog(UseCase uc, String object_id, String object_note, int complete) {
//        try {
//            theConnectionInf.open();
//            HosLog hospitalosLog = new HosLog(uc.uc_name,uc.table_name);
//            hospitalosLog.object_id = object_id;
//            hospitalosLog.object_note = object_note;
//            hospitalosLog.record_staff = theHO.theEmployee.getObjectId();
//            InetAddress ia = InetAddress.getLocalHost();
//            hospitalosLog.ip_address = ia.getHostAddress()+":"+ia.getHostName();
//            hospitalosLog.record_date_time = theHO.date_time;
//            hospitalosLog.status = String.valueOf(complete);
//            theHosDB.theHosLogDB.insert(hospitalosLog);
//        } catch (Exception ex) {
////            ex.printStackTrace(Constant.getPrintStream());
//            Constant.println(ex.getMessage());
//        }
    }
    private void saveLog(int uc_name, String object_id, String object_note, int complete) {
//        try {
//            theConnectionInf.open();
//            HosLog hospitalosLog = new HosLog(uc_name);
//            hospitalosLog.object_id = object_id;
//            hospitalosLog.object_note = object_note;
//            hospitalosLog.record_staff = theHO.theEmployee.getObjectId();
//            InetAddress ia = InetAddress.getLocalHost();
//            hospitalosLog.ip_address = ia.getHostAddress()+":"+ia.getHostName();
//            hospitalosLog.record_date_time = theHO.date_time;
//            hospitalosLog.status = String.valueOf(complete);
//            theHosDB.theHosLogDB.insert(hospitalosLog);
//        } catch (Exception ex) {
////            ex.printStackTrace(Constant.getPrintStream());
//            Constant.println(ex.getMessage());
//        }
    }

    public void saveLog(int ucname, String objectid, Object obj, int status) {
        if(status == UpdateStatus.ERROR)
        {
            sendMail((Exception) obj,theUS,UseCase.getUCName(ucname));
        }
        String note = null;
        if(obj == null){
            saveLog(ucname, objectid, note, status);
        }else{
            if (obj instanceof Exception) {
                note = ((Exception)obj).getMessage();
            }else if (obj instanceof String){
                note = (String)obj;
            }
            saveLog(ucname, objectid, note, status);
        }
    }
    /**
     * �ѧ���蹷��������Ѻ�������§ҹ�ѡ
     * author LionHearth
     * modify 12/04/53
     */
    public void sendMail(Exception exception,final UpdateStatus theUS,String uc_name)
    {
        final String working_dir = System.getProperty("user.dir");
//        File show_error = new File(working_dir+"\\config\\showerror.txt");
        //�ҡ����� showerror.txt ������� config ����� �����ӧҹ���
        //��������ա���Դ�����ҹ�к�������駺ѡ ����ö�Դ���¡�����͡
        //���ٵ�Ǫ��� > ��§ҹ��ͼԴ��Ҵ�ͧ�����
//        if(!show_error.exists())
//            return;
        if(!theHO.is_auto_report_bug)
            return;
        try
        {
            Properties props = new Properties();
            // If true, enables the use of the STARTTLS command (if supported by
            // the server) to switch the connection to a TLS-protected connection
            // before issuing any login commands. Note that an appropriate trust
            // store must configured so that the client will trust the server's
            // certificate. Defaults to false.
            props.put("mail.smtp.starttls.enable", "true");
            // ��駤�� connection timeout ������ 2 �Թҷ�
            props.put("mail.smtp.connectiontimeout", "2000");
            Session s = Session.getInstance(props, null);
            s.setDebug(true);
            final MimeMessage message = new MimeMessage(s);
            InternetAddress from = new InternetAddress(theLookupControl.readAutoReportBug().mail_from,
            theHO.theSite.off_name);
            String recipient[] = null;
            InternetAddress to_arr[];
            // 㹡óշ���õ�駤�� mailto ��������������ҡ���ѡ��� ,
            if(theLookupControl.readAutoReportBug().mail_to.indexOf(",")>0)
            {
                recipient = theLookupControl.readAutoReportBug().mail_to.split(",");
                to_arr = new InternetAddress[recipient.length];
            }
            // 㹡óշ�����������
            else
            {
                to_arr = new InternetAddress[1];
                recipient = new String[1];
                recipient[0] = theLookupControl.readAutoReportBug().mail_to.trim();
            }
            // �ӡ������ mail to ���Ѻ���
            for(int i=0;i<recipient.length;i++)
            {
                to_arr[i] = new InternetAddress(recipient[i].trim());
                message.addRecipient(Message.RecipientType.TO, to_arr[i]);
            }
            message.setSentDate(new Date());
            message.setFrom(from);
            message.setSubject("��§ҹ�ѡ�ѵ��ѵ� �ҡ" + theHO.theSite.off_name);
            StringBuffer error_message = new StringBuffer();
            error_message.append("��§ҹ�ѡ�ѵ��ѵ� �ҡ" + theHO.theSite.off_name+"\n\n");
            error_message.append("Usecase : " + uc_name + "\n");
            error_message.append("�������к�\n");
            error_message.append("###################################\n");
            error_message.append("����� :\n" + theHO.theVersion.app_code + "\n");
            error_message.append("�ҹ������ :\n" + theHO.theVersion.db_code + "\n");
            error_message.append(SystemControl.getSystemInfo());
            error_message.append("\nException\n");
            error_message.append(exception.toString()+"\n");
            StackTraceElement stackTraceElement[] = exception.getStackTrace();
            // ������ͤ��� exception ���Ѻ���
            for(int i=0;i<stackTraceElement.length;i++)
                error_message.append(stackTraceElement[i].toString()+"\n");
            String error = "";
            JFrame jf = null;
            DialogSendError dialogSendError = new DialogSendError(jf,true);
            dialogSendError.setHosObject(theHO);
            if(theUS != null)
            {
                jf = theUS.getJFrame();
                error = dialogSendError.showSendError(exception.toString(),error_message.toString());
            }
            String send_log = "0";
            if(error.indexOf("sendlog")>=0)
            {
                send_log = "1";
            }
            if(error.equals("") || error.equals("sendlog"))
                return;
            error = error.replace("sendlog", "");
            final MailSplashScreen theSplashScreen = MailSplashScreen.getInstance(jf);
            theUS.setStatus(("���ѧ���Թ�������§ҹ�ѡ"),UpdateStatus.WARNING);
            if(send_log.equals("1"))
            {
                String to_zip = "";
                String[] filesToZip = new String[1];
                // 㹡óշ���������͡Ṻ��� log ������зӡ�� zip ����� log.zip ��зӡ����价ҧ���
                if(theHO.running_program.equals(HosObject.MAINAPP))
                    filesToZip[0] = working_dir + "\\run_mod.txt";
                else if(theHO.running_program.equals(HosObject.SETUPAPP))
                    filesToZip[0] = working_dir + "\\setup_mod.txt";
                else if(theHO.running_program.equals(HosObject.REPORTAPP))
                    filesToZip[0] = working_dir + "\\log_report.txt";
                // �鴴�ҹ��ҧ����ͧ�Ѻ������������������ ��� ���ҡ�������辺���˹�����㴡�Т�����ա���˹��
                for(int i3=0;i3<filesToZip.length;i3++)
                {
                    File f = new File(filesToZip[i3]);
                    if(f.exists())
                        to_zip += i3 + "-";
                }
                if(!to_zip.equals(""))
                    to_zip = to_zip.substring(0,to_zip.length() - 1);
                String tozip_arr[] = new String[3];
                if(to_zip.indexOf("-") >= 0)
                    tozip_arr = to_zip.split("-");
                else
                {
                    tozip_arr = new String[1];
                    tozip_arr[0] = to_zip;
                }
                if(tozip_arr.length>1)
                {
                    for(int i4=0;i4<tozip_arr.length;i4++)
                    {
                        if(tozip_arr[i4] != null && !tozip_arr[i4].equals(""))
                            tozip_arr[i4] = filesToZip[Integer.parseInt(tozip_arr[i4])];
                        else
                            tozip_arr[i4] = "";
                    }
                }
                else
                {
                    if(to_zip.equals(""))
                        tozip_arr[0] = "";
                    else
                        tozip_arr[0] = filesToZip[Integer.parseInt(to_zip)];
                }
                if(!tozip_arr[0].equals(""))
                {
                    byte[] buffer = new byte[18024];
                    String zipFileName = "log.zip";
                    ZipOutputStream out =
                            new ZipOutputStream(new FileOutputStream(zipFileName));
                    out.setLevel(Deflater.DEFAULT_COMPRESSION);
                    for (int i2 = 0; i2 < tozip_arr.length; i2++) {
                        FileInputStream in = new FileInputStream(tozip_arr[i2]);
                        out.putNextEntry(new ZipEntry(tozip_arr[i2]));
                        int len;
                        while ((len = in.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                        out.closeEntry();
                        in.close();
                    }
                    out.close();
                }
                String attachments[] = {"log.zip"};
                BodyPart messageBodyPart = new MimeBodyPart();
                // ��˹���Ǣ�����
                messageBodyPart.setContent(error, "text/plain; charset=\"TIS-620\"");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                // 㹡óշ���ա��Ṻ��� attach ����㹨�����
                for(int i = 0; i<= attachments.length -1; i++)
                {
                    File f = new File(attachments[i]);
                    if(!f.exists())
                        continue;
                    String filename = attachments[i];
                    MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(filename);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    attachmentBodyPart.setFileName(filename);
                    multipart.addBodyPart(attachmentBodyPart);
                }
                // ���������Ңͧ�����¡ó�Ṻ��� log
                message.setContent(multipart, "text/plain; charset=\"TIS-620\"");
               }
           else
           {
                // ���������Ңͧ�����¡ó������Ṻ���
                message.setContent(error, "text/plain; charset=\"TIS-620\"");
           }
           final Transport tr = s.getTransport("smtp");
           theSplashScreen.start();
           Runnable r = new Runnable() {
                @Override
                public void run()
                {
                    // �Դ������������� ����觨�����
                    try{
                       theSplashScreen.setMessage(Constant.getTextBundle("���ѧ�Դ������������"));
                       AutoReportBug arb = theLookupControl.readAutoReportBug();
                       tr.connect(arb.smtp_host
                               , arb.user_name
                               , arb.password);
                       theSplashScreen.setMessage(Constant.getTextBundle("���Թ����������"));
                       message.saveChanges();
                       tr.sendMessage(message, message.getAllRecipients());
                       tr.close();
                       theSplashScreen.setMessage(Constant.getTextBundle("���Թ���ź��� temp"));
                       //���������� log.zip �������� ź���
                       File delete_log = new File(working_dir + "\\log.zip");
                       delete_log.delete();
                       theUS.setStatus(Constant.getTextBundle("����§ҹ�ѡ") + " " +
                               Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
                       theSplashScreen.finish();
                    }
                    catch(MessagingException e)
                    {
                        theUS.setStatus(("�������ö�Դ������������������س��ͧ�ա����"),UpdateStatus.ERROR);
                        e.printStackTrace(Constant.getPrintStream());
                        theSplashScreen.finish();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace(Constant.getPrintStream());
                        theSplashScreen.finish();
                        theUS.setStatus(Constant.getTextBundle("����§ҹ�ѡ") + " " +
                                Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
                    }
                }
            };
           Thread t = new Thread(r);
           t.start();
        }
        catch(Exception ee)
        {
            ee.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("�������ö����§ҹ�ѡ��"),UpdateStatus.ERROR);
        }


    }
    /**
     * �ѧ���蹷��������Ѻ�ʴ������Ţͧ�к�
     * author LionHearth
     * modify 12/04/53
     */
    public static String getSystemInfo() throws Exception
    {
        String seperator =  "$#$";
        String end_mark = "###################################";
        StringBuffer bf = new StringBuffer();
        bf.append(end_mark+ seperator);
        bf.append("Operating system name :"+ seperator);
        bf.append(System.getProperty("os.name")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Operating system architecture :"+ seperator);
        bf.append(System.getProperty("os.arch")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Operating system version :"+ seperator);
        bf.append(System.getProperty("os.version")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Runtime Environment version :"+ seperator);
        bf.append(System.getProperty("java.version")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Runtime Environment vendor :"+ seperator);
        bf.append(System.getProperty("java.vendor")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java vendor URL :"+ seperator);
        bf.append(System.getProperty("java.vendor.url")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java installation directory :"+ seperator);
        bf.append(System.getProperty("java.home")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Virtual Machine specification version :"+ seperator);
        bf.append(System.getProperty("java.vm.specification.version")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Virtual Machine specification vendor :"+ seperator);
        bf.append(System.getProperty("java.vm.specification.vendor")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Virtual Machine specification name :"+ seperator);
        bf.append(System.getProperty("java.vm.specification.name")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Virtual Machine implementation version :"+ seperator);
        bf.append(System.getProperty("java.vm.version")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Virtual Machine implementation vendor :"+ seperator);
        bf.append(System.getProperty("java.vm.vendor")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Virtual Machine implementation name :"+ seperator);
        bf.append(System.getProperty("java.vm.name")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("java.specification.version :"+ seperator);
        bf.append(System.getProperty("java.specification.version")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Runtime Environment specification version :"+ seperator);
        bf.append(System.getProperty("java.specification.vendor")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java Runtime Environment specification name :"+ seperator);
        bf.append(System.getProperty("java.specification.name")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java class format version number :"+ seperator);
        bf.append(System.getProperty("java.class.version")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Java class path :"+ seperator);
        bf.append(System.getProperty("java.class.path")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("List of paths to search when loading libraries :"+ seperator);
        bf.append(System.getProperty("java.library.path")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Default temp file path :"+ seperator);
        bf.append(System.getProperty("java.io.tmpdir")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Name of JIT compiler to use :"+ seperator);
        bf.append(System.getProperty("java.compiler")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Path of extension directory or directories :"+ seperator);
        bf.append(System.getProperty("java.ext.dirs")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("File separator (\"/\" on UNIX) :"+ seperator);
        bf.append(System.getProperty("file.separator")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Path separator (\":\" on UNIX) :"+ seperator);
        bf.append(System.getProperty("path.separator")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("Line separator (\"\\n\" on UNIX) :"+ seperator);
        bf.append(System.getProperty("line.separator").replace("\n", "\\n")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("User's account name :"+ seperator);
        bf.append(System.getProperty("user.name")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("User's home directory :"+ seperator);
        bf.append(System.getProperty("user.home")+ seperator);
        bf.append(end_mark+ seperator);
        bf.append("User's current working directory :"+ seperator);
        bf.append(System.getProperty("user.dir")+ seperator);
        bf.append(end_mark+ seperator);
        if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
        {
            Runtime rt = java.lang.Runtime.getRuntime();
            int inp;
            String s = "";
            try {
                Process proc = rt.exec("ipconfig /all");
                while ((inp = proc.getInputStream().read()) != -1) {
                    s += (char) inp;
                }
                bf.append(s);
                bf.append(end_mark+ seperator);
                proc.destroy();
            } catch (Exception e) {
                e.printStackTrace(Constant.getPrintStream());
                throw e;
            }
        }
        else
        {
            InetAddress thisIp = InetAddress.getLocalHost();
            bf.append("ip :"+ seperator);
            bf.append(thisIp.getHostAddress());
            bf.append(end_mark+ seperator);
        }
        return bf.toString().replace( seperator,"\n");
    }
    public void setStatus(String ucname, int status, Exception ex) {
        if (status == UpdateStatus.COMPLETE) {
            theHS.notifyUC(ucname);
            theUS.setStatus(Constant.getTextBundle(ucname) + Constant.STR_COMPLETE, status);
        } else if (status == UpdateStatus.ERROR) {
            theUS.setStatus(Constant.getTextBundle(ucname) + Constant.STR_ERROR, status);
        }
        if(ex!=null)
            ex.printStackTrace(Constant.getPrintStream());
    }

    void setStatusComplete(int ucid, String objectid) {
       UseCase uc = UseCase.initUC(ucid);
       saveLog(uc,objectid,"",UpdateStatus.COMPLETE);
       theUS.setStatus(Constant.getTextBundle(uc.uc_name_th)
               + Constant.STR_COMPLETE, UpdateStatus.COMPLETE);
    }

    void setStatusFail(int ucid, String objectid, Exception ex) {
       UseCase uc = UseCase.initUC(ucid);
       saveLog(uc,objectid,"",UpdateStatus.ERROR);
       theHS.notifyUC(uc.uc_name_th);
       theUS.setStatus(Constant.getTextBundle(uc.uc_name_th)
               + Constant.STR_ERROR, UpdateStatus.ERROR);
       ex.printStackTrace(Constant.getPrintStream());
       sendMail(ex,theUS,uc.uc_name_th);
    }
////////////////////////////////////////////////////////////////////////////////////////
}
