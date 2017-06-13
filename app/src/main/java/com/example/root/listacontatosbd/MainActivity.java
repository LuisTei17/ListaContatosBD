package com.example.root.listacontatosbd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.root.listacontatosbd.dao.listaDAO;
import com.example.root.listacontatosbd.models.Aluno;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Aluno aluno;
    private ArrayList<Aluno> alunos;
    private EditText etNome;
    private RadioButton rbFemi, rbMasc;
    private CheckBox cbFilm, cbMusi;
    private Button btCadastra, btSalva;

    listaDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaComponentes();
        alunos = new ArrayList<>();

        dao = new listaDAO(getApplicationContext());

        btCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome;
                String sexo = " ";
                String interesse = " ";
                nome = etNome.getText().toString();

                if(rbFemi.isChecked()) { sexo = "feminino";}
                if(rbMasc.isChecked()) { sexo = "masculino";}

                if(cbFilm.isChecked()) { interesse += " filme ";}
                if(cbMusi.isChecked()) { interesse += " musica ";}

                aluno = new Aluno(nome, sexo, interesse);
                boolean salvou = dao.salvar(aluno);

                if(salvou) {
                    Toast.makeText(MainActivity.this, "Aluno salvo: " + aluno.getNome(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, lista.class);
                startActivity(intent);

                /* Código legado que não usava banco de dados
                Bundle params = new Bundle();
                params.putSerializable("alunos", alunos);
                Intent i = new Intent(MainActivity.this, lista.class);
                i.putExtras(params);
                startActivity(i);
                */
            }
        });
    }

    public void inicializaComponentes(){
        etNome = (EditText) findViewById(R.id.etNome);
        rbFemi = (RadioButton) findViewById(R.id.rbFemi);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc);
        cbFilm = (CheckBox) findViewById(R.id.cbFilm);
        cbMusi = (CheckBox) findViewById(R.id.cbMusi);
        btCadastra = (Button) findViewById(R.id.btCadastrar);
        btSalva = (Button) findViewById(R.id.btSalvar);
    }
}
