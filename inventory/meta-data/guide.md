# Metadata Service Design Guide
A comprehensive guide for building a **multi-tenant, flexible, and scalable metadata service** using Java, Spring Boot, and PostgreSQL.

---
## 🚀 1. Core Purpose
- **Centralized metadata hub** for Inventory, Order, OMS, Pricing, and more.
- **Tenant-specific configurations** with strong data isolation.
- **Configurable, extensible** metadata for items, nodes, warehouses, etc.

---
## 🗂️ 2. Data Model (Multi-Tenant)
**Key Principle:** Every table uses `tenant_id` for strict isolation.
### **tenants**
| tenant\_id | tenant\_name | config (JSONB)                                |
| ---------- | ------------ | --------------------------------------------- |
| T1         | Vendor A     | { "timezone": "UTC+5:30", "currency": "INR" } |
| T2         | Vendor B     | { "timezone": "UTC+1", "currency": "EUR" }    |
> ### **items**
| item\_id | tenant\_id | name  | metadata (JSONB)                                       |
| -------- | ---------- | ----- | ------------------------------------------------------ |
| I1       | T1         | Shirt | { "size": ["S","M","L"], "color": ["blue","black"] }   |
| I1       | T2         | Shirt | { "material": "cotton", "brand": "Xyz" }               |
> ### **nodes**
| node\_id | tenant\_id | node\_type | config (JSONB)                             |
| -------- | ---------- | ---------- | ------------------------------------------ |
| N1       | T1         | Store      | { "location": "Mumbai", "capacity": 1000 } |
| N2       | T1         | Warehouse  | { "location": "Pune", "capacity": 5000 }   |
| N3       | T2         | Store      | { "location": "Berlin", "capacity": 2000 } | 
- **Isolation:** All queries filter by `tenant_id`.
- **Flexibility:** JSONB columns allow per-tenant customization.

---
## 🛠️ 3. Service APIs (Sample)
All endpoints are **tenant-aware**:

- `POST /{tenantId}/items` → Add item metadata
- `GET /{tenantId}/items/{itemId}` → Fetch item metadata
- `POST /{tenantId}/nodes` → Add node metadata
- `GET /{tenantId}/nodes/{nodeId}` → Fetch node metadata
- `GET /{tenantId}/configs` → Fetch tenant-level configs

---
## 🏢 4. Multi-Tenant Support Strategy
**Option 1: Shared DB + Tenant Column (Recommended)**
- Single schema, every table has `tenant_id`.
- Enforce row-level security for isolation.

**Option 2: Dedicated Schema/DB per Tenant**
- Each tenant has a separate schema or DB.
- Maximum isolation, higher operational cost.

---
## ⚙️ 5. Tech Stack & Considerations

- **Database:** PostgreSQL (with JSONB support)
- **Cache:** Redis (cache metadata per tenant)
- **API Layer:** Spring Boot (Java)
- **Authentication:** Tenant resolution via API key or JWT claim
- **Config Management:** Store defaults in `tenant` table, override at item/node level

---
## 💡 6. Example Use Case

- **Tenant A:** Needs color, size for items.
- **Tenant B:** Needs brand, material for items.
- **Unified API:** Both use the same endpoints, but metadata structure is tenant-specific.

---
## 🌟 7. Extra Features (Future Roadmap)

- **Versioning:** Track metadata changes (audit log)
- **Overrides:** Global defaults + tenant overrides
- **Schema Validation:** Define allowed metadata fields per tenant (JSON Schema)

---
This design empowers you to build a robust, configurable, and tenant-aware metadata service for any multi-tenant OMS, inventory, pricing, or catalog system.
---

