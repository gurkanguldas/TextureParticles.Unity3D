package com.Unity3DExample.TextureParticles;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ResizePanel extends JPanel implements ActionListener,MouseListener{

	boolean cliked=false, 
			entered=false ;
	
	public static int x0, 
					  y0, 
					  wight, 
					  height;
	
	private int x,
				y;
	
	Timer animator;
	
	Image img;
	
	public ResizePanel()
	{
		super();
		addMouseListener(this);
		animator = new Timer(50, this);
		animator.start();
	}
	@Override
	protected void paintComponent(Graphics e) 
	{
		super.paintComponent(e);
		img =new ImageIcon(BaseFrame.newImage.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT)).getImage();
		e.drawImage(img, 0, 0, null);
		
		if(entered&&cliked) 
		{
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			wight = (int) b.getX()-x-x0;
			height = (int) b.getY()-y-y0;
		}
		e.drawRect(x0, y0, wight, height);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(cliked==false) 
		{
			x0 = e.getX();
			y0 = e.getY();
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			x= (int) b.getX()-x0;
			y= (int) b.getY()-y0;
			cliked=true;
		}
		else 
		{
			cliked = false;
			animator.stop();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		entered = true;
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		entered = false;	
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}
}
