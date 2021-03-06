package com.example.a2uitesting.navDrawer;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder> {

    private List<DrawerItem> items;
    private Map<Class<? extends  DrawerItem>,Integer> viewTypes;
    private SparseArray<DrawerItem> holderFactories;

    private OnItemSelectedListener listener;

    public DrawerAdapter( List<DrawerItem> items){
        this.items=items;
        this.viewTypes=new HashMap<>();
        this.holderFactories=new SparseArray<>();
        processViewTypes();

    }

    private void processViewTypes() {

        int type=0;

        for (DrawerItem item : items){
            if(! viewTypes.containsKey(item.getClass())){
                viewTypes.put(item.getClass(),type);
                holderFactories.put(type,item);
                type++;
            }
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        MyViewHolder holder=holderFactories.get(viewType).createViewHolder(parent);
        holder.drawerAdapter=this;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  DrawerAdapter.MyViewHolder holder, int position) {
        items.get(position).bindViewHolder(holder);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.get(items.get(position).getClass());
    }


    public void setSelected(int position){
        DrawerItem newChecked =items.get(position);
        if(!newChecked.isSelectable()){
            return;
        }

        for (int i=0;i<items.size();i++){
            DrawerItem item=items.get(i);
            if (item.isChecked()){
                item.setChecked(false);
                notifyItemChanged(i);
                break;
            }
        }

        newChecked.setChecked(true);
        notifyItemChanged(position);

        if(listener != null){
            listener.onItemSelected(position);
        }
    }

    public void setListener(OnItemSelectedListener listener){
        this.listener=listener;
    }

    public interface OnItemSelectedListener{
        void onItemSelected(int position);
    }
    public static abstract class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private DrawerAdapter drawerAdapter;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        public void onClick(View V){
            drawerAdapter.setSelected(getAdapterPosition());
        }
    }

}
