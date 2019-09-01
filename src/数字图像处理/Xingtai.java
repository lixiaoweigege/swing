package Êý×ÖÍ¼Ïñ´¦Àí;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Xingtai extends huidubianhuan {
	int grey1;
	int grey2;
	int[] blue;

	public BufferedImage Shuipingfushi(BufferedImage image) throws IOException, InterruptedException {
		grayImage(image);
		erzhihua();
		BufferedImage greyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		blue = new int[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				blue[i * width + j] = (pixels4[i * width + j] & 0xff);

			}

		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (j == 0 || j == width) {
					greyImage.setRGB(j, i, 0xffffffff);
				} else {
					greyImage.setRGB(j, i, 0xff000000);
					if (blue[i * width + j - 1] > 128 || blue[i * width + j + 1] > 128) {
						greyImage.setRGB(j, i, 0xffffffff);
					}
				}
			}
		}
		return greyImage;

	}
	public BufferedImage Chuizhifushi(BufferedImage image) throws IOException, InterruptedException {
		grayImage(image);
		erzhihua();
		BufferedImage greyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		blue = new int[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				blue[i * width + j] = (pixels4[i * width + j] & 0xff);

			}

		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == height) {
					greyImage.setRGB(j, i, 0xffffffff);
				} else {
					greyImage.setRGB(j, i, 0xff000000);
					if (blue[(i-1) * width + j] > 128 || blue[(i+1)* width + j] > 128) {
						greyImage.setRGB(j, i, 0xffffffff);
					}
				}
			}
		}
		return greyImage;

	}
	public BufferedImage Quanfangwei(BufferedImage image) throws IOException, InterruptedException {
		grayImage(image);
		erzhihua();
		BufferedImage greyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		blue = new int[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				blue[i * width + j] = (pixels4[i * width + j] & 0xff);

			}

		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == height||j == 0 || j == width) {
					greyImage.setRGB(j, i, 0xffffffff);
				} else {
					greyImage.setRGB(j, i, 0xff000000);
					if (blue[(i-1) * width + j] > 128 || blue[(i+1)* width + j] > 128||blue[i * width + j - 1] > 128 || blue[i * width + j + 1] > 128) {
						greyImage.setRGB(j, i, 0xffffffff);
					}
				}
			}
		}
		return greyImage;

	}
	public BufferedImage Quanfangweipengzhang(BufferedImage image) throws IOException, InterruptedException {
		grayImage(image);
		erzhihua();
		BufferedImage greyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		blue = new int[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				blue[i * width + j] = (pixels4[i * width + j] & 0xff);

			}

		}
		for (int i = 1; i < height-1; i++) {
			for (int j = 1; j < width-1; j++) {
				
			
					greyImage.setRGB(j, i, 0xffffffff);
					if (blue[(i-1) * width + j] <128||blue[i*width+width+j]<128||blue[i*width+j+1]<128||blue[i*width+1+j]<128)  {
						greyImage.setRGB(j, i, 0xff000000);
					
				}
			}
		}
		return greyImage;

	}
}
