package org.func.sample.zip4jsample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;

import net.lingala.zip4j.core.NativeFile;
import net.lingala.zip4j.core.NativeStorage;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipOutputStream;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ZipUtil {

	private int compressionMethod = Zip4jConstants.COMP_DEFLATE;
	private int compressionLevel = Zip4jConstants.DEFLATE_LEVEL_NORMAL;
	private int encryptionMethod = Zip4jConstants.ENC_METHOD_STANDARD;
	private int aesKeyStrength = Zip4jConstants.AES_STRENGTH_256;
	private ZipOutputStream outputStream;

	public ZipUtil() {
	}

	public ZipUtil(int encryptionMethod, int aesKeyStrength) {
		this.encryptionMethod = encryptionMethod;
		this.aesKeyStrength = aesKeyStrength;
	}

	public void zip(String input, String output, String password)
			throws ZipException, IOException, CloneNotSupportedException {
		zip(input, output, password, "");
	}

	public void zip(String input, String output, String password, String fileNameCharset)
			throws ZipException, IOException, CloneNotSupportedException {

		ZipModel zipModel = new ZipModel();
		// If the fileNameCharset is empty then charset is detected automatically
		// Try with Cp850 and UTF8 or OS default
		if (!fileNameCharset.isEmpty()) {
			zipModel.setFileNameCharset(fileNameCharset);
		}

		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(compressionMethod);
		parameters.setCompressionLevel(compressionLevel);
		parameters.setEncryptFiles(true);
		parameters.setEncryptionMethod(encryptionMethod);
		parameters.setAesKeyStrength(aesKeyStrength);
		parameters.setPassword(password);
		parameters.setDefaultFolderPath(new NativeStorage(new File(input)));

		try {
			outputStream = new ZipOutputStream(new NativeFile(new File(output), "rw"), zipModel);
			InputStream inputStream = new FileInputStream(new File(input));
			byte[] readBuff = new byte[4096];
			int readLen = -1;
			while ((readLen = inputStream.read(readBuff)) != -1) {
				System.out.println("hello");
				outputStream.write(readBuff, 0, readLen);
			}

		} finally {
			outputStream.close();
		}
	}

	public void unzip(String input, String output, String password) throws ZipException {
		ZipFile zipFile = new ZipFile(input);
		if (zipFile.isEncrypted()) {
			zipFile.setPassword(password);
		}
		zipFile.extractAll(output);
	}
}
