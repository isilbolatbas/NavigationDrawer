package com.example.vallason;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomDialog {

    static Context context ;
    public static Dialog createDialog(final Context context) {

        // dialog nesnesi oluştur ve layout dosyasına bağlan
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.live_custom);

        // custom dialog elemanlarını tanımla - text, image ve button
        Button btnKaydet = (Button) dialog.findViewById(R.id.button_kaydet);
        Button btnIptal = (Button) dialog.findViewById(R.id.button_iptal);
        TextView eventText = (TextView) dialog.findViewById(R.id.eventText);
        TextView detailText = (TextView) dialog.findViewById(R.id.detailText);
        TextView typeText = (TextView) dialog.findViewById(R.id.typeText);
        EditText eventEdit = (EditText) dialog.findViewById(R.id.eventEdit);
        EditText detailEdit = (EditText) dialog.findViewById(R.id.detailEdit);


        // tamam butonunun tıklanma olayları
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Konum listeye eklendi", Toast.LENGTH_SHORT).show();
            }
        });
        // iptal butonunun tıklanma olayları
        btnIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
