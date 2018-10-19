package org.func.sample.zip4jsample;

import java.io.File;

import org.apache.commons.io.Charsets;

import net.lingala.zip4j.core.NativeStorage;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;


public class ZipLoop {
    public static void main(String[] args) throws Exception {
    	if (args.length != 1) { 
    		System.err.println("error");
    		System.exit(1);
    	}
    	String source = args[0];
        String zipFilePath = args[1];
        String password = "password";

        ZipLoop.doZip(source, zipFilePath, password);
        ZipLoop.doUnzip(zipFilePath, new File(zipFilePath).getParent(), password);
    }

    /**
     * ZIPファイルを作成する
     * @param source 処理対象の格納パス
     * @param zipFilePath ZIPファイルの格納パス
     * @param password ZIPファイルのパスワード
     * @throws Exception 例外
     */
    public static void doZip(String source, String zipFilePath, String password) throws Exception {
        ZipFile zipFile = new ZipFile(zipFilePath);
        zipFile.setFileNameCharset(Charsets.UTF_8.name());

        ZipParameters parameters = new ZipParameters();
        parameters.setEncryptFiles(true);
        parameters.setPassword(password);
        // 圧縮方式　Deflate
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        // 圧縮レベル 5
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        // 暗号化方式 ZipCrypt
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);

        File sourceFile = new File(source);
        if (sourceFile.isDirectory()) {
            zipFile.createZipFileFromFolder(new NativeStorage(sourceFile), parameters, false, 0);
        } else {
            zipFile.addFile(sourceFile, parameters);
        }
    }

    /**
     * ZIPファイルを解凍する
     * @param zipFilePath ZIPファイルの格納パス
     * @param dest 解凍ファイルの格納パス
     * @param password ZIPファイルのパスワード
     */
    public static void doUnzip(String zipFilePath, String dest, String password) throws Exception {
        ZipFile zipFile = new ZipFile(zipFilePath);
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(password);
        }
        zipFile.extractAll(dest);
    }
}
