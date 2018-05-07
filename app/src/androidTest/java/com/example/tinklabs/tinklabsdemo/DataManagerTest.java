package com.example.tinklabs.tinklabsdemo;

import com.example.tinklabs.tinklabsdemo.bean.ImageBean;
import com.example.tinklabs.tinklabsdemo.http.DataManager;
import com.example.tinklabs.tinklabsdemo.utils.DataType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ryze.liu on 5/7/2018.
 */
public class DataManagerTest {
    DataManager dataManager;
    @Before
    public void setUp() throws Exception {
        dataManager = new DataManager();
    }

    @Test
    public void addEatDataTest(){
        List<ImageBean> list_eat = new ArrayList<>();
        list_eat.add(new ImageBean("eat_imageUrl"));
        list_eat.add(new ImageBean("eat_imageUrl_1"));
        list_eat.add(new ImageBean("eat_imageUrl_2"));
        dataManager.addData(DataType.DATA_TYPE_EAT,list_eat);
        assertEquals(3, dataManager.getItems(DataType.DATA_TYPE_EAT).size());
    }

    @Test
    public void addCityDataTest(){
        List<ImageBean> list_eat = new ArrayList<>();
        list_eat.add(new ImageBean("city_imageUrl"));
        list_eat.add(new ImageBean("city_imageUrl_1"));
        list_eat.add(new ImageBean("city_imageUrl_2"));
        dataManager.addData(DataType.DATA_TYPE_GUIDE,list_eat);
        assertEquals(3, dataManager.getItems(DataType.DATA_TYPE_GUIDE).size());
    }

    @Test
    public void addShopDataTest(){
        List<ImageBean> list_eat = new ArrayList<>();
        list_eat.add(new ImageBean("shop_imageUrl"));
        list_eat.add(new ImageBean("shop_imageUrl_1"));
        list_eat.add(new ImageBean("shop_imageUrl_2"));
        dataManager.addData(DataType.DATA_TYPE_SHOP,list_eat);
        assertEquals(3, dataManager.getItems(DataType.DATA_TYPE_SHOP).size());
    }
}