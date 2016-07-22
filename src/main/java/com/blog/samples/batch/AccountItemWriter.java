package com.blog.samples.batch;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import com.blog.samples.batch.model.Account;

/**
 * Class takes Account model objects created in item reader and makes 
 * them available to writer to persist in the database
 * 
 */
public class AccountItemWriter implements ItemWriter<Account>
{

	private static final String INSERT_ACCOUNT = "insert into account (id,accountHolderName,accountCurrency,balance) values(?,?,?,?)";
	private static final String UPDATE_ACCOUNT = "update account set accountHolderName=?, accountCurrency=?, balance=? where id = ?";
	private JdbcTemplate jdbcTemplate_i;

	/**
	 * Method takes a list of Account model objects and uses JDBC template to either insert or
	 * update them in the database
	 */
	public void write(List<? extends Account> accounts_p) throws Exception
	{
		for (Account account : accounts_p)
		{
			int updated = jdbcTemplate_i.update(UPDATE_ACCOUNT, account.getAccountHolderName(), account.getAccountCurrency(), 
			                                  account.getBalance(), account.getId());
			if (updated == 0)
			{
				jdbcTemplate_i.update(INSERT_ACCOUNT, account.getId(), account.getAccountHolderName(), 
				                    account.getAccountCurrency(), account.getBalance());
			}
		}
	}

	public AccountItemWriter(DataSource dataSource_p)
	{
		this.jdbcTemplate_i = new JdbcTemplate(dataSource_p);
	}
}
