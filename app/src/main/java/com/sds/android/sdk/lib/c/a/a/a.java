package com.sds.android.sdk.lib.c.a.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* Entity */
public @interface a {
    String a() default "";

    e b() default @e;

    d[] c() default {};
}
