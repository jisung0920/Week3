package com.example.jisung.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder dialog;
    EditText t1, t2;

    ArrayList<Webdata> list;
    WebdataAdapter adapter;
    ListView listView;

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        settingView();

        webView.loadUrl("http://www.google.com");

        list.add(new Webdata("구글","http://www.google.com"));
        adapter.notifyDataSetChanged();

    }

    private void init() {
        list = new ArrayList<Webdata>();
        listView = (ListView) findViewById(R.id.list);
        adapter = new WebdataAdapter(list, this);
        listView.setAdapter(adapter);
        webView = (WebView) findViewById(R.id.web);
    }

    private void settingView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setSupportZoom(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                webView.loadUrl(list.get(position).getUrldata());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                dialog = new AlertDialog.Builder(this);
                View view = View.inflate(this, R.layout.addurl, null);

                t1 = (EditText) view.findViewById(R.id.name);
                t2 = (EditText) view.findViewById(R.id.data);

                dialog.setTitle("즐겨찾기 추가").setView(view).setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "항목이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                        list.add(new Webdata(t1.getText().toString(), t2.getText().toString()));
                        adapter.notifyDataSetChanged();
                    }
                }).show();

                break;
            case R.id.before:
                webView.goBack();
                break;
            case R.id.next:
                webView.goForward();
                break;
            case R.id.refresh:
                webView.reload();
            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
