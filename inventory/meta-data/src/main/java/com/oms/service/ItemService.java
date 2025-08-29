package com.oms.service;

import com.oms.dto.ItemRequest;
import com.oms.dto.ItemResponse;

import java.util.List;

public interface ItemService {
    public String createItem(ItemRequest itemRequest);

    public ItemResponse getItemByItemId(String tenantId, String itemId);

    public List<ItemResponse> getItemsByTenantId(String tenantId);

}
