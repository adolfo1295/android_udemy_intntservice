package intentservice.curso.udemy.adolfo.com.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(MyIntentService.ACTION_PROGRESO);
        mIntentFilter.addAction(MyIntentService.ACTION_FIN);
        ProgressReceiver mReceiver = new ProgressReceiver();
        registerReceiver(mReceiver,mIntentFilter);
    }

    public void start(View view) {
        Intent msgIntent = new Intent(MainActivity.this, MyIntentService.class);
        msgIntent.putExtra("iteraciones", 10);
        startService(msgIntent);
    }

    private class ProgressReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MyIntentService.ACTION_PROGRESO)) {
                int prog = intent.getIntExtra("progreso", 0);
                Log.d("main","progreso: "+prog);
            }
            else if(intent.getAction().equals(MyIntentService.ACTION_FIN)) {
                Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
