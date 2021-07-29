package com.example.a2uitesting.navDrawer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class SpaceItem extends DrawerItem<SpaceItem.MyViewHolder> {

    private int spaceDp;

    public SpaceItem(int spaceDp) {
        this.spaceDp = spaceDp;
    }

    @Override
    public MyViewHolder createViewHolder(ViewGroup parent) {
        Context c=parent.getContext();
        View view=new View(c);
        int height= (int) (c.getResources().getDisplayMetrics().density*spaceDp);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));

        return new MyViewHolder(view);
    }

    @Override
    public void bindViewHolder(MyViewHolder holder) {

    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    public class MyViewHolder extends DrawerAdapter.MyViewHolder{
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
