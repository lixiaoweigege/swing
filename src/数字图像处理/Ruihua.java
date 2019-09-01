package 数字图像处理;

import java.awt.image.BufferedImage;

public class Ruihua extends huidubianhuan{
	int grey;
	int[] blue;
	public BufferedImage Tidu(BufferedImage image) throws Exception{
		grayImage(image);
		BufferedImage greyImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		blue = new int[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				blue[i * width + j] = (pixels3[i * width + j] & 0xff);
				
			}
			
			}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				if(i==0||j==0||i==height-1||j==width-1) {
					greyImage.setRGB(j, i,0xffffffff);
				}
				else {
					
					// 中心的九个像素点
					grey = (int)Math.sqrt(((blue[i * width + j])  -  (blue[i * width + j - 1]))
							* ((blue[i * width + j])   -  (blue[i * width + j - 1]))
							+ (( blue[i * width + j]) - (blue[(i - 1) * width + j]))
									* ((blue[i * width + j]) - (blue[(i - 1)*width+j])));
					
					if (grey >= 30) {
						if ((grey + 100) > 255) {
							greyImage.setRGB(j, i, 0xffffffff);
						} else {
							grey = grey + 100;
							pixels2[i * width + j] = 255 << 24 | grey << 16 | grey << 8 | grey;
							greyImage.setRGB(j, i, pixels2[i * width + j]);
						}
					}
					else {
						pixels2[i * width + j]= 255 << 24 | blue[i * width + j] << 16 | blue[i * width + j] << 8 |blue[i * width + j];
						greyImage.setRGB(j, i,pixels2[i * width + j] );
					}

				}
			}
		}
		

		return greyImage;
	}
	//拉普拉斯锐化
	public BufferedImage laplaceProcess(BufferedImage src) {

        // 拉普拉斯算子
        int[] LAPLACE = new int[] { 0, -1, 0, -1, 4, -1, 0, -1, 0 };

        int width = src.getWidth();
        int height = src.getHeight();

        int[] pixels = new int[width * height];
        int[] outPixels = new int[width * height];

        int type = src.getType();
        if (type == BufferedImage.TYPE_INT_ARGB
                || type == BufferedImage.TYPE_INT_RGB) {
            src.getRaster().getDataElements(0, 0, width, height, pixels);
        }
        src.getRGB(0, 0, width, height, pixels, 0, width);

        int k0 = 0, k1 = 0, k2 = 0;
        int k3 = 0, k4 = 0, k5 = 0;
        int k6 = 0, k7 = 0, k8 = 0;

        k0 = LAPLACE[0];
        k1 = LAPLACE[1];
        k2 = LAPLACE[2];
        k3 = LAPLACE[3];
        k4 = LAPLACE[4];
        k5 = LAPLACE[5];
        k6 = LAPLACE[6];
        k7 = LAPLACE[7];
        k8 = LAPLACE[8];
        int offset = 0;

        int sr = 0, sg = 0, sb = 0;
        int r = 0, g = 0, b = 0;
        for (int row = 1; row < height - 1; row++) {
            offset = row * width;
            for (int col = 1; col < width - 1; col++) {
                // red
                sr = k0 * ((pixels[offset - width + col - 1] >> 16) & 0xff)
                        + k1 * ((pixels[offset - width + col] >> 16) & 0xff)
                        + k2
                        * ((pixels[offset - width + col + 1] >> 16) & 0xff)
                        + k3 * ((pixels[offset + col - 1] >> 16) & 0xff) + k4
                        * ((pixels[offset + col] >> 16) & 0xff) + k5
                        * ((pixels[offset + col + 1] >> 16) & 0xff) + k6
                        * ((pixels[offset + width + col - 1] >> 16) & 0xff)
                        + k7 * ((pixels[offset + width + col] >> 16) & 0xff)
                        + k8
                        * ((pixels[offset + width + col + 1] >> 16) & 0xff);
                // green
                sg = k0 * ((pixels[offset - width + col - 1] >> 8) & 0xff) + k1
                        * ((pixels[offset - width + col] >> 8) & 0xff) + k2
                        * ((pixels[offset - width + col + 1] >> 8) & 0xff) + k3
                        * ((pixels[offset + col - 1] >> 8) & 0xff) + k4
                        * ((pixels[offset + col] >> 8) & 0xff) + k5
                        * ((pixels[offset + col + 1] >> 8) & 0xff) + k6
                        * ((pixels[offset + width + col - 1] >> 8) & 0xff) + k7
                        * ((pixels[offset + width + col] >> 8) & 0xff) + k8
                        * ((pixels[offset + width + col + 1] >> 8) & 0xff);
                // blue
                sb = k0 * (pixels[offset - width + col - 1] & 0xff) + k1
                        * (pixels[offset - width + col] & 0xff) + k2
                        * (pixels[offset - width + col + 1] & 0xff) + k3
                        * (pixels[offset + col - 1] & 0xff) + k4
                        * (pixels[offset + col] & 0xff) + k5
                        * (pixels[offset + col + 1] & 0xff) + k6
                        * (pixels[offset + width + col - 1] & 0xff) + k7
                        * (pixels[offset + width + col] & 0xff) + k8
                        * (pixels[offset + width + col + 1] & 0xff);
                r = sr;
                g = sg;
                b = sb;
                outPixels[offset + col] = (0xff << 24) | r<< 16|g<< 8 | b;
                sr = 0;
                sg = 0;
                sb = 0;
            }
        }

        BufferedImage dest = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);

        if (type == BufferedImage.TYPE_INT_ARGB
                || type == BufferedImage.TYPE_INT_RGB) {
            dest.getRaster().setDataElements(0, 0, width, height, outPixels);
        } else {
            dest.setRGB(0, 0, width, height, outPixels, 0, width);
        }

        return dest;
    }
	
	/** 拉普拉斯叠加原图像 **/
    public BufferedImage laplaceAddProcess(BufferedImage src) {

        // 拉普拉斯算子
        int[] LAPLACE = new int[] { 0, -1, 0, -1, 4, -1, 0, -1, 0 };

        int width = src.getWidth();
        int height = src.getHeight();

        int[] pixels = new int[width * height];
        int[] outPixels = new int[width * height];

        int type = src.getType();
        if (type == BufferedImage.TYPE_INT_ARGB
                || type == BufferedImage.TYPE_INT_RGB) {
            src.getRaster().getDataElements(0, 0, width, height, pixels);
        }
        src.getRGB(0, 0, width, height, pixels, 0, width);

        int k0 = 0, k1 = 0, k2 = 0;
        int k3 = 0, k4 = 0, k5 = 0;
        int k6 = 0, k7 = 0, k8 = 0;

        k0 = LAPLACE[0];
        k1 = LAPLACE[1];
        k2 = LAPLACE[2];
        k3 = LAPLACE[3];
        k4 = LAPLACE[4];
        k5 = LAPLACE[5];
        k6 = LAPLACE[6];
        k7 = LAPLACE[7];
        k8 = LAPLACE[8];
        int offset = 0;

        int sr = 0, sg = 0, sb = 0;
        int r = 0, g = 0, b = 0;
        for (int row = 1; row < height - 1; row++) {
            offset = row * width;
            for (int col = 1; col < width - 1; col++) {

                r = (pixels[offset + col] >> 16) & 0xff;
                g = (pixels[offset + col] >> 8) & 0xff;
                b = (pixels[offset + col]) & 0xff;
                // red
                sr = k0 * ((pixels[offset - width + col - 1] >> 16) & 0xff)
                        + k1 * ((pixels[offset - width + col] >> 16) & 0xff)
                        + k2
                        * ((pixels[offset - width + col + 1] >> 16) & 0xff)
                        + k3 * ((pixels[offset + col - 1] >> 16) & 0xff) + k4
                        * ((pixels[offset + col] >> 16) & 0xff) + k5
                        * ((pixels[offset + col + 1] >> 16) & 0xff) + k6
                        * ((pixels[offset + width + col - 1] >> 16) & 0xff)
                        + k7 * ((pixels[offset + width + col] >> 16) & 0xff)
                        + k8
                        * ((pixels[offset + width + col + 1] >> 16) & 0xff);
                // green
                sg = k0 * ((pixels[offset - width + col - 1] >> 8) & 0xff) + k1
                        * ((pixels[offset - width + col] >> 8) & 0xff) + k2
                        * ((pixels[offset - width + col + 1] >> 8) & 0xff) + k3
                        * ((pixels[offset + col - 1] >> 8) & 0xff) + k4
                        * ((pixels[offset + col] >> 8) & 0xff) + k5
                        * ((pixels[offset + col + 1] >> 8) & 0xff) + k6
                        * ((pixels[offset + width + col - 1] >> 8) & 0xff) + k7
                        * ((pixels[offset + width + col] >> 8) & 0xff) + k8
                        * ((pixels[offset + width + col + 1] >> 8) & 0xff);
                // blue
                sb = k0 * (pixels[offset - width + col - 1] & 0xff) + k1
                        * (pixels[offset - width + col] & 0xff) + k2
                        * (pixels[offset - width + col + 1] & 0xff) + k3
                        * (pixels[offset + col - 1] & 0xff) + k4
                        * (pixels[offset + col] & 0xff) + k5
                        * (pixels[offset + col + 1] & 0xff) + k6
                        * (pixels[offset + width + col - 1] & 0xff) + k7
                        * (pixels[offset + width + col] & 0xff) + k8
                        * (pixels[offset + width + col + 1] & 0xff);
                // 运算后的像素值和原图像素叠加
                r += sr;
                g += sg;
                b += sb;
                outPixels[offset + col] = (0xff << 24) | (r << 16)| (g<< 8) |         b;

                // next pixel
                r = 0;
                g = 0;
                b = 0;
            }
        }

        BufferedImage dest = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);

        if (type == BufferedImage.TYPE_INT_ARGB
                || type == BufferedImage.TYPE_INT_RGB) {
            dest.getRaster().setDataElements(0, 0, width, height, outPixels);
        } else {
            dest.setRGB(0, 0, width, height, outPixels, 0, width);
        }
        return dest;
    }
}
