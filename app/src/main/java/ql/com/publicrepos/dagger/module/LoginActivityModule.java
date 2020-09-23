package ql.com.publicrepos.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ql.com.publicrepos.view.LoginActivity;

@Module
public abstract class LoginActivityModule {
    @ContributesAndroidInjector
    abstract LoginActivity provideLoginActivity();
}
