package com.pathum.eroad.processor;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.pathum.eroad.exception.ApplicationException;
import com.pathum.eroad.util.PropertyUtil;

public class CSVFileProcessor extends BaseFileProcessor {

	public CSVFileProcessor(String file, String delimiter) {
		super(file, delimiter);
	}

	@Override
	public void processOutputFile() throws ApplicationException {

		try {

			StringBuilder buf = processStringToWrite();

			OutputStream outputFs = new FileOutputStream(new File(PropertyUtil.getProperty("outputFilePath")));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputFs));
			if (buf != null)
				bw.write(buf.toString());
			bw.close();

		} catch (Exception ex) {
			logger.error(" Error occur while processing the file:", ex);
			throw new ApplicationException(" Error");

		}

	}

	private StringBuilder processStringToWrite() {
		final StringBuilder buf;
		if (outputFileRecordList != null && outputFileRecordList.size() > 0) {
			buf = new StringBuilder();
			outputFileRecordList.forEach(record -> {

				buf.append(record.getDateTime()).append(",");
				buf.append(record.getLatitude()).append(",");
				buf.append(record.getLongitude()).append(",");
				buf.append(record.getZoneName()).append(",");
				buf.append(record.getLocalDateTime()).append("\n");

			});

			return buf;
		} else {
			logger.error("Cannot append strings list is null");
			throw new IllegalArgumentException("Cannot append strings list is null");
		}
	}

}