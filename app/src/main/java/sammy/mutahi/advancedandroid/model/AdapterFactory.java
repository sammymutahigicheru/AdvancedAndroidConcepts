package sammy.mutahi.advancedandroid.model;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/*
* used when creating moshi objects
* */
@MoshiAdapterFactory
public abstract class AdapterFactory implements JsonAdapter.Factory {
    public static JsonAdapter.Factory create(){
        return new AutoValueMoshi_AdapterFactory();
    }
}
