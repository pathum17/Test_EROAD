package com.pathum.eroad.test.unit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pathum.eroad.exception.ApplicationException;
import com.pathum.eroad.model.Record;
import com.pathum.eroad.processor.CSVFileProcessor;

public class CSVFileProcessorTest {
	
	@Test
	public void testinputRecordSize() throws ApplicationException {
		CSVFileProcessor csvFileProcessor = new CSVFileProcessor("/Users/pathum/Documents/input.csv", ",");

		List<Record> list = csvFileProcessor.readFile();

		Assert.assertEquals(2, list.size());		
	}

	@Test
	public void testinputRecord() throws ApplicationException {
		Record record = new Record();
		record.setDateTime("2013-07-10 02:52:49");
		record.setLatitude("-44.490947");
		record.setLongitude("171.220966");

		CSVFileProcessor csvFileProcessor = new CSVFileProcessor("/Users/pathum/Documents/input.csv", ",");

		List<Record> list = csvFileProcessor.readFile();
		
		Assert.assertEquals(record, list.get(0));
		
	}

}
