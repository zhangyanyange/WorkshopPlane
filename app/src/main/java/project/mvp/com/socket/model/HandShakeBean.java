package project.mvp.com.socket.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuhao on 2017/5/22.
 */

public class HandShakeBean extends DefaultSendBean {

    public HandShakeBean() {
        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put("cmd", 54);
            jsonObject.put("请求链接", "连接成功");
            content = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
