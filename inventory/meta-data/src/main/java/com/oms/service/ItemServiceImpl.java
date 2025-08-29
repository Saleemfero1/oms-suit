package com.oms.service;

import com.oms.dao.ItemDoa;
import com.oms.dto.ItemRequest;
import com.oms.dto.ItemResponse;
import com.oms.exception.AppException;
import com.oms.mapper.ItemMapper;
import com.oms.model.Item;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemDoa itemDoa;
    private ItemMapper itemMapper;

    @Transactional
    @Override
    public String createItem(ItemRequest itemRequest) {
        if(Objects.isNull(itemRequest)){
            throw new AppException("ItemRequest cannot be null", 400);
        }
        Item existingItem = itemDoa.findById(itemRequest.getItemId()).orElse(null);
        if (existingItem != null) {
            // Update existing item
            existingItem.setName(itemRequest.getName());
            existingItem.setDescription(itemRequest.getDescription());
            existingItem.setCategoryPath(itemRequest.getCategoryPath());
            existingItem.setProductClass(itemRequest.getProductClass());
            existingItem.setUom(existingItem.getUom());
            existingItem.setCustomAttributes(itemRequest.getCustomAttributes());
            return "Item updated successfully";
        } else {
            Item newItem = itemMapper.toItem(itemRequest);
            itemDoa.save(newItem);
            return "Item created successfully";
        }
    }

    @Override
    public ItemResponse getItemByItemId(String tenantId, String itemId) {
        Item item = itemDoa.findByTenantAndItemId(tenantId, itemId);
        if (Objects.isNull(item)) {
            throw new AppException("Item not found", 404);
        }
        return itemMapper.toItemResponse(item);
    }

    @Override
    public List<ItemResponse> getItemsByTenantId(String tenantId) {
        if(!StringUtils.hasLength(tenantId)){
            throw new AppException("TenantId cannot be null or empty", 400);
        }
        List<Item> items = itemDoa.findByTenant(tenantId);
        if(CollectionUtils.isEmpty(items)){
            throw new AppException("No items found for the tenant", 404);
        }
        return items.stream().map(itemMapper::toItemResponse).toList();
    }
}
