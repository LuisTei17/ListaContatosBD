package com.example.root.listacontatosbd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.listacontatosbd.dao.listaDAO;
import com.example.root.listacontatosbd.models.Aluno;

import java.util.ArrayList;


/**
 * Created by root on 12/06/17.
 */

public class lista extends ListActivity {
    ArrayList<Aluno> alunos;
    listaDAO dao;
    private int posicao;
    private Aluno alunoSelecionado;
    private Dialog dialogoDeletarAluno;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new listaDAO(this);
        this.alunos = dao.listar();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos);
        this.setListAdapter(adapter);
        registerForContextMenu(getListView());

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(lista.this, toastActivity.class);
        Bundle params = new Bundle();
        params.putSerializable("aluno", alunos.get(position));
        intent.putExtras(params);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onCreate(null);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Escolha uma opção");

        menu.add(Menu.NONE, 1, Menu.NONE, "Editar");
        menu.add(Menu.NONE, 2, Menu.NONE, "Deletar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        posicao = itemInfo.position;
        alunoSelecionado = (Aluno) getListAdapter().getItem(posicao);

        dialogoDeletarAluno = createDialogDeletarContato(alunoSelecionado);

        switch(item.getItemId()){
            case 1:
                Intent itTelaEditar = new Intent(lista.this, TelaEditar.class);
                itTelaEditar.putExtra("aluno", alunoSelecionado);
                startActivity(itTelaEditar);
                break;
            case 2:
                dialogoDeletarAluno.show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private Dialog createDialogDeletarContato(final Aluno alunoSelecionado) {
        builder = new AlertDialog.Builder(lista.this);

        builder.setMessage("Deletar contato?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean deletou = dao.deletar(alunoSelecionado);
                if(deletou) {
                    alunos.remove(alunoSelecionado);
                    Toast.makeText(lista.this, "Contato deletado", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(lista.this, "Não deletado", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(lista.this, "deu merda", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
}
