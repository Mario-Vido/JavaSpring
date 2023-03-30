package sk.stuba.fei.uim.oop.assignment3.ListBooks.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendingListRepository extends JpaRepository<LendList, Long> {
    List<LendList> findAll();
    LendList findLendListById(Long id);
    void deleteById(Long id);
}
