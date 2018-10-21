package org.func.sample.zip4jsample;

import net.lingala.zip4j.exception.ZipException;

public class ZipThread extends Thread {
	private String source = null;
	private String zipFilePath = null;
	private String password = null;
	private int loop = 0;
	private int sleep = 0;
	
	public ZipThread(String source, String zipFilePath, String password, int loop, int sleep) {
		this.source = source;
		this.zipFilePath = zipFilePath;
		this.password = password;
		this.loop = loop;
		this.sleep = sleep;
	}

	public void run() {
		ZipUtil zipUtil = new ZipUtil();

		for (int i = 0; i < loop; i++) {
			try {
				zipUtil.unzip(source, zipFilePath, password);
				Thread.sleep(sleep);
			} catch (ZipException | InterruptedException e) {
				System.err.println(e);
			}
		}
	}
}
