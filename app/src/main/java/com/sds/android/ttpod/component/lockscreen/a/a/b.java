package com.sds.android.ttpod.component.lockscreen.a.a;

/* FloatEvaluator */
public class b implements k<Number> {
    public Float a(float f, Number number, Number number2) {
        float floatValue = number.floatValue();
        return Float.valueOf(floatValue + ((number2.floatValue() - floatValue) * f));
    }
}
