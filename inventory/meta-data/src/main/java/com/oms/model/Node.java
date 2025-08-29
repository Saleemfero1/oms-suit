package com.oms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "node")
public class Node {
    @Id
    String id;
    @NonNull
    String nodeId;
    @NonNull
    String nodeName;
    @NonNull
    String nodeType;
    String description;
    String location;
    String zipCode;
    List<String> networkIds;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "custom_attributes", columnDefinition = "jsonb")
    Map<String, Object> customAttributes;
}
