package Service;

import DB.WifiTable;
import Domain.Wifi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiService {
    final static String key = "43557a434270686332324f6c54444d";
    public long WifiMain() throws Exception{
        JSONParser jsonParser = new JSONParser();
        long allcnt = 0;
        WifiTable wifitable = new WifiTable();
        List<Wifi> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            JSONObject json = (JSONObject) jsonParser.parse(readUrl(i));
            JSONObject TbPublicWifiInfo = (JSONObject) json.get("TbPublicWifiInfo");
            allcnt = (long) TbPublicWifiInfo.get("list_total_count");
            JSONArray jsonlist = (JSONArray) TbPublicWifiInfo.get("row");
            for (int j = 0; j < jsonlist.size(); j++) {
                JSONObject a = (JSONObject) jsonlist.get(j);
                Wifi wifi = new Wifi();
                wifi.setAdminNumber(String.valueOf(a.get("X_SWIFI_MGR_NO")));
                wifi.setBorough(String.valueOf(a.get("X_SWIFI_WRDOFC")));
                wifi.setWifiName(String.valueOf(a.get("X_SWIFI_MAIN_NM")));
                wifi.setLoadName(String.valueOf(a.get("X_SWIFI_MAIN_NM")));
                wifi.setDetailAddress(String.valueOf(a.get("X_SWIFI_ADRES2")));
                wifi.setInstallPosition(String.valueOf(a.get("X_SWIFI_INSTL_FLOOR")));
                wifi.setInstallType(String.valueOf(a.get("X_SWIFI_INSTL_TY")));
                wifi.setInstallAgency(String.valueOf(a.get("X_SWIFI_INSTL_MBY")));
                wifi.setServiceType(String.valueOf(a.get("X_SWIFI_SVC_SE")));
                wifi.setNetType(String.valueOf(a.get("X_SWIFI_CMCWR")));
                wifi.setInstallYear(String.valueOf(a.get("X_SWIFI_CNSTC_YEAR")));
                wifi.setInOutDoorType(String.valueOf(a.get("X_SWIFI_INOUT_DOOR")));
                wifi.setWifiConnEnv(String.valueOf(a.get("X_SWIFI_REMARS3")));
                wifi.setPosX(Double.parseDouble(String.valueOf(a.get("LAT"))));
                wifi.setPosY(Double.parseDouble(String.valueOf(a.get("LNT"))));
                wifi.setDateTime(String.valueOf(a.get("WORK_DTTM")));
                list.add(wifi);
            }
        }
        wifitable.register(list);
        return allcnt;
    }
    public static String readUrl(int k) throws Exception {
        int start_idx = 1 + (1000 * k);
        int end_idx = 1000 + (1000 * k);
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") );
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(Integer.toString(start_idx),"UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(Integer.toString(end_idx),"UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }
}
