package br.fai.lds.api.config.beans;

import br.fai.lds.db.dao.*;
import br.fai.lds.db.dao.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdsRestApiConfig {

    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Bean
    public AnnouncementDao getAnnouncementDao() {
        return new AnnouncementDaoImpl();
    }

    @Bean
    public CategoryDao getCategoryDao() {
        return new CategoryDaoImpl();
    }

    @Bean
    public CityDao getCityDao() {
        return new CityDaoImpl();
    }

    @Bean
    public PublicationDao getPublicationDao() {
        return new PublicationDaoImpl();
    }

    @Bean
    public ShopDao getRequestDao() {
        return new ShopDaoImpl();
    }

    @Bean
    public TypeServiceDao getTypeServiceDao() {
        return new TypeServiceDaoImpl();
    }

    @Bean
    public AddressDao getAddressDao() {
        return new AddressDaoImpl();
    }

}
