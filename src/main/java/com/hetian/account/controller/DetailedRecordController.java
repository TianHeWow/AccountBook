package com.hetian.account.controller;

import com.hetian.account.application.DetailedRecordApplicationService;
import com.hetian.account.application.acccountInfo.AccountDetailInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/detailedRecord")
public class DetailedRecordController {
    @Autowired
    private DetailedRecordApplicationService detailedRecordApplicationService;

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @ApiOperation(value = "返回账本所有记录")
    @ResponseBody
    public List<AccountDetailInfo> queryAll() {

        return detailedRecordApplicationService.queryAllRecords();

    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    @ApiOperation(value = "插入一条账本记录", notes = "name, categoryType, content, note, occurredtime, amount")
    public ResponseEntity addRecord(@RequestBody AccountDetailInfo record) {
        detailedRecordApplicationService.addRecord(record);
        return new ResponseEntity<>("adding Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/income/year/{year}/month/{month}", method = RequestMethod.GET)
    @ApiOperation(value = "通过年份和月份查询收入", notes = "必须输入年份和月份，类型为int")
    public ResponseEntity incomeByYearAndMonth(
            @ApiParam(name = "year", required = true) @PathVariable int year,
            @ApiParam(name = "month", required = true) @PathVariable int month){
        BigDecimal income = BigDecimal.ZERO;
        income =  detailedRecordApplicationService.incomingByYearAndMonth(year, month);
        return new ResponseEntity(income, HttpStatus.OK);

    }

    @RequestMapping(value = "/spending/year/{year}/month/{month}", method = RequestMethod.GET)
    @ApiOperation(value = "通过年份和月份查询支出", notes = "必须输入年份和月份，类型为int")
    public ResponseEntity spendingByYearAndMonth(
            @ApiParam(name = "year", required = true) @PathVariable int year,
            @ApiParam(name = "month", required = true) @PathVariable int month){
        BigDecimal spending = BigDecimal.ZERO;
        spending =  detailedRecordApplicationService.spendingByYearAndMonth(year, month);
        return new ResponseEntity(spending, HttpStatus.OK);

    }

    @RequestMapping(value = "/profit/year/{year}/month/{month}", method = RequestMethod.GET)
    @ApiOperation(value = "通过年份和月份查询收益", notes = "必须输入年份和月份，类型为int")
    public ResponseEntity profitByYearAndMonth(
            @ApiParam(name = "year", required = true) @PathVariable int year,
            @ApiParam(name = "month", required = true) @PathVariable int month){
        BigDecimal profit = BigDecimal.ZERO;
        profit =  detailedRecordApplicationService.profitByYearAndMonth(year, month);
        return new ResponseEntity(profit, HttpStatus.OK);

    }


}
