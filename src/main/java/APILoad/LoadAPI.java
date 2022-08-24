package APILoad;
import java.io.*;
import java.net.*;

import Domain.Wifi;
import Service.WifiService;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;


public class LoadAPI {
    public static Integer Load(){
        String result = "";
        try{
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/43557a434270686332324f6c54444d/json/TbPublicWifiInfo/1/1000/");
            URL url=new URL(urlBuilder.toString());
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject TbPublicWifiInfo = (JSONObject)jsonObject.get("TbPublicWifiInfo");
            JSONArray row = (JSONArray)TbPublicWifiInfo.get("row");
            WifiService ws = new WifiService();
            int cnt = row.size()/1000;
            int rest = row.size()%1000;
            for (int j = 0; j < cnt; j++) {
                for (int i = 0; i < 1000; i++) {
                    JSONObject obj = (JSONObject) row.get(j*1000 + i);
                    String adminNum = (String) obj.get("X_SWIFI_MGR_NO");
                    String borough = (String) obj.get("X_SWIFI_WRDOFC");
                    String wifiname = (String) obj.get("X_SWIFI_MAIN_NM");
                    String loadname = (String) obj.get("X_SWIFI_ADRES1");
                    String detailAddress = (String) obj.get("X_SWIFI_ADRES2");
                    String installPosition = (String) obj.get("X_SWIFI_INSTL_FLOOR");
                    String installType = (String) obj.get("X_SWIFI_INSTL_TY");
                    String installAgency = (String) obj.get("X_SWIFI_INSTL_MBY");
                    String serviceType = (String) obj.get("X_SWIFI_SVC_SE");
                    String netType = (String) obj.get("X_SWIFI_CMCWR");
                    String installYear = (String) obj.get("X_SWIFI_CNSTC_YEAR");
                    String inoutdoor = (String) obj.get("X_SWIFI_INOUT_DOOR");
                    String wifiConnEnv = (String) obj.get("X_SWIFI_REMARS3");
                    Double posx = Double.valueOf((String) obj.get("LAT"));
                    Double posy = Double.valueOf((String) obj.get("LNT"));
                    String datetime = (String) obj.get("WORK_DTTM");
                    /*Wifi wifi = new Wifi(adminNum, borough, wifiname, loadname, detailAddress, installPosition, installType,
                            installAgency, serviceType, netType, installYear, inoutdoor, wifiConnEnv, posx, posy, datetime);
                    ws.register(wifi);*/
                }
            }
            for (int i = 0; i < rest; i++) {
                JSONObject obj = (JSONObject) row.get(cnt*1000 + i);
                String adminNum = (String) obj.get("X_SWIFI_MGR_NO");
                String borough = (String) obj.get("X_SWIFI_WRDOFC");
                String wifiname = (String) obj.get("X_SWIFI_MAIN_NM");
                String loadname = (String) obj.get("X_SWIFI_ADRES1");
                String detailAddress = (String) obj.get("X_SWIFI_ADRES2");
                String installPosition = (String) obj.get("X_SWIFI_INSTL_FLOOR");
                String installType = (String) obj.get("X_SWIFI_INSTL_TY");
                String installAgency = (String) obj.get("X_SWIFI_INSTL_MBY");
                String serviceType = (String) obj.get("X_SWIFI_SVC_SE");
                String netType = (String) obj.get("X_SWIFI_CMCWR");
                String installYear = (String) obj.get("X_SWIFI_CNSTC_YEAR");
                String inoutdoor = (String) obj.get("X_SWIFI_INOUT_DOOR");
                String wifiConnEnv = (String) obj.get("X_SWIFI_REMARS3");
                Double posx = Double.valueOf((String) obj.get("LAT"));
                Double posy = Double.valueOf((String) obj.get("LNT"));
                String datetime = (String) obj.get("WORK_DTTM");
                /*Wifi wifi = new Wifi(adminNum, borough, wifiname, loadname, detailAddress, installPosition, installType,
                        installAgency, serviceType, netType, installYear, inoutdoor, wifiConnEnv, posx, posy, datetime);
                ws.register(wifi);*/
            }
            return row.size();
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

}
