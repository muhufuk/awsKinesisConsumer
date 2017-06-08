package com.kinesis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InMemoryServiceImp
{
    private List<String> list = new ArrayList<>();

    public void add(String data)
    {
        list.add(data);
    }

    public List<String> getData()
    {
        return list;
    }
}
