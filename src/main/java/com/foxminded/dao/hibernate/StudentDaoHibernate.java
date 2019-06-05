package com.foxminded.dao.hibernate;

import com.foxminded.util.HibernateUtil;
import com.foxminded.dao.StudentDao;
import com.foxminded.dao.DaoException;
import com.foxminded.model.StudentCard;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class StudentDaoHibernate implements StudentDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public StudentCard create(StudentCard student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException e) {
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
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            student = session.get(StudentCard.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DaoException("Cannot find student with id " + id, e);
        }
        return student;
    }

    public List<StudentCard> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from StudentCard s order by s.id asc", StudentCard.class).list();
        }
    }

    public StudentCard update(final StudentCard student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StudentCard set name =:name where id=:id");
            query.setParameter("name", student.getName());
            query.setParameter("id", student.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot update student " + student.getName(), e);
        }
        return student;
    }

    public void delete(StudentCard student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from StudentCard where id=:id");
            query.setParameter("id", student.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot delete student " + student.getName(), e);
        }
    }

    public List<StudentCard> findStudentsByGroupId(long groupId) {
        List<StudentCard> result = null;
        try (Session session = sessionFactory.openSession()) {
             Query query = session.createQuery("from StudentCard s where s.group.id=:id order by s.id asc");
            query.setParameter("id", groupId);
            result = query.list();
            System.out.println(result);
        } catch (HibernateException e) {
            throw new DaoException("Cannot find all students of group with id " + groupId, e);
        }
        return result;
    }

}
