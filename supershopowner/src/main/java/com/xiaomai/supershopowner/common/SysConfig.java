package com.xiaomai.supershopowner.common;


import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局配置，包括本地数据存储根目录、网络设置、默认数据库设置
 * <li>移除PropertyReader，使用{@link ConfigReader}代替</li>
 * <li>使用concatPath 代替 concat</li>
 * 
 * @author Anthony
 * @author chen.zhijun
 * 
 * 
 */
public class SysConfig {

	private static final Logger LOG = LoggerFactory.getLogger(SysConfig.class);

	@Resource
	ConfigReader configRead;

	private static final String OS = System.getProperty("os.name");

	/**
	 * get property from configure file
	 * 
	 * @param key
	 *            Properties key
	 * @param def
	 *            Properties default value,"" mean not default
	 * @return Properties
	 */
	public static String getProperty(String key, String def) {

		String value = ConfigReader.getProperty(key);

		if (StringUtils.isBlank(value)) {
			LOG.warn("not found property:{}", key);
		}

		if (StringUtils.isEmpty(value) || StringUtils.isBlank(value)) {
			LOG.warn("property is a empty value!", key);
		}
		return value;
	}

	/**
	 * get property with out default value.
	 * 
	 * @see com.linkingmed.imagecloud.core.SysConfig#getProperty(String, String)
	 * 
	 */
	public static String getProperty(String key) {
		return getProperty(key, StringUtils.EMPTY);
	}

	/**
	 * 递归创建路径
	 * 
	 * @param srcpath
	 */
	public static void mkdirs(String srcpath) {
		File f = new File(srcpath);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	/**
	 * 获取文件名，不带后缀
	 * 
	 * @param srcfile
	 * @return
	 */
	public static String getFilename(String srcfile) {
		int i = srcfile.lastIndexOf(Constants.FILE_SEPERATOR);
		int j = srcfile.lastIndexOf(Constants.MARK_DOT, i);
		if (j == -1) {
			return srcfile.substring(i + 1);
		}
		return srcfile.substring(i + 1, j);
	}

	/**
	 * 获取文件名，带后缀
	 * 
	 * @param srcfile
	 * @return
	 */
	public static String getFilenameEx(String srcfile) {
		int i = srcfile.lastIndexOf(Constants.FILE_SEPERATOR);
		return srcfile.substring(i + 1);
	}

	/**
	 * 获取文件名，带一个时间戳防止重名
	 * 
	 * @param srcfile
	 * @return
	 */
	public static String getFilenameTimestamp(String srcfile) {
		int i = srcfile.lastIndexOf(Constants.FILE_SEPERATOR);
		int j = srcfile.lastIndexOf(Constants.MARK_DOT, i);
		if (j == -1) {
			return srcfile.substring(i + 1) + System.currentTimeMillis();
		}
		return srcfile.substring(i + 1, j) + +System.currentTimeMillis() + srcfile.substring(j);
	}

	/**
	 * 拼接路径
	 * 
	 * @param dir
	 *            root
	 * @param subdir
	 *            sub
	 * @return path
	 * @deprecated use {@link #concatPath(String, String...)}
	 */
	public static String concat(String dir, String... subdir) {
		List<String> subdirs = Arrays.asList(subdir);
		StringBuffer sb = new StringBuffer();
		if (dir.endsWith(Constants.FILE_SEPERATOR))
			sb.append(dir.substring(0, dir.length() - Constants.FILE_SEPERATOR.length()));
		else
			sb.append(dir);
		for (int i = 0; i < subdirs.size(); i++) {
			if (subdirs.get(i).startsWith(Constants.FILE_SEPERATOR))
				sb.append(subdirs.get(i));
			else
				sb.append(Constants.FILE_SEPERATOR).append(subdirs.get(i));
		}
		return sb.toString();
	}

	/**
	 * 路径拼接方法
	 * 
	 * @author chen.zhijun
	 * @param dir
	 *            主目录
	 * @param subdirs
	 *            子目录
	 * @return 全路径
	 */
	public static String concatPath(String dir, String... subdir) {
		return Paths.get(dir, subdir).toFile().getAbsolutePath();
	}

	/**
	 * 获取静态资源存储的根目录，根据服务器平台
	 * 
	 * @author chen.zhijun
	 */
	public static String getResourceRoot() {

		String resourceRoot = null;
		if (OS.toLowerCase().startsWith("win")) {
			resourceRoot = getProperty("resource.root.windows", "");
		} else {
			resourceRoot = getProperty("resource.root.linux", "");
		}
		return resourceRoot;
	}

	/**
	 * 获取本机的IP地址
	 * 
	 * @author chen.zhijun
	 * @since 2016-05-27 11:46
	 */
	public static String getLocalHost() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取配置中的域名
	 * 
	 * @author chen.zhijun
	 */
	public static String getDomain() {
		return getProperty("web.domain", "");
	}

	/**
	 * 获取部署的路径
	 */
	public static String getDeployPath() {

		String classesPath = Thread.currentThread().getClass().getResource("/").getPath();

		LOG.debug("classesPath:{}", classesPath);

		File classesDir = new File(classesPath); // classes目录回退两次是部署目录 String
		String deployDir = classesDir.getParentFile().getParentFile().getAbsolutePath();
		return deployDir;
	}

	/**
	 * @return
	 */
	public static String getLogo() {
		return SysConfig.getResourceRoot() + Constants.RESOURCE_DEFAULT_LOGO;
	}

}
