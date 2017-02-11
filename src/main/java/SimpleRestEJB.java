import java.util.Date;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Lock(LockType.READ)
@Path("/ejb")
public class SimpleRestEJB {

	@GET
	public String ejb(){
		return "ejb ok @ "+ new Date().toString();
	}
	
}
