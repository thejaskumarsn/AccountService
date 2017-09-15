package com.revoult.transfer.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.revolut.model.Account;
import com.revolut.model.AccountTransfer;
import com.revolut.transfer.AccountService.App;

@Path("/account")
public class AccountService {
	
	

	@POST
    @Path("/addAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addAccount(Account account) {
	 
	 if(App.manager.getAccount(account.getAccountName()) == null)
	 {
		 Account acc = new Account();
		 acc.setAccountName(account.getAccountName());
		 acc.setAccountBalance(account.getAccountBalance());
		 App.manager.addAccount(account);
		 return "Account added sucessfully";
	 }
	 else
        return "Account exists";
    }
 
 @GET
 @Path("/getAllAccounts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAllAccounts() {
        return App.manager.getAllAccounts();
    }
 
 @GET
 @Path("/getAccount")
 	@QueryParam("/accountName")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@QueryParam("accountName")String name) {
        return App.manager.getAccount(name);
    }
 
 @GET
    @Path("/getAmount")
 	@QueryParam("/accountName")
    @Produces(MediaType.TEXT_PLAIN)
    public double getAmount(@QueryParam("accountName")String name) {
	 Account acc = App.manager.getAccount(name);
	 if(acc != null)
        return App.manager.getAccount(name).getAccountBalance();
	 else
		 return 0;
    }
 
 @POST
 @Path("/transferAmount")
 @Consumes(MediaType.APPLICATION_JSON)
 public String transferAmount(AccountTransfer accountTransfer) {
	 
	 String fromAcct = accountTransfer.getFromAccountName();
	 String toAcct = accountTransfer.getToAccountName();
	 double amount = accountTransfer.getAmount();
	 Account fromAcc = App.manager.getAccount(fromAcct);
	 Account toAcc = App.manager.getAccount(toAcct);
	 
	 if(fromAcc == null || toAcc == null) {
		 return "Not valid accounts";
	 } else if(fromAcc.getAccountBalance() < amount) {
		 return "Not enough balance";
	 } else {
		 toAcc.setAccountBalance(toAcc.getAccountBalance()+amount);
		 fromAcc.setAccountBalance(fromAcc.getAccountBalance()-amount);
		return "Amount Transferred"; 
	 }
	 
 }
 
}
