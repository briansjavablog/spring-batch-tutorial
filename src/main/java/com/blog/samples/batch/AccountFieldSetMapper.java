package com.blog.samples.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.blog.samples.batch.model.Account;

/**
 * Account field mapper takes FieldSet object for each row in input 
 * file and maps it to an Account model object
 * 
 */
public class AccountFieldSetMapper implements FieldSetMapper<Account>
{
	
	/** 
	 * Map provided fieldset to Account POJO using keys defined in the names 
	 * attribute of the DelimitedLineTokenizer object
	 */
	public Account mapFieldSet(FieldSet fieldSet_p) throws BindException
	{
		Account account = new Account();
		account.setId(fieldSet_p.readString("ACCOUNT_ID"));
		account.setAccountHolderName(fieldSet_p.readString("ACCOUNT_HOLDER_NAME"));
		account.setAccountCurrency(fieldSet_p.readString("ACCOUNT_CURRENCY"));
		account.setBalance(fieldSet_p.readBigDecimal("BALANCE"));
	
		return account;
	}
}