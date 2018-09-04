package com.toolset.file.word;

import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

/**
 * Word文档导出工具类
 *
 * @author carlosxiao
 */
public class WordExportUtil {

    private static Logger logger = LoggerFactory.getLogger(WordExportUtil.class);

    /**
     * @param output            Writer
     * @param textTemplate      模板内容
     * @param replacementValues 需要替换的变量
     */
    private static void exportWordToFile(Writer output, String textTemplate, String templateName, Map<?, ?> replacementValues) {
        if (replacementValues == null || replacementValues.size() == 0) {
            throw new IllegalArgumentException("The replacementValues cannot be null or empty Map");
        }

        if (textTemplate == null || "".equals(textTemplate)) {
            throw new IllegalArgumentException("The textTemplate cannot be null or empty string, " +
                    "please pass in at least something in the template or do not call this method");
        }

        // setup freemarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDefaultEncoding("utf-8");
        // Specify how templates will see the data-model
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_25));
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        // get the template
        Template template;
        try {
            template = new Template(templateName, new StringReader(textTemplate), cfg);
        } catch (IOException e) {
            throw new RuntimeException("Failure while creating freemarker template", e);
        }
        try {
            template.process(replacementValues, output);
        } catch (TemplateException e) {
            throw new RuntimeException("Failure while processing freemarker template", e);
        } catch (IOException e) {
            throw new RuntimeException("Failure while sending freemarker output to stream", e);
        }
    }

    public static void exportWordToFile(File file, String textTemplate, String templateName, Map<?, ?> replacementValues) {
        Writer outputWriter = null;
        try {
            outputWriter = new FileWriter(file);
            exportWordToFile(outputWriter, textTemplate, templateName, replacementValues);
            outputWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputWriter != null) {
                    outputWriter.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    public static void exportWordToStream(OutputStream outputStream, String textTemplate, String templateName, Map<?, ?> replacementValues) {
        Writer outputWriter = new OutputStreamWriter(outputStream);
        exportWordToFile(outputWriter, textTemplate, templateName, replacementValues);
    }

}
