package com.oms.controller;

import com.oms.dto.ItemRequest;
import com.oms.dto.ItemResponse;
import com.oms.response.BaseResponse;
import com.oms.response.ResponseBuilder;
import com.oms.service.ItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemServiceImpl itemServiceImpl;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> createItem(
            @RequestHeader( name = "x-tenant-identifier",  defaultValue = "x-tenant") String tenant,
            @RequestBody ItemRequest request
    ) {
        request.setTenant(tenant);
        String response = itemServiceImpl.createItem(request);
        return ResponseBuilder.success(null, response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<ItemResponse>> getItemByTenantAndItemId(
    @RequestHeader(name = "x-tenant-identifier",  defaultValue = "x-tenant") String tenant,
    @RequestParam(name = "itemId", required = true) String itemId
    ) {
        ItemResponse item = itemServiceImpl.getItemByItemId(tenant,itemId);
        return ResponseBuilder.success(item, "Item fetched successfully");
    }

    @GetMapping("/{tenant}")
    public ResponseEntity<BaseResponse<List<ItemResponse>>> getItemsByTenant(
            @PathVariable("tenant") String tenant
    ) {
        List<ItemResponse> items = itemServiceImpl.getItemsByTenantId(tenant);
        return ResponseBuilder.success(items, "Item fetched successfully");
    }
}
