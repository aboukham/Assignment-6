package com.example.Assignment6.repo;

import com.example.Assignment6.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends CrudRepository<Log, Long> {
}
