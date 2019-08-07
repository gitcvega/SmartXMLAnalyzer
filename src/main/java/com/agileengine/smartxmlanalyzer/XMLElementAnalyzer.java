package com.agileengine.smartxmlanalyzer;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

public class XMLElementAnalyzer {

    public static final String FILTER_BY_TAG_NAME = "tag";

    private XMLDocument sourceXML;
    private XMLDocument targetXML;
    private String sourceElementId;

    private Map<XMLElementAttributes, Integer> counterMatcherMap = new HashMap<>();

    public XMLElementAnalyzer(XMLDocument sourceXML, XMLDocument targetXML, String sourceElementId) {
        this.sourceXML = sourceXML;
        this.targetXML = targetXML;
        this.sourceElementId = sourceElementId;
    }

    public XMLElementAttributes analyzeAndGet() throws Exception {

        XMLElementAttributes sourceElementsAttrs = getSourceElementsAttrs(sourceXML, sourceElementId);
        List<XMLElementAttributes> targetElementsAttrsList = filterTargetElementsAttrs(sourceElementsAttrs, targetXML);

        targetElementsAttrsList.forEach( attrs-> {
            counterMatcherMap.putIfAbsent(attrs, 0);
            counterMatcherMap.computeIfPresent(attrs, (key, value)-> value + counterMatcher(sourceElementsAttrs, attrs));
        });

        return maxValue(counterMatcherMap).getKey();
    }

    private XMLElementAttributes getSourceElementsAttrs(XMLDocument sourceXML, String sourceElementId) throws Exception {

        Optional<Element> opElement = XMLElementSeeker.findElementById(sourceXML, sourceElementId);
        Element sourceElement = opElement.orElseThrow(() -> new Exception("No source element id found"));
        return new XMLElementAttributes().builder(sourceElement);
    }

    private  List<XMLElementAttributes> filterTargetElementsAttrs(XMLElementAttributes sourceElementsAttrs,
                                                                  XMLDocument targetXML) throws Exception {

        String query = sourceElementsAttrs.getAttributeValue(FILTER_BY_TAG_NAME);
        Elements targetElement = XMLElementSeeker.findElementsByQuery(targetXML, query)
                                    .orElseThrow(() -> new Exception("No target elements found"));

        return targetElement.stream().map(el -> new XMLElementAttributes().builder(el)).collect(Collectors.toList());
    }


    private int counterMatcher(XMLElementAttributes source, XMLElementAttributes target){

        return source.getAttributes().entrySet().stream()
                .map(sourceEntry ->
                        target.getAttributes().containsKey(sourceEntry.getKey()) ?
                            target.getAttributeValue(sourceEntry.getKey()).equals(sourceEntry.getValue()) ? 1 : 0
                            :
                            0
                ).reduce(Integer::sum).orElse(0);
    }

    private Map.Entry<XMLElementAttributes, Integer> maxValue(Map<XMLElementAttributes, Integer> map) {

        return Collections.max(map.entrySet(), Comparator.comparing(Map.Entry::getValue));
    }

}
