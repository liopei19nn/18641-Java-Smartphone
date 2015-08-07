package ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import communication.proxyClient;
import model.nutrition.Apple;
import model.nutrition.Banana;
import model.nutrition.Beef;
import model.nutrition.Broccoli;
import model.nutrition.Chicken;
import model.nutrition.Cucumber;
import model.nutrition.Egg;
import model.nutrition.Fish;
import model.nutrition.Milk;
import model.nutrition.Nutrition;
import model.person.Person;
import model.nutrition.Tomato;
import tongyunl.teamupfatdown.R;

/**
 * This class represents the user interface in which user could
 * add nutrition.
 */
public class ChooseNutrition extends Activity {
    private Spinner nutritionSpinner;
    private EditText quantityEditText;
    private Button submitButton;
    private String nutritionName;
    private String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_choosenutrition);
        TextView title1 = (TextView) findViewById(R.id.ChooseNutrition_textView0_1);
        TextView title2 = (TextView) findViewById(R.id.ChooseNutrition_textView1_1);
        TextView title3 = (TextView) findViewById(R.id.ChooseNutrition_textView2_1);
        nutritionSpinner = (Spinner) findViewById(R.id.ChooseNutrition_nutrition);
        quantityEditText = (EditText) findViewById(R.id.ChooseNutrition_quantityEditText);
        submitButton = (Button) findViewById(R.id.ChooseNutrition_button);

        String fontPath0 = "roboto/Roboto-ThinItalic.ttf";
        String fontPath1 = "roboto/Roboto-LightItalic.ttf";
        String fontPath2 = "roboto/Roboto-Light.ttf";
        String fontPath3 = "droid-sans/DroidSans.ttf";

        Typeface tf0 = Typeface.createFromAsset(getAssets(), fontPath0);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), fontPath3);

        title1.setTypeface(tf0);
        title2.setTypeface(tf1);
        title3.setTypeface(tf1);
        quantityEditText.setTypeface(tf2);
        submitButton.setTypeface(tf3);

        MySpinnerAdapter adapter = new MySpinnerAdapter(getApplicationContext(),
                R.layout.ui_dropdownitem,
                Arrays.asList(getResources().getStringArray(R.array.ChooseNutrition_Nutrition)));
        nutritionSpinner.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nutritionName = nutritionSpinner.getSelectedItem().toString();
                quantity = quantityEditText.getText().toString();
                if (quantity.length() == 0) {
                    sendToast("Missing quantity");
                } else if (Integer.parseInt(quantity) == 0) {
                    sendToast("Quantity can not be 0");
                } else if (isConnect()) {
                    AsyncTask<Object, Object, Object> saveNutritionTask =
                            new AsyncTask<Object, Object, Object>() {
                                @Override
                                protected Object doInBackground(Object... params) {
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Object result) {
                                    if (updateNutrition()) {
                                        quantityEditText.setText("");
                                        nutritionSpinner.setSelection(0);
                                        sendToast("Add nutrition successfully");
                                    }
                                }
                            };
                    saveNutritionTask.execute((Object[]) null);
                }

            }
        });
    }

    /**
     * Update the nutrition list of user.
     */
    private boolean updateNutrition() {
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        Nutrition nutrition = null;
        if (nutritionName.equals("Apple")) {
            nutrition = new Apple(nutritionName, quantity);
        } else if (nutritionName.equals("Banana")) {
            nutrition = new Banana(nutritionName, quantity);
        } else if (nutritionName.equals("Beef")) {
            nutrition = new Beef(nutritionName, quantity);
        } else if (nutritionName.equals("Broccoli")) {
            nutrition = new Broccoli(nutritionName, quantity);
        } else if (nutritionName.equals("Chicken")) {
            nutrition = new Chicken(nutritionName, quantity);
        } else if (nutritionName.equals("Cucumber")) {
            nutrition = new Cucumber(nutritionName, quantity);
        } else if (nutritionName.equals("Egg")) {
            nutrition = new Egg(nutritionName, quantity);
        } else if (nutritionName.equals("Fish")) {
            nutrition = new Fish(nutritionName, quantity);
        } else if (nutritionName.equals("Milk")) {
            nutrition = new Milk(nutritionName, quantity);
        } else {
            nutrition = new Tomato(nutritionName, quantity);
        }
        Person.get(this).updateCal(nutrition);
        proxyClient proxy = new proxyClient(Person.get(this));
        String result = proxy.addOneToDB();
        if (result.equals("No Internet Access")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if the device connect to the Internet.
     *
     * @return
     */
    private boolean isConnect() {
        proxyClient proxy = new proxyClient(new Person());
        String result = proxy.getOneFromDB();
        if (result.equals("No Internet Access")) {
            sendToast(result);
            return false;
        }
        return true;
    }

    /**
     * Send the toast.
     *
     * @param s
     */
    private void sendToast(String s) {
        Toast.makeText(ChooseNutrition.this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Inner class: Spinner Adapter
     */
    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        String fontPath = "roboto/Roboto-Light.ttf";
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(tf);
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(tf);
            return view;
        }
    }
}
