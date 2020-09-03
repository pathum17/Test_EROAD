package com.pathum.eroad.test.integration.processor;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.pathum.eroad.FileReaderFactory;
import com.pathum.eroad.exception.ApplicationException;
import com.pathum.eroad.util.PropertyUtil;

public class CSVFileProcessorIT {

	@Test
	public void testProcessFile() throws ApplicationException, IOException {
		
		String inputFile = PropertyUtil.getProperty("inputFilePath");
		String outputFile = PropertyUtil.getProperty("outputFilePath");

		String junitTestFile = PropertyUtil.getProperty("junitOutputFilePath");

		FileReaderFactory.getReader(inputFile).processFile();
		
		File junitFile = new File(junitTestFile);
		File output = new File(outputFile);
		Assert.assertTrue("The file contect is different.", FileUtils.contentEquals(junitFile, output));

	}
}
