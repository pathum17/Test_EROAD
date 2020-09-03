package com.pathum.eroad.processor;

import java.util.List;

import com.pathum.eroad.exception.ApplicationException;
import com.pathum.eroad.model.Record;

public interface FileProcessor {
	
	List<Record> readFile() throws ApplicationException;
	
	void processFile() throws ApplicationException;

	void processOutputFile() throws ApplicationException;
}
