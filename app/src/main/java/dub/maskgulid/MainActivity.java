package dub.maskgulid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
    private int[] mLocs;
    RelativeLayout mRlRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消title
        setContentView(R.layout.activity_tips);
        mRlRootView = findViewById(R.id.tips_rootview);
        Intent intent = getIntent();
        mLocs = intent.getIntArrayExtra("loc");//获取坐标
        findViewById(R.id.tips_rootview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0, 0);
            }
        });
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(0, 0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        TipsView tipsView = new TipsView(this);//将坐标传给自定义view
        tipsView.setCircleLocation(mLocs);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRlRootView.addView(tipsView, layoutParams);
    }
}
