
package com.ml.dayornightdome;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.ml.dayornight.theme.view.ThemeInit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ThemeInit(getApplicationContext());// 初始化夜间模式
        setContentView(R.layout.activity_main);
        CheckBox mCheckBox = (CheckBox) findViewById(R.id.setting_night_check_box);
        mCheckBox.setChecked(!ThemeInit.isDay());
        mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ThemeInit.saveBoolean(ThemeInit.THEME_MODE, !isChecked);
                if (ThemeInit.mViewObservable != null) {
                    ThemeInit.mViewObservable.observableChange();
                }
            }
        });
    }

}
