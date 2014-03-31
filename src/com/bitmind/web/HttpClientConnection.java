package com.bitmind.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bitmind.util.PropertyManager;
import com.google.appengine.api.utils.SystemProperty;

public class HttpClientConnection implements Connection {

	private final Logger log = LoggerFactory
			.getLogger(HttpClientConnection.class);

	public HttpClientConnection() {
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
		String page = "";

		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(urlString);

			if (isDev()) {
				CredentialsProvider credsProvider = new BasicCredentialsProvider();

				AuthScope authScope = new AuthScope(PropertyManager.PROXY_HOST,
						80, "", "http");

				NTCredentials ntCredentials = new NTCredentials("", "", "", "");

				credsProvider.setCredentials(authScope, ntCredentials);

				// httpclient = HttpClients.custom()
				// .setDefaultCredentialsProvider(credsProvider)
				// .setProxy(new HttpHost(PropertyManager.PROXY_HOST, 80))
				// .build();

				httpclient = HttpClients.custom().useSystemProperties().build();
				httpget.setHeader("User-Agent", PropertyManager.USER_AGENT);

				// httpget.setConfig(config);
			}

			CloseableHttpResponse response = httpclient.execute(httpget);
			int responseCode = response.getStatusLine().getStatusCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				HttpEntity entity = response.getEntity();
				in = entity.getContent();
			} else {
				log.error("Bad response: " + responseCode);
			}

		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}

		if (in != null) {
			page = inputStreamToString(in);
		}

		return page;
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
