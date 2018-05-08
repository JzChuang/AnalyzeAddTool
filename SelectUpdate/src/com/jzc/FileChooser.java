/*
 * FileChooser.java
 *
 * Created on __DATE__, __TIME__
 */

package com.jzc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FileChooser extends JFrame implements ActionListener{  
    public static void main(String[] args) {  
        new FileChooser();  
    }  
    public FileChooser(){  
    	this.setTitle("������������");
    	this.getContentPane().setLayout(null);
    	JLabel	label1 = new JLabel("ԭ·����");
    	final JTextField text1 =new JTextField();
    	text1.setEditable(false);
    	JButton button1 = new JButton("ѡ��");
        label1.setBounds(50, 20, 70, 30);
        text1.setBounds(150, 20, 300, 30);
        button1.setBounds(500, 20, 60, 30);
        
        JLabel	label2 = new JLabel("��·����");
    	final JTextField text2 =new JTextField();
    	text2.setEditable(false);
    	JButton button2 = new JButton("ѡ��");
        label2.setBounds(50, 70, 70, 30);
        text2.setBounds(150, 70, 300, 30);
        button2.setBounds(500, 70, 60, 30);
        
        JLabel label3 = new JLabel("����·����");
    	final JTextField text3 =new JTextField();
    	text3.setEditable(false);
    	JButton button3 = new JButton("ѡ��");
        label3.setBounds(50, 120, 70, 30);
        text3.setBounds(150, 120, 300, 30);
        button3.setBounds(500, 120, 60, 30);
        
        JButton button4 = new JButton("��ʼ����");
        button4.setBounds(270, 170, 100, 30);
        final JTextArea textarea = new JTextArea();
        JScrollPane sp = new JScrollPane(textarea);
        sp.setBounds(20, 230, 600, 200);
        
        JLabel label4 = new JLabel();
        label4.setText("ʹ�ù��������������⼰���飬����ϵ����������825941503@qq.com��");
        label4.setBounds(50,450,500,30);
        this.getContentPane().add(sp);
        this.add(label1);
        this.add(text1);
        this.add(button1);
        this.add(label2);
        this.add(text2);
        this.add(button2);
        this.add(label3);
        this.add(text3);
        this.add(button3);
        this.add(button4);
        this.add(label4);
        //this.add(textarea); 
        this.setSize(650, 580);
        
      //�����¼�������  
        ActionListener action1=new ActionListener(){  
             //�������ķ���  
            public void actionPerformed(ActionEvent e){  
                 //�߼�����  
            	JFileChooser jfc=new JFileChooser();  
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );  
                jfc.showDialog(new JLabel(), "ѡ��");  
                File file=jfc.getSelectedFile();  
                if(file.isDirectory()){  
                    System.out.println("�ļ���:"+file.getAbsolutePath());  
                }else if(file.isFile()){  
                    System.out.println("�ļ�:"+file.getAbsolutePath());  
                }  
                System.out.println(jfc.getSelectedFile().getName()); 
                text1.setText(file.getAbsolutePath());
            }  
        };  
        ActionListener action2=new ActionListener(){  
            //�������ķ���  
           public void actionPerformed(ActionEvent e){  
                //�߼�����  
           	JFileChooser jfc=new JFileChooser();  
               jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );  
               jfc.showDialog(new JLabel(), "ѡ��");  
               File file=jfc.getSelectedFile();  
               if(file.isDirectory()){  
                   System.out.println("�ļ���:"+file.getAbsolutePath());  
               }else if(file.isFile()){  
                   System.out.println("�ļ�:"+file.getAbsolutePath());  
               }  
               System.out.println(jfc.getSelectedFile().getName()); 
               text2.setText(file.getAbsolutePath());
           }  
       };  
       ActionListener action3=new ActionListener(){  
           //�������ķ���  
          public void actionPerformed(ActionEvent e){  
               //�߼�����  
          	JFileChooser jfc=new JFileChooser();  
              jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );  
              jfc.showDialog(new JLabel(), "ѡ��");  
              File file=jfc.getSelectedFile();  
              if(file.isDirectory()){  
                  System.out.println("�ļ���:"+file.getAbsolutePath());  
              }else if(file.isFile()){  
                  System.out.println("�ļ�:"+file.getAbsolutePath());  
              }  
              System.out.println(jfc.getSelectedFile().getName()); 
              text3.setText(file.getAbsolutePath());
          }  
      };  
      
      ActionListener action4=new ActionListener(){  
          //�������ķ���  
         public void actionPerformed(ActionEvent e){  
              //�߼�����  
         	Analyze.AnalyzeFiles(text1.getText(), text2.getText(), text3.getText(), textarea);
         }  
     };  
        button1.addActionListener(action1);
        button2.addActionListener(action2);
        button3.addActionListener(action3);
        button4.addActionListener(action4);
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
       // open.addActionListener(this);  
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}  
   
    

  
}  