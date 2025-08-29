package com.oms.dao;

import com.oms.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDoa extends JpaRepository<Item, String> {
    Item findByTenantAndItemId(String tenant, String itemId);
    List<Item> findByTenant(String tenant);
}
