package sammy.mutahi.advancedandroid.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application application;
    ApplicationModule(Application application){

        this.application = application;
    }
    @Provides
    public Context provideApplicationContext(){
        return  application;
    }
}
