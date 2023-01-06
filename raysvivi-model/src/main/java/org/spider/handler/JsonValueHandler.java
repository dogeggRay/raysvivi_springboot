package org.spider.handler;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonValueHandler implements TypeHandler {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        try {
            preparedStatement.setString(i, JSONObject.toJSONString(parameter));
        } catch (Exception e) {
            throw new SQLException("字段处理失败");
        }
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        return this.decryptData(rs.getString(columnName));
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.decryptData(rs.getString(columnIndex));
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.decryptData(cs.getString(columnIndex));
    }

    private Object decryptData(String string) throws SQLException {
        try {
            if(string.indexOf("{") == 0){
                return JSONObject.parseObject(string);
            }else{
                return JSONArray.parseArray(string);
            }
        } catch (Exception e) {
            throw new SQLException("字段处理失败");
        }
    }
}
