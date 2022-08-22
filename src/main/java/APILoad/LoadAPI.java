package APILoad;
import java.io.*;
import java.net.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LoadAPI {
    public static void main(String[] args) throws IOException{
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/sample/xml/TbPublicWifiInfo/1/5/");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=43557a434270686332324f6c54444d");
        URL url=new URL(urlBuilder.toString());
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type","application/json");
        System.out.println("Responsecode:"+conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode()>=200&&conn.getResponseCode()<=300){
            rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }else{
            rd=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while((line=rd.readLine())!=null){
            sb.append(line +"\n");
        }
        rd.close();
        conn.disconnect();
        JSONObject json = null;
        try {
            Reader reader = new FileReader("src/main/java/APILoad/wifiInfo.json");
            JSONParser parser = new JSONParser();
            json = (JSONObject)parser.parse(reader);
//            JSONObject jArray1 = json.get()
//            JSONArray jArray = jArray1.getJSONArray("row");
//            for (int i = 0; i < 10; i++) {
//                JSONObject obj = jArray.getJSONObject(i);
//                String AdminNumber = obj.getString("X_SWIFI_MGR_NO");
//                String Borough = obj.getString("X_SWIFI_WRDOFC");
//                String WifiName = obj.getString("X_SWIFI_MAIN_NM");
//                String LoadAddress = obj.getString("X_SWIFI_ADRES1");
//                String DetailAddress = obj.getString("X_SWIFI_ADRES2");
//                String InstallFloor = obj.getString("X_SWIFI_INSTL_FLOOR");
//                String InstallType = obj.getString("X_SWIFI_INSTL_TY");
//                String InstallAgency = obj.getString("X_SWIFI_INSTL_MBY");
//                String ServiceType = obj.getString("X_SWIFI_SVC_SE");
//                String NetType = obj.getString("X_SWIFI_CMCWR");
//                String InstallYear = obj.getString("X_SWIFI_CNSTC_YEAR");
//                String INOUTDoorType = obj.getString("X_SWIFI_INOUT_DOOR");
//                String WifiConnEnv = obj.getString("X_SWIFI_REMARS3");
//                String Posx = obj.getString("LAT");
//                String Posy = obj.getString("LNT");
//                String WorkDate = obj.getString("WORK_DTTM");
//                System.out.println(AdminNumber + Borough + WifiName + LoadAddress + DetailAddress + InstallFloor + InstallType
//                + InstallAgency + ServiceType + NetType + InstallYear + INOUTDoorType + WifiConnEnv + Posx + Posy + WorkDate);
//            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json.toString());
    }
}
