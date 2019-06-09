package com.example.number;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int pickedNumber;
    boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button);

        setupGame();
    }

    private void setupGame() {
        gameOver = false;
        pickedNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        tvInfo.setText(getResources().getString(R.string.try_to_guess));
    }

    public void playMore(View view) {
        setupGame();
    }

    public void onClick(View view) {
        if (gameOver) {
            tvInfo.setText("Игра окончена. Сыграть еще?");
            return;
        }

        String inputValue = etInput.getText().toString();
        if (inputValue.isEmpty()) {
            tvInfo.setText(getResources().getString(R.string.error));
            return;
        }

        int value = Integer.parseInt(inputValue);

        if (value < 1 || value > 100) {
            tvInfo.setText(getResources().getString(R.string.out_of_range));
            return;
        }

        if (value > pickedNumber) {
            tvInfo.setText(getResources().getString(R.string.ahead));
        } else if (value < pickedNumber) {
            tvInfo.setText(getResources().getString(R.string.behind));
        } else {
            tvInfo.setText(getResources().getString(R.string.hit));
            gameOver = true;
        }

    }
}

