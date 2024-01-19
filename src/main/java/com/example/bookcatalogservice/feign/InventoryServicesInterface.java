package com.example.bookcatalogservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("INVENTORY-SERVICE")
public interface InventoryServicesInterface {
    @GetMapping("api/v1/inventory/checkStockLevelsBeforeDeleting/{catalogId}")
    public Boolean checkStockLevelsBeforeDeleting(@PathVariable Integer catalogId);

    @GetMapping("api/v1/inventory/checkStockAvailability/{stockId}")
    public Boolean checkStockAvailability(@PathVariable Integer stockId);
}
