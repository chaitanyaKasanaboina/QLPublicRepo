package ql.com.publicrepos.app;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import ql.com.publicrepos.dagger.component.DaggerQLRepoAppComponent;

public class QLRepoApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> DispatchingFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerQLRepoAppComponent.builder().build().inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return DispatchingFragmentInjector;
    }
}
