package cn.com.sunrise.service;

import cn.com.sunrise.mo.ClassVO;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;

import java.util.List;

public interface IClassService {


    MessageReturn saveOrUpdateClass(ClassVO classVO);


    List<ClassVO> getClassList(Pager pager);


    MessageReturn deleteClass(String id);

}
