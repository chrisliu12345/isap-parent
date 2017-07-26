package com.gosun.isap.dao.mapper.face.customer;

import com.gosun.isap.dao.po.face.Contacts;

public interface ContactsMapperCustomer {
    Contacts selectByRemark(String remark);
}