package com.androiddev97.wallpaper2021.utils;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.androiddev97.wallpaper2021.ui.fragment.BottomSheetDownload;
import com.androiddev97.wallpaper2021.ui.main.view.ShowFullActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

class DownloadFileFromURL extends AsyncTask<String, Integer, String> {
    private String nameFile;
    private TextView textProgress;
    private BottomSheetDownload bottomSheetDownload;

    public DownloadFileFromURL(BottomSheetDownload bottomSheetDownload, String nameFile, TextView textProgress) {
        this.nameFile = nameFile;
        this.textProgress = textProgress;
        this.bottomSheetDownload = bottomSheetDownload;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            int lenghtOfFile = conection.getContentLength();

            InputStream input = new BufferedInputStream(url.openStream(),
                    8192);
            // Output stream
            OutputStream output = new FileOutputStream(Environment
                    .getExternalStorageDirectory() + File.separator + nameFile + ".jpg");
            byte[] data = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                int cur = (int) ((total * 100) / lenghtOfFile);

                publishProgress(Math.min(cur, 100));
                if (Math.min(cur, 100) > 98) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Log.d("Main12345", "sleeping failure");
                    }
                }
                Log.d("Main12345", "currentProgress: " + Math.min(cur, 100) + "\n " + cur);

                output.write(data, 0, count);
            }

            output.flush();

            output.close();
            input.close();

        } catch (Exception e) {
            Log.d("Main12345:= ", e.getMessage());
        }

        return null;
    }

    int i = 0;

    protected void onProgressUpdate(Integer... progress) {
        if (textProgress != null) {
            if (progress[0] < 0 || progress[0] > 100) {
                if (i < 99) {
                    textProgress.setText(i + "%");
                    i++;
                }
            } else {
                textProgress.setText(progress[0] + "%");
            }
        }
        super.onProgressUpdate(progress);
    }

    @Override
    protected void onPostExecute(String file_url) {
        if (bottomSheetDownload != null) {
           // bottomSheetDownload.doneExportSticker();
        }
    }
}
