package com.digicart.store.cucumber;

import com.digicart.store.entity.Store;
import com.digicart.store.service.StoreService;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.when;

public class StoreStepDefinitions {
    @Autowired
    StoreService storeService;

    @Before
    public void stubs() {
        when(storeService.findAll()).thenReturn(List.of(new Store()));
    }
}
