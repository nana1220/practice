/*
 * Since XML is very verbose, you are given a way of encoding it where each tag gets mapped to a
 * pre-defined integer value. The language grammar is as follows:
 * Element --> Tag Attributes END children END
 * Attribute --> Tag Value
 * END --> 0
 * Tag --> some predefined mapping to int
 * Value --> string value END
 * For example, the following XML might be converted into the compressed string below (assuming a
 * mapping of family->1, person->2, firstName->3, lastName->4, state->5).
 * <family lastNmae='McDowell' state="CA">
 * <person firstName="Gayle">Some Message</person>
 * </family>
 * Becomes:
 * 1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0
 * Write code to print encoded version of an XML element (passed in Element and Attribute
 * objects).
 */

package ch17moderate;

import java.lang.StringBuilder;

public class EncodeXML {
  static String encode(Element element) {
    StringBuilder sb = new StringBuilder();
    sb.append(element.getTagCode(element.tag) + " " + element.value + " ");
    for (Attribute a : element.attributes) {
      sb.append(a.tag + " " + a.value + " ");
    }
    sb.append("0 ");
    if (element.value != null) {
      sb.append(element.value + " ");
    } else {
      for (Element e : element.children) {
        encode(e);
      }
    }
    sb.append("0 ");
    return sb.toString();
  }
}

class Element {
  // each element contains a list of attributes and a list of children
  public ArrayList<Attribute> attributes;
  public ArrayList<Element> children;
  public String tag;
  public String value;

  public Element(String n) {
    tag = n;
    attributes = new ArrayList<Attribute>();
    children = new ArrayList<Element>();
  }

  public Element(String n, String v) {
    tag = n;
    value = v;
    attributes = new ArrayList<Attribute>();
    children = new ArrayList<Element>();
  }

  // a map that encode element tag
  public String getTagCode() {
    if (tag == "family") {
      return "1";
    } else if (tag == "person") {
      return "2";
    } else if (tag == "firstName") {
      return "3";
    } else if (tag == "lastName") {
      return "4";
    } else if (tag == "state") {
      return "5";
    }
    return "--";
  }

  public void insert(Attribute attribute) {
    attributes.add(attribute);
  }

  public void insert(Element child) {
    children.add(child);
  }
}

class Attribute {
  public String tag;
  public String value;
  public Attribute(String t, String v) {
    tag = t;
    value = v;
  }
  // map that encode attribute names
  public String getTagCode() {
    if (tag == "family") {
      return "1";
    } else if (tag == "person") {
      return "2";
    } else if (tag == "firstName") {
      return "3";
    } else if (tag == "lastName") {
      return "4";
    } else if (tag == "state") {
      return "5";
    }
    return "--";
  }
}