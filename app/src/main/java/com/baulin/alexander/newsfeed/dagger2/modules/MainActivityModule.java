package com.baulin.alexander.newsfeed.dagger2.modules;

import com.baulin.alexander.newsfeed.dagger2.scopes.MainActivityScope;
import com.baulin.alexander.newsfeed.mvp.interfaces.View;
import com.baulin.alexander.newsfeed.mvp.presenter.Presenter;
import com.baulin.alexander.newsfeed.mvp.view.activities.Main;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @MainActivityScope
    com.baulin.alexander.newsfeed.mvp.interfaces.Presenter getPresenter() {
        return new Presenter();
    }
}
