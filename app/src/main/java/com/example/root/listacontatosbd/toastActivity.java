package com.example.root.listacontatosbd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.root.listacontatosbd.models.Aluno;

public class toastActivity extends AppCompatActivity {
    private String frase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        Bundle params = getIntent().getExtras();
        Aluno aluno = (Aluno) params.getSerializable("aluno");
        frase = aluno.getNome() + " " + aluno.getSexo() + " " + aluno.getInteresses();
        Toast.makeText(this, frase, Toast.LENGTH_SHORT).show();
    }
}
