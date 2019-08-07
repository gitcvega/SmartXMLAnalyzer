package com.agileengine.smartxmlanalyzer;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class XMLElementSeeker {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLElementSeeker.class);

    public static Optional<Element> findElementById(XMLDocument xmlDocument, String targetElementId) {
        return Optional.ofNullable(xmlDocument.getDocument().getElementById(targetElementId));
    }

    public static Optional<Elements> findElementsByQuery(XMLDocument xmlDocument, String query) {
        return Optional.ofNullable(xmlDocument.getDocument().select(query));
    }

}
