import React, {useEffect, useRef, useState} from 'react'
import axios from 'axios'


export default function Home(props){
    /* ------------------------------ Room 데이터 ------------------------------ */
    const [roomList,setRoomList] = useState([])

    useEffect(()=>{
        axios.get("/room/getroomlist")
            .then(res => { setRoomList(res.data); console.log(res.data); })
            .catch(err => console.log(err))
    }, [])
    /* ----------------------------------------------------------------------- */

    /* ------------------------------ 카카오 지도 API ------------------------------ */
    const mapContainer = useRef(null) //1. useRef() : 재렌더링 시 초기화 x
    const {kakao} = window                      //2. window : javascript 객체 [현재 window 전역객체 -> 메모리 정보 담겨져 있는 객체]
    const mapOption = {  center: new kakao.maps.LatLng(36.2683, 127.6358), level: 3 };

    useEffect( () => {
        var map = new kakao.maps.Map(mapContainer.current, mapOption);  // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다

        // 마커 클러스터러를 생성합니다
        var clusterer = new kakao.maps.MarkerClusterer({
            map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
            averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
            minLevel: 10 // 클러스터 할 최소 지도 레벨 //10레벨부터 클러스터 적용됨
        });

        // --------------- 마커 이미지 커스텀 --------------- //
        var markerImageUrl = 'http://localhost:8081/static/media/marker.4864bf08696f1439e095.png',
            markerImageSize = new kakao.maps.Size(40, 42), // 마커 이미지의 크기
            markerImageOptions = {
                offset : new kakao.maps.Point(20, 42)// 마커 좌표에 일치시킬 이미지 안의 좌표
            };
        // 마커 이미지를 생성한다
        var markerImage = new kakao.maps.MarkerImage(markerImageUrl, markerImageSize, markerImageOptions);

        // --------------- 클러스터러 --------------- //
        // * 데이터를 가져와서 마커를 생성하고 클러스터러에 추가 *
        var markers = roomList.map((position) => {
            //가져온 데이터의 좌표들을 반복문 돌리면서
            // 1. 마커 생성
            // 2. 생성된 마커들을 markers에 저장 [Why? map은 새로운 배열이 리턴됨] => 바로 리턴 안되게 커스텀 필요
            let marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(position.rlat, position.rlng), // 마커의 좌표
                image : markerImage, // 마커의 이미지
                map: map // 마커를 표시할 지도 객체
            });
            return marker
        });
        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    })
    /* -------------------------------------------------------------------------- */

    return (
        <div>
            {/* 카카오 map api */}
            <div id="map" ref={mapContainer} style={{width:'100%',height:'700px'}}></div>
        </div>
    )
}