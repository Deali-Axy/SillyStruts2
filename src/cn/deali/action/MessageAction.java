package cn.deali.action;

import cn.deali.models.Alert;
import cn.deali.models.AlertLevel;
import cn.deali.models.Message;
import cn.deali.models.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.*;

public class MessageAction extends ActionSupport {
    private final static Logger logger = LoggerFactory.getLogger(MessageAction.class);
    private Alert alert = new Alert(true, AlertLevel.info, "出现错误！");
    private List<Message> messages = new ArrayList<>();

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();

        if (request.getMethod().equals("POST")) {
            Map requestMap = new HashMap();
            Enumeration parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String key = (String) parameterNames.nextElement();
                String value = request.getParameter(key);
                requestMap.put(key, value);
            }
        }

        for (int i = 0; i < 10; i++) {
            messages.add(new Message(new User("user" + i, "123"), "content" + 1, LocalTime.now()));
        }

        return SUCCESS;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
