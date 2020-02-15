package sammy.mutahi.advancedandroid.trending;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import sammy.mutahi.advancedandroid.base.BaseController;
import sammy.mutahi.advancedandroid.di.ScreenScope;

@ScreenScope
@Subcomponent
public interface TrendingComponent extends AndroidInjector<TrendingReposController> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController>{

    }
}
