
import java.io.Serializable;

// 1. Patient
class Patient implements Serializable {
    public String id, name, gender;
    public int age;
    public Patient(String id, String name, int age, String gender) {
        this.id = id; this.name = name; this.age = age; this.gender = gender;
    }
    @Override
    public String toString() {
        return id+" | "+name+" | "+gender+" | "+age+" yrs";
    }
}

// 2. Appointment
class Appointment implements Serializable {
    public String patientId, doctorName, date;
    public Appointment(String patientId, String doctorName, String date) {
        this.patientId = patientId; this.doctorName = doctorName; this.date = date;
    }
    @Override
    public String toString() {
        return patientId+" → Dr."+doctorName+" @ "+date;
    }
}

// 3. Electronic Health Record
class HealthRecord implements Serializable {
    public String patientId, diagnosis, prescription;
    public HealthRecord(String patientId, String diagnosis, String prescription) {
        this.patientId = patientId; this.diagnosis = diagnosis; this.prescription = prescription;
    }
    @Override
    public String toString() {
        return patientId+" | Dx: "+diagnosis+" | Rx: "+prescription;
    }
}

// 4. Billing
class Billing implements Serializable {
    public String patientId;
    public double amount;
    public Billing(String patientId, double amount) {
        this.patientId = patientId; this.amount = amount;
    }
    @Override
    public String toString() {
        return patientId+" → ₹"+amount;
    }
}

// 5. Inventory Item
class InventoryItem implements Serializable {
    public String itemName;
    public int quantity;
    public InventoryItem(String itemName, int quantity) {
        this.itemName = itemName; this.quantity = quantity;
    }
    @Override
    public String toString() {
        return itemName+" : "+quantity+" units";
    }
}

// 6. Staff
class Staff implements Serializable {
    public String id, name, role;
    public Staff(String id, String name, String role) {
        this.id = id; this.name = name; this.role = role;
    }
    @Override
    public String toString() {
        return id+" | "+name+" | "+role;
    }
}
