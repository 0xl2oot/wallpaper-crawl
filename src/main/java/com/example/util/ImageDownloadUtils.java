package com.example.util;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

/**
 * 图片下载工具类
 *
 * @author 0xl2oot@gmail.com
 * @date 2018/10/3 7:54 PM
 */
public class ImageDownloadUtils{

     private static Logger logger = Logger.getLogger(ImageDownloadUtils.class);

     public static void download(String urlString) {


          URL url = null;

          URLConnection con = null;

          InputStream is = null;

          StringBuffer sb = null;

          String filename = null;

          boolean ok = false;

          try {

               // 构造 URL 对象
               url = new URL(urlString);

               // 建立连接
               con = url.openConnection();

               // 设置请求超时为 5s
               con.setConnectTimeout(5*1000);

               // 输入流
               is = con.getInputStream();

               // 构造文件名称
               sb = new StringBuffer();
               sb.append("/Users/WYH/Downloads/picdownload/");
               String pattern = "(.+/)(([^?.]*)(\\.(jpg|jpeg|png|gif)?)?)(.*)(\\?.*)*$";
               Pattern r = Pattern.compile(pattern);
               Matcher m = r.matcher(urlString);
               m.find();
               sb.append(m.group(3));
               BufferedImage image = ImageIO.read(is);
               sb.append("_");
               sb.append(image.getWidth());
               sb.append("x");
               sb.append(image.getHeight());
               if (m.group(4) != null) {
                    sb.append(m.group(4));
               } else {
                    sb.append(".jpg");
               }

               filename = sb.toString();

               // 建立文件
               File file = new File(filename);

               // 写入文件
               ok = ImageIO.write(image, "jpg", file);

          } catch (MalformedURLException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          } finally {
               try {
                    // 完毕，关闭所有链接
                    is.close();
                    if (ok) {
                         logger.info("【文件保存成功】" + filename);
                    } else {
                         logger.info("【文件保存失败】" + filename);
                    }

               } catch (IOException e) {
                    e.printStackTrace();
               }

          }
     }

     public static void main(String[] args) throws Exception {
          // 测试
          ImageDownloadUtils.download("http://seopic.699pic.com/photo/50035/0520.jpg_wh1200.jpg");
     }
}
