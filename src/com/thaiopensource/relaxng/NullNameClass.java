package com.thaiopensource.relaxng;

class NullNameClass implements NameClass {
  public boolean contains(String namespaceURI, String localName) {
    return false;
  }

  public int hashCode() {
    return NullNameClass.class.hashCode();
  }

  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof NullNameClass))
      return false;
    return true;
  }

  public void accept(NameClassVisitor visitor) {
    visitor.visitNull();
  }
}
