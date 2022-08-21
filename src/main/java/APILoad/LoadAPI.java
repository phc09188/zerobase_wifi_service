package APILoad;
import java.io.*;
import java.net.*;
public class LoadAPI {
    public static void main(String[] args) throws IOException{
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/sample/xml/TbPublicWifiInfo/1/5/");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=43557a434270686332324f6c54444d");
        urlBuilder.append("&"+URLEncoder.encode("numOfRows","UTF-8")+"="+URLEncoder.encode("10","UTF-8"));
        /*한 페이지 결과 수*/
        urlBuilder.append("&"+URLEncoder.encode("pageNo","UTF-8")+"="+URLEncoder.encode("1","UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("stationName","UTF-8")+"="+URLEncoder.encode("수내동","UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("dataTerm","UTF-8")+"="+URLEncoder.encode("DAILY","UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("ver","UTF-8")+"="+URLEncoder.encode("1.3","UTF-8"));
        URL url=new URL(urlBuilder.toString());
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type","application/json");
        System.out.println("Response code: "+conn.getResponseCode());
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
        System.out.println(sb.toString());
    }
}
