package notifier.repository;

import notifier.domain.Menu;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

public class JdbcMenuRepository implements MenuRepository{
    private final DataSource dataSource;
    public JdbcMenuRepository(DataSource dataSource){this.dataSource = dataSource;}
    @Override
    public Menu save(Menu menu) {
        String sql = "INSERT INTO meal (date, meal_time, item) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (String item : menu.getItems()){
                pstmt.setString(1, menu.getDate());
                pstmt.setString(2,menu.getMealTime());
                pstmt.setString(3,item);
                pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()){
                    menu.setId(rs.getLong(1));
                }
                else {
                    throw new SQLException("id 조회 실패");
                }
            }
            return menu;

        }catch (Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn, pstmt, rs);
        }

    }

    @Override
    public Optional<Menu> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Menu> findByDateAndMealTime(String date, String mealTime) {
        String sql = "SELECT * FROM meal WHERE date = ? AND meal_time = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            pstmt.setString(2, mealTime);
            rs = pstmt.executeQuery();

            Optional<Menu> optionalMenu = Optional.empty();

            while (rs.next()) {
                if (!optionalMenu.isPresent()) {
                    Menu menu = new Menu();
                    menu.setId(rs.getLong("id"));
                    menu.setDate(rs.getString("date"));
                    menu.setMealTime(rs.getString("meal_time"));

                    List<String> items = new ArrayList<>();
                    items.add(rs.getString("item"));
                    menu.setItems(items);

                    optionalMenu = Optional.of(menu);
                } else {
                    ResultSet finalRs = rs;
                    optionalMenu.ifPresent(menu -> {
                        try {
                            menu.getItems().add(finalRs.getString("item"));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }

            return optionalMenu;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Menu> findAll() {
        return null;
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    private Connection getConnection() {return DataSourceUtils.getConnection(dataSource);}
}
