package group1.tutoringcenter.repositories;

import group1.tutoringcenter.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {

    // findBy Field (Search)
    List<Tutor> findByNameContainingIgnoreCase(String name);

    @Query("SELECT t FROM Tutor t WHERE t.email = ?1")
    Tutor findByEmailCustom(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Tutor t SET t.experience = ?2, t.bio = ?3 WHERE t.id = ?1")
    void updateExperienceAndBioById(int id, int experience, String bio);

}
