package cn.com.sunrise.controller;

import cn.com.sunrise.controller.common.BaseController;
import cn.com.sunrise.mo.Student;
import cn.com.sunrise.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {

    private  static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @GetMapping("/toIndex/{navigatorName}")
    public ModelAndView toIndex(@PathVariable("navigatorName") String navigatorName){
        return new ModelAndView("views/main/"+navigatorName+"Index");
    }


}
