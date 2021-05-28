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
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;


public class home extends MainActivity  implements MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {
    Toolbar toolbar;
    ImageButton home;
    View.OnClickListener cl;
    Intent i;
    Button b1, b2, b3;

    private static final String LOG_TAG = "MainActivity";

    private MapView mapView;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 1000;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        home = (ImageButton) findViewById(R.id.home);
        mapView = (MapView) findViewById(R.id.map_view);
        mapView.setCurrentLocationEventListener(this);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);



/*
        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        }else {

            checkRunTimePermission();
        }*/
// 밑 구현부는 줌고정값을 신구대 식물원 좌표로 지정하고 확대 축소 비율 고정값을 줌레벨 2로 지정한것이다
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.4340312970034, 127.08084512504904), 2, true);
        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);



        MapPolyline polyline = new MapPolyline();
        //MapPolyline polyline1 = new MapPolyline();
        //polyline1.addPoint(MapPoint.mapPointWithGeoCoord(37.43380210000001, 127.08141320000004));
        //polyline.setTag(1000);
        //polyline 객체에 임의의 정수값 지정 가능(특정 폴리라인 찾을 때 사용 가능)
        //polyline.setLineColor(Color.argb(100, 0, 0, 255)); // Polyline 컬러 지정.

// Polyline 좌표 지정.
        //polyline 객체가 mapView에 등록된 후에는 점들을 추가해도 지도화면애 반영x
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43380210000001, 127.08141320000004));
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43374990000003,127.08111759999997));
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43378160000002,127.08068609999998));
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43422110000004,127.0805944));
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43428850000004, 127.08082939999997));
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43441749999999,127.0810457));
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43529936421379,127.07952713349141)); //15습지생태원
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43619319999999,127.07871640000008)); //16고층습지원
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.437062299999994, 127.07769869999993)); //18가을단풍길
//        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43831869999999,127.07702510000001)); //20라일락원

// Polyline 지도에 올리기.

        //mapView.addPolyline(polyline);
        //MapPointBounds mapPointBounds = new MapPointBounds(polyline.getMapPoints());
        //int padding = 100;
        //mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));

        MapPoint mapPoint1= MapPoint.mapPointWithGeoCoord(37.43374990000003, 127.08111759999997);//좌표에 마커를 찍는거
        MapPOIItem marker1 = new MapPOIItem();
        marker1.setItemName("중앙광장");//말풍선
        marker1.setTag(1);
        marker1.setMapPoint(mapPoint1);
        marker1.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint2= MapPoint.mapPointWithGeoCoord(37.43378160000002, 127.08068609999998);
        MapPOIItem marker2 = new MapPOIItem();
        marker2.setItemName("하늘정원");
        marker2.setTag(2);
        marker2.setMapPoint(mapPoint2);
        marker2.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint3= MapPoint.mapPointWithGeoCoord(37.43405849102472, 127.08087070726651);
        MapPOIItem marker3 = new MapPOIItem();
        marker3.setItemName("비스타정원");
        marker3.setTag(3);
        marker3.setMapPoint(mapPoint3);
        marker3.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker3.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint4= MapPoint.mapPointWithGeoCoord(37.43428850000004, 127.08082939999997);
        MapPOIItem marker4 = new MapPOIItem();
        marker4.setItemName("정통정원");
        marker4.setTag(4);
        marker4.setMapPoint(mapPoint4);
        marker4.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker4.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint5= MapPoint.mapPointWithGeoCoord(37.43441749999999, 127.0810457);
        MapPOIItem marker5 = new MapPOIItem();
        marker5.setItemName("작약원");
        marker5.setTag(5);
        marker5.setMapPoint(mapPoint5);
        marker5.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker5.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); //

        MapPoint mapPoint6= MapPoint.mapPointWithGeoCoord(37.434603651775085, 127.08064936148412);
        MapPOIItem marker6 = new MapPOIItem();
        marker6.setItemName("두꺼비분수");
        marker6.setTag(6);
        marker6.setMapPoint(mapPoint6);
        marker6.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양
        marker6.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint7= MapPoint.mapPointWithGeoCoord(37.43453092199691, 127.08023793052985);
        MapPOIItem marker7 = new MapPOIItem();
        marker7.setItemName("어린이정원");
        marker7.setTag(7);
        marker7.setMapPoint(mapPoint7);
        marker7.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker7.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint8= MapPoint.mapPointWithGeoCoord(37.43440920000004, 127.08032980000007);
        MapPOIItem marker8 = new MapPOIItem();
        marker8.setItemName("오감정원");
        marker8.setTag(8);
        marker8.setMapPoint(mapPoint8);
        marker8.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker8.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint9= MapPoint.mapPointWithGeoCoord(37.43431240322188, 127.08000265298142);
        MapPOIItem marker9 = new MapPOIItem();
        marker9.setItemName("수목관찰원");
        marker9.setTag(9);
        marker9.setMapPoint(mapPoint9);
        marker9.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker9.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint10= MapPoint.mapPointWithGeoCoord(37.43516570835482, 127.07920936229561);
        MapPOIItem marker10 = new MapPOIItem();
        marker10.setItemName("약초원");
        marker10.setTag(10);
        marker10.setMapPoint(mapPoint10);
        marker10.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker10.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint11= MapPoint.mapPointWithGeoCoord(37.43537991201445, 127.0788627385964);
        MapPOIItem marker11 = new MapPOIItem();
        marker11.setItemName("메타세쿼이아길");
        marker11.setTag(11);
        marker11.setMapPoint(mapPoint11);
        marker11.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker11.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint12= MapPoint.mapPointWithGeoCoord(37.43497920000003, 127.07983260000003);
        MapPOIItem marker12 = new MapPOIItem();
        marker12.setItemName("멸종위기 식물원");
        marker12.setTag(12);
        marker12.setMapPoint(mapPoint12);
        marker12.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker12.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint13= MapPoint.mapPointWithGeoCoord(37.43545487038663, 127.079948650858);
        MapPOIItem marker13 = new MapPOIItem();
        marker13.setItemName("그라스품종원");
        marker13.setTag(13);
        marker13.setMapPoint(mapPoint13);
        marker13.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker13.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint14= MapPoint.mapPointWithGeoCoord(37.4353757, 127.07966280000005);
        MapPOIItem marker14 = new MapPOIItem();
        marker14.setItemName("꽃무릇군락지");
        marker14.setTag(14);
        marker14.setMapPoint(mapPoint14);
        marker14.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker14.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint15= MapPoint.mapPointWithGeoCoord(37.43529936421379, 127.07952713349141);
        MapPOIItem marker15 = new MapPOIItem();
        marker15.setItemName("습지생태원");
        marker15.setTag(15);
        marker15.setMapPoint(mapPoint15);
        marker15.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker15.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint16= MapPoint.mapPointWithGeoCoord(37.43619319999999, 127.07871640000008);
        MapPOIItem marker16 = new MapPOIItem();
        marker16.setItemName("고층습지원");
        marker16.setTag(16);
        marker16.setMapPoint(mapPoint16);
        marker16.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker16.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint17= MapPoint.mapPointWithGeoCoord(37.436818099999975, 127.07884680000006);
        MapPOIItem marker17 = new MapPOIItem();
        marker17.setItemName("포도원");
        marker17.setTag(17);
        marker17.setMapPoint(mapPoint17);
        marker17.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker17.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint18= MapPoint.mapPointWithGeoCoord(37.437062299999994, 127.07769869999993);
        MapPOIItem marker18 = new MapPOIItem();
        marker18.setItemName("단풍나무길");
        marker18.setTag(18);
        marker18.setMapPoint(mapPoint18);
        marker18.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker18.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint19= MapPoint.mapPointWithGeoCoord(37.43732969999999 , 127.07770260000007 );
        MapPOIItem marker19 = new MapPOIItem();
        marker19.setItemName("억새원");
        marker19.setTag(19);
        marker19.setMapPoint(mapPoint19);
        marker19.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker19.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint20= MapPoint.mapPointWithGeoCoord(37.43831869999999 , 127.07702510000001 );
        MapPOIItem marker20 = new MapPOIItem();
        marker20.setItemName("라일락원");
        marker20.setTag(20);
        marker20.setMapPoint(mapPoint20);
        marker20.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker20.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPoint mapPoint21= MapPoint.mapPointWithGeoCoord(37.43484208519403 , 127.08035347746568 );
        MapPOIItem marker21 = new MapPOIItem();
        marker21.setItemName("A에코센터");
        marker21.setTag(21);
        marker21.setMapPoint(mapPoint21);
        marker21.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker21.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.





        /*
        mapView.addPOIItem(marker1);
        mapView.addPOIItem(marker2);
        mapView.addPOIItem(marker3);
        mapView.addPOIItem(marker4);
        mapView.addPOIItem(marker5);
        mapView.addPOIItem(marker6);
        mapView.addPOIItem(marker7);
        mapView.addPOIItem(marker8);
        mapView.addPOIItem(marker9);
        mapView.addPOIItem(marker10);
        mapView.addPOIItem(marker11);
        mapView.addPOIItem(marker12);
        mapView.addPOIItem(marker13);
        mapView.addPOIItem(marker14);
        mapView.addPOIItem(marker15);
        mapView.addPOIItem(marker16);
        mapView.addPOIItem(marker17);
        mapView.addPOIItem(marker18);
        mapView.addPOIItem(marker19);
        mapView.addPOIItem(marker20);
        mapView.addPOIItem(marker21);

         */



        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch ( v.getId() ) {
                    case R.id.home :
                        i = new Intent(getApplicationContext(), home.class);
                        startActivity(i);
                        break;
                    case R.id.button1:
                        mapView.removeAllPolylines();
                        mapView.removePolyline(polyline);
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43380210000001, 127.08141320000004));
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43374990000003,127.08111759999997));

                        polyline.setLineColor(Color.argb(100, 0, 0, 255));
                        mapView.removeAllPOIItems();

                        mapView.addPOIItem(marker21);
                        mapView.addPOIItem(marker1);
                        mapView.addPolyline(polyline);
                        break;
                    case R.id.button2:
                        //봄 마커, 폴리라인
                        mapView.removeAllPolylines();
                        mapView.removePolyline(polyline);
                        //mapView.addPolyline(polyline1);
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43380210000001, 127.08141320000004)); //입구
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43374990000003,127.08111759999997)); //1중앙광장
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.433893499999975, 127.0808012)); //1-3
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43378160000002,127.08068609999998)); //2하늘정원
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43422110000004,127.0805944)); //D곤충생태관
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43428850000004, 127.08082939999997)); //4전통정원
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.4343151,127.08108270000002)); //4-2
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43441749999999,127.0810457)); //5작약원
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.4344465,127.08083999999997)); //5-2
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43439109999999,127.08017229999996)); //5-4
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43466556958514,127.07969404356436)); //5-6
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43504329018735,127.07952524771031)); //5-8
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43529936421379,127.07952713349141)); //15습지생태원
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43546399999999, 127.07959540000002)); //15-2
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.4355383, 127.07924389999994)); //15-4
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43569849999999, 127.07892990000005)); //15-6
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.435923399999986, 127.07885570000008)); //15-8
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43619319999999,127.07871640000008)); //16고층습지원
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.4365068, 127.07823580000002)); //16-2
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43671449999999, 127.07783919999997)); //16-4
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.437062299999994, 127.07769869999993)); //18가을단풍길
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.4383086, 127.07716189999996)); //18-2
                        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.43831869999999,127.07702510000001)); //20라일락원

                        polyline.setLineColor(Color.argb(100, 255, 0, 0));
                        mapView.removeAllPOIItems();

                        mapView.addPOIItem(marker1); //중앙정원
                        mapView.addPOIItem(marker2); //하늘정원
                        mapView.addPOIItem(marker5); //작약원
                        mapView.addPOIItem(marker21); //에코센터전망대
                        mapView.addPOIItem(marker15); //습지생태원
                        mapView.addPOIItem(marker16); //고층습지원
                        mapView.addPOIItem(marker20); //라일락원
                        mapView.addPolyline(polyline);
                        break;
                    case R.id.button3:
                        //~~~~~~~~~
                        break;

                }
            }
        };
        home.setOnClickListener(cl);
        b1.setOnClickListener(cl);
        b2.setOnClickListener(cl);
        b3.setOnClickListener(cl);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mapView.setShowCurrentLocationMarker(false);
    }


    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        mapReverseGeoCoder.toString();
        onFinishReverseGeoCoding(s);
    }

    private void onFinishReverseGeoCoding(String result) {
    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        onFinishReverseGeoCoding("Fail");
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint currentLocation, float accuracyInMeters) {
        MapPoint.GeoCoordinate mapPointGeo = currentLocation.getMapPointGeoCoord();
        Log.i(LOG_TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }
/*
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {
                Log.d("@@@", "start");
                //위치 값을 가져올 수 있음
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                    Toast.makeText(home.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                }else {

                    Toast.makeText(home.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(home.this,
                Manifest.permission.ACCESS_FINE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED ) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음
            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(home.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(home.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(home.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(home.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }*/
    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
