package DB;

import Domain.History;
import Domain.Wifi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiTable {
    public List<Wifi> NearByWifi(double x, double y){
        String url = "jdbc:mariadb://127.0.0.1:3306/wifitest";
        String dbuserId = "wifiuser";
        String dbPassword = "wifi";
        List<Wifi> nearby = new ArrayList<>();
        // 1. 드라이버로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparestatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(url, dbuserId, dbPassword);
            String sql = " SELECT (6371*acos(cos(radians(?))*cos(radians(`Y좌표`))*cos(radians(`X좌표`) " +
                    " -radians(?))+sin(radians(?))*sin(radians(`Y좌표`)))) AS distance " +
                    " ,wf.* " +
                    " FROM wifi wf " +
                    " ORDER BY distance " +
                    " limit 20 ";
            preparestatement = connection.prepareStatement(sql);
            preparestatement.setDouble(1,x);
            preparestatement.setDouble(2,y);
            preparestatement.setDouble(3,x);
            rs = preparestatement.executeQuery();
            while(rs.next()) {
                Wifi wifi = new Wifi();
                wifi.setDist(rs.getDouble("distance"));
                wifi.setAdminNumber(rs.getString("관리번호"));
                wifi.setWifiName(rs.getString("와이파이명"));
                wifi.setBorough(rs.getString("자치구"));
                wifi.setLoadName(rs.getString("도로명주소"));
                wifi.setDetailAddress(rs.getString("상세주소"));
                wifi.setInstallPosition(rs.getString("설치위치"));
                wifi.setInstallType(rs.getString("설치유형"));
                wifi.setInstallAgency(rs.getString("설치기관"));
                wifi.setServiceType(rs.getString("서비스구분"));
                wifi.setNetType(rs.getString("망종류"));
                wifi.setInstallYear(rs.getString("설치년도"));
                wifi.setInOutDoorType(rs.getString("실내외구분"));
                wifi.setWifiConnEnv(rs.getString("WIFI접속환경"));
                wifi.setPosX(rs.getDouble("X좌표"));
                wifi.setPosY(rs.getDouble("Y좌표"));
                wifi.setDateTime(rs.getString("작업일자"));
                nearby.add(wifi);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(preparestatement != null &&!preparestatement.isClosed()) {
                    preparestatement.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return nearby;
    }
    public void register(List<Wifi> wifilist) {
        String url = "jdbc:mariadb://127.0.0.1:3306/wifitest";
        String dbuserId = "wifiuser";
        String dbPassword = "wifi";
        // 1. 드라이버로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparestatement = null;

        //2. 커넥션 객체 생성
        try {
            connection = DriverManager.getConnection(url, dbuserId, dbPassword);
            connection.setAutoCommit(false);
            String sql = " insert into wifi( `관리번호`,`와이파이명`,`자치구`, `도로명주소`,`상세주소` " +
                    " ,`설치위치`,`설치유형`,`설치기관`,`서비스구분`,`망종류`,`설치년도`,`실내외구분`,`WIFI접속환경`,`X좌표`,`Y좌표`,`작업일자`) " +
                    " values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,? ,? ,? ,?) ";
            preparestatement = connection.prepareStatement(sql);
            int cnt = 0;
            for(Wifi wifi : wifilist) {
                preparestatement.setString(1, wifi.getAdminNumber());
                preparestatement.setString(2, wifi.getWifiName());
                preparestatement.setString(3, wifi.getBorough());
                preparestatement.setString(4, wifi.getLoadName());
                preparestatement.setString(5, wifi.getDetailAddress());
                preparestatement.setString(6, wifi.getInstallPosition());
                preparestatement.setString(7, wifi.getInstallType());
                preparestatement.setString(8, wifi.getInstallAgency());
                preparestatement.setString(9, wifi.getServiceType());
                preparestatement.setString(10, wifi.getNetType());
                preparestatement.setString(11, wifi.getInstallYear());
                preparestatement.setString(12, wifi.getInOutDoorType());
                preparestatement.setString(13, wifi.getWifiConnEnv());
                preparestatement.setDouble(14, wifi.getPosX());
                preparestatement.setDouble(15, wifi.getPosY());
                preparestatement.setString(16, wifi.getDateTime());
                cnt++;
                preparestatement.addBatch();
                preparestatement.clearParameters();
                if(cnt % 1000 == 0) {
                    preparestatement.executeBatch();
                    preparestatement.clearBatch();
                    connection.commit();
                }
            }
            preparestatement.executeBatch();
            preparestatement.clearParameters();
            connection.commit();
            System.out.println(" 저장 성공 ");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(preparestatement != null &&!preparestatement.isClosed()) {
                    preparestatement.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
