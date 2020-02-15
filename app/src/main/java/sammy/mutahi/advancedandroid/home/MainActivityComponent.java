package sammy.mutahi.advancedandroid.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import sammy.mutahi.advancedandroid.di.ActivityScope;

@ActivityScope
@Subcomponent //will be build on top of our application component
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

    }
}
