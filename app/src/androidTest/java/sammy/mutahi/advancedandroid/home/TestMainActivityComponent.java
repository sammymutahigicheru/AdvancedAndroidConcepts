package sammy.mutahi.advancedandroid.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import sammy.mutahi.advancedandroid.di.ActivityScope;

@ActivityScope
@Subcomponent(modules = {
        TestScreenBindingModule.class,
})
public interface TestMainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

    }
}
