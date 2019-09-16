/**
 * 元年软件
 *
 * @author 胡奇伟
 * @date 2018-06-14 12:47
 **/
package cn.com.sunrise.utils.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 字符List转换处理类
 *
 * @author 胡奇伟
 * @date 2018-06-14 12:47
 **/
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

	/** 
	 * 分隔符
	 */ 
	private static final String SPLIT_STRING = ";";
	
	@Override
	public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return toList(rs.getString(columnName));
	}
	
	@Override
	public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return toList(rs.getString(columnIndex));
	}
	
	@Override
	public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return toList(cs.getString(columnIndex));
	}
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
		if(parameter != null) {
			StringBuffer strValue = new StringBuffer();
			for (int j = 0; j < parameter.size(); j++) {
				if (j > 0) {
					strValue.append(SPLIT_STRING);
				}
				strValue.append(parameter.get(j));
			}
			ps.setString(i, strValue.toString());
		} else {
			ps.setString(i, "");
		}
	}	

	private List<String> toList(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		String[] values = str.split(SPLIT_STRING);
		return Arrays.asList(values);
	}
}
