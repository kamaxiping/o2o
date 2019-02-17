package com.lianxi.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lianxi.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 将CommonsMultipartFile转换成File
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * 处理缩略图，并返回新生成图片的相对路径
	 * 
	 */
	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is:" + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException("创建缩图片失败："+e.toString());
		}
		return relativeAddr;
	}
	//处理详情图，并返回新生成图片的相对路径
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is:" + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
			.outputQuality(0.9f).toFile(dest);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException("创建缩图片失败："+e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 输入获取文件流的扩展名
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取随机的五位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sdf.format(new Date());
		return nowTimeStr + rannum;
	}

	/**
	 * 创建目标路径所涉及到的目录，即E:/projectdev/image/xxx.jpg, 那么projectdev image
	 * 这两个文件夹都得自动创建
	 * 
	 * @return
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentpath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentpath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	/**
	 * storePath是文件的路径还是目录的路径
	 * 如果storepath是文件路径则删除该文件，如果storePath是目录路径则删除该目录下的所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath){
		File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
		if(fileOrPath.exists()){
			if(fileOrPath.isDirectory()){
				File files[]=fileOrPath.listFiles();
				for(int i=0;i<files.length;i++){
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
