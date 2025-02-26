package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // @EntityGraph 는 FETCH JOIN 의 간편 버전

//    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    @EntityGraph(attributePaths = {"user"})
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

//    @Query("SELECT t FROM Todo t " +
//            "LEFT JOIN FETCH t.user " +
//            "WHERE t.id = :todoId")
    @EntityGraph(attributePaths = {"user"}) // 기존 페치 조인을 위한 JPQL 퀴리를 짜지 않고, @EntityGrapth 를 활용하여 페치 조인을 진행
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    int countById(Long todoId);
}
