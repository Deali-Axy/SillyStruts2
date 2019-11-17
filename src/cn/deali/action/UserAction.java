package cn.deali.action;

import cn.deali.utils.Database;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class UserAction extends ActionSupport {
    private final static Logger logger = LoggerFactory.getLogger(UserAction.class);


    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();

        Map requestMap = new HashMap();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            requestMap.put(key, value);
        }

        if (!requestMap.containsKey("username") ||
                !requestMap.containsKey("origin-pwd") ||
                !requestMap.containsKey("new-pwd") ||
                !requestMap.containsKey("confirm-pwd")) {
            return INPUT;
        }

        String username = (String) requestMap.get("username");
        String originPwd = (String) requestMap.get("origin-pwd");
        String newPwd = (String) requestMap.get("new-pwd");
        String confirmPwd = (String) requestMap.get("confirm-pwd");

        if (!newPwd.equals(confirmPwd))
            return "discord";


        try {
            int affectLine = Database.getInstance().executeUpdate(
                    String.format("update user set password='%s' where username='%s' and password='%s';", newPwd, username, originPwd)
            );
            if (affectLine > 0)
                return SUCCESS;
            else
                return "password-error";
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return "db-error";
        }
    }
}
