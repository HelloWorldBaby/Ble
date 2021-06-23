package com.dasouche.jiededemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseTypeActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_chezhu_kongche;
    private Button btn_chezhu_jieche;
    private Button btn_jiecheren_jieche;
    private Button btn_jiecheren_kongche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        btn_chezhu_kongche=findViewById(R.id.btn_chezhu_kongche);
        btn_chezhu_jieche=findViewById(R.id.btn_chezhu_jieche);
        btn_jiecheren_jieche=findViewById(R.id.btn_jiecheren_jieche);
        btn_jiecheren_kongche=findViewById(R.id.btn_jiecheren_kongche);
        btn_chezhu_kongche.setOnClickListener(this);
        btn_chezhu_jieche.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_chezhu_kongche:
                    startActivity(new Intent(ChooseTypeActivity.this,MainActivity.class));
                break;
            case R.id.btn_chezhu_jieche:
                startActivity(new Intent(ChooseTypeActivity.this,BorrowCarActivity.class));

                break;
        }
    }
}