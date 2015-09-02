package com.anthony.deeplinkdemo.SectionFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anthony.deeplinkdemo.R;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private Context context;
    private RecyclerViewItemClickListener listener;

    public SectionAdapter(Context context, RecyclerViewItemClickListener listener) {

        this.listener = listener;
        this.context = context;

    }

    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_items, null);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getLayoutPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SectionAdapter.ViewHolder viewHolder, int position) {

        viewHolder.txtItem.setText(context.getResources().getString(R.string.item) + ": " + String.valueOf(position) );

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtItem;

        public ViewHolder(View view) {
            super(view);

            this.txtItem = (TextView) view.findViewById(R.id.txtItem);

        }

        @Override
        public void onClick(View view) {

        }
    }

}