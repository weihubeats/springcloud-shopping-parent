package entity;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/19 19:28
 */
public class ResponseBase {
    private boolean flag;
    //状态码
    private Integer code;
    private String messag;
    //返回结果
    private Object object;

    public ResponseBase() {
    }

    public ResponseBase(boolean flag, Integer code, String messag) {
        this.flag = flag;
        this.code = code;
        this.messag = messag;
    }

    public ResponseBase(boolean flag, Integer code, String messag, Object object) {
        this.flag = flag;
        this.code = code;
        this.messag = messag;
        this.object = object;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessag() {
        return messag;
    }

    public void setMessag(String messag) {
        this.messag = messag;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
