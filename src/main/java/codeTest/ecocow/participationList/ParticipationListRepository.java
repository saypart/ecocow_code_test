package codeTest.ecocow.participationList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationListRepository extends JpaRepository<ParticipationList, Long> {
    List<ParticipationList> findByMovie_MovieId(Long movieId);

    Optional<ParticipationList> findByParticipationListId(long participationListId);

    Optional<ParticipationList> findByMovie_MovieIdAndMoviePerson_PersonId(Long movieId, Long personId);
}