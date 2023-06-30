package com.example.tachapp

import android.app.DownloadManager.Request
import android.media.tv.AdResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.telecom.Call
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.security.auth.callback.Callback

class SenderActivity : AppCompatActivity() {
    private lateinit var messageTextView: TextView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)

        messageTextView = findViewById(R.id.messageTextView)
        messageEditText = findViewById(R.id.messageEditText)
        sendButton = findViewById(R.id.sendButton)
    }
    private fun sendMessage(message:String){
        val url = URL("http://example.com/send_message")
        //url.openConnection()は、指定されたURLに対して接続を開き、URLConnectionオブジェクトを返す。
        // urlはURLオブジェクトであり、HTTP接続を行うためのURLを指定 。
        //as HttpURLConnectionは、URLConnectionオブジェクトをHttpURLConnectionオブジェクトにキャストする。
        // これは、具体的にHTTPプロトコルを使用して通信を行うために必要。
        val connection = url.openConnection() as HttpURLConnection

        try {
            //connectionオブジェクトのrequestMethodプロパティにPostメゾットを設定し、
            //リクエストのHTTPメゾットがPostになる。
            connection.requestMethod = "POST"
            //connectionオブジェクトのdoOutputプロパティをtrueに設定し、
            //これにより、リクエストのデータ送信が許可され、リクエストボディにデータを書き込むことができる。
            connection.doOutput = true
            //message変数の値を使ってPostデータを作成、
            // toByteArray(Charsets.UTF_8)は、文字列をバイト配列に変換している
            val postDate = "message=$message".toByteArray(Charsets.UTF_8)
        }finally {
            //HTTP接続を切断
            connection.disconnect()
        }
        }

    private fun receiveMessage(){
        val url ="http://example.com/receive_message"
        //OkHttpClient()の呼び出しにより、新しいOkHttpクライアントのインスタンスが作成され 、
        // client変数に代入される。OkHttpClientは、HTTPリクエストの作成と送信、レスポンスの取得、接続プールの管理など、
        // HTTP通信を簡単かつ効率的に行うためのさまざまな機能を提供する
        val client = OkHttpClient()
        //Requestクラスのインスタンスを作成します。Request.Builder()を使用して
        // ビルダーパターンを使用し、 リクエストオブジェクトを構築しています
        val request = Request.Builder().url(url).build()
        //
        client.newCall(request).enqueue(object :Callback{
            //リクエストが失敗した場合に呼び出されるCallbackメゾット
            //例外情報を受け取り、エラーハンドリングを行うために使用する
            override fun onFailure(call: Call, e:IOException){
            }
            //リクエストが成功した場合に呼び出されるCallbackメゾット
            //responseオブジェクトからレスポンスの情報を取得し、レスポンスの処理を行う
            //response.isSuccessfulを使用して、レスポンスが成功したかどうかを判定する。
            override fun onResponse(call:Call,response: Response){
                if(response.isSuccessful) {
                    val responseBody = response.body?.String()
                //responseBodyからメッセージを抽出して処理
                }else{
                //レスポンスが失敗した場合のエラーハンドリング
                }
            }
        })
    }
    }