package com.kinesis.controller;

import com.kinesis.service.InMemoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //bad code.
public class KinesisController
{
    @Autowired
    private InMemoryServiceImp inMemoryServiceImp;


    @RequestMapping(value = "/")
    public List<String> getRecievedData()
    {
        return inMemoryServiceImp.getData();
    }
}
