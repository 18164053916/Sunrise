package cn.com.sunrise.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by caojialin on 2018/3/16.
 * Desc:
 */
public abstract class BaseController {

    protected final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private ThreadLocal<Model> models = new ThreadLocal<Model>();
    private ThreadLocal<HttpServletResponse> responses = new ThreadLocal<HttpServletResponse>();
    private ThreadLocal<Long> timePoints = new ThreadLocal<Long>();

    protected static final String SUCCESS = "succ";
    protected static final String FAIL = "fail";

    protected BaseController wrap(Model model) {
        models.set(model);
        return this;
    }

    protected BaseController wrap(Model model, HttpServletResponse response) {
        models.set(model);
        responses.set(response);
        return this;
    }

    public BaseController put(String key, Object value) {
        models.get().addAttribute(key, value);
        return this;
    }

    public BaseController putAll(Map<String, Object> map) {
        models.get().addAllAttributes(map);
        return this;
    }

    public <T> BaseController list(List<T> list) {
        models.get().addAttribute("data_list", list);
        return this;
    }

    public BaseController statu(int statu) {
        responses.get().setStatus(statu);
        return this;
    }

    public String view(String viewPath) {
        LOGGER.debug("Request has been done!");
        return viewPath;
    }

    public String done(String viewPath) {
        models.remove();
        responses.remove();
        LOGGER.debug("Request has been done!");
        return viewPath;
    }

    protected void start() {
        timePoints.set(System.currentTimeMillis());
    }

    protected void end(String content) {
        end(content, true);
    }

    /**
     *
     * @param content
     * @param over 是否结束  start-end-end ...
     */
    protected void end(String content, boolean over) {
        long end = System.currentTimeMillis();
        LOGGER.info(content + ", 耗时：" + (end - timePoints.get()) + "ms");
        if (over) {
            timePoints.remove();
        } else {
            timePoints.set(end);
        }
    }
}
