import java.io.IOException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

class WrongAge extends Exception {}
class WrongStudentName extends Exception { }
class WrongDateOfBirth extends Exception {}

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            }
          catch(WrongAge e){
            System.out.println("Błędny wiek studenta!");
          }
          catch(WrongDateOfBirth e){
            System.out.println("Błędna data urodzenia!");
          }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static int ReadAge() throws WrongAge {

      System.out.println("Podaj wiek: ");
      int age = scan.nextInt();
      if(age <0 || age> 50)
      throw new WrongAge();
      return age;
    }
    public static String ReadDate() throws WrongDateOfBirth { 
  scan.nextLine(); 
  System.out.println("Podaj datę urodzenia DD-MM-YYYY"); 
  String date = scan.nextLine(); 
  try { 
  DateFormat format = new SimpleDateFormat("dd-MM-yyyy"); 
  format.setLenient(false); 
  format.parse(date); 
  return date; 
  } catch(ParseException e){ 
  throw new WrongDateOfBirth(); 
} 
      }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }

    public static void exercise1() throws IOException, WrongStudentName,WrongAge,       WrongDateOfBirth{
        var name = ReadName();
        var age = ReadAge();
        var date = ReadDate();
        (new Service1()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service1()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service1()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}