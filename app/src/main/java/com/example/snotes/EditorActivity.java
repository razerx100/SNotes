package com.example.snotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.snotes.databinding.ActivityEditorBinding;

public class EditorActivity extends AppCompatActivity {
    private ActivityEditorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Toolbar toolbar = binding.toolbarEditor;
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        accept_incoming_data();
    }

    public void accept_incoming_data(){
        Intent intent = getIntent();
        if(intent.hasExtra(notesFragment.TAG)) {
            String message = intent.getStringExtra(notesFragment.TAG);
            String title = message.split("\n")[0];
            String content = message.split("\n")[1];
            TextView content_editor = binding.textEditor;
            TextView title_editor = binding.titleEditor;
            content_editor.setText(content);
            title_editor.setText(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_toolbar_buttons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.done_action){
            //Done Button Action
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    public  void delete_note(View view){
        //Delete opened note
    }
}