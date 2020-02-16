package sammy.mutahi.advancedandroid.networking;

import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import sammy.mutahi.advancedandroid.NetworkModule;
import sammy.mutahi.advancedandroid.model.AdapterFactory;
import sammy.mutahi.advancedandroid.model.ZonedDateTimeAdapter;

@Module(includes = NetworkModule.class)
public abstract class ServiceModule {
    @Provides
    @Singleton //used throughout the app
    static Moshi provideMoshi(){
        return new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZonedDateTimeAdapter())
                .build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Moshi moshi, Call.Factory factory, @Named("base_url") String baseUrl){
        return new Retrofit.Builder()
                .callFactory(factory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }
}
