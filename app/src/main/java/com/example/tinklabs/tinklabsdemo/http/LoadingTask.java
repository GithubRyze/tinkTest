package com.example.tinklabs.tinklabsdemo.http;


import android.content.Context;
import android.os.AsyncTask;

import com.example.tinklabs.tinklabsdemo.bean.ImageBean;
import com.example.tinklabs.tinklabsdemo.bean.ImageDescriptionBean;
import com.example.tinklabs.tinklabsdemo.utils.AssetsReadUtil;
import com.example.tinklabs.tinklabsdemo.utils.Constant;
import com.example.tinklabs.tinklabsdemo.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryzeliu on 2018/5/5.
 */

public class LoadingTask extends AsyncTask<String, Void, List<ImageBean>> {

    private static final String TAG = "LoadingTask";
    private final WeakReference<Context> mContext;
    private final WeakReference<CallBack> mCallBack;

    interface CallBack{

        void onLoadingFinished(List<ImageBean> list);
    }

    public LoadingTask(Context context,CallBack callBack){
        this.mContext = new WeakReference<Context>(context);
        this.mCallBack = new WeakReference<CallBack>(callBack);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<ImageBean> doInBackground(String... strings) {
        List<ImageBean> list = new ArrayList<>();
        Context context = mContext.get();
        if (context == null) {
            return list;
        }
        try {
            String  data = loadDataFromAsset(context,strings);
            list = parseData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    protected void onPostExecute(List<ImageBean> imageBeans) {
        super.onPostExecute(imageBeans);
        CallBack callBack = mCallBack.get();
        if (callBack != null){
            callBack.onLoadingFinished(imageBeans);
        }
    }

    /**
     *
     * @param context
     * @param strings
     * @return
     * @throws IOException
     * load data from Asset
     */

    private String loadDataFromAsset(Context context, String... strings) throws IOException {
        String fileName = strings[0];
        LogUtils.d(TAG,"filename ::" + fileName);
        return AssetsReadUtil.readStringFromAssets(context,fileName);
    }


    /**
     *
     * @param data
     * @return
     * @throws IOException
     * @throws JSONException
     * parse json data
     */
    public List<ImageBean> parseData(String data){

        List<ImageBean> rtn = new ArrayList<>();
        try {
            if (data == null) {
                return rtn;
            }
            final JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                final String type = jsonObject.getString(Constant.KEY_TYPE);
                if (Constant.KEY_IMAGE_TYPE.equals(type)) {
                    rtn.add(new ImageBean(jsonObject.getString(Constant.KEY_IMAGE_URL)));
                } else if (Constant.KEY_IMAGE_DESCRIPTION_TYPE.equals(type)) {
                    rtn.add(new ImageDescriptionBean(jsonObject.getString(Constant.KEY_IMAGE_URL),
                            jsonObject.getString(Constant.KEY_IMAGE_TITLE), jsonObject.getString(Constant.KEY_IMAGE_DESCRIPTION)));
                } else {
                    throw new UnsupportedOperationException();
                }
            }
        }catch (JSONException e){
            LogUtils.d(TAG, e.getMessage());
            return rtn;
        }
        return rtn;
    }





}
