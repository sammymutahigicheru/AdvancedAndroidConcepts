package sammy.mutahi.advancedandroid.base;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import sammy.mutahi.advancedandroid.home.MainActivity;
import sammy.mutahi.advancedandroid.home.TestMainActivityComponent;

@Module(subcomponents = {
        TestMainActivityComponent.class,
})
public abstract class TestActivityBindingModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector<? extends MainActivity> bindMainActivityInjector(TestMainActivityComponent.Builder builder);
}
