package com.fzt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fzt.entity.AddressBook;
import com.fzt.mapper.AddressBookMapper;
import com.fzt.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
