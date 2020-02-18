package sammy.mutahi.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import sammy.mutahi.advancedandroid.details.RepoDetailsComponent;
import sammy.mutahi.advancedandroid.details.RepoDetailsController;
import sammy.mutahi.advancedandroid.di.ControllerKey;
import sammy.mutahi.advancedandroid.trending.TrendingComponent;
import sammy.mutahi.advancedandroid.trending.TrendingReposController;

@Module(subcomponents = {
        TrendingComponent.class,
        RepoDetailsComponent.class
})
public abstract class MainScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindingTrendingReposController(TrendingComponent.Builder builder);

    /*
    * tell main screen binder which component this component will be provided by
    * */
    @Binds
    @IntoMap
    @ControllerKey(RepoDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindingReposDetailsInjector(RepoDetailsComponent.Builder builder);
}
