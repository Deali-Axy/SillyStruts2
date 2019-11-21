package cn.deali.models;

public class Alert {
    private boolean visible;
    private String level;
    private String content;

    public Alert(boolean visible, String level, String content) {
        this.visible = visible;
        this.level = level;
        this.content = content;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
