package com.hetian.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedRecord {

    private String name;
    private com.hetian.account.domain.CategoryType categoryType;
    private String content;
    private String note;
    private LocalDateTime occurredTime;
    private BigDecimal amount;

    public boolean isIncome(){
        return categoryType == categoryType.Income;
    }

    public boolean isSpending(){
        return categoryType == categoryType.Spending;
    }

    public boolean haveSameYearAndMonth(int year, int month){
        return (occurredTime.getMonthValue() == month) && (occurredTime.getYear() == year);
    }


    @Override
    public String toString() {
        return name + " : " + categoryType + " : " + content + " : " + note + " : " + amount + " : " + occurredTime;
    }

//    public String getName() {
//        return name;
//    }
//
//    public categoryType getCategoryType() {
//        return categoryType;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public String getNote() {
//        return note;
//    }



//    public BigDecimal getAmount() {
//        return amount;
//    }



//    public LocalDateTime getOccurredTime() {
//        return occurredTime;
//    }
//
//    public DetailedRecord(String name, categoryType categoryType, String content, String note, BigDecimal amount, LocalDateTime occurredTime) {
//        name = name;
//        this.categoryType = categoryType;
//        content = content;
//        note = note;
//        amount = amount;
//        this.occurredTime = occurredTime;
//    }


}
