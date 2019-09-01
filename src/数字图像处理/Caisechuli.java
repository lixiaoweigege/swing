package 数字图像处理;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class Caisechuli {
	int width, height;
	int[] red;
	int[] green;
	int[] blue;
	int pixels5[];

	public BufferedImage HSI(BufferedImage image) throws InterruptedException {
		int[] I;
		int[] H;
		int[] S;
		int F = 0;
		int R = 0;
		int G = 0;
		int B = 0;
		width = image.getWidth();
		height = image.getHeight();
		pixels5 = new int[width * height];
		red = new int[width * height];
		green = new int[width * height];
		blue = new int[width * height];
		I = H = S = new int[width * height];
		BufferedImage RGBImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		PixelGrabber pg5 = new PixelGrabber(image, 0, 0, width, height, pixels5, 0, width);
		pg5.grabPixels();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				red[i * width + j] = (pixels5[i * width + j] >> 16) & 0xff;// rgb的红色分量
				green[i * width + j] = (pixels5[i * width + j] >> 8) & 0xff;
				blue[i * width + j] = (pixels5[i * width + j] & 0xff);
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				R = red[i * width + j];
				G = green[i * width + j];
				B = blue[i * width + j];
				int min1 = Math.min(R, G);
				int min2 = Math.min(min1, B);
				int a = G > B ? 0 : 180;
				if ((G - B) != 0) {
					F = (int) ((R - G - B) / (G - B));
				} else {
					F = 1;
				}
				I[i * width + j] = (R + G + B) / 3;
				H[i * width + j] = (int) ((90 - Math.atan(F / Math.sqrt(3))) + a) / 360;
				if ((R + G + B) != 0) {
					S[i * width + j] = 1 - min2 * 3 / (R + G + B);
				} else {
					S[i * width + j] = 1;
				}
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int H1 =H[i * width + j];
				int S1 = S[i * width + j];
				int I1 = I[i * width + j];
				R = red[i * width + j];
				G = green[i * width + j];
				B = blue[i * width + j];
				if (H1 < 120 &&H1 > 0) {
					R = (int) ((I1 / Math.sqrt(3))* (1 + S1 * Math.cos(H1) / Math.cos(60 -H1)));
					B = (int) ((I1 / Math.sqrt(3)) * (1 - S1));
					G = (int) (Math.sqrt(3) * I1 - R - B);
				} else if (H1 > 120 &&H1 < 240) {
					G = (int) ((I1 / Math.sqrt(3))* (1 + S1* Math.cos(H1-120) / Math.cos(180 -H1)));
					R = (int) ((I1 / Math.sqrt(3)) * (1 - S1));
					B = (int) (Math.sqrt(3) * I1 - R - G);
				} else {
					B= (int) ((I1 / Math.sqrt(3))* (1 + S1* Math.cos(H1-240) / Math.cos(300 -H1)));
					G = (int) ((I1 / Math.sqrt(3)) * (1 - S1));
					B = (int) (Math.sqrt(3) * I1 - B - G);
				}
				int rgb = 255 << 24 | R << 16 | G << 8 | B;
				RGBImage.setRGB(j, i, rgb);
			}
		}

		return RGBImage;
	}

	public BufferedImage filter1(BufferedImage image) throws InterruptedException {
		width = image.getWidth();
		height = image.getHeight();
		pixels5 = new int[width * height];
		red = new int[width * height];
		green = new int[width * height];
		blue = new int[width * height];
		PixelGrabber pg5 = new PixelGrabber(image, 0, 0, width, height, pixels5, 0, width);
		pg5.grabPixels();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				red[i * width + j] = (pixels5[i * width + j] >> 16) & 0xff;// rgb的红色分量
				green[i * width + j] = (pixels5[i * width + j] >> 8) & 0xff;
				blue[i * width + j] = (pixels5[i * width + j] & 0xff);
			}

		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
					image.setRGB(j, i, 0xffffffff);
				} else {

					// 中心的8个像素点
					int average1 = (int) ((blue[i * width + j] + (blue[i * width + j + 1]) + (blue[(i - 1) * width + j])
							+ (blue[(i - 1) * width + j - 1]) + (blue[(i - 1) * width + j + 1])
							+ (blue[(i + 1) * width + j]) + (blue[(i + 1) * width + j - 1])
							+ (blue[(i + 1) * width + j + 1])) / 8);
					int average2 = (int) (((green[i * width + j - 1]) + (green[i * width + j + 1])
							+ (green[(i - 1) * width + j]) + (green[(i - 1) * width + j - 1])
							+ (green[(i - 1) * width + j + 1]) + (green[(i + 1) * width + j])
							+ (green[(i + 1) * width + j - 1]) + (green[(i + 1) * width + j + 1])) / 8);
					int average3 = (int) (((red[i * width + j - 1]) + (red[i * width + j + 1])
							+ (red[(i - 1) * width + j]) + (red[(i - 1) * width + j - 1])
							+ (red[(i - 1) * width + j + 1]) + (red[(i + 1) * width + j])
							+ (red[(i + 1) * width + j - 1]) + (red[(i + 1) * width + j + 1])) / 8);

					int grey = 255 << 24 | average3 << 16 | average2 << 8 | average1;
					image.setRGB(j, i, grey);
				}
			}
		}

		return image;

	}
}
