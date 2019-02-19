package ethered.wallpapershots;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class ImageCropSave extends AppCompatActivity {

    private CropImageView mCropImageView;
    private Uri mCropImageUri;
    String Url;
    private Button button;
    //git pushing trail
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_crop_save);


        mCropImageView = (CropImageView) findViewById(R.id.CropImageView);

        button = (Button)findViewById(R.id.cropbutton);
        Bundle extras = getIntent().getExtras();
        Url = extras.getString("url");
        GlideApp
                .with(getApplicationContext())
                .load(Url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Bitmap bitmap = ((BitmapDrawable)resource).getBitmap();

                        mCropImageView.setImageBitmap(bitmap);
                    }
                });



        ; }
            /**
             * Crop the image and set it back to the  cropping view.
             */
            public void onCropImageClick(View view) {
                Bitmap cropped =  mCropImageView.getCroppedImage(500, 500);
                if (cropped != null)
                    setwallpaper(cropped);
            }


    private void setwallpaper(Bitmap bitmap) {
        try {
            WallpaperManager mywallapapermanager = WallpaperManager.getInstance(this);

            mywallapapermanager.setBitmap(bitmap);
            Toast.makeText(getApplicationContext(),"Wallpaper SET",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
            @Override
            public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
                if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mCropImageView.setImageUriAsync(mCropImageUri);
                } else {
                    Toast.makeText(this, "Required permissions are not granted", Toast.LENGTH_LONG).show();
                }
            }


        }

