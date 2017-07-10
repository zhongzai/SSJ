package com.xiaomai.supershopowner.common;


import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtils {

	private static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static HttpResponse post(String url, Header header) {

		HttpPost httpPost = new HttpPost(url);

		if (null != header) {
			httpPost.addHeader(header);
		}

		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return httpResponse;
	}

}
