package com.zxin.basemodel.dao;

import com.zxin.basemodel.entity.HttpUrl;
import com.zxin.basemodel.gen.DataBaseUtil;
import com.zxin.basemodel.gen.HttpUrlDao;
import com.zxin.basemodel.util.GlobalUtil;
import com.zxin.root.bean.BasePageBean;
import com.zxin.root.bean.HttpUrlBean;
import com.zxin.root.bean.TitleBean;
import com.zxin.root.util.logger.LogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class HttpUrlDaoUtil {
    private static final LogUtils.Tag TAG = new LogUtils.Tag("HttpUrlDaoUtil");
    private static volatile HttpUrlDaoUtil httpUrlDaoUtil;
    private static HttpUrlDao httpUrlDao = null;

    private HttpUrlDaoUtil() {
        DataBaseUtil dataBaseUtil = GlobalUtil.getDataBaseUtil();
        httpUrlDao = dataBaseUtil.getDao(DataBaseUtil.Mode.HttpUrlMode);
    }

    public static HttpUrlDaoUtil getInstance() {
        if (httpUrlDaoUtil == null) {
            synchronized (HttpUrlDaoUtil.class) {
                if (httpUrlDaoUtil == null) {
                    httpUrlDaoUtil = new HttpUrlDaoUtil();
                }
            }
        }
        return httpUrlDaoUtil;
    }

    /**
     * 增加数据
     */
    public boolean addHttpUrl(String name, String lable, String url) {
        String createTimer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        HttpUrl httpUrl = new HttpUrl();
        httpUrl.setCreateTimer(createTimer);
        httpUrl.setName(name);
        httpUrl.setUrl(url);
        httpUrl.setLastTime(createTimer);
        httpUrl.setTimes(0);
        httpUrl.setIsEffective(1);
        httpUrl.setLable(lable);
        httpUrl.setModifyTime(createTimer);
        httpUrl.setOrderNum(getEffectiveNum());
        //插入数据库
        return httpUrlDao.insert(httpUrl) > 0;
    }

    /**
     * 根据主键删除
     */
    public boolean deleteHttpUrl(long id) {
        return httpUrlDao.deleteById(id) > 0;
    }

    /**
     * 更改数据
     */
    public boolean updateHttpUrl(long id, String name, String lable, String url, boolean checked) {
        HttpUrl httpUrl = getHttpBean(id);
        String createTimer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        httpUrl.setId(id);
        httpUrl.setName(name);
        httpUrl.setUrl(url);
        httpUrl.setLable(lable);
        httpUrl.setModifyTime(createTimer);
        httpUrl.setIsEffective(checked ? 1 : 0);
        return httpUrlDao.update(httpUrl)>0;
    }

    /*****
     * 更新浏览次数、浏览时间
     * @param id
     */
    public boolean updateHttpUrlTimes(long id, long times) {
        String createTimer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return httpUrlDao.update(id,times,createTimer) >0;
    }

    /**
     * 查找数据
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public BasePageBean getHttpUrlList(int pageNum, int pageSize) {
        BasePageBean pageBean = new BasePageBean();
        List<HttpUrlBean> httpUrlList = new ArrayList<>();
        List<HttpUrl> list = httpUrlDao.getByLimit((pageNum - 1) * pageSize, pageSize);
        int count = httpUrlDao.getAll().size();
        for (HttpUrl httpUrl : list) {
            HttpUrlBean bean = new HttpUrlBean();
            bean.id = httpUrl.getId();
            bean.createTimer = httpUrl.getCreateTimer();
            bean.name = httpUrl.getName();
            bean.lastTime = httpUrl.getLastTime();
            bean.url = httpUrl.getUrl();
            bean.times = httpUrl.getTimes();
            bean.isEffective = httpUrl.getIsEffective();
            httpUrlList.add(bean);
        }
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setHasNextPage(list.size() >= pageSize);
        pageBean.setCountSize(count);
        pageBean.setCountPage(count % pageSize == 0 ? count / pageSize : ((count / pageSize) + 1));
        pageBean.setList(httpUrlList);
        return pageBean;
    }

    /****
     * 获取具体实体
     * @param id
     * @return
     */
    public HttpUrlBean getHttpUrl(long id) {
        HttpUrlBean bean = new HttpUrlBean();
        HttpUrl httpUrl = getHttpBean(id);
        bean.id = httpUrl.getId();
        bean.createTimer = httpUrl.getCreateTimer();
        bean.name = httpUrl.getName();
        bean.lastTime = httpUrl.getLastTime();
        bean.url = httpUrl.getUrl();
        bean.times = httpUrl.getTimes();
        bean.lable = httpUrl.getLable();
        bean.modifyTime = httpUrl.getModifyTime();
        bean.orderNum = httpUrl.getOrderNum();
        bean.isEffective = httpUrl.getIsEffective();
        return bean;
    }

    public TitleBean getTitleBean(long id) {
        TitleBean title = new TitleBean();
        HttpUrl httpUtil = getHttpBean(id);
        title.id = httpUtil.getId();
        title.label = httpUtil.getLable();
        title.orderNum = httpUtil.getOrderNum();
        title.isEffective = httpUtil.getIsEffective();
        title.lineUrl = httpUtil.getUrl();
        title.title = httpUtil.getName();
        return title;
    }

    public List<TitleBean> getTitleMainList() {
        List<TitleBean> titleList = new ArrayList<>();
        List<HttpUrl> httpList = httpUrlDao.getTitleMainList();
        for (HttpUrl httpUtil : httpList) {
            TitleBean title = new TitleBean();
            title.id = httpUtil.getId();
            title.label = httpUtil.getLable();
            title.orderNum = httpUtil.getOrderNum();
            title.isEffective = httpUtil.getIsEffective();
            title.lineUrl = httpUtil.getUrl();
            title.title = httpUtil.getName();
            titleList.add(title);
        }
        return titleList;
    }

    public List<TitleBean> getTitleAllList() {
        List<TitleBean> titleList = new ArrayList<>();
        List<HttpUrl> httpList = httpUrlDao.getAll();
        for (HttpUrl httpUtil : httpList) {
            TitleBean title = new TitleBean();
            title.id = httpUtil.getId();
            title.label = httpUtil.getLable();
            title.orderNum = httpUtil.getOrderNum();
            title.isEffective = httpUtil.getIsEffective();
            title.lineUrl = httpUtil.getUrl();
            title.title = httpUtil.getName();
            titleList.add(title);
        }
        return titleList;
    }

    public void updateHttpEffective(long id, int isEffective, int orderNum) {
        String createTimer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        HttpUrl httpUrl = getHttpBean(id);
        httpUrl.setId(id);
        httpUrl.setOrderNum(orderNum);
        httpUrl.setModifyTime(createTimer);
        httpUrl.setIsEffective(isEffective);
        httpUrlDao.update(httpUrl);
    }

    public void updateHttpSelect(long id) {
        String createTimer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        HttpUrl httpUrl = getHttpBean(id);
        httpUrl.setId(id);
        httpUrl.setOrderNum(getEffectiveNum());
        httpUrl.setModifyTime(createTimer);
        httpUrl.setIsEffective(1);
        httpUrlDao.update(httpUrl);
    }

    public int getEffectiveNum() {
        HttpUrl httpBean = httpUrlDao.getTitleMain();
        int orderNum = httpBean == null ? 0 : httpBean.getOrderNum();
        return orderNum == 0 ? 0 : orderNum + 1;
    }

    public HttpUrl getHttpBean(long id) {
        return httpUrlDao.getHttpUrlById(id);
    }

}
