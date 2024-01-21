package notifier.repository;

import notifier.domain.Menu;

import java.util.*;

public class MemoryMenuRepository implements MenuRepository{
    public static Map<Long, Menu> store = new HashMap<>();
    @Override
    public Menu save(Menu menu) {
        store.put(menu.getId(), menu);
        return menu;
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Menu> findByDateAndMealTime(Date date, String mealTime) {
        return store.values().stream()
                .filter(menu -> menu.getDate().equals(date) && menu.getMealTime().equals(mealTime))
                .findAny();
    }

    @Override
    public List<Menu> findAll() {return new ArrayList<>(store.values());}
    public void clearStore(){store.clear();}
}
