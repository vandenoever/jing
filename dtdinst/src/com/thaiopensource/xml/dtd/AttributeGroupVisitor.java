package com.thaiopensource.xml.dtd;

public interface AttributeGroupVisitor {
  void attribute(String name,
		 boolean optional,
		 Datatype datatype,
		 String defaultValue)
    throws Exception;
  void attributeGroupRef(String name, AttributeGroup attributeGroup)
    throws Exception;
}
