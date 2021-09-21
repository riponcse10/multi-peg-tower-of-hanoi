/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mptoh;

import java.util.Stack;

/**
 *
 * @author SNIPPER
 */
public class Peg 
{
    
        int x,y;
        int wide,height;
        int id;
        int topdisk_y;
        
        Stack<Integer> pegdisk=new Stack<Integer>();
        
        public Peg(int x,int y,int wide,int height,int id,int yyy)
        {
            this.x=x;
            this.y=y;
            this.wide=wide;
            this.height=height;
            this.id=id;
            this.topdisk_y=yyy;
            
        }
}
