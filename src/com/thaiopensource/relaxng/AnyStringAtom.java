package com.thaiopensource.relaxng;

class AnyStringAtom extends Atom {
  boolean matchesString() {
    return true;
  }
  boolean matchesPreserveString(String s) {
    return true; // can happen during error recovery
 }

  boolean matchesNormalizeString(String s) {
    return true; // can happen during error recovery
  }

}
