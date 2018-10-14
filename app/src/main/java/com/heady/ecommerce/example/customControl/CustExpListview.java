package com.heady.ecommerce.example.customControl;

import android.content.Context;
import android.widget.ExpandableListView;

/**
 * Created by ayyazkhan on 14/10/18.
 */

public class CustExpListview extends ExpandableListView {

    int intGroupPosition, intChildPosition, intGroupid;

    public CustExpListview(Context context) {
        super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(1200, MeasureSpec.AT_MOST);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(1200, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

