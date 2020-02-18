package sammy.mutahi.advancedandroid.base;

import androidx.appcompat.app.ActionBar;

import javax.inject.Singleton;

import dagger.Component;
import sammy.mutahi.advancedandroid.data.TestRepoServiceModule;
import sammy.mutahi.advancedandroid.networking.ServiceModule;
import sammy.mutahi.advancedandroid.ui.NavigatorModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigatorModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

}
