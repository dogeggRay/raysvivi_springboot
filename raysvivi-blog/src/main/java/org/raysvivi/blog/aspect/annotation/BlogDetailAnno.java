package org.raysvivi.blog.aspect.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BlogDetailAnno {

    String relativeId();
}
