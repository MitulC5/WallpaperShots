package ethered.wallpapershots;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.takusemba.cropme.CropView;
import com.takusemba.cropme.OnCropListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class cropactivity extends AppCompatActivity {
    CropView cropView;
Context context;
String urlink ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap bitmap;
        cropView = (CropView)findViewById(R.id.cropView);
        setContentView(R.layout.activity_cropactivity);
        Intent intent = getIntent();

        urlink = intent.getStringExtra("urlnextactivity");
        Uri myUri = Uri.parse(urlink);
        Toast.makeText(this,""+myUri,Toast.LENGTH_LONG).show();
        Log.d("URL", myUri+"");
     cropView.setUri(myUri);
//        cropView.setBitmap(bitmap);
        cropView.crop(new OnCropListener() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                // do something
                try {
                    WallpaperManager mywallapapermanager = WallpaperManager.getInstance(getApplicationContext());
                    mywallapapermanager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(),"Wallpaper SET",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure() {

            }
        });


    }
}
