package com.hospital_os.utility;

/*
 * GUIException.java
 *
 * Created on 29 �ѹ��¹ 2546, 15:55 �.
 */

/**
 *
 * @author  Surachai Thowong
 */
public class GUIException extends java.lang.Exception
{
    
    /**
     * Creates a new instance of <code>GUIException</code> without detail message.
     */
    public GUIException()
    {
    }
    
    
    /**
     * Constructs an instance of <code>GUIException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public GUIException(String msg)
    {
        super(msg);
    }
}
