import React, {useEffect, useRef, useState} from 'react';
import {useDaumPostcodePopup} from 'react-daum-postcode';// npm i react-daum-postcode
import axios from 'axios'

import icon from '../../img/marker.png'     //아이콘 이미지 불러오기  //스프링 통합 배포시 : resources-static-static-media에 존재함

export default function RoomWrite(props) {
    /* ------------------------------ 카카오 주소 API ------------------------------ */
    //0. 검색된 주소,위도,경도 저장하는 state
    const [address,setAddress] = useState({name: '',lat: '',lng: ''})//주소이름,위도,경도
    //1. 다음 주소 API 사용하기 위한 API scriptUrl
    const open = useDaumPostcodePopup("//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js");
    //2. 다음 주소 검색 버튼 눌렀을 때 이벤트
    const handleClick = () => { open({ onComplete: handleComplete }); };
    //3. 다음 주소 검색 결과 이벤트
    const handleComplete = (data) => {
        //4. 주소 -> 좌표 변환 [카카오 공식 문서]
        //4-1. 카카오 주소정보검색 Rest API 쿼리스트링 이용해서 주소 전달
        console.log(data.address)
        axios.get("https://dapi.kakao.com/v2/local/search/address.json?query="+data.address
        , {headers: {Authorization:'KakaoAK fb47aa8a66dfdd51634b7c9b5e235fef'}})    //4-2.요청 옵션에 인증키 넣기 [RestAPI KEY]
            .then(res=>{    //4-3. 요청 결과 [카카오 문서 참고]
                //4. json 결과에서 좌표 추출
                const location = res.data.documents[0]//documents[0]: [카카오 공식 문서]보고 쓴 것
                //5. state 업데이트
                setAddress({name: data.address,lat: location.y,lng: location.x})
            })
    };
    /* --------------------------------------------------------------------------- */


    /* ------------------------------ 카카오 지도 API ------------------------------ */
    const mapContainer = useRef(null) //1. useRef() : 재렌더링 시 초기화 x
    const {kakao} = window                      //2. window : javascript 객체 [현재 window 전역객체 -> 메모리 정보 담겨져 있는 객체]
    //상위에 있는 JS에서 kakao 객체 필요 [kakao 객체]
    //<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 사용하세요"></script>

    const mapOption = {     //3. 지도 옵션 [위치, 확대 레벨]
        center: new kakao.maps.LatLng(address.lat, address.lng), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    useEffect( () => {  //4. 렌더링 될때마다 map 생성
        //1. 해당 div에 옵션을 넣은 map 객체 생성
        var map = new kakao.maps.Map(mapContainer.current, mapOption);  // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        //2. https://apis.map.kakao.com/web/wizard/ 에서 마커 체크-이미지 마커 체크 후 복붙
        // 마커 이미지의 주소
        var markerImageUrl = 'http://localhost/8081/static/media/marker.4864bf08696f1439e095.png',
            markerImageSize = new kakao.maps.Size(40, 42), // 마커 이미지의 크기
            markerImageOptions = {
                offset : new kakao.maps.Point(20, 42)// 마커 좌표에 일치시킬 이미지 안의 좌표
            };

        // 마커 이미지를 생성한다
        var markerImage = new kakao.maps.MarkerImage(markerImageUrl, markerImageSize, markerImageOptions);

        // 지도에 마커를 생성하고 표시한다
        var marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(address.lat, address.lng), // 마커의 좌표
            image : markerImage, // 마커의 이미지
            map: map // 마커를 표시할 지도 객체
        });
    })
    /* -------------------------------------------------------------------------- */

    //4. 방 등록 버튼을 눌렀을 때 이벤트
    const onWrite = () =>{

    }

    return(
        <div>
            <h3>방 등록</h3>
            <form>
                방이름 : <input type={"text"} name={"rtitle"}/>
                가격 : <input type={"text"} name={"rprice"}/>
                거래방식 :
                <select name={"rtrans"}>
                    <option value="매매">매매</option>
                    <option value="전세">전세</option>
                    <option value="월세">월세</option>
                </select>
                이미지 : <input type={"file"} multiple={"multiple"} name={"rimg"} />
                {/* 카카오 주소 API */}
                위치[좌표] :
                <div>{address.name}</div>
                <button type='button' onClick={handleClick}> 방 위치 찾기 </button>

                {/* 카카오 map */}
                <div id="map" ref={mapContainer} style={{width:'100%',height:'350px'}}></div>

                <button type={"button"} onClick={onWrite}>등록</button>
            </form>
        </div>
    )
}