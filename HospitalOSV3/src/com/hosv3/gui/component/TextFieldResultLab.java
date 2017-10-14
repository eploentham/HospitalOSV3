/*
 * TextFieldRultLab.java
 *
 * Created on 28 �ѹ��¹ 2548, 16:46 �.
 */

package com.hosv3.gui.component;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JTextField;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.object.*;
import com.hospital_os.utility.Constant;

/**
 *
 * @author  kingland
 */
public class TextFieldResultLab extends JTextField implements FocusListener, KeyListener{
    
    /** Creates a new instance of TextFieldRultLab */
    float max = -999;
    float min = -999;
    int mode = 0;
    UpdateStatus theUS;
    // Somprasong 20101012
    String flag = "";
    boolean nonormal = false;
    public TextFieldResultLab(UpdateStatus us){
        this.theUS = us;
        this.setRequestFocusEnabled(true);
        this.addKeyListener(this);
    }
    public TextFieldResultLab(int mode,UpdateStatus us){
        this.theUS = us;
        this.mode = mode;
        this.setRequestFocusEnabled(true);
        this.addKeyListener(this);
    }
    public TextFieldResultLab(Font defaultFont1,int mode,UpdateStatus us,LabResultItem lri,OrderItem oi,ResultLab rl)
    {
        this.theUS = us;
        this.mode = mode;
        this.setRequestFocusEnabled(true);
        this.addKeyListener(this);
        setFont(defaultFont1);
        setMax(lri.max);
        setMin(lri.min);
        setFlag(lri.flag);
        if("1".equals(oi.secret)){
            //�ѭ�Ҥ�͡���ʴ����ѧ�Դ����������� mode ��ͻ������ͧ���Ż
            //if(mode==1){
                setText(String.valueOf(rl.result)); //amp:22/02/2549
                setToolTipText(String.valueOf(rl.result));
//            }
//            else
//                setText("*********");
        }
        else{
            setText(rl.result);
            setToolTipText(rl.result);
        }
        
       if(oi.status.equals(OrderStatus.REPORT)) 
            setEnabled(false);        
        else
            setEnabled(true);    
    }
    /**
     * ����觤�� Flag 㹡óշ�� ����¡�õ�Ǩ��Ф�һ����繵���Ţ ���ա���觤�� Flag �繴ѧ���
"||" ����դ�����͡�ҡ���� Flag �����ҧ㹡óշ��š�õ�Ǩ�������º�Ѻ ��һ��Ԣͧ LIS �繤�һ��� �� ��һ��� "10 - 20" �š�õ�Ǩ "15"
"N" ����� Flag 㹡óշ��š�õ�Ǩ�������º�Ѻ ��һ��Ԣͧ LIS �繤�һ��� �� ��һ��� "Negative" �š�õ�Ǩ "Negative" ���� "< 20" �š�õ�Ǩ "15"
"L" ����� Flag 㹡óշ��š�õ�Ǩ�������º�Ѻ ��һ��Ԣͧ LIS �繤�һ��� �� ��һ��� "10 - 20" �š�õ�Ǩ "5" ���� ��һ��� "> 20" �š�õ�Ǩ "5"
"H" ����� Flag 㹡óշ��š�õ�Ǩ�������º�Ѻ ��һ��Ԣͧ LIS �繤�һ��� �� ��һ��� "10 - 20" �š�õ�Ǩ "25" ���� ��һ��� "< 20" �š�õ�Ǩ "25"
"AA" ����� Flag 㹡óշ��š�õ�Ǩ�������º�Ѻ ��һ��Ԣͧ LIS �繤�һ��� �� ��һ��� "Negative" �š�õ�Ǩ "Trace" ���� ��һ��� "Negative" �š�õ�Ǩ "Positive" ��觤�� Flag "AA"
     * @return
     */
    public boolean isAbnormalValue(){
        try{
            boolean normal = (flag == null|| flag.equals("null")|| flag.equals(""))?true:(flag.equalsIgnoreCase("n")?true:false);
            if(normal && (flag == null|| flag.equals("null")|| flag.equals(""))){
            float value = Float.parseFloat(getText());
            if(max==-999||min==-999)
                return false;
            if(value > max || value < min){
                return true;
            }
            return false;
            }else if(normal){
                return false;
            }else{
                return true;
            }
        }
        catch(Exception e){
            Constant.println(e.getMessage());
            return false;
        }
    }
    public boolean isNormalValue(){
        try{
            boolean normal = (flag.equalsIgnoreCase("n")?true:false);
            if(normal){
                return true;
            }else{
            float value = Float.parseFloat(getText());
            if(value <= max && value >= min){
                return true;
            }
            return false;
            }
        }
        catch(Exception e){
            Constant.println(e.getMessage());
            return false;
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    public void setMax(String max){
        if(!max.equals("")){
            try{
                this.max = Float.parseFloat(max);
            }
            catch(Exception e){
                Constant.println("Convert max from string to float error");
            }
        }
    }
    public void setMin(String min){
        if(!min.equals("")){
            try{
                this.min = Float.parseFloat(min);
            }
            catch(Exception e){
               Constant.println("Convert min from string to float error");
            }
        }
    }

    public void setFlag(String flag){
        this.flag = flag;
    }
    public void focusGained(FocusEvent e) {
        String str = this.getText();
        try{
            if(!str.equals("")){
                float value = Float.parseFloat(str);
                if(value > max || value < min){
                    this.theUS.setStatus(Constant.getTextBundle("��һ��Ԣͧ���Ż�����������ҧ")
                            +" "+min+" - "+max, UpdateStatus.WARNING);
//                    this.setText("");
                    this.requestFocus();
                }
            }
        }
        catch(Exception ex){
            Constant.println("Exception");
            this.theUS.setStatus("��һ��Ե�ͧ�繵���Ţ���� ���� ��ҷȹ���", UpdateStatus.WARNING);
            //this.setText("");
            this.requestFocus();
        }
    }
    
    public void focusLost(FocusEvent e) {
        String str = this.getText();        
        try{
            if(!str.equals("")){
                 Constant.println("try");
                float value = Float.parseFloat(str);
                if(value > max || value < min){
                    Constant.println("if");
                    this.theUS.setStatus(Constant.getTextBundle("��һ��Ԣͧ���Ż�����������ҧ")
                            +" "+min+" - "+max, UpdateStatus.WARNING);
//                    this.setText("");
                    this.requestFocus();
                }
            }
        }
        catch(Exception ex){
            Constant.println("Exception");
            this.theUS.setStatus("��һ��Ե�ͧ�繵���Ţ���� ���� ��ҷȹ���", UpdateStatus.WARNING);
            //this.setText("");
            this.requestFocus();
        }
    }
    
    public void keyPressed(KeyEvent e) {
    }
    
    public void keyReleased(KeyEvent e) {
        this.requestFocus();
    }
    
    public void keyTyped(KeyEvent e) {
    }
    ///////////////////////////////////////////////////////////////////////////
    public void check(){
        String str = this.getText();
        char[] c = str.toCharArray();
        if(mode == 1){
//            checkPoint(str);
        }
        else{
            for(int i = 0; i < c.length;i++){
                if(c[i] == '.'){
                    this.theUS.setStatus("��سҡ�͡����Ţ�ӹǹ���", UpdateStatus.WARNING);
                    //this.setText("");
                    return;
                }
            }
        }
            
        
        try{
            if(!str.equals("")){
                float value = Float.parseFloat(str);
                if(value > max || value < min){
                    this.theUS.setStatus(Constant.getTextBundle("��һ��Ԣͧ���Ż�����������ҧ")
                            +min+" - "+max, UpdateStatus.WARNING);
//                    this.setText("");
                    this.requestFocus();
                }
            }
        }
        catch(Exception ex){
            Constant.println("Exception");
            this.theUS.setStatus("��һ��Ե�ͧ�繵���Ţ���� ���� ��ҷȹ���", UpdateStatus.WARNING);
            //this.setText("");
            this.requestFocus();
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     *��Ǩ�ͺ����繨ش�ȹ���
     */
    private void checkPoint(String str){
        boolean isDot = false;
        char[] c = str.toCharArray();
        for(int i = 0; i < c.length;i++){
            if(c[i] == '.'){
                if(c.length-1 >= i+1){
                    isDot = true;
                    break;
                }
                else{
                    //this.setText("");
                    this.requestFocus();
                    isDot = false;
                    break;
                }
            }
        }
        if(isDot){
             try{
                if(!str.equals("")){
                    Float.parseFloat(this.getText());
                }
            }
            catch(Exception e){
                this.theUS.setStatus(Constant.getTextBundle("��һ��Ԣͧ���Ż�����������ҧ")
                            +min+" - "+max, UpdateStatus.WARNING);
//                this.setText("");
                this.requestFocus();
            }
        }
        else if(!str.equals("")){
            //this.setText("");
            this.requestFocus();
        }
    }

    void setNoNormal(boolean b) {
        this.nonormal = b;
    }
}
