/**
 * 
 */
package src.main.java;

/**
 * @author elliottplack
 *
 */

import java.io.*;
import java.util.*;

public class ExamGrade {
	public static void main (String args[]) throws FileNotFoundException 
    {
        Scanner fileLength = new Scanner(new File("src/main/resources/grades.txt")); //provide file name from outside
        int counter = 0; //keep track of how many integers in the file
        while(fileLength.hasNextInt()) 
        {
            counter++;
            fileLength.nextInt();
        }
       
        Scanner arrayBuild = new Scanner(new File("src/main/resources/grades.txt")); 
        int grades[] = new int[counter];
        for(int i=0;i<counter;i++)
        {
            grades[i]=arrayBuild.nextInt(); //fill the array with the integers
        }
       
        /* test
        String gradesOut = "";
        gradesOut = Arrays.toString(grades);
        System.out.print(gradesOut);
        */
        fileLength.close();
        arrayBuild.close();
        
        double average = calculateAverage(grades);
        System.out.print(average);
        
        
    }
	public static double calculateAverage(int[] grades) {
		int sum = 0;
		double average = 0;
		
		for (int i = 0; i < grades.length; i++) {
			sum += grades[i];
		}
		average = (double) sum / grades.length;
		return average;
	}
}