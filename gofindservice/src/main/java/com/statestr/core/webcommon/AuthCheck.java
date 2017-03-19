package com.statestr.core.webcommon;

import java.lang.annotation.*;

/**
 * Created by ruantianbo on 2017/3/19.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AuthCheck {
    boolean value() default true; //whether will check login.
}
