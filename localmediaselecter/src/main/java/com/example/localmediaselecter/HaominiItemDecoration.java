package com.example.localmediaselecter;

import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhiyicx on 2017/5/26.
 */

public class HaominiItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) { //网格
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            if (((GridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.HORIZONTAL) {//水平
                //待绘制
            } else { //垂直
                if (parent.getChildLayoutPosition(view) % spanCount == 0) {
                    outRect.set(0, getDimen(view, R.dimen.dimen_2dp), 0, 0);
                } else {
                    outRect.set(getDimen(view, R.dimen.dimen_2dp),
                            getDimen(view, R.dimen.dimen_2dp), 0, 0);
                }
            }
        } else if (layoutManager instanceof LinearLayoutManager) { //线性
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                //待绘制
            } else {
                outRect.set(0, getDimen(view, R.dimen.dimen_2dp), 0, 0);
            }
        } else { //瀑布
            //待绘制
        }
    }

    public int getDimen(View v, @DimenRes int id) {
        return (int) v.getResources().getDimension(id);
    }
}
