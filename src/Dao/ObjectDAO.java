package Dao;

import java.util.List;
import Model.Bee;

/* HaiNT47 */

public interface ObjectDAO {
	public List<Bee> randomBee();
	public List<Bee> damgeBee(int damage);
	public boolean saveBee();
	public List<Bee> loadBee();
}
