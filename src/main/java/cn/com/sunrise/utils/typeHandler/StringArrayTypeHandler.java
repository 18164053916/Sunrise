/**
 * 元年软件
 *
 * @author 李永华
 * @date 2018-06-13 15:47
 **/
package cn.com.sunrise.utils.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 字符数组转换处理类
 *
 * @author 李永华
 * @date 2018-06-13 15:47
 **/
public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {

	/** 
	 * 分隔符
	 */ 
	private static final String SPLIT_STRING = ";";
	
	@Override
	public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return toArray(rs.getString(columnName));
	}
	
	@Override
	public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return toArray(rs.getString(columnIndex));
	}
	
	@Override
	public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return toArray(cs.getString(columnIndex));
	}
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		if(parameter != null && parameter.length > 0) {
			StringBuffer strValue = new StringBuffer();
			for (int j = 0; j < parameter.length; j++) {
				if (j > 0) {
					strValue.append(SPLIT_STRING);
				}
				strValue.append(parameter[j]);
			}
			ps.setString(i, strValue.toString());
		} else {
			ps.setString(i, "");
		}
	}	

	private String[] toArray(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		return str.split(SPLIT_STRING);
	}
}
