package com.example.root.listacontatosbd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.root.listacontatosbd.dao.listaDAO;
import com.example.root.listacontatosbd.models.Aluno;

import java.util.ArrayList;

public class TelaEditar extends AppCompatActivity {
    private Aluno aluno;
    private ArrayList<Aluno> alunos;
    private EditText etNome;
    private RadioButton rbFemi, rbMasc;
    private CheckBox cbFilm, cbMusi;
    private Button btSalva;
    listaDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_tela_editar);

        inicializaComponentes();

        this.etNome.setText(aluno.getNome());

        if(this.rbFemi.isChecked()) {this.rbFemi.toggle();}
        if(this.rbMasc.isChecked()){ this.rbMasc.toggle();}

        if(this.cbFilm.isChecked()) { this.cbFilm.toggle();}
        if(this.cbMusi.isChecked()) { this.cbMusi.toggle();}


        dao = new listaDAO(this);
        this.btSalva.setOnClickListener(new View.OnClickListener() {
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
                aluno.setId("3");
                aluno = new Aluno(nome, sexo, interesse);

                boolean editou = dao.editar(aluno);
                if(editou) {
                    Toast.makeText(TelaEditar.this, "Aluno editado", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(TelaEditar.this, "Erro", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public void inicializaComponentes(){
        etNome = (EditText) findViewById(R.id.etNome_editar);
        rbFemi = (RadioButton) findViewById(R.id.rbFemi_editar);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc_editar);
        cbFilm = (CheckBox) findViewById(R.id.cbFilm_editar);
        cbMusi = (CheckBox) findViewById(R.id.cbMusi_editar);
        btSalva = (Button) findViewById(R.id.btSalvar_editar);
        aluno = (Aluno) getIntent().getExtras().getSerializable("aluno");

    }
}
