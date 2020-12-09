package com.yzjminus.utils;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yzjdev.utils.FileUtil;
import com.yzjdev.utils.LogUtil;

public class MainActivity extends AppCompatActivity {

    private TextView contentView;
    private EditText contentEdit, pathNameEdit;
    String pathName, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.Debug();

        contentView = findViewById(R.id.display);
        contentEdit = findViewById(R.id.content);
        pathNameEdit = findViewById(R.id.pathName);
    }

    public void write(View view) {
        pathName = pathNameEdit.getText().toString();
        content = contentEdit.getText().toString();
        FileUtil.write(this, pathName, content);
    }

    public void read(View view) {
        content = FileUtil.read(this, pathNameEdit.getText().toString(),"");
        contentView.setText(content);
    }

    public void copy(View view) {
        EditText sourceEdit = findViewById(R.id.source);
        EditText destEdit = findViewById(R.id.dest);
        FileUtil.copy(this, sourceEdit.getText().toString(), destEdit.getText().toString());
    }

    public void delete(View view) {
        pathName = pathNameEdit.getText().toString();
//        boolean z = FileUtil.delete(pathName, true);
//        LogUtil.d(z);
        String a = FileUtil.getPath(pathName);
        LogUtil.d(a);
        a=FileUtil.getPath(this, pathName);
        LogUtil.d("context: "+a);
    }
}