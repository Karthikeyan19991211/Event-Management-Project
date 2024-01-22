package eventmanagement_Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import eventmanagement_DAO.AdminDAO;
import eventmanagement_DAO.ClientDAO;
import eventmanagement_DAO.ClientEventDAO;
import eventmanagement_DAO.ClientServiceDAO;
import eventmanagement_DAO.ServiceDAO;
import eventmanagement_DTO.Admin;
import eventmanagement_DTO.Client;
import eventmanagement_DTO.ClientEvent;
import eventmanagement_DTO.ClientService;
import eventmanagement_DTO.EventType;
import eventmanagement_DTO.Service;

public class Driver {
	AdminDAO dao = new AdminDAO();
	ServiceDAO sdao = new ServiceDAO();
	ClientDAO cdao = new ClientDAO();
	ClientEventDAO cedao = new ClientEventDAO();
	ClientServiceDAO csdao = new ClientServiceDAO();

	public static void main(String[] args) {

		Driver d = new Driver();

		Scanner scr = new Scanner(System.in);

		System.out.println("1. Save The New Admin Data");
		System.out.println("2. Find the Admin Data");
		System.out.println("3. Save The New Servive Data");
		System.out.println("4. Update The Service Data");
		System.out.println("5. Delete the Service Data");
		System.out.println("6. Save The New Client Data");
		System.out.println("7. Find the Client Data");
		System.out.println("8. Save The New Client Event Data");
		System.out.println("9. Save The New Client Service Data");
		System.out.println("10. Delete the Client Service Data");
		System.out.println();

		System.out.println("Choose the above Operation, Which operation you want to Perform ?");
		int num = scr.nextInt();

		switch (num) {

		// Save new Admin
		case 1:
			System.out.println(d.saveAdmin());
			break;

		// find Admin
		case 2:
			System.out.println(d.findAdmin());
			break;

		// Save new Service
		case 3:
			System.out.println(d.saveService());
			break;

		// Update the Service
		case 4:
			System.out.println(d.updateService());
			break;

		// Delete the Service
		case 5:
			System.out.println(d.deleteService());
			break;

		// Save new Client
		case 6:
			System.out.println(d.seveClient());
			break;

		// find client
		case 7:
			System.out.println(d.loginClient());
			break;

		// save client event
		case 8:
			System.out.println(d.saveClientEvent());
			break;

		// save client service
		case 9:
			System.out.println(d.insertClientService());
			break;

		// delete client service
		case 10:
			System.out.println(d.deleteClientService());
			break;

		default:
			System.out.println("Please Enter the Correct Operation");
			break;

		}

	}

	public Admin saveAdmin() {
		Admin admin = new Admin();
		Scanner scr = new Scanner(System.in);

		System.out.println("Enter the Admin Name");
		admin.setAdminName(scr.next());

		System.out.println("Enter the Admin Contact");
		admin.setAdminContact(scr.nextLong());

		System.out.println("Enter the Admin Email");
		admin.setAdminEmail(scr.next());

		System.out.println("Enter the Admin Password");
		admin.setAdminPassword(scr.next());

		return dao.insertAdmin(admin);

	}

	public Admin findAdmin() {
		Scanner scr = new Scanner(System.in);

		System.out.println("Enter the Admin Email");
		String email = scr.next();

		System.out.println("Enter the Admin Password");
		String password = scr.next();

		return dao.findAdmin(email, password);

	}

	public Service saveService() {
		Admin a = findAdmin();

		Service ser = new Service();

		if (a != null) {
			Scanner scr = new Scanner(System.in);

			System.out.println();
			System.out.println("Enter the Service Name");
			ser.setServiceName(scr.next());

			System.out.println("Enter the Service Cost Per Day");
			ser.setServiceCostPerDay(scr.nextDouble());

			System.out.println("Enter the Service Cost Per Person");
			ser.setServiceCostPerPerson(scr.nextDouble());

			Service service = sdao.insertService(ser);
			a.getServices().add(service);
			dao.updateAdmin(a, a.getAdminId());

			return ser;
		}
		return null;

	}

	public String updateService() {
		Admin a = findAdmin();

		Scanner scr = new Scanner(System.in);

		List<Service> service = sdao.getAllService();

		System.out.println(
				"(Service-Id)   " + "(Service-Name)   " + "(Service-Cost-Per-Day)   " + "(Service-Cost-Per-Person)");

		if (service != null) {
			for (Service s : service) {
				System.out.println(s.getServiceId() + "              " + s.getServiceName() + "           "
						+ s.getServiceCostPerDay() + "                " + s.getServiceCostPerPerson());
			}
		}

		System.out.println();
		System.err.println();
		System.out.println("Choose the above service id which you need to update");

		int id = scr.nextInt();
		Service exservice = sdao.findService(id);
		if (exservice != null) {
			System.out.println("Enter the new " + exservice.getServiceName() + " Service Cost Per Day ");
			exservice.setServiceCostPerDay(scr.nextDouble());

			System.out.println("Enter the new " + exservice.getServiceName() + " Service Cost Per Person");
			exservice.setServiceCostPerPerson(scr.nextDouble());

			Service newservice = sdao.updateService(exservice, id);
			if (newservice != null) {
				return "Updated Successful...!";
			}

			System.out.println(service);
		}

		return "Something is wrong,please check and give the correct input...!";

	}

	public String deleteService() {
		Admin a = findAdmin();

		Scanner scr = new Scanner(System.in);

		List<Service> service = sdao.getAllService();

		System.out.println(
				"(Service-Id)   " + "(Service-Name)   " + "(Service-Cost-Per-Day)   " + "(Service-Cost-Per-Person)");

		if (service != null) {
			for (Service s : service) {
				System.out.println(s.getServiceId() + "              " + s.getServiceName() + "           "
						+ s.getServiceCostPerDay() + "                " + s.getServiceCostPerPerson() + "/n" + "/n");
			}
		}

		System.out.println("Choose the above service id which you need to delete");

		int id = scr.nextInt();

		sdao.deleteService(id, a.getAdminId());

		return "Sucessfully Deleted";
	}

	public Client seveClient() {
		Scanner scr = new Scanner(System.in);

		Client client = new Client();

		System.out.println("Enter the Client Name");
		client.setClientName(scr.next());

		System.out.println("Enter the Client Email");
		client.setClientEmail(scr.next());

		System.out.println("Enter the Client Contact");
		client.setClientContact(scr.nextLong());

		return cdao.insertClient(client);
	}

	public Client loginClient() {
		Scanner scr = new Scanner(System.in);

		System.out.println("Enter the Client Email Id");
		String mail = scr.next();

		System.out.println("Enter the Client Password");
		String password = scr.next();

		return cdao.loginClient(mail, password);
	}

	public ClientEvent saveClientEvent() {
		Client client = loginClient();

		Scanner scr = new Scanner(System.in);

		ClientEvent event = new ClientEvent();

		if (client != null) {

			List<EventType> list = Arrays.asList(EventType.values());
			int count = 0;
			for (EventType e : list) {
				System.out.println(count + ". " + e);
				count++;
			}
			System.out.println();
			System.out.println("Choose your Event Type");
			int userEvent = scr.nextInt();

			switch (userEvent) {
			case 0:
				event.setEventType(EventType.Marriage);
				break;
			case 1:
				event.setEventType(EventType.Engagement);
				break;
			case 2:
				event.setEventType(EventType.BirthDay);
				break;
			case 3:
				event.setEventType(EventType.Anniversary);
				break;
			case 4:
				event.setEventType(EventType.BabyShower);
				break;
			case 5:
				event.setEventType(EventType.Reunion);
				break;
			case 6:
				event.setEventType(EventType.NamingCeremony);
				break;
			case 7:
				event.setEventType(EventType.BachelorParty);
				break;
			default:
				System.out.println("Enter the Valid Input");
				;
				break;
			}

			System.out.println("Enter the total number of People");
			event.setClientEventNoOfPeople(scr.next());

			System.out.println("Enter the Event Date as per the given format (DAY,MM,YYY)");

			System.out.println("Enter the day");
			int day = scr.nextInt();
			System.out.println("Enter the Month");
			int month = scr.nextInt();
			System.out.println("Enter the year");
			int year = scr.nextInt();

			event.setStartDate(LocalDate.of(year, month, day));

			System.out.println("Enter the total Client Event number of Days");
			event.setClientEventNoOfDate(scr.nextInt());

			System.out.println("Enter the Event Location");
			event.setClientEventLocation(scr.next());

			System.out.println("Enter the Total cost of Event");
			event.setClientEventCost(scr.nextDouble());

			ClientEvent addevent = cedao.insertClientEvent(event);
			// client.getEvents().add(addevent);

			return cedao.updateClientEvent(addevent.getClientEventId(), client.getClientId());
		}
		return null;
	}

	public List<ClientService> insertClientService() {
		loginClient();

		Scanner scr = new Scanner(System.in);
		ClientService clientService = new ClientService();

		List<ClientEvent> clientEvent = cedao.getAllClientEvent();
		for (ClientEvent e : clientEvent) {
			System.out.println(e.getClientEventId() + ". " + e.getEventType());
		}
		System.out.println();
		System.out.println("Choose above the given number which event you want to do Client Service");

		ClientEvent event = cedao.findClientEvent(scr.nextInt());

		Admin a = findAdmin();

		if (a != null) {
			List<Service> list = sdao.getAllService();
			if (event != null) {
				for (Service s : list) {
					System.out.println(s.getServiceId() + ". " + s.getServiceName());

				}
				System.out.println();
			}

			System.out.println(" Which service you want for your Event? (as per the count)");
			int num = scr.nextInt();

			while (num > 0) {
				System.out.println("Choose above the given number");
				Service service = sdao.findService(scr.nextInt());

				if (service != null) {

					// System.out.println("Enter the Service Name");
					clientService.setClientServiceName(service.getServiceName());

					// System.out.println("Enter the Service Cost");
					clientService.setClientServiceCost(service.getServiceCostPerDay());

					System.out.println("Total how many days do you want Client Service");
					clientService.setClientServiceNoOfDay(scr.nextInt());

					// System.out.println("Enter the Cost Service per Person");
					clientService.setClientServiceCostPerPerson(service.getServiceCostPerPerson());

					ClientService newService = csdao.insertClientService(clientService);

					List<ClientService> newClientService = new ArrayList<ClientService>();
					newClientService.add(newService);

					cedao.updateClientService(event.getClientEventId(), newClientService);

				}
				num--;
			}
			return cedao.findClientEvent(event.getClientEventId()).getClientServices();
		}

		return null;

	}

	public String deleteClientService() {
		Client client = loginClient();
		List<ClientEvent> event = client.getEvents();

		Scanner scr = new Scanner(System.in);

		if (event != null) {
			for (ClientEvent e : event) {
				System.out.println(e.getClientEventId() + ". " + e.getEventType());
			}
			System.out.println();
			System.out.println("Select your Event above the given number");

			ClientEvent cevent = cedao.findClientEvent(scr.nextInt());
			List<ClientService> cservice = cevent.getClientServices();

			if (cevent != null) {

				for (ClientService s : cservice) {
					System.out.println(s.getClientServiceId() + ". " + s.getClientServiceName());
				}

				System.out.println();
				System.out.println("Which Client Service you have to Cancel above the given number ?");
				int id = scr.nextInt();
				csdao.deleteClientService(id, cevent.getClientEventId());
				return "Client Service Deleted Successfully...!";
			}
		}
		return "Something is Wrong...!";
	}

}
