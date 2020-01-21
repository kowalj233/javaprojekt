package pl.project.pk.database.dbutils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class DbManagerTest {
    @Test
    public void getConnectionSource() throws Exception {
        Assert.assertNotNull(DbManager.getConnectionSource());
    }
}