package com.konatsup.dailystudymemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class NoteAdapter extends RealmBaseAdapter<Note> implements ListAdapter {

    public NoteAdapter(OrderedRealmCollection<Note> realmResults){
        super(realmResults);
    }

    public static class ViewHolder{
        ImageView imageView;
        TextView titleTextView;
        TextView contentTextView;

        public ViewHolder(View view){
            imageView = (ImageView)view.findViewById(R.id.imageView);
            titleTextView = (TextView) view.findViewById(R.id.titleTextView);
            contentTextView = (TextView) view.findViewById(R.id.contentTextView);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        // Viewを再利用している場合は新たにViewを作らない
        if (convertView == null) {
            convertView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(adapterData != null){
            final Note item = adapterData.get(position);
            viewHolder.imageView.setImageResource(R.drawable.hiza);
            viewHolder.titleTextView.setText(item.getTitle());
            viewHolder.contentTextView.setText(item.getContent());

            /* ClickListener系はとりあえず外しておく*/
//            viewHolder.titleTextView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                    if(viewHolder.titleTextView.getText().toString()==""){
//                        viewHolder.titleTextView.setText(item.getTitle());
//                    }else {
//                        viewHolder.titleTextView.setText("");
//                    }
//                }
//            });
//            viewHolder.contentTextView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                    if(viewHolder.contentTextView.getText().toString()==""){
//                        viewHolder.contentTextView.setText(item.getContent());
//                    }else {
//                        viewHolder.contentTextView.setText("");
//                    }
//                }
//            });
        }
        return convertView;
    }

}
