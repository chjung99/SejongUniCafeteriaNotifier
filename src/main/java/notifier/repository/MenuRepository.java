package notifier.repository;

import notifier.domain.Menu;
import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Menu save(Menu menu);
    Optional<Menu> findById(Long id);
    Optional<Menu> findByDateAndMealTime(String date, String mealTime);
    List<Menu> findAll();
}
