package com.sds.android.ttpod.framework.base;

import com.sds.android.ttpod.framework.modules.a;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* ObserverCommandID */
public @interface j {
    a[] a();
}
