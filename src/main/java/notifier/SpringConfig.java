package notifier;

import notifier.repository.JdbcMenuRepository;
import notifier.repository.MemoryMenuRepository;
import notifier.repository.MenuRepository;
import notifier.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MenuService menuService(){
        return new MenuService(menuRepository());
    }
//    @Bean
//    public MenuRepository menuRepository(){
//        return new MemoryMenuRepository();
//    }
    @Bean
    public MenuRepository menuRepository(){
        return new JdbcMenuRepository(dataSource);
    }
}
