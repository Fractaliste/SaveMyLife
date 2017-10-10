package svl.raphiki.savemylife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import svl.raphiki.savemylife.external.EndPoint;

public class Eteindre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eteindre);
        fillSpinner();
    }

    private void fillSpinner() {
        Spinner pc = (Spinner) findViewById(R.id.pc);
        pc.setAdapter(getAdapter());
    }

    private SpinnerAdapter getAdapter() {
        try {
            Map<String, InetAddress> hosts = EndPoint.getAvailableHosts();

            List<String> entries = Collections.emptyList();
            entries.addAll(hosts.keySet());
            return new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
