package com.bitmind.web;

import java.io.IOException;

public interface Connection {

	public String loadPage(String urlString) throws IOException;
}