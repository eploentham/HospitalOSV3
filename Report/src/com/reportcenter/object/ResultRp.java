/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reportcenter.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 *
 * @author henbe
 */
public class ResultRp {
    public static String NEWLINE = "\n";
    public static String NEWLINE_HTML = "<br>";

    public static void showDialog(String parseHtml) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void copyFile(File in, File out) throws Exception {
         FileInputStream fis = new FileInputStream(in);
         FileOutputStream fos = new FileOutputStream(out);
         byte[] buf = new byte[1024];
         int i = 0;
             while((i=fis.read(buf))!=-1) {
             fos.write(buf, 0, i);
         }
         fis.close();
         fos.close();
    }

    public String missing_cause = "";
    public int total;
    public int pass;
    public String current_file;
    public Vector records = new Vector();

    public static String parseString(Vector v){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<v.size();i++){
            ResultRp rr = (ResultRp)v.get(i);
            sb.append(rr.toString()).append(ResultRp.NEWLINE);
        }
        return sb.toString();
    }
    public String toString(){
        StringBuffer ret = new StringBuffer();
        ret.append("current_file = " + current_file).append(ResultRp.NEWLINE);
        ret.append("total = " + total).append(ResultRp.NEWLINE);
        ret.append("pass = " + pass).append(ResultRp.NEWLINE);
        ret.append("missing_cause = " + missing_cause).append(ResultRp.NEWLINE);
        return ret.toString();
    }
    /**
     * <html><body></body></html>
     * <a href='#tip'>0</a> <a name='tip'></a>
     * @param v
     * @return
     */
    public static String parseHtmlTotal(Vector v, String date_time){
        StringBuffer sb = new StringBuffer("<html><body><h3>รายงานผลการออกรายงาน<br>ประจำวันที่ ")
                .append(date_time).append("</h3><table border='1'>");
        sb.append("\n<tr><td>ชื่อแฟ้ม</td><td>ทั้งหมด</td><td>ผ่าน</td><td>ไม่ผ่านDataDict</td><td>รายละเอียด</td></tr>");
        for(int i=0;i<v.size();i++){
            ResultRp rr = (ResultRp)v.get(i);
            sb.append(rr.toHtml());
        }
        sb.append("</table>สามารถตรวจสอบข้อมูลได้ที่ Report/history/RpXX_xxxx_xxxx.html</body></html>");
        return sb.toString();
    }
    /**
     * <html><body></body></html>
     * <a href='#tip'>0</a> <a name='tip'></a>
     * @param v
     * @return
     */
    public static String parseHtml(Vector v, String date_time,String date,String dbname){
        StringBuffer sb = new StringBuffer("<html><body><h3>รายงานผลการออกรายงาน<br>ประจำวันที่ ").append(date_time)
                .append("</h3><b>ฐานข้อมูล : ").append(dbname)
                .append("<br>ช่วงเวลา : ").append(date)
                .append("</b><br><table border='1'>");
        sb.append("\n<tr><td>ชื่อแฟ้ม</td><td>ทั้งหมด</td><td>ผ่าน</td><td>ไม่ผ่านDataDict</td><td>รายละเอียด</td></tr>");
        for(int i=0;i<v.size();i++){
            ResultRp rr = (ResultRp)v.get(i);
            sb.append(rr.toHtml());
        }
//        sb.append("</table><br><table border='1'>\n<tr><td>ชื่อแฟ้ม</td><td>รายละเอียดที่ผิดพลาด</td></tr>");
//        for(int i=0;i<v.size();i++){
//            ResultRp rr = (ResultRp)v.get(i);
//            sb.append(rr.toHtml2());
//        }
        sb.append("</table><br>\nรายการที่ผิดพลาด <br>");
        for(int i=0;i<v.size();i++){
            ResultRp rr = (ResultRp)v.get(i);
            sb.append(rr.toHtml3());
        }
        sb.append("สามารถตรวจสอบข้อมูลได้ที่ Report/history/RpXX_xxxx_xxxx.html</body></html>");
        return sb.toString();
    }
    /**
     * <table>
     * <tr><td>xx</td>..... </tr>
     * </table>
     * @return
     */
    public String toHtml(){
        StringBuffer ret = new StringBuffer();
        ret.append("\n<tr><td>").append(current_file).append("</td>");
        ret.append("<td align='right'>").append(total).append("</td>");
        ret.append("<td align='right'>").append(pass).append("</td>");
        if((total-pass)>0)
            ret.append("<td align='right'><a href='#").append(current_file).append("1'>").append(total-pass).append("</a></td>");
        else
            ret.append("<td align='right'>").append(total-pass).append("</td>");
 
        ret.append("<td>").append(missing_cause).append("</td></tr>");
        return ret.toString();
    }
    public String toHtml2(){
        StringBuffer ret = new StringBuffer();
        if(missing_cause.equals(""))
            return "";
        ret.append("\n<tr><td><a name='").append(current_file).append("'>").append(current_file).append("</a></td>");
        ret.append("<td><a href='#").append(current_file).append("1'>").append(missing_cause).append("</a></td>");
        ret.append("</tr>");
        return ret.toString();
    }
    public String toHtml3(){
        StringBuffer ret = new StringBuffer();
        if(records.isEmpty())
            return "";
        ret.append("\n<a name='").append(current_file).append("1'>").append(current_file).append("</a><table border='1'>");
        for(int i=0;i<records.size();i++){
            String[] record = (String[])records.get(i);
            ret.append("\n<tr>");
            for(int j=0;j<record.length;j++){
                ret.append("<td>").append(record[j]).append("</td>");
            }
            ret.append("</tr>");
        }
        ret.append("</table>");
        return ret.toString();
    }
    public static String getNameAddress(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            byte[] ipAddr = addr.getAddress();
            String hostname = addr.getHostName() + ":" + addr.getHostAddress();
            return hostname;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] argc){
        System.out.println(getNameAddress());
    }
}
