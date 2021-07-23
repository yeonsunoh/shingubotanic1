package com.example.shingubotanic;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import org.w3c.dom.Text;


public class home extends MainActivity {

    //final static String FRAGMENT_TAG = "fragment";

    Toolbar toolbar;
    ImageButton home;
    View.OnClickListener cl;
    Intent i;
    Button b1, b2, b3, b4;

    //프래그먼트 객체 선언
    springFragment spring;
    summerFragment summer;
    fallFragment fall;
    winterFragment winter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        home = (ImageButton) findViewById(R.id.home);

        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);

        spring = new springFragment();
        summer = new summerFragment();
        fall = new fallFragment();
        winter = new winterFragment();

        //프래그먼트 매니저, 프래그먼트 트랜잭션 생성
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, spring).commitAllowingStateLoss();
        //프래그먼트 매니저란? Activity에서  fragment를 관리.
        //프래그먼트 트랜잭션이란? fragment를 동적으로 생성(add)/제거(remove)/교체(replace)하기 위해 사용.


        //changeFragment

        //프래그먼트 백스택 - 현재 실행하려는 트랜잭션의 상태를 기억해두기 위해 사용, 스마트폰의 back key를 사용해 프래그먼트를 이전 상태로 되돌릴 수 있다.


        //commit() - 프래그먼트 트랜잭션 마무리 commit()을 호출해 주어야 transaction 작업을 정삭적으로 수행
       cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch ( v.getId() ) {
                    case R.id.home :
                        i = new Intent(getApplicationContext(), home.class);
                        startActivity(i);
                        break;
                    case R.id.btn1: //봄 코스 (디폴트)
                        transaction.replace(R.id.fragment_container, spring);
                        transaction.commitAllowingStateLoss();
                        break;
                    case R.id.btn2: //여름 코스
                        transaction.replace(R.id.fragment_container, summer);
                        transaction.commitAllowingStateLoss();
                        break;
                    case R.id.btn3: //가을 코스
                        transaction.replace(R.id.fragment_container, fall);
                        transaction.commitAllowingStateLoss();
                        break;
                    case R.id.btn4: //겨울 코스
                        transaction.replace(R.id.fragment_container, winter);
                        transaction.commitAllowingStateLoss();
                        break;
                }
            }
        };
        home.setOnClickListener(cl);
        b1.setOnClickListener(cl);
        b2.setOnClickListener(cl);
        b3.setOnClickListener(cl);
        b4.setOnClickListener(cl);
    }

}
