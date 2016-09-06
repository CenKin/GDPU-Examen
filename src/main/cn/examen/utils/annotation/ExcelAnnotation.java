package cn.examen.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author guoxuhui 
 * @create 2016-05-06  11:46
*/
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD})
public @interface ExcelAnnotation {
    String columnName() default "";
}
