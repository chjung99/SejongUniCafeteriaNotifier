package notifier.service;

import notifier.domain.Menu;
import notifier.repository.MenuRepository;

import java.util.List;
import java.util.Optional;

public class MenuService {
    private final MenuRepository menuRepository;
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Long join(Menu menu) {
        menuRepository.save(menu);
        return menu.getId();
    }

    public Optional<Menu> findTodayMenu(String date, String mealTime) {
        return menuRepository.findByDateAndMealTime(date, mealTime);
    }
    public List<Menu> findAll(){
        return menuRepository.findAll();
    }
}
