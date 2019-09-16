package cn.com.sunrise.controller;

import cn.com.sunrise.mo.ClassVO;
import cn.com.sunrise.service.IClassService;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private IClassService classService;


    @PutMapping("/saveOrUpdateClass")
    public MessageReturn saveOrUpdateClass(@RequestBody ClassVO classVO) {
        return classService.saveOrUpdateClass(classVO);
    }

    @PostMapping("/getClassList")
    public List<ClassVO> getClassList(@RequestBody Pager pager){
        return classService.getClassList(pager);
    }


    @DeleteMapping("/deleteClass/{id}")
    public MessageReturn deleteClass(@PathVariable String id) {
        return classService.deleteClass(id);
    }

}
