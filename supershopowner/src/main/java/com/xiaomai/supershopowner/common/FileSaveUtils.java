package com.xiaomai.supershopowner.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;

public class FileSaveUtils {

	public static void upload(String uploadFile, String root, String... directory) {
		File oraginalFile = new File(uploadFile);

		String targetDir = Paths.get(root, directory).toString();
		String taggetFilePath = targetDir + "/" + oraginalFile.getName();

		try {
			IOUtils.copy(new FileInputStream(oraginalFile), new FileOutputStream(taggetFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
