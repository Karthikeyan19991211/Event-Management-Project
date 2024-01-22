package eventmanagement_DTO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClientEvent 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientEventId;
	private String clientEventNoOfPeople;
	private LocalDate startDate;
	private int clientEventNoOfDate;
	private String clientEventLocation;
	private double clientEventCost;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Client clients;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientService> clientServices;
	
	private EventType eventType;

	public int getClientEventId() {
		return clientEventId;
	}

	public void setClientEventId(int clientEventId) {
		this.clientEventId = clientEventId;
	}

	public String getClientEventNoOfPeople() {
		return clientEventNoOfPeople;
	}

	public void setClientEventNoOfPeople(String clientEventNoOfPeople) {
		this.clientEventNoOfPeople = clientEventNoOfPeople;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getClientEventNoOfDate() {
		return clientEventNoOfDate;
	}

	public void setClientEventNoOfDate(int clientEventNoOfDate) {
		this.clientEventNoOfDate = clientEventNoOfDate;
	}

	public String getClientEventLocation() {
		return clientEventLocation;
	}

	public void setClientEventLocation(String clientEventLocation) {
		this.clientEventLocation = clientEventLocation;
	}

	public double getClientEventCost() {
		return clientEventCost;
	}

	public void setClientEventCost(double clientEventCost) {
		this.clientEventCost = clientEventCost;
	}

	public Client getClients() {
		return clients;
	}

	public void setClients(Client clients) {
		this.clients = clients;
	}

	public List<ClientService> getClientServices() {
		return clientServices;
	}

	public void setClientServices(List<ClientService> clientServices) {
		this.clientServices = clientServices;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "ClientEvent [clientEventId=" + clientEventId + ", clientEventNoOfPeople=" + clientEventNoOfPeople
				+ ", startDate=" + startDate + ", clientEventNoOfDate=" + clientEventNoOfDate + ", clientEventLocation="
				+ clientEventLocation + ", clientEventCost=" + clientEventCost + ", clientServices=" + clientServices
				+ ", eventType=" + eventType + "]";
	}	

}
