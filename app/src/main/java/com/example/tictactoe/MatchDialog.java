package com.example.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MatchDialog extends Dialog {

    private final String message;
    private final MainActivity mainActivity;

    public MatchDialog(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.match_dialog);

        final TextView tv_message = findViewById(R.id.tv_message);
        final Button bt_tryAgain = findViewById(R.id.bt_tryAgain);

        tv_message.setText(message);

        bt_tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainActivity.restartMatch();
                dismiss();
            }
        });
    }
}
