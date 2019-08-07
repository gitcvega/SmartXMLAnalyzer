package com.agileengine.smartxmlanalyzer;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class XMLElementAttributes {

    private Element element;
    private Map<String, String> attributes;

    public XMLElementAttributes builder(Element element){
        this.element = element;
        attributes = new HashMap<>();
        attributes = XMLElementAttributes.getAttributes(element);
        attributes.put("tag", element.tagName());
        attributes.put("text", element.text());
        return this;
    }

    public static Map<String, String> getAttributes(Element element){

        return element.attributes().asList().stream()
                .collect(Collectors.toMap(Attribute::getKey, Attribute::getValue));
    }

    public String buildElementsPath(){
        String cssPattern = String.format("%1s[%2s=\"%3s\"]",
                this.getElement().tagName(),
                "class",
                this.getElement().attr("class"));
        return buildPath(this.getElement().parent(), cssPattern);
    }

    private String buildPath(Element node, String tagName){

        if(node != null){
            String parentTagName = node.tagName();
            return (parentTagName.equals("#root"))?
                    buildPath(null, tagName) :
                    buildPath(node.parent(), parentTagName + " > " + tagName);
        }
        return tagName;
    }

    public String getAttributeValue(String key) {
        return attributes.get(key);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XMLElementAttributes that = (XMLElementAttributes) o;
        return Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes);
    }

    @Override
    public String toString() {
        return "XMLElementAttributes{" +
                "attributes=" + attributes +
                '}';
    }

}
