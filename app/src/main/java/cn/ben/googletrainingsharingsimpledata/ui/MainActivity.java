package cn.ben.googletrainingsharingsimpledata.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

import cn.ben.googletrainingsharingsimpledata.R;
import cn.ben.googletrainingsharingsimpledata.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public View.OnClickListener sendTextContentOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendTextContent();
        }
    };
    public View.OnClickListener sendBinaryContentOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendBinaryContent();
        }
    };
    public View.OnClickListener sendMultipleOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendMultiple();
        }
    };
    public View.OnClickListener startShareActionActivityOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startShareActionActivity();
        }
    };

    private void startShareActionActivity() {
        startActivity(new Intent(this, ShareActionActivity.class));
    }

    private void sendMultiple() {
        ArrayList<Uri> imageUris = new ArrayList<>();
        File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/1.png");
        if (!file1.exists()) return;
        File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/2.png");
        if (!file2.exists()) return;
        imageUris.add(Uri.fromFile(file1)); // Add your image URIs here
        imageUris.add(Uri.fromFile(file2));

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "Share images to.."));
    }

    private void sendBinaryContent() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/1.png");
        if (!file.exists()) return;

        Uri uriToImage = Uri.fromFile(file);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
    }

    private void sendTextContent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
    }
}
