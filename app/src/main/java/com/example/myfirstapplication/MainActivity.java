 package com.example.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


 public class MainActivity extends Activity {

    public int contador;
    TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResultado = (TextView)findViewById(R.id.contadorTexto);
        contador = 0;
        eventoTeclado teclado = new eventoTeclado();
        EditText nReseteo=(EditText)findViewById(R.id.nReseteo);

        nReseteo.setOnEditorActionListener(teclado);
    }

    class eventoTeclado implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

            if(i == EditorInfo.IME_ACTION_DONE){
                reseteaContador(null);
            }

            return false;
        }
    }

    public void incrementaContador (View vista){

        contador++;
        textResultado.setText(""+ contador);
        //mostrarResultado();

    }

    public void decrementaContador (View vista){

        contador--;
        if (contador<0){
            CheckBox negativos = (CheckBox)findViewById(R.id.negativos);

            if(!negativos.isChecked()){
                contador = 0;
            }
        }

        textResultado.setText(""+contador);
        //mostrarResultado();

    }

    public void reseteaContador (View vista){

        //contador = 0;
        EditText numero_reset = (EditText)findViewById(R.id.nReseteo);

        try {
            contador = Integer.parseInt(numero_reset.getText().toString());
        }catch (Exception e){
            contador=0;
        }
        numero_reset.setText("");

        textResultado.setText(""+contador);

        InputMethodManager miTeclado =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        miTeclado.hideSoftInputFromWindow(numero_reset.getWindowToken(),0);

        //mostrarResultado();
    }


    /*public void mostrarResultado(){

        TextView textResultado =(TextView)findViewById(R.id.contadorTexto);
        textResultado.setText("" + contador);
    }*/
}
