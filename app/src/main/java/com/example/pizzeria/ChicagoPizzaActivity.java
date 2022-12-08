package com.example.pizzeria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChicagoPizzaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String flavors[] = {"Build Your Own", "BBQ Chicken", "Deluxe", "Meatzza"};

    //private RecyclerView recyclerViewAvailable;
    private Spinner FlavorSpinner;
    private Button addButton;
    private Button removeButton;
    private static RadioButton smallButton;
    private static RadioButton mediumButton;
    private static RadioButton largeButton;
    private static TextView pizzaPrice;
    private TextView crustText;

    //private Pizza currentPizza;
    public static Pizza currentPizza;
    private PizzaFactory pizzaFactory;
    //private RecyclerAdapter ra;

    //private ArrayList<ToppingItem> items = new ArrayList<>();

    public ChicagoPizzaActivity(){
        pizzaFactory = new ChicagoPizza();
        currentPizza = pizzaFactory.createBuildYourOwn();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago_pizza);

        smallButton = findViewById(R.id.smallButton);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sizeSelection);
        smallButton = radioGroup.findViewById(R.id.smallButton);
        mediumButton = radioGroup.findViewById(R.id.mediumButton);
        largeButton = radioGroup.findViewById(R.id.largeButton);
        smallButton.setChecked(true);

        pizzaPrice = findViewById(R.id.pizzaPrice);
        crustText = findViewById(R.id.crustText);

        // Setting the RecyclerViewAvailable
        //recyclerViewAvailable = findViewById(R.id.recyclerViewAvailable);
       // setupAvailableItems(Topping.STRINGS);
        //ToppingItemAdapter adapter = new ToppingItemAdapter(this, items);
        //recyclerViewAvailable.setAdapter(adapter);
        //recyclerViewAvailable.setLayoutManager(new LinearLayoutManager(this));
        //recyclerViewAvailable.setAdapter(new RecyclerAdapter(Topping.STRINGS));
        //recyclerViewAvailable.set



        //Setting the flavorSpinner
        FlavorSpinner = findViewById(R.id.flavorSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, flavors);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FlavorSpinner.setAdapter(dataAdapter);
        FlavorSpinner.setOnItemSelectedListener(this);
        FlavorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hi(adapterView, view, i, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                setPrice();
            }
        });

//        recyclerViewAvailable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("myApp", "iTSLISTENING");
//                setPrice();
//            }
//        });
        pizzaFactory = new ChicagoPizza();
        currentPizza = pizzaFactory.createBuildYourOwn();



    }

    private void setupAvailableItems(ArrayList<String> toppings) {
        //items.clear();
//        for (int i = 0; i < toppings.size(); i++) {
//          //  items.add(new ToppingItem(toppings.get(i)));
//        }
    }


    public void hi(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), flavors[i], Toast.LENGTH_SHORT).show();

        String selectedFlavor = (String) adapterView.getItemAtPosition(i);
        if(selectedFlavor.equalsIgnoreCase("Build Your Own")){
            setBuildYourOwnToppings();
            currentPizza = pizzaFactory.createBuildYourOwn();
//            File file = new File("src/main/resources/img/" +
//                    "ChicagoBYOPizza.png");
//            Image image = new Image(file.toURI().toString());
//            pizzaImage.setImage(image);
        } else {
            if(selectedFlavor.equalsIgnoreCase("Deluxe")){
                currentPizza = pizzaFactory.createDeluxe();
//                File file = new File("src/main/resources/img/" +
//                        "ChicagoDeluxePizza.png");
//                Image image = new Image(file.toURI().toString());
//                pizzaImage.setImage(image);
            }
            if(selectedFlavor.equalsIgnoreCase("BBQ Chicken")){
                currentPizza = pizzaFactory.createBBQChicken();
//                File file = new File("src/main/resources/img/" +
//                        "ChicagoBBQPizza.png");
//                Image image = new Image(file.toURI().toString());
//                pizzaImage.setImage(image);
            }
            if(selectedFlavor.equalsIgnoreCase("Meatzza")){
                currentPizza = pizzaFactory.createMeatzza();
//                File file = new File("src/main/resources/img/" +
//                        "ChicagoMeatzzaPizza.png");
//                Image image = new Image(file.toURI().toString());
//                pizzaImage.setImage(image);
            }
            //setupAvailableItems(currentPizza.getToppings());
           // recyclerViewAvailable.setAdapter(new ToppingItemAdapter(this, items, true));
            //disableAddRemove();
        }
        setCrust();
        setPrice();





    }

    public void setBuildYourOwnToppings(){
       // setupAvailableItems(Topping.STRINGS);
     //   recyclerViewAvailable.setAdapter(new ToppingItemAdapter(this, items, currentPizza, this));
    }


    private void setCrust(){
        crustText.setText("Crust: " + currentPizza.getCrust().toString());
    }

//    public void setPrice(){
//        if(smallButton.isChecked())
//            currentPizza.setSize("SMALL");
//        else if(mediumButton.isChecked())
//            currentPizza.setSize("MEDIUM");
//        else if(largeButton.isChecked())
//            currentPizza.setSize("LARGE");
//        Log.d("MyApp", "from setPrice " + currentPizza.price());
//        pizzaPrice.setText("");
//        pizzaPrice.setText("" + currentPizza.price());
//    }

    public static void setPrice(){
        if(smallButton.isChecked())
            currentPizza.setSize("SMALL");
        else if(mediumButton.isChecked())
            currentPizza.setSize("MEDIUM");
        else if(largeButton.isChecked())
            currentPizza.setSize("LARGE");
        Log.d("MyApp", "from setPrice " + currentPizza.price());
        pizzaPrice.setText("");
        pizzaPrice.setText("" + currentPizza.price());
    }

//    public void addTopping(ActionEvent event){
//        if(currentTopping == null || currentTopping.equals("")){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("Error");
//            alert.setContentText("Select a topping to add.");
//            alert.showAndWait();
//        }
//
//        else if(availableToppings.getSelectionModel().getSelectedItems().
//                size() == 0 || currentTopping != null ||
//                !currentTopping.equals("")) {
//            String topping = currentTopping.substring(1,
//                    currentTopping.length() - 1);
//            availableToppings.getItems().remove(topping);
//            selectedToppings.getItems().add(topping);
//            currentPizza.add(Topping.getTopping(topping));
//            setPrice();
//        }
//
//        if(checkToppingsLimit()){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("Maximum Toppings");
//            alert.setContentText("You have added the maximum number " +
//                    "of toppings.");
//            alert.showAndWait();
//        }
//    }




    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // nothing XD
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //hi(adapterView, view, i, l);
    }

//    @Override
//    public void onItemClicked(ToppingItem ti) {
//
//    }

    // @Override
   // public void onItemClicked(ToppingItem ti) {
       // setPrice();
    //}
}