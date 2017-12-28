package org.forten.course.dto.ro;

public class MessageForEasyUI {
    private boolean success;
    private String message;

    public MessageForEasyUI() {
    }

    public MessageForEasyUI(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageForEasyUI{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
