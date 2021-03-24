package com.luban.utils;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.poi.xwpf.converter.xhtml.Base64EmbedImgManager;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ApacheWord2PdfUtil {

    public static void wordToHtml(InputStream inputStream, OutputStream outputStream) {
        try {
            XWPFDocument docxDocument = new XWPFDocument(inputStream);
            XHTMLOptions options = XHTMLOptions.create();
            options.setImageManager(new Base64EmbedImgManager());
            XHTMLConverter.getInstance().convert(docxDocument, outputStream, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param inputStream docx文档输入流
     * @param outputStream pdf输出流
     */
    public static void wordToPdf(InputStream inputStream, OutputStream outputStream) {
        try {
            XWPFDocument docxDocument = new XWPFDocument(inputStream);
            PdfOptions pdfOptions = PdfOptions.create();
            PdfConverter.getInstance().convert(docxDocument, outputStream, pdfOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String filepath = "C:\\Users\\luban\\Desktop\\siPrvUqIyVIwZTOx.docx";
            String outpath = "C:\\Users\\luban\\Desktop\\test.pdf";
            inputStream = new FileInputStream(filepath);
            outputStream = new FileOutputStream(outpath);
            wordToPdf(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
