package com.example.practice15.services;

import com.example.practice15.models.Game;
import com.example.practice15.models.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Queue;

@Service
public class LevelService {
    @Autowired
    private final SessionFactory sessionFactory;
    private Session session;

    public LevelService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addLevel(Level level) {
        var transaction = session.beginTransaction();
        session.saveOrUpdate(level);
        transaction.commit();
    }

    public Level findLevel(int id ) {
        return session.createQuery("select l from level  l where l.id = '" + id + "'", Level.class).getSingleResult();
    }

    public List<Level> findAllLevels() {
        return session.createQuery("select l from level l",Level.class).list();
    }

    public Game findGameByLevel(int levelId) {
        return session.createQuery("select l from level  l where l.id = '" + levelId + "'", Level.class).getSingleResult().getGame();
    }

    public void deleteLevel(int id) {
        var transaction = session.beginTransaction();
        Level level = findLevel(id);
        session.delete(level);
        transaction.commit();
    }


    public List<Level> filterLevelName() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Level> q = cb.createQuery(Level.class);
        Root<Level> root = q.from(Level.class);
        q.select(root).orderBy(cb.desc(root.get("levelName")));
        Query<Level> query = session.createQuery(q);
        return query.getResultList();
    }

    public List<Level> filterById() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Level> q = cb.createQuery(Level.class);
        Root<Level> root = q.from(Level.class);
        q.select(root).where(cb.gt(root.get("id"),3));
        Query<Level> query = session.createQuery(q);
        return query.getResultList();
    }

    public List<Level> filterByComplexity() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Level> q = cb.createQuery(Level.class);
        Root<Level> root = q.from(Level.class);
        q.select(root);
        q.where(cb.like(root.get("complexity"),"low"));
        Query<Level> query = session.createQuery(q);
        return query.getResultList();
    }
}
