package com.foxminded;

import com.foxminded.dao.DAOException;
import com.foxminded.dao.UniversityDao;
import com.foxminded.domain.University;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UniversityDaoTest {

    private UniversityDao universityDao;

    @Before
    public void initDao(){
        universityDao = new UniversityDao();
    }

    @Test
    public void find_existing() throws DAOException {
        University university = universityDao.find("KPI");
        assertTrue("KPI".equals(university.getName()));
    }

    @Test
    public  void create() throws DAOException{
        University university = universityDao.create("OXFORD");

        assertTrue("OXFORD".equals(university.getName()));

        universityDao.delete("OXFORD");
        assertNull(universityDao.find("OXFORD"));
    }

    @Test(expected = DAOException.class)
    public  void create_existing() throws DAOException{
        universityDao.create("HARVARD");
        universityDao.create("HARVARD");
    }

    @Test
    public  void rename() throws DAOException{
        universityDao.create("OXFORD");

        University university = universityDao.rename("OXFORD", "NEWOXFORD");

        assertTrue("NEWOXFORD".equals(university.getName()));

        universityDao.delete("NEWOXFORD");
        assertNull(universityDao.find("NEWOXFORD"));

    }

    @Test
    public  void delete() throws DAOException{
        universityDao.create("BERKLEY");
        assertNotNull(universityDao.find("BERKLEY"));

        universityDao.delete("BERKLEY");

        assertNull(universityDao.find("BERKLEY"));
    }

}
