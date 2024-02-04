package notifier.controller;

import notifier.domain.Menu;
import notifier.domain.MenuRequest;
import notifier.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;//의존성 주입?
    }
    @GetMapping
    public Optional<Menu> getMenu(String date, String mealTime){
        return menuService.findTodayMenu(date, mealTime);
    }

    @GetMapping("/all")
    public List<Menu> list(){
        return menuService.findAll();
    }
    @PostMapping
    public void create(@RequestBody MenuRequest menuRequest){
        Menu menu = new Menu();
        menu.setDate(menuRequest.getDate());
        menu.setMealTime(menuRequest.getMealTime());
        menu.setItems(menuRequest.getItems());
        menuService.join(menu);
    }

}
