package APILoad;


import Service.HistoryService;
import Service.WifiService;

public class CRUDTest {
    public static void main(String[] args) throws Exception{
        WifiService ws = new WifiService();
        long cnt = ws.WifiMain();
        System.out.println(cnt);
    }
}
