package com.zxin.basemodel.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*****
 * 创两参数范围
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ApiUrlMode {
    
    //常量定义
    int APIURL_MODE_Show = 0x0;
    int APIURL_MODE_DBmeinv = 0x1;
    int APIURL_MODE_XiuMei99 = 0x2;
    int APIURL_MODE_Codekk = 0x3;
    int APIURL_MODE_YuShangJi = 0x4;
    int APIURL_MODE_ApkBus = 0x5;
    int APIURL_MODE_MeiZu = 0x6;
    int APIURL_MODE_Picasso = 0x7;
    int APIURL_MODE_RuBaoo = 0x8;
    int APIURL_MODE_Beauty = 0x9;
    int APIURL_MODE_BaiDu = 0xA;
    int APIURL_MODE_SoGou = 0xB;
}
