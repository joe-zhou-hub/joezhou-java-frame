package com.joezhou.springdata2elasticsearch.es;

import com.joezhou.springdata2elasticsearch.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JoeZhou
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, Integer> {

}