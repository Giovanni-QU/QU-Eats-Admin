package edu.quinnipiac.ser210.adminqu_eats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ArrayList<DataModel> orderList;
    private FirebaseFirestore db;
    private CollectionReference ordersCollection;

    private MyData theData;
    private Button displayBtn;
    private ArrayList<String> fullOrderString;
    private ListView listView;
    private String[] orderNames;
    private String[] orderItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theData = new MyData();
        orderList = new ArrayList<>();






    }



    public void onClickDisplay(View view) {
        Log.v("onClickDisplay", " " +theData.getSize() );
        theData.populateFullOrder();

        fullOrderString = theData.getFullOrders();
        //orderNames = new String[theData.getSize()];
       // orderItems = new String[theData.getSize()];
       // populateFullOrderString();
        listView = (ListView) findViewById(R.id.ordersList);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, fullOrderString);

        listView.setAdapter(adapter);
    }
}
