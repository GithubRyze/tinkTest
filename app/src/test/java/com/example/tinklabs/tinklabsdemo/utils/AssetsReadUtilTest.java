package com.example.tinklabs.tinklabsdemo.utils;

import android.content.Context;
import android.test.mock.MockContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by ryzeliu on 2018/5/7.
 */
public class AssetsReadUtilTest {
    Context context;
    @Before
    public void setUp(){
        context = new MockContext();
    }
    @Test
    public void readEatFileNotNull () throws IOException {
        final String rawData = AssetsReadUtil.readStringFromAssets(context, "eat");
        assertNotNull(rawData);
    }
    @Test
    public void readShopFileNotNull () throws IOException {
        final String rawData = AssetsReadUtil.readStringFromAssets(context, "shop");
        assertNotNull(rawData);
    }
    @Test
    public void readCityFileNotNull () throws IOException {
        final String rawData = AssetsReadUtil.readStringFromAssets(context, "city");
        assertNotNull(rawData);
    }

    @Test
    public void cityDataJSONArray_length() throws IOException, JSONException {
        final String rawData = AssetsReadUtil.readStringFromAssets(context, "city");
        final JSONArray expectedJSONArray = new JSONArray(rawData);

        assertEquals(6, expectedJSONArray.length());
    }

    @Test
    public void shopDataJSONArray_length() throws IOException, JSONException {
        final String rawData = AssetsReadUtil.readStringFromAssets(context, "shop");
        final JSONArray expectedJSONArray = new JSONArray(rawData);

        assertEquals(6, expectedJSONArray.length());
    }

    @Test
    public void eatDataJSONArray_length() throws IOException, JSONException {
        final String rawData = AssetsReadUtil.readStringFromAssets(context, "eat");
        final JSONArray expectedJSONArray = new JSONArray(rawData);

        assertEquals(6, expectedJSONArray.length());
    }



}