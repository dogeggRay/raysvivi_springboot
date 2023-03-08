package org.raysvivi.blog.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class StrsTypeHandler extends BaseTypeHandler<List<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        JSONArray jsonArray = JSONArray.parseArray( rs.getString(columnName));
        return jsonArray;
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        JSONArray jsonArray = JSONArray.parseArray( rs.getString(columnIndex));
        return jsonArray;
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        JSONArray jsonArray = JSONArray.parseArray( cs.getString(columnIndex));
        return jsonArray;
    }
}