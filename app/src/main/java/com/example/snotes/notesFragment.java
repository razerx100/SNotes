package com.example.snotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.snotes.databinding.FragmentItemListBinding;

public class notesFragment extends Fragment implements MynotesRecyclerViewAdapter.OnNoteListener {
    private FragmentItemListBinding binding;
    public static final String TAG = "com.example.snotes.MOISTURE";
    public static final String EDIT_TAG = "com.example.snotes.DROUGHT";
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private NotesContent notesContent;
    public notesFragment() {
    }

    @SuppressWarnings("unused")
    public static notesFragment newInstance(int columnCount) {
        notesFragment fragment = new notesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        notesContent = new NotesContent(getActivity());
        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        recyclerView.setAdapter(new MynotesRecyclerViewAdapter(notesContent.ITEMS, this));
        return view;
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(getActivity(), EditorActivity.class);
        String[] title_and_content = { notesContent.ITEMS.get(position).title, notesContent.ITEMS.get(position).content };
        intent.putExtra(TAG, title_and_content);
        intent.putExtra(EDIT_TAG, true);
        startActivity(intent);
    }
}