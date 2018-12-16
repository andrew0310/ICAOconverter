package com.jepp.icaoConverter.dao;

import com.jepp.icaoConverter.model.ICAOcode;
import com.jepp.icaoConverter.model.WordInput;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Log
public class ICAOcodeDAO {

    public static boolean saveStringToDatabase(WordInput wordInput) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.save(wordInput);
            transaction.commit();

        } catch (PersistenceException pe) {
            log.log(Level.SEVERE, "Error while saving", pe);
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
        return true;
    }

    public static List<String> getLastTenStrings() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        List<String> wordList = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Query<WordInput> query = session.createQuery("from WordInput order by dateTime, setMaxResults(10)", WordInput.class);

            String word = query.getSingleResult().toString();
            wordList.add(word);
            return wordList;
        } catch (PersistenceException pe) {
            log.log(Level.SEVERE, "Error while requesting device data", pe);
        }
        return new ArrayList<>();
    }

    public static Optional<ICAOcode> getCodeFromDatabase(String letter, String type) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            if (type == fonetic) {
                Query<ICAOcode> query = session.createQuery("from ICAOcode where letter =:letter and foneticCode = :foneticCode", ICAOcode.class);
                query.setParameter("letter", letter);
                return Optional.of(query.getSingleResult());
            } else {
                Query<ICAOcode> query = session.createQuery("from ICAOcode where letter =:letter and iCode = :iCode", ICAOcode.class);
                query.setParameter("letter", letter);
                return Optional.of(query.getSingleResult());
            }
        } catch (PersistenceException pe) {
            log.log(Level.SEVERE, "Error while requesting data");
        }
        return Optional.empty();
    }
}

