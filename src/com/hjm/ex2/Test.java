package com.hjm.ex2;

/**
 * @Author: hjm
 * @Date: 2025/03/05/10:50
 * @Description: 测试类
 */
public class Test {
    public static void main(String[] args) {
        JDBCTemplate template = new JDBCTemplate();
        String sql = "SELECT * FROM User WHERE id = ?";
        Object[] params = {1};
        User user = template.querForObject(sql, params, User.class);
        System.out.println(user);
    }
}
