package ethered.wallpapershots;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Mitul Champaneri on 3/3/2018.
 */
//GITHUB
public class Fullimage2 extends AppCompatActivity {

    private Button button;
    private PhotoView photoView;
    String Url;
    Bitmap bmap;
    String Url1,Url2,Url3;
    Context context;
    String getUrl;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage2);

        context = getApplicationContext();
        button = (Button)findViewById(R.id.button);
        PhotoView photoView = (PhotoView) findViewById(R.id.fullimg);

        RequestOptions options = new RequestOptions();
        Bundle extras = getIntent().getExtras();
        Url = extras.getString("url");
        Log.d("Fullimage",Url);
        RequestOptions requestOptions  = new RequestOptions();
        GlideApp
                .with(context)
                .load(Url)
                .apply(requestOptions)
                .into(photoView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getbitmap();

            }
        });
    }
    private void getbitmap(){
        Intent i = new Intent(this,ImageCropSave.class);
        i.putExtra("url",Url);

        startActivity(i);


    }


}
