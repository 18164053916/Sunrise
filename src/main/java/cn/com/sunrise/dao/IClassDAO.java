package cn.com.sunrise.dao;

import cn.com.sunrise.mo.ClassVO;
import cn.com.sunrise.utils.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IClassDAO {

    void saveClass(@Param("classVO") ClassVO classVO);

    void updateClass(@Param("classVO") ClassVO classVO);

    List<ClassVO> getClassList(@Param("pager") Pager pager);

    void deleteClass(String id);

}
