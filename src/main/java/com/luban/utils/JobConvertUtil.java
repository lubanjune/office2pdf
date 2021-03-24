package com.luban.utils;

import org.jodconverter.LocalConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeManager;
import org.jodconverter.office.OfficeUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class JobConvertUtil {
    public static void covert2Pdf(InputStream inputStream, OutputStream outputStream) {
        OfficeManager officeManager =
                LocalOfficeManager.builder()
                        .portNumbers(8100)
                        .officeHome("C:/Program Files (x86)/OpenOffice 4")
                        .build();
        try {
            officeManager.start();
            LocalConverter.make(officeManager)
                    .convert(inputStream)
                    .as(DefaultDocumentFormatRegistry.DOCX)
                    .to(outputStream)
                    .as(DefaultDocumentFormatRegistry.PDF)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            OfficeUtils.stopQuietly(officeManager);
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String filepath = "C:\\Users\\luban\\Desktop\\test.docx";
            String outpath = "C:\\Users\\luban\\Desktop\\test.pdf";
            inputStream = new FileInputStream(filepath);
            outputStream = new FileOutputStream(outpath);
            covert2Pdf(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
