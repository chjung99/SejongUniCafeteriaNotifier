package notifier;

import notifier.repository.MemoryMenuRepository;
import notifier.repository.MenuRepository;
import notifier.service.MenuService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MenuService menuService(){
        return new MenuService(menuRepository());
    }
    @Bean
    public MenuRepository menuRepository(){
        return new MemoryMenuRepository();
    }
}
