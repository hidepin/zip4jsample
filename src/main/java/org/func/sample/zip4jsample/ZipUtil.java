package org.func.sample.zip4jsample;

import java.io.File;

import net.lingala.zip4j.core.NativeStorage;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ZipUtil {

	private static int compressionMethod = Zip4jConstants.COMP_DEFLATE;
	private static int compressionLevel = Zip4jConstants.DEFLATE_LEVEL_NORMAL;
	private static int encryptionMethod = Zip4jConstants.ENC_METHOD_STANDARD;
	private static int aesKeyStrength = Zip4jConstants.AES_STRENGTH_256;

	public static boolean zip(String outFile, String input, String password) {
		try {
			ZipFile zipFile = new ZipFile(outFile);
			zipFile.setFileNameCharset("UTF-8");

			ZipParameters parameters = new ZipParameters();
			// 圧縮率
			parameters.setCompressionMethod(compressionMethod);
			parameters.setCompressionLevel(compressionLevel);
			// 暗号化
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(encryptionMethod);
			parameters.setAesKeyStrength(aesKeyStrength);
			parameters.setPassword(password);

			NativeStorage inputFile = new NativeStorage(new File(input));
			if (inputFile.isDirectory()) {
				zipFile.createZipFileFromFolder(inputFile, parameters, false, 0);
			} else {
				zipFile.addFile(inputFile, parameters);
			}

			return true;
		} catch (ZipException ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
