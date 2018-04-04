package com.mattstine.rentaltrucks.reservations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController("/catalog-items")
public class CatalogItemController {

    private final CatalogItemRepository repository;

    public CatalogItemController(CatalogItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Collection<CatalogItem> listCatalogItems() {
        return toList(repository.findAll());
    }

    private static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}
