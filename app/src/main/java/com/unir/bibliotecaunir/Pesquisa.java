package com.unir.bibliotecaunir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Pesquisa extends AppCompatActivity {

    private Button btnVoltar;
    private TableLayout tabela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        btnVoltar = findViewById(R.id.btn_voltar_pesquisar);
        tabela = findViewById(R.id.tb_pesquisaAtualizar);

        Intent intent = getIntent();
        int tipo = intent.getIntExtra("tipo", 0);
        String busca = intent.getStringExtra("busca");
        List<ContentValues> lista = new ArrayList<>();
        BancoControlador bd = new BancoControlador(getApplicationContext());

        if (tipo == R.id.rd_titulo){
            lista = bd.pesquiarPorTitulo(busca);
        }else if (tipo == R.id.rd_ano){
            lista = bd.pesquiarPorAno(Integer.parseInt(busca));
        }else if (tipo == R.id.rd_todos){
            lista = bd.pesquiarPorTodos();
        }

        if (lista != null && lista.size() > 0){
            for (ContentValues cv : lista){
                TableRow tr = new TableRow(getApplicationContext());
                TextView col1 = new TextView(getApplicationContext());
                col1.setText(String.valueOf(cv.getAsInteger("id")));
                tr.addView(col1);

                TextView col2 = new TextView(getApplicationContext());
                col2.setText(cv.getAsString("titulo"));
                tr.addView(col2);

                TextView col3 = new TextView(getApplicationContext());
                col3.setText(cv.getAsString("autor"));
                tr.addView(col3);

                TextView col4 = new TextView(getApplicationContext());
                col4.setText(String.valueOf(cv.getAsInteger("ano")));
                tr.addView(col4);
                tr.setPadding(24, 8, 8, 8);

                tabela.addView(tr);
            }
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}