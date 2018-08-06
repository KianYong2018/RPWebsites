package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCategory;
    Spinner spnSub;
    Button btnGo;

    ArrayList<String> al;
    ArrayAdapter<String> aa;

    String[] strSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCategory = findViewById(R.id.spinner);
        spnSub = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGO);

        al = new ArrayList<>();
        strSub = getResources().getStringArray(R.array.Sub_category1);
        al.addAll(Arrays.asList(strSub));
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,al);
        spnSub.setAdapter(aa);

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        al.clear();
                        strSub = getResources().getStringArray(R.array.Sub_category1);
                        al.addAll(Arrays.asList(strSub));
                        break;
                    case 1:
                        al.clear();
                        strSub = getResources().getStringArray(R.array.Sub_category2);
                        al.addAll(Arrays.asList(strSub));
                        break;
                }
                aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[][] sites = {
                        {
                            "http://www.rp.edu.sg",
                                "https://www.rp.edu.sg/student-life",
                        },
                        {
                         "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"
                        }
                };
                String url = sites[spnCategory.getSelectedItemPosition()][spnSub.getSelectedItemPosition()];

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor prefedit = sharedPreferences.edit();

                prefedit.putInt("cat",spnCategory.getSelectedItemPosition());
                prefedit.putInt("subcat",spnSub.getSelectedItemPosition());
                prefedit.commit();

                Intent intent = new Intent(getBaseContext(),Main2Activity.class);
                intent.putExtra("URL",url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences retrieve = PreferenceManager.getDefaultSharedPreferences(this);
        int rcat = retrieve.getInt("cat",3);
        int rsubcat = retrieve.getInt("subcat",3);

        spnCategory.setSelection(rcat);
        spnSub.setSelection(rsubcat);
        aa.notifyDataSetChanged();
    }
}