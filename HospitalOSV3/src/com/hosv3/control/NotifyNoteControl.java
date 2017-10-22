/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.control;

import com.hospital_os.object.NotifyNote;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.object.HosObject;
import com.hosv3.subject.HosSubject;
import com.hosv3.utility.Constant;
import com.hosv3.utility.connection.UpdateStatus;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sd.comp.idandcombobox.KeyValue;

/**
 *
 * @author Somprasong
 */
/**
 * 
 * @author ekapop
 * 1. เรื่อง AttachNote  60-10-22
 * 
 * Modify doc 5.

 */
public class NotifyNoteControl {

    protected ConnectionInf theConnectionInf;
    protected HosDB theHosDB;
    protected HosObject theHO;
    protected HosSubject theHS;
    protected UpdateStatus theUS;
    protected LookupControl theLookupControl;

    /** Creates a new instance of LookupControl */
    public NotifyNoteControl(ConnectionInf con, HosObject ho, HosDB hdb, HosSubject hs, LookupControl theLookupControl) {
        theConnectionInf = con;
        theHosDB = hdb;
        theHS = hs;
        theHO = ho;
        this.theLookupControl = theLookupControl;
    }

    public void setUpdateStatus(UpdateStatus theUS) {
        this.theUS = theUS;
    }

    @SuppressWarnings("UseOfObsoleteCollectionType")
    public List<NotifyNote> listNotifyNote(String hn) {
        List<NotifyNote> list = new ArrayList<NotifyNote>();
        theConnectionInf.open();
        try {
//            theConnectionInf.getConnection().setAutoCommit(false);
            list.addAll(theHosDB.theNotifyNoteDB.listByHn(hn));
//            theConnectionInf.getConnection().commit();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
//            try {
//                theConnectionInf.getConnection().rollback();
//            } catch (SQLException ex1) {
//                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        } finally {
            theConnectionInf.close();
        }
        return list;
    }

    @SuppressWarnings("UseOfObsoleteCollectionType")
    public List<NotifyNote> listNotifyNote2(String hn) {
        List<NotifyNote> list = new ArrayList<NotifyNote>();
        theConnectionInf.open();
        try {
//            theConnectionInf.getConnection().setAutoCommit(false);
            list.addAll(theHosDB.theNotifyNoteDB.listByHn2(hn));
//            theConnectionInf.getConnection().commit();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
//            try {
//                theConnectionInf.getConnection().rollback();
//            } catch (SQLException ex1) {
//                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        } finally {
            theConnectionInf.close();
        }
        return list;
    }
    public String listNotifyNoteFirst(String hn) {      //+1
        List<NotifyNote> list = new ArrayList<NotifyNote>();
        NotifyNote nn = new NotifyNote();
        String aa = "";
        theConnectionInf.open();
        try {
//            theConnectionInf.getConnection().setAutoCommit(false);
            list = theHosDB.theNotifyNoteDB.listByHnFirst(hn);
            if(list.size()>0){
                nn = list.get(0);
                aa = nn.note_subject+""+nn.note_detail;
            }
//            theConnectionInf.getConnection().commit();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
//            try {
//                theConnectionInf.getConnection().rollback();
//            } catch (SQLException ex1) {
//                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        } finally {
            theConnectionInf.close();
        }
        return aa;
    }

    public NotifyNote getNotifyNote(String id) {
        NotifyNote notifyNote = null;
        theConnectionInf.open();
        try {
//            theConnectionInf.getConnection().setAutoCommit(false);
            notifyNote = theHosDB.theNotifyNoteDB.getById(id);
//            theConnectionInf.getConnection().commit();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
//            try {
//                theConnectionInf.getConnection().rollback();
//            } catch (SQLException ex1) {
//                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        } finally {
            theConnectionInf.close();
        }
        return notifyNote;
    }

    public int saveOrUpdateNotifyNote(NotifyNote obj) {
        int iRes = 0;
        theConnectionInf.open();
        try {
            theConnectionInf.getConnection().setAutoCommit(false);
            String date_time = theLookupControl.intReadDateTime();
            if (obj.getObjectId() == null) {
                obj.patient_hn = theHO.theVisit.hn;
                obj.visit_id_rec = theHO.theVisit.getObjectId();
                obj.rec_staff = theHO.theEmployee.getObjectId();
                obj.rec_datetime = date_time;
                obj.active = "1";
                theHosDB.theNotifyNoteDB.insert(obj);
            } else {
                obj.mod_datetime = date_time;
                theHosDB.theNotifyNoteDB.update(obj);
            }

            theConnectionInf.getConnection().commit();
            iRes = 1;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            try {
                theConnectionInf.getConnection().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                theConnectionInf.getConnection().close();
            } catch (SQLException ex1) {
                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return iRes;
    }

    public int deleteNotifyNote(NotifyNote obj) {
        int iRes = 0;
        theConnectionInf.open();
        try {
            theConnectionInf.getConnection().setAutoCommit(false);
            String date_time = theLookupControl.intReadDateTime();

            obj.del_datetime = date_time;
            obj.active = "0";
            theHosDB.theNotifyNoteDB.inactive(obj);

            theConnectionInf.getConnection().commit();
            iRes = 1;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            try {
                theConnectionInf.getConnection().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                theConnectionInf.getConnection().close();
            } catch (SQLException ex1) {
                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return iRes;
    }

    public KeyValue[] getNotifyType() {
        KeyValue[] keyvalues = new KeyValue[]{};
        theConnectionInf.open();
        try {
//            theConnectionInf.getConnection().setAutoCommit(false);
            List<Object[]> listNotifyType = theHosDB.theNotifyNoteDB.listNotifyType();
            System.out.println(">>> listNotifyType.size() = " + listNotifyType.size());
            keyvalues = new KeyValue[listNotifyType.size()];
            for (int i = 0; i < keyvalues.length; i++) {
                Object[] get = listNotifyType.get(i);
                keyvalues[i] = new KeyValue((String) get[0], (String) get[1]);
            }
//            theConnectionInf.getConnection().commit();

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
//            try {
//                theConnectionInf.getConnection().rollback();
//            } catch (SQLException ex1) {
//                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        } finally {
            theConnectionInf.close();
        }
        return keyvalues;
    }

    public List<NotifyNote> listNotifyNoteViewOnly(String hn, String visitId) {
        List<NotifyNote> list = new ArrayList<NotifyNote>();
        theConnectionInf.open();
        try {
//            theConnectionInf.getConnection().setAutoCommit(false);
            list.addAll(theHosDB.theNotifyNoteDB.listByHnAbdVisitId(hn, visitId));
//            theConnectionInf.getConnection().commit();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
//            try {
//                theConnectionInf.getConnection().rollback();
//            } catch (SQLException ex1) {
//                Logger.getLogger(NotifyNoteControl.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        } finally {
            theConnectionInf.close();
        }
        return list;
    }
}
