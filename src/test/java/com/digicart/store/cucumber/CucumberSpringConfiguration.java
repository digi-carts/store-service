package com.digicart.store.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import com.digicart.store.exception.GlobalExceptionHandler;
import com.digicart.store.controller.HealthController;
import com.digicart.store.controller.StoreController;
import com.digicart.store.service.StoreService;

@CucumberContextConfiguration
@WebMvcTest(controllers = { HealthController.class, StoreController.class })
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
public class CucumberSpringConfiguration {
    @MockBean
    StoreService storeService;

}
