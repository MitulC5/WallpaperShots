package ethered.wallpapershots;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.OnClick;

public class ImagesView extends AppCompatActivity {
    private static final String TAG = "Firestore";
    RecyclerView rv;
    FirebaseFirestore mFireStore;
    List<ImageModel> ImageModelList;
    List<String> ImageModelList1;
    ImageModelRecyclerAdapter imageModelRecyclerViewAdapter,imageModelRecyclerViewAdapter1;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        );
        setContentView(R.layout.activity_images_view);
        rv =(RecyclerView)findViewById(R.id.rv);
        mFireStore = FirebaseFirestore.getInstance();
        ImageModelList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);

        Log.d(TAG,"Error!");
        imageModelRecyclerViewAdapter = new ImageModelRecyclerAdapter(ImagesView.this,ImageModelList);
        imageModelRecyclerViewAdapter1 = new ImageModelRecyclerAdapter(getApplicationContext(),ImageModelList);
        rv.setAdapter(imageModelRecyclerViewAdapter);
        rv.setHasFixedSize(true);
        GridLayoutManager mGrid = new GridLayoutManager(this,2);
        StaggeredGridLayoutManager mLayoutManager  = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(mGrid);

        /*swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //swipeto refresh not working
                //to-do
                mFireStore.collection("imagedata").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {


                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG,"Error");

                        for(QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots) {
                            String URLs = (String) documentSnapshot.get("image_url");
                            Log.d(TAG, "URLs of the Image :" + URLs);
                            ImageModel imageModel = documentSnapshot.toObject(ImageModel.class);
                            ImageModelList.add(imageModel);

                        }rv.setAdapter(imageModelRecyclerViewAdapter);

                        Log.d(TAG,"urls from the SwipeRefresh" +ImageModelList.get(1).getImage_url().toString());

                        onitemsloadcomplete();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                });

            }

            void onitemsloadcomplete(){

                Log.d(TAG,"Error during item upload!");
// kya kiya

                swipeRefreshLayout.setEnabled(false);
            }
        });
*/
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                shuffleItems();
            }
        });

        mFireStore.collection("imagedata").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {


            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d(TAG,"Error getting image data!");

                for(QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots) {
                    String URLs = (String) documentSnapshot.get("image_url");
                    Log.d(TAG, "URLs of the Image :" + URLs);
                    ImageModel imageModel = documentSnapshot.toObject(ImageModel.class);
                    ImageModelList.add(imageModel);

                }rv.setAdapter(imageModelRecyclerViewAdapter);

                Log.d(TAG,ImageModelList.get(1).getImage_url().toString());

                //might crash if no data
            }
        });

    }
    public void shuffleItems() {
        // shuffle the ArrayList's items and set the adapter
        Collections.shuffle(ImageModelList, new Random(System.currentTimeMillis()));
       // Collections.shuffle(personImages, new Random(System.currentTimeMillis()));
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        //CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personNames, personImages);
        //recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        imageModelRecyclerViewAdapter = new ImageModelRecyclerAdapter(ImagesView.this,ImageModelList);
        imageModelRecyclerViewAdapter1 = new ImageModelRecyclerAdapter(getApplicationContext(),ImageModelList);
        rv.setAdapter(imageModelRecyclerViewAdapter);
    }

}
