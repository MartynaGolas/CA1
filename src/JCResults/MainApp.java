package JCResults;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MainApp
{
    public static void main(String[] args)
    {
        try
        {
            Scanner scan = new Scanner(new File("JC_Results.txt"));
            scan.useDelimiter(",|\r\n");
            while(scan.hasNextLine())
            {
                int[] temp = new int[17];
                int studentNo;
                int count = 0;
                int count2 = 0;
                int[] codes = new int[8];
                int[] grades = new int[8];

                if(scan.hasNextInt())
                {
                    for (int i = 0; i < temp.length; i++)
                    {
                        temp[i] = scan.nextInt();
                        if(i % 2 != 0)
                        {
                            codes[count] = temp[i];
                            count++;
                        }
                        else if(i != 0)
                        {
                            grades[count2] = temp[i];
                            count2++;
                        }
                    }

                    studentNo = temp[0];
                    System.out.println(studentNo + Arrays.toString(codes) + "\n" + Arrays.toString(grades));
                    int[] selectedFive = selectFiveGrades(codes, grades);

                }
                else if(scan.hasNext())
                {
                    scan.nextLine();
                }
            }

            scan.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("This file does not exist. Make sure to place a file named 'JC_Results.txt' in the main project directory.");
        }

    }

    public static int[] selectFiveGrades(int[] codes, int[] grades)
    {
        int[] selectedGrades = new int[5];
        int[] tempArray = new int[5];
        int count = 0;
        int count2 = 0;
        int biggest1 = 0;
        int biggest2 = 0;

        for(int i = 0; i < codes.length; i++)
        {
            if (codes[i] == 1 || codes[i] == 2 || codes[i] == 3)
            {
                selectedGrades[count] = grades[i];
                count++;
            }
            else
            {
                if(codes[i] != 218)
                {
                    tempArray[count2] = grades[i];
                    count2++;
                }
            }
        }

        for(int j = 0; j < tempArray.length; j++)
        {
            if(tempArray[j] >= biggest1)
            {
                biggest2 = biggest1;
                biggest1= tempArray[j];
            }
            else if(tempArray[j] >= biggest2)
            {
                biggest2 = tempArray[j];
            }
        }

        selectedGrades[3] = biggest2;
        selectedGrades[4] = biggest1;

        return selectedGrades;
    }

}
