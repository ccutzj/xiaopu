package com.zzkj.xiaopu.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回接口
 * @param <T>
 */
@Getter
@Setter
public class JsonData<T> {
    Integer  code;
    private String msg;
    private T data;
    private String token;

    protected JsonData(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param object 获取数据
     * @param msg 提示消息
     * @return
     */
    public static <T> JsonData<T> success(T object, String msg){
        return new JsonData<T>(ResultCode.SUCCESS.getCode(), msg, object);
    }

    /**
     * 成功返回结果
     * @param object  获取数据
     * @param msg 提示消息
     * @param token token
     * @return
     */
    public static <T> JsonData<T> success(T object, String msg, String token){
        JsonData jsonData = new JsonData<T>(ResultCode.SUCCESS.getCode(), msg, object);
        jsonData.token = token;
        return jsonData;
    }

    /**
     * 成功返回结果
     * @param object 获取数据
     * @return
     */
    public static <T> JsonData<T> success(T object){
        return new JsonData<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), object);
    }

    /**
     *  失败返回结果
     * @param msg 提示信息
     * @return
     */
    public static <T> JsonData<T> fail(String msg){
        return new JsonData<T>(ResultCode.FAILED.getCode(), msg, null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @return
     */
    public static <T> JsonData<T> fail(ErrorCode errorCode){
        return new JsonData<T>(errorCode.getCode(), errorCode.getMessage() , null);
    }

    /**
     * 失败返回结果
     * @param <T>
     * @return
     */
    public static <T> JsonData<T> fail(){
        return fail(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> JsonData<T> validateFailed(){
        return fail(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param msg 提示信息
     */
    public static <T> JsonData<T> validateFailed(String msg){
        return new JsonData<T>(ResultCode.VALIDATE_FAILED.getCode(), msg, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> JsonData<T> unauthorized(T data){
        return new JsonData<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> JsonData<T> forbidden(T data){
        return new JsonData<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
