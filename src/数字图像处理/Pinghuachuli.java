package 数字图像处理;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.IOException;

public class Pinghuachuli extends huidubianhuan{
	int[]pixels5;
	int[]red;
	int[]green;
	int[]blue;
	int average1;
	int average2;
	int average3;
public BufferedImage filter(BufferedImage image) throws Exception{
	grayImage(image);
	erzhihua();
	
		BufferedImage greyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
					greyImage.setRGB(j, i, 0xffffffff);
				} else {
					int average = 0;
					// 中心的8个像素点
					average = (int) (((pixels4[i * width + j - 1] & 0xff) + (pixels4[i * width + j + 1] & 0xff)
							+ (pixels4[(i - 1) * width + j] & 0xff) + (pixels4[(i - 1) * width + j - 1] & 0xff)
							+ (pixels4[(i - 1) * width + j + 1] & 0xff) + (pixels4[(i + 1) * width + j] & 0xff)
							+ (pixels4[(i + 1) * width + j - 1] & 0xff) + (pixels4[(i + 1) * width + j + 1] & 0xff))
							/ 8);

					if (average == 0) {

						greyImage.setRGB(j, i, 0xff000000);
					} else {
						greyImage.setRGB(j, i, 0xffffffff);
					}
				}
			}
		}

		tmp = greyImage;

		return greyImage;
	}

	

	public BufferedImage filter2(BufferedImage image) throws InterruptedException, IOException {
		grayImage(image);
		BufferedImage greyImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);  	      
		red = new int[width * height];
		green = new int[width * height];
	    blue = new int[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				red[i * width + j] = (pixels3[i * width + j] & 0xff0000) >> 16;
				green[i * width + j] = (pixels3[i * width + j] & 0xff00) >> 8;
				blue[i * width + j] = (pixels3[i * width + j] & 0xff);
			}

		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
					greyImage.setRGB(j, i, 0xffffffff);
				} else {
					
					average1 = (int) ((blue[i * width + j] + (blue[i * width + j + 1])
							+ (blue[(i - 1) * width + j]) + (blue[(i - 1) * width + j - 1])
							+ (blue[(i - 1) * width + j + 1]) + (blue[(i + 1) * width + j])
							+ (blue[(i + 1) * width + j - 1]) + (blue[(i + 1) * width + j + 1])) / 8);
					int grey=255<<24|average1<<16|average1<<8|average1;
					greyImage.setRGB(j, i, grey);
				}
			}
		}

		return greyImage;

	}

}
