package com.hosv3.control;
import com.hospital_os.object.OrderItem;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.object.HosObject;
import com.hosv3.object.LabList;
import com.hosv3.utility.DateTime;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author henbe
 * @modify Somprasong
 */
public class LabControl {


    public Connection theConnection;
    public UpdateStatus theUS;

    public String str_latestTime = "";
    public String str_currentTime = "";
    public String url = "";
    public long delay = 5000;
    public int i_format = 2;
    public int sender;
    public String ln_type = "";
    private final ConnectionInf cdb;
    private HosControl theHosControl;
    private HosObject theHO;
    public static String ONE_LN = "1";
    public static String MANY_LN = "2";
    String current_file_name = "";

    /** Creates a new instance of LabControl */
    public LabControl(HosControl hc) {
        theHosControl = hc;
        theHO = theHosControl.theHO;
        cdb = theHosControl.theConnectionInf;
        theConnection = cdb.getConnection();
    }
    public void setUpdateStatus(UpdateStatus us) {
        theUS = us;
    }


    public Vector<LabList> listLabOrderByVn(String visit_id)
    {
        boolean err = false;
        Vector v = new Vector();
        try
        {

            String sql = "select * from t_lis_ln where t_visit_id = '"+this.theHO.theVisit.getObjectId()+"'";
            ResultSet rs = executeQuery(theConnection,sql);
            if(rs.next())
            {
                sql = "select t_lis_order.t_order_id as order_id "+
                    ",t_lis_order.exec_datetime as order_execute_date "+
                    ",bb.item_common_name as order_name "+
                    ",bb.item_nick_name as item_nick_name "+
                    ",aa.order_date_time as order_date "+
                    ",t_lis_order.status as status "+
                    ",t_lis_order.lab_number as lab_no "+
                    "from (select * from t_lis_ln) as cc "+
                    "inner join t_lis_order on cc.lab_number = t_lis_order.lab_number "+
                    "inner join (select t_order_id,b_item_id,order_date_time from t_order where t_order.t_visit_id = '"+visit_id+"' and t_order.f_order_status_id <> '3'  and t_order.f_order_status_id <> '0' and t_order.f_order_status_id <> '1') "+
                    "as aa on aa.t_order_id = t_lis_order.t_order_id "+
                    "inner join (select b_item_id,item_common_name,item_nick_name from b_item) as bb on bb.b_item_id = aa.b_item_id "+
                    "where cc.t_visit_id = '"+visit_id+"' "+
                    "group by t_lis_order.t_order_id"
                    + ",t_lis_order.exec_datetime"
                    + ",bb.item_common_name"
                    + ",bb.item_nick_name"
                    + ",aa.order_date_time"
                    + ",t_lis_order.status"
                    + ",t_lis_order.lab_number";
                rs = executeQuery(theConnection,sql);
                LabList listLab;
                while(rs.next())
                {
                    listLab = new LabList();
                    listLab.order_id = rs.getString("order_id");
                    listLab.order_name = rs.getString("order_name");
                    listLab.item_nick_name = rs.getString("item_nick_name");
                    listLab.order_date = rs.getString("order_date");
                    listLab.order_execute_date = rs.getString("order_execute_date");
                    listLab.status = rs.getString("status");
                    listLab.ln = rs.getString("lab_no");
                    listLab.type = LabList.TYPE_NEW;
                    v.add(listLab);
                }
            }
            else
            {
                String sql_chk = "select t_order.t_order_id,order_common_name,b_item.item_nick_name from t_order " +
                    "inner join b_item on b_item.b_item_id = t_order.b_item_id " +
                    "where t_order.f_order_status_id <> '3' and t_order.f_item_group_id = '2' and t_order.t_visit_id = '" + visit_id + "' and b_item.item_nick_name = ''";
                ResultSet rs_chk = executeQuery(theConnection,sql_chk);
                while(rs_chk.next())
                {
                    err = true;
                }
                if(err)
                {
                    theUS.setStatus("รายการแลปบางรายการยังไม่ได้ตั้งค่า Specimen กรุณาตั้งค่า",UpdateStatus.WARNING);
                }//1007692
                sql = "select t_order.order_executed_date_time,t_order.order_date_time,t_order.t_order_id,order_common_name,b_item.item_nick_name from t_order " +
                    "inner join b_item on b_item.b_item_id = t_order.b_item_id " +
                    "where t_order.f_order_status_id <> '3' and t_order.f_item_group_id = '2' and t_order.t_visit_id = '" + visit_id + "' and b_item.item_nick_name <> ''";
                rs = executeQuery(theConnection,sql);
                LabList listLab;
                while(rs.next())
                {
                    listLab = new LabList();
                    listLab.order_id = rs.getString("t_order_id");
                    listLab.order_name = rs.getString("order_common_name");
                    listLab.item_nick_name = rs.getString("item_nick_name");
                    listLab.order_date = rs.getString("order_date_time");
                    listLab.order_execute_date = rs.getString("order_executed_date_time");
                    listLab.type = LabList.TYPE_OLD;
                    v.add(listLab);
                }
                rs_chk.close();
                return null;
            }
            rs.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(err && v.size()==0)
            return null;
        return v;
    }
    public Vector<LabList> listLabOrderByVnForPrintLabResult(String visit_id)
    {
        boolean err = false;
        Vector v = new Vector();
        try
        {
            String sql = "select * from t_lis_ln where t_visit_id = '"+this.theHO.theVisit.getObjectId()+"'";
            ResultSet rs = executeQuery(theConnection,sql);
            if(rs.next())
            {
                sql = "select t_lis_order.t_order_id as order_id "+
                    ",t_lis_order.exec_datetime as order_execute_date "+
                    ",bb.item_common_name as order_name "+
                    ",bb.item_nick_name as item_nick_name "+
                    ",aa.order_date_time as order_date "+
                    ",t_lis_order.status as status "+
                    ",t_lis_order.lab_number as lab_no "+
                    "from (select * from t_lis_ln) as cc "+
                    "inner join t_lis_order on cc.lab_number = t_lis_order.lab_number "+
                    "inner join (select t_order_id,b_item_id,order_date_time from t_order where t_order.t_visit_id = '"+visit_id+"' and t_order.f_order_status_id <> '3'  and t_order.f_order_status_id <> '0' and t_order.f_order_status_id <> '1') "+
                    "as aa on aa.t_order_id = t_lis_order.t_order_id "+
                    "inner join (select b_item_id,item_common_name,item_nick_name from b_item) as bb on bb.b_item_id = aa.b_item_id "+
                    "where cc.t_visit_id = '"+visit_id+"' "+
                    "group by t_lis_order.t_order_id"
                    + ",t_lis_order.exec_datetime"
                    + ",bb.item_common_name"
                    + ",bb.item_nick_name"
                    + ",aa.order_date_time"
                    + ",t_lis_order.status"
                    + ",t_lis_order.lab_number";
                rs = executeQuery(theConnection,sql);
                LabList listLab;
                while(rs.next())
                {
                    listLab = new LabList();
                    listLab.order_id = rs.getString("order_id");
                    listLab.order_name = rs.getString("order_name");
                    listLab.item_nick_name = rs.getString("item_nick_name");
                    listLab.order_date = rs.getString("order_date");
                    listLab.order_execute_date = rs.getString("order_execute_date");
                    listLab.status = rs.getString("status");
                    listLab.ln = rs.getString("lab_no");
                    listLab.type = LabList.TYPE_NEW;
                    v.add(listLab);
                }
            }
            rs.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(err && v.size()==0)
            return null;
        return v;
    }
    public Vector<LabList> listLabOrderByVn2(String visit_id)
    {
        boolean err = false;
        Vector v = new Vector();
        try
        {
            String ln = "";
            String sql = "select * from t_lis_ln where t_visit_id = '"+this.theHO.theVisit.getObjectId()+"' order by lab_number desc limit 1";
            ResultSet rs = executeQuery(theConnection,sql);
            if(rs.next())
                ln = rs.getString("lab_number");
            System.err.println("ln "+ln);
            sql = "select t_lis_order.t_order_id as order_id "+
                ",t_lis_order.exec_datetime as order_execute_date "+
                ",bb.item_common_name as order_name "+
                ",bb.item_nick_name as item_nick_name "+
                ",aa.order_date_time as order_date "+
                ",t_lis_order.status as status "+
                ",t_lis_order.lab_number as lab_no "+
                "from (select * from t_lis_ln where lab_number = '"+ln+"') as cc "+
                "inner join t_lis_order on cc.lab_number = t_lis_order.lab_number "+
                "inner join (select t_order_id,b_item_id,order_date_time from t_order where t_order.t_visit_id = '"+visit_id+"' and t_order.f_order_status_id <> '3'  and t_order.f_order_status_id <> '0' and t_order.f_order_status_id <> '1') "+
                "as aa on aa.t_order_id = t_lis_order.t_order_id "+
                "inner join (select b_item_id,item_common_name,item_nick_name from b_item) as bb on bb.b_item_id = aa.b_item_id "+
                "where cc.t_visit_id = '"+visit_id+"' "+
                "group by t_lis_order.t_order_id"
                + ",t_lis_order.exec_datetime"
                + ",bb.item_common_name"
                + ",bb.item_nick_name"
                + ",aa.order_date_time"
                + ",t_lis_order.status"
                + ",t_lis_order.lab_number";
            rs = executeQuery(theConnection,sql);
            LabList listLab;
            while(rs.next())
            {
                listLab = new LabList();
                listLab.order_id = rs.getString("order_id");
                listLab.order_name = rs.getString("order_name");
                listLab.item_nick_name = rs.getString("item_nick_name");
                listLab.order_date = rs.getString("order_date");
                listLab.order_execute_date = rs.getString("order_execute_date");
                listLab.status = rs.getString("status");
                listLab.ln = rs.getString("lab_no");
                listLab.type = LabList.TYPE_NEW;
                v.add(listLab);
            }


            rs.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(err && v.size()==0)
            return null;
        return v;
    }

/**
 *
 * @param sql
 * @return
 */
    ResultSet executeQuery(Connection con,String sql) throws SQLException{
        System.out.println("sql:"+sql);
        theHosControl.theConnectionInf.open();
        return this.theHosControl.theConnectionInf.getConnection().createStatement().executeQuery(sql);
//        return con.createStatement().executeQuery(sql);
    }
    int executeUpdate(Connection con,String sql) throws SQLException{
        System.out.println("sql:"+sql);
        return con.createStatement().executeUpdate(sql);
    }


    public String getCurrentDateTime() {
        String str_dateTime = "";
        try {
            str_dateTime = getCurrentDateTime(theConnection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str_dateTime;
    }

    public String getCurrentDateTime(Connection c2) throws Exception {
        String date_time = "";
        java.sql.ResultSet rs = executeQuery(c2,"select CURRENT_TIME");
        if (rs.next()) {
            date_time = rs.getString(1);
        }
        String time = DateTime.convertThaiTime(date_time);
        rs = executeQuery(c2,"select CURRENT_DATE");
        if (rs.next()) {
            date_time = rs.getString(1);
        }
        String date = DateTime.convertThaiDate(date_time);
        date_time = date + "," + time;
        return date_time;
    }

    public String getCurrentDateTime(Connection c2, String minus_minute) throws Exception {
        String date_time = "";
        java.sql.ResultSet rs = executeQuery(c2,"select CURRENT_TIME-time '00:" + minus_minute + "'");
        if (rs.next()) {
            date_time = rs.getString(1);
        }
        String time = DateTime.convertThaiTime(date_time);
        rs = executeQuery(c2,"select CURRENT_DATE");
        if (rs.next()) {
            date_time = rs.getString(1);
        }
        String date = DateTime.convertThaiDate(date_time);
        date_time = date + "," + time;
        return date_time;
    }




    /** Somprasong copy from HOS*/
    private String setTemp(String temp) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("0000000000");
        temp = df.format(Double.parseDouble(temp));
        return temp;
    }
   //henbe add 140610///////////////////////////////////////////////////////////
    public boolean printResultLab(Vector vFromPanelLab,Vector vResult ,int[] rows,boolean preview)
    {
        String objectid =   null;
        cdb.open();
        try{
            if(theHO.thePatient == null) {
                theHosControl.theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
                return false;
            }
            if(theHO.theVisit == null) {
                theHosControl.theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.vVisitPayment == null) {
                theHosControl.theUS.setStatus(("ไม่สามารถพิมพ์ได้ เนื่องจากไม่พบสิทธิการรักษา"),UpdateStatus.WARNING);
                return false;
            }
            if(vResult.size() <= 0) {
                theHosControl.theUS.setStatus(("ไม่สามารถพิมพ์ได้ เนื่องจากผลแลปไม่ได้ส่ง"),UpdateStatus.WARNING);
                return false;
            }
            //ผู้ใช้ไม่เลือกหรือเลือกว่าไม่พิมพ์รายการใดๆ
            if(rows.length == 0){
                theHosControl.theUS.setStatus(("ไม่สามารถพิมพ์ได้ เนื่องจากยังไม่ได้เลือกรายการ Lab ที่จะพิมพ์"),UpdateStatus.WARNING);
                return false;
            }
            HashMap hm = new HashMap();
            String order_id = "";
            for(int i=0;i<rows.length;i++) {
                OrderItem orderitem = (OrderItem) vFromPanelLab.get(rows[i]);
                if(i!=0)  order_id += ",";
                order_id += "'"+orderitem.getObjectId()+"'";
            }
            hm.put("order_id", order_id);
            hm.put("visit_id", theHO.theVisit.getObjectId());
            int value_preview = 0;
            if(preview)
                value_preview = 1;
            boolean retp = initPrint("result_lab_con",value_preview,hm,null,true);
            if(!retp) return false;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            cdb.close();
        }
        return true;
    }
    public boolean printResultLab2(Vector vFromPanelLab,Vector vResult ,int[] rows,boolean preview)
    {
        String objectid =   null;
        cdb.open();
        try{
            if(theHO.thePatient == null) {
                theHosControl.theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
                return false;
            }
            if(theHO.theVisit == null) {
                theHosControl.theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.vVisitPayment == null) {
                theHosControl.theUS.setStatus(("ไม่สามารถพิมพ์ได้ เนื่องจากไม่พบสิทธิการรักษา"),UpdateStatus.WARNING);
                return false;
            }
            if(vResult.size() <= 0) {
                theHosControl.theUS.setStatus(("ไม่สามารถพิมพ์ได้ เนื่องจากผลแลปไม่ได้ส่ง"),UpdateStatus.WARNING);
                return false;
            }
            //ผู้ใช้ไม่เลือกหรือเลือกว่าไม่พิมพ์รายการใดๆ
            if(rows.length == 0){
                theHosControl.theUS.setStatus(("ไม่สามารถพิมพ์ได้ เนื่องจากยังไม่ได้เลือกรายการ Lab ที่จะพิมพ์"),UpdateStatus.WARNING);
                return false;
            }
            HashMap hm = new HashMap();
            String order_id = "";
            for(int i=0;i<rows.length;i++) {
                LabList orderitem = (LabList) vFromPanelLab.get(rows[i]);
                if(i!=0)  order_id += ",";
                order_id += "'"+orderitem.order_id+"'";
            }
            hm.put("order_id", order_id);
            hm.put("visit_id", theHO.theVisit.getObjectId());
            int value_preview = 0;
            if(preview)
                value_preview = 1;
            boolean retp = initPrint("result_lab_con",value_preview,hm,null,true);
            if(!retp) return false;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            cdb.close();
        }
        return true;
    }

    public void setSender(int i) {
        sender = i;
        System.setProperty("sender",String.valueOf(sender));
    }

    public void printStickerTube(Vector<LabList> labListV,int[] select,int preview)
    {
        boolean res = false;
        try
        {
            if(select.length == 0)
            {
                this.theUS.setStatus("กรุณาเลือกรายการแลปที่ต้องการพิมพ์ Sticker ติด Tube",UpdateStatus.WARNING);
                return;
            }
            String bartrend_txt = "";
            String sql = "";
            ResultSet rs;
            for(int i=0;i<select.length;i++)
            {
                LabList labList = labListV.get(select[i]);
                String str_labNo = null;
                if(labList.type.equals(LabList.TYPE_NEW))
                {
                    sql = "select lab_number from t_lis_order where t_order_id = '"+labList.order_id
                            +"' and exec_datetime = '"+labList.order_execute_date+"'";
                    rs = executeQuery(theConnection,sql);
                    if(rs.next())
                        str_labNo = rs.getString("lab_number");
                }
                else
                {
                    sql = "select t_lab_order_number_map_visit_id from " +
                    "t_lab_order_number_map_visit where t_visit_id = '"+theHosControl.theHO.theVisit.getObjectId()+"' " +
                    "and order_executed_date_time = '" + labList.order_execute_date + "'" +
                    " order by t_lab_order_number_map_visit_id limit 1";
                    rs = executeQuery(theConnection,sql);

                    while(rs.next())
                    {
                        str_labNo = rs.getString("t_lab_order_number_map_visit_id");
                    }
                    rs.close();

                    if(str_labNo==null||str_labNo.equals(""))
                    {
    //                    String ln_tmp = theHosControl.theHO.theVisit.getObjectId();
    //                    str_labNo = ln_tmp.substring(ln_tmp.length()-10, ln_tmp.length());
    //                    sql = "select * from t_map_visit_ln where t_visit_id ='" + theHosControl.theHO.theVisit.getObjectId() + "'"; 2553-09-21,15:54:23
                        sql = "select * from t_map_visit_ln where t_visit_id = '"+theHosControl.theHO.theVisit.getObjectId()+"' "
                                + "and map_visit_ln_record_date_time like '" + labList.order_execute_date.substring(0,18) + "%'";
                        ResultSet rs_ln = executeQuery(theConnection,sql);
                        String next_time_mil = "";
                        if(rs_ln.next())
                        {
                            str_labNo = rs_ln.getString("t_map_visit_ln_id");
                        }
                        else
                        {
                            next_time_mil = getNextTimeMil(labList.order_execute_date);
                            sql = "select * from t_map_visit_ln where t_visit_id = '"+theHosControl.theHO.theVisit.getObjectId()+"' "
                                + "and map_visit_ln_record_date_time = '" + next_time_mil + "'";
                            rs_ln = executeQuery(theConnection,sql);
                            if(rs_ln.next())
                            {
                                str_labNo = rs_ln.getString("t_map_visit_ln_id");
                            }
                            else
                            {
                                next_time_mil = getNextTimeMil(next_time_mil);
                                sql = "select * from t_map_visit_ln where t_visit_id = '"+theHosControl.theHO.theVisit.getObjectId()+"' "
                                    + "and map_visit_ln_record_date_time = '" + next_time_mil + "'";
                                rs_ln = executeQuery(theConnection,sql);
                                if(rs_ln.next())
                                {
                                    str_labNo = rs_ln.getString("t_map_visit_ln_id");
                                }
                                else
                                {
                                    next_time_mil = getNextTimeMil(next_time_mil);
                                    sql = "select * from t_map_visit_ln where t_visit_id = '"+theHosControl.theHO.theVisit.getObjectId()+"' "
                                        + "and map_visit_ln_record_date_time = '" + next_time_mil + "'";
                                    rs_ln = executeQuery(theConnection,sql);
                                    if(rs_ln.next())
                                    {
                                        str_labNo = rs_ln.getString("t_map_visit_ln_id");
                                    }
                                    else
                                    {
                                        next_time_mil = getNextTimeMil(next_time_mil);
                                        sql = "select * from t_map_visit_ln where t_visit_id = '"+theHosControl.theHO.theVisit.getObjectId()+"' "
                                            + "and map_visit_ln_record_date_time = '" + next_time_mil + "'";
                                        rs_ln = executeQuery(theConnection,sql);
                                        if(rs_ln.next())
                                        {
                                            str_labNo = rs_ln.getString("t_map_visit_ln_id");
                                        }
                                        else
                                        {
                                            next_time_mil = getNextTimeMil(next_time_mil);
                                            sql = "select * from t_map_visit_ln where t_visit_id = '"+theHosControl.theHO.theVisit.getObjectId()+"' "
                                                + "and map_visit_ln_record_date_time = '" + next_time_mil + "'";
                                            rs_ln = executeQuery(theConnection,sql);
                                            if(rs_ln.next())
                                            {
                                                str_labNo = rs_ln.getString("t_map_visit_ln_id");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

//                if(!ln)
//                {
//                    String sql = "select t_lab_order_number_map_visit_id from " +
//                    "t_lab_order_number_map_visit where t_visit_id = '"+theHosControl.theHO.theVisit.getObjectId()+"' " +
//                    "and order_executed_date_time = '" + labList.order_execute_date + "'" +
//                    " order by t_lab_order_number_map_visit_id limit 1";
//                    ResultSet rs = executeQuery(theConnection,sql);
//
//                    while(rs.next())
//                    {
//                        str_labNo = rs.getString("t_lab_order_number_map_visit_id");
//                    }
//                    rs.close();
//                }
//                else
//                {
//                    String ln_tmp = theHosControl.theHO.theVisit.getObjectId();
//                    str_labNo = ln_tmp.substring(ln_tmp.length()-10, ln_tmp.length());
//                }


                Map o;
                String[] specimen = labList.item_nick_name.split(",");
                for(int i2=0;i2<specimen.length;i2++)
                {
                    o = new HashMap();
                    o.put("t_order_id", labList.order_id);
                    System.err.println("labList.order_name = " + labList.order_name);
                    System.err.println("labList.order_id = " + labList.order_id);
                    //530616004376

                    //o.put("str_labNo","530616004376");
//                    String sql = "select b_item_id" +
//                    ",t_order_id,t_visit_id,order_verify_date_time" +
//                    ",order_service_point, order_executed_date_time" +
//                    " from t_order  " +
//                    " where " +
//                    "f_item_group_id = '2' and t_order_id = '" + labList.order_id + "'" +
//                    " and f_order_status_id = '2'" +
//                    " order by order_executed_date_time";
//                    ResultSet rs = executeQuery(theConnection,sql);
//                    String str_labNo = null;
//                    System.err.println("#############################");
//                    System.err.println("sql = " + sql);
//                    System.err.println("specimen[i2] = " + specimen[i2]);
//                    System.err.println("labList.order_id = " + labList.order_id);
//                    while(rs.next())
//                    {
//                        String str_curVisitId = rs.getString("t_visit_id");
//                        String str_verifyDateTime = rs.getString("order_verify_date_time");
//                        String str_execDateTime = rs.getString("order_executed_date_time");
//                        str_labNo = this.getOrderNumber(theConnection, str_curVisitId, str_verifyDateTime, str_execDateTime);
//                        System.err.println("t_visit_id = " + rs.getString("t_visit_id"));
//                        System.err.println("str_labNo = " + str_labNo);
//                    }
//                    rs.close();
//                    o.put("str_labNo",str_labNo);





//                    String str_labNo = this.getOrderNumber(theConnection, this.the, str_verifyDateTime, str_execDateTime);

                    String[] tmp = specimen[i2].split("=");
                    if(tmp.length >= 2)
                    {
                        o.put("str_labNo",str_labNo + tmp[0]);
                        o.put("item_nick_name", tmp[1]);
                        bartrend_txt = bartrend_txt + str_labNo + tmp[0] + ",";
                    }
                    else
                    {
                        bartrend_txt = bartrend_txt + " " + ",";
                    }
                    bartrend_txt = bartrend_txt + "(" +this.theHosControl.theHO.thePatient.hn + ") ";
                    bartrend_txt = bartrend_txt + this.theHosControl.theHO.thePatient.fname.replace(",", "") + " ";
                    bartrend_txt = bartrend_txt + this.theHosControl.theHO.thePatient.lname.replace(",", "") + ",";
                    int year = Integer.parseInt(labList.order_date.substring(0,4))-543;
                    String month = labList.order_date.substring(5, 7);
                    String day = labList.order_date.substring(8, 10);
                    String order_date = day+"/"+month+"/"+year;
                    o.put("order_date", order_date);
                    bartrend_txt = bartrend_txt + order_date + ",";
                    if(labList.order_name.length()>19)
                    {
                        bartrend_txt = bartrend_txt + labList.order_name.substring(0, 19).replace(",", "") + "-";
                    }
                    else
                    {
                        bartrend_txt = bartrend_txt + labList.order_name.replace(",", "") + "-";
                    }
                    if(tmp.length >= 2)
                    {
                        bartrend_txt = bartrend_txt + tmp[1] + ",1";
                    }
                    else
                    {
                        bartrend_txt = bartrend_txt + " " + ",1";
                    }
                    bartrend_txt = bartrend_txt + "\n";
                    if(preview == 1)
                        res = initPrint("sticker_tube128",preview,o,null,true);
                    if(res)
                        this.theUS.setStatus("พิมพ์ Sticker ติด Tube เสร็จสิ้น",UpdateStatus.COMPLETE);
                }
            }
            if(preview == 1)
                return;
            FileOutputStream fo = new FileOutputStream("C:/Bartend/Barcode.txt");
            Runtime runtime = Runtime.getRuntime();
            String path = "C:/Bartend/Bartend2.bat";
            fo.write(bartrend_txt.getBytes());
            fo.close();
            runtime.exec(path);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            this.theUS.setStatus("พิมพ์ Sticker ติด Tube ผิดพลาด",UpdateStatus.ERROR);
        }
    }
    protected boolean initPrint(String filename,int valuePrint,Map o,JRDataSource ds,boolean mode_con) throws Exception
    {
       JasperReport jp=null;
       String file_name = "hprinting/lab/"+filename+".jrxml";
       File file = new File(file_name);
       if(!file.isFile()){
           theHosControl.theUS.setStatus("ไม่พบไฟล์ที่ทำการสั่งพิมพ์"+
                   " "+file_name,UpdateStatus.WARNING);
           return false;
       }
       try{
           jp = JasperCompileManager.compileReport( "hprinting/lab/" +filename+".jrxml");
       }
       catch(Exception e){
           e.printStackTrace();
           theHosControl.theUS.setStatus("การสั่งพิมพ์รายการผิดพลาด",UpdateStatus.ERROR);
           return false;
       }
        JasperPrint jprint = null;
        if(mode_con)
            jprint = JasperFillManager.fillReport(jp,o,this.theConnection );
        else{
            if(ds!=null)
                jprint = JasperFillManager.fillReport(jp,o, ds);
            else
                jprint = JasperFillManager.fillReport(jp,o, new JREmptyDataSource());
        }
        if(valuePrint==1){
            JasperViewer.viewReport(jprint,false);
            return true;
        }
        JasperPrintManager.printReport(jprint, false);
        return true;
   }
    public static void main(String[] args) {
        String test = "P|1|5308090128|0705490||ขวัญแก้ว^^นาย กิตตินันต์||19860903|M||^^^||||^|||||||10/8/2010||20100810154229^20100810154229|R|||^O||||||0^|0^99";
        String arr[] = test.split("\\|");
        for(int i=0;i<arr.length;i++)
        {
            System.err.println(i + " " + arr[i]);
        }
    }
    public Vector convertToSpecimenView(Vector<LabList> theLabListV)
    {
        Vector<LabList> theLabListVTmp = new Vector();
        LabList tmp = new LabList();
        String tmp_arr[];
        HashMap hm = new HashMap();
        String index = "";
        for(int i=0;i<theLabListV.size();i++)
        {
            tmp = new LabList();
            tmp = theLabListV.get(i);
            tmp_arr = tmp.item_nick_name.split(",");
            for(int i2=0;i2<tmp_arr.length;i2++)
            {
                LabList tmp2 = new LabList();
                tmp2 = (LabList) theLabListV.get(i).clone();
                if(hm.size()==0)
                {
                    hm.put(tmp_arr[i2],tmp_arr[i2]);
                    tmp2.item_nick_name = tmp_arr[i2];
                    theLabListVTmp.add(tmp2);
                    if(!index.equals(""))
                        index+="-"+tmp_arr[i2];
                    else
                        index = tmp_arr[i2];
                }
                if(hm.get(tmp_arr[i2])==null)
                {
                    hm.put(tmp_arr[i2],tmp_arr[i2]);
                    tmp2.item_nick_name = tmp_arr[i2];
                    if(!index.equals(""))
                        index+="-"+tmp_arr[i2];
                    else
                        index = tmp_arr[i2];
                    theLabListVTmp.add(tmp2);
                }
            }
        }
        return theLabListVTmp;
    }
    public static String getNextTimeMil(String tmp)
    {
//        String tmp = "2553-09-21,15:54:59";
        int mil = Integer.parseInt(tmp.substring(17,tmp.length()))+1;
        int minute = Integer.parseInt(tmp.substring(14, 16));
        int hour = Integer.parseInt(tmp.substring(11, 13));
        if(mil>59)
        {
            mil = 0;
            minute = minute +1;
            if(minute>59)
            {
                minute = 0;
                hour = hour + 1;
            }
        }
        String mil_str = String.valueOf(mil);
        String minute_str = String.valueOf(minute);
        String hour_str = String.valueOf(hour);
        while(mil_str.length()<2)
        {
            mil_str = "0" + mil_str;
        }
        while(minute_str.length()<2)
        {
            minute_str = "0" + minute_str;
        }
        while(hour_str.length()<2)
        {
            hour_str = "0" + hour_str;
        }
        String first_path = tmp.substring(0,11);
        return first_path+hour_str+":"+minute_str+":"+mil_str;
    }

    public static String convertDateTime(String str)
    {
        int year = Integer.parseInt(str.substring(0, 4));
        year = year + 543;
        String month = str.substring(4, 6);
        String day = str.substring(6, 8);
        String hour = str.substring(8, 10);
        String minute = str.substring(10, 12);
        String mil_sec = str.substring(12, 14);
        String tmp = year+"/"+month+"/"+day+","+hour+":"+minute+":"+mil_sec;
        System.err.println(tmp);
        return tmp;
    }
}
