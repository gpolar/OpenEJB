import org.apache.openejb.jee.jpa.unit.Persistence;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.testing.Module;

public class Modules {

	@Module
	public Persistence persistence() {
		final PersistenceUnit unit = new PersistenceUnit("dbUnit");
		//unit.addClass(Movie.class);
		unit.setProperty("openjpa.RuntimeUnenhancedClasses", "supported");
	    unit.setProperty("openjpa.jdbc", "buildSchema(ForeignKeys=true)");	
		
	    final Persistence persistence = new Persistence(unit);
	    persistence.setVersion("2.0");
	    return persistence;
	}
	
}
