package com.hetian.account.application;

import com.hetian.account.application.acccountInfo.AccountDetailInfo;
import com.hetian.account.domain.CategoryType;
import com.hetian.account.domain.DetailedAccountService;
import com.hetian.account.domain.RegularAccountBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailedRecordApplicationService {
    @Autowired
    private DetailedAccountService detailedAccountService;


    public List<AccountDetailInfo> queryAllRecords() {
        return detailedAccountService.queryAllRecords().stream()
                .map(obj -> {
                        AccountDetailInfo accountDetailInfo = new AccountDetailInfo();
                        accountDetailInfo.from(obj);
                        return accountDetailInfo;
                        })
                .collect(Collectors.toList());
    }

    public void addRecord(AccountDetailInfo record) {
        detailedAccountService.addRecord(record.to());
    }


    @Autowired
    private RegularAccountBook regularAccountBook;

    public BigDecimal incomingByYearAndMonth(int year, int month)  {

        return detailedAccountService.queryAllRecords().stream()
                .map(obj -> {
                    AccountDetailInfo accountDetailInfo = new AccountDetailInfo();
                    accountDetailInfo.from(obj);
                    return accountDetailInfo;
                })
                .filter(accord -> accord.getCategoryType() == CategoryType.Income)
                .filter(accord -> accord.getOccurredTime().getYear() == year)
                .filter(accord -> accord.getOccurredTime().getMonthValue() == month)
                .map(accord -> accord.getAmount())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }

    public BigDecimal spendingByYearAndMonth(int year, int month) {
        return detailedAccountService.queryAllRecords().stream()
                .map(obj -> {
                    AccountDetailInfo accountDetailInfo = new AccountDetailInfo();
                    accountDetailInfo.from(obj);
                    return accountDetailInfo;
                })
                .filter(accord -> accord.getCategoryType() == CategoryType.Spending)
                .filter(accord -> accord.getOccurredTime().getYear() == year)
                .filter(accord -> accord.getOccurredTime().getMonthValue() == month)
                .map(accord -> accord.getAmount())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

    }

    public BigDecimal profitByYearAndMonth(int year, int month){
        return incomingByYearAndMonth(year, month).subtract(spendingByYearAndMonth(year, month));
    }

}
