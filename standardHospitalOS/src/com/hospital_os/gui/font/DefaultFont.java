/*
 * DefaultFont.java
 *
 * Created on 26 กรกฎาคม 2549, 14:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.gui.font;

import com.hospital_os.utility.IOStream;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Henbe
 * การปรับแก้ฟ้อนต์ไม่สามารถทำให้อ่านจาก OS ของเครื่องได้เนื่องจากว่าภายใน class นี้
 * มีการ new ทุกครั้งที่มีการสร้าง gui การเพิ่มการทำงานเพื่อค้นหาฟอ้นต์ของระบบจะทำให้เกิดความช้า
 * ในการแสดงหน้าจอและมีการทำงานซ้ำซ้อนกัน 
 */
public class DefaultFont extends java.awt.Font{

        public static String filenamefont = "config/gui/Font.xml";
        public static DefaultFont system_font = null;
        public static DefaultFont system_font1 = new DefaultFont("tahoma",PLAIN,13);
    /** Creates a new instance of DefaultFont */
    public DefaultFont() {
        super(DefaultFont.getFont().getName(),DefaultFont.getFont().getStyle()
                ,DefaultFont.getFont().getSize());
//        super("tahoma",PLAIN,13);
    }
    public DefaultFont(Font font) {
        super(font.getFontName(),font.getStyle(),font.getSize());
    }
    public DefaultFont(String name,int style,int size) {
        super(name,style,size);
    }
    public static DefaultFont getFont(){
        if(system_font!=null)
            return system_font;
        Font font = readFontFile();
        if(font!=null){
            system_font = new DefaultFont(font);
            return system_font;
        }
        try {
            File file = new File("font/Loma.ttf");
            if(!file.exists())
                return new DefaultFont(new Font("tahoma",PLAIN,13));
            Font tahoma = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("font/Loma.ttf"));
            tahoma = tahoma.deriveFont(Font.PLAIN, 13);
            system_font = new DefaultFont(tahoma);
            return system_font;
        }
        catch (FontFormatException ex) {
            ex.printStackTrace();
            return system_font1;
        } catch (IOException ex) {
            ex.printStackTrace();
            return system_font1;
        }
    }
    public static Font readFontFile(){
        File file = new File(filenamefont);
        if(!file.exists())
            return null;
        String str = IOStream.readInput(filenamefont);
        if(str==null)
            return null;
        try{
            String font_name = str.substring(str.indexOf("<font_name>")+11,str.indexOf("</font_name>"));
            int font_style = Integer.parseInt(str.substring(str.indexOf("<font_style>")+12,str.indexOf("</font_style>")));
            int font_size = Integer.parseInt(str.substring(str.indexOf("<font_size>")+11,str.indexOf("</font_size>")));
            return new Font(font_name,font_style,font_size);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] argc){
    }
}
