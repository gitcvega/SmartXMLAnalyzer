package com.agileengine.smartxmlanalyzer;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;

public class Main
{
    @Option(name="-s", usage="Source XML file")
    public File source;

    @Option(name="-t", usage="Target XML file")
    public File target;

    @Option(name="-i", usage="ID source element")
    public String sourceElementId;


    public void setArgs(String[] args){
        final CmdLineParser parser = new CmdLineParser(this);

        if (args.length < 2){
            parser.printUsage(System.out);
            System.exit(-1);
        }

        try{
            parser.parseArgument(args);
        }catch (CmdLineException clEx){
            System.out.println("ERROR: Unable to parse command-line options: " + clEx);
            System.exit(-1);
        }

//        System.out.println("Source: " + source.getAbsolutePath());
//        System.out.println("Target: " + target.getAbsolutePath());
//        System.out.println("SourceElementId: " + sourceElementId);
    }

    public void run()  {

        XMLDocument sourceXML = null;
        XMLDocument targetXML = null;
        try {
            sourceXML = new XMLDocument(source);
        } catch (IOException e) {
            System.out.println("Source file was not found");
            System.exit(-1);
        }
        try {
            targetXML = new XMLDocument(target);
        } catch (IOException e) {
            System.out.println("Target file was not found");
            System.exit(-1);
        }

        XMLElementAnalyzer analyzer = new XMLElementAnalyzer(sourceXML, targetXML, sourceElementId);
        XMLElementAttributes elementAttributes = null;
        try {
            elementAttributes = analyzer.analyzeAndGet();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        System.out.println(elementAttributes.buildElementsPath());
//        String buildElementsPath = elementAttributes.buildElementsPath();
//        Optional<Elements> elementsByQuery = XMLElementSeeker.findElementsByQuery(targetHtml, buildElementsPath);
//        elementsByQuery.ifPresent(elements -> {
//            System.out.println(elements.text());
//        });
    }

    public static void main( String[] args ) throws Exception {
        Main main = new Main();
        main.setArgs(args);
        main.run();
    }
}
