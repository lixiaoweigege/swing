package 数字图像处理;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.IOException;

public class huidubianhuan {
	 static Image tmp;  
	    static int width,height;//图像宽度，高度  
	    static double ma,mi;//线性变化灰度上下限  
	    static int[] pixels;//图像所有像素点  
	    static int[] pixels2;//备份pixels，用于灰度线性变化  
	    static int[] pixels3;//备份pixels，用于灰度拉伸  
	    static int[] pixels4;//备份pixels，用于平滑处理
	    static int[] histogram=new int[256];  
	   
	 //灰度化  
public static BufferedImage grayImage(BufferedImage image) throws IOException, InterruptedException  
	    { 
	        width=image.getWidth();  
	        height=image.getHeight();  
	        pixels=new int[width*height];  
	        pixels2=new int[width*height];  
	        pixels3=new int[width*height];  
	      
	          
	        BufferedImage grayImage=new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);//无符号 byte 灰度级图像  
	        for(int i=0;i<width;i++)  
	            for(int j=0;j<height;j++)  
	            {  
	                int rgb=image.getRGB(i, j);  
	                int grey=(int) ((0.3*((rgb&0xff0000)>>16)+0.59*((rgb&0xff00)>>8))+0.11*((rgb&0xff)));  
	                rgb=255<<24|grey<<16|grey<<8|grey;  
	                grayImage.setRGB(i, j, rgb);  
	            }//读入所有像素，转换图像信号,使其灰度化  
	        tmp=grayImage;  
	        PixelGrabber pg=new PixelGrabber(tmp, 0, 0, width, height, pixels,0,width);  
	        pg.grabPixels();//将该灰度化后的图片所有像素点读入pixels数组  
	        PixelGrabber pg2=new PixelGrabber(tmp, 0, 0, width, height, pixels2,0,width);  
	        pg2.grabPixels();  
	        PixelGrabber pg3=new PixelGrabber(tmp, 0, 0, width, height, pixels3,0,width);  
	        pg3.grabPixels();//  
	        return grayImage;
	    }  
	      
//直方图均衡  
public BufferedImage zhifangtu() throws InterruptedException, IOException  
	    {  
	        //PixelGrabber pg=new PixelGrabber(tmp, 0, 0, iwidth, iheight, pixels,0,iwidth);  
	        //pg.grabPixels();  
	        BufferedImage greyImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);  
	          
	        for(int i=0;i<height-1;i++)  
	            for(int j=0;j<width-1;j++)  
	            {  
	                int grey=pixels[i*width+j]&0xff;  
	                histogram[grey]++;  
	            }//计算每一个灰度级的像素数  
	        double a=(double)255/(width*height);  
	        double[] c=new double[256];  
	        c[0]=(a*histogram[0]);  
	        for(int i=1;i<256;i++)  
	            c[i]=c[i-1]+(int)(a*histogram[i]);//直方图均衡化(离散情况)  
	        for(int i=0;i<height;i++)  
	            for(int j=0;j<width;j++)  
	            {  
	                int grey=pixels[i*width+j]&0x0000ff;  
	                int hist=(int)c[grey];  
	                pixels[i*width+j]=255<<24|hist<<16|hist<<8|hist;  
	                greyImage.setRGB(j, i, pixels[i*width+j]);  
	            }  
	        tmp=greyImage;
			return greyImage;  
	        
	    }  
	      
//灰度线性变换  
public BufferedImage linearConversion() throws IOException  
	    {  
	        int min=255,max=0;  ma=100; mi=0;
	        for(int i=0;i<256;i++)  
	        {  
	            if(histogram[i]>0)  
	            {  
	                if(i<min)  
	                    min=i;  
	                if(i>max)  
	                    max=i;  
	            }  
	        }//找出灰度的最大级和最小级  
	        double k=(ma-mi)/(max-min);//计算变换比  
	        BufferedImage greyImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);  
	        for(int i=0;i<height;i++) 
	            for(int j=0;j<width;j++)  
	            {  
	                int grey=pixels2[i*width+j]&0xff;  
	                grey=(int)(k*(grey-min)+mi);  
	                if(grey>255)  
	                    grey=255;  
	                if(grey<0)  
	                    grey=0;  
	                pixels2[i*width+j]=255<<24|grey<<16|grey<<8|grey;  
	                greyImage.setRGB(j, i, pixels2[i*width+j]);  
	            }
	        //灰度线性变换  
	        tmp=greyImage;
			return greyImage;
	    }  
	      
 //灰度拉伸  
public BufferedImage huidulashen() throws IOException  
	    {  
	        int min = 0,max = 1;   ma=255; mi=0;
	        int sum=0;  
	        for(int i=0;i<256;i++)  
	        {  
	            sum+=histogram[i];  
	            if(sum>width*height*0.05)  
	            {  
	                min=i;  
	                break;  
	            }  
	        }//找出灰度的大部分像素范围的最小级  
	        sum=0;  
	        for(int i=255;i>=0;i--)  
	        {  
	            sum+=histogram[i];  
	            if(sum>width*height*0.05)  
	            {  
	                max=i;  
	                break;  
	            }  
	        }//找出灰度的大部分像素范围的最大级  
	        double k=(ma-mi)/(max-min);  
	        BufferedImage greyImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);  
	        for(int i=0;i<height;i++) { 
	            for(int j=0;j<width;j++)  
	            {  
	                int grey=pixels3[i*width+j]&0xff;  
	               
	                if(grey<min)  
	                    grey=(int) mi;//小于min部分设为下限  
	                else if(grey>=max)  
	                    grey=(int) ma;//大于max部分设为上限  
	                else   
	                {  
	                    grey=(int)(k*(grey-min)+mi);  
	                    if(grey>255)  
	                        grey=255;  
	                    if(grey<0)  
	                        grey=0;  
	                }//大部分区域线性变换  
	                  
	                pixels3[i*width+j]=255<<24|grey<<16|grey<<8|grey;  
	                
	                greyImage.setRGB(j, i, pixels3[i*width+j]);  
	            }
	        }//灰度拉伸  

	        tmp=greyImage;
			return greyImage;
	    }  
//二值化
public static BufferedImage erzhihua() throws IOException, InterruptedException  {  
	pixels4=new int[width*height];
  BufferedImage greyImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);  
    for(int i=0;i<height;i++) 
        for(int j=0;j<width;j++)  
        {  
            int grey=pixels2[i*width+j]&0xff;  
            
            if(grey>100)  
                grey=255; 
            else 
            	grey=0;
            pixels2[i*width+j]=255<<24|grey<<16|grey<<8|grey;  
            greyImage.setRGB(j, i, pixels2[i*width+j]);  
        }
    
    tmp=greyImage;

    PixelGrabber pg4=new PixelGrabber(tmp, 0, 0, width, height, pixels4,0,width);  
    pg4.grabPixels();//
    return greyImage;
}  


}

