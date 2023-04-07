package exercise;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

// BEGIN
// Аннотация задаётся описанием соответствующего интерфейса с ключевым словом @interface
// Саму аннотацию можно пометить несколькими аннотациями

// Аннотация @Target указывает, что именно можно пометить этой аннотацией.
// Аннотация @Retention указывает жизненный цикл аннотации.
// RUNTIME указывает, что она будет видна и в процессе выполнения кода.
// Это нужно для того, чтобы мы могли в процессе выполнения обращаться к классу через рефлексию.
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inspect {
    String level() default "debug";
}
// END
