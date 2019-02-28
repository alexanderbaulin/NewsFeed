package com.baulin.alexander.newsfeed;

import android.support.test.runner.AndroidJUnit4;

import com.baulin.alexander.newsfeed.mvp.model.Data;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.Image;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ModelTest {

    @Test
    public void testData() {
        Data data = new Data();
        List<NewsItem> list = new LinkedList<>();

        NewsItem item = new NewsItem();
        item.setHeadLine("TestHeadLine");
        item.setStory("TestStory");
        item.setDateLine("TestDateLine");
        Image image = new Image();
        image.setPhoto("TestPhoto");
        item.setImage(image);

        list.add(item);
        data.rewrite(list);

        list = data.read();
        NewsItem resultItem = list.get(0);
        boolean result =
                list.size() == 1 &&

                item.getHeadLine().equals(resultItem.getHeadLine()) &&
                item.getStory().equals(resultItem.getStory()) &&
                item.getDateLine().equals(resultItem.getDateLine()) &&
                item.getImage().getPhoto().equals(resultItem.getImage().getPhoto());

        assertTrue(result);
    }

}
