package com.michaelrichards.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        try {
            saveBitmap(this, null, Bitmap.CompressFormat.JPEG, "image/jpeg", "MyImage");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @NonNull
    public Uri saveBitmap(@NonNull final Context context,  final Bitmap bitmap,
                          @NonNull final Bitmap.CompressFormat format,
                          @NonNull final String mimeType,
                          @NonNull final String displayName) throws IOException {

        final ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName);
        values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM);

        final ContentResolver resolver = context.getContentResolver();
        Uri uri = null;

        try {
            final Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            uri = resolver.insert(contentUri, values);

            if (uri == null)
                throw new IOException("Failed to create new MediaStore record.");

            try (final OutputStream stream = resolver.openOutputStream(uri)) {
                if (stream == null)
                    throw new IOException("Failed to open output stream.");

                if (!bitmap.compress(format, 95, stream))
                    throw new IOException("Failed to save bitmap.");
            }

            return uri;
        }
        catch (IOException e) {

            if (uri != null) {
                // Don't leave an orphan entry in the MediaStore
                resolver.delete(uri, null, null);
            }

            throw e;
        }
    }
}