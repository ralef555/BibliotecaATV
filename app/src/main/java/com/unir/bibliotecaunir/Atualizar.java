package com.unir.bibliotecaunir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Atualizar extends AppCompatActivity {

    private TableLayout tabelaAtualizar;
    private Button btn_atualizar_entrada;
    private Button btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        tabelaAtualizar = findViewById(R.id.tb_pesquisaAtualizar);


        List<ContentValues> lista = new ArrayList<>();
        BancoControlador bd = new BancoControlador(getApplicationContext());

        lista = bd.pesquiarPorTodos();

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

                tabelaAtualizar.addView(tr);
            }
        }

        btn_atualizar_entrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                public void atualizarRegistro(int id){
                    String where = "id=" + id;
                    SQLiteDatabase db = this.getReadableDatabase();
                    db.delete("catalogo", where, null);
                    db.close();
                }
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }



}