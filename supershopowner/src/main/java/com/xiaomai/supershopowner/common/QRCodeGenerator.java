package com.xiaomai.supershopowner.common;


import java.io.IOException;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator {

	public static String generateQRCode(String url) {
		QRCode qr = new QRCode();

		qr.setErrorCorrectionLevel(ErrorCorrectionLevel.H);
		qr.setCharacterSet("UTF-8");
		qr.setWidth(300);
		qr.setHeight(300);
		qr.setMargin(0);
		qr.setContents(url);
		String path = SysConfig.concatPath(SysConfig.getResourceRoot(), "/uploadify/qrcode");
		qr.setPath(path);
		String filename = System.nanoTime() + ".png";
		qr.setFilename(filename);
		qr.setFormat("png");
		qr.setFlag(false); // 是否中间贴图
		qr.setLogoPath(null);

		try {
			QRCodeUtil.encode(qr);
		} catch (IOException e) {
			return null;
		}

		FileSaveUtils.upload(path + "/" + filename, SysConfig.getResourceRoot(), "/public", "/qrcode");

		return filename;
	}

	public static String generateQRCodeForEform(String url) {
		QRCode qr = new QRCode();

		qr.setErrorCorrectionLevel(ErrorCorrectionLevel.H);
		qr.setCharacterSet("UTF-8");
		qr.setWidth(300);
		qr.setHeight(300);
		qr.setMargin(0);
		qr.setContents(url);
		String path = SysConfig.concatPath(SysConfig.getResourceRoot(), "/uploadify/qrcode");
		qr.setPath(path);
		String filename = System.nanoTime() + ".png";
		qr.setFilename(filename);
		qr.setFormat("png");
		qr.setFlag(false); // 是否中间贴图
		qr.setLogoPath(null);

		try {
			QRCodeUtil.encode(qr);
		} catch (IOException e) {
			return null;
		}
		return filename;
	}
}
