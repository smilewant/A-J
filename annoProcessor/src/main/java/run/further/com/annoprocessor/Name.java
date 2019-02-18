package run.further.com.annoprocessor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Hukuan
 * 2018/5/18.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String name() default "";
}
