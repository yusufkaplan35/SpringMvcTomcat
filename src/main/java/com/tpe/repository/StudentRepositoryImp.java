package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component
@Repository //db ye erişimi olan  - okunabilirlik ve database erişim olduğu anlaşılması için
public class StudentRepositoryImp implements StudentRepository{

    private SessionFactory sessionFactory;
    @Autowired
    public StudentRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Student student) {
        Session session=sessionFactory.openSession();
        Transaction tx =session.beginTransaction();

        session.saveOrUpdate(student);//db de varsa update, yoksa insert/save

        tx.commit();
        session.close();
    }

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Student> studentList = session.createQuery("FROM Student", Student.class).getResultList();

        tx.commit();
        session.close();
        return studentList;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student student = session.get(Student.class,id); //id: 1,2,3,4,5

        Optional<Student> optional = Optional.ofNullable(student);
        //null yerine içi boş bir optional objesi döner

        tx.commit();
        session.close();
        return optional;
    }

    @Override
    public void delete(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.delete(student);

        tx.commit();
        session.close();


    }
}
