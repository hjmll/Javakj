package com.hjm.ex3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: hjm
 * @Date: 2025/03/10/10:55
 * @Description:
 */
public class JDBCTemplate {
    private DataSource dataSource;
    public JDBCTemplate(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public void query(String sql) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // 处理查询结果
                //获取第一列数据
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
