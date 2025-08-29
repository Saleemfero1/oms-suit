package com.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class ItemResponse {
    String tenant;
    String itemId;
    String name;
    String description;
    String uom;
    String productClass;
    String categoryPath;
    Map<String, Object> customAttributes;
}
