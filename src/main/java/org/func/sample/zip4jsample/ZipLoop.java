package org.func.sample.zip4jsample;

import net.lingala.zip4j.util.Zip4jConstants;

public class ZipLoop {
	public static void main(String[] args) throws Exception {

		String source = args[0];
		String zipFilePath = args[1];
		String password = "password";
		String method = args[2];
		int loop = Integer.parseInt(args[3]);
		int sleep = Integer.parseInt(args[4]);
				
		
		if (method.equals("zip")) {
			new ZipUtil().zip(source, zipFilePath, password);
		} else if (method.equals("unzip")) {
			for (int i = 0; i < 6; i++) {
				new ZipThread(source, zipFilePath, password, loop, sleep).start();
			}
		}
	}
}
