package com.bookSearch.service.impl;

import com.bookSearch.dao.BorrowRecordDao;
import com.bookSearch.model.BorrowRecord;
import com.bookSearch.service.IBorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordServiceImpl implements IBorrowRecordService {

    private final BorrowRecordDao borrowRecordDao;

    @Autowired
    public BorrowRecordServiceImpl(BorrowRecordDao borrowRecordDao) {
        this.borrowRecordDao = borrowRecordDao;
    }

    @Override
    public BorrowRecord getBorrowRecordById(Integer recordId) {
        return borrowRecordDao.getBorrowRecordById(recordId);
    }

    @Override
    public List<BorrowRecord> getBorrowRecordByUser_id(Integer User_id) {
        return borrowRecordDao.getBorrowRecordByUser_id(User_id);
    }

    @Override
    public List<BorrowRecord> getBorrowRecordByBook_id(Integer Book_id) {
        return borrowRecordDao.getBorrowRecordByBook_id(Book_id);
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordDao.getAllBorrowRecords();
    }

    @Override
    public int insertBorrowRecord(BorrowRecord borrowRecord) {
        return borrowRecordDao.insertBorrowRecord(borrowRecord);
    }

    @Override
    public int updateBorrowRecord(BorrowRecord borrowRecord) {
        return borrowRecordDao.updateBorrowRecord(borrowRecord);
    }

    @Override
    public void deleteBorrowRecord(Integer recordId) {
        borrowRecordDao.deleteBorrowRecord(recordId);
    }
}
