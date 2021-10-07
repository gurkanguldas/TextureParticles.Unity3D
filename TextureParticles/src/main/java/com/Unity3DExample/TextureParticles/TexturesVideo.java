package com.Unity3DExample.TextureParticles;

import java.io.File;
import java.io.FilenameFilter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class TexturesVideo {
	public static void Frames() {
		
		VideoCapture cap = new VideoCapture();
		cap.open(BaseFrame.BrowseText.getText());

		int video_length = (int) cap.get(Videoio.CAP_PROP_FRAME_COUNT);
		int frames_per_second = (int) cap.get(Videoio.CAP_PROP_FPS);
		int frame_number = (int) cap.get(Videoio.CAP_PROP_POS_FRAMES);

		int video_height = (int) cap.get(Videoio.CAP_PROP_FRAME_HEIGHT);
		int video_width = (int) cap.get(Videoio.CAP_PROP_FRAME_WIDTH);

		BaseFrame.log += ("Video size: " + video_width + " x " + video_height + "\n");
		BaseFrame.LOG.setText(BaseFrame.log);

		Mat frame = new Mat();

		int startFrame = (
						  Integer.valueOf(BaseFrame.Start.getText().substring(0, 2)) * 3600 + 
						  Integer.valueOf(BaseFrame.Start.getText().substring(3, 5)) * 60 +
						  Integer.valueOf(BaseFrame.Start.getText().substring(6, 8))
						 ) * frames_per_second;
						
		
		int finishFrame = (
						   Integer.valueOf(BaseFrame.Finish.getText().substring(0, 2)) * 3600 +
						   Integer.valueOf(BaseFrame.Finish.getText().substring(3, 5)) * 60 +
						   Integer.valueOf(BaseFrame.Finish.getText().substring(6, 8))
						  ) * frames_per_second;
		
		int ImageID = 0;
		
		if (cap.isOpened()) 
		{
			BaseFrame.log += ("Video is opened\n");
			BaseFrame.log += ("Number of Frames: " + video_length + "\n");
			BaseFrame.log += (frames_per_second + " Frames per Second\n");
			BaseFrame.LOG.setText(BaseFrame.log);

			while (frame_number < video_length) 
			{
				cap.read(frame);
				if (frame_number >= startFrame && frame_number <= finishFrame) 
				{
					if (ImageID < 10)
						Imgcodecs.imwrite(BaseFrame.Path + "Frames" + "\\000" + ImageID + ".png", frame);
					else if (ImageID > 9 && ImageID < 100)
						Imgcodecs.imwrite(BaseFrame.Path + "Frames" + "\\00" + ImageID + ".png", frame);
					else if (ImageID > 99 && ImageID < 999)
						Imgcodecs.imwrite(BaseFrame.Path + "Frames" + "\\0" + ImageID + ".png", frame);
					else
						Imgcodecs.imwrite(BaseFrame.Path + "Frames" + "\\" + ImageID + ".png", frame);

					ImageID++;
				}
				if (frame_number == finishFrame)
					break;

				frame_number++;
			}
			cap.release();

			BaseFrame.log += ((finishFrame - startFrame) + " Frames extracted\n");
			BaseFrame.LOG.setText(BaseFrame.log);
		}
		else 
		{
			BaseFrame.log += ("Fail\n");
			BaseFrame.LOG.setText(BaseFrame.log);
		}
	}
	public static void setTexture()
	{
		File f = new File(BaseFrame.Path+"Frames");
		BaseFrame.matchingFiles = f.listFiles(new FilenameFilter() 
		{
			public boolean accept(File dir, String name) 
			{
				return name.endsWith("png") || name.endsWith("jpg") || name.endsWith("jpeg") || name.endsWith("tiff");
			}
		});
		 
		int grid = (int)Math.sqrt(BaseFrame.matchingFiles.length);
		if(Math.abs((grid + 1) * (grid + 1) - BaseFrame.matchingFiles.length) < 
		   Math.abs(grid * grid - BaseFrame.matchingFiles.length)) 
		{
			grid+=1;
		}
		 
		int a=0 , b=0 , st=1;
		int row=0 , col=0;
		int resize = 4096 / grid;
		 
		Mat Resim = Imgcodecs.imread(BaseFrame.matchingFiles[0].toString());
			
		int x0 = ResizePanel.x0 * Resim.cols() / 800;
		int y0 = ResizePanel.y0 * Resim.rows() / 600;
		int width = ResizePanel.wight * Resim.cols() / 800;
		int height = ResizePanel.height * Resim.rows()/600;
			
		Rect clip= new Rect(x0,y0,width,height);
		Resim = new Mat(Resim,clip);
			
		if(Resim.rows()>=Resim.cols()) 
		{
			row = resize; 
			col = resize * Resim.cols() / Resim.rows();
		}
		else 
		{
			col = resize; 
			row = resize * Resim.rows() / Resim.cols();
		}

		Mat Grid = new Mat();	
		Imgproc.resize( Resim, Resim, new Size(col, row));
		Grid.create(resize * grid, resize * grid, Resim.type());
			
		for(int x=0 ; x<grid ; x++) 
		{
			for(int y=0 ; y<grid ; y++) 
			{
				for(int i=0 ; i<resize ; i++) 
				{ 
					for(int j=0 ; j<resize ; j++) 
					{
						if(i<row && j<col)
							Grid.put(i + a, j + b, Resim.get(i, j));
						else
							Grid.put(i + a, j + b, new double[] {0,255,0});
					}
				}
			
				Resim = Imgcodecs.imread(BaseFrame.matchingFiles[st].toString());
				Resim = new Mat(Resim,clip);
				Imgproc.resize( Resim, Resim, new Size(col,row));
				b+=resize;
				if(st+1 < BaseFrame.matchingFiles.length)
					st++;
			}
			b = 0;
			a += resize;
		}
		
		Imgproc.resize( Grid, Grid,new Size(4096,4096) );
		Imgcodecs.imwrite(BaseFrame.Path+"Output.png", Grid);
		
		}
}
