package com.b.a.a;

import com.b.a.w;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* JsonAdapter */
public @interface b {
    Class<? extends w<?>> a();
}
