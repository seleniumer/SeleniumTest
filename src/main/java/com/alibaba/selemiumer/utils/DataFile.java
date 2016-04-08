package com.alibaba.selemiumer.utils;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RUNTIME)
public @interface DataFile {

    /** 数据文件的地址 */
    String path() default "";

    /** 如果是 Excel格式的数据来源，可以设置要读取的sheet的名字 */
    String sheetName() default "";

    /** 如果是 Excel格式的数据来源，可以设置要读取的sheet的index */
    int sheetIndex() default 0;

}
