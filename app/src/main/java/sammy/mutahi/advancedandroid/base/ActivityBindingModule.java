package sammy.mutahi.advancedandroid.base;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import sammy.mutahi.advancedandroid.home.MainActivity;
import sammy.mutahi.advancedandroid.home.MainActivityComponent;

@Module(subcomponents = {
        MainActivityComponent.class
})
public abstract class ActivityBindingModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector(MainActivityComponent.Builder builder);
}
