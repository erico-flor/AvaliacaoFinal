package br.com.senai.avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.senai.avaliacaofinal.Database.EventoDAO;
import br.com.senai.avaliacaofinal.Database.LocalDAO;
import br.com.senai.avaliacaofinal.Modelo.Evento;
import br.com.senai.avaliacaofinal.Modelo.Local;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;
    private Spinner spinnerLocal;
    private ArrayAdapter<Local> locaisAdapter;
    private EditText editTextNome;
    private EditText editTextData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Eventos");
        spinnerLocal = findViewById(R.id.spinner_local);
        editTextNome = findViewById(R.id.editText_nome);
        editTextData = findViewById(R.id.editText_data);
        carregarLocais();
        carregarEvento();
    }

    public void carregarLocais(){
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        locaisAdapter = new ArrayAdapter<Local>(CadastroEventoActivity.this,
                android.R.layout.simple_spinner_item,
                localDAO.listar());
        spinnerLocal.setAdapter(locaisAdapter);
    }

    public  void carregarEvento(){
        Intent intent = getIntent();
        if ( intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null){
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData());
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocal.setSelection(posicaoLocal);
            id = evento.getId();
        }
    }

    private int obterPosicaoLocal(Local local) {
        for( int posicao = 0; posicao < locaisAdapter.getCount(); posicao++) {
            if(locaisAdapter.getItem(posicao).getId() == local.getId()) {
                return posicao;
            }
        }
        return 0;
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();

        int posicaoLocal = spinnerLocal.getSelectedItemPosition();
        Local local = (Local) locaisAdapter.getItem(posicaoLocal);
        Evento evento = new Evento(id,nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvar(evento);
        if (salvou){
            finish();
        } else {
            Toast.makeText(CadastroEventoActivity.this, "ERRO ao Salvar", Toast.LENGTH_LONG).show();
        }
    }
}