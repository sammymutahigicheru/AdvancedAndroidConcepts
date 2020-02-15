package sammy.mutahi.advancedandroid.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/*
* @Scope tells dagger this is a scope
* @Retention means this scope will be maintained in the final apk
* */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
