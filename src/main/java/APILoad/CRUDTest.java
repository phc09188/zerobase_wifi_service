package APILoad;


import DB.WifiTable;
import Domain.Wifi;
import Service.HistoryService;
import Service.WifiService;

import java.util.List;

public class CRUDTest {
    public static void main(String[] args) throws Exception{
        WifiTable wt = new WifiTable();
        List<Wifi> list = wt.NearByWifi(37.5544069,126.8998666);
//        WifiService ws = new WifiService();
//        ws.WifiMain();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
