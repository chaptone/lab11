package student;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Display reminders of students having a birthday soon.
 * @author Rakkan Jintasatien Student ID :5810546722
 */
public class StudentApp {

	/**
	 * Print the names (and birthdays) of students having a birtday in the
	 * specified month.
	 * @param students list of students
	 * @param month the month to use in selecting bithdays
	 */
	public void filterAndPrint( List<Student> students, int month ) {
		for(Student s : students ) {
			if (s.getBirthdate().getMonthValue() == month)
	                  System.out.println( s );
		}
	}
	
	public void filterAndPrint( List<Student> students, Predicate<Student> filter ) {
		for(Student s : students ) {
			if (filter.test(s))
	                  System.out.println( s );
		}
	}
	
	public void filterAndPrint( List<Student> students, Predicate<Student> filter ,Consumer<Student> action) {
		for(Student s : students ) {
			if (filter.test(s))
	                  action.accept( s );
		}
	}
	
	public static void main(String[] args) {
		List<Student> students = Registrar.getInstance().getStudents();
		StudentApp app = new StudentApp();
		app.filterAndPrint(students, 7 /* may */);
		Predicate<Student> temp =  month -> month.getBirthdate().getMonthValue() !=5;
		Consumer<Student> action = student -> System.out.println(student.getFirstname()+" "+student.getLastname()+" will have birthday on "+student.getBirthdate().getDayOfMonth()+" "+student.getBirthdate().getMonth()+".");
		app.filterAndPrint(students, 1 /* may */);
		app.filterAndPrint(students, month ->  month.getBirthdate().getMonthValue() == 7);
		app.filterAndPrint(students, month -> month.getBirthdate().getMonthValue() == 5, 
				student -> System.out.printf("%s %s will have birthday on %d %s.\n"
						,student.getFirstname(),student.getLastname(),student.getBirthdate().getDayOfMonth(),student.getBirthdate().getMonth()));
//		app.filterAndPrint(students, test, action);

	}
}
