package com.toolset.file.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.css.media.MediaDeviceDescription;
import com.itextpdf.html2pdf.css.media.MediaType;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.font.FontProvider;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <b>PDF 操作工具类</b>
 *
 * @author carlosxiao
 */
public class PdfExportUtil {

    /**
     * Logger for this class.
     */
    private static Logger logger = LoggerFactory.getLogger(PdfExportUtil.class);

    private static void exportToOutputStream(OutputStream outputStream, String content) {
        try {
            PdfWriter writer = new PdfWriter(outputStream, new WriterProperties().setFullCompressionMode(true));
            PdfDocument pdfDocument = new PdfDocument(writer);
            PageSize pageSize = new PageSize(1095.0F, 1000.0F).rotate();
            pdfDocument.setDefaultPageSize(pageSize);
            ConverterProperties converterProperties = new ConverterProperties();
            //中文设置
            FontProvider fp = new FontProvider();
            fp.addStandardPdfFonts();
            byte[] fontByte = IOUtils.toByteArray(PdfExportUtil.class.getResource("/fonts/msyh.ttf").openStream());
            fp.addFont(fontByte);
            converterProperties.setFontProvider(fp);
            MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.SCREEN);
            mediaDeviceDescription.setWidth(pageSize.getWidth());
            converterProperties.setMediaDeviceDescription(mediaDeviceDescription);
            HtmlConverter.convertToPdf(content, pdfDocument, converterProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportToFileOutputStream(OutputStream outputStream, String content) {
        exportToOutputStream(outputStream, content);
    }

    public static void exportToFile(String filePath, String content) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            exportToOutputStream(outputStream, content);
            // flush
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }
}
