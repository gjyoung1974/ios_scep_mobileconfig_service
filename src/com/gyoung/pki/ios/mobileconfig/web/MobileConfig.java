package com.gyoung.pki.ios.mobileconfig.web;

import java.io.StringWriter;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MobileConfig {

    /**
     * Create a .mobileconfig via DOM XML Tree:
     *
     * @return
     */

    public String MobileConfig(String CN, String OU, String Challenge) {

        String xmlString = "";
        try {
            // ///////////////////////////
            // Creating an empty XML Document
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = fact.newDocumentBuilder();
            Document doc = parser.newDocument();
            doc.setXmlStandalone(true);

            // //////////////////////
            // Creating the XML tree
            Element root = doc.createElement("plist");
            root.setAttribute("version", "1.0");
            doc.appendChild(root);

            // create a comment and put it in the root element
            Comment comment = doc
                    .createComment("iPhone config created just for you!");
            root.appendChild(comment);

            // create child element, add an attribute, and add to root
            Element rootDict = doc.createElement("dict");

            root.appendChild(rootDict);

            Element key = doc.createElement("key");
            key.setTextContent("PayloadContent");
            rootDict.appendChild(key);

            Element Array0 = doc.createElement("array");

            Element Array00 = doc.createElement("array");

            /*
             * Set the SubjectDN "O" value
             */

            // outer
            Element Array1 = doc.createElement("array");
            // inner
            Element Array2 = doc.createElement("array");

            // String 1
            Element Array2String = doc.createElement("string");
            Array2String.setTextContent("O");
            Array2.appendChild(Array2String);

            // String 2
            Element Array2String2 = doc.createElement("string");
            Array2String2.setTextContent("ACME Corporation");
            Array2.appendChild(Array2String2);

            Array1.appendChild(Array2);

            /*
             * Set the SubjectDN "OU" value
             */

            // outer
            Element Array3 = doc.createElement("array");
            // inner
            Element Array4 = doc.createElement("array");

            // String 1
            Element Array4String = doc.createElement("string");
            Array4String.setTextContent("OU");
            Array4.appendChild(Array4String);

            // String 2
            Element Array4String2 = doc.createElement("string");
            Array4String2.setTextContent(OU);
            Array4.appendChild(Array4String2);

            Array3.appendChild(Array4);

            /*
             * Set the SubjectDN "CN" value
             */

            // outer
            Element Array5 = doc.createElement("array");
            // inner
            Element Array6 = doc.createElement("array");

            // String 1
            Element Array6String = doc.createElement("string");
            Array6String.setTextContent("CN");
            Array6.appendChild(Array6String);

            // String 2
            Element Array6String2 = doc.createElement("string");
            Array6String2.setTextContent(CN);
            Array6.appendChild(Array6String2);

            Array5.appendChild(Array6);

            Array00.appendChild(Array1);
            Array00.appendChild(Array3);
            Array00.appendChild(Array5);

            Element dict1 = doc.createElement("dict");

            Element dict1key1 = doc.createElement("key");
            dict1key1.setTextContent("PayloadContent");
            dict1.appendChild(dict1key1);

            Element dict2 = doc.createElement("dict");

            Element dict2key1 = doc.createElement("key");
            dict2key1.setTextContent("Challenge");
            dict2.appendChild(dict2key1);

            Element dict2string1 = doc.createElement("string");
            dict2string1.setTextContent(Challenge);
            dict2.appendChild(dict2string1);

            Element dict2key2 = doc.createElement("key");
            dict2key2.setTextContent("Key Type");
            dict2.appendChild(dict2key2);

            Element dict2string2 = doc.createElement("string");
            dict2string2.setTextContent("RSA");
            dict2.appendChild(dict2string2);

            Element dict2key3 = doc.createElement("key");
            dict2key3.setTextContent("Key Usage");
            dict2.appendChild(dict2key3);

            Element dict2string3 = doc.createElement("integer");
            dict2string3.setTextContent("5");
            dict2.appendChild(dict2string3);

            Element dict2key4 = doc.createElement("key");
            dict2key4.setTextContent("Keysize");
            dict2.appendChild(dict2key4);

            Element dict2string4 = doc.createElement("integer");
            dict2string4.setTextContent("2048");
            dict2.appendChild(dict2string4);

            Element dict2key5 = doc.createElement("key");
            dict2key5.setTextContent("Name");
            dict2.appendChild(dict2key5);

            Element dict2string5 = doc.createElement("string");
            dict2string5.setTextContent("CA-DC01");
            dict2.appendChild(dict2string5);

            Element dict2key6 = doc.createElement("key");
            dict2key6.setTextContent("Subject");
            dict2.appendChild(dict2key6);

            dict2.appendChild(Array00);
            // dict2.appendChild(Array6);

            Element dict2key7 = doc.createElement("key");
            dict2key7.setTextContent("URL");
            dict2.appendChild(dict2key7);

            Element dict2string7 = doc.createElement("string");
            dict2string7
                    .setTextContent("http://pki.gordonjyoung.com/certsrv/mscep/mscep.dll");
            dict2.appendChild(dict2string7);

            dict1.appendChild(dict2);

            Element dict1key2 = doc.createElement("key");
            dict1key2.setTextContent("PayloadDescription");
            dict1.appendChild(dict1key2);

            Element dict1string2 = doc.createElement("string");
            dict1string2.setTextContent("Configures SCEP");
            dict1.appendChild(dict1string2);

            Element dict1key3 = doc.createElement("key");
            dict1key3.setTextContent("PayloadDisplayName");
            dict1.appendChild(dict1key3);

            Element dict1string3 = doc.createElement("string");
            dict1string3.setTextContent("SCEP (CA-DC01)");
            dict1.appendChild(dict1string3);

            Element dict1key4 = doc.createElement("key");
            dict1key4.setTextContent("PayloadIdentifier");
            dict1.appendChild(dict1key4);

            Element dict1string4 = doc.createElement("string");
            dict1string4.setTextContent("com.goyoung.pki.util.scep.profile.scep1");
            dict1.appendChild(dict1string4);

            Element dict1key5 = doc.createElement("key");
            dict1key5.setTextContent("PayloadOrganization");
            dict1.appendChild(dict1key5);

            Element dict1string5 = doc.createElement("string");
            dict1string5.setTextContent("ACME");
            dict1.appendChild(dict1string5);

            Element dict1key6 = doc.createElement("key");
            dict1key6.setTextContent("PayloadType");
            dict1.appendChild(dict1key6);

            Element dict1string6 = doc.createElement("string");
            dict1string6.setTextContent("com.apple.security.scep");
            dict1.appendChild(dict1string6);

            Element dict1key7 = doc.createElement("key");
            dict1key7.setTextContent("PayloadUUID");
            dict1.appendChild(dict1key7);

            Element dict1string7 = doc.createElement("string");
            dict1string7.setTextContent(UUID.randomUUID().toString());
            dict1.appendChild(dict1string7);

            Element dict1key8 = doc.createElement("key");
            dict1key8.setTextContent("PayloadVersion");
            dict1.appendChild(dict1key8);

            Element dict1string8 = doc.createElement("integer");
            dict1string8.setTextContent("1");
            dict1.appendChild(dict1string8);

            Array0.appendChild(dict1);

            rootDict.appendChild(Array0);

            Element key1 = doc.createElement("key");
            key1.setTextContent("PayloadDescription");
            rootDict.appendChild(key1);

            Element string1 = doc.createElement("string");
            string1.setTextContent("ACME PKI NDES Profile");
            rootDict.appendChild(string1);

            Element key2 = doc.createElement("key");
            key2.setTextContent("PayloadDisplayName");
            rootDict.appendChild(key2);

            Element string2 = doc.createElement("string");
            string2.setTextContent("SCEP_Payload ");
            rootDict.appendChild(string2);

            Element key3 = doc.createElement("key");
            key3.setTextContent("PayloadIdentifier");
            rootDict.appendChild(key3);

            Element string3 = doc.createElement("string");
            string3.setTextContent("com.acme.pki.util.scep.profile");
            rootDict.appendChild(string3);

            Element key4 = doc.createElement("key");
            key4.setTextContent("PayloadOrganization");
            rootDict.appendChild(key4);

            Element string4 = doc.createElement("string");
            string4.setTextContent("ACME");
            rootDict.appendChild(string4);

            Element key5 = doc.createElement("key");
            key5.setTextContent("PayloadRemovalDisallowed");
            rootDict.appendChild(key5);

            Element false1 = doc.createElement("false");
            rootDict.appendChild(false1);

            Element key6 = doc.createElement("key");
            key6.setTextContent("PayloadType");
            rootDict.appendChild(key6);

            Element string6 = doc.createElement("string");
            string6.setTextContent("Configuration");
            rootDict.appendChild(string6);

            Element key7 = doc.createElement("key");
            key7.setTextContent("PayloadUUID");
            rootDict.appendChild(key7);

            Element string7 = doc.createElement("string");
            string7.setTextContent(UUID.randomUUID().toString());
            rootDict.appendChild(string7);

            Element key8 = doc.createElement("key");
            key8.setTextContent("PayloadVersion");
            rootDict.appendChild(key8);

            Element Integer1 = doc.createElement("integer");
            Integer1.setTextContent("1");
            rootDict.appendChild(Integer1);

            // ///////////////
            // Output the XML

            // set up the transformer factory
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            // trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            trans.setOutputProperty(OutputKeys.INDENT, "no");

            //set the Apple pList doctype/system
            trans.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
                    "-//Apple//DTD PLIST 1.0//EN");
            trans.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                    "http://www.apple.com/DTDs/PropertyList-1.0.dtd");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            xmlString = sw.toString();

        } catch (Exception e) {
            System.out.println(e);
        }
        return xmlString;
    }
}