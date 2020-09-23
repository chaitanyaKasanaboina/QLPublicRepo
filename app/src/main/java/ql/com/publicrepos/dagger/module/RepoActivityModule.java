package ql.com.publicrepos.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ql.com.publicrepos.view.RepoActivity;

@Module
public abstract class RepoActivityModule {
    @ContributesAndroidInjector
    abstract RepoActivity provideRepoActivity();
}
