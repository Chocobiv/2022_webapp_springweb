import React, {useEffect, useRef, useState} from 'react';
import {useDaumPostcodePopup} from 'react-daum-postcode';

export default function RoomWrite(props) {
    /* ------------------------------ 카카오 주소 API ------------------------------ */
    //0. 검색된 주소 저장하는 state
    const [address,setAddress] = useState('')
    //1. 다음 주소 API 사용하기 위한 API scriptUrl
    const open = useDaumPostcodePopup("//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js");
    //2. 다음 주소 검색 버튼 눌렀을 때 이벤트
    const handleClick = () => { open({ onComplete: handleComplete }); };
    //3. 다음 주소 검색 결과 이벤트
    const handleComplete = (data) => {
        console.log(data.address); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
    };
    /* --------------------------------------------------------------------------- */


    /* ------------------------------ 카카오 지도 API ------------------------------ */
    const mapContainer = useRef(null) //1. useRef() : 재렌더링 시 초기화 x
    const {kakao} = window                      //2. window : javascript 객체 [현재 window 전역객체 -> 메모리 정보 담겨져 있는 객체]
    //상위에 있는 JS에서 kakao 객체 필요 [kakao 객체]
    //<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 사용하세요"></script>

    const mapOption = {     //3. 지도 옵션 [위치, 확대 레벨]
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    useEffect( () => {  //4. 렌더링 될때마다 map 생성
        console.log(mapContainer)
        var map = new kakao.maps.Map(mapContainer.current, mapOption);  // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
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
                <div>{address}</div>
                <button type='button' onClick={handleClick}>
                    방 위치 찾기
                </button>

                {/* 카카오 map */}
                <div id="map" ref={mapContainer} style={{width:'100%',height:'350px'}}></div>

                <button type={"button"} onClick={onWrite}>등록</button>
            </form>
        </div>
    )
}