package com.hb.utils.net.okhttp;

/**
 * 这个类中描述了httpCode错误所对应的消息
 *
 * Created by xianlai on 2017/1/13 0013.
 */
public class HttpCodeMessage {

    public  String codeHandle(int httpCode){
        String message = "";
        switch (httpCode){
            case 403:
                message = "指定位置的资源不可用";
                break;
            case 404:
                message = "无法找到指定位置的资源";
                break;
            case 405:
                message = "请求方法（GET、POST、HEAD、Delete、PUT、TRACE等）对指定的资源不适用";
                break;
            case 406:
                message = "指定的资源已经找到，但它的MIME类型和客户在Accpet头中所指定的不兼容";
                break;
            case 407:
                message = "请求必须先经过代理服务器的授权";
                break;
            case 408:
                message = "连接超时，请检查网络后重试";
                break;
            case 410:
                message = "所请求的资源已经不再可用，而且服务器不知道应该重定向到哪一个地址";
                break;
            case 411:
                message = "服务器不能处理请求，除非客户发送一个Content-Length头";
                break;
            case 412:
                message = "请求头中指定的一些前提条件失败";
                break;
            case 413:
                message = "请求数据的大小超过服务器当前愿意处理的大小";
                break;
            case 414:
                message = "URL太长，连接失败";
                break;
            case 500:
                message = "服务器遇到了错误，连接失败";
                break;
            case 501:
                message = "服务器不支持当前请求方式";//例如，客户发出了一个服务器不支持的PUT请求";
                break;
            case 502:
                message = "服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答";
                break;
            case 503:
                //例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个Retry-After头
                message = "服务器由于维护或者负载过重未能应答";
                break;
            case 504:
                message = "未能及时地从远程服务器获得应答";
                break;
            case 505:
                message = "服务器不支持请求中所指明的HTTP版本";
                break;
            default:
                message = "未知Http错误，连接失败";
        }
        return message+"("+httpCode+")";
    }
}
