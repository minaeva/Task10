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
    public void getById() throws DAOException {
        University university = universityDao.getById(1);
        assertTrue("KPI".equals(university.getName()));
    }

    @Test
    public  void insert() throws DAOException{
        University university = universityDao.insert("OXFORD");

        assertTrue("OXFORD".equals(university.getName()));

        universityDao.delete(university);
        assertNull(universityDao.getById(university.getId()));
    }

    @Test(expected = DAOException.class)
    public  void create_existing() throws DAOException{
        universityDao.insert("HARVARD");
        universityDao.insert("HARVARD");
    }

    @Test
    public  void update() throws DAOException{
        University university = universityDao.insert("OXFORD");
        university.setName("NEWOXFORD");

        University updatedUniversity = universityDao.update(university);

        assertTrue("NEWOXFORD".equals(updatedUniversity.getName()));

        Long id = updatedUniversity.getId();
        universityDao.delete(updatedUniversity);
        assertNull(universityDao.getById(id));

    }

    @Test
    public  void delete() throws DAOException{
        University university = universityDao.insert("BERKLEY");
        Long id = university.getId();
        assertNotNull(universityDao.getById(id));

        universityDao.delete(university);

        assertNull(universityDao.getById(id));
    }

}
