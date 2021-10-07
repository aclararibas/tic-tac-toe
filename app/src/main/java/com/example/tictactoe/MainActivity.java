package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<int[]> combinationsList = new ArrayList<>(); // possible game combinations

    private int[] blockPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // block position's array

    private int playerTurn = 1;

    private int totalSelectedBlocks = 1;

    private LinearLayout ll_playerOne, ll_playerTwo; // possible players
    private TextView tv_playerOne, tv_playerTwo;
    private ImageView iv_block01, iv_block02, iv_block03, iv_block04, iv_block05, iv_block06, iv_block07, iv_block08, iv_block09;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_playerOne = findViewById(R.id.tv_playerOne);
        tv_playerTwo = findViewById(R.id.tv_playerTwo);

        ll_playerOne = findViewById(R.id.ll_playerOne);
        ll_playerTwo = findViewById(R.id.ll_playerTwo);

        iv_block01 = findViewById(R.id.iv_block01);
        iv_block02 = findViewById(R.id.iv_block02);
        iv_block03 = findViewById(R.id.iv_block03);
        iv_block04 = findViewById(R.id.iv_block04);
        iv_block05 = findViewById(R.id.iv_block05);
        iv_block06 = findViewById(R.id.iv_block06);
        iv_block07 = findViewById(R.id.iv_block07);
        iv_block08 = findViewById(R.id.iv_block08);
        iv_block09 = findViewById(R.id.iv_block09);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});

        final String getPlayerOne = getIntent().getStringExtra("playerOne");
        final String getPlayerTwo = getIntent().getStringExtra("playerTwo");

        tv_playerOne.setText(getPlayerOne);
        tv_playerTwo.setText(getPlayerTwo);

        iv_block01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(0)) {
                    playerAction((ImageView) view, 0);
                }
            }
        });

        iv_block02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(1)) {
                    playerAction((ImageView) view, 1);

                }
            }
        });

        iv_block03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(2)) {
                    playerAction((ImageView) view, 2);

                }
            }
        });

        iv_block04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(3)) {
                    playerAction((ImageView) view, 3);

                }
            }
        });

        iv_block05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(4)) {
                    playerAction((ImageView) view, 4);
                }

            }
        });

        iv_block06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(5)) {
                    playerAction((ImageView) view, 5);
                }

            }
        });

        iv_block07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(6)) {
                    playerAction((ImageView) view, 6);
                }

            }
        });

        iv_block08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(7)) {
                    playerAction((ImageView) view, 7);
                }

            }
        });

        iv_block09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBlockSelectable(8)) {
                    playerAction((ImageView) view, 8);
                }
            }
        });
    }

    private void playerAction(ImageView imageView, int selectedBlockPosition) {
        blockPositions[selectedBlockPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.cross_icon);

            if (checkPlayerWin()) {

                MatchDialog message = new MatchDialog(MainActivity.this, tv_playerOne.getText().toString()
                        + " has won the match, congrats!", MainActivity.this);
                message.setCancelable(false);
                message.show();
            } else if (totalSelectedBlocks == 9) {

                MatchDialog message = new MatchDialog(MainActivity.this, "Oh no, it's a draw!", MainActivity.this);
                message.setCancelable(false);
                message.show();
            } else {

                changePlayerTurn(2);
                totalSelectedBlocks++;
            }
        } else {
            imageView.setImageResource(R.drawable.zero_icon);

            if (checkPlayerWin()) {

                MatchDialog message = new MatchDialog(MainActivity.this, tv_playerTwo.getText().toString()
                        + " has won the match, congrats!", MainActivity.this);
                message.setCancelable(false);
                message.show();

            } else if (selectedBlockPosition == 9) {

                MatchDialog message = new MatchDialog(MainActivity.this, "Oh no, it's a draw!", MainActivity.this);
                message.setCancelable(false);
                message.show();
            }

            else {

                changePlayerTurn(1);
                totalSelectedBlocks++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;

        if (playerTurn == 1) {
            ll_playerOne.setBackgroundResource(R.drawable.background_rounded_purple);
            ll_playerTwo.setBackgroundResource(R.drawable.background_blue_transparent);
        } else {
            ll_playerTwo.setBackgroundResource(R.drawable.background_rounded_purple);
            ll_playerOne.setBackgroundResource(R.drawable.background_blue_transparent);
        }
    }

    private boolean checkPlayerWin() {
        boolean response = false;

        for (int i = 0; i < combinationsList.size(); i++) {

            final int[] combination = combinationsList.get(i);

            if (blockPositions[combination[0]] == playerTurn && blockPositions[combination[1]] == playerTurn && blockPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }

        return response;
    }

    private boolean isBlockSelectable(int blockPosition) {
        boolean response = false;

        if (blockPositions[blockPosition] == 0) {
            response = true;
        }

        return response;
    }

    public void restartMatch() {
        blockPositions = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};

        playerTurn = 1;
        totalSelectedBlocks = 1;

        iv_block01.setImageResource(R.drawable.background_blue);
        iv_block02.setImageResource(R.drawable.background_blue);
        iv_block03.setImageResource(R.drawable.background_blue);
        iv_block04.setImageResource(R.drawable.background_blue);
        iv_block05.setImageResource(R.drawable.background_blue);
        iv_block06.setImageResource(R.drawable.background_blue);
        iv_block07.setImageResource(R.drawable.background_blue);
        iv_block08.setImageResource(R.drawable.background_blue);
        iv_block09.setImageResource(R.drawable.background_blue);
    }
}