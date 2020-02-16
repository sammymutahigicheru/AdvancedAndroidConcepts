package sammy.mutahi.advancedandroid.home;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bluelinelabs.conductor.Controller;

import sammy.mutahi.advancedandroid.R;
import sammy.mutahi.advancedandroid.base.BaseActivity;
import sammy.mutahi.advancedandroid.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}
