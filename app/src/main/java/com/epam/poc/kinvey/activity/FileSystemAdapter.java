package com.epam.poc.kinvey.activity;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.poc.kinvey.SyncApplication;
import com.epam.poc.kinvey.R;
import com.epam.poc.kinvey.events.FileEvent;
import com.epam.poc.kinvey.events.FolderEvent;
import com.epam.poc.kinvey.model.File;
import com.epam.poc.kinvey.model.FileSystem;
import com.epam.poc.kinvey.model.Folder;

import java.util.List;

class FileSystemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FOLDER_ITEM = 1;
    private static final int FILE_ITEM = 2;

    private FileSystem mFileSystem;

    FileSystemAdapter(FileSystem fileSystem) {
        mFileSystem = fileSystem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == FOLDER_ITEM) {
            viewHolder = FolderViewHolder.createViewHolder(parent);
        } else {
            viewHolder = FileViewHolder.createViewHolder(parent);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final int itemType = getItemViewType(position);

        if (itemType == FOLDER_ITEM) {
            final Folder folder = mFileSystem.getFolders().get(position);
            FolderViewHolder holder = (FolderViewHolder)viewHolder;
            holder.txtViewTitle.setText(folder.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SyncApplication.busPost(new FolderEvent(folder.getName(), folder.getSyncpointId(), folder.getFolderId()));
                }
            });
        } else if (itemType == FILE_ITEM) {
            List<File> files = mFileSystem.getFiles();
            int newPosition = position - mFileSystem.getFolders().size();
            final File file = files.get(newPosition);
            FileViewHolder holder = (FileViewHolder)viewHolder;
            holder.txtViewTitle.setText(file.getFilename());
            if (file.getFileType() == File.Type.IMAGE) {
                holder.imgViewIcon.setImageResource(R.mipmap.image_file_icon);
            } else if (file.getFileType() == File.Type.TEXT) {
                holder.imgViewIcon.setImageResource(R.mipmap.text_file_icon);
            } else {
                holder.imgViewIcon.setImageResource(R.mipmap.unknown_file_icon);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SyncApplication.busPost(new FileEvent(file.getFileType(), file.getFilename(), file.getSyncpointId(), file.getLatestVersionId()));
                }
            });
        }
    }

    private static class FolderViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitle;

        static RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
            View itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.folder_list_item, parent, false);
            return new FolderViewHolder(itemLayoutView);
        }

        private FolderViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.itemTitle);
        }
    }

    private static class FileViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitle;
        ImageView imgViewIcon;

        static RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
            View itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.file_list_item, parent, false);
            return new FileViewHolder(itemLayoutView);
        }

        private FileViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.itemTitle);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.itemIcon);
        }
    }

    @Override
    public int getItemCount() {
        return mFileSystem.getFolders().size() + mFileSystem.getFiles().size();
    }

    @Override
    public int getItemViewType(int position) {
        int size = mFileSystem.getFolders().size();
        if (size > 0 && size > position) {
            return FOLDER_ITEM;
        } else {
            return FILE_ITEM;
        }
    }
}
