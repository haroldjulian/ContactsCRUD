package aplicacioncontroles.contactscrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NuevoContactoActivity extends AppCompatActivity {

        EditText txtContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        txtContacto = (EditText) findViewById(R.id.txtContacto);

    }
    public void guardar(View v){
        String nombres = txtContacto.getText().toString();
        Intent i = new Intent();
        i.putExtra("nombres", nombres);
        setResult(RESULT_OK,i);
        finish();

    }
}
