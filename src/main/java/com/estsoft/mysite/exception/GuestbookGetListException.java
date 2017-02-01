package com.estsoft.mysite.exception;

@SuppressWarnings("serial")
public class GuestbookGetListException extends RuntimeException 
{
	public GuestbookGetListException()
	{
		super("Exception occurs: getting guestbook list");
	}
	
	public GuestbookGetListException(String message)
	{
		super(message);
	}
}
