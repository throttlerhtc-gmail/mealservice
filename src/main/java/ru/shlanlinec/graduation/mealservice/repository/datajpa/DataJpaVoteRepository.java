package ru.shlanlinec.graduation.mealservice.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.shlanlinec.graduation.mealservice.repository.VoteRepository;
import ru.shlanlinec.graduation.mealservice.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id).filter(meal -> meal.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    @Override
    public List<Vote> getBetweenInclusive(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudVoteRepository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public Vote getWithUser(int id, int userId) {
        return crudVoteRepository.getWithUser(id, userId);
    }
}
