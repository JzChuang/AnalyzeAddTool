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
        textarea.setText(textarea.getText()+getTime()+"===> 程序已启动"+"\r\n");
        textarea.setText(textarea.getText()+getTime()+"===> 正在遍历文件夹 " + newPath+"\r\n");
        // 遍历新文件目录
        Iterator iterator = FileUtils.iterateFiles(new File(newPath), null, true);
        // 遍历旧文件目录
        Iterator iterator1 = FileUtils.iterateFiles(new File(oldPath), null, true);
        // 用于接收被删除的目录
        List<String> deleteFiles = new ArrayList<String>();
        textarea.setText(textarea.getText()+getTime()+"===> 遍历完成，开始执行分析"+"\r\n");
        long startTime = System.currentTimeMillis();  //获取开始时间
        try {

            // 遍历比较新旧目录
            // 1. 如果文件不存在，则说明是新增的文件，复制该文件到输出路径
            // 2. 如果MD5值不一样，则文件被修改，复制该文件到输出路径
            while (iterator.hasNext()) {
                // 新文件路径
                String nPathStr = iterator.next().toString();
                File newFile = new File(nPathStr);
                // 旧文件路径
                File oldFile = new File(nPathStr.replace(newPath, oldPath));
                //System.out.println("===> 正在分析 " + nPathStr);

                // 判断文件是否存在
                if (!oldFile.exists()) {
                    File outFile = new File(nPathStr.replace(newPath, outPath));
                    FileUtils.copyFile(newFile, outFile);
                    textarea.setText(textarea.getText()+getTime()+"===> 已找到新增文件 "+"\r\n");
                    textarea.setText(textarea.getText()+getTime()+"======> 复制文件 " + outFile.toString()+"\r\n");
                    textarea.setText(textarea.getText()+getTime()+"Done."+"\r\n");
                    textarea.setText(textarea.getText()+"\r\n");
                } else {
                    // MD5校验
                    // 如果不相同，则copy到输出路径
                    String newMD5 = CheckMD5.getMD5(newFile);
                    String oldMD5 = CheckMD5.getMD5(oldFile);
                    if (!StringUtils.equals( newMD5, oldMD5 )) {
                        File outFile = new File(nPathStr.replace(newPath, outPath));
                        FileUtils.copyFile(newFile, outFile);
                        textarea.setText(textarea.getText()+getTime()+"===> 已找到修改文件 "+"\r\n");
                        textarea.setText(textarea.getText()+getTime()+"======> 新文件 " + newFile + " (MD5: " + newMD5 + " )"+"\r\n");
                        textarea.setText(textarea.getText()+getTime()+"======> 旧文件 " + oldFile + " (MD5: " + oldMD5 + " )"+"\r\n");
                        textarea.setText(textarea.getText()+getTime()+"======> 复制文件 " + outFile.toString()+"\r\n");
                        textarea.setText(textarea.getText()+getTime()+"Done."+"\r\n");
                        //System.out.println();
                    }
                }
            }

            // 遍历旧的文件目录
            // 主要是用于查找被删除的文件
            textarea.setText(textarea.getText()+getTime()+"===> 已找到删除文件 "+"\r\n");
            while (iterator1.hasNext()) {
                // 旧文件路径
                String oPathStr = iterator1.next().toString();
                // 新文件路径
                File newFile = new File(oPathStr.replace(oldPath, newPath));
                if (!newFile.exists()) {
                    deleteFiles.add(oPathStr);
                    textarea.setText(textarea.getText()+getTime()+"======> 文件路径 " + oPathStr+"\r\n");
                }
            }


        } catch (Exception e) {
        	e.printStackTrace();
        	textarea.setText(textarea.getText()+getTime()+"发生异常!");
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        textarea.setText(textarea.getText()+"\r\n");
        textarea.setText(textarea.getText()+getTime()+"分析完成 耗时：" + ((endTime-startTime) / 1000) + "s"+"\r\n");
        textarea.setText(textarea.getText()+getTime()+"输出路径：" + outPath+"\r\n");
        textarea.setText(textarea.getText()+"*******************************************************"+"\r\n");
        String logtext = textarea.getText();
        textarea.setText(textarea.getText()+"详细信息请查看日志文件（d:\\AnalyzeAddLog\\log.txt）"+"\r\n");
        writeLog(logtext);

    }
    /***
     * 生成日志文件
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
