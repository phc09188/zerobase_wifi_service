package Service;

import Domain.History;
import Domain.Wifi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    public List<History> Historylist() {
        List<History> wifihistory = new ArrayList<>();
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
        ResultSet rs = null;
        //2. 커넥션 객체 생성
        try {
            connection = DriverManager.getConnection(url, dbuserId, dbPassword);

            String sql = "  select * " +
                    " from history ";
            preparestatement = connection.prepareStatement(sql);
            // 3. 쿼리 실행
            rs = preparestatement.executeQuery();
            while(rs.next()) {
                History history = new History();
                history.setID(rs.getInt("아이디"));
                history.setPosx(rs.getDouble("X좌표"));
                history.setPosy(rs.getDouble("Y좌표"));
                history.setCurDate(rs.getString("작업일자"));

                wifihistory.add(history);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
        return wifihistory;
    }
    public void register(double x, double y) {
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
        ResultSet rs = null;

        //2. 커넥션 객체 생성
        try {
            connection = DriverManager.getConnection(url, dbuserId, dbPassword);

            String sql = " insert into history(`아이디`,`X좌표`,`Y좌표`, `작업일자`) " +
                    " values(?,?,now()) ";
            preparestatement = connection.prepareStatement(sql);
            preparestatement.setDouble(1,x);
            preparestatement.setDouble(2,y);
            // 3. 쿼리 실행
            int affected_row = preparestatement.executeUpdate();
            if( affected_row >0) {
                System.out.println("저장 성공");
            }else {
                System.out.println("저장 실패 ");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
    public void withdraw(double x, double y) {
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
        ResultSet rs = null;
        //추가 객체 정보
        //2. 커넥션 객체 생성
        try {
            connection = DriverManager.getConnection(url, dbuserId, dbPassword);

            String sql = " delete from history where (`X좌표`) = ? and (`Y좌표`) = ? ";
            preparestatement = connection.prepareStatement(sql);
            preparestatement.setDouble(1,x);
            preparestatement.setDouble(2, y);

            // 3. 쿼리 실행
            int affected_row = preparestatement.executeUpdate();
            if( affected_row >0) {
                System.out.println("탈퇴 성공");
            }else {
                System.out.println("탈퇴 실패 ");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
