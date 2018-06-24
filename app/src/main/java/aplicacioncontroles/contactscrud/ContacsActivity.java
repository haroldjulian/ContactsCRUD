package aplicacioncontroles.contactscrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContacsActivity extends AppCompatActivity {

    ListView lstContacts;
    ArrayAdapter adapterContacts;
    String[] source = new String[]{"Victor","Criss","Daniel","Daniel2","Martin","Harold","Luciano","Anthony"};

    ArrayList<String> contacts = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacs);

        resetContacts();
        lstContacts = (ListView) findViewById(R.id.lstContacts);
        //adapterContacts = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,source);
        adapterContacts = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        lstContacts.setAdapter(adapterContacts);

        registerForContextMenu(lstContacts);  //menu contextual opciones de cada opcion
    }

    private void resetContacts() {
        contacts.clear();
        for (int i =0; i < source.length; i++){
            contacts.add(source[i]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_nuevo){
           // Toast.makeText(this,"Adicionar un alumno mas",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,NuevoContactoActivity.class);
            startActivityForResult(i,10001);  //una subactividad y 10001 es un identificador cualquiera que uno coloca a libre alvedrio
        }else if (item.getItemId() == R.id.action_reset)
        {
            resetContacts();
            adapterContacts.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10001){   //pregunto de que pedido viene
            if (resultCode == RESULT_OK){
                if (data.hasExtra("nombres")){
                    String nombres = data.getStringExtra("nombres");
                    contacts.add(nombres);  //adiciona uno nuevo
                    adapterContacts.notifyDataSetChanged();  //refresca el adaptador muy necesario
                }
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_contact,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if (item.getItemId()==R.id.action_editar)
        {
    //        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    //        int position = info.position;

        //    Toast.makeText(this,"Editando a...."+ source[position],Toast.LENGTH_SHORT).show();
            String nombres = contacts.get(position);
            Intent i = new Intent(this, EditarActivity.class);
            i.putExtra("nombres",nombres);
            i.putExtra("position",position);
            startActivityForResult(i,10002);

        }else if (item.getItemId() == R.id.action_eliminar){
   //         AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    //        int position = info.position;

          //  Toast.makeText(this,"Te vamos a extrañar :"+ source[position],Toast.LENGTH_LONG).show();
            contacts.remove(position);
            adapterContacts.notifyDataSetChanged();
        }

        return super.onContextItemSelected(item);
    }
}
