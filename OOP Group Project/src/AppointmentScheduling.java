import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Scanner;
import Visit_Referral_Management.Date;

class Appointment {
    Scanner scanner = new Scanner(System.in);
    private static final int MAX_APPOINTMENTS = 3;
    private static final int recordSize = 58; 
    private static final int TotalRecords = 6; // assuming 2 doctors with 3 slots each
    private static final int dateSize = 10; // assuming format is dd/mm/yyyy
    private static final int statusSize = 9; // assuming max status length is 18
    private int appointmentNumber;
    private int patientNumber;
    private int doctorNumber;
    private Date appointmentDate;
    private String status;
    private static int slotNumber = 0;// to give each appointment a unique number

    public Appointment() {
        this.appointmentNumber = 0; // 4 bytes
        this.patientNumber = 0;// 4 bytes
        this.doctorNumber = 0; // 4 bytes
        this.appointmentDate = new Date(1,0, 0); // assuming format is yyyy-MM-dd 10 char bytes
        this.status = String.format("%-9s", " ").substring(0, 9);// 18 bytes
                                                                  // start time int 4 bytes
                                                                  // end time int 4 bytes
    }

    public Appointment(int patientNumber, int doctorNumber, int day, int month, int year, String status) {
        this.patientNumber = patientNumber;
        this.doctorNumber = doctorNumber;
        this.appointmentDate = new Date(year, month, day);
        this.status = String.format("%-9s", status).substring(0, 9);
    }

    public static int getMaxAppointments() {
        return MAX_APPOINTMENTS;
    }

    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(int patientNumber) {
        this.patientNumber = patientNumber;
    }

    public int getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(int doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static int getSlotNumber() {
        return slotNumber;
    }

    public static void setSlotNumber(int slotNumber) {
        Appointment.slotNumber = slotNumber;
    }

    public void displayAppointment() {
        RandomAccessFile pen = null;
        try {
            pen = new RandomAccessFile("appointment_times.txt", "rw");
            pen.seek(6 + (long) (getPatientNumber() - 1) * recordSize);
            int startTime = pen.readInt();
            int endTime = pen.readInt();
            if (startTime == 0 && endTime == 0) {
                System.out.println("No appointments available");
            } else {
                System.out.println("Appointment Number: " + getAppointmentNumber());
                System.out.println("Patient Number: " + getPatientNumber());
                System.out.println("Doctor Number: " + getDoctorNumber());
                System.out.println("Date: " + getAppointmentDate());
                System.out.println("Status: " + getStatus());
                System.out.println("Time: " + startTime + "-" + endTime);
                System.out.println("-------------------------");
            }
            pen.close();
        } catch (Exception e) {
            System.out.println("An Exception occured");
        }
    }

    public void ManageAppointments() {
        char option;
        viewAppointments();
        System.out.println("1)To update appointment status");
        System.out.println("2)To cancel appointment");
        System.out.println("3)To Schedule appointment");
        int choice = scanner.nextInt();
        do {
            switch (choice) {
                case 1:
                    option = 'U'; // U for update
                    modifyAppointment(option);
                    break;
                case 2:
                    option = 'C'; // C for cancel
                    modifyAppointment(option);
                    break;
                case 3:
                    setAppointment();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice < 1 || choice > 3);
        System.out.println("Exiting appointment management.");
    }

    public void setAppointment() {
        int startTime=0, endTime=0;
        Scanner scan = new Scanner(System.in);
        viewAppointments();
        RandomAccessFile pen = null;
        int apptNumber=0;
        int slot=0;

        try {
            pen = new RandomAccessFile("appointment_times.txt", "rw");

            do {
                System.out.println("Enter appointment number: ");
                slot = scan.nextInt();
                pen.seek((long) (slot - 1) * recordSize);
                apptNumber = pen.readInt();
                pen.seek(5 + (long) (slot - 1) * recordSize);
                char[] stat = new char[statusSize];
                for (int i = 0; i < statusSize; i++) {
                    stat[i] = pen.readChar();
                }
                String statusFromFile = new String(stat);
                if (slot == apptNumber && statusFromFile.trim().equals(" ")) {
                    System.out.println("Appointment Scheduled Successfully");
                    pen.seek((long) (slot - 1) * recordSize);
                    setAppointmentNumber(pen.readInt());
                    pen.writeInt(getPatientNumber());
                    pen.seek(4 + (long) (slot - 1) * recordSize);
                    char[] dateChars = new char[dateSize]; // "DD-MM-YYYY" is 10 characters long
                    for (int i = 0; i < dateSize; i++) {
                        dateChars[i] = pen.readChar();
                    } 
                    String[] dateParts = new String(dateChars).split("-");
                    int day = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int year = Integer.parseInt(dateParts[2]);
                    Date appointmentDate= new Date(day, month, year);
                    setAppointmentDate(appointmentDate);
                    String stat1="Scheduled";

                    pen.writeChars(stat1);
                    setStatus("Scheduled");
                    startTime = pen.readInt();
                    endTime = pen.readInt();
                    Appointment appointment = new Appointment();
                    appointment.displayAppointment();

                } else {
                    System.out.println("Not a valid appointment number");
                }

            } while (apptNumber != slot);
            pen = new RandomAccessFile("setAppointments.txt", "rw");
            pen.seek((getPatientNumber() - 1) * 68L);
            pen.writeInt(getAppointmentNumber()); // 4 bytes
            pen.writeInt(getPatientNumber());// 4 bytes
            pen.writeInt(getDoctorNumber());// 4 bytes
            pen.writeChars(getAppointmentDate().toString());// 10 bytes
            pen.writeChars(getStatus());// 18 bytes
            pen.writeInt(startTime);// 4 bytes
            pen.writeInt(endTime);// 4 bytes
            pen.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void modifyAppointment(char choice) {
        viewAppointments();
        RandomAccessFile pen = null;
        boolean found = false;
        int apptNumber = 0;
        String newStatus =" ";
        try {
            pen = new RandomAccessFile("appointment_times.txt", "rw");
            do {
                if (choice == 'U') {
                    System.out.println("Enter appointment number to update status: ");
                    apptNumber = scanner.nextInt();
                    System.out.println("Enter new status: ");
                    newStatus = String.format("%-9s", scanner.next()).substring(0, 9);
                } else if (choice == 'C') {
                    System.out.println("Enter appointment number to cancel: ");
                    apptNumber = scanner.nextInt();
                    setStatus("Cancelled");
                }
                pen.seek((long) (apptNumber - 1) * recordSize);
                if (pen.readInt() == apptNumber) {
                    found = true;
                } else {
                    System.out.println("No appointment found");
                }
            } while (found == false);//can be simplified

            if (choice == 'U') {
                System.out.println("updating appointment status");
                pen.seek(5 + (long) (getAppointmentNumber() - 1) * recordSize);
                setStatus(String.format("%-9s", newStatus));
                pen.writeChars(newStatus);
            }
            if (choice == 'C') {
                pen.seek(5 + (long) (getAppointmentNumber() - 1) * recordSize);
                System.out.println("cancelling appointment");
                setStatus("Cancelled");
                pen.writeChars(" ");

            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void doctorDashboard() {
        while (true) { // needs to throw exception
            System.out.println("\nDoctor Dashboard");
            System.out.println("1. Generate Appointment Times");
            System.out.println("2. Manage Appointments");
            System.out.println("3. Return to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    generateAppointmentTimes();
                    break;
                case 2:
                    ManageAppointments();
                    break;
                case 3:
                    start();
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void patientDashboard() {
        Scanner reader = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nPatient Dashboard");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Return to Main Menu");
            System.out.print("Choose an option: ");
             choice = reader.nextInt();

            switch (choice) {
                case 1:
                    setAppointment();
                    break;
                case 2:
                    displayAppointment();
                    break;
                case 3:
                    char cancel = 'C';
                    modifyAppointment(cancel);
                    break;
                case 4:
                    start();
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice < 1 || choice > 4);
    }

    public void generateAppointmentTimes()  {
        Appointment appointment = new Appointment();
        RandomAccessFile pen = null;
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("\n--- Generate Appointment Times ---");

        int startHour, endHour;
        do {
            System.out.print("Enter start time (HH 24-hour format): ");
            startHour = scanner.nextInt();
            System.out.print("Enter end time (HH 24-hour format): ");
            endHour = scanner.nextInt();
            if (startHour >= endHour) {
                System.out.println("Start time must be less than end time. Please try again.");
            }
        } while (startHour >= endHour);
        System.out.print("Enter day (DD):");
        int day = scanner.nextInt();
        System.out.print("Enter month (MM):");
        int month = scanner.nextInt();
        System.out.print("Enter year (YYYY):");
        int year = scanner.nextInt();
        
        Date date = new Date(year, month, day);

        try {
            pen = new RandomAccessFile("appointment_times.txt", "rw");
            // array of time slots
            LocalTime[][] timeSlots = new LocalTime[MAX_APPOINTMENTS][2];
            int slotIndex = 0;
            for (LocalTime time = LocalTime.of(startHour, 0); time.plusMinutes(30).isBefore(LocalTime.of(endHour, 0)) &&
                    slotIndex < MAX_APPOINTMENTS; time = time.plusMinutes(30)) {
                LocalTime endTimeSlot = time.plusMinutes(30);
                System.out.println("Time Slot: " + time + " - " + endTimeSlot);
                timeSlots[slotIndex][0] = time;
                timeSlots[slotIndex][1] = endTimeSlot;
                slotIndex++;
            }
            for (int appt = 1; appt <= MAX_APPOINTMENTS; appt++) {
                pen.seek((appointment.getAppointmentNumber() - 1) * recordSize);

                pen.writeInt(appointment.getAppointmentNumber());
                pen.writeInt(appointment.getPatientNumber());
                pen.writeInt(appointment.getDoctorNumber());
                pen.writeChars(new SimpleDateFormat("dd-mm-yyyy").format(date));
                pen.writeChars(appointment.getStatus());
                pen.writeInt(timeSlots[appt][0].getHour());// start time slot
                pen.writeInt(timeSlots[appt][1].getHour());// end time slot
                appointment.setAppointmentNumber(appointment.getAppointmentNumber() + 1);
            }

        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void viewAppointments() {
        RandomAccessFile read = null;
        try {
            read = new RandomAccessFile("appointment_times.txt", "r");
            while (read.getFilePointer() < read.length()) {
                int appointmentNumber = read.readInt();
                int patientNumber = read.readInt();
                int doctorNumber = read.readInt();
                char[] schedule = new char[dateSize];
                for (int i = 0; i <= dateSize; i++) {
                    schedule[i] = read.readChar();
                }
                String date= new String (schedule);

                char[] status = new char[statusSize];
                for (int i = 0; i < statusSize; i++) {
                    status[i] = read.readChar();
                }
                String stat = new String(status);
                int startTime = read.readInt();
                int endTimeSlot = read.readInt();
                System.out.println("------------------------------------------------");
                System.out.println("Dashboard");
                System.out.println("------------------------------------------------");
                System.out.println("Appointment Number: " + appointmentNumber);
                System.out.println("Patient Number: " + patientNumber);
                System.out.println("Doctor Number: " + doctorNumber);
                System.out.println("Date: " + date);
                System.out.println("Status: " + stat);
                System.out.println("Time: " + startTime + " - " + endTimeSlot);
                System.out.println("------------------------------------------------");
            }
        } catch (IOException e) {
            System.out.println(e);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createFile(){
        RandomAccessFile pen=null;
        Appointment appointment = new Appointment();
        try{
            pen = new RandomAccessFile("appointment_times.txt", "rw");
            for(int entry=1; entry<=TotalRecords; entry++){
                pen.seek((entry-1)*recordSize);
                pen.writeInt(getAppointmentNumber());
                pen.writeInt(getPatientNumber());
                pen.writeInt(getDoctorNumber());
                String date = String.format("%-10s", getAppointmentDate()).substring(0,10);
                pen.writeChars(date);
                pen.writeChars(String.format("%-9s", getStatus()).substring(0,9));
                int starttime=0;//time slots
                int endtime=0;//time slots
                pen.writeInt( starttime);
                pen.writeInt( endtime);
                
            }
            if(pen.length() == (TotalRecords*recordSize)){
                System.out.println("File created successfully");
            }
            pen.close();
        }catch(IOException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void start(){        
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("------------------------------------------------");
            System.out.println("         Welcome to Appointment Scheduling   ");
            System.out.println("         Choose dashboard to enter:          ");
            System.out.println("         1) Patient Dashboard                ");
            System.out.println("         2) Doctor Dashboard                 ");
            System.out.println("         3) Exit                             ");
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Patient Number: ");
                    setPatientNumber(scanner.nextInt());
                    patientDashboard();
                    break;
                case 2:
                    System.out.println("Enter Doctor Number: ");
                    setDoctorNumber(scanner.nextInt());
                    doctorDashboard();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice< 1 && choice > 3);
    }


    public static void main(String[] args) {
        Appointment appt = new Appointment();
        appt.start();
        
}
}