package br.com.senai.avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.senai.avaliacaofinal.Database.LocalDAO;
import br.com.senai.avaliacaofinal.Modelo.Local;

public class ListarLocalActivity extends AppCompatActivity {

    private int id = 0;
    private ListView listViewLocal;
    private ArrayAdapter<Local> adapterlocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Listar Local");
        setContentView(R.layout.activity_listar_local);
        listViewLocal = findViewById(R.id.listView_local);
        definirOnClickListenerListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterlocal = new ArrayAdapter<Local>(ListarLocalActivity.this,
                android.R.layout.simple_list_item_1,
                localDAO.listar());
        listViewLocal.setAdapter(adapterlocal);
    }

    private void definirOnClickListenerListView() {
        listViewLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Local localClicado = adapterlocal.getItem(position);
                Intent intent = new Intent(ListarLocalActivity.this, CadastroLocalActivity.class);
                intent.putExtra("localEdicao", localClicado);
                startActivity(intent);
            }
        });

        listViewLocal.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Local localClicadoLongo = adapterlocal.getItem(position);

                for (int i = 0; i < adapterlocal.getCount(); i++){
                    Local evento = adapterlocal.getItem(i);
                    if(evento.getId() == localClicadoLongo.getId()) {
                        adapterlocal.remove(evento);
                        Toast.makeText(getApplicationContext(), "local excluido!", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                return true;
            }
        });
    }

    public void onClickNovoLocal(View v){
        Intent intent = new Intent(ListarLocalActivity.this, CadastroLocalActivity.class);
        startActivity(intent);
    }

    public void onClickEventos(View v) {
        Intent intent = new Intent(ListarLocalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}