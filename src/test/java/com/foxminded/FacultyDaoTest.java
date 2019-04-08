package com.foxminded;

import com.foxminded.dao.DAOException;
import com.foxminded.dao.FacultyDAOImpl;
import com.foxminded.domain.Faculty;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class FacultyDaoTest {

    private FacultyDAOImpl facultyDAO;

    @Before
    public void initDao(){
        facultyDAO = new FacultyDAOImpl();
    }

    @Test
    public  void create() throws DAOException{
        Faculty createdFaculty = facultyDAO.insert(new Faculty("Law"));

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


    @Test(expected = DAOException.class)
    public  void create_existing() throws DAOException{
        facultyDAO.insert(new Faculty("HARVARD"));
        facultyDAO.insert(new Faculty("HARVARD"));
    }

    @Test
    public  void update() throws DAOException{
        Faculty faculty = facultyDAO.insert(new Faculty("OXFORD"));
        faculty.setName("NEW OXFORD");

        Faculty updatedFaculty = facultyDAO.update(faculty);

        assertTrue("NEW OXFORD".equals(updatedFaculty.getName()));

        facultyDAO.delete(updatedFaculty);
        assertEquals(updatedFaculty.getId(), -1);
    }


    @Test
    public  void delete() throws DAOException{
        Faculty faculty = facultyDAO.insert( new Faculty("BERKLEY"));
        Long id = faculty.getId();
        assertNotNull(facultyDAO.findById(id));

        facultyDAO.delete(faculty);

        assertNull(facultyDAO.findById(id));
    }

}
