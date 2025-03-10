package com.hjm.ex2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hjm
 * @Date: 2025/03/05/10:27
 * @Description: JDBCTemplate
 */
public class JDBCTemplate {
    public <T> T querForObject(String sql, Object[] params, Class<T> clazz){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = DataBaseUtil.getConnection();
            // 预编译sql
            pstmt = conn.prepareStatement(sql);
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            // 执行查询，获取结果集
            rs = pstmt.executeQuery();
            // 获取结果集的元数据，用于获取列信息
            ResultSetMetaData metaData = rs.getMetaData();
            // 获取结果集的列数
            int columnCount = metaData.getColumnCount();
            // 用于存储列名的列表
            List<String> columnNames = new ArrayList<>();
            // 遍历列，将列名添加到列表中
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            //遍历结果集
            if (rs.next()) {
                //创建对象实例
                T obj = clazz.getDeclaredConstructor().newInstance();
                for (String columnName : columnNames) {
                    Field field = null;
                    try {
                        // 通过反射获取对象的属性
                        field = clazz.getDeclaredField(columnName);
                    } catch (NoSuchFieldException e) {
                        // 如果没有找到对应的属性，跳过当前列
                        continue;
                    }
                    // 生成对应的 set 方法名
                    String setMethodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    // 通过反射获取 set 方法
                    Method setMethod = clazz.getMethod(setMethodName, field.getType());
                    // 从结果集中获取当前列的值
                    Object value = rs.getObject(columnName);
                    // 调用 set 方法将值设置到对象的属性中
                    setMethod.invoke(obj, value);
                }
                return obj;
            }

        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            DataBaseUtil.closeConnection(rs, pstmt, conn);
        }
        return null;
    }
}
