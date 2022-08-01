package com.unir.bibliotecaunir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    private Button btnSalvar, btnVoltar;
    private EditText edtTitulo, edtAutor, edtAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnSalvar = findViewById(R.id.btn_salvar);
        btnVoltar = findViewById(R.id.btn_voltar);
        edtTitulo = findViewById(R.id.edt_titulo);
        edtAno = findViewById(R.id.edt_ano);
        edtAutor = findViewById(R.id.edt_autor);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                String titulo = edtTitulo.getText().toString();
                String autor = edtAutor.getText().toString();
                String ano = edtAno.getText().toString();
                cv.put("titulo", titulo);
                cv.put("autor", autor);
                cv.put("ano", ano);
                BancoControlador bd = new BancoControlador(getApplicationContext());
                String msg = "";
                long res = bd.inserir(cv);
                if(res > 0){
                    msg = "Operação realizada com sucesso!";
                    edtAno.setText("");
                    edtAutor.setText("");
                    edtTitulo.setText("");
                    edtTitulo.requestFocus();
                }else{
                    msg = "Ocorreu um erro durante a operação!";
                }
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}