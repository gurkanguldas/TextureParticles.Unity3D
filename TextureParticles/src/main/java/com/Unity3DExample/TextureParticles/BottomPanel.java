package com.Unity3DExample.TextureParticles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class BottomPanel extends JLabel implements ActionListener {
	Timer animator;
	
	public BottomPanel()
	{
		super();
		animator = new Timer(100, this);
		animator.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.blue);
		g2.setStroke(new BasicStroke(4f));
		if(BottomLabel.draw) 
		{
			g2.drawLine(1 + (305 * BottomLabel.GlobalID), 5, 307 + (305 * BottomLabel.GlobalID), 5);
			g2.drawLine(307 + (305 * BottomLabel.GlobalID), 5, 307 + (305 * BottomLabel.GlobalID), 200);
			g2.drawLine(307 + (305 * BottomLabel.GlobalID), 200, 1 + (305 * BottomLabel.GlobalID), 200);
			g2.drawLine(1 + (305 * BottomLabel.GlobalID), 200, 1 + (305 * BottomLabel.GlobalID), 5);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		repaint();
	}

}
