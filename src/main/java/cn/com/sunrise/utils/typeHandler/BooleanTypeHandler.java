/**
 * 元年软件
 * @author 胡奇伟
 * @date 2018年5月21日 下午8:33:05
 */
package cn.com.sunrise.utils.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 布尔类型转换处理类
 * 
 * @author 胡奇伟
 * @date 2018年5月21日 下午8:33:05
 * 
 */
public class BooleanTypeHandler extends BaseTypeHandler<Boolean> {

	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		 String name = rs.getString(columnName);
	     return rs.wasNull() ? null : nameOf(name);
	}

	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String name = rs.getString(columnIndex);
		return rs.wasNull() ? null : nameOf(name);
	}

	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String name = cs.getString(columnIndex);
        return cs.wasNull() ? null : nameOf(name);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean value, JdbcType jdbcType) throws SQLException {
		if (value == null) {
			ps.setString(i, "FALSE");
		} else if (jdbcType != null) {
			ps.setObject(i, value.toString().toUpperCase(), jdbcType.TYPE_CODE);			
		} else {
			ps.setString(i, value.toString().toUpperCase());
		}
	}
	
	/**
	 * 通过名称转换为布尔对象
	 * @param name 名称
	 * @return Boolean
	 */
	private Boolean nameOf(String name){
        try {        	
            return Boolean.valueOf(name);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + name + " to Boolean by name value.", ex);
        }
    }
}
