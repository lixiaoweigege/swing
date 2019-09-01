package 数字图像处理;
	import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.Mat;
	import org.opencv.core.MatOfRect;
	import org.opencv.core.Point;
	import org.opencv.core.Rect;
	import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

	public class FaceDetect {
		
		public void run(String a){
			CascadeClassifier faceDetector = new CascadeClassifier("./Resources/lbpcascade_frontalface.xml");
			Mat image =Imgcodecs.imread(a);//Imgcodecs.imread("./Resources/222121.jpg");	//读取Resources文件中的图像
			MatOfRect faceDetections = new MatOfRect();
			faceDetector.detectMultiScale(image, faceDetections);

			System.out.println(String.format("识别出 %s 张脸",
					faceDetections.toArray().length));
			for (Rect rect : faceDetections.toArray()) {
				Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
						+ rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
			}

			String filename = "./Result/Face2.png";
			System.out.println(String.format("结果存入 %s", filename));
			Imgcodecs.imwrite(filename, image);	//进行图像的保存
		}
		/**  
	     * opencv实现人眼识别  
	     * @param imagePath  
	     * @param outFile  
	     * @throws Exception  
	     */  
	    public  void detectEye(String imagePath) throws Exception {  
	  
	  
	        CascadeClassifier eyeDetector = new CascadeClassifier(  
	                "./Resources/haarcascade_eye.xml");  
	  
	        Mat image = Imgcodecs.imread(imagePath);  //读取图片  
	  
	        // 在图片中检测人脸  
	        MatOfRect faceDetections = new MatOfRect();  
	  
	        eyeDetector.detectMultiScale(image, faceDetections, 2.0,1,1,new Size(20,20),new Size(20,20));  
	  
	        System.out.println(String.format("检测到 %s 双眼睛", faceDetections.toArray().length));  
	        Rect[] rects = faceDetections.toArray();  
	        if(rects != null && rects.length <2){  
	            throw new RuntimeException("不是一双眼睛");  
	        }  
	        
	  
	        
	  
	         
	  
	        for(Rect rect:faceDetections.toArray()) {  
	            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x  
	                    + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));  
	        }  
	        String filename = "./Result/Face3.png";
	        Imgcodecs.imwrite(filename, image);  
	  
	        System.out.println(String.format("人眼识别成功，结果存为： %s", filename));  
	  
	    }  
		public void renlianshibie(String a) {
			// TODO Auto-generated method stub
						System.loadLibrary("opencv_java341");
						new FaceDetect().run(a); 
		}
		public void eyeselect(String a) throws Exception {
			// TODO Auto-generated method stub
			System.loadLibrary("opencv_java341");
			new FaceDetect().detectEye(a); 
		}
	}

