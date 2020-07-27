package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static java.lang.Package.*;

public class MainActivity extends AppCompatActivity {
    private ListView lvNews;
    ArrayAdapter<String> adapter;

    private static final int PERMISSION_STORAGE_CODE = 1000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tela_inicial);
    }

    public void onClickBoletimEpidemiologico(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_STORAGE_CODE);
            }else{
                startDownloading();
            }
        }else{
            startDownloading();
        }

    }

    private void startDownloading() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://www.saude.rr.gov.br/index.php/informacoesx/coronavirus/informacoes-coronavirus/file/1439-boletim-epidemiologico-coronavirus-172-2020"));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //request.setDestinationInExternalPublicDir(DIRECTORY_DOWNLOADS);
        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch(requestCode){
            case PERMISSION_STORAGE_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startDownloading();
                }else{
                    Toast.makeText(this,"Permission denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void onClickNumeroEmergencia(View view) {
        openMainActivityNumeroEmergencia();
    }

    private void openMainActivityNumeroEmergencia() {
        Intent intent = new Intent(this,MainActivityNumeroEmergencia.class);
        startActivity(intent);
    }

    public void onClickHome(View view) {
        openMainActivityMainTelaInicial();
    }

    private void openMainActivityMainTelaInicial(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNoticias(View view) throws IOException, SAXException {
        openMainActivityNoticias();
    }

    private void openMainActivityNoticias() throws IOException, SAXException {
        setContentView(R.layout.activity_main_noticias);
        lvNews = (ListView) findViewById(R.id.lvNews);
        URL url = new URL("http://example.com/feed.rss");
        RssFeed feed = RssReader.read(url);

        ArrayList<RssItem> rssItems = feed.getRssItems();
        ArrayList<String> titles = new ArrayList<String>();
        for(RssItem rssItem : rssItems) {
            titles.add(rssItem.getTitle());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,titles);
        lvNews.setAdapter(arrayAdapter);




    }
}

