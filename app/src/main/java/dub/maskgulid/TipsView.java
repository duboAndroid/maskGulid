package dub.maskgulid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Bob on 2017/9/12.
 */
public class TipsView extends FrameLayout {
    private final Context mContext;
    private int[] mCircleLocation;

    public TipsView(Context context) {
        this(context, null);
    }

    public TipsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.parseColor("#7f000000"));//半透明底色
    }

    public void setCircleLocation(int[] location) {
        this.mCircleLocation = location;
        invalidate();//重新绘画
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCircleLocation != null) {
            // 掏空一个圆形
            Paint paintarc = new Paint(Paint.ANTI_ALIAS_FLAG);
            PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
            paintarc.setXfermode(porterDuffXfermode);
            paintarc.setAntiAlias(true);

            RectF rectF = new RectF(mCircleLocation[0], mCircleLocation[1], mCircleLocation[2], mCircleLocation[3]);
            canvas.drawArc(rectF, 0, 360, true, paintarc); //画虚线
            Paint paintdashed = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintdashed.setStyle(Paint.Style.STROKE);
            paintdashed.setColor(Color.WHITE);
            paintdashed.setStrokeWidth(1);
            PathEffect pathEffect = new DashPathEffect(new float[]{10, 10}, 0);
            paintdashed.setPathEffect(pathEffect);
            canvas.drawArc(rectF, 0, 360, true, paintdashed);
            //画指引图片
            Paint paintImage = new Paint(Paint.ANTI_ALIAS_FLAG);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int def = dip2px(mContext,20);
            int left = mCircleLocation[0] - width + def;
            int top = mCircleLocation[1] - height;
            canvas.drawBitmap(bitmap, left, top, paintImage);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
