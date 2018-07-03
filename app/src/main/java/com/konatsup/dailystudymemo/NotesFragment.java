package com.konatsup.dailystudymemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;

public class NotesFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";
    private Realm realm;
    private ListView listView;
    private NoteAdapter noteAdapter;

    public static NotesFragment newInstance(@ColorRes int IdRes) {
        NotesFragment frag = new NotesFragment();
        Bundle b = new Bundle();
        b.putInt(BACKGROUND_COLOR, IdRes);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, null);

        listView = (ListView) view.findViewById(R.id.listView);
        RealmResults<Note> result = realm.where(Note.class).findAll();
        noteAdapter = new NoteAdapter(result);
        listView.setAdapter(noteAdapter);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_main_linearlayout);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        return view;
    }
}
