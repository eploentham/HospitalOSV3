/*
 * Test.java
 *
 * Created on 27 ���Ҥ� 2549, 15:44 �.
 */

package com.hospital_os.utility;

import java.io.File;
import java.io.IOException; 
import javax.sound.sampled.*;
 
/**
 *
 * @author kingland
 */
public class Audio implements Runnable
{
        Thread thread;
        /**
         * ��㹡������ҹ������§�����
         */    
	AudioInputStream din = null;
        /**
         * �ٻẺ
         */        
        AudioFormat decodedFormat;
        /**
         * ��㹡������ҹ������§�����
         */        
        AudioInputStream in;
        /**
         * ��ҹ�����ŷ���кѹ�Ѵ
         */        
        SourceDataLine line;
        
        
        public Audio(File file)
        {   
            try
            {   
                in = AudioSystem.getAudioInputStream(file);
                AudioFormat baseFormat = in.getFormat();
                decodedFormat = new AudioFormat(
                                AudioFormat.Encoding.PCM_SIGNED,
                                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                                false);
            }
            catch(Exception ex)
            {
                ex.printStackTrace(Constant.getPrintStream());
            }
        }
        
        public Audio(String filepath)
        {
            Constant.println("=========filepath============="+filepath);
             try
            {
                File file = new File(filepath);
                in = AudioSystem.getAudioInputStream(file);
                AudioFormat baseFormat = in.getFormat();
                decodedFormat = new AudioFormat(
                                AudioFormat.Encoding.PCM_SIGNED,
                                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                                false);
            }
            catch(Exception ex)
            {
                ex.printStackTrace(Constant.getPrintStream());
            }
        }
        
	public static void main(String[] args)
        {   
		AudioInputStream din = null;
		try 
                {
			File file = new File("c://complete.wav");//("c://test.mp3");
			AudioInputStream in = AudioSystem.getAudioInputStream(file);
			AudioFormat baseFormat = in.getFormat();
			AudioFormat decodedFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
					baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
					false);
			din = AudioSystem.getAudioInputStream(decodedFormat, in);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
			if(line != null) 
                        {
				line.open(decodedFormat);
				byte[] data = new byte[10240];
				// Start
				line.start();				
				int nBytesRead;
				while ((nBytesRead = din.read(data, 0, data.length)) != -1)
                                {	
					line.write(data, 0, nBytesRead);
				}
				// Stop
				line.drain();
				line.stop();
				line.close();
				din.close();
			}
			
		}
		catch(Exception e) 
                {
			e.printStackTrace(Constant.getPrintStream());
		}
		finally 
                {
			if(din != null)
                        {
				try { din.close(); } catch(IOException e) { }
			}
		}
             
            
            
            
	}
        /**
         * ���¡������͵�ͧ���������
         * @param
         * @return
         * @author kingland
         * @date 28/01/2549
         */        
        public void play()
        {
            thread =  new Thread(this);
            thread.start();
            stop();
        }
        /**
         * ���¡������͵�ͧ�����ش���
         * @param
         * @return
         * @author kingland
         * @date
         */        
        public void stop()
        {
            this.thread.yield();
            try
            {    
                if(line != null)
                {
                    line.drain();
                    line.stop();
                    line.close();
                    din.close();
                }
                
            }
            catch(Exception ex)
            {
                ex.printStackTrace(Constant.getPrintStream());
            }
            if(this.thread != null)
            {
              Constant.println(thread.isAlive());  
            }
           thread = null;
        }
        /**
         * ��ҹ���Audio
         * @param ���
         * @return Audio
         * @author kingland
         * @date 28/01/2549
         */        
        public static Audio  getAudio(File file)
        {
            Audio au = new Audio(file);
            return au;            
        }
        /**
         * ��ҹ���Audio
         * @param string �������ͧ���
         * @return Audio
         * @author kingland
         * @date 28/01/2549
         */        
        public static Audio getAudio(String filepath)
        {
           Audio au = new Audio(filepath);
            return au;
        }
 
        public void run()
        {
            try
            {
                din = AudioSystem.getAudioInputStream(decodedFormat, in);
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
                line = (SourceDataLine) AudioSystem.getLine(info);
                if(line != null) 
                {
                        line.open(decodedFormat);
                        byte[] data = new byte[10240];
                        // Start
                        line.start();				
                        int nBytesRead;
                        while ((nBytesRead = din.read(data, 0, data.length)) != -1)
                        {	
                                line.write(data, 0, nBytesRead);
                        }
                        // Stop
//                        line.drain();
//                        line.stop();
//                        line.close();
//                        din.close();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace(Constant.getPrintStream());
            }
        }        
}

