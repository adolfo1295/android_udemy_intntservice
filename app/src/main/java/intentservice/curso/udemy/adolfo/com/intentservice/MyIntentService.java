package intentservice.curso.udemy.adolfo.com.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Adolfo Chavez on 06/09/2017.
 */

public class MyIntentService extends IntentService {

    public static final String ACTION_PROGRESO = "adolfo.intent.action.PROGRESO";
    public static final String ACTION_FIN = "adolfo.intent.action.FIN";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int iterador = intent.getIntExtra("iteraciones",0);
        for (int i = 1; i<=iterador;i++){
            //hilo que se ejecutara cada segundo
            tareaPorSegundo();
            //comunicando el proceso
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_PROGRESO);
            broadcastIntent.putExtra("progreso",i*10);
            sendBroadcast(broadcastIntent);
        }

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_FIN);
        sendBroadcast(broadcastIntent);

    }

    private void tareaPorSegundo(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
