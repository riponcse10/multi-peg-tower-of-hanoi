/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mptoh;

import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.*;

/**
 *
 * @author SNIPPER
 */
public class MPTOH {
    
    static int x,y;
    
    public static void main(String[] args) throws InterruptedException {
        
        

        String disk = JOptionPane.showInputDialog ( "How Many Disks"+'\n'+"(Must be between 3 to 50)"); 
        final int DISK= Integer.parseInt (disk);
        String peg = JOptionPane.showInputDialog ( "How Many Pegs"+'\n'+"(Must be between 3 to 15)" ); 
        final int PEG= Integer.parseInt (peg);
        if((PEG>15)||(DISK>50)||(PEG<3)||(DISK<3))
        {
            JOptionPane.showMessageDialog(null,"PEG can not be >15 and disk can not be >50 ");
            System.exit(1);
        }
        
        JFrame frame=new JFrame("MP TOWER OF HANOY");
        JFrame.setDefaultLookAndFeelDecorated(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        x= ((int) tk.getScreenSize().getWidth());
        y= ((int) tk.getScreenSize().getHeight());

        frame.setSize(x,y);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /*
         * JMenuBar menuBar = new JMenuBar();


        JMenu fileMenu = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("CREDIT..");
        aboutItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        ////////////////////////////////////////////////////
                        JOptionPane.showMessageDialog(null,"By Tanzir \n kjq");
                        

                    }

                }
        );
        fileMenu.add(aboutItem);
        
        
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);*/
        DrawGuiMP d=new DrawGuiMP(PEG,DISK,frame);
        
        frame.add(d);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        
    }
}
