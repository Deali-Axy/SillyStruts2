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
import java.util.Objects;

public class SignInAction extends ActionSupport {
    private final static Logger logger = LoggerFactory.getLogger(SignInAction.class);

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
                !requestMap.containsKey("password") ||
                !requestMap.containsKey("confirm-password")) {
            return INPUT;
        }

        String username = (String) requestMap.get("username");
        String password = (String) requestMap.get("password");
        String confirmPassword = (String) requestMap.get("confirm-password");

        if (!password.equals(confirmPassword))
            return "discord";

        try {
            Objects.requireNonNull(Database.getInstance()).executeUpdate(
                    String.format("insert into user values('%s','%s');", username, password)
            );

            return SUCCESS;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return "db-error";
        }

    }
}
