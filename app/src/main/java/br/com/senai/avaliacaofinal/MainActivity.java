package br.com.senai.avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.com.senai.avaliacaofinal.Database.EventoDAO;
import br.com.senai.avaliacaofinal.Modelo.Evento;

public class
MainActivity extends AppCompatActivity {


    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEvento;
    private EditText itemBuscar;
    private Boolean ordem;
    private int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");
        listViewEventos = findViewById(R.id.listView_eventos);
        ordem = false;
        definirOnClickListenerListView();
        definirOnLongClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscar(this.ordem);
    }

    private void buscar(Boolean ordem) {
        itemBuscar = findViewById(R.id.editText_pesquisa);
        String item = itemBuscar.getText().toString();

        EventoDAO eventoDAO = new EventoDAO(getBaseContext());

        adapterEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.buscar(item, ordem));
        listViewEventos.setAdapter(adapterEvento);

    }

    private void definirOnClickListenerListView() {
        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEvento.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

    private void definirOnLongClickListener() {
        listViewEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Evento eventoClicadoLongo = adapterEvento.getItem(position);
                EventoDAO eventoDAO = new EventoDAO(getBaseContext());

                if(eventoDAO.excluir(eventoClicadoLongo)) {
                    Toast.makeText(getApplicationContext(), "Evento excluido!", Toast.LENGTH_LONG).show();
                    onResume();
                }else {
                    Toast.makeText(getApplicationContext(), "Erro ao excluir!", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    public void onClickEventos(View v){
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intent);
    }

    public void onClickLocal(View v) {
        Intent intent = new Intent(MainActivity.this, ListarLocalActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickBuscar(View v) {
        buscar(this.ordem);
    }

    public void onCLickBuscarOrdem(View v) {
        this.ordem = !this.ordem;
        buscar(this.ordem);
    }

}


