package com.example.crud.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/favorites")
public class Favorites {


//    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ItemDto>> getFavoriteItems() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
//            throw new AccessDeniedException("Вам нужно авторизоваться для доступа к избранному");
//        }
//        User user = userService.findByUsername(authentication.getName()).get();
//        Optional<Favorite> optFavorites = favoriteService.findByUser(user);
//        Collection<Item> items;
//        if (optFavorites.isPresent()) {
//            Favorite favorites = optFavorites.get();
//            items = favorites.getItems();
//            LOGGER.info(String.format("Пользователь с id %d успешно получил список избранных товаров", user.getId()));
//            List<ItemDto> itemsDto = items.stream().map(i -> itemMapper.itemToItemDto(i)).collect(Collectors.toList());
//            return ResponseEntity.ok(itemsDto);
//        } else {
//            List<ItemDto> itemsDto  = new ArrayList<>();
//            return ResponseEntity.ok(itemsDto);
//        }
//    }

    @GetMapping("/existsItem/{id}")
    public String getCheckItem(@PathVariable long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange("http://192.168.1.38:8080/api/favorites/existsItem/"+id, HttpMethod.GET, entity, String.class);
        return respEntity.getBody();
    }

//    @Transactional
//    @PatchMapping("/items/add/{id}")
//    public ResponseEntity<Void> addItemToFavorites(@PathVariable Long id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
//            throw new AccessDeniedException("Вам нужно авторизоваться для того чтобы добавить в избранное");
//        }
//        User user = userService.findByUsername(authentication.getName()).get();
//        Favorite favorite;
//        if (user.getFavorite() == null) {
//            favorite = new Favorite();
//            favorite.setUser(user);
//            LOGGER.info(String.format("Пользователь с id %d успешно создал раздел избранного", user.getId()));
//            favoriteService.persist(favorite);
//            user.setFavorite(favorite);
//        } else {
//            favorite = user.getFavorite();
//        }
//        if(favorite.getItems() == null) {
//            Collection<Item> items = new ArrayList<>();
//            favorite.setItems(items);
//        }
//        Item item = itemService.getByKey(id);
//        if (!favorite.getItems().contains(item)) {
//            favorite.getItems().add(item);
//        }
//        favoriteService.update(favorite);
//        LOGGER.info(String.format("Пользователь с id %d успешно добавил товар в избранное", user.getId()));
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @Transactional
//    @DeleteMapping("/items/delete/{id}")
//    public ResponseEntity<Void> deleteFavoriteItem(@PathVariable Long id) {
//        Item item = itemService.getByKey(id);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
//            throw new AccessDeniedException("Вам нужно авторизоваться для доступа к избранному");
//        }
//        User user = userService.findByUsername(authentication.getName()).get();
//        favoriteService.findByUser(user).ifPresent(favorite -> {
//            favorite.getItems().remove(item);
//            LOGGER.info(String.format("Пользователь с id %d успешно удалил товар с id %d из избранного", user.getId(), item.getId()));
//        });
//        return ResponseEntity.ok().body(null);
//    }

//    @Transactional
//    @GetMapping(value = "/shops", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ShopDto>> getFavoriteShops() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
//            throw new AccessDeniedException("Вам нужно авторизоваться для доступа к избранному");
//        }
//        User user = userService.findByUsername(authentication.getName()).get();
//        Optional<Favorite> optFavorites = favoriteService.findByUser(user);
//        Collection<Shop> shops;
//        if (optFavorites.isPresent()) {
//            Favorite favorites = optFavorites.get();
//            shops = favorites.getShops();
//            LOGGER.info(String.format("Пользователь с id %d успешно получил список избранных магазинов", user.getId()));
//            List<ShopDto> shopsDto = shops.stream().map(s -> shopMapper.shopToShopDto(s)).collect(Collectors.toList());
//            return ResponseEntity.ok(shopsDto);
//        } else {
//            List<ShopDto> shopsDto  = new ArrayList<>();
//            return ResponseEntity.ok(shopsDto);
//        }
//    }
//
//    @Transactional
//    @PatchMapping("/shops/add/{id}")
//    public ResponseEntity<Void> addShopToFavorites(@PathVariable Long id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
//            throw new AccessDeniedException("Вам нужно авторизоваться для того чтобы добавить в избранное");
//        }
//        User user = userService.findByUsername(authentication.getName()).get();
//        Favorite favorite;
//        if (user.getFavorite() == null) {
//            favorite = new Favorite();
//            favorite.setUser(user);
//            LOGGER.info(String.format("Пользователь с id %d успешно создал раздел избранного", user.getId()));
//            favoriteService.persist(favorite);
//            user.setFavorite(favorite);
//        } else {
//            favorite = user.getFavorite();
//        }
//        if(favorite.getShops() == null) {
//            Collection<Shop> shops = new ArrayList<>();
//            favorite.setShops(shops);
//        }
//        Shop shop = shopService.getByKey(id);
//        if (!favorite.getShops().contains(shop)) {
//            favorite.getShops().add(shop);
//        }
//        favorite.getShops().add(shopService.getByKey(id));
//        favoriteService.update(favorite);
//        LOGGER.info(String.format("Пользователь с id %d успешно добавил товар в избранное", user.getId()));
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @Transactional
//    @DeleteMapping("/shops/delete/{id}")
//    public ResponseEntity<Void> deleteFavoriteShop(@PathVariable Long id) {
//        Shop shop = shopService.getByKey(id);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
//            throw new AccessDeniedException("Вам нужно авторизоваться для доступа к избранному");
//        }
//        User user = userService.findByUsername(authentication.getName()).get();
//        favoriteService.findByUser(user).ifPresent(favorite -> {
//            favorite.getShops().remove(shop);
//            LOGGER.info(String.format("Пользователь с id %d успешно удалил  магазин с id %d из избранного", user.getId(), shop.getId()));
//        });
//        return ResponseEntity.ok().body(null);
//    }
}
