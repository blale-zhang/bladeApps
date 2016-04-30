package org.oschina.blade.android.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {


    Button mButton;
    Button mShowProgressBtn;

    Button mShowDetailProgressBtn;
    ProgressDialog progressDialog;
    CharSequence[] items = {"Google", "Apple", "Microsoft"};
    boolean[] itemsChecked = new boolean[items.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


        //getIntent().getStringExtra("key");
        Bundle bundle = getIntent().getExtras();
        bundle.getString("str2");
        Toast.makeText(this,  getIntent().getStringExtra("key"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this,   bundle.getString("str2"), Toast.LENGTH_SHORT).show();

        mButton = (Button) this.findViewById(R.id.btn_dialog);


        mShowProgressBtn = (Button) this.findViewById(R.id.btn_dialog2);
        mShowDetailProgressBtn = (Button) this.findViewById(R.id.btn_dialog3);

        mButton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           onCreateDialog(0).show();
                                       }
                                   }

        );

        mShowProgressBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    showProgressDialog(0);
                                                }
                                            }

        );

        mShowDetailProgressBtn.setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View v) {
                                                          onCreateDialog(1).show();

                                                          new Thread(new Runnable(){ public void run(){
                                                              for (int i=1; i<=15; i++) { try {

//---simulate doing something lengthy---
                                                                  Thread.sleep(1000);
//---update the dialog---
                                                                  progressDialog.incrementProgressBy((int)(100/15)); } catch (InterruptedException e) {
                                                                  e.printStackTrace(); }
                                                              }
                                                              progressDialog.dismiss(); }
                                                          }).start();
                                                      }
                                                  }
        );


    }


    public void showProgressDialog(int i) {
        //---show the dialog---
        final ProgressDialog dialog = ProgressDialog.show(
                this, "Doing something", "Please wait...", true);
        new Thread(new Runnable() {
            public void run() {
                try {
                    //---simulate doing something lengthy---
                    Thread.sleep(5000); //---dismiss the dialog---
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new AlertDialog.Builder(this)
                        .setTitle("This is a dialog with some simple text...").setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(getBaseContext(),
                                                "OK clicked!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ).setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(getBaseContext(),
                                                "Cancel clicked!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        )
                        .setMultiChoiceItems(items, itemsChecked,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        Toast.makeText(getBaseContext(),
                                                items[which] + (isChecked ? " checked!" : " unchecked!"), Toast.LENGTH_SHORT).show();

                                    }
                                }
                        ).create();
            case 1:
                progressDialog = new ProgressDialog(this);
//                progressDialog.setIcon(R.drawable.ic_launcher);
                progressDialog.setTitle("Downloading files...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(),
                                        "OK clicked!", Toast.LENGTH_SHORT).show();
                            }
                        });
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(),
                                        "Cancel clicked!", Toast.LENGTH_SHORT).show();
                            }
                        });

                return progressDialog;
        }
        return null;
    }

}


