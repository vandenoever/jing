package com.thaiopensource.validate.picl;

import org.xml.sax.Locator;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

class KeyConstraint implements Constraint {
  private final Pattern key;

  KeyConstraint(Pattern key) {
    this.key = key;
  }

  static class KeyIndex {
    private final Hashtable table;
    KeyIndex() {
      table = new Hashtable();
    }

    KeyInfo lookupCreate(String key) {
      KeyInfo info = (KeyInfo)table.get(key);
      if (info == null) {
        info = new KeyInfo();
        table.put(key, info);
      }
      return info;
    }

    Enumeration keys() {
      return table.keys();
    }
  }

  static class KeyInfo {
    Locator firstKeyLocator;
    Vector pendingRefLocators;
  }

  static class KeySelectionHandler extends ValueSelectionHandler {
    private final KeyIndex index;

    KeySelectionHandler(KeyIndex index) {
      this.index = index;
    }

    void select(ErrorContext ec, String value) {
      KeyInfo info = index.lookupCreate(value);
      if (info.firstKeyLocator == null) {
        info.firstKeyLocator = ec.saveLocator();
        info.pendingRefLocators = null;
      }
      else
        ec.error("duplicate_key", value);
    }
  }

  public void activate(PatternManager pm) {
    activate(pm, new KeyIndex());
  }

  void activate(PatternManager pm, KeyIndex index) {
    pm.registerPattern(key, new KeySelectionHandler(index));
  }
}
