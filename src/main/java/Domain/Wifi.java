package Domain;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class Wifi {
    private double dist;  //거리

    private  String adminNumber; // 관리 번호

    private String borough; // 자치구

    private String wifiName; // wifi 네임

    private String loadName; // 도로명 주소

    private String detailAddress; // 상세 주소

    private String installPosition; // 설치 위치

    private String installType; // 설치 유형

    private String installAgency; // 설치 기관

    private String serviceType; // 서비스구분

    private String netType; // 망종류

    private String installYear; // 설치년도

    private String inOutDoorType; // 실내외구분

    private String wifiConnEnv; // wifi 접속 환경

    private Double PosX;

    private Double PosY;

    private String dateTime;

}
