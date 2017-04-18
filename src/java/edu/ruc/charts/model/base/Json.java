package edu.ruc.charts.model.base;

/**
 * @version V1.0
 * @Package: edu.ruc.charts.model.base
 * @ClassName: Json
 * @Description: JSON模型
 * @author: Tao
 * @date: Create in 2017-04-17 19:21
 **/
public class Json implements java.io.Serializable {
    private boolean success = false;// 是否成功
    private String msg = "";// 提示信息
    private Object obj = null;// 其他信息

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
