package com.bitmind.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import com.bitmind.util.PropertyManager;

public final class WebConnection {

	private static final String PROXY_PORT = PropertyManager.PROXY_PORT;
	private static final String PROXY_HOST = PropertyManager.PROXY_HOST;
	private static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)";

	static {
		setProxy();
	}

	private static void setProxy() {
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost", PROXY_HOST);
		systemProperties.getProperty("http.proxyPort", PROXY_PORT);
	}

	/**
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public static String loadPage(String urlString) throws IOException {

		InputStream in = null;

		String page = "";

		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.connect();
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = connection.getInputStream();
			}

		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}

		if (in != null) {
			page = inputStreamToString(in);
		}

		return page;
	}

	/**
	 * @param is
	 * @return
	 */
	private static String inputStreamToString(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;

		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
