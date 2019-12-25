package com.hetian.account.domain;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public abstract class AccountBook {

    protected  List<DetailedRecord> accountBook = new ArrayList<>();

    // add events into regularaccountbook.regularaccountbook.regularaccountbook
    public List<DetailedRecord> addAccount(DetailedRecord event) {
        if (event != null) accountBook.add(event);
        return accountBook;
    }

    public  List<DetailedRecord> getAccountBook() {
        return accountBook;
    }

    // use predicate do search
    public List<DetailedRecord> recordsBySingleCondition(Predicate<DetailedRecord> predicate) {
        if (predicate == null) {
            return new ArrayList<>();
        }
        return accountBook.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<DetailedRecord> recordsByDoubleConditions(Predicate<DetailedRecord> predicate1, Predicate<DetailedRecord> predicate2) {
        if ((predicate1 == null) || (predicate2 == null)) {
            return new ArrayList<>();
        }
        return accountBook.stream().filter(predicate1.and(predicate2)).collect(Collectors.toList());
    }

    public List<DetailedRecord> allIncomeRecords() {
        return recordsBySingleCondition(ar -> ar.isIncome());
    }

    public List<DetailedRecord> allSpendingRecords() {
        return recordsBySingleCondition(ar -> ar.isSpending());
    }

    public List<DetailedRecord> incomeByYearAndMonthRecords(int year, int month) {
        return recordsByDoubleConditions(ar -> ar.isIncome(), ar -> ar.haveSameYearAndMonth(year, month));
    }

    public List<DetailedRecord> spendingByYearAndMonthRecords(int year, int month) {
        return recordsByDoubleConditions(ar -> ar.isSpending(), ar -> ar.haveSameYearAndMonth(year, month));
    }

    public abstract BigDecimal profitByYearAndMonth(int year, int month);

    public abstract BigDecimal totalIncomeByYearAndMonth(int year, int month);

    public abstract BigDecimal totalSpendingByYearAndMonth(int year, int month);

    protected BigDecimal sumBigDecimal(List<DetailedRecord> accountRecords) {
        BigDecimal totalNum = new BigDecimal("0");
        for(DetailedRecord detailedRecord : accountRecords){
            totalNum.add(detailedRecord.getAmount());
        }
        return totalNum;
    }



//    // search event by date
//    public List<regularaccountbook.regularaccountbook.DetailedRecord> recordByDate(int year, int month) {
//        List<regularaccountbook.regularaccountbook.DetailedRecord> targetEvents = new ArrayList<>();
//        for (regularaccountbook.regularaccountbook.DetailedRecord event : accountBook) {
//            if (matchTime(year, month, event)){
//                targetEvents.add(event);
//            }
//        }
//        return targetEvents;
//    }
//
//    private boolean matchTime(int year, int month, regularaccountbook.regularaccountbook.DetailedRecord event) {
//        return event.getOccurredTime().getMonthValue() == month && event.getOccurredTime().getYear() == year;
//    }
//
//    // search event by date and category
//    public List<regularaccountbook.regularaccountbook.DetailedRecord> searchEventByDateAndCategory(int year, int month, regularaccountbook.regularaccountbook.CategoryType regularaccountbook.regularaccountbook.CategoryType) {
//        List<regularaccountbook.regularaccountbook.DetailedRecord> targetEvents = new ArrayList<>();
//        for (regularaccountbook.regularaccountbook.DetailedRecord event : accountBook) {
//            if (matchTime(year, month, event) && event.getCategoryType() == regularaccountbook.regularaccountbook.CategoryType){
//                targetEvents.add(event);
//            }
//        }
//        return targetEvents;
//    }

//    public BigDecimal calculateTotal() {
//    BigDecimal totalNum = new BigDecimal("0");
//    // if total spending is bigger than total income. Return negative number.
//    for (regularaccountbook.regularaccountbook.DetailedRecord event : accountBook){
//        if (event.getCategoryType() == regularaccountbook.regularaccountbook.CategoryType.Income){
//            totalNum.add(event.getAmount());
//        }
//        if (event.getCategoryType() == regularaccountbook.regularaccountbook.CategoryType.Spending){
//            totalNum.subtract(event.getAmount());
//        }
//    }
//
//    return totalNum;
//}
//
//    public BigDecimal calculateTotal(regularaccountbook.regularaccountbook.CategoryType regularaccountbook.regularaccountbook.CategoryType) {
//        BigDecimal totalNum = new BigDecimal("0");
//        //  return rotal number by regularaccountbook.regularaccountbook.CategoryType
//        for (regularaccountbook.regularaccountbook.DetailedRecord event : accountBook){
//            if (event.getCategoryType() == regularaccountbook.regularaccountbook.CategoryType){
//                totalNum.add(event.getAmount());
//            }
//        }
//
//        return totalNum;
//    }
}
