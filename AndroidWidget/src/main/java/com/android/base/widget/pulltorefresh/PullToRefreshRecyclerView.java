package com.android.base.widget.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;

import com.android.base.widget.R;
import com.android.base.widget.recycler.ExRecyclerView;

/**
 * Created by Administrator on 2015/11/24 0024.
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<ExRecyclerView> {

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override protected ExRecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        ExRecyclerView recyclerView = new ExRecyclerView(context, attrs);
        recyclerView.setId(R.id.recyclerview);
        return recyclerView;
    }

    public void setAdapter(ExRecyclerView.Adapter adapter){
        mRefreshableView.setAdapter(adapter);
    }

    public void setLayoutManager(ExRecyclerView.LayoutManager layoutManager){
        mRefreshableView.setLayoutManager(layoutManager);
    }

    @Override protected boolean isReadyForPullEnd() {
        int lastVisiblePostion =
                mRefreshableView.getChildAdapterPosition(mRefreshableView.getChildAt(mRefreshableView.getChildCount() - 1));
        if (lastVisiblePostion >= mRefreshableView.getAdapter().getItemCount() - 1) {
            return mRefreshableView.getChildAt(mRefreshableView.getChildCount() - 1).getBottom()
                    <= mRefreshableView.getBottom();
        }
        return false;
    }

    @Override protected boolean isReadyForPullStart() {
        if (mRefreshableView.getChildCount() <= 0) return true;
        int firstVisiblePosition = mRefreshableView.getChildAdapterPosition(mRefreshableView.getChildAt(0));
        if (firstVisiblePosition == 0) {
            return mRefreshableView.getChildAt(0).getTop() == mRefreshableView.getPaddingTop();
        } else {
            return false;
        }
    }
}
