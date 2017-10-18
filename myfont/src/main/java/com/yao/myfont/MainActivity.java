package com.yao.myfont;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private VerticalMarqueeView vmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vmView = (VerticalMarqueeView)findViewById(R.id.tv_font);
        String[] datas = new String[]{
                "南海又开始动荡了"
//                ,"菲律宾到处都在肇事","这次为了一张审判废纸，菲律宾投入了多少成本呢","测试数据4","测试数据5为了长度不一样","就把这条当做测试数据吧"
        };
        vmView.color(getResources().getColor(android.R.color.holo_red_dark))
                .textSize(sp2px(this, 15))
                .datas(datas).commit();
        vmView.startScroll();
        vmView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "当前的索引为：" + vmView.getCurrentPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int sp2px(Context context, int sp){
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * density + 0.5f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //必须要调用，否则内存中会一直无限循环
        vmView.stopScroll();
    }
}
