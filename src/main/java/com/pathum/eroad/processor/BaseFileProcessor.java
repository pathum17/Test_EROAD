package com.pathum.eroad.processor;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pathum.eroad.exception.ApplicationException;
import com.pathum.eroad.model.OutputFileRecord;
import com.pathum.eroad.model.Record;
import com.pathum.eroad.model.WebServiceResponse;
import com.pathum.eroad.webservice.WebServiceCaller;

public abstract class BaseFileProcessor implements FileProcessor {
	
    protected final static Logger logger = Logger.getLogger(BaseFileProcessor.class);
	
	private static final String INCOMING_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	protected BufferedReader bufferedReader;
	protected FileReader fileReader;
	protected File file;
	protected List<Record> recordList;
	protected List<OutputFileRecord> outputFileRecordList = null;
	protected String delimiter;
	private static WebServiceCaller caller = new WebServiceCaller();



	public BaseFileProcessor(String filePath) {
		super();
		setFile(filePath);
	}
	
	public BaseFileProcessor(String filePath, String delimiter) {
		super();
		setFile(filePath);
		this.delimiter = delimiter;
	}

	private void setFile(String filePath) {
		if (filePath != null) {
			file = new File(filePath);
		}
	}
	
	@Override
	public void processFile() throws ApplicationException{
		readFile();
		processRecord();
		processOutputFile();
	}
	
	@Override
	public List<Record> readFile() throws ApplicationException{

		String line = "";

		try {
			bufferedReader = new BufferedReader(new FileReader(file));

			recordList = new ArrayList<Record>();
			while ((line = bufferedReader.readLine()) != null) {
				String[] record = line.split(this.delimiter);
				recordList.add(new Record(record[0], record[1], record[2]));
			}
			logger.info("File read process completed.");

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new ApplicationException("Input file read error.");
		}

		return recordList;
	}
	
	protected void processRecord() {
		if(recordList != null) {
			outputFileRecordList = new ArrayList<OutputFileRecord>();
		}else {
			return;
		}
		
		recordList.forEach(i -> {

			// web service call to get location details
			WebServiceResponse response = caller.getLocationDetails(i.getLongitude(), i.getLatitude());

			if (response != null) {
				LocalDateTime localDateTime = getLocalisedDateTime(response, i.getDateTime());
				OutputFileRecord outputFileRecord = new OutputFileRecord();
				outputFileRecord.setLatitude(i.getLatitude());
				outputFileRecord.setLongitude(i.getLongitude());
				outputFileRecord.setDateTime(i.getDateTime());
				outputFileRecord.setZoneName(response.getZoneName());
				outputFileRecord.setLocalDateTime(localDateTime);

				outputFileRecordList.add(outputFileRecord);

			}

		});
	}
	
	private static LocalDateTime getLocalisedDateTime(WebServiceResponse response, String dateTime) {
		SimpleDateFormat formatter = new SimpleDateFormat(INCOMING_DATETIME_FORMAT);
		LocalDateTime localDateTime = null;

		try {
			ZoneId zoneId = ZoneId.of(response.getZoneName());
			Date date = formatter.parse(dateTime);
			Instant instant = date.toInstant();
			localDateTime = instant.atZone(zoneId).toLocalDateTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localDateTime;
	}

}
