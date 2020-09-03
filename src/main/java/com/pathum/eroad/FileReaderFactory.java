package com.pathum.eroad;

import java.util.Locale;

import com.pathum.eroad.processor.CSVFileProcessor;
import com.pathum.eroad.processor.FileProcessor;

public class FileReaderFactory {

	public final static String COMMA_DELIMITER = ",";

	public static FileProcessor getReader(String file) {

		FileExtension extension = getFileExtention(file);
		if (extension == FileExtension.CSV) {
			return new CSVFileProcessor(file, COMMA_DELIMITER);
		}

		if (extension == FileExtension.TXT) {
			// for .txt files
		}

		return null;

	}

	private static FileExtension getFileExtention(String file) {
		String separator = System.getProperty("file.separator");
		String filename;

		int lastSeparatorIndex = file.lastIndexOf(separator);
		if (lastSeparatorIndex == -1) {
			filename = file;
		} else {
			filename = file.substring(lastSeparatorIndex + 1);
		}

		int extensionIndex = filename.lastIndexOf(".");
		String ext = filename.substring(extensionIndex + 1, filename.length());

		return FileExtension.valueOf(ext.toUpperCase(Locale.ENGLISH));
	}

}
