package com.pera_software.aidkit.nio.socket;

//#############################################################################

public class NioException extends Exception
{
	//=============================================================================

	public NioException()
	{
	}

	//=============================================================================

	public NioException( String message )
	{
		super( message );
	}

	//=============================================================================

	public NioException( Throwable cause )
	{
		super( cause );
	}

	//=============================================================================

	public NioException( String message, Throwable cause )
	{
		super( message, cause );
	}

	//=============================================================================

	@Override
	public String getMessage()
	{
		String message = super.getMessage();
		if ( message == null )
			message = getClass().getSimpleName();

		return ( message );
	}
}
