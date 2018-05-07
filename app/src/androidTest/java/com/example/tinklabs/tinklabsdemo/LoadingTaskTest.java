package com.example.tinklabs.tinklabsdemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.tinklabs.tinklabsdemo.bean.ImageBean;
import com.example.tinklabs.tinklabsdemo.http.LoadingTask;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ryze.liu on 5/7/2018.
 */
@RunWith(AndroidJUnit4.class)
public class LoadingTaskTest {
    Context context;
    LoadingTask loadingTask;
    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
        loadingTask = new LoadingTask(context,null);
    }

    @Test
    public void parseDataNotNullTest() throws IOException, JSONException {
        assertNotNull(loadingTask.parseData(null));
        assertEquals(new ArrayList<>(), loadingTask.parseData(null));
        assertEquals(new ArrayList<>(), loadingTask.parseData(""));
        assertEquals(new ArrayList<>(), loadingTask.parseData("def fda 1255"));
    }

    private final static String CITY_JSON = "[\n" +
            "  {\n" +
            "    \"dataType\":\"ImageDescription\",\n" +
            "    \"imageUrl\":\"file:///android_asset/city_1.jpg\",\n" +
            "    \"description\":\"中华人民共和国直辖市，国家中心城市，超大城市，沪杭甬大湾区核心城市，国际经济、金融、贸易、航运、科技创新中心。隔东中国海与日本九州岛相望，南濒杭州湾\",\n" +
            "    \"title\":\"上海\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Image\",\n" +
            "    \"imageUrl\":\"file:///android_asset/city_2.jpg\"\n" +
            "  }]";
    private final static String SHOP_JSON = "[\n" +
            "  {\n" +
            "    \"dataType\":\"ImageDescription\",\n" +
            "    \"imageUrl\":\"file:///android_asset/hongkong_1.jpg\",\n" +
            "    \"description\":\"购物天堂是对香港的雅称。香港素来被中外游客称作“购物天堂”，香港店铺售卖着世界各地不同特色的货品，由国际顶级品牌至地方特色小商品。\",\n" +
            "    \"title\":\"购物天堂\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Image\",\n" +
            "    \"imageUrl\":\"file:///android_asset/hongkong_2.jpg\"\n" +
            "  }]";

    private final static String EAT_JSON = "[\n" +
            "  {\n" +
            "    \"dataType\":\"ImageDescription\",\n" +
            "    \"imageUrl\":\"file:///android_asset/eat_1.jpg\",\n" +
            "    \"description\":\"美食，顾名思义就是美味的食物，贵的有山珍海味，便宜的有街边小吃。其实美食是不分贵贱的，只要是自己喜欢的，都可以称之为美食\",\n" +
            "    \"title\":\"美食\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Image\",\n" +
            "    \"imageUrl\":\"file:///android_asset/eat_2.jpg\"\n" +
            "  }]";

    @Test
    public void parseData_is_right(){

        List<ImageBean> list_city = loadingTask.parseData(CITY_JSON);
        assertNotNull(list_city);
        assertEquals(2,list_city.size());

        List<ImageBean> list_shop = loadingTask.parseData(SHOP_JSON);
        assertNotNull(list_shop);
        assertEquals(2,list_shop.size());

        List<ImageBean> list_eat = loadingTask.parseData(EAT_JSON);
        assertNotNull(list_eat);
        assertEquals(2,list_eat.size());

    }

}