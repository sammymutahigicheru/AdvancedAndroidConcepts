package sammy.mutahi.advancedandroid.base;

import android.app.Application;

import javax.inject.Inject;

import dagger.android.DaggerApplication;
import sammy.mutahi.advancedandroid.di.ActivityInjector;

public class MyApplication extends Application {
    //get activity Injector
    @Inject
    ActivityInjector activityInjector;
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
