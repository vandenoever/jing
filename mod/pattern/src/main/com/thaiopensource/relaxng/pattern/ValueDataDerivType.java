package com.thaiopensource.relaxng.pattern;

import org.relaxng.datatype.Datatype;
import org.relaxng.datatype.ValidationContext;

import java.util.HashMap;
import java.util.Map;

class ValueDataDerivType extends DataDerivType {
  private final Datatype dt;
  private PatternMemo noValue;
  private Map<DatatypeValue, PatternMemo> valueTable;

  ValueDataDerivType(Datatype dt) {
    this.dt = dt;
  }

  DataDerivType copy() {
    return new ValueDataDerivType(dt);
  }

  PatternMemo dataDeriv(ValidatorPatternBuilder builder, Pattern p, String str, ValidationContext vc) {
    Object value = dt.createValue(str, vc);
    if (value == null) {
      if (noValue == null)
        noValue = super.dataDeriv(builder, p, str, vc);
      return noValue;
    }
    else {
      DatatypeValue dtv = new DatatypeValue(value, dt);
      if (valueTable == null)
        valueTable = new HashMap<DatatypeValue, PatternMemo>();
      PatternMemo tem = valueTable.get(dtv);
      if (tem == null) {
        tem = super.dataDeriv(builder, p, str, vc);
        valueTable.put(dtv, tem);
      }
      return tem;
    }
  }

  DataDerivType combine(DataDerivType ddt) {
    if (ddt instanceof ValueDataDerivType) {
      if (((ValueDataDerivType)ddt).dt == this.dt)
        return this;
      else
        return InconsistentDataDerivType.getInstance();
    }
    else
      return ddt.combine(this);
  }

  Datatype getDatatype() {
    return dt;
  }
}
