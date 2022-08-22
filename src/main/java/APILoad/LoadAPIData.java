package APILoad;

import org.json.simple.parser.JSONParser;
import org.json.XML;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class LoadAPIData {
    public static void main(String[] args){
        String result = "";
        try{
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/key");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=43557a434270686332324f6c54444d");
            URL url=new URL(urlBuilder.toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = br.readLine();
            JSONObject json = XML.toJSONObject(result);
            System.out.println(json.toString());
            JSONParser jsonparser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonparser.parse(result);
            System.out.println(jsonObject.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
