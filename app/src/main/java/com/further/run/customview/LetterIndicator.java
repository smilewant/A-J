package com.further.run.customview;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Hukuan
 * 2018/7/26.
 */
public class LetterIndicator extends AppCompatTextView {
    private String titleName;
    public LetterIndicator(Context context) {
        super(context);
    }

    public LetterIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LetterIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LetterIndicator name(String newName) {
        this.titleName = newName;
        return this;
    }

    public void text() {
        setText("bb");
    }

    public static class TagLabelBuilder {
        private String titleName;

        public TagLabelBuilder name(String newName) {
            this.titleName = newName;
            return this;
        }

        public LetterIndicator create(Context context) {
            LetterIndicator letterIndicator = new LetterIndicator(context);
            letterIndicator.text();
            return letterIndicator;
        }

    }
}
