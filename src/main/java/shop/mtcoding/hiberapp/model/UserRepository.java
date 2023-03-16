package shop.mtcoding.hiberapp.model;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public User save(User user) {
        em.persist(user); // DB에 영구히 기록하다. save
        return user;
    }

    @Transactional
    public User update(User user) {
        return em.merge(user); // 기존에 있는 것에 합치다. 즉, update이다. 만약 일치하는 Id가 없으면 새로 생성한다.
    }

    @Transactional
    public void delete(User user) {
        em.remove(user); // 제거하다 delete
    }

    public User findById(Long id) {
        return em.find(User.class, id); // 찾다.
    }

    public List<User> findAll(int page, int row) {
        return em.createQuery("select u from User u", User.class) // slString은 from에 테이블명이 아닌 Entity명을 넣는다.
                .setFirstResult(page * row)
                .setMaxResults(row)
                .getResultList();
    };

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class) // slString은 from에 테이블명이 아닌 Entity명을 넣는다.
                .getResultList();
    };
}
