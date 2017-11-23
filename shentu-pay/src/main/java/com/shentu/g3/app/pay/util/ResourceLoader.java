//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shentu.g3.app.pay.util;

import java.io.InputStream;

public class ResourceLoader {
	public ResourceLoader() {
	}

	public static InputStream loadResource(String name) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}

	public static ClassLoader getCurrentClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}
