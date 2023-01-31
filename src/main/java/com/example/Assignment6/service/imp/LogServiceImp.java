package com.example.Assignment6.service.imp;

import com.example.Assignment6.entity.Log;
import com.example.Assignment6.entity.dto.LogDto;
import com.example.Assignment6.repo.LogRepo;
import com.example.Assignment6.service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImp implements LogService {
    @Autowired
    LogRepo logRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void save(LogDto log) {
        logRepo.save(modelMapper.map(log, Log.class));
    }
}
