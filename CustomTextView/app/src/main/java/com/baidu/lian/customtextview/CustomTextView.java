package com.baidu.lian.customtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by lianguanjun on 2015/6/10.
 */
public class CustomTextView extends View {
    private String mText = null;
    private int mTextColor = 0;
    private int mTextSize = 0;
    private Paint mPaint;
    private Rect mBound;

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTextView , defStyle, 0);

//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch(attr) {
                case R.styleable.CustomTextView_text:
                    mText = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTextView_textColor:
                    mTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTextView_textSize:
                    mTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    // getDimensionPixelSize: 获取尺寸属性的值
                    // applyDimension:
                    break;
            }

        }
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);

    }

    /**
     *
     * @param widthMeasureSpec
     *  onMesure 的作用就是CustomTextView告诉父控件自己需要多大的空间，两个参数的作用是：父控件告诉CustomTextView能提供多大的空间
     *  widthMesureSpec 中的width和widthMode都是由CustomTextView的layout_width决定的，若是layout_width为match_parent，则width为父控件
     *  的width，widthMode为EXACTLY； 若是layout_width为具体数值， 则width为具体数值，widthMode为EXACTLY； 若是layout_width为wrap_content，
     *  则width为父控件的width，widthMode为AT_MOST, 所以将自定义控件的layout_width设为wrap_content而没有重写onMesure方法时，自定义控件将会
     *  撑满父控件。
     *  最后调用setMeasuredDimension(width, height)就是告诉父控件自己需要多大的空间
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTextColor);
        canvas.drawText(mText, getWidth() - mBound.width(), (getHeight() - mBound.height())/2 + mBound.height(), mPaint);
        canvas.drawText(mText, getWidth() - mBound.width(), (getHeight() - mBound.height())/2 + mBound.height(), mPaint);
    }
}
