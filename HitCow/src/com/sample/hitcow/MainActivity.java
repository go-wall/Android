package com.sample.hitcow;

import java.util.Random;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnTouchListener{
	//View
	private ImageView img;
	
	//効果音用
	private SoundPool sp;
	private int soundId1;	//通常のHIT音
	private int soundId2;	//20%で発生する特殊なHIT音

	//Activityが生成された時に呼ばれるメソッド（Activityのライフサイクルを参照。ちょっと難しいが大事）
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Viewの初期化
		img = (ImageView)findViewById(R.id.cow_img);
		img.setOnTouchListener(this);
		
		//効果音の初期化
		sp = new SoundPool(1,AudioManager.STREAM_MUSIC, 0);
		soundId1 = sp.load(this, R.raw.hit, 1);
		soundId2 = sp.load(this, R.raw.mega_hit, 1);
	}

	//タッチされた時
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//0～4までの5つの乱数を生成し、1つだけ取得する
		Random rnd = new Random();
		int num = rnd.nextInt(5);
		
		//タッチイベントの判別
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN :
			//画面が押された時の処理
			if(num != 0) {
				//取得した乱数が0以外の時（80%）
				//画像を変更
				img.setImageResource(R.drawable.hit_img);
				//音を鳴らす
				sp.play(soundId1, 1.0f, 1.0f, 0, 0, 1.0f);
			} else {
				//取得した乱数が0の時（20%）
				//画像を変更
				img.setImageResource(R.drawable.mega_hit_img);
				//音を鳴らす
				sp.play(soundId2, 1.0f, 1.0f, 0, 0, 1.0f);
			}
			return true;
		case MotionEvent.ACTION_UP :
			//画面から指が離れたとき
			//画像を戻す
			img.setImageResource(R.drawable.normal_img);
			return true;
		}
		return false;
	}

	//メニューが作られる時に呼ばれるメソッド。今回は何もしない（消してもOK）
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
