package com.hetian.account.domain;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RegularAccountBook extends AccountBook {

    @Override
    public BigDecimal totalIncomeByYearAndMonth(int year, int month){
        List<DetailedRecord> accountRecords = incomeByYearAndMonthRecords(year, month);
        return sumBigDecimal(accountRecords);
    }

    @Override
    public BigDecimal totalSpendingByYearAndMonth(int year, int month){
        List<DetailedRecord> accountRecords = spendingByYearAndMonthRecords(year, month);
        return sumBigDecimal(accountRecords);
    }

    @Override
    public BigDecimal profitByYearAndMonth(int year, int month){
        return totalIncomeByYearAndMonth(year, month).subtract(totalSpendingByYearAndMonth(year, month));
    }



}