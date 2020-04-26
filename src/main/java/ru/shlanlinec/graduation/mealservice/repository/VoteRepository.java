package ru.shlanlinec.graduation.mealservice.repository;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.shlanlinec.graduation.mealservice.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.shlanlinec.graduation.mealservice.util.DateTimeUtil.getEndExclusive;
import static ru.shlanlinec.graduation.mealservice.util.DateTimeUtil.getStartInclusive;

public interface VoteRepository {
    // null if updated vote do not belong to userId
    Vote save(Vote vote, int userId);

    // false if vote do not belong to userId
    boolean delete(int id, int userId);

    // null if vote do not belong to userId
    Vote get(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAll(int userId);

    // ORDERED date desc
    default List<Vote> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return getBetweenInclusive(getStartInclusive(startDate), getEndExclusive(endDate), userId);
    }

    // ORDERED date desc
    List<Vote> getBetweenInclusive(@NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate, int userId);

    default Vote getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
