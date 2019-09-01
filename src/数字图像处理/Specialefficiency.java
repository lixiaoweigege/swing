package 数字图像处理;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


public class Specialefficiency {

//旋转
public BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
    int width = bufferedimage.getWidth();
    int height = bufferedimage.getHeight();
    int type = bufferedimage.getColorModel().getTransparency();
    BufferedImage img;
    Graphics2D graphics2d;
    (graphics2d = (img = new BufferedImage(width,height , type))
            .createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics2d.rotate(Math.toRadians(degree), width / 2,height  / 2);
    graphics2d.drawImage(bufferedimage, 0, 0, null);
    graphics2d.dispose();
    return img;
}
//镜像
public BufferedImage ImageMirror( BufferedImage image ) {
     int width = image.getWidth();
        int height = image.getHeight();
          for (int j = 0; j < height; j++) {
                int l = 0, r = width - 1;
                while (l < r) {
                    int pl = image.getRGB(l, j);
                    int pr = image.getRGB(r, j);
                    image.setRGB(l, j, pr);
                    image.setRGB(r, j, pl);

                    l++;
                    r--;
                }
            }
		return image;
    }
//水平镜像
public BufferedImage shuipingjingxiang(BufferedImage image) {
    int width=image.getWidth();
    int height=image.getHeight();
    
    for(int i=0;i<width;i++) {
    int j=0;int k=height-1;
     while(j<k) {
    	int pj=image.getRGB(i, j);
    	int pk=image.getRGB(i, k);
    	image.setRGB(i, j,  pk);
    	image.setRGB(i, k,  pj);
    	j++;
    	k--;
    	}	
    } 
	return image;
}

/** 

 * 对图片进行放大 

 * @param originalImage 原始图片 

 * @param times 放大倍数 

 * @return 

 */  

public BufferedImage  zoomInImage(BufferedImage  originalImage, Integer times){  

    int width = originalImage.getWidth()*times;  

    int height = originalImage.getHeight()*times;  

    BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());  

    Graphics2D g = (Graphics2D) newImage.getGraphics(); 

    g.drawImage(originalImage, 0,0,width,height,null);  

    g.dispose();  

    return newImage;  

}  

}


