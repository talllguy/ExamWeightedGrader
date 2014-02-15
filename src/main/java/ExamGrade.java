package src.main.java;

/**************************************************************
COSC 600
Elliott Plack
12 FEB 2014                           Due date: 14 FEB 2014

Problem: Take a list of exam scores and process them by computing
	the average and then assigning a grade based on each score's
	deviation away from the average. The standards are below 10%
	difference = unsatisfactory, between 10% over and under =
	satisfactory, over 10% better = outstanding. Process the
	grade for each score in Java and output the results to a
	neatly ordered file.
Algorithm:
	1. read the grades from a file to an array
	2. get the average of the scores in the array
	3. make another array of the grades for each score
	4. output the scores and grades to a column file

***************************************************************/

import java.io.*;
import java.util.*;

public class ExamGrade {
	public static void main (String args[]) throws IOException 
    {
        int[] grades = gradesGetter(); // method to get the grades from a file
        double average = calculateAverage(grades); // method to calculate average
        char[] gradeScoreArray = gradeScoreArrayCalc(grades,average); // method to store calculated grades in an array
		makeOutputFile(grades, gradeScoreArray); // method to write the final file
        
    }

	private static int[] gradesGetter() throws FileNotFoundException {
		String path = "src/main/resources/grades.txt"; // setup a path var
		Scanner fileLength = new Scanner(new File(path)); //provide file name from outside
        int counter = 0; //keep track of how many integers in the file
        while(fileLength.hasNextInt()) // counting the numbers in file
        {
            counter++;
            fileLength.nextInt();
        }
       
        Scanner arrayBuild = new Scanner(new File(path));
        int grades[] = new int[counter];
        for(int i=0;i<counter;i++)
        {
            grades[i]=arrayBuild.nextInt(); //fill the array with the integers
        }
        // close everything for good measure
        fileLength.close();
        arrayBuild.close();
		
		return grades;
	}

	private static char scoreGrade(int gradeIndex, double average) {
		// determines what grade to assign by calculating pct change and using logic
		double percentage = 0;
		percentage = ((((double)gradeIndex - average) / average) * 100);
		
		if (percentage >= 10)
			return 'O';
		else if (percentage < 10 && percentage > -10)
			return 'S';
		else if (percentage < -10)
			return 'U';
		else
			return 'F'; // fail condition
	}
	
	private static char[] gradeScoreArrayCalc(int[] grades, double average) {
		// takes each grade calcuation and puts it in an array
		char[] tempArray = new char[grades.length]; // make a temporary array for the method
		for (int i = 0; i < grades.length; i++) {
        	char gradeScore = scoreGrade(grades[i], average); // get the grade
        	tempArray[i] = gradeScore; // put it in the array
        }
		return tempArray;
	}

	private static double calculateAverage(int[] grades) {
		// Calculates the average of the score's array
		int sum = 0;
		double average = 0;
		
		for (int i = 0; i < grades.length; i++) {
			sum += grades[i];
		}
		average = (double) sum / grades.length;
		return average;
	}


	private static void makeOutputFile(int[] grades, char[] gradeScoreArray) throws IOException {
	// writes the formatted output to a file	
	PrintWriter out = new PrintWriter("target/output.txt"); // create printwriter
	
	String col1Heading = "Test Score"; // set up defaults
	String col2Heading = "Grade";
	String divider = "--------------------------";
	
	out.printf("%s %15s %n",col1Heading,col2Heading); // print the top row
	out.printf("%s %n",divider); // divider
	
	for (int i = 0; i < grades.length; i++)
		out.printf("%6d %17s %n",grades[i],gradeScoreArray[i]); // prints the array value for each iteration

	out.close();
	}
}
