package sammy.mutahi.advancedandroid.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import sammy.mutahi.advancedandroid.di.Injector;

public abstract class BaseActivity extends AppCompatActivity {
    public static final String INSTANCE_ID_KEY = "instance_id";
    public String instaceId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null){
            instaceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        }else{
            instaceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY,instaceId);
    }
    public String getInstanceId(){
        return instaceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()){
            Injector.clearComponent(this);
        }
    }
}
