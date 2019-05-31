package com.foxminded.dao.impl;

import com.foxminded.util.HibernateUtil;
import com.foxminded.dao.StudentDao;
import com.foxminded.dao.DaoException;
import com.foxminded.model.StudentCard;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    public StudentCard create(StudentCard student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot create student " + student.getName(), e);
        }
        return student;
    }

    public StudentCard findById(final long id) {
        StudentCard student;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            student = session.get(StudentCard.class, id);
            transaction.commit();
        } catch (Exception e) {
            throw new DaoException("Cannot find student with id " + id, e);
        }
        return student;
    }

    public List<StudentCard> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from students s order by s.id asc", StudentCard.class).list();
        }
    }

    public StudentCard update(final StudentCard student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update students set name =:n where id=:i");
            query.setParameter("n", student.getName());
            query.setParameter("i", student.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot update student " + student.getName(), e);
        }
        return student;
    }

    public void delete(StudentCard student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from students where id=:i");
            query.setParameter("i", student.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot delete student " + student.getName(), e);
        }
    }

    public List<StudentCard> findStudentsByGroupId(long groupId) {
         List<StudentCard> result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from students s order by s.id asc");
//            Query query = session.createQuery("from students s where s.group_id=:i order by s.id asc");
//            query.setParameter("i", groupId);
            result = query.list();
            System.out.println(result);
        } catch (Exception e) {
            throw new DaoException("Cannot find all students of group with id " + groupId, e);
        }
        return result;
    }

}
