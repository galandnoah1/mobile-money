package com.galandnoah.mobile_money.transaction.mapper;

import com.galandnoah.mobile_money.transaction.dto.CreateTransaction;
import com.galandnoah.mobile_money.transaction.dto.TransactionResponse;
import com.galandnoah.mobile_money.transaction.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionResponse toDTO(Transaction transaction);

    Transaction toEntity(CreateTransaction createTransaction);
}
