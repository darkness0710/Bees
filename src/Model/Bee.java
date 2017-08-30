package Model;

import java.util.concurrent.atomic.AtomicInteger;

/* HaiNT47 */

public class Bee {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int id;
	private String name;
	private String type;
	private float health;
	private String status;

	public Bee() {
		super();
		this.id = count.incrementAndGet(); 
	}

	public Bee(int id, String name, String type, float health, String status) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.health = health;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
