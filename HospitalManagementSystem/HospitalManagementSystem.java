
import java.util.*;
import java.io.File;

public class HospitalManagementSystem {
    private static final Scanner sc = new Scanner(System.in);
    // load from data/*.dat
    private static List<Patient>       patients     = FileHandler.loadList("data/patients.dat");
    private static List<Appointment>   appointments = FileHandler.loadList("data/appointments.dat");
    private static List<HealthRecord>  records      = FileHandler.loadList("data/records.dat");
    private static List<Billing>       billings     = FileHandler.loadList("data/billing.dat");
    private static List<InventoryItem> inventory    = FileHandler.loadList("data/inventory.dat");
    private static List<Staff>         staffList    = FileHandler.loadList("data/staff.dat");

    public static void main(String[] args) {
        ensureDataFolder();
        while (true) {
            System.out.println("\n=== Hospital Management ===");
            System.out.println("1.Register Patient   2.Schedule Appointment");
            System.out.println("3.Add Health Record  4.Generate Bill");
            System.out.println("5.Inventory Mgmt     6.Staff Mgmt");
            System.out.println("7.Show All Data      8.Save & Exit");
            System.out.print("Choose> ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: registerPatient(); break;
                case 2: scheduleAppointment(); break;
                case 3: addHealthRecord(); break;
                case 4: generateBill(); break;
                case 5: manageInventory(); break;
                case 6: manageStaff(); break;
                case 7: showAllData(); break;
                case 8: saveAll(); System.out.println("Saved & Exiting."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void ensureDataFolder() {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdir();
    }

    // 1. Patient
    private static void registerPatient() {
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
        System.out.print("Gender: "); String gender = sc.nextLine();
        patients.add(new Patient(id, name, age, gender));
        System.out.println("➤ Patient Registered.");
    }

    // 2. Appointment
    private static void scheduleAppointment() {
        System.out.print("Patient ID: "); String pid = sc.nextLine();
        System.out.print("Doctor Name: "); String doc = sc.nextLine();
        System.out.print("Date (YYYY-MM-DD): "); String date = sc.nextLine();
        appointments.add(new Appointment(pid, doc, date));
        System.out.println("➤ Appointment Scheduled.");
    }

    // 3. EHR
    private static void addHealthRecord() {
        System.out.print("Patient ID: "); String pid = sc.nextLine();
        System.out.print("Diagnosis: "); String dx = sc.nextLine();
        System.out.print("Prescription: "); String rx = sc.nextLine();
        records.add(new HealthRecord(pid, dx, rx));
        System.out.println("➤ Health Record Added.");
    }

    // 4. Billing
    private static void generateBill() {
        System.out.print("Patient ID: "); String pid = sc.nextLine();
        System.out.print("Amount: ₹"); double amt = Double.parseDouble(sc.nextLine());
        billings.add(new Billing(pid, amt));
        System.out.println("➤ Bill Generated.");
    }

    // 5. Inventory
    private static void manageInventory() {
        System.out.println(" a) Add Item   b) View Inventory");
        String opt = sc.nextLine();
        if (opt.equalsIgnoreCase("a")) {
            System.out.print("Item Name: "); String item = sc.nextLine();
            System.out.print("Quantity: "); int qty = Integer.parseInt(sc.nextLine());
            inventory.add(new InventoryItem(item, qty));
            System.out.println("➤ Item Added.");
        } else {
            System.out.println("--- Current Inventory ---");
            inventory.forEach(i -> System.out.println(i));
        }
    }

    // 6. Staff
    private static void manageStaff() {
        System.out.println(" a) Add Staff   b) View Staff");
        String opt = sc.nextLine();
        if (opt.equalsIgnoreCase("a")) {
            System.out.print("ID: "); String id = sc.nextLine();
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Role: "); String role = sc.nextLine();
            staffList.add(new Staff(id, name, role));
            System.out.println("➤ Staff Added.");
        } else {
            System.out.println("--- Staff List ---");
            staffList.forEach(s -> System.out.println(s));
        }
    }

    // 7. Show everything
    private static void showAllData() {
        System.out.println("\n-- Patients --");      patients.forEach(System.out::println);
        System.out.println("\n-- Appointments --");  appointments.forEach(System.out::println);
        System.out.println("\n-- Health Records --");records.forEach(System.out::println);
        System.out.println("\n-- Billings --");      billings.forEach(System.out::println);
        System.out.println("\n-- Inventory --");     inventory.forEach(System.out::println);
        System.out.println("\n-- Staff --");         staffList.forEach(System.out::println);
    }

    // Save all lists back to .dat
    private static void saveAll() {
        FileHandler.saveData(patients,     "data/patients.dat");
        FileHandler.saveData(appointments, "data/appointments.dat");
        FileHandler.saveData(records,      "data/records.dat");
        FileHandler.saveData(billings,     "data/billing.dat");
        FileHandler.saveData(inventory,    "data/inventory.dat");
        FileHandler.saveData(staffList,    "data/staff.dat");
    }
}
