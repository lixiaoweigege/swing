package Êý×ÖÍ¼Ïñ´¦Àí;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tuxiangshibie extends Pinghuachuli{
	public BufferedImage shibie(BufferedImage image) throws Exception {
		
		 grayImage(image);
		 erzhihua();
		 filter(image);
		 BufferedImage RGBImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		 for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int grey1=pixels4[i*width+j]&0xff;
					int grey=255<<24|grey1<<16|grey1<<8|grey1;
					RGBImage.setRGB(i, j,grey);
				}
				
				}
		 for (int i = 0; i < height; i++) {
			 int a=0;
				for (int j = 0; j < width; j++) {
					
					int grey1=pixels4[i*width+j]&0xff;
					if (grey1==0) {
						a+=a;
					}
				}
				if(a>20) {
					for (int k = 0; k < width; k++) {
					RGBImage.setRGB(i, k, 0xffffffff);
					}
				}
				}
	
		
		
		return RGBImage;
	}
	
}
