package sammy.mutahi.advancedandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import sammy.mutahi.advancedandroid.di.Injector;

public abstract class BaseController extends Controller {
    //allows to add more that one disposable and clear all of them with just one call
    private final CompositeDisposable disposable = new CompositeDisposable();
    private boolean injected = false;
    private Unbinder unBinder;

    public BaseController(){
        super();
    }

    public BaseController(Bundle bundle){
        super(bundle);
    }

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        //controllers are maintained accross allconfiguration changes,so this method can be called more than once.
        //this make sure we dont waste anytime injecting more than once
        if (!injected){
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }

    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(layoutRes(),container,false);
        unBinder = ButterKnife.bind(this,view);
        onViewBound(view);
        disposable.addAll(subscriptions());
        return view;
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        disposable.clear(); //allows us to reuse disposable unlike dispose()
        if(unBinder != null){
            unBinder.unbind();
            unBinder = null;
        }
    }

    //when the views are completely bound
    protected void onViewBound(View view){

    }
    /*
    * Returns an array of disposables
    * automatically handles disposals
    * */
    protected Disposable[] subscriptions(){
        return new Disposable[0];
    }

    /*
    * allows sub-class to provide its layout
    * */
    @LayoutRes
    abstract protected int layoutRes();
}
