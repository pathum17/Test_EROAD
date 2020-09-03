package com.pathum.eroad;

public enum FileExtension {
	
	CSV("CSV"), 
	TXT("TXT");
	
	private String extension;
	
	FileExtension(String extension) {
		this.extension = extension;
	}
	
	public String extension() {
		return extension;
	}

}
