package com.defa.slack.api;

import com.defa.slack.Slack;
import com.defa.slack.api.msg.ErrorResponse;
import com.defa.slack.util.HttpHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Methods {
    private static final String SLACK_API = "https://slack.com/api/";
    private Slack slack;
    private String token;

    private interface Method {
        String call(Api fn, Map<String, Object> data) throws MethodException;
    }

    private Map<Api, Method> methods;

    private void register(Api fn, Method method){
        this.methods.put(fn, method);
    }

    private Methods(){}

    private Methods(Slack slack, String token){
        this.slack = slack;
        this.token = token;
        this.methods = new HashMap<>();
    }

    public interface Callback {
        void call(String response) throws MethodException, IOException;
    }

    private String doPostJson(String url, Map<String, Object> data) throws IOException {
        return HttpHelper.doPostSlackJson(url, this.token, data);
    }

    private String doPostForm(String url, Map<String, Object> data) throws IOException {
        return HttpHelper.doPostSimpleForm(url, data);
    }

    private String doGet(String url, Map<String, Object> data) throws IOException {
        return HttpHelper.doGet(url, data);
    }

    private void call(Api method, Map<String, Object> data, Callback callback){
        Method md = this.methods.get(method);
        if(null!=md){
            Map<String, Object> params = new HashMap<>(data);
            params.put("token", this.token);
            try{
                String response = md.call(method, params);
                Callback cb = callback == null ? resp->{
                    ObjectMapper om = new ObjectMapper();
                    try {
                        JsonNode root = om.readTree(resp);
                        JsonNode ok = root.findValue("ok");
                        if(null==ok){
                            System.out.println(resp);
                        }else if(!ok.asBoolean()) {
                            System.out.println(resp);
                        }
                    } catch (IOException ioe) {
                        System.out.println(resp);
                    }
                }: callback;
                cb.call(response);
            }catch (MethodException | IOException e) {
                //pass
                e.printStackTrace();
            }
        }
    }

    /**
     * shortcuts of OpenAPI Methods
     */
    public void rtmConnect(Map<String, Object> optional, Callback cb) {
        Map<String, Object> required = new HashMap<>();
        if(optional!=null) required.putAll(optional);
        call(Api.RTM_CONNECT, required, cb);
    }

    public void chatPostMessage(String channel, String text, Map<String, Object> optional, Callback cb) {
        Map<String, Object> required = getParameter(optional);
        required.put("channel", channel);
        required.put("text", text);
        call(Api.CHAT_POST_MESSAGE, required, cb);
    }

    public void chatPostEphemeral(String channel, String text, String user, Map<String, Object> optional, Callback cb){
        Map<String, Object> required = getParameter(optional);
        required.put("channel", channel);
        required.put("text", text);
        required.put("user", user);
        call(Api.CHAT_POST_EPHEMERAL, required, cb);
    }

    public void chatUpdate(String channel, String text, String ts, Map<String, Object> optional, Callback cb){
        Map<String, Object> required = getParameter(optional);
        required.put("channel", channel);
        required.put("text", text);
        required.put("ts", ts);
        call(Api.CHAT_UPDATE, required, cb);
    }

    public void chatDelete(String channel, String ts, Map<String, Object> optional, Callback cb){
        Map<String, Object> required = getParameter(optional);
        required.put("channel", channel);
        required.put("ts", ts);
        call(Api.CHAT_DELETE, required, cb);
    }

    public void pinsAdd(String channel, String ts, Map<String, Object> optional, Callback cb){
        Map<String, Object> required = getParameter(optional);
        required.put("channel", channel);
        required.put("timestamp", ts);
        call(Api.PINS_ADD, required, cb);
    }

    public void pinsRemove(String channel, Map<String, Object> optional, Callback cb){
        Map<String, Object> required = getParameter(optional);
        required.put("channel", channel);
        call(Api.PINS_ADD, required, cb);
    }

    public void pinsList(String channel, Map<String, Object> optional, Callback cb){
        Map<String, Object> required = getParameter(optional);
        required.put("channel", channel);
        call(Api.PINS_ADD, required, cb);
    }

    private Map<String, Object> getParameter(Map<String, Object> optional) {
        Map<String, Object> required = new HashMap<>();
        if(optional!=null) required.putAll(optional);
        return required;
    }

    public static Methods instance(Slack slack, String token){
        assert null!=slack && null!=token;
        Methods ms = new Methods(slack, token);

        registerGet(ms, Api.RTM_CONNECT);

        registerPostJson(ms, Api.CHAT_POST_MESSAGE);
        registerPostJson(ms, Api.CHAT_POST_EPHEMERAL);
        registerPostJson(ms, Api.CHAT_UPDATE);
        registerPostJson(ms, Api.CHAT_DELETE);

        registerPostJson(ms, Api.PINS_ADD);
        registerPostJson(ms, Api.PINS_REMOVE);
        registerGet(ms, Api.PINS_LIST);

        return ms;
    }

    public Slack getSlack(){
        return this.slack;
    }

    private static void registerGet(Methods ms, Api api){
        ms.register(api, (fn, data)->{
            try {
                return ms.doGet(SLACK_API + fn, data);
            }catch (IOException e){
                e.printStackTrace();
                return (new ErrorResponse(e.getMessage())).toString();
            }
        });
    }

    private static void registerPostJson(Methods ms, Api api){
        ms.register(api, (fn, data)->{
            try {
                return ms.doPostJson(SLACK_API + fn, data);
            }catch (IOException e){
                e.printStackTrace();
                return (new ErrorResponse(e.getMessage())).toString();
            }
        });
    }


}
