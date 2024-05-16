package com.bookSearch.service;

import com.bookSearch.model.BorrowRecord;

import java.util.List;

public interface IBorrowRecordService {

    // 根据 recordId 查询借阅记录
    BorrowRecord getBorrowRecordById(Integer recordId);
    List<BorrowRecord> getBorrowRecordByUser_id(Integer User_id);
    List<BorrowRecord> getBorrowRecordByBook_id(Integer Book_id);

    // 查询所有借阅记录
    List<BorrowRecord> getAllBorrowRecords();

    // 插入借阅记录
    int insertBorrowRecord(BorrowRecord borrowRecord);

    // 更新借阅记录
    int updateBorrowRecord(BorrowRecord borrowRecord);

    // 删除借阅记录 by recordId
    void deleteBorrowRecord(Integer recordId);
}
