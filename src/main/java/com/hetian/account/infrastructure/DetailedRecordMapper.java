package com.hetian.account.infrastructure;

import com.hetian.account.domain.DetailedRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DetailedRecordMapper {

    List<DetailedRecord> queryAllRecords();

    void addRecord(DetailedRecord record);
}
