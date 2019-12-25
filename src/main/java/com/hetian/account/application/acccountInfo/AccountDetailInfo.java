package com.hetian.account.application.acccountInfo;

import com.hetian.account.domain.DetailedRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailInfo {
    private String name;
    private com.hetian.account.domain.CategoryType categoryType;
    private String content;
    private String note;
    private LocalDateTime occurredTime;
    private BigDecimal amount;

    public AccountDetailInfo from(DetailedRecord record){
        name = record.getName();
        categoryType = record.getCategoryType();
        content = record.getContent();
        note = record.getNote();
        occurredTime = record.getOccurredTime();
        amount = record.getAmount();
        return this;
    }

    public DetailedRecord to(){
        return new DetailedRecord(name, categoryType, content, note, occurredTime, amount);
    }

}
