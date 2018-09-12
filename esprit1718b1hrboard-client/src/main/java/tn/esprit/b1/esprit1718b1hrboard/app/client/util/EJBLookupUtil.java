package tn.esprit.b1.esprit1718b1hrboard.app.client.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*Class singleton ---> CREATE ONE INITIAL CONTEXTE TO HANDLE THE CONNECTION WITH EJB*/
public class EJBLookupUtil {

	private static Context ctx;

	public static Object doLookup(String jndiName)

	{
		Object proxy = null;

		if (null == ctx)

		{

			try {
				ctx = new InitialContext();
			} catch (NamingException e) {
			}

		}

		try {
			proxy= ctx.lookup(jndiName);
		} catch (NamingException e) {
		}
		return proxy;

	}

}
