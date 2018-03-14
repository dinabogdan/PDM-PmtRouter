package org.pdm.ib.pmt.router.converters;

import org.pdm.ib.pmt.router.entities.Account;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AccountConverter implements AttributeConverter<Account, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Account value) {
        if(value == null){
            return null;
        }else{
            return value.getAccountNumber();
        }
    }

    @Override
    public Account convertToEntityAttribute(Integer value) {
        if(value == null){
            return null;
        }else{
            return null; /*TODO: acctRepo.findAcctById(Integer id)*/
        }
    }
}
