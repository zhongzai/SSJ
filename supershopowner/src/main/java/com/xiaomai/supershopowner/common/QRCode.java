package com.xiaomai.supershopowner.common;


import java.io.Serializable;
import java.net.URL;

public class QRCode implements Serializable {

	private static final long serialVersionUID = -5209257286415377369L;
	/**
	 * 纠错级别
	 */
	private Object errorCorrectionLevel;
	/**
	 * 编码格式
	 */
	private String characterSet;
	/**
	 * 图片的宽度
	 */
	private int width;
	/**
	 * 图片的高度
	 */
	private int height;
	/**
	 * 二维码边缘留白
	 */
	private int margin;
	/**
	 * 二维码内容
	 */
	private String contents;
	/**
	 * 生成图片的目录地址（不包含图片名称）
	 */
	private String path;
	/**
	 * 二维码的文件名
	 */
	private String filename;
	/**
	 * 生成图片的格式
	 */
	private String format;
	/**
	 * 是否中间贴图
	 * 
	 * @return
	 */
	private boolean flag;
	/**
	 * logo图地址
	 */
	private URL logoPath;

	public Object getErrorCorrectionLevel() {
		return errorCorrectionLevel;
	}

	public void setErrorCorrectionLevel(Object errorCorrectionLevel) {
		this.errorCorrectionLevel = errorCorrectionLevel;
	}

	public String getCharacterSet() {
		return characterSet;
	}

	public void setCharacterSet(String characterSet) {
		this.characterSet = characterSet;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public URL getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(URL logoPath) {
		this.logoPath = logoPath;
	}
}
