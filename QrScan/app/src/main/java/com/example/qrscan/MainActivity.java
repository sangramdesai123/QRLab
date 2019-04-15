package com.example.qrscan;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class MainActivity extends AppCompatActivity {
    EditText textView;
    Button button;
    ImageView imv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*get reference of all ids*/
        textView=(EditText)findViewById(R.id.book_id);
        button=(Button)findViewById(R.id.btn);
        imv=(ImageView)findViewById(R.id.qrcode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=textView.getText().toString();
                MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                QRCodeWriter writer = new QRCodeWriter();
                try {
                    //BitMatrix bitMatrix=multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
                    //BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                    //Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                    BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    imv.setImageBitmap(bmp);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
