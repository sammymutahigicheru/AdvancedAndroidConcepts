package sammy.mutahi.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigatorModule {
    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator defaultScreenNavigator);
}
