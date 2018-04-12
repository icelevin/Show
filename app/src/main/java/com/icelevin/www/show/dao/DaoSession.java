package com.icelevin.www.show.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.icelevin.www.show.model.CacheModle;
import com.icelevin.www.show.model.UserInfo;

import com.icelevin.www.show.dao.CacheModleDao;
import com.icelevin.www.show.dao.UserInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cacheModleDaoConfig;
    private final DaoConfig userInfoDaoConfig;

    private final CacheModleDao cacheModleDao;
    private final UserInfoDao userInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cacheModleDaoConfig = daoConfigMap.get(CacheModleDao.class).clone();
        cacheModleDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        cacheModleDao = new CacheModleDao(cacheModleDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);

        registerDao(CacheModle.class, cacheModleDao);
        registerDao(UserInfo.class, userInfoDao);
    }
    
    public void clear() {
        cacheModleDaoConfig.clearIdentityScope();
        userInfoDaoConfig.clearIdentityScope();
    }

    public CacheModleDao getCacheModleDao() {
        return cacheModleDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

}