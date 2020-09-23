
package com.daniel.delivery.main;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;

@Stereotype  
@Alternative
@Target( { TYPE, METHOD, FIELD })  
@Retention(RUNTIME)
public @interface Testing {
}
