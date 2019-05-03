package edu.quinnipiac.ser210.adminqu_eats;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyData {
    private final CollectionReference ordersDB;
    private FirebaseFirestore db;
    private CollectionReference ordersCollection;
    private ArrayList<String> orderNamesList;
    private ArrayList<String> orderItemsList;
    private ArrayList<String> fullOrderList;
    public MyData(){
    orderNamesList = new ArrayList<>();
    orderItemsList = new ArrayList<>();
    fullOrderList = new ArrayList<>();
    db = FirebaseFirestore.getInstance();
    ordersDB = db.collection("orders");
     ordersDB.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                List<String> group = (List<String>) document.get("Items");
                                Log.d("msg", document.getId() + " => " + document.getData().get("name"));
                                //must pull data using method
                                populateOrderNamesList(document.getData().get("name").toString());
                                populateOrderItemsList(group);
                            }
                        } else {
                            Log.d("msg", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void populateOrderItemsList(List<String> items) {
        String str = " ";
       for(int i=0;i<items.size();i++){
            str += ", " + items.get(i);


       }
        orderItemsList.add(str);
        Log.v("MyData", " "+ str);
    }

    private void populateOrderNamesList(String name) {
        orderNamesList.add(name);

    }
    public int getSize(){
        return orderNamesList.size();
    }
    public ArrayList<String> getNameList(){
        return orderNamesList;
    }
    public ArrayList<String> getOrderItemsList(){
        return orderItemsList;
    }

    public void populateFullOrder() {
        for(int i = 0;i<getSize();i++){
            fullOrderList.add(orderNamesList.get(i) + orderItemsList.get(i));
        }

    }
    public ArrayList<String> getFullOrders(){
        return fullOrderList;
    }
}
