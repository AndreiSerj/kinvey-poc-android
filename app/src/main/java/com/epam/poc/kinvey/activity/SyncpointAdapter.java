package com.epam.poc.kinvey.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.epam.poc.kinvey.SyncApplication;
import com.epam.poc.kinvey.R;
import com.epam.poc.kinvey.events.SyncpointEvent;
import com.epam.poc.kinvey.model.Syncpoint;

class SyncpointAdapter extends RecyclerView.Adapter<SyncpointAdapter.SyncpointViewHolder> {

    private List<Syncpoint> mSyncpoints;

    SyncpointAdapter(List<Syncpoint> syncpoints) {
        mSyncpoints = syncpoints;
    }

    @Override
    public SyncpointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SyncpointAdapter.SyncpointViewHolder.createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(SyncpointViewHolder viewHolder, int position) {
        final Syncpoint syncpoint = mSyncpoints.get(position);

        viewHolder.txtViewTitle.setText(syncpoint.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SyncApplication.busPost(
                      new SyncpointEvent(syncpoint.getName(), syncpoint.getId(), syncpoint.getRootFolderId()));
            }
        });
    }

    static class SyncpointViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitle;

        static SyncpointViewHolder createViewHolder(ViewGroup parent) {
            View itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.syncpoint_list_item, parent, false);
            return new SyncpointAdapter.SyncpointViewHolder(itemLayoutView);
        }

        private SyncpointViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.itemTitle);
        }
    }

    @Override
    public int getItemCount() {
        return mSyncpoints.size();
    }

}
