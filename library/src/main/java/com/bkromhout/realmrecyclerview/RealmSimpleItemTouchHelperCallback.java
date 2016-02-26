package com.bkromhout.realmrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Implementation of {@link ItemTouchHelper.Callback} for supporting drag and drop. Adapted from:
 * https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf
 */
public class RealmSimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final boolean isDragTriggerLongPress;
    private Listener listener;

    public RealmSimpleItemTouchHelperCallback(boolean isDragTriggerLongPress) {
        this.isDragTriggerLongPress = isDragTriggerLongPress;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return isDragTriggerLongPress;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder dragging, RecyclerView.ViewHolder target) {
        return dragging.getItemViewType() == target.getItemViewType() && listener.onMove(dragging, target);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // Never called.
    }

    public interface Listener {
        boolean onMove(RecyclerView.ViewHolder dragging, RecyclerView.ViewHolder target);
    }
}
