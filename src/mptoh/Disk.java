/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mptoh;

/**
 *
 * @author SNIPPER
 */
public class Disk {
    
        int x,y,wide,height,id,peg,middle;
        boolean on_move;
        
        public Disk(int x,int y,int width,int height,int id,int peg,int midpoint)
            {
                this.x=x;
                this.y=y;
                this.id=id;
                this.wide=width;
                this.height=height;
                this.peg=peg;
                this.middle=midpoint;
                
            }
    
}
