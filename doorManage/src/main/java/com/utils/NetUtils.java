package com.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NetUtils {

	private static final Logger log = LoggerFactory.getLogger(NetUtils.class);

	/**
	 * return the ip address of the machine while running this jvm.
	 * 
	 * @return
	 */
	public final static String getIpAddress() {
		String ip = null;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("unknow host!", e);
		}
		return ip;
	}

}
