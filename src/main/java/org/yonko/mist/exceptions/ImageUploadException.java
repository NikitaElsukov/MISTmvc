package org.yonko.mist.exceptions;

import java.io.IOException;

public class ImageUploadException extends RuntimeException {

	public ImageUploadException(String string) {
		super(string);
	}

	public ImageUploadException(String string, Exception e) {
		super(string, e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
