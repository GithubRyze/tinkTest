package com.example.tinklabs.tinklabsdemo.utils;

import android.util.Log;

/**
 * Created by ryzeliu on 2018/5/6.
 */

public class LogUtils {
    private static final boolean isWrite = false;
    /**
     * isDebug :是用来控制，是否打印日志
     */
    private static final boolean isDeBug = true;

    /**
     * 设置时间的格式
     */
    private static final String INFORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * VERBOSE日志形式的标识符
     */
    public static final int VERBOSE = 5;
    /**
     * DEBUG日志形式的标识符
     */
    public static final int DEBUG = 4;
    /**
     * INFO日志形式的标识符
     */
    public static final int INFO = 3;
    /**
     * WARN日志形式的标识符
     */
    public static final int WARN = 2;
    /**
     * ERROR日志形式的标识符
     */
    public static final int ERROR = 1;



    /**
     * 用来输出日志的综合方法（文本内容）
     *
     * @param @param tag 日志标识
     * @param @param msg 要输出的内容
     * @param @param type 日志类型
     * @return void 返回类型
     * @throws
     */
    public static void log(String tag, String msg, int type) {
        switch (type) {
            case VERBOSE:
                v(tag, msg);// verbose等级
                break;
            case DEBUG:
                d(tag, msg);// debug等级
                break;
            case INFO:
                i(tag, msg);// info等级
                break;
            case WARN:
                w(tag, msg);// warn等级
                break;
            case ERROR:
                e(tag, msg);// error等级
                break;
            default:
                break;
        }
    }

    /**
     * verbose等级的日志输出
     *
     * @param tag
     *            日志标识
     * @param msg
     *            要输出的内容
     * @return void 返回类型
     * @throws
     */
    public static void v(String tag, String msg) {
        // 是否开启日志输出
        if (isDeBug) {
            Log.v(tag, msg);
        }

    }

    /**
     * debug等级的日志输出
     *
     * @param tag
     *            标识
     * @param msg
     *            内容
     * @return void 返回类型
     * @throws
     */
    public static void d(String tag, String msg) {
        if (isDeBug) {
            Log.d(tag, msg);
        }

    }

    /**
     * info等级的日志输出
     *
     * @param  tag 标识
     * @param  msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void i(String tag, String msg) {
        if (isDeBug) {
            Log.i(tag, msg);
        }

    }

    /**
     * warn等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void w(String tag, String msg) {
        if (isDeBug) {
            Log.w(tag, msg);
        }

    }

    /**
     * error等级的日志输出
     *
     * @param  tag 标识
     * @param  msg 内容
     * @return void 返回类型
     */
    public static void e(String tag, String msg) {
        if (isDeBug) {
            Log.e(tag, msg);
        }

    }






}
