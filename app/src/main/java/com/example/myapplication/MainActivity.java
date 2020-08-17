package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lvNews;
    ArrayAdapter<String> adapter;

    private static final int PERMISSION_STORAGE_CODE = 1000 ;
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tela_inicial);
        button1  = (Button) findViewById(R.id.symptoms);
        button2  = (Button) findViewById(R.id.mobile);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.symptoms:
                openMainActivityDoencasSintomas();
                break;
            case R.id.mobile:
                openMainActivityNumeroEmergencia();
                break;
            case R.id.covid19Home:
                openMainActivityCovid19Home();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void openMainActivityCovid19Home() {
        Intent intent = new Intent(this, MainActivityCovid19Home.class);
        startActivity(intent);
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


    private void openMainActivityDoencasSintomas() {
        Intent intent = new Intent(this,MainActivityDoencasSintomas.class);
        startActivity(intent);
    }

    private void openMainActivityNumeroEmergencia() {
        Intent intent = new Intent(this,MainActivityNumeroEmergencia.class);
        startActivity(intent);
    }

    public void onClickMedidasPreventivas(View view) {
         openMainActivityMedidasPreventivas();
    }

    private void openMainActivityMedidasPreventivas() {
        Intent intent = new Intent(this, MainActivityCovid19Preventions.class);
        startActivity(intent);
    }

//    private void openMainActivityNoticias() throws IOException, SAXException {
//        setContentView(R.layout.activity_main_noticias);
//        lvNews = (ListView) findViewById(R.id.lvNews);
//        URL url = new URL("http://example.com/feed.rss");
//        RssFeed feed = RssReader.read(url);
//
//        ArrayList<RssItem> rssItems = feed.getRssItems();
//        ArrayList<String> titles = new ArrayList<String>();
//        for(RssItem rssItem : rssItems) {
//            titles.add(rssItem.getTitle());
//        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,titles);
//        lvNews.setAdapter(arrayAdapter);
//    }

}

