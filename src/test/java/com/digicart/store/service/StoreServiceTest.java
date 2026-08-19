package com.digicart.store.service;

import com.digicart.store.dto.CreateStoreRequest;
import com.digicart.store.entity.Store;
import com.digicart.store.exception.EntityNotFoundException;
import com.digicart.store.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @Test
    void createDefaultsCurrencyInr() {
        CreateStoreRequest req = new CreateStoreRequest();
        req.setAdminId("a1");
        req.setName("Shop");
        req.setSubdomain("shop");
        when(storeRepository.save(any(Store.class))).thenAnswer(i -> i.getArgument(0));
        Store store = storeService.create(req);
        assertThat(store.getCurrency()).isEqualTo("INR");
        assertThat(store.getSubdomain()).isEqualTo("shop");
    }

    @Test
    void findBySubdomainThrows() {
        when(storeRepository.findBySubdomain("no")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> storeService.findBySubdomain("no")).isInstanceOf(EntityNotFoundException.class);
    }
}
