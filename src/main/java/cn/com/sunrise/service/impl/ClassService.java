package cn.com.sunrise.service.impl;

import cn.com.sunrise.dao.IClassDAO;
import cn.com.sunrise.mo.ClassVO;
import cn.com.sunrise.service.IClassService;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassService implements IClassService {

    private  static final Logger logger = LoggerFactory.getLogger(ClassService.class);

    @Autowired
    private IClassDAO classDAO;

    @Override
    public MessageReturn saveOrUpdateClass(ClassVO classVO) {
        logger.info("进入新增班级的方法----saveOrUpdateClass");
        MessageReturn msg = new MessageReturn();
        try {
            if(classVO.getId().equals("1")){
                classVO.setId(UUID.randomUUID().toString());
                classDAO.saveClass(classVO);
            }else{
                classDAO.updateClass(classVO);
            }
            msg.setStatus(0);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(-500).setErrmsg(e.getMessage());
            logger.info(msg.toString());
            if(e.getMessage()!=null&&e.getMessage().contains("class_name")){
                msg.setErrmsg("班级名称重复！");
            }
        }
        return msg;
    }

    @Override
    public List<ClassVO> getClassList(Pager pager) {
        logger.info("进入ClassService的getClassList()-------------");
        List<ClassVO> list = null;
        try {
            list=classDAO.getClassList(pager);
        }catch (Exception e){
            logger.error(new MessageReturn().setErrmsg(e.getMessage()).toString());
        }
        logger.info("退出ClassService的getClassList()-------------");
        return list;
    }

    @Override
    public MessageReturn deleteClass(String id) {
        logger.info("进入ClassService的deleteClass()-------------");
        MessageReturn msg = new MessageReturn();
        try {
            classDAO.deleteClass(id);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(-500).setErrmsg(e.getMessage());
            logger.error(msg.toString());
        }
        logger.info("退出ClassService的deleteClass()-------------");
        return msg;
    }
}
