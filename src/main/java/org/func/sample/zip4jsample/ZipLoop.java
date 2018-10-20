package org.func.sample.zip4jsample;

import net.lingala.zip4j.util.Zip4jConstants;

public class ZipLoop {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("error");
			System.exit(1);
		}
		String source = args[0];
		String zipFilePath = args[1];
		String password = "password";

		ZipUtil.zip(zipFilePath, source, password);
	}
}
