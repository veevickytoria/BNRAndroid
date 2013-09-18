package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by vzheng on 9/15/13.
 */
public class CheatActivity extends Activity{

    public static final String EXTRA_ANSWER_IS_TRUE = "com.bnr.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.bnr.android.geoquiz.answer_shown";

    private static final String KEY_CHEATED = "cheatAnswer";

    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private String mCheatAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mCheatAnswer = getString(R.string.true_button);
                } else {
                    mCheatAnswer = getString(R.string.false_button);
                }
                mAnswerTextView.setText(mCheatAnswer);
                setAnswerShownResult(true);
            }
        });

        if (savedInstanceState != null) {
            mCheatAnswer = savedInstanceState.getString(KEY_CHEATED);
            mAnswerTextView.setText(mCheatAnswer);
            setAnswerShownResult(true);
        } else {
            setAnswerShownResult(false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_CHEATED, mCheatAnswer);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

}
