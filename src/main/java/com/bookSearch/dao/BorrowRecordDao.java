package com.bookSearch.dao;

import com.bookSearch.model.BorrowRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordDao {

    // 根据 recordId 查询借阅记录
    BorrowRecord getBorrowRecordById(Integer record_id);
    List<BorrowRecord> getBorrowRecordByUser_id(Integer record_id);
    List<BorrowRecord> getBorrowRecordByBook_id(Integer record_id);

    // 查询所有借阅记录
    List<BorrowRecord> getAllBorrowRecords();

    // 插入借阅记录
    int insertBorrowRecord(BorrowRecord borrowRecord);

    // 更新借阅记录
    int updateBorrowRecord(BorrowRecord borrowRecord);

    // 删除借阅记录 by recordId
    void deleteBorrowRecord(Integer record_id);
}
