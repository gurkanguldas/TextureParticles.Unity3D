package com.Unity3DExample.TextureParticles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BaseFrame extends JFrame implements ActionListener{
	public static JPanel TopPanel , 
						 BottomPanel, 
						 RightPanel,
						 CenterPanel;
	
	public static ResizePanel  resizePanel ;

	public static JLabel[] labelMap;
	public static JLabel imageLabel = new JLabel();
	public static JLabel mom = new BottomPanel();
	
	public static JButton ImportProgram,
						  Delete,
						  DivideByFrames,
						  Export,
						  Browse,
						  ResizeImage;
	
	public static JTextField Start, 
							 Finish, 
							 BrowseText;
	
	public static JTextArea LOG;
	
	public static Image img;
	
	public static ImageIcon newImage;
	
	public static File[] matchingFiles;
	
	public static JProgressBar Loading;
	
	public static String log="";
	public static String Path = "";
	
	private JFileChooser jfc;
	
	public BaseFrame()
	{
		Path = System.getProperty("user.dir")+"\\";

		setBounds(50,50,800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("UnityTexture in JAVA v1.0");
		
		CenterPanel = new JPanel();
		TopPanel    = new JPanel();
		BottomPanel = new JPanel();
		RightPanel  = new JPanel();

		img = new ImageIcon(getClass().getResource("delete.png")).getImage();		
		
		CenterPanel.addComponentListener(CenterPanelComponentListener());
		CenterPanel.add(imageLabel,BorderLayout.CENTER);

		Start =  new JTextField("00:00:00");
		Finish = new JTextField("00:00:00");
		RightPanel.setPreferredSize(new Dimension(215,0));
		Start.setBounds(0,30,200,25);
		Finish.setBounds(0,90,200,25);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setPreferredSize(new Dimension(215,700));
		
		JLabel StartLabel = new JLabel();
		JLabel FinishLabel = new JLabel();
		
		StartLabel.setText("Start Time [hh:mm:ss]: ");
		FinishLabel.setText("Finish Time [hh:mm:ss]: ");
		
		StartLabel.setBounds(0,0,200,25);
		FinishLabel.setBounds(0,60,200,25);
		
		DivideByFrames = new JButton();
		DivideByFrames.setText("Divide By Frames");
		DivideByFrames.setBounds(0,120,200,50);
		DivideByFrames.addActionListener(this);
		
		ImportProgram = new JButton();
		ImportProgram.setText("Import");
		ImportProgram.setBounds(0,175,200,50);
		ImportProgram.addActionListener(this);
	
		Delete = new JButton();
		Delete.setText("Delete");
		Delete.setBounds(0,230,200,50);
		Delete.addActionListener(this);
		
		Export = new JButton();
		Export.setText("Export");
		Export.setBounds(0,285,200,50);
		Export.addActionListener(this);
		
		LOG = new JTextArea();
		LOG.setEditable(false);
		JScrollPane LOGScroll = new JScrollPane(LOG, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		LOGScroll.setBounds(0,340,200,300);
		
		ResizeImage = new JButton();
		ResizeImage.setText("ResizeImage");
		ResizeImage.setBounds(0,645,200,50);
		ResizeImage.addActionListener(this);
		
		rightPanel.add(StartLabel);
		rightPanel.add(Start);
		rightPanel.add(FinishLabel);
		rightPanel.add(Finish);
		rightPanel.add(DivideByFrames);
		rightPanel.add(ImportProgram);
		rightPanel.add(Delete);
		rightPanel.add(Export);
		rightPanel.add(LOGScroll);
		rightPanel.add(ResizeImage);
		
		JScrollPane RightScrool = new JScrollPane(rightPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		RightPanel.setLayout(new GridLayout(1,1));
		RightPanel.add(RightScrool);
		
		BottomPanel.setPreferredSize(new Dimension(0,220));
		
		TopPanel.setPreferredSize(new Dimension(0,30));
		TopPanel.setLayout(null);
		
		BrowseText = new JTextField();
		BrowseText.setPreferredSize(new Dimension(400,25));
		
		Browse = new JButton("BROWSE");
		Browse.setPreferredSize(new Dimension(200,25));
		Browse.addActionListener(this);
		
		Loading = new JProgressBar();
		Loading.setStringPainted(true);
		
		TopPanel.addComponentListener(TopPanelComponentListener());
		TopPanel.add(Loading);
		TopPanel.add(BrowseText);
		TopPanel.add(Browse);
		
		add(CenterPanel , BorderLayout.CENTER);
		add(TopPanel    , BorderLayout.NORTH);
		add(BottomPanel , BorderLayout.SOUTH);
		add(RightPanel  , BorderLayout.EAST);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ImportProgram) 
		{
			
			File f = new File(Path+"Frames");
			matchingFiles = f.listFiles(new FilenameFilter() 
			{
			    public boolean accept(File dir, String name)
			    {
			        return name.endsWith("png") || name.endsWith("jpg") || name.endsWith("jpeg") || name.endsWith("tiff");
			    }
			});
			
			labelMap = new JLabel[matchingFiles.length];
			
			int x = 0,
				ImageID=0;
			
			for (int i=0 ; i<labelMap.length ; i++) 
			{
				labelMap[i] = new BottomLabel(ImageID);ImageID++;
				labelMap[i].setBounds(4+x, 3, 300, 200);x+=305;
				
				Image BottomImage = new ImageIcon(matchingFiles[i].toString()).getImage();
				
				newImage = new ImageIcon(BottomImage.getScaledInstance(300, 190, Image.SCALE_DEFAULT));
				labelMap[i].setIcon(newImage);
				mom.add(labelMap[i]);
			}
				
			Loading.setValue(100);
					
			mom.setPreferredSize(new Dimension(305*labelMap.length,200));
			JScrollPane IconScroll = new JScrollPane(mom, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
														 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			BottomPanel.setLayout(new GridLayout(1,1));
					
			BottomPanel.add(IconScroll);
			BottomPanel.validate();
			BottomPanel.repaint();
			ImportProgram.setEnabled(false);
			log += ("\nFrames Imported\n\n");
			LOG.setText(log);
		}
		if(e.getSource()==Delete) 
		{
			matchingFiles[BottomLabel.GlobalID].delete();
			labelMap[BottomLabel.GlobalID].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("delete.png")).getImage().getScaledInstance(300, 190, Image.SCALE_DEFAULT)));
			BottomPanel.validate();
			BottomPanel.repaint();
			log += (matchingFiles[BottomLabel.GlobalID].toString()+" Deleted\n");
		    LOG.setText(log);
		}
		if(e.getSource()==DivideByFrames) 
		{
			TexturesVideo.Frames();
		}
		
		if(e.getSource()==Browse) 
		{
			jfc = new JFileChooser();
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) 
			{
				File selectedFile = jfc.getSelectedFile();
				BrowseText.setText(selectedFile.getAbsolutePath()); 
			}
		}
		
		if(e.getSource() == Export) 
		{
			TexturesVideo.setTexture();
			
			for (File image : matchingFiles) 
				image.delete();
			
			for (JLabel label : labelMap) 
				label.removeAll();
			
			mom.removeAll();
			
			BottomPanel.removeAll();
			BottomPanel.validate();
			BottomPanel.repaint();
			
			img = new ImageIcon(Path+"Output.png").getImage();
			CenterRefresh();
			ImportProgram.setEnabled(true);
			
			log += ("\nOutput.png Exported\n\n");
		    LOG.setText(log);
		}
		
		if(e.getSource() == ResizeImage) 
		{
			new ResizeFrame();
		}
		
	}
	public static void CenterRefresh() 
	{
			
		int width,height; 
		if(CenterPanel.getWidth()>=CenterPanel.getHeight()) 
		{
			height = CenterPanel.getHeight();
			width = (CenterPanel.getHeight()-img.getHeight(null))*img.getWidth(null)/img.getHeight(null)+img.getWidth(null);
		}
			
		else 
		{
			width = CenterPanel.getWidth();
			height = (CenterPanel.getWidth()-img.getWidth(null))*img.getHeight(null)/img.getWidth(null)+img.getHeight(null);
		}

		newImage = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		imageLabel.setIcon(newImage);
		CenterPanel.add(imageLabel,BorderLayout.CENTER);
	}
	
	private ComponentListener CenterPanelComponentListener() {
		// TODO Auto-generated method stub
		return new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e) {
				int width,height; 
				if(CenterPanel.getWidth()>=CenterPanel.getHeight()) {
					
					height = CenterPanel.getHeight();
					width = (CenterPanel.getHeight()-img.getHeight(null))*img.getWidth(null)/img.getHeight(null)+img.getWidth(null);
				}
				else {

					width = CenterPanel.getWidth();
					height = (CenterPanel.getWidth()-img.getWidth(null))*img.getHeight(null)/img.getWidth(null)+img.getHeight(null);
				}

				newImage = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
				imageLabel.setIcon(newImage);
				
            }
		};	
	}
	private ComponentListener TopPanelComponentListener() {
		// TODO Auto-generated method stub
		return new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Loading.setBounds(0,2,TopPanel.getWidth()/2,27);
				BrowseText.setBounds(TopPanel.getWidth()/2, 2, TopPanel.getWidth()/3, 28);
				Browse.setBounds(5*TopPanel.getWidth()/6, 2, TopPanel.getWidth()/6, 27);
            }
		};
	}
}
