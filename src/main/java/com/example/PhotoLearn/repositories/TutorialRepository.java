package com.example.PhotoLearn.repositories;

import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    @Query("select new com.example.PhotoLearn.dto.TutorialDto(" +
                "t, " +
                "count(tl), " +
                "sum(case when tl = :currentUser then 1 else 0 end) > 0" +
            ") " +
            "from Tutorial as t left join t.likes as tl " +
            "where t.id = :id " +
            "group by t"
    )
    TutorialDto findTutorialById(@Param("id") Long id, @Param("currentUser") User currentUser);

    @Query("select new com.example.PhotoLearn.dto.TutorialDto(" +
                "t, " +
                "count(tl), " +
                "sum(case when tl = :currentUser then 1 else 0 end) > 0" +
            ") " +
            "from Tutorial as t left join t.likes as tl " +
            "group by t " +
            "order by t.createdOn desc"
    )
    Page<TutorialDto> findAllByOrderByCreatedOnDesc(Pageable pageable, @Param("currentUser") User currentUser);

    @Query("select new com.example.PhotoLearn.dto.TutorialDto(" +
            "t, " +
            "count(tl), " +
            "sum(case when tl = :currentUser then 1 else 0 end) > 0" +
            ") " +
            "from Tutorial as t left join t.likes as tl " +
            "where UPPER(t.title) like concat('%', UPPER(:filter), '%') " +
            "group by t " +
            "order by t.createdOn desc"
    )
    Page<TutorialDto> findByTitleContainingIgnoreCase(
            @Param("filter") String filter,
            Pageable pageable,
            @Param("currentUser") User currentUser
    );

}
