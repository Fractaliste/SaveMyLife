package svl.raphiki.savemylife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startEteindrePcActivity(View v) {
        Intent intent = new Intent(this, Eteindre.class);
        startActivity(intent);
    }
}
