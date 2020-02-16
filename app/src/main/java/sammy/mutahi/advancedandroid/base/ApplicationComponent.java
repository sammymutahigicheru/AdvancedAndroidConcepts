package sammy.mutahi.advancedandroid.base;


import javax.inject.Singleton;

import dagger.Component;
import sammy.mutahi.advancedandroid.data.ReposServiceModule;
import sammy.mutahi.advancedandroid.networking.ServiceModule;

/*
 *
 * singleton means this class will be active within the application lifecycle
 * Handles the injection
 * */
@Singleton
@Component(modules = {ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        ReposServiceModule.class})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);
}
