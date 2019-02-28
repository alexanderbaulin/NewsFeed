package com.baulin.alexander.newsfeed;


import com.baulin.alexander.newsfeed.mvp.interfaces.Presenter;
import com.baulin.alexander.newsfeed.mvp.view.activities.Main;
import com.baulin.alexander.newsfeed.mvp.view.adapter.PostAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ViewTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Presenter presenter;

    private @InjectMocks Main activity;

    @Test
    public void testOnRefresh() {
        activity.onRefresh();
        verify(presenter).getPosts(false);
    }

}
