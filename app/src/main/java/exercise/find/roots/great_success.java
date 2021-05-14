package exercise.find.roots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class great_success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great_succes);

        Intent createdMe = getIntent();


        String res = createdMe.getStringExtra("res");
        String time = createdMe.getStringExtra("time");
        TextView succusstext = findViewById(R.id.roots);
        succusstext.setText(res);
        TextView timetext = findViewById(R.id.time);
        timetext.setText("time was "+time);


    }
}