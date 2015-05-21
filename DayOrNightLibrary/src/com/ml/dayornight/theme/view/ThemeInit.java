
package com.ml.dayornight.theme.view;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Observable;
/**
 * @Package com.ml.dayornight.theme.view
 * @ClassName: ThemeInit
 * @Description: 初始化夜间模式
 * @author malong
 * @date May 20, 2015 6:32:57 PM
 */
public class ThemeInit {
    public static ViewObservable mViewObservable;
    public static SharedPreferences sharedPreferences;
    public static final String THEME_MODE = "isDay";

    public ThemeInit(Context context) {
        mViewObservable = new ViewObservable();
        sharedPreferences = context.getSharedPreferences("day_or_night", Context.MODE_PRIVATE);
    }

    /**
     * @Package com.ml.dayornight.theme.view
     * @ClassName: ViewObservable
     * @Description: 创建一个被观察者
     * @author malong
     * @date May 20, 2015 3:41:14 PM
     */
    public class ViewObservable extends Observable {
        public void observableChange() {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * @Description: 保存夜间模式状态
     * @param key
     * @param value
     * @return void
     * @author malong
     * @date May 20, 20153:37:51 PM
     */
    public static void saveBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * @Description: 读取夜间模式状态
     * @param key
     * @param defValue
     * @return
     * @return Boolean
     * @author malong
     * @date May 20, 20153:40:39 PM
     */
    public static Boolean getBoolean(String key, Boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static Boolean isDay() {
        return sharedPreferences.getBoolean(THEME_MODE, true);
    }
}
