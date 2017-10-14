/*
 * PrintSelectDrugList.java
 *
 * Created on 11 กรกฎาคม 2548, 13:00 น.
 */

package com.hosv3.object.printobject;
import java.util.*;
/**
 *
 * @author  kingland
 * not@deprecated ให้ย้ายไปอยู่ใน printing.jar ดีกว่า
 */
public class PrintSelectDrugList {
    
    /** Creates a new instance of PrintSelectDrugList */
    public boolean selectdrug = false;
    public boolean selectxray = false;
    public boolean selectlab = false;
    public boolean selectsupply = false;
    public boolean selectservice = false;
    public int typePrint;
    public String employeeid ;
    public String nameDoctor ;
    public Vector vDoctor;
    public PrintSelectDrugList() {
    }
    
}
