package com.softcustomer.perfectfit.widget.support;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;


public class ExpandableTextView extends android.support.v7.widget.AppCompatTextView {

    private final String readMoreText = " ...more";
    private final int readMoreColor = Color.parseColor("#6D6D72");
    private boolean isExpanded = false;
    private int _maxLines = 4;
    private CharSequence originalText;

    public ExpandableTextView(Context context) {
        super(context);
        init(context);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);

                truncateText();
            }
        });
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        if (originalText == null) {
            originalText = text;
        }
    }

    @Override
    public int getMaxLines() {
        return _maxLines;
    }

    @Override
    public void setMaxLines(int maxLines) {
        _maxLines = maxLines;
    }

    public void truncateText() {
        isExpanded = false;
        int maxLines = _maxLines;
        String text = getText().toString();

        if (getLineCount() >= maxLines) {

            int lineEndIndex = getLayout().getLineEnd(maxLines - 1);

            String truncatedText = getText().subSequence(0, lineEndIndex - readMoreText.length() + 1) + readMoreText;

            Spannable spannable = new SpannableString(truncatedText);
            spannable.setSpan(new ForegroundColorSpan(readMoreColor), truncatedText.length() - readMoreText.length(), truncatedText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            setText(spannable, TextView.BufferType.SPANNABLE);

            super.setMaxLines(_maxLines);
        }
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void expandText() {
        isExpanded = true;
        setText(originalText);
        super.setMaxLines(Integer.MAX_VALUE);
    }

    public void reset() {
        originalText = null;
    }
}