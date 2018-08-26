package com.example.luiz_.dicesdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {
    //Gerador de números rondomicos usado para simular o lançamento do dado
    private Random geradorRandomico;

    //referência para componentes visuais no arquivo de Layout

    private TextView resultadoTextView;
    private ImageView resultadoImageView;
    private Spinner numDadosSpinner;
    private ImageView resultado2ImageView;
    private EditText numFacesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instanciando gerador rondomico
        geradorRandomico = new Random(System.currentTimeMillis());
        //recuperando referencia para os componentes de Layout
        resultadoTextView = findViewById(R.id.resultadoTextView);
        numDadosSpinner = findViewById(R.id.numDadosSpinner);
        resultadoImageView = findViewById(R.id.resultadoImageView);
        resultado2ImageView = findViewById(R.id.resultado2ImageView);
        numFacesEditText = findViewById(R.id.numFacesEditText);
    }

    public void jogarDado(View view) {
        if (view.getId() == R.id.button) {
            int numDices = Integer.parseInt(numDadosSpinner.getSelectedItem().toString());
            int numFaces = Integer.parseInt(numFacesEditText.getText().toString());
            if (numFaces > 6) {
                resultadoImageView.setVisibility(View.GONE);
                resultado2ImageView.setVisibility(View.GONE);
            } else {
                resultadoImageView.setVisibility(View.VISIBLE);
                if (numDices == 2) {
                    resultado2ImageView.setVisibility(View.VISIBLE);
                } else {

                    resultado2ImageView.setVisibility(View.GONE);
                }
            }

            String resultadoText = "";
            for (int i = 1; i <= numDices; i++) {
                int resultado = geradorRandomico.nextInt(numFaces) + 1;
                resultadoText += resultado + " ";

                ImageView imageView = i == 1 ? resultadoImageView : resultado2ImageView;

                switch (resultado) {
                    case 1:
                        imageView.setImageResource(R.drawable.dice_1);
                        break;

                    case 2:
                        imageView.setImageResource(R.drawable.dice_2);
                        break;

                    case 3:
                        imageView.setImageResource(R.drawable.dice_3);
                        break;

                    case 4:
                        imageView.setImageResource(R.drawable.dice_4);
                        break;

                    case 5:
                        imageView.setImageResource(R.drawable.dice_5);
                        break;
                    case 6:
                        imageView.setImageResource(R.drawable.dice_6);
                        break;

                    default:
                        break;
                }
            }
            resultadoTextView.setText(getString(R.string.face_sorteada) + resultadoText);
        } else {
            Toast.makeText(this, "Número de faces deve ser maior que 0", Toast.LENGTH_SHORT).show();
        }
    }
}
