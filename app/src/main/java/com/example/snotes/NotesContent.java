package com.example.snotes;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotesContent {
    public final List<NoteItem> ITEMS = new ArrayList<NoteItem>();
    public final Map<String, NoteItem> ITEM_MAP = new HashMap<String, NoteItem>();

    //Update with Date/Alphabetize name sorting functions

    public NotesContent(Context context){
        String[] names = DataManager.get_all_files_name(context);
        for (int i = 0; i < names.length; i++) {
            addItem(createNoteItem(i + 1, names[i], DataManager.get_data(names[i], context)));
        }
    }

    private void addItem(NoteItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private NoteItem createNoteItem(int position, String title, String content) {
        return new NoteItem(String.valueOf(position), title, content);
    }

    public static class NoteItem {
        public final String id;
        public final String title;
        public final String content;

        public NoteItem(String id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}