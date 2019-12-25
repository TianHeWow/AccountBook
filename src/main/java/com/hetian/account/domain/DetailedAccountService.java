package com.hetian.account.domain;

import com.hetian.account.infrastructure.DetailedRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetailedAccountService {
    @Autowired
    DetailedRecordMapper mapper;

    public void addRecord(DetailedRecord record){
        mapper.addRecord(record);
    }

    public List<DetailedRecord> queryAllRecords(){
        return mapper.queryAllRecords();
    }


}
