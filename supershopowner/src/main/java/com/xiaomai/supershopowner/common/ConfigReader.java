package com.xiaomai.supershopowner.common;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 配置文件读取，只提供了读取方法
 * 
 * @author chen.zhijun idtFlag
 * 
 * ImageDS
 */
public class ConfigReader extends PropertyPlaceholderConfigurer {

	private static Map<String, String> ctxPropertiesMap;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {

		super.processProperties(beanFactory, props);
		ctxPropertiesMap = new HashMap<String, String>();
		for (Map.Entry<Object, Object> entry : props.entrySet()) {
			String key = entry.getKey().toString();
			String value = props.getProperty(key);
			ctxPropertiesMap.put(key, value);
		}

	}

	/**
	 * 使用静态方法读取配置文件
	 */
	public static String getProperty(String key) {
		return ctxPropertiesMap.get(key);
	}

	/**
	 * 更新一个配置文件
	 */
	public static String updateProperty(String key, String value) {
		return ctxPropertiesMap.replace(key, value);
	}

}
