package pl.project.pk.database.dao;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.omg.CORBA.portable.ApplicationException;
import pl.project.pk.database.dbutils.DbManager;
import pl.project.pk.database.models.BaseModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDao.class);
    protected final ConnectionSource connectionSource;

    //TODO: AplicationExcepion wlasny dodac

    public BaseDao(ConnectionSource connectionSource){
        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e){
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
        return null;
    }

    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try{
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseModel, I> void update(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try{
            dao.update((T) baseModel);
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseModel, I> void updateId(BaseModel baseModel, I id) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try{
            dao.updateId((T) baseModel, id);
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e){
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws  ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e ){
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void deteleById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e ){
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e ){
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
        return null;
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e){
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
        return null;
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws ApplicationException {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

    private void closeDbConnection() throws ApplicationException {
        try {
            this.connectionSource.close();
        } catch (IOException e ){
            System.out.println("error");
        }
    }


}
