
package com.ml.dayornight.theme.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

/**
 * @Package com.ml.dayornight.theme.view
 * @ClassName: ThemeRelativeLayout
 * @Description: 可设置夜间模式Button
 * @author malong
 * @date May 18, 2015 6:24:13 PM
 */
public class ThemeButton extends Button {

    private Drawable nightBackground, dayBackground;// 设置日间和夜间的背景（可以是颜色值，图片，或selector）
    private float dayAlpha, nightAlpha;// 设置日间和夜间的透明度
    private ColorStateList dayTextColor;
    private ColorStateList nightTextColor;

    /**
     * @Description: 构造函数,初始化日间夜间模式
     * @param @param context
     * @author malong
     * @date May 18, 20156:25:12 PM
     */
    public ThemeButton(Context context) {
        super(context);
        init(context, null);
    }

    /**
     * @Description: 构造函数,初始化日间夜间模式
     * @param @param context
     * @param @param attrs
     * @author malong
     * @date May 18, 20156:25:12 PM
     */
    public ThemeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * @Description: 构造函数,初始化日间夜间模式
     * @param @param context
     * @param @param attrs
     * @param @param defStyle
     * @author malong
     * @date May 18, 20159:18:13 PM
     */
    public ThemeButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /**
     * @Description: 初始化日间夜间模式
     * @return void
     * @author malong
     * @date May 18, 20156:25:12 PM
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.day_or_night);
        if (a != null) {
            dayAlpha = a.getFloat(R.styleable.day_or_night_dayAlpha, 1.0f);
            nightAlpha = a.getFloat(R.styleable.day_or_night_nightAlpha, 1.0f);
            dayBackground = a.getDrawable(R.styleable.day_or_night_dayBackground);
            nightBackground = a.getDrawable(R.styleable.day_or_night_nightBackground);
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.day_or_night_dayTextColor:
                        dayTextColor = a.getColorStateList(attr);
                        break;

                    case R.styleable.day_or_night_nightTextColor:
                        nightTextColor = a.getColorStateList(attr);
                        break;
                }
            }
        }
        a.recycle();
        if (ThemeInit.mViewObservable != null) {
            ThemeInit.mViewObservable.addObserver(new ViewObserver());
        }
        changeBackground();
    }

    /**
     * @Description: 改变控件的背景色
     * @param context2
     * @return void
     * @author malong
     * @date May 18, 20156:25:12 PM
     */
    public void changeBackground() {
        if (ThemeInit.sharedPreferences != null) {
            if (ThemeInit.isDay()) {// 日间
                if (dayBackground != null) {
                    this.setBackground(dayBackground);
                }
                if (dayAlpha != 1.0f) {
                    this.setAlpha(dayAlpha);
                }
                if (dayTextColor != null) {
                    this.setTextColor(dayTextColor);
                }
            } else {// 夜间
                if (nightBackground != null) {
                    this.setBackground(nightBackground);
                }
                if (nightAlpha != 1.0f) {
                    this.setAlpha(nightAlpha);
                }
                if (nightTextColor != null) {
                    this.setTextColor(nightTextColor);
                }
            }
        }
    }

    /**
     * @Package com.ccdt.news.ui.view
     * @ClassName: ViewObserver
     * @Description: 观察者
     * @author malong
     * @date May 18, 20156:25:12 PM
     */
    public class ViewObserver implements Observer {// 观察者
        @Override
        public void update(Observable observable, Object data) {
            changeBackground();
        }
    }

}
