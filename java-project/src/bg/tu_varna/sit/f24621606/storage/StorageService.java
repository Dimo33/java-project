package bg.tu_varna.sit.f24621606.storage;

import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 Отговаря за четене и запис във файл.
 */

public interface StorageService {

    void save(RestaurantService restaurantService, String fileName);

    void load(RestaurantService restaurantService, String fileName);

}