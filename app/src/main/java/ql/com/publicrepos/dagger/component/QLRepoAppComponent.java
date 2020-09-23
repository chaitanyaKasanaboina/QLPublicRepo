package ql.com.publicrepos.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ql.com.publicrepos.app.QLRepoApplication;
import ql.com.publicrepos.dagger.module.LoginActivityModule;
import ql.com.publicrepos.dagger.module.RepoActivityModule;
import ql.com.publicrepos.dagger.module.RepoServiceModule;
import ql.com.publicrepos.dagger.module.ViewModelFactoryModule;
import ql.com.publicrepos.dagger.module.ViewModelModules;

@Singleton
@Component(
        modules = {
                LoginActivityModule.class,
                RepoActivityModule.class,
                RepoServiceModule.class, //change name
                ViewModelModules.class,
                ViewModelFactoryModule.class,
                AndroidInjectionModule.class,
                AndroidSupportInjectionModule.class,
        }
)
public interface QLRepoAppComponent extends AndroidInjector<QLRepoApplication> {
}
