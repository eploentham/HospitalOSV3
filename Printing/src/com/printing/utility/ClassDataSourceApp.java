/*
 * ClassDataSourceApp.java
 *
 * Created on 21 ����Ҥ� 2547, 10:57 �.
 */

package com.printing.utility;
import net.sf.jasperreports.engine.*;

//import dori.jasper.engine.*;
/**
 *
 * @author  tong
 */
public interface ClassDataSourceApp// implements JRDataSource
{
    
    /** Creates a new instance of ClassDataSourceApp */
    
    public Object getFieldValue(JRField jRField) throws JRException;
    public boolean next() throws JRException ;
    
}
