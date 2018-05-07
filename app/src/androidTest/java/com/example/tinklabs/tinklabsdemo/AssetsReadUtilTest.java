package com.example.tinklabs.tinklabsdemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.tinklabs.tinklabsdemo.utils.AssetsReadUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AssetsReadUtilTest {
    public Context context;
    @Before
    public void setUp(){
        context = InstrumentationRegistry.getTargetContext();
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
