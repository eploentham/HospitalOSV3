/*
 * BalloonSubject.java
 *
 * Created on 7 ¾ÄÈ¨Ô¡ÒÂ¹ 2548, 16:40 ¹.
 */

package com.hosv3.subject;
import java.util.*;
import com.hosv3.usecase.transaction.*;
/**
 *
 * @author  kingland
 */
public class BalloonSubject implements ManageBalloon{
    
    /** Creates a new instance of BalloonSubject */
    Vector vBalloon;
    public BalloonSubject() {
        vBalloon = new Vector();
    }
    public void attachBalloon(ManageBalloon o){
        vBalloon.add(o);
    }
    
    public void notifyCloseBalloon(String str, int status) {
//        for(int i=0,size=vBalloon.size();i<size;i++){
//            ((ManageBalloon)this.vBalloon.get(i)).notifyCloseBalloon(str,status);
//        }
    }
    
}
