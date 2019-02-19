package com.baulin.alexander.newsfeed.dagger2.components;

import com.baulin.alexander.newsfeed.dagger2.modules.MainActivityModule;
import com.baulin.alexander.newsfeed.dagger2.scopes.MainActivityScope;
import com.baulin.alexander.newsfeed.mvp.view.activities.Main;

import dagger.Component;

@MainActivityScope
@Component(modules = { MainActivityModule.class }, dependencies = AppComponent.class)
public interface MainActivityComponent {
    void injectMainActivity(Main mainActivity);
}