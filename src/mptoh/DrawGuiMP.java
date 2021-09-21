/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mptoh;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author SNIPPER
 */
public class DrawGuiMP extends JPanel implements Runnable{
    
    int height,width;
    int peg,disk;
    Thread th=null;
    boolean isFirst;
    Timer timer;
    int xx,yy,speed;
    int count=0;
    
    int [][] table=new int[100][100];
    Peg pegarr[]=new Peg[16];
    Disk diskarr[]=new Disk[51];
    Random rand = new Random();
    float rr = rand.nextFloat();
    float gg = rand.nextFloat();
    float bb = rand.nextFloat();
    

    

    public DrawGuiMP(int PEG,int DISK,JFrame frame)
    {
        this.peg=PEG;
        this.disk=DISK;
        this.speed=1;
        Insets in=frame.getInsets();
        
        //height=frame.getHeight()-(in.top+in.bottom);
        height=frame.getHeight();
        width=frame.getWidth();
        //width=frame.getWidth()-(in.left+in.right);
        
        System.out.println(width+","+height);
        xx=0;
        yy=200;
        isFirst=false;
        
        int midpoint;
        int increment=width/peg;
        
        midpoint=width/(peg*2);
        int diskwidth=increment-20;
        int diskheight=(height-300)/disk;
        int y=height-55-diskheight;

        //g.setColor(new Color(100, 200, 115));
        
       for(int i=1;i<=peg;i++)
       {
           
           pegarr[i]=new Peg(midpoint,200,5,height-140,i,height-55-diskheight);
           midpoint+=increment;
           
       }
       midpoint=width/(peg*2);
        for(int i=disk;i>0;i--)
        {
            //g.fill3DRect(midpoint-diskwidth/2,y, diskwidth, diskheight,true);//disk
            diskarr[i]=new Disk(midpoint-(diskwidth/2)+5, y, diskwidth-5, diskheight, i,1, midpoint);
            pegarr[1].pegdisk.push(i);
            pegarr[1].topdisk_y-=diskheight;
           // System.out.println(diskarr[i].x+"-"+diskarr[i].y+"-"+diskarr[i].wide+"-"+diskarr[i].height+"-"+diskarr[i].id);
            //System.out.println(pegarr[1].pegdisk.peek());
            y=y-diskheight;
            diskwidth-=(diskwidth)/(disk);
        }
        
        
        ///////////////////////////////table/////////////////////////////////////////
        for(int i=0;i<100;i++){
        table[i][3]=1;
        }
        for(int i=3;i<100;i++){
        table[0][i]=1;
        }
        for(int i=1;i<100;i++)
        for(int j=4;j<100;j++){
        table[i][j]=table[i-1][j]+table[i][j-1];
        }
        
        th=new Thread(this);
        th.start();
        

    }
    
 

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int midpoint=width/(peg*2);
        int increment=width/peg;
           
        for(int i=1;i<=peg;i++)
        {

            g.fill3DRect(midpoint,160,5,height,false);//peg
            //pegarr[i]=new Peg(midpoint,100,5,height,i);
            //System.out.println(pegarr[i].x+"-"+pegarr[i].y+"-"+pegarr[i].wide+"-"+pegarr[i].height+"-"+pegarr[i].id);
            g.fill3DRect(midpoint-(increment/2)+10,height-55,increment-10,25,false);//bottom
            midpoint+=increment;

        }

       
        System.out.println("repaint()");
        //for(int i=disk;i>0;i--)
        {
        
            //g.setComposite(AlphaComposite.SrcOver.derive(0.8f));
            //g.setColor(new Color(100, 200, 115));
            // g.fill3DRect(xx,yy,150,300/disk,false);
           

            for(int i=1;i<=disk;i++)
            {

                g.setColor(new Color(rr,gg,bb));
                g.fill3DRect(diskarr[i].x,diskarr[i].y,diskarr[i].wide,diskarr[i].height,true); 
            }


        }
       

    }
    
    public void movedisk(int frompeg,int topeg) throws InterruptedException
    {
        int disknum=pegarr[frompeg].pegdisk.peek();
        pegarr[frompeg].pegdisk.pop();
        while(diskarr[disknum].y>0)
        {
              //xx++;
              //diskarr[disknum].y--;
              diskarr[disknum].y=diskarr[disknum].y-5;
              repaint();
              th.sleep(speed);
              
        }
        if(frompeg<topeg)
        {
            while(diskarr[disknum].x!=(pegarr[topeg].x-diskarr[disknum].wide/2))
            {
              diskarr[disknum].x++;  
              
              repaint();
              th.sleep(speed);
            }
        
        }
        else
        {
            while(diskarr[disknum].x!=(pegarr[topeg].x-diskarr[disknum].wide/2))
            {
              diskarr[disknum].x--;  
              repaint();
              th.sleep(speed);
            }
            
        }
        
        while(diskarr[disknum].y!=pegarr[topeg].topdisk_y)
        {
              diskarr[disknum].y++;  
              repaint();
              th.sleep(speed);
        }
        
        pegarr[topeg].pegdisk.push(disknum);
        pegarr[frompeg].topdisk_y+=diskarr[disknum].height;
        pegarr[topeg].topdisk_y-=diskarr[disknum].height;
        
        
    }
    
    
    //int count=0;
    
    public void toh(int n,int from,int to,int temp) throws InterruptedException
    {

            if(n>0)
            {
                    toh(n-1,from,temp,to);
                    //System.out.println("move disk "+n+" from "+from+" to "+to);
                    movedisk(from,to);
                    count++;
                    toh(n-1,temp,to,from);
            }
    }
    
    
    
    
    
    
    
    
    
    
    @Override
    public void run()
    {
        try {
            Thread.sleep(3000);
            //toh(disk,1,3,2);
            
            MPTOH(disk,peg,1,peg,0);
            System.out.println(count);
            JOptionPane.showMessageDialog(this,"Total Move : "+count);
        } catch (InterruptedException ex) {
            Logger.getLogger(DrawGuiMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    ////////////////////////////////////MPTOH/////////////////////////////////////////
    
    

        
    int min(int x,int y)
    {
        return x<y?x:y;
    }

    int function(int disk,int peg)
    {
        if(peg==3) return disk-1;
        if(peg>disk) return 1;
        int kmax,Na,l1,l2,u1,u2;
        kmax=0;
        Na=0;
 
        for(int i=0;i<100;i++)
            {
            if(table[i][peg+1]>=disk)
                {
                kmax=i;
                Na=disk-table[i-1][peg+1];
                break;
                }
            }

        l1=table[kmax-2][peg+1];
        u1=table[kmax-2][peg+1]+min(Na,table[kmax-1][peg]);

        u2=disk-table[kmax-1][peg];
        l2=disk -(table[kmax-1][peg]+min(Na,table[kmax][peg-1]));

        return u1<u2?u1:u2;
        //return l1>l2?l1:l2;
    }
    

public void MPTOH(int disk,int peg,int source,int destination,int mask) throws InterruptedException
    {
    if(disk==1)
        {
        
        System.out.println(source+" to "+destination);
        count++;
        movedisk(source,destination);

        }
    else
        {
        int temp=1;
        int ans=0;
        int pi=0;
        int changedmask=0;
        for(int i=1;i<=32;i++)
            {
            if(source!=i && destination!=i)
                {
                    ans=mask&temp;
                    if(ans==0)
                    {
                        pi=i;
                        changedmask=mask|temp;
                        break;
                    }
                }
            temp<<=1;
            }
        int n1=function(disk,peg);


        MPTOH(n1,peg,source,pi,mask);
        MPTOH(disk-n1,peg-1,source,destination,changedmask);
        MPTOH(n1,peg,pi,destination,mask);

        }
    }
    
}
