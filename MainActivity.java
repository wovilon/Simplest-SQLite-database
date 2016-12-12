package wovilon.sqlite_simplest_base;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button btnRet;
    TextView t1,t2,t3;
    boolean disp=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRet=(Button)findViewById(R.id.button);
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
        t3=(TextView)findViewById(R.id.textView4);

        final SQLiteDatabase db=openOrCreateDatabase("MyDB", MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MyTable(LastName VARCHAR, FirstName VARCHAR, Age INT(3))");
        db.execSQL("INSERT INTO MyTable VALUES ('Pitt','Bradd',35);" );

        btnRet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (!disp){
                    Cursor c=db.rawQuery("SELECT * FROM MyTable",null);
                    c.moveToFirst();

                    t1.setText(c.getString(c.getColumnIndex("LastName")));
                    t2.setText(c.getString(c.getColumnIndex("FirstName")));
                    t3.setText(c.getString(c.getColumnIndex("Age")));

                    db.close();
                    disp=true;
                }
            }

        });
    }


}
