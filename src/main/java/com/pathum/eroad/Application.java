package com.pathum.eroad;

import com.pathum.eroad.exception.ApplicationException;
import com.pathum.eroad.util.PropertyUtil;

public class Application {

	public static void main(String[] args) {

		String file = PropertyUtil.getProperty("inputFilePath");
		try {
			FileReaderFactory.getReader(file).processFile();
		} catch (ApplicationException ae) {
			System.out.println(ae);
		}

	}

}
