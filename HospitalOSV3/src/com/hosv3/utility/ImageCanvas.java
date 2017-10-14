/*
 * ImageCanvas.java
 *
 * Created on 16 มิถุนายน 2549, 9:25 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.utility;

import java.awt.*;
import java.awt.image.*;
import java.util.Vector;
import java.util.Hashtable;
import com.hosv3.utility.connection.ExecuteControlInf;

//import javax.swing.JPanel;


public class ImageCanvas extends Canvas implements java.awt.event.MouseListener,java.awt.event.MouseMotionListener {
    public static Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    public static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    public static Cursor moveCursor = new Cursor(Cursor.MOVE_CURSOR);
    public static Cursor crosshairCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
    
    Vector vPoint = new Vector();
    ExecuteControlInf theEC;
    Image image;
    MediaTracker media;
    Graphics theGraphics;
    Hashtable theHashImage; 
    ImagePoint theImagePoint;
    int theImageID;
            
    public static int NORMAL_MODE = 0;
    public static int INSERT_MODE = 1;
    public static int SELECT_MODE = 2;
    
    boolean LOAD_IMAGE = false;
    int POINT_X;
    int POINT_Y;
    int CURRENT_MODE = 0;
    int BEGIN_MODE = -1;
    int SET_MODE = -1;
    int POINT_WIDTH = 10;
    int START_PAINT_X = -1;
    int START_PAINT_Y = -1;
    /********/
    public ImageCanvas(String name) 
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        media = new MediaTracker(this);
        image = Toolkit.getDefaultToolkit().getImage(name);
        media.addImage(image, 0);
        try 
        {
          media.waitForID(0);
        }
        catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
        }
    }
    /********/
     public ImageCanvas(ImageProducer imageProducer) 
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        image = createImage(imageProducer);
    }
    /********/
     public ImageCanvas()
     {
        addMouseListener(this);
        addMouseMotionListener(this);
     }
     public void setEC(ExecuteControlInf ec){
         theEC = ec;
     }
    /**
     * อ่านรูปภาพ
     * @author kingland
     * @date 16/06/2549
     */
    public Image getImage()
    {
      return image;
    }
    /**
     * ขนาดความกว้างของรูป
     * @author kingland
     * @date 16/06/2549
     */
    public int getImageWidth()
    {
        int result = -1;
        if(image != null)
        {
            result = image.getWidth(null);
            return result;
        }
        return result;
    }
    /**
     * ขนาดความยาวของรูป
     * @author kingland
     * @date 16/06/2549
     */
    public int getImageHight()
    {
        int result = -1;
        if(image != null)
        {
            result = image.getHeight(null);
        }
        return result;
    }
    /**
     * ใช้ในการวาดรูปลงบน Canvas
     * @author kingland
     * @date 16/06/2549
     */
    public void paint(Graphics g) 
    {   
        
        theGraphics = g;
        if(image != null){                        
            START_PAINT_X =(this.getWidth()-this.getImageWidth())/2;
            START_PAINT_Y = (this.getHeight()-this.getImageHight())/2;
            g.drawImage(image,START_PAINT_X,START_PAINT_Y,this.getImageWidth(),this.getImageHight(), this);
            LOAD_IMAGE = true;
        }
        this.paintPoint(g);
    }
    /**
     * พิกัดปัจจุบัน
     * @author kingland
     * @date 16/06/2549
     */
    public Point getCurrentPoint()
    {
        return new Point(POINT_X,POINT_Y);
    }
    /**
     * พิกัดทั้งหมด
     * @author kingland
     * @date 16/06/2549
     */
    public Vector getAllPoint()
    {
        return vPoint;
    }
    /**
     * นับจำนวนจุด
     * @author kingland
     * @date 16/06/2549
     */
    public int countPoint()
    {
        if(vPoint == null )return 0;
        return vPoint.size();
    }
    /**
     * เพิ่มรูป
     * @author kingland
     * @date 16/06/2549
     */
    public void addImage(Image img,int id)
    {
        if(media == null)media = new MediaTracker(this);
        media.addImage(img,id);
        if(theHashImage == null )theHashImage = new Hashtable();
        theHashImage.put(String.valueOf(id), img);
    }
    /**
     * โหลดรูป
     * @author kingland
     * @date 16/06/2549
     */
    public void loadImage(int id)
    {
        try
        {
            media.waitForID(id);            
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        image = (Image)theHashImage.get(String.valueOf(id));        
        this.repaint();
        theImageID = id;
    }
    /**
     * กำลังแสดงรูปที่เท่าไรอยู่
     * @author kingland
     * @date 16/06/2549
     */
    public int getImageID()
    {
        return theImageID;
    }
    /**
     * ดูโหมดว่าเป็นโหมดใด
     * @author kingland
     * @date 16/06/2549
     */
    public int getMode()
    {
        return CURRENT_MODE;
    }
    /**
     * เซตค่าโหมด
     * @author kingland
     * @date 16/06/2549
     */
    public void setMode(int mode)
    {
        if(BEGIN_MODE != CURRENT_MODE) BEGIN_MODE = CURRENT_MODE;
        CURRENT_MODE = mode;
        SET_MODE = mode;
    }
    /**
     * เซต Cursor
     * @author kingland
     * @date 16/06/2549
     */
    public void setCursor()
    {
        if(CURRENT_MODE == NORMAL_MODE){
            this.setCursor(this.defaultCursor);
        }
        else if(CURRENT_MODE == INSERT_MODE){
            this.setCursor(this.crosshairCursor);
        }
        else if(CURRENT_MODE == SELECT_MODE){
            this.setCursor(this.handCursor);
        }
    }
   /**
     * กำหนดจุด
     * @paramitor x = ตำแหน่งในแกนx y = ตำแหน่งในแกน y
     * @author kingland
     * @date 16/06/2549
     */
    public void setPoint(int x,int y,Color color)
    {
        if(vPoint == null)vPoint = new Vector();
        if(x <= START_PAINT_X + this.getImageWidth() && x >= START_PAINT_X
        && y <= START_PAINT_Y + this.getImageHight() && y >= START_PAINT_Y){
            theImagePoint = new ImagePoint(x,y, color);
            theImagePoint.setOrder(countPoint()+1);
            vPoint.add(theImagePoint);
        }
//        Constant.println("########theImagePoint##########"+theImagePoint);
//        Constant.println("########vPoint##########"+vPoint.size());
    }
    /**
     * กำหนดจุด
     * @author kingland
     * @date 16/06/2549
     */
    public void setPoint(ImagePoint point)
    {
        if(vPoint == null)vPoint = new Vector();
        if(point.x <= START_PAINT_X + this.getImageWidth() && point.x >= START_PAINT_X
        && point.y <= START_PAINT_Y + this.getImageHight() && point.y >= START_PAINT_Y)
        {
            theImagePoint = point;
            vPoint.add(point);
        }
    }
    /**
     * กำหนดจุด
     * @author kingland
     * @date 16/06/2549
     */
    public void setPoint(Vector vPoint)
    {
        if(vPoint == null)vPoint = new Vector();
        this.vPoint = vPoint;
    }
    public void removePoint(ImagePoint point)
    {
        vPoint.remove(point);
    }
    /**
     * วาดรูปจุด
     * @author kingland
     * @date 16/06/2549
     */
    public void paintPoint(Graphics g)
    {
        int  x = -1;
        int y = -1;
        for(int i=0,size=vPoint.size();vPoint != null && i<size;i++)
        {
            ImagePoint imp = (ImagePoint)vPoint.get(i);
            x = imp.x - POINT_WIDTH;
            y = imp.y - POINT_WIDTH;
            if(x<0){
                x = 0;
            }
            if(y<0){
                y = 0;
            }
            g.setColor(imp.getColor());
            g.fillRect(x, y, POINT_WIDTH*2, POINT_WIDTH*2);
        }
    }
    /**
     * เซตขนาดของจุด
     * @author kingland
     * @date 16/06/2549
     */
    public void setPointWidth(int pointwidth)
    {
        this.POINT_WIDTH = pointwidth;
    }
    /**
     * ตรวจสอบว่าเป็นจุดใช่หรือไม่
     * @author kingland
     * @date 16/06/2549
     */
    public boolean isPoint()
    {
        boolean result = false;
        int x = -1;
        int y = -1;   
//        setMode(this.SELECT_MODE);  
        for(int i=0,size=vPoint.size();vPoint != null && i<size;i++)
        {
            theImagePoint = (ImagePoint)vPoint.get(i);
            x = theImagePoint.x - this.POINT_WIDTH;
            y = theImagePoint.y - this.POINT_WIDTH;
            if(this.POINT_X >= x && this.POINT_X <= (x+(this.POINT_WIDTH*2)) 
            && this.POINT_Y >= y && this.POINT_Y <= (y+(this.POINT_WIDTH*2)) )
            {
                result = true;
                break;
            }
        }
        if(result == false){
            theImagePoint = null;
        }
        return result;
    }    
    
    /**
     * ตรวจสอบว่าเป็นจุดใช่หรือไม่
     * @author kingland
     * @date 16/06/2549
     */
    public ImagePoint getImagePoint()
    {
        boolean result = false;
        int x = -1;
        int y = -1;   
//        setMode(this.SELECT_MODE);  
        for(int i=0,size=vPoint.size();vPoint != null && i<size;i++)
        {
            theImagePoint = (ImagePoint)vPoint.get(i);
            x = theImagePoint.x - this.POINT_WIDTH;
            y = theImagePoint.y - this.POINT_WIDTH;
            if(this.POINT_X >= x && this.POINT_X <= (x+(this.POINT_WIDTH*2)) 
            && this.POINT_Y >= y && this.POINT_Y <= (y+(this.POINT_WIDTH*2)) )
            {
                result = true;
                return theImagePoint;
            }
        }
        return null;
    }    
    /**
     * แสดงค่าของจุดที่เลือก
     * @author kingland
     * @date 16/06/2549
     */
    public ImagePoint getSelectImagePoint()
    {
        return theImagePoint;
    }
    
    public static void main(String argv[]) 
    {
        String image  = "C:/Documents and Settings/Administrator/My Documents/My Pictures/full_front400.gif";    
        Frame frame = new Frame(image);
        frame.setLayout(new BorderLayout());
        ImageCanvas img = new ImageCanvas(image);
        img.setMode(1);
        img.setPointWidth(3);
        int x = img.getImage().getWidth(null);
        int y = img.getImage().getHeight(null);       
        frame.add("Center", img);    
        frame.setSize(x,y+15);
        frame.setVisible(true);
    }

    public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {
    }

    public void mouseDragged(java.awt.event.MouseEvent mouseEvent) {
    }

    public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {
    }

    public void mouseExited(java.awt.event.MouseEvent mouseEvent) {
    }

    public void mouseMoved(java.awt.event.MouseEvent e) {
        POINT_X = e.getX();
        POINT_Y = e.getY();
        setMode(SET_MODE);
        boolean is_insert = CURRENT_MODE==INSERT_MODE;
        setCursor();
        if(this.isPoint()){
            CURRENT_MODE = SELECT_MODE;
//            Constant.println("######################ISPOINT########################");
            setCursor();
            if(theEC!=null && is_insert)
                theEC.execute(getImagePoint());
        }
//        Constant.println("######################e.x########################"+e.getX());
//        Constant.println("######################e.y########################"+e.getY());
    }

    public void mousePressed(java.awt.event.MouseEvent mouseEvent) {
    }

    public void mouseReleased(java.awt.event.MouseEvent e) {        
//        if(this.CURRENT_MODE == this.INSERT_MODE)
//        {
//            if(this.POINT_X <= START_PAINT_X + this.getImageWidth() && this.POINT_X >= START_PAINT_X
//            && this.POINT_Y <= START_PAINT_Y + this.getImageHight() && this.POINT_Y >= START_PAINT_Y)
//            {
//                setPoint(new ImagePoint(this.POINT_X, this.POINT_Y, Color.RED));
//                repaint();
//            }
//        }
    }    
    
}

 

