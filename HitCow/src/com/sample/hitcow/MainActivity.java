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
	
	//���ʉ��p
	private SoundPool sp;
	private int soundId1;	//�ʏ��HIT��
	private int soundId2;	//20%�Ŕ�����������HIT��

	//Activity���������ꂽ���ɌĂ΂�郁�\�b�h�iActivity�̃��C�t�T�C�N�����Q�ƁB������Ɠ�����厖�j
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//View�̏�����
		img = (ImageView)findViewById(R.id.cow_img);
		img.setOnTouchListener(this);
		
		//���ʉ��̏�����
		sp = new SoundPool(1,AudioManager.STREAM_MUSIC, 0);
		soundId1 = sp.load(this, R.raw.hit, 1);
		soundId2 = sp.load(this, R.raw.mega_hit, 1);
	}

	//�^�b�`���ꂽ��
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//0�`4�܂ł�5�̗����𐶐����A1�����擾����
		Random rnd = new Random();
		int num = rnd.nextInt(5);
		
		//�^�b�`�C�x���g�̔���
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN :
			//��ʂ������ꂽ���̏���
			if(num != 0) {
				//�擾����������0�ȊO�̎��i80%�j
				//�摜��ύX
				img.setImageResource(R.drawable.hit_img);
				//����炷
				sp.play(soundId1, 1.0f, 1.0f, 0, 0, 1.0f);
			} else {
				//�擾����������0�̎��i20%�j
				//�摜��ύX
				img.setImageResource(R.drawable.mega_hit_img);
				//����炷
				sp.play(soundId2, 1.0f, 1.0f, 0, 0, 1.0f);
			}
			return true;
		case MotionEvent.ACTION_UP :
			//��ʂ���w�����ꂽ�Ƃ�
			//�摜��߂�
			img.setImageResource(R.drawable.normal_img);
			return true;
		}
		return false;
	}

	//���j���[������鎞�ɌĂ΂�郁�\�b�h�B����͉������Ȃ��i�����Ă�OK�j
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
