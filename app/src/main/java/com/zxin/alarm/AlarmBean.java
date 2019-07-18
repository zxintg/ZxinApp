package com.zxin.alarm;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/****
 * 闹钟实体
 *
 */
public class AlarmBean implements Parcelable {
    //message id
    @SerializedName("msg_id")
    private String mesgId;
    //配置的标识key
    @SerializedName("key")
    private String mesgType;
    //目标app_id列表，使用逗号分隔，车机各APP只取包含自己app_id的消息
    @SerializedName("target_app_ids")
    private String appIds;
    //目标用户列表，使用逗号分隔，无则适用所有用户
    @SerializedName("account_ids")
    private String accountIds;
    //配置变更时的时间戳
    @SerializedName("timestamp")
    private long updateTime;
    //配置内容
    @SerializedName("config")
    private CarLogo carLogo;


    //1.车机最低车机版本号
    private String version;
    // 4.推送类型 (例如纯文本推送 图标替换 资源替换 等)
    private String pushType;
    // 5.文本描述
    private String describe;
    // 6.表现形式 (支持根据时间段自动替换；支持根据不同的手势操作（如双击自车图标等）自动替换)
    private String showType;
    // 7.类别 (巡航-自车图标；路线规划-自车图标；搜索-自车图标；导航中-自车图标 等)
    private String type;
    // 9.生效类别 (类如：重启生效等)
    private String validType;
    // 10.失效类别 (类如：重启失效等)
    private String invalidType;
    // 11.扩展字段
    private String expansion;

    public AlarmBean() {

    }

    protected AlarmBean(Parcel in) {
        mesgId = in.readString();
        mesgType = in.readString();
        appIds = in.readString();
        accountIds = in.readString();
        updateTime = in.readLong();
        carLogo = in.readParcelable(CarLogo.class.getClassLoader());
        version = in.readString();
        pushType = in.readString();
        describe = in.readString();
        showType = in.readString();
        type = in.readString();
        validType = in.readString();
        invalidType = in.readString();
        expansion = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mesgId);
        dest.writeString(mesgType);
        dest.writeString(appIds);
        dest.writeString(accountIds);
        dest.writeLong(updateTime);
        dest.writeParcelable(carLogo, flags);
        dest.writeString(version);
        dest.writeString(pushType);
        dest.writeString(describe);
        dest.writeString(showType);
        dest.writeString(type);
        dest.writeString(validType);
        dest.writeString(invalidType);
        dest.writeString(expansion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AlarmBean> CREATOR = new Creator<AlarmBean>() {
        @Override
        public AlarmBean createFromParcel(Parcel in) {
            return new AlarmBean(in);
        }

        @Override
        public AlarmBean[] newArray(int size) {
            return new AlarmBean[size];
        }
    };

    public String getMesgId() {
        return mesgId;
    }

    public void setMesgId(String mesgId) {
        this.mesgId = mesgId;
    }

    public String getMesgTye() {
        return mesgType;
    }

    public void setMesgTye(String mesgType) {
        this.mesgType = mesgType;
    }

    public String getAppIds() {
        return appIds;
    }

    public void setAppIds(String appIds) {
        this.appIds = appIds;
    }

    public String getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(String accountIds) {
        this.accountIds = accountIds;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public CarLogo getCarLogo() {
        return carLogo;
    }

    public void setCarLogo(CarLogo carLogo) {
        this.carLogo = carLogo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public String getInvalidType() {
        return invalidType;
    }

    public void setInvalidType(String invalidType) {
        this.invalidType = invalidType;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    /****
     * 消息内容实体
     */
    public static class CarLogo implements Parcelable {
        //开始时间戳
        @SerializedName("start_time")
        private long startTime;
        //结束时间戳
        @SerializedName("end_time")
        private long endTime;
        //新icon cdn地址
        @SerializedName("icon_url")
        private String iconURL;
        // 12.文件名称
        private String fileName;

        public CarLogo() {

        }

        protected CarLogo(Parcel in) {
            startTime = in.readLong();
            endTime = in.readLong();
            iconURL = in.readString();
            fileName = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(startTime);
            dest.writeLong(endTime);
            dest.writeString(iconURL);
            dest.writeString(fileName);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<CarLogo> CREATOR = new Creator<CarLogo>() {
            @Override
            public CarLogo createFromParcel(Parcel in) {
                return new CarLogo(in);
            }

            @Override
            public CarLogo[] newArray(int size) {
                return new CarLogo[size];
            }
        };

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getIconURL() {
            return iconURL;
        }

        public void setIconURL(String iconURL) {
            this.iconURL = iconURL;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String toString() {
            return "CarLogo{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", iconURL='" + iconURL + '\'' +
                    ", fileName='" + fileName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AlarmBean{" +
                "mesgId='" + mesgId + '\'' +
                ", mesgType='" + mesgType + '\'' +
                ", appIds='" + appIds + '\'' +
                ", accountIds='" + accountIds + '\'' +
                ", updateTime=" + updateTime +
                ", carLogo='" + carLogo + '\'' +
                ", version='" + version + '\'' +
                ", pushType='" + pushType + '\'' +
                ", describe='" + describe + '\'' +
                ", showType='" + showType + '\'' +
                ", type='" + type + '\'' +
                ", validType='" + validType + '\'' +
                ", invalidType='" + invalidType + '\'' +
                ", expansion='" + expansion + '\'' +
                '}';
    }

    /****
     * 设置默认自定义地图自车图标 本地
     *
     * 国庆彩蛋【2019/10/01  00:00】～【2019/10/07 24:00】
     */
    public void getDefaultCarLogo() {
        try {
            CarLogo carLogo = new CarLogo();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            carLogo.setStartTime(format.parse("2019/10/01 00:00:00").getTime());
            carLogo.setEndTime(format.parse("2019/10/07 24:00:00").getTime());
            this.setCarLogo(carLogo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
