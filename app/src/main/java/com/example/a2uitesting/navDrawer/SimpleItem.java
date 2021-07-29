package com.example.a2uitesting.navDrawer;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.a2uitesting.R;

public class SimpleItem extends DrawerItem<SimpleItem.MyViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private Drawable icon;
    private String title;

    public SimpleItem(Drawable screenIcon, String title) {
        this.title = title;
        this.icon= screenIcon;
    }

    @Override
    public MyViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.single_item,parent,false); // app crash korle ekhane korte pare.
        return new MyViewHolder(v);
    }

    @Override
    public void bindViewHolder(MyViewHolder holder) {

        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);

        holder.title.setText(isChecked ? selectedItemTextTint:normalItemTextTint);
        holder.icon.setColorFilter(isChecked ? selectedItemIconTint:normalItemIconTint);

    }

    public SimpleItem withSelectedIconTint(int SelectedItemIconTint){
        this.selectedItemIconTint=selectedItemTextTint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int SelectedItemTextTint){
        this.selectedItemTextTint=selectedItemTextTint;
        return this;
    }

    public SimpleItem withIconTint(int normalItemIconTint){
        this.normalItemIconTint=normalItemIconTint;
        return this;
    }

    public SimpleItem withTextTint(int normalItemTextTint){
        this.normalItemTextTint=normalItemTextTint;
        return this;
    }

    public static class MyViewHolder extends DrawerAdapter.MyViewHolder {

        private ImageView icon;
        private TextView title;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            icon=itemView.findViewById(R.id.icon);
            title=itemView.findViewById(R.id.titleId);
        }
    }
}
