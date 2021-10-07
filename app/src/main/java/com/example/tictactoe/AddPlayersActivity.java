package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        final EditText et_playerOne = findViewById(R.id.et_playerOne);
        final EditText et_playerTwo = findViewById(R.id.et_playerTwo);
        final Button bt_startGame = findViewById(R.id.bt_startGame);

        bt_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String getPlayerOne = et_playerOne.getText().toString();
                final String getPlayerTwo = et_playerTwo.getText().toString();

                if (getPlayerOne.isEmpty() || getPlayerTwo.isEmpty()) {
                    Toast.makeText(AddPlayersActivity.this, "Player's name is missing.", Toast.LENGTH_SHORT).show();
                }

                else  {
                    Intent intent = new Intent(AddPlayersActivity.this, MainActivity.class);
                    intent.putExtra("playerOne", getPlayerOne);
                    intent.putExtra("playerTwo", getPlayerTwo);
                    startActivity(intent);
                }
            }
        });
    }
}