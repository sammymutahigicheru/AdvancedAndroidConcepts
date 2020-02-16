package sammy.mutahi.advancedandroid.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import sammy.mutahi.advancedandroid.di.ActivityScope;
import sammy.mutahi.advancedandroid.ui.NavigatorModule;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigatorModule.class
}) //will be build on top of our application component
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

    }
}
