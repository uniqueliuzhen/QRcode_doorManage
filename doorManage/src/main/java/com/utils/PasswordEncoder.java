package com.utils;

/**
 * 
 * <p>密码加密接口</p> 
 *
 */

public interface PasswordEncoder {
	final static String MD5 = "MD5";

	final static String SHA = "SHA";

	final static String SMD5 = "SMD5";

	final static String SSHA = "SSHA";

	final static String HEX = "HEX";

	final static String BASE64 = "BASE64";

	final static String DEFAULT_ENCODER = MD5;

	final static String ENCODING_UTF8 = "UTF-8";

	String encode(String rawPassword);

	Boolean matches(String rawPassword, String encodedPassword);
}
