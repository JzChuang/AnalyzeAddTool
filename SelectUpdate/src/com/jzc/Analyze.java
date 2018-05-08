package com.jzc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class Analyze {

  

    public static void AnalyzeFiles(String oldPath,String newPath,String outPath,JTextArea textarea) {

        textarea.setText("*******************************************************"+"\r\n");
        textarea.setText(textarea.getText()+getTime()+"===> ����������"+"\r\n");
        textarea.setText(textarea.getText()+getTime()+"===> ���ڱ����ļ��� " + newPath+"\r\n");
        // �������ļ�Ŀ¼
        Iterator iterator = FileUtils.iterateFiles(new File(newPath), null, true);
        // �������ļ�Ŀ¼
        Iterator iterator1 = FileUtils.iterateFiles(new File(oldPath), null, true);
        // ���ڽ��ձ�ɾ����Ŀ¼
        List<String> deleteFiles = new ArrayList<String>();
        textarea.setText(textarea.getText()+getTime()+"===> ������ɣ���ʼִ�з���"+"\r\n");
        long startTime = System.currentTimeMillis();  //��ȡ��ʼʱ��
        try {

            // �����Ƚ��¾�Ŀ¼
            // 1. ����ļ������ڣ���˵�����������ļ������Ƹ��ļ������·��
            // 2. ���MD5ֵ��һ�������ļ����޸ģ����Ƹ��ļ������·��
            while (iterator.hasNext()) {
                // ���ļ�·��
                String nPathStr = iterator.next().toString();
                File newFile = new File(nPathStr);
                // ���ļ�·��
                File oldFile = new File(nPathStr.replace(newPath, oldPath));
                //System.out.println("===> ���ڷ��� " + nPathStr);

                // �ж��ļ��Ƿ����
                if (!oldFile.exists()) {
                    File outFile = new File(nPathStr.replace(newPath, outPath));
                    FileUtils.copyFile(newFile, outFile);
                    textarea.setText(textarea.getText()+getTime()+"===> ���ҵ������ļ� "+"\r\n");
                    textarea.setText(textarea.getText()+getTime()+"======> �����ļ� " + outFile.toString()+"\r\n");
                    textarea.setText(textarea.getText()+getTime()+"Done."+"\r\n");
                    textarea.setText(textarea.getText()+"\r\n");
                } else {
                    // MD5У��
                    // �������ͬ����copy�����·��
                    String newMD5 = CheckMD5.getMD5(newFile);
                    String oldMD5 = CheckMD5.getMD5(oldFile);
                    if (!StringUtils.equals( newMD5, oldMD5 )) {
                        File outFile = new File(nPathStr.replace(newPath, outPath));
                        FileUtils.copyFile(newFile, outFile);
                        textarea.setText(textarea.getText()+getTime()+"===> ���ҵ��޸��ļ� "+"\r\n");
                        textarea.setText(textarea.getText()+getTime()+"======> ���ļ� " + newFile + " (MD5: " + newMD5 + " )"+"\r\n");
                        textarea.setText(textarea.getText()+getTime()+"======> ���ļ� " + oldFile + " (MD5: " + oldMD5 + " )"+"\r\n");
                        textarea.setText(textarea.getText()+getTime()+"======> �����ļ� " + outFile.toString()+"\r\n");
                        textarea.setText(textarea.getText()+getTime()+"Done."+"\r\n");
                        //System.out.println();
                    }
                }
            }

            // �����ɵ��ļ�Ŀ¼
            // ��Ҫ�����ڲ��ұ�ɾ�����ļ�
            textarea.setText(textarea.getText()+getTime()+"===> ���ҵ�ɾ���ļ� "+"\r\n");
            while (iterator1.hasNext()) {
                // ���ļ�·��
                String oPathStr = iterator1.next().toString();
                // ���ļ�·��
                File newFile = new File(oPathStr.replace(oldPath, newPath));
                if (!newFile.exists()) {
                    deleteFiles.add(oPathStr);
                    textarea.setText(textarea.getText()+getTime()+"======> �ļ�·�� " + oPathStr+"\r\n");
                }
            }


        } catch (Exception e) {
        	e.printStackTrace();
        	textarea.setText(textarea.getText()+getTime()+"�����쳣!");
        }
        long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
        textarea.setText(textarea.getText()+"\r\n");
        textarea.setText(textarea.getText()+getTime()+"������� ��ʱ��" + ((endTime-startTime) / 1000) + "s"+"\r\n");
        textarea.setText(textarea.getText()+getTime()+"���·����" + outPath+"\r\n");
        textarea.setText(textarea.getText()+"*******************************************************"+"\r\n");
        String logtext = textarea.getText();
        textarea.setText(textarea.getText()+"��ϸ��Ϣ��鿴��־�ļ���d:\\AnalyzeAddLog\\log.txt��"+"\r\n");
        writeLog(logtext);

    }
    /***
     * ������־�ļ�
     * @param logtext
     */
    public static void writeLog(String logtext){
    	try {
    		File path = new File("d:\\AnalyzeAddLog");
    		if(!path.exists()){
    			path.mkdirs();
    		}
    		File file = new File(path,"log.txt");
    		if(!file.exists()){
    			file.createNewFile();
    		}
			FileWriter fw = new FileWriter(file,true);
			/*FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);*/
			

			fw.write(logtext);
			
			fw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static String getTime(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = sdf.format(System.currentTimeMillis());
    	return time;
    }


}
