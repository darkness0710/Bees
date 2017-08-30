package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Model.Bee;
import Model.ConnectDB;



/* HaiNT47 */

public class BeeDAO implements ObjectDAO {
	private static final List<Bee> result = new ArrayList<Bee>();
	
	
	@Override
	public List<Bee> randomBee() {
		result.clear();
		for(int i=0; i<10; i++) {
			result.add(random());
		}
		return result;
	}
	
	@Override
	public List<Bee> damgeBee(int damage) {
		for(Bee bee:result) {
			dameBee(bee, damage);
		}
		return result;
	}
	
	@Override
	public boolean saveBee() {
		
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
		 	conn = ConnectDB.Connect();
		 	preStmt = conn.prepareStatement("DELETE FROM Bees");
		 	preStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Bee bee:result) {
			addBee(bee);
		}
		
		return true;
	}

	@Override
	public List<Bee> loadBee() {
		List<Bee> load = new ArrayList<Bee>();
		
		Connection conn = null;
		PreparedStatement preStmt = null;
		
		try {
			conn = ConnectDB.Connect();
			preStmt = conn.prepareStatement("SELECT * FROM Bees");
			ResultSet rs = preStmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String status = rs.getString("status");
				String type = rs.getString("type");
				float health = rs.getFloat("health");
				
				load.add(new Bee(id, name, type, health, status));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return load;
	}
	
	public boolean addBee(Bee bee) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		
		try {
		    conn = ConnectDB.Connect();

		    preStmt = conn.prepareStatement("INSERT INTO Bees(id, name, type, health, status) VALUES (?, ?, ?, ?, ?)");
		    preStmt.setInt(1, bee.getId());
		    preStmt.setString(2, bee.getName());
		    preStmt.setString(3, bee.getType());
		    preStmt.setFloat(4, bee.getHealth());
		    preStmt.setString(5, bee.getStatus());
		    preStmt.execute();
            
		    return true;
		} catch (Exception e) {
	           e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	public Bee random() {
		Bee bee = new Bee();
		bee.setName(randomName());
		bee.setType(randomType());
		bee.setHealth(100);
		bee.setStatus("Alive");
		return bee;
	}
	
	public String randomType() {
		String[] types = {"Worker", "Queen", "Drone"};
		int idx = new Random().nextInt(types.length);
		return types[idx];
	}
	
	public String randomName() {
		String[] names = {"Fsoft1", "Fsoft2", "Fsoft3", "Fsoft4", "Fsoft5"};
		int idx = new Random().nextInt(names.length);
		return names[idx];
	}
	
	public Bee dameBee(Bee bee, int damage) {
		bee.setHealth(bee.getHealth() - (damage*bee.getHealth()/100)); 
		String type = bee.getType();
		
		switch(type) {
			case "Worker":
				if(bee.getHealth() < 70) {
					bee.setStatus("Dead");
				}
				break;
			case "Queen":
				if(bee.getHealth() < 20) {
					bee.setStatus("Dead");
				}
				break;
			case "Drone":
				if(bee.getHealth() < 50) {
					bee.setStatus("Dead");
				}
				break;
		}
		
		/* What the errors? Bug ? What java */
		
		return bee;
	}
	
}
