package Anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.function.Consumer;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface MiContrato{ }
//probando...