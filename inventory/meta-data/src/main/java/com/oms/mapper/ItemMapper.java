package com.oms.mapper;

import com.oms.dto.ItemRequest;
import com.oms.dto.ItemResponse;
import com.oms.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target = "id", ignore = true)
    Item toItem(ItemRequest dto);

    ItemResponse toItemResponse(Item item);
}
