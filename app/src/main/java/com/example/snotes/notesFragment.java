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
import com.example.snotes.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class notesFragment extends Fragment implements MynotesRecyclerViewAdapter.OnNoteListener {
    private FragmentItemListBinding binding;
    public static final String TAG = "com.example.snotes.MOIST";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public notesFragment() {
    }

    // TODO: Customize parameter initialization
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

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        recyclerView.setAdapter(new MynotesRecyclerViewAdapter(DummyContent.ITEMS, this));
        return view;
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(getActivity(), EditorActivity.class);
        String message = DummyContent.ITEMS.get(position).id + "\n" +DummyContent.ITEMS.get(position).content;
        intent.putExtra(TAG, message);
        startActivity(intent);
    }
}