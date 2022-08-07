package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    LinearLayout btn_calculate,github,link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.principal);
        final EditText editText2 = (EditText) findViewById(R.id.interest);
        final EditText editText3 = (EditText) findViewById(R.id.years);
        final EditText editText4 = (EditText) findViewById(R.id.interest_total);
        final EditText editText5 = (EditText) findViewById(R.id.emi);
        LinearLayout button = (LinearLayout) findViewById(R.id.btn_calculate2);
        this.btn_calculate = button;


        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                String obj = editText.getText().toString();
                String obj2 = editText2.getText().toString();
                String obj3 = editText3.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    editText.setError("Enter Principal Amount");
                    editText.requestFocus();
                } else if (TextUtils.isEmpty(obj2)) {
                    editText2.setError("Enter Interest Rate");
                    editText2.requestFocus();
                } else if (TextUtils.isEmpty(obj3)) {
                    editText3.setError("Enter Years");
                    editText3.requestFocus();
                } else {
                    float parseFloat = Float.parseFloat(obj);
                    float parseFloat2 = Float.parseFloat(obj2);
                    float parseFloat3 = Float.parseFloat(obj3);
                    float calPric = MainActivity.this.calPric(parseFloat);
                    float calInt = MainActivity.this.calInt(parseFloat2);
                    float calMonth = MainActivity.this.calMonth(parseFloat3);
                    float calDvdnt = MainActivity.this.calDvdnt(calInt, calMonth);
                    float calEmi = MainActivity.this.calEmi(MainActivity.this.calFinalDvdnt(calPric, calInt, calDvdnt), Float.valueOf(MainActivity.this.calDivider(calDvdnt)));
                    float calTotalInt = MainActivity.this.calTotalInt(MainActivity.this.calTa(calEmi, Float.valueOf(calMonth)), calPric);
                    editText5.setText(String.valueOf(calEmi));
                    editText4.setText(String.valueOf(calTotalInt));
                }
            }
        });
    }

    public float calEmi(float f, Float f2) {
        return f / f2.floatValue();
    }

    public float calTa(float f, Float f2) {
        return f2.floatValue() * f;
    }

    public void onClick(View view) {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.TransparentDialog);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_view);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
        github = bottomSheetDialog.findViewById(R.id.git);
        link = bottomSheetDialog.findViewById(R.id.link);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://github.com/kalathiyavraj";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                bottomSheetDialog.dismiss();
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.linkedin.com/in/vraj-kalathiya-4944a7213";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                bottomSheetDialog.dismiss();
            }
        });

    }
    public float calDivider(float f) {
        return f - 1.0f;
    }

    public float calDvdnt(float f, float f2) {
        return (f + 1.0f) * f2;
    }

    public float calFinalDvdnt(float f, float f2, float f3) {
        return f * f2 * f3;
    }

    public float calInt(float f) {
        return (f / 12.0f) / 100.0f;
    }

    public float calMonth(float f) {
        return f * 12.0f;
    }

    public float calPric(float f) {
        return f;
    }

    public float calTotalInt(float f, float f2) {
        return f - f2;
    }

}
