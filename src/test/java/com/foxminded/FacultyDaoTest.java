package com.foxminded;

import com.foxminded.dao.DaoException;
import com.foxminded.dao.FacultyDaoImpl;
import com.foxminded.model.Faculty;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class FacultyDaoTest {

    private FacultyDaoImpl facultyDAO;

    @Before
    public void initDao(){
        facultyDAO = new FacultyDaoImpl();
    }

    @Test
    public  void create() throws DaoException {
        Faculty createdFaculty = facultyDAO.create(new Faculty("Law"));

        assertEquals("Law", createdFaculty.getName());

        facultyDAO.delete(createdFaculty);
        assertEquals(createdFaculty.getId(), -1);
    }


    @Test
    public void findById() {
        Faculty faculty = facultyDAO.findById(2);

        assertTrue("Management".equals(faculty.getName()));
    }


    @Test
    public void findAll() {
        List<Faculty> faculties = facultyDAO.findAll();

        assertEquals("Applied Math", faculties.get(0).getName());
        assertEquals("Management", faculties.get(1).getName());
        assertEquals("History", faculties.get(2).getName());
    }


    @Test(expected = DaoException.class)
    public  void create_existing() throws DaoException {
        facultyDAO.create(new Faculty("HARVARD"));
        facultyDAO.create(new Faculty("HARVARD"));
    }

    @Test
    public  void update() throws DaoException {
        Faculty faculty = facultyDAO.create(new Faculty("OXFORD"));
        faculty.setName("NEW OXFORD");

        Faculty updatedFaculty = facultyDAO.update(faculty);

        assertTrue("NEW OXFORD".equals(updatedFaculty.getName()));

        facultyDAO.delete(updatedFaculty);
        assertEquals(updatedFaculty.getId(), -1);
    }


    @Test
    public  void delete() throws DaoException {
        Faculty faculty = facultyDAO.create( new Faculty("BERKLEY"));
        Long id = faculty.getId();
        assertNotNull(facultyDAO.findById(id));

        facultyDAO.delete(faculty);

        assertNull(facultyDAO.findById(id));
    }

}
