package ethered.wallpapershots;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;
import com.marshalchen.ultimaterecyclerview.expanx.Util.parent;

import java.io.InputStream;
import java.util.List;



public  class ImageModelRecyclerAdapter extends RecyclerView.Adapter<ImageModelRecyclerAdapter.ViewHolder>  {


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        private TextView photographersname;
        private TextView imageDesc;
        private TextView imageTitle;

        private ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.itemimage1);
            imageTitle = (TextView)itemView.findViewById(R.id.imagetitle);


            }

    }

    public List<String> ImageModelList1;
    public List<ImageModel> imageModelList;
    public Context context;


    public ImageModelRecyclerAdapter(Context applicationContext, List<ImageModel> ImageModelList){
        context = applicationContext;
        imageModelList = ImageModelList;
    }

    @NonNull
    @Override
    public ImageModelRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.imglay2, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ImageModelRecyclerAdapter.ViewHolder holder, int position) {
        final ImageModel currentimage = imageModelList.get(position);
        Log.d("Firestore1",currentimage.getImage_url());
        Log.d("Firestore1",currentimage.getUser_id());
        holder.imageTitle.setText(currentimage.getUser_id());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context,Fullimage2.class);

                i.putExtra("url",currentimage.getImage_url());
                context.startActivity(i);

            }

    });


        GlideApp.with(context)
                .load(currentimage.getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imageModelList.size();
    }



}