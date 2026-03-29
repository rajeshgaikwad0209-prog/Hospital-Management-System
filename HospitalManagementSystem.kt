class Doctor(docId: String, docName: String, docSpecialty: String, docContact: String) {
    var id: String = docId
    var name: String = docName
    var specialty: String = docSpecialty
    var contact: String = docContact
    var activePatients: Int = 0
    var totalPatientsTreated: Int = 0

    fun displayInfo() {
        println("ID: $id | Dr. $name | Specialty: $specialty | Contact: $contact | Active Patients: $activePatients | Total Treated: $totalPatientsTreated")
    }
}

class Patient(patId: String, patName: String, patIllness: String, patAge: Int) {
    var id: String = patId
    var name: String = patName
    var illness: String = patIllness
    var age: Int = patAge
    var isAdmitted: Boolean = false
    var totalBillPaid: Int = 0

    fun displayInfo() {
        val status = if (isAdmitted) "Admitted" else "Not Admitted"
        println("ID: $id | Name: $name | Age: $age | Illness: $illness | Status: $status | Bills Paid: Rs.$totalBillPaid")
    }
}

class AdmissionRecord(pId: String, dId: String, day: Int) {
    var patientId: String = pId
    var doctorId: String = dId
    var dayAdmitted: Int = day
    var isDischarged: Boolean = false
    var dayDischarged: Int = -1
    var billAmount: Int = 0

    fun displayInfo() {
        val status = if (isDischarged) "Discharged on Day $dayDischarged | Bill: Rs.$billAmount" else "Active since Day $dayAdmitted"
        println("Patient: $patientId | Doctor: $doctorId | Admitted: Day $dayAdmitted | Status: $status")
    }
}

class BillingSystem {
    val DAILY_RATE: Int = 1000
    val MIN_DAYS: Int = 1

    fun calculateBill(dayAdmitted: Int, dayDischarged: Int): Int {
        var daysStayed = dayDischarged - dayAdmitted
        if (daysStayed < MIN_DAYS) daysStayed = MIN_DAYS
        return daysStayed * DAILY_RATE
    }

    fun printSummary(daysStayed: Int, bill: Int, patientName: String, doctorName: String) {
        println("\n========= DISCHARGE SUMMARY =========")
        println("  Patient  : $patientName")
        println("  Doctor   : Dr. $doctorName")
        println("  Days Stayed : $daysStayed")
        println("  Total Bill  : Rs.$bill")
        println("=====================================")
    }
}

class HospitalManagement {
    var doctors = arrayOfNulls<Doctor>(15)
    var patients = arrayOfNulls<Patient>(15)
    var admissions = arrayOfNulls<AdmissionRecord>(30)
    var currentDay: Int = 1
    val billing = BillingSystem()

    fun advanceDay() {
        currentDay++
        println("\n>>> Day advanced. It is now Day $currentDay <<<")
        checkLongStayPatients()
    }

    fun checkLongStayPatients() {
        var count = 0
        for (i in 0 until 30) {
            val a = admissions[i]
            if (a != null && !a.isDischarged) {
                val daysStayed = currentDay - a.dayAdmitted
                if (daysStayed >= 7) {
                    val projectedBill = billing.calculateBill(a.dayAdmitted, currentDay)
                    println("  [LONG STAY] Patient ${a.patientId} under Dr. ${a.doctorId} — ${daysStayed} day(s) admitted. Projected bill: Rs.$projectedBill")
                    count++
                }
            }
        }
        if (count == 0) println("  No long-stay alerts today.")
    }

    fun addDoctor(id: String, name: String, specialty: String, contact: String) {
        for (i in 0 until 15) {
            if (doctors[i] != null && doctors[i]?.id == id) {
                println("Hospital: Doctor ID '$id' already exists.")
                return
            }
        }
        for (i in 0 until 15) {
            if (doctors[i] == null) {
                doctors[i] = Doctor(id, name, specialty, contact)
                println("Hospital: Registered Dr. $name | Specialty: $specialty | Contact: $contact")
                return
            }
        }
        println("Hospital: Doctor registry is full.")
    }

    fun addPatient(id: String, name: String, illness: String, age: Int) {
        for (i in 0 until 15) {
            if (patients[i] != null && patients[i]?.id == id) {
                println("Hospital: Patient ID '$id' already exists.")
                return
            }
        }
        for (i in 0 until 15) {
            if (patients[i] == null) {
                patients[i] = Patient(id, name, illness, age)
                println("Hospital: Registered patient '$name' | Age: $age | Illness: $illness")
                return
            }
        }
        println("Hospital: Patient registry is full.")
    }

    fun displayDoctors() {
        println("\n--- Doctor Directory ---")
        var count = 0
        for (i in 0 until 15) {
            val d = doctors[i]
            if (d != null) {
                d.displayInfo()
                count++
            }
        }
        if (count == 0) println("No doctors registered.")
        else println("Total doctors: $count")
    }

    fun displayPatients() {
        println("\n--- Patient Registry ---")
        var count = 0
        for (i in 0 until 15) {
            val p = patients[i]
            if (p != null) {
                p.displayInfo()
                count++
            }
        }
        if (count == 0) println("No patients registered.")
        else println("Total patients: $count")
    }

    fun displayAdmissionHistory() {
        println("\n--- Admission History ---")
        var count = 0
        for (i in 0 until 30) {
            val a = admissions[i]
            if (a != null) {
                a.displayInfo()
                count++
            }
        }
        if (count == 0) println("No admission records found.")
        else println("Total records: $count")
    }

    fun searchPatient(keyword: String) {
        println("\n--- Search Results for '$keyword' ---")
        var count = 0
        for (i in 0 until 15) {
            val p = patients[i]
            if (p != null && (p.name.contains(keyword, ignoreCase = true) || p.illness.contains(keyword, ignoreCase = true))) {
                p.displayInfo()
                count++
            }
        }
        if (count == 0) println("No matching patients found.")
    }

    fun deleteDoctor(doctorId: String) {
        for (i in 0 until 15) {
            val d = doctors[i]
            if (d != null && d.id == doctorId) {
                if (d.activePatients > 0) {
                    println("Error: Cannot remove Dr. ${d.name} — they are currently treating ${d.activePatients} patient(s).")
                    return
                }
                println("Hospital: Dr. ${d.name} has been removed from the system.")
                doctors[i] = null
                return
            }
        }
        println("Hospital: Doctor ID '$doctorId' not found.")
    }

    fun deletePatient(patientId: String) {
        for (i in 0 until 15) {
            val p = patients[i]
            if (p != null && p.id == patientId) {
                if (p.isAdmitted) {
                    println("Error: Cannot remove '${p.name}' — they are currently admitted. Discharge them first.")
                    return
                }
                println("Hospital: Patient '${p.name}' removed from registry.")
                patients[i] = null
                return
            }
        }
        println("Hospital: Patient ID '$patientId' not found.")
    }

    fun admitPatient(patientId: String, doctorId: String) {
        var foundPatient: Patient? = null
        var foundDoctor: Doctor? = null

        for (i in 0 until 15) {
            if (patients[i]?.id == patientId) foundPatient = patients[i]
            if (doctors[i]?.id == doctorId) foundDoctor = doctors[i]
        }

        if (foundPatient == null || foundDoctor == null) {
            println("Hospital: Invalid Patient ID or Doctor ID.")
            return
        }

        if (foundPatient.isAdmitted) {
            println("Hospital: '${foundPatient.name}' is already admitted.")
            return
        }

        if (foundDoctor.activePatients >= 5) {
            println("Error: Dr. ${foundDoctor.name} has reached the patient limit (5 active patients max).")
            return
        }

        for (i in 0 until 30) {
            if (admissions[i] == null) {
                admissions[i] = AdmissionRecord(patientId, doctorId, currentDay)
                foundPatient.isAdmitted = true
                foundDoctor.activePatients++
                foundDoctor.totalPatientsTreated++
                println("Hospital: '${foundPatient.name}' admitted under Dr. ${foundDoctor.name} on Day $currentDay.")
                println("         Daily charge: Rs.${billing.DAILY_RATE}/day")
                return
            }
        }
        println("Hospital: Ward is full. Cannot process more admissions.")
    }

    fun dischargePatient(patientId: String, doctorId: String) {
        var foundRecord: AdmissionRecord? = null
        var foundDoctor: Doctor? = null
        var foundPatient: Patient? = null

        for (i in 0 until 30) {
            val a = admissions[i]
            if (a != null && a.patientId == patientId && a.doctorId == doctorId && !a.isDischarged) {
                foundRecord = a
                break
            }
        }

        for (i in 0 until 15) {
            if (patients[i]?.id == patientId) foundPatient = patients[i]
            if (doctors[i]?.id == doctorId) foundDoctor = doctors[i]
        }

        if (foundRecord == null) {
            println("Hospital: No active admission found for Patient '$patientId' under Doctor '$doctorId'.")
            return
        }

        foundRecord.isDischarged = true
        foundRecord.dayDischarged = currentDay

        var daysStayed = currentDay - foundRecord.dayAdmitted
        if (daysStayed < 1) daysStayed = 1

        val bill = billing.calculateBill(foundRecord.dayAdmitted, currentDay)
        foundRecord.billAmount = bill

        billing.printSummary(daysStayed, bill, foundPatient?.name ?: patientId, foundDoctor?.name ?: doctorId)

        for (i in 0 until 15) {
            if (patients[i]?.id == patientId) {
                patients[i]?.isAdmitted = false
                patients[i]?.totalBillPaid = (patients[i]?.totalBillPaid ?: 0) + bill
            }
            if (doctors[i]?.id == doctorId) {
                doctors[i]?.activePatients = (doctors[i]?.activePatients ?: 1) - 1
            }
        }
        println("Hospital: Patient discharged successfully on Day $currentDay.")
    }
}

class HospitalApp {
    val hospital = HospitalManagement()

    fun run() {
        var running = true
        println("╔════════════════════════════════════════╗")
        println("║    Advanced Hospital Management System  ║")
        println("╚════════════════════════════════════════╝")

        while (running) {
            println("\n--- Menu (Today: Day ${hospital.currentDay}) ---")
            println(" 1. Display Doctors        2. Display Patients")
            println(" 3. Register Doctor        4. Register Patient")
            println(" 5. Delete Doctor          6. Delete Patient")
            println(" 7. Admit Patient          8. Discharge Patient")
            println(" 9. Admission History     10. Search Patient")
            println("11. Advance Day           12. Exit")
            print("Enter choice: ")

            when (readln().trim()) {
                "1"  -> hospital.displayDoctors()
                "2"  -> hospital.displayPatients()
                "3"  -> {
                    print("Doctor ID: "); val id = readln()
                    print("Name: "); val name = readln()
                    print("Specialty: "); val specialty = readln()
                    print("Contact Number: "); val contact = readln()
                    hospital.addDoctor(id, name, specialty, contact)
                }
                "4"  -> {
                    print("Patient ID: "); val id = readln()
                    print("Name: "); val name = readln()
                    print("Illness: "); val illness = readln()
                    print("Age: "); val age = readln().toIntOrNull() ?: 0
                    hospital.addPatient(id, name, illness, age)
                }
                "5"  -> {
                    hospital.displayDoctors()
                    print("Doctor ID to delete: ")
                    hospital.deleteDoctor(readln())
                }
                "6"  -> {
                    hospital.displayPatients()
                    print("Patient ID to delete: ")
                    hospital.deletePatient(readln())
                }
                "7"  -> {
                    hospital.displayPatients()
                    hospital.displayDoctors()
                    print("Patient ID to admit: "); val pId = readln()
                    print("Doctor ID to assign: "); val dId = readln()
                    hospital.admitPatient(pId, dId)
                }
                "8"  -> {
                    hospital.displayPatients()
                    hospital.displayDoctors()
                    print("Patient ID to discharge: "); val pId = readln()
                    print("Assigned Doctor ID: "); val dId = readln()
                    hospital.dischargePatient(pId, dId)
                }
                "9"  -> hospital.displayAdmissionHistory()
                "10" -> {
                    print("Search by name or illness: ")
                    hospital.searchPatient(readln())
                }
                "11" -> hospital.advanceDay()
                "12" -> { running = false; println("Goodbye! 🏥") }
                else -> println("Invalid choice. Please enter 1–12.")
            }
        }
    }
}

fun main() {
    HospitalApp().run()
}