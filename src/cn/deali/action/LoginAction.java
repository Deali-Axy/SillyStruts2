package cn.deali.action;

import cn.deali.models.User;
import cn.deali.utils.Database;
import cn.deali.utils.sqlite.RowMapper;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport {
    private final static Logger logger = LoggerFactory.getLogger(LoginAction.class);


    private String username;
    private String password;

    @Override
    public String execute() {
        HttpServletRequest request = ServletActionContext.getRequest();

        Map requestMap = new HashMap();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            requestMap.put(key, value);
        }

        if (!requestMap.containsKey("username") ||
                !requestMap.containsKey("password")) {
            return LOGIN;
        }

        String username = (String) requestMap.get("username");
        String password = (String) requestMap.get("password");

        try {
            List<User> sList = Database.getInstance().executeQuery(
                    String.format("select * from user where username='%s'", username),
                    new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet rs, int index) throws SQLException {
                            return new User(rs.getString("username"), rs.getString("password"));
                        }
                    });
            logger.info(sList.get(0).getUsername());

            if (sList.get(0).getPassword().equals(password)) {
                return SUCCESS;
            } else {
                return ERROR;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return "db-error";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
