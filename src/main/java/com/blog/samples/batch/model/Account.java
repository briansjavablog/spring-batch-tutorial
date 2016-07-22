/**
 * 
 */

package com.blog.samples.batch.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Account model object  
 */
public class Account implements Serializable
{

	private static final long serialVersionUID = -3166540015278455392L;
	private String id;
	private String accountHolderName;
	private String accountCurrency;
	private BigDecimal balance;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getAccountHolderName()
	{
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName)
	{
		this.accountHolderName = accountHolderName;
	}

	public String getAccountCurrency()
	{
		return accountCurrency;
	}

	public void setAccountCurrency(String accountCurrency)
	{
		this.accountCurrency = accountCurrency;
	}

	public BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}

	@Override
	public String toString()
	{
		return "Account [id=" + id + ", accountHolderName=" + accountHolderName + ", accountCurrency=" + accountCurrency + ", balance=" + balance + "]";
	}
}
