package cn.deali.action;

import cn.deali.utils.Database;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IndexAction extends ActionSupport {
    private final static Logger logger = LoggerFactory.getLogger(IndexAction.class);

    @Override
    public String execute() throws Exception {
        System.out.println("Index Action");
        logger.info("Index Action");

        // 初始化数据库
        if (Database.init()) {
            Database.eraseData = false;
            return SUCCESS;
        } else
            return ERROR;
    }
}
