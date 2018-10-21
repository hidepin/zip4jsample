package org.func.sample.zip4jsample;

import net.lingala.zip4j.util.Zip4jConstants;

public class ZipLoop {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.println("error");
			System.exit(1);
		}
		String source = args[0];
		String zipFilePath = args[1];
		String password = "password";
		String method = args[2];
		
		if (method.equals("zip")) {
			new ZipUtil().zip(source, zipFilePath, password);
		} else if (method.equals("unzip")) {
			new ZipUtil().unzip(source, zipFilePath, password);
		}
	}
}
