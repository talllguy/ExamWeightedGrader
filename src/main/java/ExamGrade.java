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
	public static void main (String args[]) throws IOException 
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
       
        fileLength.close();
        arrayBuild.close();
        
        double average = calculateAverage(grades);
        char[] gradeScoreArray = gradeScoreArrayCalc(grades,average);

		makeOutputFile(grades, gradeScoreArray);
        
    }

	private static char[] gradeScoreArrayCalc(int[] grades, double average) {
		// TODO Auto-generated method stub
		char[] tempArray = new char[grades.length];
		for (int i = 0; i < grades.length; i++) {
        	char gradeScore = scoreGrade(grades[i], average);
        	tempArray[i] = gradeScore;
        }
		return tempArray;
	}

	private static void makeOutputFile(int[] grades, char[] gradeScoreArray) throws IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = new PrintWriter("target/output.txt");
		
		String col1Heading = "Test Score";
		String col2Heading = "Grade";
		String divider = "--------------------------";
		
		out.printf("%s %15s %n",col1Heading,col2Heading);
		out.printf("%s %n",divider);
		
		for (int i = 0; i < grades.length; i++)
			out.printf("%d %15s %n",grades[i],gradeScoreArray[i]);
		
		
		out.close();
		
		
	}

	private static char scoreGrade(int gradeIndex, double average) {
		// TODO Auto-generated method stub
		double percentage = 0;
		percentage = (((gradeIndex - average) / average) * 100);
		
		if (percentage >= 10)
			return 'O';
		else if (percentage < 10 && percentage > -10)
			return 'S';
		else if (percentage < -10)
			return 'U';
		else
			return 'F'; // fail condition
	}

	private static double calculateAverage(int[] grades) {
		int sum = 0;
		double average = 0;
		
		for (int i = 0; i < grades.length; i++) {
			sum += grades[i];
		}
		average = (double) sum / grades.length;
		return average;
	}
}