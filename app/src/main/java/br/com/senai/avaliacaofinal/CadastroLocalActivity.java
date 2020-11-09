package br.com.senai.avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.senai.avaliacaofinal.Database.LocalDAO;
import br.com.senai.avaliacaofinal.Modelo.Local;

public class CadastroLocalActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextDescricao;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextlotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("Cadastro do local do evento");
        editTextDescricao = findViewById(R.id.editText_descricao);
        editTextBairro = findViewById(R.id.editText_bairro);
        editTextCidade = findViewById(R.id.editText_cidade);
        editTextlotacao = findViewById(R.id.editText_lotacao);
        carregarLocal();
    }

    public void carregarLocal(){
        Intent intent = getIntent();
        if ( intent != null && intent.getExtras() != null &&
                intent.getExtras().get("localEdicao") != null){
            Local local = (Local) intent.getExtras().get("localEdicao");
            editTextDescricao.setText(local.getDescricao());
            editTextBairro.setText(local.getBairro());
            editTextCidade.setText(local.getCidade());
            editTextlotacao.setText(local.getLotacao());
            id = local.getId();
        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {

        String descricao = editTextDescricao.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String lotacao = editTextlotacao.getText().toString();
        Local local = new Local(id, descricao, bairro, cidade, lotacao);
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        boolean salvou = localDAO.salvar(local);
        if (salvou){
            finish();
        } else {
            Toast.makeText(CadastroLocalActivity.this, "ERRO ao Salvar", Toast.LENGTH_LONG).show();
        }
    }
}