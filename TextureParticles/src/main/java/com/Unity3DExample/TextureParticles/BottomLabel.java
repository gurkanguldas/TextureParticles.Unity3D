package com.Unity3DExample.TextureParticles;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BottomLabel extends JLabel implements MouseListener {
	
public static Boolean draw = false,
			          enter= false;
private int ID ;

public static int GlobalID;
	BottomLabel(int IDs){
		addMouseListener(this);
		ID = IDs;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		if(enter==false) 
		{
			draw=true;
			GlobalID=ID;
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		if(enter==false)
			draw=false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		enter=true;
		draw=true;
		GlobalID=ID;

		BaseFrame.img = new ImageIcon(BaseFrame.matchingFiles[BottomLabel.GlobalID].toString()).getImage();
		BaseFrame.CenterRefresh();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		
	}

}
