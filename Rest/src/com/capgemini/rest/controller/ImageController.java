package com.capgemini.rest.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController implements ServletContextAware {

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletcontext) {
		this.servletContext = servletcontext;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/image2", produces = "image/jpeg", headers = "Accept=image/jpeg")
	public @ResponseBody
	BufferedImage image() throws IOException {
		String realPath = servletContext.getRealPath("/");
		File file = new File(realPath + "WEB-INF/rsrc/capgemini.jpg");
		return ImageIO.read(file);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/image", headers = "Accept=image/jpeg")
	public @ResponseBody
	byte[] getImage() throws IOException {
		String realPath = servletContext.getRealPath("/");
		File file = new File(realPath + "WEB-INF/rsrc/capgemini.jpg");
		return IOUtils.toByteArray(new FileInputStream(file));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/image", headers = "Accept=text/plain")
	public @ResponseBody
	String upload(@RequestParam("file") MultipartFile file,
			@RequestParam("field") String value) {
		try {
			createUploadDirIfNotExists();
			FileOutputStream newFile = new FileOutputStream(
					getFileName(file.getOriginalFilename()));
			newFile.write(file.getBytes());
			newFile.close();
			return "OK " + value;
		} catch (Exception exc) {
			exc.printStackTrace();
			return "NOK";
		}
	}

	private void createUploadDirIfNotExists() {
		File uploadDir = new File(getUploadDirPath());
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}

	private String getFileName(String originalFileName) {
		return getUploadDirPath() + getUploadedFileNumber() + originalFileName;
	}

	private String getUploadedFileNumber() {
		File uploadDir = new File(getUploadDirPath());
		int number = uploadDir.list().length + 1;
		return String.valueOf(number);
	}

	private final String getUploadDirPath() {
		String realPath = servletContext.getRealPath("/");
		return realPath + "/WEB-INF/upload/";
	}
}
