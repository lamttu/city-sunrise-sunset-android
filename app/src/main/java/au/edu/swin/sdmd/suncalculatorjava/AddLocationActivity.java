package au.edu.swin.sdmd.suncalculatorjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddLocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_location);
        Log.i("debugapp", "got to the AddLocatinAct");
        Button saveButton = findViewById(R.id.btnSave);
        saveButton.setOnClickListener(saveLocationInfoListener);
    }

    private View.OnClickListener saveLocationInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean isValidated = validateForm();
            if(isValidated){
                Log.i("debug-img", "All field is validated");
                Intent returnIntent = new Intent();
                AULocation location = null;
                returnIntent.putExtra("result", location);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    };
    public AULocation getLocation() {
        EditText txtCity = findViewById(R.id.txtCity);
        String strCity = txtCity.getText().toString();
        EditText txtLatitude = findViewById(R.id.txtLatitude);
        String strLatitude = txtLatitude .getText().toString();
        Double latitude = Double.parseDouble(strLatitude);
        EditText txtLongtitude = findViewById(R.id.txtLongtitude);
        String strLongtitude = txtLatitude .getText().toString();
        Double longitude = Double.parseDouble(strLongtitude);
        EditText txtTimezone = findViewById(R.id.txtLocation);
        String strTimezone = txtLatitude .getText().toString();
        Integer timezone = Integer.parseInt(strTimezone);
        return null;
    }

    public boolean validateForm(){
        boolean isValidated = true;
        EditText txtCity = findViewById(R.id.txtCity);
        String strCity = txtCity.getText().toString();
        EditText txtLatitude = findViewById(R.id.txtLatitude);
        String strLatitude = txtLatitude .getText().toString();
        EditText txtLongtitude = findViewById(R.id.txtLongtitude);
        String strLongtitude = txtLatitude .getText().toString();
        EditText txtTimezone = findViewById(R.id.txtLocation);
        String strTimezone = txtLatitude .getText().toString();

        if(TextUtils.isEmpty(strCity)){
            txtCity.setError("City cannot be empty");
            isValidated = false;
        }
        if(TextUtils.isEmpty(strLatitude)){
            txtLatitude .setError("Latitude cannot be empty");
            isValidated = false;
        }else if(!isDouble(strLatitude)){
            txtLatitude .setError("Latitude must be a decimal number");
            isValidated = false;
        }

        if(TextUtils.isEmpty(strLongtitude)){
            txtLatitude .setError("Longtitude cannot be empty");
            isValidated = false;
        }else if(!isDouble(strLongtitude)){
            txtLatitude .setError("Longtitude must be a decimal number");
            isValidated = false;
        }

        if(TextUtils.isEmpty(strTimezone)){
            txtLatitude .setError("Timezone cannot be empty");
            isValidated = false;
        }else if(!isInteger(strTimezone)){
            txtLatitude .setError("Timezone must be an integer");
            isValidated = false;
        }

        return  isValidated;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
