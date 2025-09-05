package com.example.dish.service.impl;

import com.example.dish.dto.request.DishRequestDTO;
import com.example.dish.dto.request.DishUpdateDTO;
import com.example.dish.dto.response.DishResponse;
import com.example.dish.dto.response.DishResponseDTO;
import com.example.dish.event.DishEvent;
import com.example.dish.exception.DishExistException;
import com.example.dish.exception.MenuExistException;
import com.example.dish.mapper.DishMapper;
import com.example.dish.models.Dish;
import com.example.dish.models.Menu;
import com.example.dish.repository.DishRepository;
import com.example.dish.repository.MenuRepository;
import com.example.dish.service.DishEventProducer;
import com.example.dish.service.DishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;
    private final DishEventProducer dishEventProducer;


    public DishServiceImpl(DishRepository dishRepository,  MenuRepository menuRepository, DishEventProducer dishEventProducer) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
        this.dishEventProducer = dishEventProducer;
    }

    public DishResponseDTO createDish(DishRequestDTO dishRequestDTO){

        if(dishRequestDTO == null){
            return null;
        }

        Menu menu = menuRepository.findById(dishRequestDTO.menuId()).orElseThrow(() -> new MenuExistException("Menu with id " + dishRequestDTO.menuId() + " does not exist"));

        Dish dish = DishMapper.toDishEntity(dishRequestDTO, menu);
        dish.setCreatedAt(LocalDateTime.now());
        dish.setUpdatedAt(LocalDateTime.now());

        Dish savedDish = dishRepository.save(dish);

        DishResponseDTO dishResponseDTO = DishMapper.toDishResponseDTO(savedDish);
        DishResponse dishResponse = DishMapper.toDishResponse(savedDish);
        DishEvent event = new DishEvent(DishEvent.EventType.CREATED, dishResponse);
        dishEventProducer.sendDishEvent(event);

        return dishResponseDTO;
    }

    public Page<DishResponseDTO> getAllDishes(Pageable pageable) {
        Page<Dish> dishes = dishRepository.findAll(pageable);
        return  dishes.map(DishMapper::toDishResponseDTO);
    }

    public DishResponseDTO getDishById(UUID id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new DishExistException("Dish with id " + id + " does not exist"));
        return DishMapper.toDishResponseDTO(dish);
    }

    public DishResponseDTO updateDish(UUID id, DishUpdateDTO dishUpdateDTO) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish with id " + id + " does not exist"));

        if (dishUpdateDTO.name() != null) {
            dish.setName(dishUpdateDTO.name());
        }

        if (dishUpdateDTO.description() != null) {
            dish.setDescription(dishUpdateDTO.description());
        }

        if (dishUpdateDTO.price() != null) {
            dish.setPrice(dishUpdateDTO.price());
        }

        if (dishUpdateDTO.image_url() != null) {
            dish.setImage_url(dishUpdateDTO.image_url());
        }

        if (dishUpdateDTO.isAvailable() != null) {   // now nullable Boolean
            dish.setAvailable(dishUpdateDTO.isAvailable());
        }

        if (dishUpdateDTO.menuId() != null) {
            Menu menu = menuRepository.findById(dishUpdateDTO.menuId())
                    .orElseThrow(() -> new MenuExistException("Menu with id " + dishUpdateDTO.menuId() + " does not exist"));
            dish.setMenu(menu);
        }

        dish.setUpdatedAt(LocalDateTime.now());

        Dish updatedDish = dishRepository.save(dish);

        DishResponseDTO dishResponseDTO = DishMapper.toDishResponseDTO(updatedDish);
        DishResponse dishResponse = DishMapper.toDishResponse(updatedDish);
        DishEvent event = new DishEvent(DishEvent.EventType.UPDATED, dishResponse);
        dishEventProducer.sendDishEvent(event);

        return dishResponseDTO;
    }

    public void deleteDish(UUID id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new DishExistException("Dish with id " + id + " does not exist"));

        dishRepository.delete(dish);

        DishResponseDTO response = DishMapper.toDishResponseDTO(dish);
        DishResponse dishResponse = DishMapper.toDishResponse(dish);
        DishEvent event = new DishEvent(DishEvent.EventType.DELETED, dishResponse);
        dishEventProducer.sendDishEvent(event);
    }

}
