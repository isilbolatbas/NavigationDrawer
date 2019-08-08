package com.example.vallason;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class CustomDialog  {

    static Context context;
    private EventDatabase eventDatabase;
    private Event event;
    static boolean close = true;
    static Map map;


    public static Dialog createDialog(final Context context)  {


        // dialog nesnesi oluştur ve layout dosyasına bağlan
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.live_custom);


        // custom dialog elemanlarını tanımla - text, image ve button
        final Button btnKaydet = (Button) dialog.findViewById(R.id.button_kaydet);
        Button btnIptal = (Button) dialog.findViewById(R.id.button_iptal);
        final EditText eventEdit = (EditText) dialog.findViewById(R.id.eventEdit);
  //   final   String eventText = eventEdit.getText().toString().trim();
        final EditText detailEdit = (EditText) dialog.findViewById(R.id.detailEdit);
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.action_bar_spinner);






        // tamam butonunun tıklanma olayları
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(context, "Konum listeye eklendi", Toast.LENGTH_SHORT).show();

                if(eventEdit.getText().toString().isEmpty()){
                    Toast.makeText(context, "event bos olamaz", Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(detailEdit.getText().toString().isEmpty()){
                    Toast.makeText(context, "event bos olamaz", Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(spinner.getSelectedItemPosition()== 0){
                    Toast.makeText(context, "type secmeye ihtiyacın var", Toast.LENGTH_SHORT).show();

                } else {
                    Event event = new Event(eventEdit.getText().toString(), detailEdit.getText().toString(), spinner.getSelectedItemPosition());
                    EventDatabase.getDatabase(context).daoEvent().insert(event);
                    dialog.dismiss();
                    Map.getInstance().clearMap();
                    MapFragment.makeFabIconAdd();
                }

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
