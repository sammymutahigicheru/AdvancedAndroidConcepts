package sammy.mutahi.advancedandroid.base;

import android.app.Application;

import dagger.android.DaggerApplication;

public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
