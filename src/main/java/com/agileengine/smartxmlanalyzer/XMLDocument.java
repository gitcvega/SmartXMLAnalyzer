package com.agileengine.smartxmlanalyzer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class XMLDocument {

    private static final String CHARSET_NAME = "utf8";
    private Document document ;
    private File xmlFile;

    public XMLDocument(File xmlFile) throws IOException {
        this.xmlFile = xmlFile;
        this.document = Jsoup.parse(
                this.xmlFile,
                CHARSET_NAME,
                this.xmlFile.getAbsolutePath());;
    }

    public Document getDocument() {
        return document;
    }

    public File getXmlFile() {
        return xmlFile;
    }

}
