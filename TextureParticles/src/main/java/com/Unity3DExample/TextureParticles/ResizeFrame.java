package com.Unity3DExample.TextureParticles;

import javax.swing.JFrame;

public class ResizeFrame extends JFrame {
	
	public ResizeFrame() 
	{
		setBounds(150,150,820,650);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
        add(new ResizePanel());
	}
}
