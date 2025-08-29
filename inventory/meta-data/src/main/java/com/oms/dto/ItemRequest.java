package com.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    String tenant;
    String itemId;
    String name;
    String description;
    String uom;
    String productClass;
    String categoryPath;
    Map<String, Object> customAttributes;
}
