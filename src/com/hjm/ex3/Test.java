package com.hjm.ex3;

/**
 * @Author: hjm
 * @Date: 2025/03/10/11:00
 * @Description: 测试类
 */
public class Test {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ex";
        String username = "root";
        String password = "root";
        // 使用 BasicDataSource
        DataSource basicDataSource  = new BasicDataSource(url,username,password);
        JDBCTemplate basicJdbcTemplate = new JDBCTemplate(basicDataSource);
        basicJdbcTemplate.query("SELECT * FROM user");

        //分割线
        System.out.println("---------------------------------------------");

        // 使用 DBCPDataSource
        DataSource dbcpDataSource = new DBCPDataSource(url, username, password);
        JDBCTemplate dbcpJdbcTemplate = new JDBCTemplate(dbcpDataSource);
        dbcpJdbcTemplate.query("SELECT * FROM user");
    }



}
