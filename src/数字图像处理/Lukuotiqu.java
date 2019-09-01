package 数字图像处理;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Lukuotiqu extends huidubianhuan{
	static int[] blue;
	static int n1;
	static int n2, n3,n4, n5, n6, n7,n8;
    static int grey;
	public static BufferedImage getImg(BufferedImage image) throws IOException, InterruptedException {
		grayImage(image);
		erzhihua();
		blue = new int[width * height];
		BufferedImage greyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				blue[i * width + j] = (pixels4[i * width + j] & 0xff);
			}

		}
		for (int i = 1; i < height-1; i++) {
			for (int j = 1; j < width-1; j++) {

				if (blue[i * width + j] == 0) {
					greyImage.setRGB(j, i, 0x000000);
					n1 = blue[(i + 1) * width + j];
					n2 = blue[(i + 1) * width + j + 1];
					n3 = blue[i * width + j - 1];
					n4 = blue[i * width + j + 1];
					n5 = blue[(i + 1) * width + j - 1];
					n6 = blue[(i + 1) * width + j];
					n7 = blue[(i - 1) * width + j - 1];
					n8 = blue[(i - 1) * width + j + 1];
					if (n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 == 0) {
						greyImage.setRGB(j, i, 0xffffff);
					}
				}else {
					greyImage.setRGB(j, i, 0xffffff);
				}
			}

		}

		return greyImage;

	}
	
	public static BufferedImage tezheng(BufferedImage image) throws IOException, InterruptedException  { 
		grayImage(image);
		 BufferedImage grayImage=new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);//无符号 byte 灰度级图像  
	       
		int i, j, t, thresh, newthresh, gmax, gmin; // 最大,最小灰度值
		double a1, a2, max, pt;
		double[] p = new double[256];
		long[] num = new long[256];

		int[][] im = new int[width][height];

		for (j = 0; j < height; j++)
			for (i = 0; i < width; i++)
				im[i][j] = pixels2[i + j * width] & 0xff;

		for (i = 0; i < 256; i++)
			p[i] = 0;

		// 1.统计各灰度级出现的次数、灰度最大和最小值
		gmax = 0;
		gmin = 255;
		for (j = 0; j < height; j++) {
			for (i = 0; i < width; i++) {
				int g = im[i][j];
				p[g]++;
				if (g > gmax)
					gmax = g;
				if (g < gmin)
					gmin = g;
			}
		}

		thresh = 0;
		newthresh = (gmax + gmin) / 2;

		int meangray1, meangray2;
		long p1, p2, s1, s2;
		for (i = 0; (thresh != newthresh) && (i < 100); i++) {
			thresh = newthresh;
			p1 = 0;
			p2 = 0;
			s1 = 0;
			s2 = 0;

			// 2. 求两个区域的灰度平均值
			for (j = gmin; j < thresh; j++) {
				p1 += p[j] * j;
				s1 += p[j];
			}
			meangray1 = (int) (p1 / s1);

			for (j = thresh + 1; j < gmax; j++) {
				p2 += p[j] * j;
				s2 += p[j];
			}
			meangray2 = (int) (p2 / s2);
			// 3. 计算新阈值
			newthresh = (meangray1 + meangray2) / 2;
		}
		for (int k = 0; k < height; k++) {
			for (int l= 0; l < width; l++) {
		        t =pixels2[i + j * width] & 0xff;  
		                              
		        if(t >newthresh)   {
		            grey= (255<<24)|(255<<16)|(255<<8)|255;//背景色  
		            grayImage.setRGB(l, k, grey); 
		        }
		        else  
		        	grey = (255<<24)|(0<<16)|(0<<8)|0;  
		        grayImage.setRGB(l, k, grey); 
		        //前景色为         
		    } 
		}
		    return grayImage;  
	}  
}
