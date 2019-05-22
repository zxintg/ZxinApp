package com.zxin.basemodel.dao;

import com.zxin.basemodel.app.BaseApplication;
import com.zxin.basemodel.entity.City;
import com.zxin.basemodel.gen.CityDao;
import com.zxin.basemodel.gen.DataBaseUtil;
import com.zxin.root.util.logger.LogUtils;

import java.util.List;

/**
 * Created by liukui on 2016/12/14 0014.
 */

public class CityDaoUtil {
    private static final LogUtils.Tag TAG = new LogUtils.Tag("CityDaoUtil");
    private static volatile CityDaoUtil daoUtil = null;
    private CityDao cityDao = null;

    private CityDaoUtil() {
        if (cityDao != null) {
            return;
        }
        DataBaseUtil dataBaseUtil = BaseApplication.getInstance().getDataBaseUtil();
        cityDao = dataBaseUtil.getDao(DataBaseUtil.Mode.CityMode);
    }

    public static CityDaoUtil getInstance() {
        if (daoUtil == null) {
            synchronized (CityDaoUtil.class) {
                if (daoUtil == null) {
                    daoUtil = new CityDaoUtil();
                }
            }
        }
        return daoUtil;
    }

    /**
     * 查询所有【省】级城市
     */
    public List<City> getAllCityProvince() {
        return getCityByPid(0);
    }

    /**
     * 查询所有【pid】的城市
     */
    public List<City> getCityByPid(int pid) {
        return cityDao.getCityByParentId(pid == 0 ? "0" : addZeroId(pid));
    }

    /**
     * 根据上一级的PD和当前区域名字查询得到当前区域名字
     */
    public City getCityByIdAndName(int id, String name) {
        return cityDao.getCityByParentIdArea(addZeroId(id), name);
    }

    /**
     * 查询【id】的城市
     */
    public City getCityById(String id) {
        return cityDao.getCityById(id);
    }

    /**
     * 查询【name】的城市
     */
    public City getCityByName(String name, String areaLevel) {
        return cityDao.getCityByAreaLevel(name, areaLevel);
    }

    /**
     * 查询【name】的城市
     */
    public City getCityByName(String name) {
        return cityDao.getCityByName(name);
    }

    /**
     * 查询所有【市】级城市
     */
    public List<City> getAllCity() {
        return cityDao.getCityByParent();
    }

    /****
     * 地址补0
     * @param pid
     */
    private String addZeroId(Object pid) {
        String pidStr = pid instanceof String ? (String) pid : String.valueOf(pid);
        switch (pidStr.length()) {
            case 1:
                pidStr += "00000";
                break;

            case 2:
                pidStr += "0000";
                break;

            case 3:
                pidStr += "000";
                break;

            case 4:
                pidStr += "00";
                break;

            case 5:
                pidStr += "0";
                break;

        }
        return pidStr;
    }

}
