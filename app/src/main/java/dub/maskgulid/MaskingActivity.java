package dub.maskgulid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

/**
 * Created by Bob on 2017/9/12.
 */

public class MaskingActivity extends Activity {

    private AppCompatButton mBtMasking;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masking);
        mBtMasking = findViewById(R.id.mask_bt);
        showMask();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //onCreat中直接去测量view的大小是测不出来的 所以在这个demo中我延时500ms去测量
    // 实际使用一般在网络加载完成后去测量view的大小然后去显示蒙版
    private void showMask() {
        mBtMasking.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // mHeight = getSupportActionBar().getHeight();
                        int left = mBtMasking.getLeft();
                        int right = mBtMasking.getRight();
                        int top = mBtMasking.getTop() + mHeight;
                        int bottom = mBtMasking.getBottom() + mHeight;
                        int loc[] = {left, top, right, bottom};
                        Intent intent = new Intent(MaskingActivity.this, MainActivity.class);
                        intent.putExtra("loc", loc);
                        startActivity(intent);
                    }
                });
            }
        }, 500);
    }
}
