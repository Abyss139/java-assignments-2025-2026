/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlysinhvien;

/**
 *
 * @author phamd
 */
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    private static final ArrayList<Student> studentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n== QUAN LY SINH VIEN ==");
            System.out.println("1. Them sinh vien");
            System.out.println("2. Tim kiem sinh vien");
            System.out.println("3. Cap nhat thong tin");
            System.out.println("4. Xoa sinh vien");
            System.out.println("5. Thong ke (Danh sach & GPA TB)");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            
            int choice = getValidInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: searchStudent(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: showStatistics(); break;
                case 0: 
                    System.out.println("Ket thuc chuong trinh.");
                    System.exit(0);
                default: 
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private static void addStudent() {
        System.out.println("\n--- THEM SINH VIEN ---");
        System.out.print("Nhap ID: ");
        String id = scanner.nextLine();
       
        if (findStudentById(id) != null) {
            System.out.println("Loi!!! ID nay da ton tai!");
            return;
        }

        System.out.print("Nhap Ten: ");
        String name = scanner.nextLine();

        System.out.print("Nhap Tuoi: ");
        int age = getValidInt();
        while (age <= 0) {
        System.out.print("Loi!!! Tuoi khong hop le! Nhap lai: ");
        age = getValidInt();
}
        System.out.print("Nhap GPA (0-10): ");
        double gpa = getValidDouble();
        while (gpa < 0 || gpa > 10) {
            System.out.print("Loi!!! GPA phai tu 0 den 10! Nhap lai: ");
            gpa = getValidDouble();
        }

        studentList.add(new Student(id, name, age, gpa));
        System.out.println("Them sinh vien thanh cong!");
    }

    private static void searchStudent() {
        System.out.println("\n--- TIM KIEM SINH VIEN ---");
        System.out.print("Nhap ID can tim: ");
        String id = scanner.nextLine();
        Student s = findStudentById(id);
        if (s != null) {
            System.out.println("Tim thay!!!: " + s);
        } else {
            System.out.println("Khong tim thay sinh vien co ID: " + id);
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- CAP NHAT THONG TIN ---");
        System.out.print("Nhap ID sinh vien can sua: ");
        String id = scanner.nextLine();
        Student s = findStudentById(id);
        
        if (s != null) {
            System.out.print("Nhap Ten moi: ");
            s.setName(scanner.nextLine());
            System.out.print("Nhap Tuoi moi: ");
            s.setAge(getValidInt());
            System.out.print("Nhap GPA moi: ");
            double gpa = getValidDouble();
            while (gpa < 0 || gpa > 10) {
                 System.out.print("GPA phai tu 0 -> 10! Nhap lai: ");
                 gpa = getValidDouble();
            }
            s.setGpa(gpa);
            System.out.println("Cap nhat thanh cong!");
        } else {
            System.out.println("Khong tim thay sinh vien!");
        }
    }

    private static void deleteStudent() {
        System.out.println("\n--- XOA SINH VIEN ---");
        System.out.print("Nhap ID sinh vien can xoa: ");
        String id = scanner.nextLine();
        Student s = findStudentById(id);
        
        if (s != null) {
            studentList.remove(s);
            System.out.println("Da xoa sinh vien co ID: " + id);
        } else {
            System.out.println("Khong tim thay sinh vien!");
        }
    }

    private static void showStatistics() {
        System.out.println("\n--- THONG KE ---");
        if (studentList.isEmpty()) {
            System.out.println("Danh sach trong!");
            return;
        }
        
        System.out.println("| ID         | Ten                  | Tuoi  | GPA   |");
        System.out.println("-----------------------------------------------------");
        double totalGpa = 0;
        for (Student s : studentList) {
            System.out.println(s);
            totalGpa += s.getGpa();
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Tong so sinh vien: " + studentList.size());
        System.out.printf("Diem trung binh lop: %.2f\n", (totalGpa / studentList.size()));
    }

    private static Student findStudentById(String id) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
    private static int getValidInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Loi!!! Vui long nhap so nguyen! Nhap lai: ");
            }
        }
    }

    private static double getValidDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Loi!!! Vui long nhap so thuc! Nhap lai: ");
            }
        }
    }
}
