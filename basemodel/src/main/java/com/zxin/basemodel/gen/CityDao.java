package com.zxin.basemodel.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zxin.basemodel.entity.City;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "t_sse_param_area".
*/
public class CityDao extends AbstractDao<City, Integer> {

    public static final String TABLENAME = "t_sse_param_area";

    /**
     * Properties of entity City.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Area_id = new Property(0, int.class, "area_id", true, "AREA_ID");
        public final static Property Parent_id = new Property(1, int.class, "parent_id", false, "PARENT_ID");
        public final static Property Area_name = new Property(2, String.class, "area_name", false, "AREA_NAME");
        public final static Property Short_name = new Property(3, String.class, "short_name", false, "SHORT_NAME");
        public final static Property English_name = new Property(4, String.class, "english_name", false, "ENGLISH_NAME");
        public final static Property Area_level = new Property(5, String.class, "area_level", false, "AREA_LEVEL");
        public final static Property Create_user = new Property(6, String.class, "create_user", false, "CREATE_USER");
        public final static Property Create_time = new Property(7, String.class, "create_time", false, "CREATE_TIME");
        public final static Property Remark = new Property(8, String.class, "remark", false, "REMARK");
    }


    public CityDao(DaoConfig config) {
        super(config);
    }
    
    public CityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, City entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getArea_id());
        stmt.bindLong(2, entity.getParent_id());
 
        String area_name = entity.getArea_name();
        if (area_name != null) {
            stmt.bindString(3, area_name);
        }
 
        String short_name = entity.getShort_name();
        if (short_name != null) {
            stmt.bindString(4, short_name);
        }
 
        String english_name = entity.getEnglish_name();
        if (english_name != null) {
            stmt.bindString(5, english_name);
        }
 
        String area_level = entity.getArea_level();
        if (area_level != null) {
            stmt.bindString(6, area_level);
        }
 
        String create_user = entity.getCreate_user();
        if (create_user != null) {
            stmt.bindString(7, create_user);
        }
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(8, create_time);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(9, remark);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, City entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getArea_id());
        stmt.bindLong(2, entity.getParent_id());
 
        String area_name = entity.getArea_name();
        if (area_name != null) {
            stmt.bindString(3, area_name);
        }
 
        String short_name = entity.getShort_name();
        if (short_name != null) {
            stmt.bindString(4, short_name);
        }
 
        String english_name = entity.getEnglish_name();
        if (english_name != null) {
            stmt.bindString(5, english_name);
        }
 
        String area_level = entity.getArea_level();
        if (area_level != null) {
            stmt.bindString(6, area_level);
        }
 
        String create_user = entity.getCreate_user();
        if (create_user != null) {
            stmt.bindString(7, create_user);
        }
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(8, create_time);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(9, remark);
        }
    }

    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.getInt(offset + 0);
    }    

    @Override
    public City readEntity(Cursor cursor, int offset) {
        City entity = new City( //
            cursor.getInt(offset + 0), // area_id
            cursor.getInt(offset + 1), // parent_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // area_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // short_name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // english_name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // area_level
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // create_user
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // create_time
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // remark
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, City entity, int offset) {
        entity.setArea_id(cursor.getInt(offset + 0));
        entity.setParent_id(cursor.getInt(offset + 1));
        entity.setArea_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setShort_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEnglish_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setArea_level(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCreate_user(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCreate_time(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRemark(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Integer updateKeyAfterInsert(City entity, long rowId) {
        return entity.getArea_id();
    }
    
    @Override
    public Integer getKey(City entity) {
        if(entity != null) {
            return entity.getArea_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(City entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
