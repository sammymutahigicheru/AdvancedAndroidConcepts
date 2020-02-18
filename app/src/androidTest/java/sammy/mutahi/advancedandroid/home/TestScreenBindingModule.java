package sammy.mutahi.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import sammy.mutahi.advancedandroid.di.ControllerKey;
import sammy.mutahi.advancedandroid.trending.TrendingComponent;
import sammy.mutahi.advancedandroid.trending.TrendingReposController;

@Module(subcomponents = {
        TrendingComponent.class
})
public abstract class TestScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindingTrendingReposController(TrendingComponent.Builder builder);
}
