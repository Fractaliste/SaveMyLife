package svl.raphiki.savemylife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class Eteindre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eteindre);
        fillSpinner();
    }

    private void fillSpinner() {
        Spinner pc = (Spinner) findViewById(R.id.pc);
        SpinnerAdapter adapteur = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, new String[]{"Test", "Test2"});
        pc.setAdapter(adapteur);
    }
}
