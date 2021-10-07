package com.Unity3DExample.TextureParticles;

/**
 * Hello world!
 *
 */
public class App 
{
	static {
		nu.pattern.OpenCV.loadShared();
	}
    public static void main( String[] args )
    {
    	System.out.println(BaseFrame.Path);
    	new BaseFrame();
    }
}
