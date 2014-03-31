package com.bitmind.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bitmind.util.PropertyManager;
import com.google.appengine.api.utils.SystemProperty;

public class WebConnection implements Connection {

	private final Logger log = LoggerFactory.getLogger(WebConnection.class);

	public WebConnection() {
		super();
		setProxy();
	}

	/**
	 * @param is
	 * @return
	 */
	private String inputStreamToString(InputStream is) {

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

	private boolean isDev() {
		return SystemProperty.environment.value() == SystemProperty.Environment.Value.Development;
	}

	/**
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	@Override
	public String loadPage(String urlString) throws IOException {

		InputStream in = null;
		StringBuilder sb = new StringBuilder();
		String page = "";

		try {
			URL url = new URL(urlString);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();

			System.out.println(sb.toString());
			//
			// HttpURLConnection connection = (HttpURLConnection) url
			// .openConnection();
			//
			// if (isDev()) {
			// connection.setRequestProperty("User-Agent",
			// PropertyManager.USER_AGENT);
			// }
			// connection.connect();
			// int responseCode = connection.getResponseCode();
			//
			// if (responseCode == HttpURLConnection.HTTP_OK) {
			// in = connection.getInputStream();
			// } else {
			// log.error("Bad response: " + responseCode);
			// }

		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}

		// if (in != null) {
		// page = inputStreamToString(in);
		// }

		return sb.toString();
	}

	private void setProxy() {

		log.info(SystemProperty.environment.value().toString());

		if (isDev()) {
			Properties systemProperties = System.getProperties();
			systemProperties.setProperty("http.proxyHost",
					PropertyManager.PROXY_HOST);

		}
	}
}
