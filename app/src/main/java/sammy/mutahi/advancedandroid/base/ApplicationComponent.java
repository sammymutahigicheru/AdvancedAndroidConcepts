package sammy.mutahi.advancedandroid.base;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/*
*
* singleton means this class will be active within the application lifecycle
* Handles the injection
* */
@Singleton
@Component(modules = {Application.class})
public interface ApplicationComponent {

}
