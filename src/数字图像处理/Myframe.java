package 数字图像处理;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import org.opencv.core.CvType;
import org.opencv.core.Mat;


public class Myframe extends JFrame{
	 File imageFile;
	 int[] pixels;
  /**
	 * 
	 */
	 BufferedImage src;
	private static final long serialVersionUID = 1L;
/**
 * @throws IOException 
	 * 
	 */

public Myframe() throws IOException  {
	  window();
  }
  public void window() throws IOException {
	 
	  setSize(850,500);//设置大小
	  setLocation(100, 100);//设置位置
	  setLayout(null);
	  setVisible(true);//设置可见性
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置关闭方式
	  setTitle("黎小伟哥哥");
	  JMenuBar menuBar = new JMenuBar(); //初始化一个菜单栏
	 add( menuBar);
	//初始化菜单
      JMenu menu1 = new JMenu("文件");
      JMenu menu2 = new JMenu("几何变换");
      JMenu menu3 = new JMenu("灰度变换");
      JMenu menu4 = new JMenu("平滑处理");
      JMenu menu5 = new JMenu("锐化处理");
      JMenu menu6 = new JMenu("图像分割");
      JMenu menu7 = new JMenu("形态处理");
      JMenu menu8 = new JMenu("彩色图处理");
      JMenu menu9 = new JMenu("图像识别");
      
      //添加子菜单
      JMenuItem item11=new JMenuItem("打开");
      JMenuItem item12=new JMenuItem("保存");
      
      
      JMenuItem item21=new JMenuItem("旋转");
      JMenuItem item22=new JMenuItem("垂直镜像");
      JMenuItem item23=new JMenuItem("缩小");
      JMenuItem item24=new JMenuItem("水平镜像");
    
      JMenuItem item31=new JMenuItem("灰度化");
      JMenuItem item32=new JMenuItem("直方图");
      JMenuItem item33=new JMenuItem("线性图");
      JMenuItem item34=new JMenuItem("拉伸图");
      JMenuItem item35=new JMenuItem("二值化");
      
      JMenuItem item41=new JMenuItem("噪声消除");    
      JMenuItem item43=new JMenuItem("灰度平滑");
      
      JMenuItem item51=new JMenuItem("梯度锐化");
      JMenuItem item52=new JMenuItem("拉普拉斯锐化");
      JMenuItem item53=new JMenuItem("拉普拉斯叠加");
      
      JMenuItem item61=new JMenuItem("轮廓提取");
      JMenuItem item62=new JMenuItem("特征提取");
      
      JMenuItem item71=new JMenuItem("水平腐蚀");
      JMenuItem item72=new JMenuItem("垂直腐蚀");
      JMenuItem item73=new JMenuItem("全面腐蚀");
      JMenuItem item74=new JMenuItem("全面膨胀");
      
      JMenuItem item81=new JMenuItem("HSI处理");
      JMenuItem item82=new JMenuItem("平滑处理");
      
      JMenuItem item91=new JMenuItem("人脸识别");
      JMenuItem item92=new JMenuItem("眼睛识别");
      
      //初始化一个panel
      Panel panel1 = new Panel();
      Panel panel2 = new Panel();
   
      //把panel添加到容器
      JSplitPane splitPane=new JSplitPane();//创建一个分割容器类
      splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//设置分割线方向 纵向分布
      splitPane.setLeftComponent(panel1);//设置左边的组件
      splitPane.setRightComponent(panel2);//设置右边的组件
      splitPane.setOneTouchExpandable(true);//让分割线显示出箭头
      splitPane.setContinuousLayout(true);//操作箭头，重绘图形
      splitPane.setDividerSize(1);//设置分割线的大小
      splitPane.setDividerLocation(420);//设置分割线位于中央
      setContentPane(splitPane);
      
    //加载图像添加事件监听器
      item11.addActionListener(new ActionListener() {
  	
  	@Override
  	public void actionPerformed(ActionEvent e) {
  		// TODO 自动生成的方法存根
  		//创建文件选择器
  		JFileChooser chooser=new JFileChooser();
  		//显示文件选择窗口
  	    chooser.showDialog(null, "导入图片");
  	    //获得用户选择的图片
  		 imageFile=chooser.getSelectedFile();
  	
  		 try {
  			  src=ImageIO.read(imageFile);
  			
  		     panel1.showImage(src);
  			
  		} catch (IOException e1) {
  			// TODO 自动生成的 catch 块
  			e1.printStackTrace();
  		}
  	}
  });
      
      item21.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			Specialefficiency s21=new Specialefficiency();
			src=s21.rotateImage(src, 90);
			panel2.showImage(src);
		}
    	  
      });
      item22.addActionListener(new ActionListener() {

  		@Override
  		public void actionPerformed(ActionEvent arg0) {
  			// TODO 自动生成的方法存根
  			Specialefficiency s22=new Specialefficiency();
  			src=s22.ImageMirror(src);
  			panel2.showImage(src);
  		}
      	  
        });
      item23.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO 自动生成的方法存根
    			Specialefficiency s23=new Specialefficiency();
    			src=s23.zoomInImage(src,5);
    			panel2.showImage1(src);
    		}
        	  
          });
      item24.addActionListener(new ActionListener() {

  		@Override
  		public void actionPerformed(ActionEvent arg0) {
  			// TODO 自动生成的方法存根
  			Specialefficiency s24=new Specialefficiency();
  			src=s24.shuipingjingxiang(src);
  			panel2.showImage(src);
  		}
      	  
        });
      
      item31.addActionListener(new ActionListener() {

  		@Override
  		public void actionPerformed(ActionEvent arg0) {
  			// TODO 自动生成的方法存根
  			huidubianhuan s31=new huidubianhuan();
  			try {
				src=s31.grayImage(src);
			} catch (InterruptedException | IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
  			
  		}
      	  });
      item32.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO 自动生成的方法存根
    			huidubianhuan s32=new huidubianhuan();
    			try {
  				src=s32.zhifangtu();
  			} catch (InterruptedException | IOException e) {
  				// TODO 自动生成的 catch 块
  				e.printStackTrace();
  			}
  			panel2.showImage(src);
    			
    		}
        	  });
      item33.addActionListener(new ActionListener() {

  		@Override
  		public void actionPerformed(ActionEvent arg0) {
  			// TODO 自动生成的方法存根
  			huidubianhuan s33=new huidubianhuan();
  			try {
				src=s33.linearConversion();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
  			
  		}
      	  });
      item34.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO 自动生成的方法存根
    			huidubianhuan s34=new huidubianhuan();
    			try {
  				src=s34.huidulashen();
  			} catch (IOException e) {
  				// TODO 自动生成的 catch 块
  				e.printStackTrace();
  			}
  			panel2.showImage(src);
    			
    		}
        	  });
      item35.addActionListener(new ActionListener() {

  		@Override
  		public void actionPerformed(ActionEvent arg0) {
  			// TODO 自动生成的方法存根
  			huidubianhuan s35=new huidubianhuan();
  			try {
				src=s35.erzhihua();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
  			
  		}
      	  });
      item41.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO 自动生成的方法存根
    			Pinghuachuli s41=new Pinghuachuli();
    			try {
  				src=s41.filter(src);
  			} catch (Exception e) {
  				// TODO 自动生成的 catch 块
  				e.printStackTrace();
  			}
  			panel2.showImage(src);
    			
    		}
        	  });
      
      item43.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO 自动生成的方法存根
    			Pinghuachuli s43=new Pinghuachuli();
    			try {
  				src=s43.filter2(src);
  			} catch (Exception e) {
  				// TODO 自动生成的 catch 块
  				e.printStackTrace();
  			}
  			panel2.showImage(src);
    			
    		}
        	  });
      item51.addActionListener(new ActionListener() {

  		@Override
  		public void actionPerformed(ActionEvent arg0) {
  			// TODO 自动生成的方法存根
  			Ruihua s51=new Ruihua();
  			try {
				src=s51.Tidu(src);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
  			
  		}
      	  });
      item52.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO 自动生成的方法存根
    			Ruihua s52=new Ruihua();
    			try {
  				src=s52.laplaceProcess(src);
  			} catch (Exception e) {
  				// TODO 自动生成的 catch 块
  				e.printStackTrace();
  			}
  			panel2.showImage(src);
    			
    		}
        	  });
      item53.addActionListener(new ActionListener() {

  		@Override
  		public void actionPerformed(ActionEvent arg0) {
  			// TODO 自动生成的方法存根
  			Ruihua s53=new Ruihua();
  			try {
				src=s53.laplaceAddProcess(src);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
  			
  		}
      	  });
      item61.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO 自动生成的方法存根
    			Lukuotiqu s61=new Lukuotiqu();
    			try {
  				src=s61.getImg(src);
  			} catch (Exception e) {
  				// TODO 自动生成的 catch 块
  				e.printStackTrace();
  			}
  			panel2.showImage(src);
    			
    		}
        	  });
      item62.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO 自动生成的方法存根
    			Lukuotiqu s62=new Lukuotiqu();
    			try {
  				src=s62.tezheng(src);
  			} catch (Exception e) {
  				// TODO 自动生成的 catch 块
  				e.printStackTrace();
  			}
  			panel2.showImage(src);
    			
    		}
        	  });
    item71.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			Xingtai s71=new Xingtai();
			try {
				src=s71.Shuipingfushi(src);
			} catch (IOException | InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
		}
	});
 item72.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			Xingtai s72=new Xingtai();
			try {
				src=s72.Chuizhifushi(src);
			} catch (IOException | InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
		}
	});
 item73.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			Xingtai s73=new Xingtai();
			try {
				src=s73.Quanfangwei(src);
			} catch (IOException | InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
		}
	});
 item74.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			Xingtai s74=new Xingtai();
			try {
				src=s74.Quanfangweipengzhang(src);
			} catch (IOException | InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} 
			panel2.showImage(src);
		}
	});
 item81.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			Caisechuli s81=new Caisechuli();
			try {
				src=s81.HSI(src);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
		}
	});
 item82.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			Caisechuli s82=new Caisechuli();
			try {
				src=s82.filter1(src);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			panel2.showImage(src);
			
		}
   	  });
 item91.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			FaceDetect s91=new FaceDetect();
			try {	
				
				s91.renlianshibie("./Resources/"+imageFile.getName());//("C:\\Users\\23132\\Desktop\\图片\\"+imageFile.getName());
				
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}						
		}
	  });
 item92.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			FaceDetect s92=new FaceDetect();
			try {	
				
				s92.eyeselect("./Resources/"+imageFile.getName());//("C:\\Users\\23132\\Desktop\\图片\\"+imageFile.getName());
				
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}						
		}
	  });
 
      menu1.add(item11);
      menu1.add(item12);
      
      menu2.add(item21);
      menu2.add(item22);
      menu2.add(item23);
      menu2.add(item24);
      
      menu3.add(item31);
      menu3.add(item32);
      menu3.add(item33);
      menu3.add(item34);
      menu3.add(item35);
      
      menu4.add(item41);     
      menu4.add(item43);
      
      menu5.add(item51);
      menu5.add(item52);
      menu5.add(item53);
      
      menu6.add(item61);
      menu6.add(item62);
      
      menu7.add(item71);
      menu7.add(item72);
      menu7.add(item73);
      menu7.add(item74);
      
      menu8.add(item81);
      menu8.add(item82);
      
      menu9.add(item91);
      menu9.add(item92);
      
    //把菜单添加到菜单栏
      menuBar.add(menu1);
      menuBar.add(menu2);
      menuBar.add(menu3);
      menuBar.add(menu4);
      menuBar.add(menu5);
      menuBar.add(menu6);
      menuBar.add(menu7);
      menuBar.add(menu8);
      menuBar.add(menu9);
      
      setJMenuBar(menuBar);//设置菜单栏
   
  }
	 
	 
	  
  }

