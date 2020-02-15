package sammy.mutahi.advancedandroid.base;



import javax.inject.Singleton;

import dagger.Component;

/*
*
* singleton means this class will be active within the application lifecycle
* Handles the injection
* */
@Singleton
@Component(modules = {ApplicationModule.class,
ActivityBindingModule.class})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);
}
