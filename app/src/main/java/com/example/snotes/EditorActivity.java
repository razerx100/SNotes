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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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
            String[] title_and_content = intent.getStringArrayExtra(notesFragment.TAG);
            TextView content_editor = binding.textEditor;
            TextView title_editor = binding.titleEditor;
            content_editor.setText(title_and_content[1]);
            title_editor.setText(title_and_content[0]);
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
            String title = binding.titleEditor.getText().toString();
            String content = binding.textEditor.getText().toString();
            if(title.isEmpty()){
                Snackbar snackbar = Snackbar.make(binding.getRoot(), "Please provide a title", BaseTransientBottomBar.LENGTH_SHORT);
                snackbar.show();
            }
            else if(DataManager.isNameExist(title, this)){
                Snackbar snackbar = Snackbar.make(binding.getRoot(), "Note with same title already exists!", BaseTransientBottomBar.LENGTH_SHORT);
                snackbar.show();
            }
            else {
                DataManager.save_data(content, title, this);
                go_back_to_main();
            }
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void delete_note(View view){
        //Delete opened note
    }

    private void go_back_to_main(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}