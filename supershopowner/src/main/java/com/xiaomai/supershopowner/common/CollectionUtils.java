package com.xiaomai.supershopowner.common;

/**
 * CollectionUtils
 * 2016年6月14日
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author chen.zhijun
 *
 */
public class CollectionUtils {

	private static final String COLLECTION_PREFIX = "[";
	private static final String COLLECTION_SUBFIX = "]";
	private static final String DEFAULT_DECOLLATOR = ",";

	private CollectionUtils() {

	}

	public static String collectionToString(Object[] objects, String start, String decollator, String end) {
		return collectionToString(Arrays.asList(objects), start, decollator, end);
	}

	public static <T> String collectionToString(Collection<T> collection, String start, String decollator, String end) {

		VerificationUtils.validNotNull(start, decollator, end);

		StringBuilder builder = new StringBuilder();
		builder.append(start);

		Iterator<T> iterator = collection.iterator();

		while (iterator.hasNext()) {
			T cell = iterator.next();
			builder.append(cell).append(decollator);
		}

		int length = builder.length();
		// delete the last decollator
		builder.delete(length - decollator.length(), length);
		builder.append(end);
		return builder.toString();
	}

	public static <T> String collectionToString(Collection<T> collection) {
		return collectionToString(collection, COLLECTION_PREFIX, DEFAULT_DECOLLATOR, COLLECTION_SUBFIX);
	}

}
