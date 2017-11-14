package com.sds.android.sdk.lib.c.a.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* Field */
public @interface b {
    int a() default 0;

    int b();

    boolean c() default false;

    boolean d() default false;

    boolean e() default false;

    String f() default "";

    String g() default "";
}
