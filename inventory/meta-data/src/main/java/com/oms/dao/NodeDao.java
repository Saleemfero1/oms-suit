package com.oms.dao;

import com.oms.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeDao extends JpaRepository<Node, String> {

}
